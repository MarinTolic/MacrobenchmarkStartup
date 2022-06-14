package com.example.macrobenchmarkstartup

import android.app.Application
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.logger.ChatLogLevel
import io.getstream.chat.android.client.models.User
import io.getstream.chat.android.offline.model.message.attachments.UploadAttachmentsNetworkType
import io.getstream.chat.android.offline.plugin.configuration.Config
import io.getstream.chat.android.offline.plugin.factory.StreamOfflinePluginFactory

class App : Application() {

    private val offlinePluginFactory by lazy {
        StreamOfflinePluginFactory(
            config = Config(
                backgroundSyncEnabled = true,
                userPresence = true,
                persistenceEnabled = true,
                uploadAttachmentsNetworkType = UploadAttachmentsNetworkType.NOT_ROAMING,
            ),
            appContext = applicationContext,
        )
    }


    override fun onCreate() {
        super.onCreate()

        val client = ChatClient.Builder("ryhbdtzrtvj5", applicationContext)
            .withPlugin(offlinePluginFactory)
            .logLevel(ChatLogLevel.ALL) // Set to NOTHING in prod
            .build()

        client.connectUser(
            user = createUser(),
            token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoibWFyaW50b2xpYyJ9.rSB3BT7Oa8Y49K2gCndJtSAKDpxLZ4J-pCogt6SgA6U"
        ).enqueue()
    }

    private fun createUser() = User(
        id = "marintolic",
        name = "Marin Tolić",
        image = "https://ca.slack-edge.com/T02RM6X6B-U02N2HTP79A-645f7845aa22-512"
    )
}