import notification from "../models/notificationModel.js";
import Express from "express";
import { createNotification, getNotification, deleteNotification, updateNotification } from "../controllers/notificationController.js";
const app = Express();
app.use(Express.json());
const router = Express.Router();

router.get("/", getNotification);
router.post("/", createNotification);
router.delete("/:id", deleteNotification);
router.patch("/:id", updateNotification);

export default router;