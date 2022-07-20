const {Blog}= require("../model/model");

const BlogController={
    addPost: async(req,res)=>{
        try {
            const newBlog= new Blog(req.body);
            const saveBlog = await newBlog.save();
            res.status(200).json(saveBlog);
        } catch (error) {
            res.status(500).json(error);
        }
    },
    getAllPost: async(req,res)=>{
        try {
            const allpost = await Blog.find();
            res.status(200).json(allpost);
        } catch (error) {
            res.status(500).json(error);
        }
    },
    getBlog: async(req,res)=>{
        try {
            const getblog = await Blog.findById(req.params.id)
            res.status(200).json(getblog);
        } catch (error) {
            res.status(500).json(error);
        }
    },
    updateBlog: async(req,res)=>{
        try {
            const blog = await Blog.findById(req.params.id)
            await blog.updateOne({$set: req.body})
            res.status(200).json("SUCCESS UPDATE");
        } catch (error) {
            res.status(500).json(error);
        }
    },
    deleteBlog: async(req,res)=>{
        try {
            
            await Blog.findByIdAndDelete(req.params.id);
            res.status(200).json("SUCCESS DELETE");
        }catch (error) {
            res.status(500).json(error);
        }
    }
} 
module.exports=BlogController;