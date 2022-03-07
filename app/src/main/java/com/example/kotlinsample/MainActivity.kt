package com.example.kotlinsample

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinsample.databinding.ActivityUsersBinding
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUsersBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var factory: UserViewModelFactory
    private lateinit var data: ArrayList<UserModel.UserModelItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_users)
        val api = ApiInterface()
        val repository = GRRepository(api)
        factory = UserViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)
        binding.progressBar.visibility = View.VISIBLE
        viewModel.getUserList()
        viewModel.userLiveData.observe(this, Observer {
            data = it as ArrayList<UserModel.UserModelItem>
            binding.progressBar.visibility = View.GONE
            binding.recMainServices.also { it ->
                it.layoutManager = LinearLayoutManager(this)
                it.setHasFixedSize(true)
                it.adapter =
                    UserAdapter(
                        data
                    )
            }


        })
        viewModel.errorLiveData.observe(this, Observer {
            val Error = it
            Toast.makeText(this, Error, Toast.LENGTH_SHORT).show()
            binding.progressBar.visibility = View.GONE

        })


    }


}