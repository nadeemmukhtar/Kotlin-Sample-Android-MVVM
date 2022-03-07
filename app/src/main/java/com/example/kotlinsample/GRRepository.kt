package com.example.kotlinsample



class GRRepository(
    private val api: ApiInterface
) : SafeApiRequest() {
    suspend fun getUserList(

    ): UserModel =
        apiRequest { api.getUserList() }


}