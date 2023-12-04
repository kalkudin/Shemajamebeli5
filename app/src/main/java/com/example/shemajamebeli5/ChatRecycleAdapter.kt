package com.example.shemajamebeli5

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shemajamebeli5.databinding.UserChatLayoutBinding

class ChatRecycleAdapter : ListAdapter<UserInformation, ChatRecycleAdapter.ChatItemViewHolder>(UserInfoUtil()){

    inner class ChatItemViewHolder(private val binding: UserChatLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(userInformation: UserInformation) {
            binding.apply {
                userName.text = userInformation.owner
                lastMessageTime.text = userInformation.lastActive

                // Set last message text and handle ellipsis
                userLastMessage.text = userInformation.lastMessage
                userLastMessage.ellipsize = TextUtils.TruncateAt.END
                userLastMessage.maxLines = 1

                // Set unread messages count
                if (userInformation.unreadMessages > 0) {
                    unreadMessages.text = userInformation.unreadMessages.toString()
                    unreadMessages.visibility = View.VISIBLE
                } else {
                    unreadMessages.visibility = View.GONE
                }
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