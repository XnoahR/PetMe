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

|Bangkit ID|Name|Learning Path|University|Contact|
|-----|-----|-----|-----|-----|
|C175BSY3576|Ramandhika Ilham Pratama|Cloud Computing|STMIK Sinar Nusantara|[Github](https://github.com/ramandhika) [LinkedIn](https://www.linkedin.com/in/ramandhika-ip/)|
|C312BSY3846|Rio Saputro|Cloud Computing|Sebelas Maret University|[Github](https://github.com/XnoahR) [LinkedIn](https://www.linkedin.com/in/icedmocca/)|
|M312BSY1675|Aditya Prasetya Nugraha|Machine Learning|Sebelas Maret University|[Github](https://github.com/Adztrz) [LinkedIn](https://www.linkedin.com/in/aditia-nugraha-65071a210/)|
|M010BSY0955|Balthasar Toslur Hasaraini Lase|Machine Learning|University of Indonesia|[Github](https://github.com/lasebalth) [LinkedIn](https://www.linkedin.com/in/balthasarthlase/)|
|M312BSY1691|Wahyu Heriyanto|Machine Learning|Sebelas Maret University|[Github](https://github.com/WahyuHeriyanto) [LinkedIn](https://www.linkedin.com/in/wahyu-heriyanto-bb972921a/)|
|A123BSY2575|Arbi Julianto|Mobile Development|Politeknik Elektronika Negeri Surabaya|[Github](https://github.com/Arbikoj) [LinkedIn](https://www.linkedin.com/in/aarbii/)|
|A229BSY2114|Fardan Maula Azizi|Mobile Development|Jenderal Soedirman University|[Github](https://github.com/fardanmaulaazizi) [LinkedIn](https://www.linkedin.com/in/fardanmaula/)|



---
## Documentation
<p>You can access the Documentation here! <br> </p>

[PetMe API Documentation](https://github.com/XnoahR/PetMe/blob/main/Cloud%20Computing/README.md) 

---
# PetMe - Rest API

## Cloud Architecture

![Cloud Architecture](https://raw.githubusercontent.com/XnoahR/PetMe/main/asset/Cloud%20Architecture.png)
As depicted in the architectural blueprint, the project leveraged the following cloud services for its infrastructure:

-   **Cloud Storage**, which serves to store container images and assets such as user profile photos and website files.
-   **Cloud SQL (MySQL)**, which is used to store application data and handle structured data for applications.
-   **Cloud Run**, which is used to deploy our application API and Website Admin so that it can be accessed by everyone.

## Replicating Steps

Below are the steps to replicate this project.

### Step 1: Clone Repository

Clone this repository to your local machine using the following command:

```
git clone https://github.com/XnoahR/PetMe.git
cd Cloud Computing
cd app
```

### Step 2: Install Dependencies

Install the dependencies using the following command:

```
npm install
```

### Step 3: Create Database

Create a database in Cloud SQL, to populate the database we only need to run the program. The program will automatically create a table using Sequelize.

### Step 4: Configuration

Set up the environment configuration by creating a .env file and adding the database connection variables.

```
DB_DIALECT=mysql
DB_HOST=<DB_HOSTNAME or CLOUD_SQL_INSTANCE_PUBLIC_IP>
DB_USER=<DB_USER>
DB_NAME=<DB_NAME>
DB_PASSWORD=<DB_PASSWORD>
DB_PORT=3306

NODE_ENV=dev

JWT_SECRET= <JWT_SECRET>
JWT_EXPIRES_IN= <JWT_EXPIRES_IN>
JWT_COOKIE_EXPIRES_IN= <JWT_COOKIE_EXPIRES_IN>



CREDENTIAL_TYPE = <type>
CREDENTIAL_PROJECT_ID = <project_id>
CREDENTIAL_PRIVATE_KEY_ID = <private_key_id>
CREDENTIAL_PRIVATE_KEY = <private_key>
CREDENTIAL_CLIENT_EMAIL = <client_email>
CREDENTIAL_CLIENT_ID = <client_id>
CREDENTIAL_AUTH_URI = <auth_uri>
CREDENTIAL_TOKEN_URI = <token_uri>
CREDENTIAL_AUTH_PROVIDER_X509_CERT_URL = <auth_provider_x509_cert_url>
CREDENTIAL_CLIENT_X509_CERT_URL = <client_x509_cert_url>
CREDENTIAL_UNIVERSE_DOMAIN = <universe_domain>
```

### Step 5: Run

Start the server using the following command:

```
npm start
```

Open http://localhost:3000/ in a web browser and ensure the expected message is displayed.

Once testing is done, stop the server using CTRL+C then press Y.

### Step 6: API Test

Test the API using a testing application such as Postman. For documentation, you can refer to the Postman Documentation.

### Step 7: Deploy to the Cloud using Google Cloud Platform

As previously articulated in the context of the cloud architecture, we employed Cloud SQL (MySQL) and Cloud Run. The following are the steps to launch this project in the Cloud.

1. Enable the Cloud Build API and Cloud SQL Admin API.
2. Open Cloud Shell.
3. Clone the repository.
4. Create a database in Cloud SQL, to populate the database we only need to run the program. The program will automatically create a table using Sequelize.
5. Set up the environment configuration.
6. Deploy the application using the following command:

```
gcloud builds submit --tag gcr.io/PROJECT-ID/IMAGE-NAME
```

```
gcloud run deploy --image gcr.io/PROJECT-ID/IMAGE-NAME --platform managed
```

Access your deployed application by opening https://<your_application_url> in a web browser.
