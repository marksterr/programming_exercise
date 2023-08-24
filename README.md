# Country List Android App

This Android application fetches and displays a list of countries in a `RecyclerView`. The country data includes details such as the name, region, code, and capital.

## Features

- Fetch country data from a remote JSON source.
- Display the country data in a scrollable list.
- Clean, high-quality Kotlin code.
- Use of Kotlin coroutines for asynchronous operations.
- Robust error handling.
- Support for device rotation.

## Technical Details

### Packages

- `data`: Contains the data layer components such as DTOs, API service interface, and the network provider.
- `presentation`: Contains the UI layer components such as ViewModels, Fragments, and Adapters.

### Major Components

- `CountryDTO`: Represents the data fetched from the service.
- `Country`: A domain model, derived from `CountryDTO`, that is used within the app.
- `APIService`: An interface for the Retrofit service to fetch countries.
- `CountryRepo`: A repository that abstracts the data fetching and transformation logic.
- `CountryViewModel`: The ViewModel which provides the country data to the UI.
- `CountryFragment`: A fragment that observes the country data from the ViewModel and displays it in a `RecyclerView`.

## Setup

1. Clone the repository: `git clone [REPO_URL]`.
2. Open the project in Android Studio.
3. Run the app on an emulator or a real device.

## Running Tests

- Make sure you've set up the testing dependencies in your `build.gradle.kts`.
- Run tests using the Android Studio test runner or via the command line with `./gradlew test`.

## Acknowledgements

- Data source: [Country Data JSON](https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json).

