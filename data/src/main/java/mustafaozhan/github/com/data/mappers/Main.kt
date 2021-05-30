package mustafaozhan.github.com.data.mappers

import mustafaozhan.github.com.data.entity.Main as MainEntity
import mustafaozhan.github.com.model.Main as MainModel

internal fun MainEntity.toModel() = MainModel(
    feelsLike = feelsLike,
    grndLevel = grndLevel,
    humidity = humidity,
    pressure = pressure,
    seaLevel = seaLevel,
    temp = temp,
    tempKf = tempKf,
    tempMax = tempMax,
    tempMin = tempMin,
)
