rootProject.name = "Weather"
include(
    ":app",
    ":ui",
    ":client",
    ":data",
    ":basemob"
)

project(":basemob").projectDir = file("basemob/basemob")
