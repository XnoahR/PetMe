package com.bangkit.petme.ui.main.fragment.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony.Carriers.PASSWORD
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bangkit.petme.ui.main.MainActivity
import com.bangkit.petme.databinding.ActivityEditProfileBinding
import com.bangkit.petme.viewmodel.ProfileViewModel
import com.bangkit.petme.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch
import retrofit2.HttpException


class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val profileViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this.application)).get(
            ProfileViewModel::class.java)

        binding.edName.setText(intent.getStringExtra(NAME))
        binding.edEmail.setText(intent.getStringExtra(EMAIL))
        binding.edPhoneNumber.setText(intent.getStringExtra(PHONE))
        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnEdit.setOnClickListener {
            if(binding.edName.text.toString() == ""){
                showToast("Please input name")
            }else if(binding.edEmail.text.toString() == ""){
                showToast("Please input email")
            }else if(!isValidEmail(binding.edEmail.text.toString())){
                Toast.makeText(this, "Please input valid email", Toast.LENGTH_SHORT).show()
                binding.edEmail.setError("Please input valid email")
            }else if(binding.edPhoneNumber.text.toString() == ""){
                showToast("Please input phone number")
            }else if(binding.edCurrentPassword.text.toString() == ""){
                showToast("Please input current password")
            }else if(binding.edNewPassword.text.toString() == ""){
                showToast("Please input new password")
            }else if(binding.edCurrentPassword.text.toString() != intent.getStringExtra(PASSWORD)){
                showToast("Current Password is incorrect, Please try Again")
            }else{
                lifecycleScope.launch {
                    try {
                        val id = intent.getStringExtra(ID)!!.toInt()
                        val name = binding.edName.text.toString()
                        val email = binding.edEmail.text.toString()
                        val phone = binding.edPhoneNumber.text.toString()
                        val password = binding.edNewPassword.text.toString()
                        val editProfileResult = profileViewModel.editProfile(id, name, email, phone, password)
                        val status = editProfileResult.message
                        if (status == "Profile updated") {
                            Toast.makeText(
                                this@EditProfileActivity,
                                "Update Success",
                                Toast.LENGTH_SHORT
                            ).show()
                            startActivity(Intent(this@EditProfileActivity, MainActivity::class.java).apply {
                                putExtra("page", "Profile")
                            })
                        }
                    } catch (e: HttpException) {
                        Toast.makeText(
                            this@EditProfileActivity,
                            "Update Failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    companion object{
        val ID: String = "id"
        val NAME: String = "name"
        val PHONE: String = "phone"
        val EMAIL: String = "email"
        val PASSWORD: String = "password"
    }

    fun showToast(text: String){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun isValidEmail(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
}