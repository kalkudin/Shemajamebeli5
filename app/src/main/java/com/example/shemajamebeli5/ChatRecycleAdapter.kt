package com.example.shemajamebeli5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shemajamebeli5.databinding.UserChatLayoutBinding

class ChatRecycleAdapter : ListAdapter<UserInformation, ChatRecycleAdapter.ChatItemViewHolder>(UserInfoUtil()){

    inner class ChatItemViewHolder(private val binding: UserChatLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(userInformation: UserInformation) {
            binding.userName.text = userInformation.owner
            binding.lastMessageTime.text = userInformation.lastActive

            when (userInformation.itemType) {
                UserInformation.ItemType.TEXT -> {
                    binding.userLastMessage.text = userInformation.lastMessage
                }
                UserInformation.ItemType.FILE -> {
                    binding.userLastMessage.text = "Sent a File"
                }
                UserInformation.ItemType.VOICE -> {
                    binding.userLastMessage.text = "Sent a Voice Message"
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatItemViewHolder {
        return ChatItemViewHolder(UserChatLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ChatItemViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class UserInfoUtil : DiffUtil.ItemCallback<UserInformation>(){
        override fun areItemsTheSame(oldItem: UserInformation, newItem: UserInformation): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: UserInformation,
            newItem: UserInformation
        ): Boolean {
            return oldItem == newItem
        }

    }
}