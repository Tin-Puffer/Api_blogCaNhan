const express = require("express");
const cors = require("cors");
const app = express();
const mongoose = require("mongoose");
var bodyParser = require("body-parser");
const morgan = require("morgan");
const dotenv = require("dotenv");
const blogRoute =require("./route/blog");


mongoose.connect("mongodb+srv://tinpro007qaz:iloveyou113@cluster0.ap5zshd.mongodb.net/?retryWrites=true&w=majority",()=>{
    console.log("SUCCESS CONNECT.. ");
})

app.use(bodyParser.json({limit:"90mb"}));
app.use(cors());
app.use(morgan("common"));
//////////
// app.use("/v1/author",authorRoute)
app.use("/v1/blog",blogRoute)
app.listen(8000,()=>{
    console.log("server run on...");
})