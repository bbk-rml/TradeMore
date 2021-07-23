package com.shree.trademore.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shree.trademore.MyApplication
import com.shree.trademore.databinding.ActivityLoginBinding
import com.shree.trademore.model.LoginModel
import com.shree.trademore.ui.home.HomeActivity
import com.shree.trademore.ui.signup.SignUpActivity
import com.shree.trademore.util.MySharePrefrence
import com.shree.trademore.util.Status
import com.shree.trademore.util.ViewModelFactory

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setupUI()
        setUpViewModel()
        setUpObserver()
    }

    private fun setUpObserver() {
        loginViewModel.getLoginData().observe(this, Observer {
            when(it.status){
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { loginDataModel -> loginSuccess(loginDataModel) }
                }

                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun setUpViewModel() {
        loginViewModel = ViewModelProvider(
            this,
            ViewModelFactory((baseContext.applicationContext as MyApplication).repositoryLogin)
        ).get(LoginViewModel::class.java)
    }

    private fun setupUI() {
        binding.signUpNow.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
        }

        binding.loginBtn.setOnClickListener {
            if (!TextUtils.isEmpty(binding.userName.text.toString())) {

                if(!TextUtils.isEmpty(binding.password.text.toString())){
                    loginViewModel.login(
                        binding.userName.text.toString(),
                        binding.password.text.toString()
                    )
                }else binding.userName.error = "Password is required"

            }else binding.password.error = "Mobile Number is required"
        }
    }

    private fun loginSuccess(loginModel: LoginModel){
        if(loginModel.status){
            Toast.makeText(applicationContext,loginModel.comments,Toast.LENGTH_SHORT).show()
            MySharePrefrence.instance?.setUserId("1")
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }else{
            Toast.makeText(applicationContext,loginModel.comments,Toast.LENGTH_SHORT).show()
        }
    }
}