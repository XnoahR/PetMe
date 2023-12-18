import Express from "express";
import {
  getPost,
  createPost,
  findPost,
  editPost,
  updatePost,
  updatePostPicture,
  deletePost,
  userPost,
  addFavourite,
  deleteFavourite,
} from "../controllers/postController.js";

const router = Express.Router();

router.get("/", getPost);
router.get("/find/:id", findPost);
router.post("/find/:id", addFavourite);
router.delete("/favourite/:id", deleteFavourite);
router.get("/my", userPost);
router.get("/edit/:id", editPost);
router.post("/create", createPost);
router.patch("/edit/:id", updatePost);
router.patch("/edit/pict/:id", updatePostPicture);
router.delete("/:id", deletePost);

export default router;
