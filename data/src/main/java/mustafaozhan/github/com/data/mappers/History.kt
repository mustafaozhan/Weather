package mustafaozhan.github.com.data.mappers

import mustafaozhan.github.com.data.entity.History as HistoryEntity
import mustafaozhan.github.com.model.History as HistoryModel

internal fun HistoryEntity.toModel() = HistoryModel(
    text = text
)

internal fun HistoryModel.toEntity() = HistoryEntity(
    text = text
)
