import { DataTypes } from "sequelize";
import sequelize from "../utils/db.js";

const notification = sequelize.define(
    "notification",
    {
        id: {
        type: DataTypes.INTEGER,
        primaryKey: true,
        autoIncrement: true,
        allowNull: false,
        },
       title: {
        type: DataTypes.STRING,
        allowNull: false,
        },
        description: {
        type: DataTypes.STRING,
        allowNull: false,
        },
        date:{
        type: DataTypes.DATE,
        allowNull: false,
        },
        
    },
    {
        tableName: "notification",
        timestamps: false,
    }
    );  

sequelize.sync();
export default notification;