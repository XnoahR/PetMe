import {login, logout, register} from "../controllers/authController.js";
import  Express  from "express";
import authMiddleware from "../middlewares/authMiddleware.js";

const router = Express.Router();

router.post("/login", login);
router.post("/register", register);
router.get("/logout", authMiddleware,logout);

export default router;