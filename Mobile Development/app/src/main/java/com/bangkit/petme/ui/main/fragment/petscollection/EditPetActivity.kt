package com.bangkit.petme.ui.main.fragment.petscollection

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.bangkit.petme.databinding.ActivityEditPetBinding
import com.bangkit.petme.utils.getImageUri
import com.bangkit.petme.ml.CatDog
import com.bangkit.petme.ui.main.MainActivity
import com.bangkit.petme.viewmodel.PetsCollectionViewModel
import com.bangkit.petme.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer


class EditPetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditPetBinding
    private var currentImageUri: Uri? = null
    private lateinit var petCollectionViewModel: PetsCollectionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditPetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        petCollectionViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this.application)).get(
            PetsCollectionViewModel::class.java)

        binding.edTitle.setText(intent.getStringExtra(NAME))
        binding.edDescription.setText(intent.getStringExtra(DESCRIPTION))
        binding.edBreed.setText(intent.getStringExtra(TYPE))
        Glide.with(this)
            .load(intent.getStringExtra(IMAGE)) // URL Gambar
            .into(binding.ivImagePet)
        binding.btnBack.setOnClickListener {
           finish()
        }

        binding.btnGallery.setOnClickListener {
            startGallery()
        }

        binding.btnCamera.setOnClickListener {
            startCamera()
        }

        binding.btnEdit.setOnClickListener {
            predict()
        }


        binding.btnDelete.setOnClickListener {
            petCollectionViewModel.deletePost(intent.getIntExtra(ID, 0))
            startActivity(Intent(this, MainActivity::class.java).apply {
                putExtra("page", "PetCollection")
            })
        }
    }
    private fun predict() {
        var label = application.assets.open("label.txt").bufferedReader().readLines()

        var imageProcessor = ImageProcessor.Builder()
//            .add(NormalizeOp(0.0f, 255.0f))
//            .add(TransformToGrayscaleOp())
            .add(ResizeOp(224, 224, ResizeOp.ResizeMethod.BILINEAR))
            .build()

        val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, currentImageUri)

        var tensorImage = TensorImage(DataType.FLOAT32)
        tensorImage.load(bitmap)

        tensorImage = imageProcessor.process(tensorImage)

        val model = CatDog.newInstance(this)

        val inputFeature0 =
            TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)
        inputFeature0.loadBuffer(tensorImage.buffer)

        val outputs = model.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer.floatArray

        var maxIdx = 0
        outputFeature0.forEachIndexed { index, fl ->
            if (outputFeature0[maxIdx] < fl) {
                maxIdx = index
            }
        }

        binding.tvTitle.setText(label[maxIdx])

        model.close()
    }

    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            showImage()
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.ivImagePet.setImageURI(it)
        }
    }

    private fun startCamera() {
        currentImageUri = getImageUri(this)
        launcherIntentCamera.launch(currentImageUri)
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            showImage()
        }
    }

    companion object {
        val ID: String = "id"
        val NAME: String = "name"
        val TYPE: String = "type"
        val IMAGE: String = "image"
        val DESCRIPTION: String = "description"
    }
}