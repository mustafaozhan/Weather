rootProject.name = "Weather"
include(
    ":app",
    ":ui",
    ":basemob"
)

project(":basemob").projectDir = file("basemob/basemob")
