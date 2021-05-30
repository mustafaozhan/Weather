package mustafaozhan.github.com.data.mappers

import mustafaozhan.github.com.data.entity.City as CityEntity
import mustafaozhan.github.com.model.City as CityModel

internal fun CityEntity.toModel() = CityModel(
    coord = coord?.toModel(),
    country = country,
    id = id,
    name = name,
    population = population,
    sunrise = sunrise,
    sunset = sunset,
    timezone = timezone
)
