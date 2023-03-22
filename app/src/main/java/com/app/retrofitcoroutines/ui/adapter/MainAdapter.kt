package com.app.retrofitcoroutines.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.retrofitcoroutines.R
import com.app.retrofitcoroutines.databinding.ItemLayoutBinding
import com.bumptech.glide.Glide
import com.mindorks.retrofit.coroutines.data.model.User
import com.app.retrofitcoroutines.ui.adapter.MainAdapter.DataViewHolder
import retrofit2.Response

class MainAdapter(private val users: ArrayList<User>) : RecyclerView.Adapter<DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var binding = ItemLayoutBinding.bind(itemView)

        fun bind(user: User) {
            itemView.apply {
                binding.textViewUserName.text = user.name
                binding.textViewUserEmail.text = user.email
                Glide.with(binding.imageViewAvatar.context)
                    .load(user.avatar)
                    .into(binding.imageViewAvatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    fun addUsers(users: List<User>) {
        this.users.apply {
            clear()
            addAll(users)
        }
    }
}