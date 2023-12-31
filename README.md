# World Countries

<img src="https://github.com/AhmetOcak/WorldCountries/assets/73544434/91b594e6-726c-461a-91b4-d9ba1231133c" width="192" height="192"/>

World Countries is an application I developed using Kotlin-XML that allows you to get information about countries. You can search for any country you want in the application and view the selected country's history, geography, population, flag, location on the map, etc. You can get information about. You can also add or remove any country you want to your favorites.

## Tech Stack 📚

* [Android Architecture Components](https://developer.android.com/topic/architecture)
    * [Navigation](https://developer.android.com/guide/navigation)
      
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
      
    * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle)
      
    * [Repository](https://developer.android.com/topic/architecture/data-layer?hl=en)
      
* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
  
* [Room](https://developer.android.com/training/data-storage/room)
  
* [Retrofit](https://github.com/square/retrofit)
  
* [Coil](https://github.com/coil-kt/coil)
  
* [Okhttp](https://square.github.io/okhttp/)
  
* [Android Chart](https://github.com/PhilJay/MPAndroidChart)

## Outputs 🖼

|                  |              |
|------------------|--------------|
| Video from app   | <video src="https://github.com/AhmetOcak/WorldCountries/assets/73544434/367cd76d-d1ee-4e8d-9dac-de5af2f7bcb5" width="240" height="480" /> |


|                  | Light | Dark |
|------------------|-------|------|
| Home Screen      | <img src="https://github.com/AhmetOcak/WorldCountries/assets/73544434/0ee36278-a99b-4ab9-993d-dfe206f8c144" width="240" height="480"/>       | <img src="https://github.com/AhmetOcak/WorldCountries/assets/73544434/c737547a-b536-4fc2-8d30-f4a763384187" width="240" height="480"/>      |
| Search Screen    | <img src="https://github.com/AhmetOcak/WorldCountries/assets/73544434/0104250c-2c1f-4327-a428-68db4341709c" width="240" height="480"/>      |  <img src="https://github.com/AhmetOcak/WorldCountries/assets/73544434/b176b162-f02f-4911-8f0b-565c45fc43a9" width="240" height="480"/>    |
| Favorites Screen | <img src="https://github.com/AhmetOcak/WorldCountries/assets/73544434/a5507683-c832-496f-b6ec-c8b7e4f50ede" width="240" height="480"/>      | <img src="https://github.com/AhmetOcak/WorldCountries/assets/73544434/1946e073-2942-4025-a9a3-cc7dbec5af40" width="240" height="480"/>     |
| Our World Screen | <img src="https://github.com/AhmetOcak/WorldCountries/assets/73544434/da28e113-972f-4337-8615-9a1057e28bc6" width="240" height="480"/>      | <img src="https://github.com/AhmetOcak/WorldCountries/assets/73544434/dde0e67b-17af-47f4-9321-c0517e05357d" width="240" height="480"/>     |
| Sort             | <img src="https://github.com/AhmetOcak/WorldCountries/assets/73544434/2d06119f-4c8b-4805-83eb-d5532726754d" width="240" height="480"/>      | <img src="https://github.com/AhmetOcak/WorldCountries/assets/73544434/47b3ad49-0bf7-4c94-9c8c-139385d3397d" width="240" height="480"/>     |
| Filter           |  <img src="https://github.com/AhmetOcak/WorldCountries/assets/73544434/74e32206-b712-48fd-9b80-3c94387698dd" width="240" height="480"/>     | <img src="https://github.com/AhmetOcak/WorldCountries/assets/73544434/a48f4d8e-9dad-4b75-83c5-66cdc9f320d1" width="240" height="480"/>     |  

|                  |             |
|------------------|-------------|
| Detail Screen     | <video src="https://github.com/AhmetOcak/WorldCountries/assets/73544434/cf78838e-7687-4e42-9461-3940b97d9423" width="240" height="480"/>     |

## Architecture 🏗
The app uses MVVM [Model-View-ViewModel] architecture to have a unidirectional flow of data, separation of concern, testability, and a lot more.

![mvvm](https://user-images.githubusercontent.com/73544434/197416569-d42a6bbe-126e-4776-9c8f-2791925f738c.png)

## Country Data Source 📦

-> [CIA World Factbook API](https://github.com/iancoleman/cia_world_factbook_api) (I rearranged the JSON data file as there was information I didn't plan to use.)

## Known issues ⚠

* The color of description texts in charts does not change in dark mode.
