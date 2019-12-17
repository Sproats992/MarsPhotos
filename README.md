# MarsPhotos

This Android app was created as part of a technical test for a job opportunity and is a demo showing MVVM and Clean architecture layering principles, using Kotlin Coroutines and Flow to manage reactive streams.

It fetches a list of Mars (the planet) related articles from NASA (via endpoint: https://images-api.nasa.gov/search?q=mars&media_type=image ) and displays a parent list of articles and allows the user to click on an article to reveal a more detailed photograph and any descriptive text that is available.

This endpoint is has a very rich data return, really too rich for a mobile application (IMHO). If this was a commercial proposition I would look at reducing the complexity of the data sent to the app by adding an intermediary back-end microservice, removing unused parts and tailoring returns to suit particlar functional areas of the mobile app, though the feed is used directly for demo purposes.

This codebase reuses an MVVM and Clear Architecture template pattern I have used for several demo projects.


# Software Architecture

The architecture employed attempts to align with the latest Android architectural patterns (Jetpack) and incorporate strong Clean Architecture (Robert C. Martin type) in the software design. Reactive streaming for data passing is achieved using a combination of Android LiveData, Kotlin Coroutines and Kotlin Flow.

To that end, the app is modular and employs seperate Presentaton/UI feature modules (presentation), a repository module to contain data and network requests and Domain module defining entities and usecases. In line with Clean Architecture the Domain module is completely platform independent and is isolated by the Gradle dependencies which contain no references to the Android target platform.

The presentation modules incorporates a Model, View, ViewModel (MVVM) structure.

Entity mappers are provided at the boundaries of the Presentation and Repository layers to convert data models into entities suitable for each layer and isolating it from changes.

The Domain specifies repository interfaces, data models and UseCases.

The parent app module is responsible for creating the dependency graph (using Dagger 2) and populating required dependencies.

The repository module will cache successful network responses in a Room persistent database, allowing for usage offline once the initial data has been populated. 

Picasso is used for UI image caching, as this is a complex problem that has been solved before. The cache space for which is rather more limited.


# Unit Testing

Comprehensive unit testing is not performed. There are a selection of example unit tests provided for the ViewModels as these comprise the most challenging area of the app to unit test. UI testing would be covered by other methods not shown here using, for example, Espresso. Repositories could be tested via the same Coroutine example methods used for the ViewModels.

These feature Kotlin Coroutine and Flow calls and return, feeding into Android LiveData objects. Kotlin Coroutines use the standard Test Dispatcher method and each ViewModel features injected Coroutine Dispatchers for this reason, in line with Google recommendations for Coroutine testing.

Two methods of intercepting LiveData are provided, using the observeForever method and a more complex single observation. The tests can be inspected in the following files:

* TestPresentationDetailViewModel.kt
* TestPresentationViewModel.kt


# Technologies used

* Dependencies are managed (for the most part) with Dagger2 with the parent app module populating the dependency graph.
* UseCases are isolated from the main thread using Kotlin Coroutines and Kotlin Flow.
* Android Jetpack (AndroidX) is used for most of the app, including Navigation.
* Application structure is based on Clean Architure, breaking the app into the "onion" as far as possible.
* Presentation software structure is based on MVVM using Android ViewModels.
* Networking and Internet access uses Retrofit2.
* Data is persisted using the Android Room database using Coroutine interfacing.
* Images are loaded and persistent by Picasso.


# TODOs.

I didn't quite finish according to the provided designs in the time allowed. 
I opted not to crop the images in the home recyclerview as the images seemed far too interesting, so the gridview does not have a consistent element size. 
I also didn't format the date using a date formatter, just a simple string splitting system.

TODO - Replace the trivial date formatting with real date formatters.

TODO - Fix gradle dependency versioning in the various gradle files, remove hardcoded versions.

TODO - Finish testsuite for ViewModels and Data.

TODO - Allow sorting of data.
