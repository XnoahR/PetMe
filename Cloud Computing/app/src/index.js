import express from "express";
import "dotenv/config";
import cors from "cors";

import fileUpload from "express-fileupload";
import userRoutes from "./routes/userRoute.js";
import postRoutes from "./routes/postRoute.js";
import authRoutes from "./routes/authRoute.js";
import authMiddleware from "./middlewares/authMiddleware.js";
import notificatttionRoute from "./routes/notificationRoute.js";

const app = express();
const port = process.env.PORT || 3000;


app.use(express.json());
app.use(fileUpload());
app.use("/user", authMiddleware, userRoutes);
app.use("/post", authMiddleware, postRoutes);
app.use("/account", authRoutes);
app.use("/notification", notificatttionRoute);


app.use((req, res, next) => {
  res.status(404).send("Page not found!");
});

app.listen(port, () => {
  console.log(`listening at http://localhost:${port}`);
});
