package mustafaozhan.github.com.data.mappers

import mustafaozhan.github.com.data.entity.Forecast as ForecastEntity
import mustafaozhan.github.com.model.Forecast as ForecastModel

internal fun ForecastEntity.toModel() = ForecastModel(
    clouds = clouds?.toModel(),
    dt = dt,
    dtTxt = dtTxt,
    main = main?.toModel(),
    pop = pop,
    rain = rain?.toModel(),
    sys = sys?.toModel(),
    visibility = visibility,
    weather = weather?.map { it.toModel() },
    wind = wind?.toModel()
)
