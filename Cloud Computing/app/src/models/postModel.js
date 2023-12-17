import { DataTypes } from "sequelize";
import sequelize from "../utils/db.js";
import animal from "./animalModel.js";
import User from "./userModel.js";

const post = sequelize.define(
  "post",
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
    upload_date: {
      type: DataTypes.DATE,
      allowNull: false,
    },
    status: {
      type: DataTypes.INTEGER,
      allowNull: false,
    },
    breed:{
      type: DataTypes.STRING,
      allowNull: true,
    },
    post_picture: {
      type: DataTypes.STRING,
      allowNull: false,
    },
    id_user: {
      type: DataTypes.INTEGER,
      allowNull: false,
    },
    id_animal: {
      type: DataTypes.INTEGER,
      allowNull: false,
    },
    description: {
      type: DataTypes.TEXT,
      allowNull: false,
    },
    latitude: {
      type: DataTypes.FLOAT,
      allowNull: false,
    },
    longitude: {
      type: DataTypes.FLOAT,
      allowNull: false,
    },

  },
  {
    tableName: "post",
    timestamps: false,
  }
);

post.belongsTo(User, { foreignKey: "id_user" });
post.belongsTo(animal, { foreignKey: "id_animal" });

sequelize.sync();

export default post;
