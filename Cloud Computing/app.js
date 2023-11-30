const express = require("express");
const app = express();
const port = process.env.PORT || 3000;
const { getConnection, getBook, pool } = require("./db");
// Use let instead of const for variables that might be reassigned
let conn;

getConnection((connection) => {
    console.log("Mysql Connected...");
    conn = connection;
  });

  
app.get("/", (req, res) => {
  getBook(conn);
});

app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`);
});
