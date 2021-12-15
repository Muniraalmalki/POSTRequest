package com.example.postrequest.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postrequest.Model.UserItem
import com.example.postrequest.databinding.ItemRowBinding

class RecyclerViewAdapter(private var userList: List<UserItem>):
    RecyclerView.Adapter<RecyclerViewAdapter.HolderView>() {
    class HolderView(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderView {
        return HolderView(
            ItemRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HolderView, position: Int) {
        val user = userList[position]
        holder.binding.apply {
            tvName.text = "Name:${user.name}"
            tvLocation.text = "Location:${user.location}"
            tvId.text = "ID:${user.pk}"
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

}