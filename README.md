# MarvelRepo

Programing language: Kotlin
Pattern design: Model View ViewModel

Used libraries:
  - Retrofit
  - Dagger2
  - Lottie
  - Picasso
  - Room
  - Robolectric
  
Description.

Character's List
    * Character image
    * Character name
    * Like icon button
The user can navigate to character's detail.
The user can save the character as favorite
The user can see his/her favorite characters
At the first app load, 100 characters are obtained from server and saved in local database.
When the user scroll to the last loaded character a new request is made in order to obtain another
100 characters and save them into local database. 

Character's details:
    * Collapsible toolbar with the character's image
    * Character's name and description
    * Character's list of comics where it appears.
