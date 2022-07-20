const mongoose = require("mongoose")
const blogschema = new mongoose.Schema({
    Image:{
        type:String,
        required:true
    },
    Title:{
        type:String,
    },
    Decript:{
        type:String,
    },
    Time:{
        type:String,
    },
    Content:{
        type:String,
    },

});
let Blog = mongoose.model("Blog",blogschema);
module.exports ={Blog};