package mustafaozhan.github.com.data.mappers

import mustafaozhan.github.com.data.entity.Sys as SysEntity
import mustafaozhan.github.com.model.Sys as SysModel

internal fun SysEntity.toModel() = SysModel(pod = pod)
