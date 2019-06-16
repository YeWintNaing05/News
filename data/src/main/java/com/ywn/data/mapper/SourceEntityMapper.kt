package com.ywn.data.mapper

import com.ywn.data.Mapper
import com.ywn.data.entity.SourceEntity
import com.ywn.domain.model.Source
import javax.inject.Inject

class SourceEntityMapper
    @Inject constructor()
    : Mapper<Source, SourceEntity>() {
    override fun map(entity: SourceEntity): Source {
        return Source(entity.id, entity.name)
    }
}