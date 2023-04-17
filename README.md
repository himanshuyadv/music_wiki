# Music Wiki

Music Wiki is a music application that provides information about different music genres, artists, and albums. The app uses the MVVM architecture pattern with Kotlin language and Retrofit library for API calls. The app also uses Kotlin Coroutines for network operations.


## Features

- Browse through different music genres
- View list of artists for a specific genre
- View list of albums for a specific genre
- View list of tracks for a specific genre
- View albums of an genre
- View details about an album

## Architecture

The app is developed using the MVVM (Model-View-ViewModel) architecture pattern. This pattern helps to separate the UI logic from the business logic and provides a clean separation of concerns. 


The View layer contains the UI elements and interacts with the ViewModel to get and set data. The ViewModel interacts with the Model layer to retrieve and update data. The Model layer contains the business logic and data access code. 

## Libraries Used

The following libraries were used in the development of the app:

- Retrofit: For making API calls
- Glide: For loading images
- Kotlin Coroutines: For handling network operations
- ViewModel and LiveData: For implementing the MVVM pattern


## API

The app uses the Last.fm API for retrieving music-related data. You will need to obtain an API key from Last.fm to use the app. 

## Installation

To run the app on your device, follow these steps:

1. Clone the repository 


2. Open the project in Android Studio

3. Add your Last.fm API key to the `local.properties` like this ->  lastFmApiKey=YOUR_API_KEY :


4. Build and run the app on your device

## App Preview

Check out the video preview of the Music Wiki app:
 
https://github.com/himanshuyadv/music_wiki/blob/master/video_2023-04-17_08-12-08.mp4

Click on the link above to watch the video preview of the app.


## License

This project is licensed under the MIT License - see the LICENSE.md file for details. 

## Acknowledgments

- The app uses data from the Last.fm API
- Thanks to the developers of the libraries used in this project.

