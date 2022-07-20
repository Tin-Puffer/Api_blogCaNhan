var blog_link ="http://localhost:8000/v1/blog"

detailPost();
function detailPost(){
    // console.log("local"+localStorage.getItem("id"))
    var id=localStorage.getItem("id");
    fetch(blog_link+`/`+id)
    .then(function(value)
    {
        return value.json();
    })
    .then(function(value)
    {
        console.log(value._id)
        // localStorage.removeItem("id");
        var main= document.querySelector(".main2")
        var html= `
        <header class="masthead" style="background-image: url('${value.Image}')">
        <div class="container position-relative px-4 px-lg-5">
                <div class="row gx-4 gx-lg-5 justify-content-center">
                    <div class="col-md-10 col-lg-8 col-xl-7">
                        <div class="post-heading">
                            <h1>${value.Title}</h1>
                            <h2 class="subheading">${value.Decript}</h2>
                            <span class="meta">
                                Posted by
                                <a href="#!">By Admin</a>
                                ${value.Time}
                            </span>
                        </div>
                    </div>
                </div>
            </div> 
            </header>`
      main.innerHTML= html;
      var content_main= document.querySelector(".content");
      html=`<p>${value.Content}</p>
      <p> 
                            Placeholder text by
                            <a href="http://spaceipsum.com/">Space Ipsum</a>
                            &middot; Images by
                            <a href="https://www.flickr.com/photos/nasacommons/">NASA on The Commons</a>
                        </p>`
      content_main.innerHTML=html;

      });
}