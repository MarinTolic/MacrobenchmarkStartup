package com.example.macrobenchmarkstartup.messages

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.macrobenchmarkstartup.R
import com.getstream.sdk.chat.viewmodel.MessageInputViewModel
import com.getstream.sdk.chat.viewmodel.messages.MessageListViewModel
import io.getstream.chat.android.ui.message.input.viewmodel.bindView
import io.getstream.chat.android.ui.message.list.header.viewmodel.MessageListHeaderViewModel
import io.getstream.chat.android.ui.message.list.header.viewmodel.bindView
import io.getstream.chat.android.ui.message.list.viewmodel.bindView
import io.getstream.chat.android.ui.message.list.viewmodel.factory.MessageListViewModelFactory

class MessagesActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messages)

        val channelId = intent?.getStringExtra(KEY_CHANNEL_ID)

        if ((channelId) != null) {
            Log.d("ChannelId::::::::::::",channelId)
            val messageListViewModelFactory = MessageListViewModelFactory(cid = channelId)
            val messagesListViewModel: MessageListViewModel by viewModels { messageListViewModelFactory }
            val messageListHeaderViewModel: MessageListHeaderViewModel by viewModels { messageListViewModelFactory }
            val messageInputViewModel: MessageInputViewModel by viewModels { messageListViewModelFactory }

            messageListHeaderViewModel.bindView(
                view = findViewById(R.id.messageListHeaderView),
                lifecycle = this
            )
            messagesListViewModel.bindView(
                view = findViewById(R.id.messageListView),
                lifecycleOwner = this
            )
            messageInputViewModel.bindView(
                view = findViewById(R.id.messageInputView),
                lifecycleOwner = this
            )
        }
    }

    companion object {
        private const val KEY_CHANNEL_ID = "channelId"

        fun createIntent(context: Context, channelId: String): Intent {
            return Intent(context, MessagesActivity::class.java).apply {
                putExtra(KEY_CHANNEL_ID, channelId)
            }
        }
    }
}