const mysql = require('mysql'); 

const pool = mysql.createPool({
    host: 'localhost',
    user: 'root',
    password: '',   
    database: 'librarow',
    connectionLimit: 10,
});

const getConnection = (cb) => {
    pool.getConnection((err, conn) => {
        if (err) throw err;
        cb(conn);
    });
};

const getBook = async (conn) =>{
    q = "SELECT * FROM buku";
    conn.query(q, (err, result) => {
        if(err) throw err;
        console.log(result);
        conn.release(); 
    })
}

module.exports = {
    getConnection,
    getBook,
    pool
};