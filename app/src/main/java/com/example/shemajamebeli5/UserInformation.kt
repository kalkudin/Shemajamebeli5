package com.example.shemajamebeli5

import com.squareup.moshi.Json
import java.util.Locale

data class UserInformation(
    @Json(name = "id") val id: Int,
    @Json(name = "image") val image: String?,
    @Json(name = "owner") val owner: String,
    @Json(name = "last_message") val lastMessage: String,
    @Json(name = "last_active") val lastActive: String,
    @Json(name = "unread_messages") val unreadMessages: Int,
    @Json(name = "is_typing") val isTyping: Boolean,
    @Json(name = "laste_message_type") val lastMessageType: String,
) {
    val itemType: ItemType
        get() = when (lastMessageType.lowercase(Locale.ROOT)) {
            "text" -> ItemType.TEXT
            "file" -> ItemType.FILE
            "voice" -> ItemType.VOICE
            else -> ItemType.TEXT
        }
    enum class ItemType {
        TEXT, FILE, VOICE
    }
}