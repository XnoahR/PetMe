package com.bangkit.petme.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bangkit.petme.R
import com.bangkit.petme.databinding.ActivityRegisterBinding
import com.bangkit.petme.ui.login.LoginActivity
import com.bangkit.petme.ui.main.MainActivity
import com.bangkit.petme.viewmodel.LoginViewModel
import com.bangkit.petme.viewmodel.RegisterViewModel
import com.bangkit.petme.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val hyperlinkText = SpannableString(getString(R.string.have_account))

        val clickableHyperlinkText = object : ClickableSpan() {
            override fun onClick(widget: View) {
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            }
        }

        hyperlinkText.setSpan(clickableHyperlinkText, 24, 29, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvToLogin.text = hyperlinkText
        binding.tvToLogin.movementMethod = LinkMovementMethod.getInstance()
        val registerViewModel =
            ViewModelProvider(this, ViewModelFactory.getInstance(this.application)).get(
                RegisterViewModel::class.java
            )
        registerViewModel.isLoading.observe(this) {
            if (it == true) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.INVISIBLE
            }
        }

        binding.btnRegister.setOnClickListener {
            if (binding.etName.text.toString() == "") {
                Toast.makeText(this, "Please input name", Toast.LENGTH_SHORT).show()
                binding.etName.setError("Please input name")
            }else if (binding.etEmail.text.toString() == "") {
                Toast.makeText(this, "Please input email", Toast.LENGTH_SHORT).show()
                binding.etEmail.setError("Please input email")
            }else if(!isValidEmail(binding.etEmail.text.toString())){
                Toast.makeText(this, "Please input valid email", Toast.LENGTH_SHORT).show()
                binding.etEmail.setError("Please input valid email")
            }else if (binding.etPhone.text.toString() == "") {
                Toast.makeText(this, "Please input phone number", Toast.LENGTH_SHORT).show()
                binding.etPhone.setError("Please input phone number")
            }else if (binding.etPassword.text.toString() == "") {
                Toast.makeText(this, "Please input password", Toast.LENGTH_SHORT).show()
                binding.etPassword.setError("Please input password")
            }else if (binding.etPasswordConfirmation.text.toString() == "") {
                Toast.makeText(this, "Please input password confirmation", Toast.LENGTH_SHORT).show()
                binding.etPasswordConfirmation.setError("Please input password confirmation")
            }else if(binding.etPassword.text.toString() != binding.etPasswordConfirmation.text.toString()){
                Toast.makeText(this, "Please input correct password confirmation", Toast.LENGTH_SHORT).show()
                binding.etPasswordConfirmation.setError("Please input correct password confirmation")
            } else {
                val name = binding.etName.text.toString()
                val email = binding.etEmail.text.toString()
                val phone = binding.etPhone.text.toString()
                val password = binding.etPassword.text.toString()
                lifecycleScope.launch {
                    try {
                        val registerResult = registerViewModel.register(name, email, phone, password)
                        val name = registerResult.name
                        if (name != null) {
                            Toast.makeText(this@RegisterActivity, "Registration Success, Please Login!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                            finish()
                        }
                    } catch (e: HttpException) {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Email already registered, please try another email!",
                            Toast.LENGTH_SHORT
                        ).show()
                        registerViewModel.stopLoading()
                    }
                }
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
}