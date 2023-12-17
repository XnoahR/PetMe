package com.bangkit.petme.ui.main.fragment.petscollection

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.bangkit.petme.BuildConfig
import com.bangkit.petme.ui.main.MainActivity
import com.bangkit.petme.databinding.ActivityEditPetBinding
import com.bangkit.petme.getImageUri
import com.bangkit.petme.ml.CatDog
import com.bumptech.glide.Glide
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class EditPetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditPetBinding
    private var currentImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditPetBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        val NAME: String = "name"
        val TYPE: String = "type"
        val IMAGE: String = "image"
        val DESCRIPTION: String = "description"
    }
}