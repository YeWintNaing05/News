package com.ywn.common.mapper

import android.content.Context
import com.ywn.common.base.mapper.ViewStateMapper
import com.ywn.common.model.SourceViewItem
import com.ywn.domain.model.Source
import javax.inject.Inject

class SourceViewItemMapper
@Inject constructor(
    context: Context
) : ViewStateMapper<SourceViewItem, Source>(context) {


    override fun map(domainModel: Source): SourceViewItem =
        SourceViewItem(domainModel.id, domainModel.name)
}