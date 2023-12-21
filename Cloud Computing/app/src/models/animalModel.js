import { DataTypes } from "sequelize";
import sequelize from "../utils/db.js";

const animal = sequelize.define("animal", {
  id: {
    type: DataTypes.INTEGER,
    primaryKey: true,
    autoIncrement: true,
    allowNull: false,
  },
  type: {
    type: DataTypes.ENUM("cat", "dog"),
    allowNull: false,
  },
  breed: {
    type: DataTypes.STRING,
    allowNull: true,
    set(value) {
      this.setDataValue("breed", value.toLowerCase());
    } 
  },
  fur: {
    type: DataTypes.STRING,
    allowNull: true,
  },
},
{
  tableName: "animal",
  timestamps: false,
});

sequelize.sync();

export default animal;
