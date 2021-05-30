package mustafaozhan.github.com.data.mappers

import mustafaozhan.github.com.data.entity.Clouds as CloudsEntity
import mustafaozhan.github.com.model.Clouds as CloudsModel

internal fun CloudsEntity.toModel() = CloudsModel(
    all = all
)
