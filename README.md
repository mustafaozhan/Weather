# Weather
[![Weather CI](https://github.com/mustafaozhan/Weather/actions/workflows/main.yml/badge.svg)](https://github.com/mustafaozhan/Weather/actions/workflows/main.yml)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/66e5f351dbe34d6fa73dedb6ab03e466)](https://app.codacy.com/gh/mustafaozhan/Weather?utm_source=github.com&utm_medium=referral&utm_content=mustafaozhan/Weather&utm_campaign=Badge_Grade_Settings)

## Built With 🛠

- Android Studio 4.2.1+
- [Kotlin](https://kotlinlang.org/)
- [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) - Coroutines is Google's recommended solution for asynchronous programming on Android. 
- [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/) - An asynchronous data stream that sequentially emits values and completes normally or with an exception.
- [StateFlow and SharedFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow) - StateFlow and SharedFlow are Flow APIs that enable flows to optimally emit state updates and emit values to multiple consumers.
- [Material Components](https://github.com/material-components/material-components-android) - Material Components for Android (MDC-Android) help developers execute Material Design. Developed by a core team of engineers and UX designers at Google.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
  - [Room](https://developer.android.com/topic/libraries/architecture/room) - SQLite object mapping library.
  - [Navigation](https://developer.android.com/guide/navigation/navigation-getting-started) - Android Navigation Component
  - [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
- [Dependency Injection](https://developer.android.com/training/dependency-injection)
  - [Dagger](https://dagger.dev/dev-guide/) - Standard library to incorporate Dagger dependency injection into an Android application.
- Networking
  - [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
  - [Moshi](https://github.com/square/moshi) - Moshi is a modern JSON library for Android and Java. It makes it easy to parse JSON into Java objects.
- Testing
  - [JUnit](https://junit.org/junit4/) - JUnit is a simple framework to write repeatable tests. It is an instance of the xUnit architecture for unit testing frameworks.
  - [MockK](https://mockk.io/) - mocking library for Kotlin.
  - [kotlinx-coroutines-test](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-test/) - This package provides testing utilities for effectively testing coroutines.
  - [Arch Core](https://developer.android.com/jetpack/androidx/releases/arch-core) - Helper for other arch dependencies, including JUnit test rules that can be used with LiveData.
- [Glide](https://github.com/bumptech/glide) - Glide is a fast and efficient open source media management and image loading framework for Android that wraps media decoding, memory and disk caching, and resource pooling into a simple and easy to use interface.
- [Timber](https://github.com/JakeWharton/timber) - This is a logger with a small, extensible API which provides utility on top of Android's normal Log class.

## Architecture
- This app uses Google's recommended [MVVM (Model View View-Model) - Repository](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) architecture, repository pattern. In addition, it it has a component called SEED (State, Effect, Event, Data). It makes viewModels cleaner and defines data flow better.
