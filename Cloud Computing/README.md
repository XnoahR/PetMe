### Endpoints
* --- Account ---
  
  ```
  POST /account/register
  ```
  This endpoint is used to register a new user account. How to use:
  ```
  {
    "name" : "your name",
    "email": "youremail@gmail.com",
    "password": "your password",
    "phone" : "085333222111"
  }
  ```
  <p> <br> </p>


  ```
  POST /account/login
  ```
  This endpoint is used to log in to an existing user account. How to use:
  ```
  {
    "email": "your email",
    "password": "your password"
  }
  ```
  <p> <br> </p>

  ```
  GET /account/logout
  ```
  This endpoint is used to log out of the current user account.
  <p> <br> </p>

  
* --- User ---

  ```
  GET /user/
  ```
  This endpoint is used to retrieve the profile information of the currently authenticated user.
  <p> <br> </p>
  
  ```
  GET /user/edit/:id
  ```
  This endpoint is used to retrieve the user profile information for editing.
  <p> <br> </p>
  
  ```
  PATCH /user/edit/:id
  ```
  This endpoint is used to update the user profile with the specified `id`. how to use:
  ```
  {
    "username" : "your username"
    "name" : "your name",
    "email": "youremail@gmail.com",
    "password": "your password",
    "phone" : "085333222111"
  }
  ```
  <p> <br> </p>
  
  ```
  GET /user/fav
  ```
  This endpoint is used to retrieve the list of favorite posts associated with the currently authenticated user.
  <p> <br> </p>
  
  
* --- Post ---
   ```
  GET /post/
  ```
  This endpoint is used to get all posts.
  <p> <br> </p>

  ```
  GET /post/find/:id
  ```
  This endpoint is used to get a specific post.
  <p> <br> </p>

  ```
  GET /post/my
  ```
  This endpoint is used to get posts created by the user.
  <p> <br> </p>

  ```
  GET /post/edit/:id
  ```
  This endpoint is used to get a post for editing.
  <p> <br> </p>

   ```
   POST /post/find/:id
   ```
  This endpoint is used to add a post to the list of favorite posts.
  <p> <br> </p>

   ```
   POST /post/create
   ```
  This endpoint is used to create a new post. How to use:
  ```
  {
    "title": "Your post title",
    "breed": "Your animal breed",
    "description": "Description title",
    "longitude": 1.23,
    "latitude": 2.11,
    "id_animal": 1
  }
  ```
  <p> <br> </p>

  ```
  PATCH /post/edit/:id
  ```
  This endpoint is used to update a post without changing the picture. How to use:
  ```
   {
    "title": "Your post title",
    "breed": "Your animal breed",
    "description": "Description title",
    "longitude": 1.23,
    "latitude": 2.11
  }
  ```
  <p> <br> </p>
  
  ```
  PATCH /post/edit/pict/:id
  ```
  This endpoint is used to update a post, including changing the picture
   ```
   {
    "title": "Your post title",
    "breed": "Your animal breed",
    "description": "Description title",
    "longitude": 1.23,
    "latitude": 2.11
  }
  add file to form-data with name 'file'
  ```
  <p> <br> </p>
  
  ```
  DELETE /post/:id
  ```
  This endpoint is used to delete a post.
  <p> <br> </p>

  ```
  DELETE /post/favourite/:id
  ```
  This endpoint is used to remove a post from the list of favorite posts.
