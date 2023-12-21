package com.bangkit.petme.ui.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.AlertDialog
import android.content.Intent
import android.opengl.Visibility
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
import com.bangkit.petme.api.Response.LoginResponse
import com.bangkit.petme.databinding.ActivityLoginBinding
import com.bangkit.petme.ui.main.MainActivity
import com.bangkit.petme.ui.register.RegisterActivity
import com.bangkit.petme.viewmodel.LoginViewModel
import com.bangkit.petme.viewmodel.PetsCollectionViewModel
import com.bangkit.petme.viewmodel.ViewModelFactory
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val hyperlinkText = SpannableString(getString(R.string.Dont_have_account))

        val clickableHyperlinkText = object : ClickableSpan() {
            override fun onClick(widget: View) {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }
        }

        hyperlinkText.setSpan(clickableHyperlinkText, 27, 35, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvToRegister.text = hyperlinkText
        binding.tvToRegister.movementMethod = LinkMovementMethod.getInstance()

        playAnimation()

        val loginViewModel =
            ViewModelProvider(this, ViewModelFactory.getInstance(this.application)).get(
                LoginViewModel::class.java
            )


        if(loginViewModel.getToken() != "" && loginViewModel.getToken() != null){
            startActivity(Intent(this, MainActivity::class.java))
        }

        loginViewModel.isLoading.observe(this) {
            if (it == true) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.INVISIBLE
            }
        }
        binding.loginButton.setOnClickListener {
            if (binding.etEmail.text.toString() == "") {
                Toast.makeText(this, "Please input email", Toast.LENGTH_SHORT).show()
                binding.etEmail.setError("Please input email")
            } else if(!isValidEmail(binding.etEmail.text.toString())){
                Toast.makeText(this, "Please input correct email", Toast.LENGTH_SHORT).show()
                binding.etEmail.setError("Please input correct email")
            }else if (binding.etPassword.text.toString() == "") {
                Toast.makeText(this, "Please input password", Toast.LENGTH_SHORT).show()
                binding.etPassword.setError("Please input password")
            } else {
                val email = binding.etEmail.text.toString()
                val password = binding.etPassword.text.toString()
                lifecycleScope.launch {
                    try {
                        val loginresult = loginViewModel.login(email, password)
                        val status = loginresult.status
                        if (status == "Success") {
                            val token = loginresult.token
                            loginViewModel.saveToken(token.toString())
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                            finish()

                        }
                    } catch (e: HttpException) {
                        Toast.makeText(
                            this@LoginActivity,
                            "Account is not registered",
                            Toast.LENGTH_SHORT
                        ).show()
                        loginViewModel.stopLoading()
                    }
                }
            }
        }

    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.ivLogin, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val edEmail =
            ObjectAnimator.ofFloat(binding.textInputEmail, View.ALPHA, 1f).setDuration(500)
        val edPassword =
            ObjectAnimator.ofFloat(binding.textInputPassword, View.ALPHA, 1f).setDuration(500)
        val buttonLogin =
            ObjectAnimator.ofFloat(binding.loginButton, View.ALPHA, 1f).setDuration(500)
        val tvToRegister =
            ObjectAnimator.ofFloat(binding.tvToRegister, View.ALPHA, 1f).setDuration(500)

        AnimatorSet().apply {
            playSequentially(edEmail, edPassword, buttonLogin, tvToRegister)
            start()
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

}