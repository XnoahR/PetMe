const express = require("express");
const {Storage} = require('@google-cloud/storage');
const path = require("path");
const ejs = require("ejs");
const app = express();
const port = process.env.PORT || 3000;
const { getConnection, getBook, pool } = require("./db");
// Use let instead of const for variables that might be reassigned
let conn;

app.set("view engine", "ejs");
app.use(express.static(path.join(__dirname, "views")));
const credentials = new Storage({
  keyFilename: path.join(__dirname, './credentials/pomomato-deploy-a8a3a6822d62.json'),
  projectId: 'pomomato-deploy',
});
const bucket = credentials.bucket('tomadoro');
// credentials.getBuckets().then(x => console.log(x));
const fileName = 'ayng.jpeg';
const folderName = 'testFolder'

getConnection((connection) => {
    console.log("Mysql Connected...");
    conn = connection;
  });



  
app.get("/", async (req, res) => {
  try {
    // Get a list of files in the bucket
    const [files] = await bucket.getFiles();

    // Log each file in the console
    files.forEach(file => {
      console.log(file.name);
    });

    res.status(200).send('Check the console for a list of files in the bucket.');
  } catch (error) {
    console.error('Error retrieving files:', error);
    res.status(500).send('Internal Server Error');
  }
  // getFiles();
});

app.get("/test", async (req, res) => {
  try {
    const [files] = await bucket.getFiles({
      prefix: `${folderName}/`, // Set the folder path as the prefix
    });

    if (files.length > 0) {
      console.log(`Files in ${folderName} folder:`);
      files.forEach(file => {
        console.log(file.name);
      });
    } else {
      console.log(`No files found in ${folderName} folder.`);
    }
  } catch (error) {
    console.error('Error getting file list:', error);
  }
}
);

app.get("/get", async (req, res) => {
  express.query = req.query;
  
    try {
      const [file] = await bucket.file(path.join(folderName, fileName).replace(/\\/g, '/')).get();
  
      if (file) {
        const filePath = file.name;
        res.status(200)
        .render("./index.ejs", { dataLink: `https://storage.googleapis.com/tomadoro/${filePath}` });
        
      } else {
        console.log(`File not found: ${path.join(folderName, fileName).replace(/\\/g, '/')}`);
      }
    } catch (error) {
      console.error('Error getting file path:', error);
    }
});






app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`);
});
