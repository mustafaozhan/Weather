package mustafaozhan.github.com.data.mappers

import mustafaozhan.github.com.data.entity.ForecastResponse as ForecastResponseEntity
import mustafaozhan.github.com.model.ForecastResponse as ForecastResponseModel

internal fun ForecastResponseEntity.toModel() = ForecastResponseModel(
    city = city?.toModel(),
    cnt = cnt,
    cod = cod,
    list = list?.map { it.toModel() },
    message = message
)
