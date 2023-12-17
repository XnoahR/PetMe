import { DataTypes } from "sequelize";
import sequelize from "../utils/db.js";
import User from "./userModel.js";
import post from "./postModel.js";

const favourite = sequelize.define(
  "favourite",
  {
    id: {
      type: DataTypes.INTEGER,
      primaryKey: true,
      autoIncrement: true,
      allowNull: false,
    },
    id_user: {
      type: DataTypes.INTEGER,
      allowNull: false,
    },
    id_post: {
      type: DataTypes.INTEGER,
      allowNull: false,
    },
  },
  {
    tableName: "favourite",
    timestamps: false,
  }
);

favourite.belongsTo(User, { foreignKey: "id_user" });
favourite.belongsTo(post, { foreignKey: "id_post" });

sequelize.sync();
export default favourite;
