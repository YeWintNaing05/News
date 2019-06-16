package com.ywn.remote.mapper

import com.ywn.data.entity.SourceEntity
import com.ywn.remote.Mapper
import com.ywn.remote.entity.SourceResponse
import javax.inject.Inject

class SourceResultMapper
@Inject constructor() : Mapper<SourceResponse, SourceEntity> {
    override fun map(item: SourceResponse): SourceEntity {
        return SourceEntity(item.id, item.name)
    }
}