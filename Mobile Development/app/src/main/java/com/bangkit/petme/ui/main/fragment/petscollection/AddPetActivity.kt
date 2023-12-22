package com.bangkit.petme.ui.main.fragment.petscollection

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.exifinterface.media.ExifInterface
import androidx.lifecycle.ViewModelProvider
import com.bangkit.petme.R
import com.bangkit.petme.api.Response.AddPostResponse
import com.bangkit.petme.api.Retrofit.ApiConfig
import com.bangkit.petme.databinding.ActivityAddPetBinding
import com.bangkit.petme.utils.Utils
import com.bangkit.petme.ml.CatDog
import com.bangkit.petme.ui.main.MainActivity
import com.bangkit.petme.utils.Utils.getImageUri
import com.bangkit.petme.utils.Utils.reduceFileImage
import com.bangkit.petme.viewmodel.MainViewModel
import com.bangkit.petme.viewmodel.ViewModelFactory
import okhttp3.FormBody
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val FILENAME_FORMAT = "yyyyMMdd_HHmmss"
private const val MAXIMAL_SIZE = 1000000
private val timeStamp: String = SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(Date())

class AddPetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddPetBinding
    private var currentImageUri: Uri? = null
    private lateinit var id_animal: String
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnGallery.setOnClickListener {
            startGallery()
        }

        binding.btnCamera.setOnClickListener {
            startCamera()
        }

        binding.btnAdd.setOnClickListener {
            uploadImage()
        }

        mainViewModel =
            ViewModelProvider(this, ViewModelFactory.getInstance(this.application)).get(
                MainViewModel::class.java
            )
    }

    private fun predict() {
        var label = application.assets.open("label.txt").bufferedReader().readLines()
        var imageProcessor = ImageProcessor.Builder()
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

        id_animal = label[maxIdx]
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
            binding.ivPhoto.setImageURI(it)
            predict()
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

    fun uriToFile(imageUri: Uri, context: Context): File {
        val myFile = createCustomTempFile(context)
        val inputStream = context.contentResolver.openInputStream(imageUri) as InputStream
        val outputStream = FileOutputStream(myFile)
        val buffer = ByteArray(1024)
        var length: Int
        while (inputStream.read(buffer).also { length = it } > 0) outputStream.write(
            buffer,
            0,
            length
        )
        outputStream.close()
        inputStream.close()
        return myFile
    }

    private fun uploadImage() {
        currentImageUri?.let { uri ->
            val imageFile = uriToFile(uri, this).reduceFileImage()
            Log.d("Image File", "showImage: ${imageFile.path}")
            val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())

            val title = binding.etTitle.text.toString()
            val breed = binding.etBreeds.text.toString()
            val description = binding.etDescription.text.toString()
            val idAnimal = id_animal.toString()
            val longitude = mainViewModel.getLongitude()!!
            val latitude = mainViewModel.getLatitude()!!

            Log.d("ISI VARIABELNYA", "$title, $breed, $description, $idAnimal, $longitude, $latitude")
            val response = ApiConfig.getApiService().uploadImage("Bearer ${mainViewModel.getToken()}",
                    RequestBody.create("multipart/form-data".toMediaType(), "$title"),
                    RequestBody.create("multipart/form-data".toMediaType(), "$breed"),
                    RequestBody.create("multipart/form-data".toMediaType(), "$description"),
                    RequestBody.create("multipart/form-data".toMediaType(), "$idAnimal"),
                    RequestBody.create("multipart/form-data".toMediaType(), "${mainViewModel.getLongitude()}"),
                    RequestBody.create("multipart/form-data".toMediaType(), "${mainViewModel.getLatitude()}"),
                    MultipartBody.Part.createFormData("file", imageFile.name, requestImageFile))
//

            response.enqueue(object : Callback<AddPostResponse> {
                override fun onResponse(
                    call: Call<AddPostResponse>,
                    response: Response<AddPostResponse>
                ) {
                    showLoading(true)
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            showLoading(false)
                            finish()
                        }
                    }
                }

                override fun onFailure(call: Call<AddPostResponse>, t: Throwable) {
                    showLoading(false)
                }
            })
        }
    }

    fun createCustomTempFile(context: Context): File {
        val filesDir = context.externalCacheDir
        return File.createTempFile(timeStamp, ".jpg", filesDir)
    }

    fun File.reduceFileImage(): File {
        val file = this
        val bitmap = BitmapFactory.decodeFile(file.path).getRotatedBitmap(file)
        var compressQuality = 100
        var streamLength: Int
        do {
            val bmpStream = ByteArrayOutputStream()
            bitmap?.compress(Bitmap.CompressFormat.JPEG, compressQuality, bmpStream)
            val bmpPicByteArray = bmpStream.toByteArray()
            streamLength = bmpPicByteArray.size
            compressQuality -= 5
        } while (streamLength > MAXIMAL_SIZE)
        bitmap?.compress(Bitmap.CompressFormat.JPEG, compressQuality, FileOutputStream(file))
        return file
    }

    fun Bitmap.getRotatedBitmap(file: File): Bitmap? {
        val orientation = ExifInterface(file).getAttributeInt(
            ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED
        )
        return when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> rotateImage(this, 90F)
            ExifInterface.ORIENTATION_ROTATE_180 -> rotateImage(this, 180F)
            ExifInterface.ORIENTATION_ROTATE_270 -> rotateImage(this, 270F)
            ExifInterface.ORIENTATION_NORMAL -> this
            else -> this
        }
    }

    fun rotateImage(source: Bitmap, angle: Float): Bitmap? {
        val matrix = Matrix()
        matrix.postRotate(angle)
        return Bitmap.createBitmap(
            source, 0, 0, source.width, source.height, matrix, true
        )
    }

    fun showLoading(isLoading: Boolean){
        if(isLoading == true){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.INVISIBLE
        }
    }
}


