# Movie App By Jetpack Compose

- The Movie App is an Android app built using modern tech like Jetpack Compose, TMDB API, Retrofit, and MVI with clean architecture by layer (data - domain - presentation).
  
- It allows users to explore movie categories, view detailed information, and add movie to favorite movie list and search on movies in search screen.
  
-  The app uses pagination by Paging3(RemoteMediator - PagingSource) for smooth data loading and caching the movies in  Room Database, ensuring a fast, seamless experience.

## Features:
  - Splash Screen
  - Intro Screen
  - Home Screen to display Movies by category (Trending - Popular - Top Rated - Upcoming - Discover - Now Playing)
  - Details Screen to display movie details
  - Search Screen to search for movie
  - Favorite Screen to display all movies that is favorite in RoomDb
  - Caching movie in RoomDb

## The skills are used in this application:
- language: Kotlin </br>
- UI layouts using Jetpack Compose </br>
- Multi-screen by navigation compose </br>
- SavedStateHandle  </br>
- Coil Compose  </br>
- Clean Architecture {presentation - domain - data} </br>
- MVI architecture </br>
- Pagination by Paging3  </br>
- RemoteMediator & PagingSource </br>
- Retrofit2 & Gson - construct the REST APIs. </br>
- Flow </br>
- Room Database </br>
- Dependency injection by (Dagger Hilt) </br>
- Coroutines for asynchronous </br> </br>


## Images this application : <br>

### Splash Screen & Intro Screen: <br>

<p align="center">
<img src="https://github.com/user-attachments/assets/25dd7b65-40ac-4230-841f-31f7e3b0db52" width="230" height="500" />
 <span> &nbsp;  &nbsp; </span>
<img src="https://github.com/user-attachments/assets/8fcb129e-1413-4d6b-a621-7d4b21cc3197" width="230" height="500" />
</p>  <br>

### Home Screen: <br>

<p align="center">
<img src="https://github.com/user-attachments/assets/a93b7f18-6f9e-447d-ba63-427c5cf5bfcf" width="230" height="500" />
 <span> &nbsp;  &nbsp; </span>
<img src="https://github.com/user-attachments/assets/5d0adb14-08ef-4ed1-a619-37f9c0b9aea6" width="230" height="500" />
</p>  <br>

### Movies Details Screen: <br>

<p align="left">
<img src="https://github.com/user-attachments/assets/f359dfb7-51a6-45a0-81b8-22224efd78bc" width="185" height="400" />
 <span> &nbsp;  &nbsp; </span>
<img src="https://github.com/user-attachments/assets/bdeff750-207e-48f3-8ff7-072edcb7125a" width="185" height="400" />
 <span> &nbsp;  &nbsp;  </span>
<img src="https://github.com/user-attachments/assets/941437b7-6924-4d69-8f55-d1ff32fd1a38" width="185" height="400" />
 <span> &nbsp;  &nbsp;  </span>
<img src="https://github.com/user-attachments/assets/40d20287-edf4-4d33-b010-f814add43bed" width="185" height="400" />
</p>

### Movies Search Screen: <br>

<p align="left">
<img src="https://github.com/user-attachments/assets/a6b2da7a-6bf3-4695-9391-8eaefdd50b91" width="185" height="400" />
 <span> &nbsp;  &nbsp; </span>
<img src="https://github.com/user-attachments/assets/51d123a2-1892-4b2f-a120-cf0107b145a5" width="185" height="400" />
 <span> &nbsp;  &nbsp;  </span>
<img src="https://github.com/user-attachments/assets/7ba5dec9-2aa0-4dcc-845f-8508431a032c" width="185" height="400" />
 <span> &nbsp;  &nbsp;  </span>
<img src="https://github.com/user-attachments/assets/abe63e95-42f2-45a8-8c5a-69801d5e1982" width="185" height="400" />
</p>


### Favorite Movies Screen: <br>

<p align="center">
<img src="https://github.com/user-attachments/assets/a7409cfb-ac4b-4274-b128-985ba15e0cf0" width="230" height="500" />
 <span> &nbsp;  &nbsp; </span>
<img src="https://github.com/user-attachments/assets/4c4f6e19-6fff-4f9b-84cd-ccc73205fc7d" width="230" height="500" />
</p>  <br>

### In case of no internet: <br>

<p align="left">
<img src="https://github.com/user-attachments/assets/972b0fa3-ac45-46af-8646-cc911246ed52" width="185" height="400" />
 <span> &nbsp;  &nbsp; </span>
<img src="https://github.com/user-attachments/assets/b6a822ec-160c-4953-b97e-4ef179de867b" width="185" height="400" />
 <span> &nbsp;  &nbsp;  </span>
<img src="https://github.com/user-attachments/assets/1f58601a-1862-4e25-8fe0-3f7921f3b360" width="185" height="400" />
 <span> &nbsp;  &nbsp;  </span>
<img src="https://github.com/user-attachments/assets/a2fe95ea-bba2-4b5f-8d73-6fa635b9822b" width="185" height="400" />
</p>
































## Architecture
The following diagram shows all the modules and how each module interact with one another after. This architecture using a layered software architecture.  <br>
<p align="center">
<img src="https://user-images.githubusercontent.com/72816466/202196876-39bb8b5d-aa81-4693-8a5e-b1b588133975.jpeg"/>
  <img src="https://github.com/user-attachments/assets/a2797910-e4df-4c8a-9d40-4455d88826e5"/>
</p>  <br> 












