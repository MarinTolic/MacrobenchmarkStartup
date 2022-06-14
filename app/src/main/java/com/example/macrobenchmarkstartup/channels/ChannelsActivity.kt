package com.example.macrobenchmarkstartup.channels

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.macrobenchmarkstartup.R
import com.example.macrobenchmarkstartup.messages.MessagesActivity
import io.getstream.chat.android.ui.channel.list.ChannelListView
import io.getstream.chat.android.ui.channel.list.header.viewmodel.ChannelListHeaderViewModel
import io.getstream.chat.android.ui.channel.list.header.viewmodel.bindView
import io.getstream.chat.android.ui.channel.list.viewmodel.ChannelListViewModel
import io.getstream.chat.android.ui.channel.list.viewmodel.bindView
import io.getstream.chat.android.ui.channel.list.viewmodel.factory.ChannelListViewModelFactory

class ChannelsActivity : AppCompatActivity() {

    private val channelListViewModelFactory = ChannelListViewModelFactory()
    private val channelListViewModel by viewModels<ChannelListViewModel> { channelListViewModelFactory }

    private val channelListHeaderViewModel: ChannelListHeaderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_channels)
        val channelListView = findViewById<ChannelListView>(R.id.channelListView)
        channelListView.setChannelItemClickListener {
            startActivity(MessagesActivity.createIntent(context = applicationContext, channelId =  it.cid))
        }

        channelListHeaderViewModel.bindView(
            view = findViewById(R.id.channelListHeaderView),
            lifecycleOwner = this
        )
        channelListViewModel.bindView(
            view = findViewById(R.id.channelListView),
            lifecycleOwner = this
        )
    }
}