package com.ywn.common.exception

import android.content.Context
import com.ywn.common.R
import com.ywn.data.exception.Issue
import javax.inject.Inject

class IssueFactory @Inject
constructor(private val context: Context) {

    fun getMessage(issue: Issue): String {
        return when (issue) {
            Issue.API -> context.getString(R.string.error_message_api)
            Issue.SERVER -> context.getString(R.string.error_message_server)
            Issue.NETWORK -> context.getString(R.string.error_message_network)
            else -> context.getString(R.string.error_message_unknown)
        }
    }
}