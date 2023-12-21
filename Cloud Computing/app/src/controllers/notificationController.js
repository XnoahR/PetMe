import notification from "../models/notificationModel.js";
import Express from "express";

const app = Express();
app.use(Express.json());
const router = Express.Router();

const createNotification = (req, res) => {
  const { title, description, date } = req.body;
  notification
    .create({
      title,
      description,
      date,
    })
    .then((result) => {
      res.status(200).json({
        status: "Success",
        message: "Notification created",
        data: result,
      });
    });
};

const getNotification = (req, res) => {
  notification.findAll().then((result) => {
    res.status(200).json({
      status: "Success",
      message: "Notification found",
      data: result,
    });
  });
};

const deleteNotification = (req, res) => {
  const id = req.params.id;
  notification.destroy({ where: { id: id } }).then((result) => {
    res.status(200).json({
      status: "Success",
      message: "Notification deleted",
      data: result,
    });
  });
};

const updateNotification = (req, res) => {
  const id = req.params.id;
  const { title, description, date } = req.body;
  notification
    .update(
      {
        title: title,
        description: description,
        date: date,
      },
      { where: { id: id } }
    )
    .then((result) => {
      res.status(200).json({
        status: "Success",
        message: "Notification updated",
        data: result,
      });
    });
};

export {
  createNotification,
  getNotification,
  deleteNotification,
  updateNotification,
};
