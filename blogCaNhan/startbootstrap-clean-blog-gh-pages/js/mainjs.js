var blog_link = "http://localhost:8000/v1/blog";
function start() {
  getapi();
}
start();

function getapi() {
  fetch(blog_link)
    .then(function (value) {
      return value.json();
    })
    .then(function (value) {
      var main = document.querySelector(".main");
      if (main) {
          
        var html = value.map(function (e) { 
          console.log(e._id);
          return `
             <div class="post-preview">
             <div class="click-menu">
                            <ul class="nav">
                                <li onclick="handleEdit('${e._id}')" class="item edit" >EDIT</li>
                                <li onclick="handleDelete('${e._id}')" class="item delete">DELETE</li>
                            </ul>
                        </div>
             <div class="test" onclick="handleClick('${e._id}')">
                 <h2 class="post-title">${e.Title}</h2>
                 <h3 class="post-subtitle">${e.Decript}</h3>
             </div>
             <p class="post-meta">
                 Posted by
                 <a href="#!">ADMIN</a>
                 ${e.Time}
             </p>
             </div>`;
        });
        main.innerHTML = html.join("");
   
      }
    });

}
function handleEdit(e){
  localStorage.setItem("idUpdate",e)
     console.log(e)
     window.location="update.html"
}
function handleDelete(id){
  var obj={
    method: 'DELETE',
    headers: {
        'Content-Type': 'application/json'
      }
    }
  fetch(blog_link+`/`+id,obj)
  .then(function(res)
  {
      res.json();
      start();
    
  })
}
 function handleClick(e){
     localStorage.setItem("id",e)
     console.log(e)
     window.location="post.html"
 }
