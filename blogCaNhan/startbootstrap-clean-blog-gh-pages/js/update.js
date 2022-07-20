var blog_link = "http://localhost:8000/v1/blog";
var id = localStorage.getItem("idUpdate")
load_data();
function load_data()
{
   
    if(id)
    {
        fetch(blog_link+`/`+id)
    .then(function(value)
    {
        return value.json();
    })
    .then(function(e)
    {
        document.querySelector("#name").value=e.Title;
        document.querySelector("#email").value=e.Image;
        document.querySelector("#phone").value=e.Decript;
        document.querySelector("#message").value=e.Content;

      });
    }
}
document.getElementById("submitButton").onclick = function() {update()};
function update(){
    let current = new Date();
    let cDate = current.getFullYear() + '-' + (current.getMonth() + 1) + '-' + current.getDate();
    let cTime = current.getHours() + ":" + current.getMinutes() + ":" + current.getSeconds();
    let dateTime = cDate + ' ' + cTime;
    const title= document.querySelector("#name").value;
    const img= document.querySelector("#email").value;
    const decription= document.querySelector("#phone").value;
    const content= document.querySelector("#message").value;
    var baiviet={

        Image:img,
        Title:title,
        Decript:decription,
        Content:content,
        Time:dateTime,
    }
    var object={
        method: 'PUT',
        headers: {
         'Content-Type': 'application/json'
       },
        body: JSON.stringify(baiviet)

    }
    fetch(blog_link+`/`+id,object)
        .then(function(value)
        {
           return value.json()
        })
        .then(function(value)
        {
            window.location="index.html"
        })
        .catch((value) => {
            alert(" ERROR !")
        });
}

 