package mustafaozhan.github.com.data.mappers

import mustafaozhan.github.com.data.entity.Coord as CoordEntity
import mustafaozhan.github.com.model.Coord as CoordModel

internal fun CoordEntity.toModel() = CoordModel(
    lat = lat,
    lon = lon
)
