package com.ywn.data.exception

class DataException(val issue: Issue) : Exception() {

    fun isIssue(issue: Issue): Boolean {
        return this.issue === issue
    }


    fun shouldRetry(): Boolean {
        return issue.shouldRetry()
    }
}