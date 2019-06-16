package com.ywn.domain.model

data class
News(
    val status: String? = null,
    val totalResults: Int? = null,
    val articles: List<Article>? = null,
    override val throwable: Throwable?,
    override val state: State
) : DomainModel {
    companion object {

        fun success(
            status: String? = null,
            totalResults: Int? = null,
            articles: List<Article>? = null
        ): News = News(
            status,
            totalResults,
            articles,
            null,
            State.SUCCESS
        )


        fun progress(
        ): News = News(
            "",
            0,
            ArrayList(),
            null,
            State.PROGRESS
        )


        fun error(
            throwable: Throwable
        ): News = News(
            "",
            0,
            null,
            throwable,
            State.ERROR
        )

    }

}