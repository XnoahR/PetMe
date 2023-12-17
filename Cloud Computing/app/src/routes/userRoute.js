import Express from "express";

import {
  profile,
  editProfile,
  updateProfile,
  userFavourite
  
} from "../controllers/userController.js";

const router = Express.Router();

router.get("/", profile);
router.get("/edit/:id", editProfile);
router.get("/fav", userFavourite);
router.patch("/edit/:id", updateProfile);


export default router;
