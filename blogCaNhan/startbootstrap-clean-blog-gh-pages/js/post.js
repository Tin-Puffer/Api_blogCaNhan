var blog_link ="http://localhost:8000/v1/blog"

document.getElementById("submitButton").onclick = function() {new_post()};
function new_post(){
    const title= document.querySelector("#name").value;
    const img= document.querySelector("#email").value;
    const decription= document.querySelector("#phone").value;
    const content= document.querySelector("#message").value;
    console.log("1:" +title+" --");
    console.log("2:" +img+" --");
    console.log("3:" +decription+" --");
    console.log("4:" +content+" --");
    if(title && decription && content &&img)
    {
        let current = new Date();
        let cDate = current.getFullYear() + '-' + (current.getMonth() + 1) + '-' + current.getDate();
        let cTime = current.getHours() + ":" + current.getMinutes() + ":" + current.getSeconds();
        let dateTime = cDate + ' ' + cTime;
        var baiviet={

            Image:img,
            Title:title,
            Decript:decription,
            Content:content,
            Time:dateTime,
        }
        var object={
            method: 'POST',
            headers: {
             'Content-Type': 'application/json'
           },
            body: JSON.stringify(baiviet)

        }
        fetch(blog_link,object)
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

    
}
//     function addcosst()
//     {
//         var iputId=document.querySelector('#names')
//         var iputDcr=document.querySelector('#decription') 
//         var subject={

//             name:iputId.value,
//             decription:iputDcr.value
//         }
        
//         var object={
//             method: 'POST',
//             headers: {
//              'Content-Type': 'application/json'
//            },
//             body: JSON.stringify(subject)

//         }
//         fetch(coseipa,object)
//         .then(function(value)
//         {
//            return value.json()
//         })
//         .then(function(e)
//         {
//             var divBox=document.querySelector('#COSE')

//             divBox.innerHTML+=`<li id="cost${e.id}">
//             <h4>${e.name}</h4>
//             <p>${e.decription}</p>
//             <button onclick="deletecost(${e.id})" id="bntdelete">DELTE</button>
//             <button onclick="updatecost(${e.id} )" id="bntupdate">update</button>
//             </li> `
//         })
//         iputId.value=""
//         iputDcr.value=""
//  }