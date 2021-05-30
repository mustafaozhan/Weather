package mustafaozhan.github.com.data.mappers

import mustafaozhan.github.com.data.entity.Weather as WeatherEntity
import mustafaozhan.github.com.model.Weather as WeatherModel

internal fun WeatherEntity.toModel() = WeatherModel(
    description = description,
    icon = icon,
    id = id,
    main = main
)
