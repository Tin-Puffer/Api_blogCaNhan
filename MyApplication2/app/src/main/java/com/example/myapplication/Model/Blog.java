package com.example.myapplication.Model;

public class Blog {
    private String _id;
    private String Image;
    private String Title;
    private String Decript;
    private String Time;
    private String Content;
    private String __v;

    public Blog(String _id, String image, String title, String decript, String time, String content, String __v) {
        this._id = _id;
        Image = image;
        Title = title;
        Decript = decript;
        Time = time;
        Content = content;
        this.__v = __v;
    }

    public Blog() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDecript() {
        return Decript;
    }

    public void setDecript(String decript) {
        Decript = decript;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String get__v() {
        return __v;
    }

    public void set__v(String __v) {
        this.__v = __v;
    }
}
