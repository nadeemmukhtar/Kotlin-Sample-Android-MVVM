package com.example.kotlinsample

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class UserViewModel(private val repository: GRRepository) : ViewModel() {


    val userLiveData: MutableLiveData<UserModel> = MutableLiveData()
    val errorLiveData: MutableLiveData<String> = MutableLiveData()


    fun getUserList(

    ) {
        val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
        }
        viewModelScope.launch(handler) {
            try {
                val response = repository.getUserList(
                )
                Log.wtf("Response", "" + response.toString())
                userLiveData.value = response
            } catch (e: ApiException) {
                Log.wtf("getUserList", "" + e.message)
                errorLiveData.value = e.message.toString()
            } catch (msg: Exception) {
                Log.wtf("getUserList", "" + msg.message)
                errorLiveData.value = msg.message.toString()
            }

        }
    }
}