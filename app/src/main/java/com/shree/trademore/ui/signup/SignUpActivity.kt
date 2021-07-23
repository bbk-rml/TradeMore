package com.shree.trademore.ui.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.shree.trademore.MyApplication
import com.shree.trademore.databinding.ActivitySignUpBinding
import com.shree.trademore.model.SignUpModel
import com.shree.trademore.ui.login.LoginActivity
import com.shree.trademore.util.MySharePrefrence
import com.shree.trademore.util.Status
import com.shree.trademore.util.ViewModelFactory

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    lateinit var viewModel:SignUpViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setupUI()
        setUpViewModel()
        setUpObserver()
    }

    private fun setUpObserver() {
        viewModel.getSignUpData().observe(this, {
            when (it.status) {
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { loginDataModel -> registerSuccess(loginDataModel) }
                }

                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun registerSuccess(signUpModel: SignUpModel) {
        if(signUpModel.status){
            Toast.makeText(applicationContext,signUpModel.comments,Toast.LENGTH_SHORT).show()
            MySharePrefrence.instance?.setUserId("1")
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }else{
            Toast.makeText(applicationContext,signUpModel.comments,Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory((baseContext.applicationContext as MyApplication).repositorySignUp)
        ).get(SignUpViewModel::class.java)
    }

    private fun setupUI() {
        binding.signUpButton.setOnClickListener {
            if (!TextUtils.isEmpty(binding.userName.text.toString())) {

                if(!TextUtils.isEmpty(binding.password.text.toString())){

                    if(!TextUtils.isEmpty(binding.mobileNumber.text.toString())){
                        viewModel.register(
                            binding.mobileNumber.text.toString(),
                            binding.userName.text.toString(),
                            binding.password.text.toString()
                        )
                    }else binding.userName.error = "Mobile Number is required"

                }else binding.userName.error = "Password is required"

            }else binding.password.error = "UserName is required"
        }
    }
}