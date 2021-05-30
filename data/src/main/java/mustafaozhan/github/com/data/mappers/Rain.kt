package mustafaozhan.github.com.data.mappers

import mustafaozhan.github.com.data.entity.Rain as RainEntity
import mustafaozhan.github.com.model.Rain as RainModel

internal fun RainEntity.toModel() = RainModel(threeHours = threeHours)
