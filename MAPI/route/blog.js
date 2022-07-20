const BlogController = require("../controller/blogController");

const router = require("express").Router();

router.post("/",BlogController.addPost);
router.get("/",BlogController.getAllPost)
router.get("/:id",BlogController.getBlog);
router.put("/:id",BlogController.updateBlog);
router.delete("/:id",BlogController.deleteBlog);

module.exports= router ;