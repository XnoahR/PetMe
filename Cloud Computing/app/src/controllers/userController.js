import Express from "express";
import fileUpload from "express-fileupload";
import cors from "cors"; // Cross Origin Resource Sharing
import user from "../models/userModel.js";
import favourite from "../models/favouriteModel.js";
import { bucket, folderName } from "../utils/bucket.js";
import md5 from "md5";
import fs from "fs";
import path from "path";
import multer from "multer";
import post from "../models/postModel.js";
import sharp from "sharp";

const app = Express();

app.use(fileUpload());
app.use(Express.json());
app.use(cors());

//Buat ke halaman profile
const profile = (req, res) => {
  const id = req.user.id;
  user
    .findAll({
      where: { id: id },
    })
    .then((result) => {
      //json
      res.send(result);
    });
};

//Buat ke halaman edit profile
const editProfile = (req, res) => {
  const id = req.user.id;
  user
    .findAll({
      where: { id: id },
    })
    .then((result) => {
      //json
      res.json(result);
    });
};

//Buat update profile
const updateProfile = async (req, res) => {
  try {
    const { username, email, name, password, phone } = req.body;
    const id = req.user.id;
    const file = req.files;
    let newProfilePicture = await user.findByPk(id).profile_picture;

    if (file) {
      const fileName = file.file.name;
      const ext = path.extname(fileName);
      const newFileName = md5(new Date().getTime()) + ext;
      const fileSize = file.file.size;
      const allowedFileTypes = /jpeg|jpg|png/;
      if (!allowedFileTypes.test(path.extname(fileName).toLowerCase())) {
        res.status(400).json({ message: "Invalid file type" });
      }
      if (fileSize > 5 * 1024 * 1024) {
        res.status(400).json({ message: "File size too large" });
      }
      newProfilePicture = `${req.protocol}://storage.googleapis.com/petmebucket/user_data/rsz${newFileName}`;

      await uploadFileToBucket(file.file, newFileName); // Separate function for file upload
    }

    // Update the user record
    await user.update(
      {
        username,
        email,
        name,
        password,
        phone,
        profile_picture: newProfilePicture,
      },
      {
        where: { id: id },
      }
    );

    res.json({
      message: "Profile updated",
      data: {
        username,
        email,
        name,
        password,
        profile_picture: newProfilePicture,
      },
    });
  } catch (error) {
    console.error(error);
    res.status(500).json({ message: "Internal Server Error" });
  }
};

const upload = multer({
  storage: multer.memoryStorage(),
  limits: {
    fileSize: 5 * 1024 * 1024, // keep images size < 5 MB
  },
});

const uploadFileToBucket = async (file, newFileName) => {
  const rszFileName = `rsz${newFileName}`;

  return new Promise((resolve, reject) => {
    file.mv(`./public/img/${newFileName}`, async (err) => {
      if (err) {
        console.error(err);
        reject("Error uploading file");
      }
      try {
        await sharp(`./public/img/${newFileName}`)
          .resize(512, 512)
          .toFile(`./public/img/${rszFileName}`);
        await bucket.upload(`./public/img/${rszFileName}`, {
          destination: `user_data/${rszFileName}`,
          public: true,
          metadata: {
            contentType: "image/png",
          },
        });
        // fs.unlinkSync(`./public/img/${newFileName}`);

        resolve();
      } catch (error) {
        console.error(error.message);
        reject("Error uploading file to bucket");
      }
    });
  });
};

//Menambahkan favourite
const addFavourite = (req, res) => {
  const id_user = req.user.id;
  const id_post = req.body.id_post;
  try {
    favourite
      .create({
        id_user: id_user,
        id_post: id_post, 
      })
      .then((result) => {
        res.status(200).json({ message: "Favourite added", data: result });
      });
  } catch {
    res.send(err.message);
  }
};

//Mengakses favourite
const userFavourite = (req, res) => {
  const id_user = req.user.id;
  try {
    favourite
      .findAll({
        where: { id_user: id_user },
        include: [
          {
            model: post,
            attributes: [
              "id",
              "title",
              "upload_date",
              "status",
              "breed",
              "post_picture",
              "id_user",
              "id_animal",
              "description",
              "latitude",
              "longitude",
            ],
          },
        ],
      })
      //join table
      .then((result) => {
        res.status(200).json(result);
      });
  } catch (err) {
    res.send(err.message);
  }
};

//Menghapus favourite

export {
  profile,
  editProfile,
  updateProfile,
  addFavourite,
  userFavourite,
  uploadFileToBucket,
};
