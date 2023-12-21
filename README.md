# <p align="center">PetMe! </p>
<p align="center" >
    <img width="500" src="https://raw.githubusercontent.com/XnoahR/PetMe/main/asset/PetMe.png" alt="Pet Me Logo">
</p>

## <p align="center">"Pet Me! Paw-sibilities Unleashed, Your Ultimate Pet Care Companion"</p>
<p >PetMe is a mobile-based platform for users who want to find adopters or animals for adoption. This app provided as a marketplace with various option of animals to explore and find the best fit for users, also assisted wit pet breeds identification to help users identify more of the variety</p>
<div align="center">
    <img width="200" height="425" src="https://raw.githubusercontent.com/XnoahR/PetMe/main/asset/fav.jpg" alt="Pet Me Logo">
    <img width="200" height="425" src="https://raw.githubusercontent.com/XnoahR/PetMe/main/asset/my.jpg" alt="Pet Me Logo">
    <img width="200" height="425" src="https://raw.githubusercontent.com/XnoahR/PetMe/main/asset/home.jpg" alt="Pet Me Logo">
</div>

<h2> Project Team Members : </h2>

- Rio Saputro [Github](https://github.com/XnoahR)
- Ramandhika Ilham Pratama [Github](https://github.com/ramandhika)
- Aditia Prasetya Nugraha [Github](https://github.com/Adztrz)
- Balthasar Toslur Hasaraini Lase [Github](https://github.com/lasebalth)
- Wahyu Heriyanto [Github](https://github.com/WahyuHeriyanto)
- Fardan Maula Azizi[Github](https://github.com/fardanmaulaazizi)
- Arbi Julianto [Github](https://github.com/Arbikoj)


---
## Documentation
### Endpoints
* --- Account ---
  
  ```
  POST /account/register
  ```
  This endpoint is used to register a new user account.

  ```
  POST /account/login
  ```
  This endpoint is used to log in to an existing user account.

  ```
  GET /account/logout
  ```
  This endpoint is used to log out of the current user account.
  
* --- User ---

  ```
  GET /user/
  ```
  This endpoint is used to retrieve the profile information of the currently authenticated user.
  
  ```
  GET /user/edit/:id
  ```
  This endpoint is used to retrieve the user profile information for editing.
  ```
  
  PATCH /user/edit/:id
  ```
  This endpoint is used to update the user profile with the specified `id`.

  ```
  GET /user/fav
  ```
  This endpoint is used to retrieve the list of favorite posts associated with the currently authenticated user.
  
* --- Post ---
   ```
  GET /post/
  ```
  This endpoint is used to get all posts.

  ```
  GET /post/find/:id
  ```
  This endpoint is used to get a specific post.

  ```
  GET /post/my
  ```
  This endpoint is used to get posts created by the user.

  ```
  GET /post/edit/:id
  ```
  This endpoint is used to get a post for editing.

   ```
   POST /post/find/:id
   ```
  This endpoint is used to add a post to the list of favorite posts.

   ```
   POST /post/create
   ```
  This endpoint is used to create a new post.

  ```
  PATCH /post/edit/:id
  ```
  This endpoint is used to update a post without changing the picture.

  ```
  PATCH /post/edit/pict/:id
  ```
  This endpoint is used to update a post, including changing the picture
  
  ```
  DELETE /post/:id
  ```
  This endpoint is used to delete a post.

  ```
  DELETE /post/favourite/:id
  ```
  This endpoint is used to remove a post from the list of favorite posts.
