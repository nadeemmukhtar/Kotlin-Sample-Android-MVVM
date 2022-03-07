package com.example.kotlinsample

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinsample.databinding.UserItemLayBinding
import java.util.*


class UserAdapter(
    private var userList: ArrayList<UserModel.UserModelItem>,
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun getItemCount() = userList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.user_item_lay,
                parent,
                false
            )
        )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val obj = userList[position]
        holder.dashBoardRVBinding.tvName.text = obj.name
        holder.dashBoardRVBinding.tvUserName.text = obj.username
        holder.dashBoardRVBinding.tvEmail.text = obj.email
        holder.dashBoardRVBinding.tvPhone.text = obj.phone
        holder.dashBoardRVBinding.tvUserID.text = "" + obj.id
        holder.dashBoardRVBinding.tvWebsite.text = obj.website
    }


    inner class UserViewHolder(
        val dashBoardRVBinding: UserItemLayBinding
    ) : RecyclerView.ViewHolder(dashBoardRVBinding.root)

}