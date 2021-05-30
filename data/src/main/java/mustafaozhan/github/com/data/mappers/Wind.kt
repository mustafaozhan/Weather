package mustafaozhan.github.com.data.mappers

import mustafaozhan.github.com.data.entity.Wind as WindEntity
import mustafaozhan.github.com.model.Wind as WindModel

internal fun WindEntity.toModel() = WindModel(
    deg = deg,
    gust = gust,
    speed = speed
)
