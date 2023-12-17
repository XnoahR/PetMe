import Express from "express";
import post from "../models/postModel.js";
import favourite from "../models/favouriteModel.js";
import user from "../models/userModel.js";
import { uploadFileToBucket } from "./userController.js";
import fileUpload from "express-fileupload";
import path from "path";
import md5 from "md5";
import fs from "fs";



const app = Express();
app.use(fileUpload());
app.use(Express.json());
const router = Express.Router();

const getPost = (req, res) => {
  post.findAll().then((result) => {
    res.json(result);
  });
};

const createPost = async (req, res) => {
  try {
    const { title, id_animal, description, breed, latitude, longitude } =
      req.body;

    const id_user = req.user.id;
    const file = req.files;

    if (!file) return res.status(400).json({ message: "No file uploaded" });

    // Adjust the file handling based on your actual file structure
    // For example, if your file object has a 'name' property, you can use it like:
    const fileName = file.file.name;
    const ext = path.extname(fileName);
    const newFileName = md5(new Date().getTime()) + ext;
    const rszFileName = `https://storage.googleapis.com/petmebucket/user_data/rsz${newFileName}`;
    const folder = `./public/images/${rszFileName}`;
    const fileSize = file.file.size;
    const allowedFileTypes = /jpeg|jpg|png/;
    if (!allowedFileTypes.test(path.extname(fileName).toLowerCase())) {
      res.status(400).json({ message: "Invalid file type" });
    }
    if (fileSize > 12 * 1024 * 1024) {
      res.status(400).json({ message: "File size too large" });
    }

    // Adjust the file handling function based on your actual file handling approach
    await uploadFileToBucket(file.file, newFileName);

    post
      .create({
        title,
        upload_date: new Date(),
        status: 1,
        id_user,
        id_animal,
        description,
        breed,
        latitude,
        longitude,
        post_picture: rszFileName,
      })
      .then((result) => {
        res.status(201).json({ message: "Post created", data: result });
      });
  } catch (err) {
    res.status(500).json({ message: "Internal Server Error" });
  }
};

const findPost = async (req, res) => {
  const userId = req.user.id;
  const postId = req.params.id;
  let isFav = false;

  try {
    const favCheck = await favourite.findAll({
      where: { id_post: postId, id_user: userId },
    });

    if (favCheck.length > 0) {
      isFav = true;
    }

    const result = await post.findOne({
      where: { id: postId },
      include: [
        {
          model: user, // Assuming user is the Sequelize model for users
          attributes: ["name", "username", "phone"],
        },
      ],
    });

    if (!result) {
      res.status(204).json({ message: "Post not found" });
      return;
    }

    res.status(200).json({ message: "Post found", data: result, user: userId, isFav, favCheck });
  } catch (error) {
    console.error(error);
    res.status(500).json({ message: "Internal Server Error" });
  }
};


const userPost = (req, res) => {
  const user = req.user.id;
  // I want to get ID from user
  // json: {
  //  "id": 1,
  //  "exp": 1625241600,
  //}
  post
    .findAll({
      where: { id_user: user },
    })
    .then((result) => {
      res.json(result);
    });
};

const editPost = (req, res) => {
  const id = req.params.id;
  post
    .findAll({
      where: { id: id },
    })
    .then((result) => {
      res.json(result);
    });
};

const updatePost = (req, res) => {
  const id_user = req.user.id;
  const { title } = req.body;
  const id = req.params.id;
  post.update(
    {
      title: req.body.title,
      date: new Date(),
    },
    { where: { id: id, id_user: id_user } }
  );
  res.json({ message: `Post updated. ID: ${id}` });
};

const deletePost = async (req, res) => {
  const id = req.params.id;
  const id_user = req.user.id;
  try {
    await favourite.destroy({
      where: { id_post: id },
    });
    await post.destroy({
      where: { id: id, id_user: id_user },
    });
    res.json({ message: `Post deleted. ID: ${id}` });
  } catch (err) {
    res.send(err.message);
  }
};

const addFavourite = (req, res) => {
  const id = req.params.id;
  const id_user = req.user.id;
  favourite
    .create({
      id_user: id_user,
      id_post: id,
    })
    .then((result) => {
      res.json(result);
    });
};

const deleteFavourite = async (req, res) => {
  const id_post = req.params.id;
  const id_user = req.user.id;
  console.log(id_post, id_user);
  try {
    await favourite.destroy({
      where: { id_post: id_post, id_user: id_user },
    });
    res.json({ message: `Favourite deleted. ID: ${id_post}` });
  } catch (err) {
    res.send(err.message);
  }
};

export {
  getPost,
  createPost,
  findPost,
  editPost,
  updatePost,
  deletePost,
  addFavourite,
  deleteFavourite,
  userPost,
};
