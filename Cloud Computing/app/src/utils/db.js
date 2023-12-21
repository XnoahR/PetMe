import { Sequelize } from "sequelize";
import "dotenv/config";
import mariadb from "mariadb";

const sequelize = new Sequelize(
  process.env.DB_NAME,
  process.env.DB_USER,
  process.env.DB_PASSWORD,
  {
    host: process.env.DB_HOST,
    port: process.env.DB_PORT,
    dialect: process.env.DB_DIALECT,
    logging:
      process.env.NODE_ENV === "development"
        ? (...mgs) => {
            console.log(mgs);
          }
        : false,
  }
);

// const createTable = () => {
//     const q = `CREATE TABLE IF NOT EXISTS bookTest (
//         id INT(11) PRIMARY KEY AUTO_INCREMENT,
//         judul VARCHAR(255) NOT NULL,
//         penulis VARCHAR(255) NOT NULL,
//         tahun VARCHAR(255) NOT NULL
//     )`;

//     mariadb.getConnection({
//         host: process.env.DB_HOST,
//         user: process.env.DB_USER,
//         password: process.env.DB_PASSWORD,
//         port: process.env.DB_PORT,
//         database: process.env.DB_NAME,
//     }).then((conn) => {
//         conn.query(q, (err, result) => {
//             if (err) throw err;
//             console.log("Table created");
//         });
//     }).catch((err) => {
//         console.log(err.message);
//     });

export default sequelize;
