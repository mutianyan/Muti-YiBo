$(document).ready(function() { 
    $("#tab_bar").show();
    $("#imageView1").show(); 
    $("#imageView2").hide();
    $("#imageView3").hide();

    $("#firstMethod").click(function() { 
    $("#tab_bar").show();
    $("#imageView1").show();
    $("#imageView2").hide();
    $("#imageView3").hide(); 
});      
    $("#secondMethod").click(function() { 
    $("#tab_bar").show(); 
    $("#imageView2").show();
    $("#imageView1").hide(); 
    $("#imageView3").hide();
}); 
    $("#thirdMethod").click(function() { 
  
     
    $("#tab_bar").show(); 
    $("#imageView3").show();
    $("#imageView1").hide(); 
    $("#imageView2").hide();
 }); 

 //button shortkey function
document.onkeydown= function (e) {
    var theEvent = window.event || e; 
    var code = theEvent.keyCode || theEvent.which; 
    if (code == 37) { 
        $("#left_button").click(); 
    }else if(code == 39){
        $("#right_button").click();
    }else if(code == 49){
        $("#firstMethod").click();
    }else if(code == 50){
        $("#secondMethod").click();
    }else if(code == 51){
        $("#thirdMethod").click();
    }
    
    }

var count = 1;

// first view
const firstViewDiv = document.querySelector('#image_and_desc');
function renderFirstView(doc){

    let image_div = document.createElement('div');
    let div1 = document.createElement('div');
    let image = document.createElement('IMG');
    let label_desc = document.createElement('label');
    let desc = document.createElement('div');

    let label_artists = document.createElement('label');
    let artists = document.createElement('div');

    let label_year = document.createElement('label');
    let year = document.createElement('div');

    let comments = document.createElement("INPUT");
    let com_btn = document.createElement("BUTTON");

    image.setAttribute("id", "image1");
    label_desc.setAttribute("class", "message");
    label_desc.textContent= "Image Description";
    label_artists.textContent= "Artists";
    label_year.textContent= "Year";

    image_div.setAttribute("id", "image_div");
    comments.setAttribute("id", "comments");
    div1.setAttribute("id", "div1");
    comments.setAttribute("placeholder", "The Description");
    com_btn.setAttribute("id", "add-comment");
    button_text = document.createTextNode('Add Comment');

    // li.setAttribute('data-id', doc.id);
    desc.textContent = doc.data().desc;
    artists.textContent = doc.data().artists;
    year.textContent = doc.data().year;

    image.src = doc.data().source;
    comments.placeholder = doc.data().comments;

    com_btn.appendChild(button_text);
    image_div.appendChild(image);
    firstViewDiv.appendChild(image_div);
    div1.appendChild(label_desc);
    div1.appendChild(desc);
    div1.appendChild(label_artists);
    div1.appendChild(artists);
    div1.appendChild(label_year);
    div1.appendChild(year);
    div1.appendChild(comments);
    div1.appendChild(com_btn);
    firstViewDiv.appendChild(div1);


    // update the comment when click the 'button'
    com_btn.addEventListener('click', (e) => {
        console.log("clicked the comment button");
        e.stopPropagation();
        var comment_text = document.getElementById("comments").value;
        console.log(comment_text);
        console.log(db.collection('pictures').doc(doc.id));
        db.collection('pictures').doc(doc.id).update({
            comments: comment_text
        })
    })


    // click right button
    var right_btn = document.getElementById("right_button");
    right_btn.addEventListener('click', (e) => {
        e.stopPropagation();
        count++;
        console.log('hello');
        console.log('count is: ', count);
        // remove the previous component
        var element = document.getElementById('div1');
        element.parentNode.removeChild(element);
        var element2 = document.getElementById('image_div');
        element2.parentNode.removeChild(element2);
        render();
        //document.location.reload(true);

    // db.collection('pictures').where('pic-id', '==', count).get().then((snapshot) => {
    // //console.log(snapshot.docs);

    // // get the data of the doc
    // snapshot.docs.forEach(doc => {
    //     //console.log(doc.data)
    //     renderFirstView(doc);
    // })
    // })





    })

    // click left button
    var left_btn = document.getElementById("left_button");
    left_btn.addEventListener('click', (e) => {
        e.stopPropagation();
        count--;
        console.log('count is: ', count);
        var element = document.getElementById('div1');
        console.log(element.parentNode);
        element.parentNode.removeChild(element);
        var element2 = document.getElementById('image_div');
        element2.parentNode.removeChild(element2);
        render();

    })



}


// get the collection from firebase

db.collection('pictures').where('pic-id', '==', count).get().then((snapshot) => {
    //console.log(snapshot.docs);

    // get the data of the doc
    snapshot.docs.forEach(doc => {
        //console.log(doc.data)
        renderFirstView(doc);
    })
})



function render(){
db.collection('pictures').where('pic-id', '==', count).get().then((snapshot) => {
    //console.log(snapshot.docs);

    // get the data of the doc
    snapshot.docs.forEach(doc => {
        //console.log(doc.data)
        renderFirstView(doc);
    })
})
}
// approach 1: query the object collection from firebase, then loop to get the next one when the button is clicked

// approach 2: get the entire collection, then store into array, finally do array access






//second view

const secondViewList = document.querySelector('#imageShow2');
function renderSecondView(doc){
    let div = document.createElement('div');
    let image = document.createElement('IMG');

    div.setAttribute("id", "div_container");
    image.setAttribute("id", "pic");

    div.setAttribute('data-id', doc.id);
    image.src = doc.data().source;


    div.appendChild(image);




    // Show the description when mouse over
    image.addEventListener('mouseover', (e) => {
        e.stopPropagation();
        // get the description of the image
        console.log(doc.data().desc);
        var desc = doc.data().desc;

        // create a span 
        let text = document.createElement('div');
        text.setAttribute("id", "tip-content");

        text.textContent = desc;
        div.appendChild(text);




    })

    // Clear the description when mouse out
    image.addEventListener('mouseout', (e) => {
        e.stopPropagation();
        var elem = document.getElementById('tip-content');
        elem.parentNode.removeChild(elem);

    })


        secondViewList.appendChild(div);
}

// get the collection from firebase
db.collection('pictures').get().then((snapshot) => {
    //console.log(snapshot.docs);

    // get the data of the doc
    snapshot.docs.forEach(doc => {
        //console.log(doc.data)
        renderSecondView(doc);
    })
})





// third view

const picList = document.querySelector('#pic_list');


function renderPics(doc){
    let li = document.createElement('li');
    let label_desc = document.createElement('label');
    let desc = document.createElement('div');

    let label_artists = document.createElement('label');
    let artists = document.createElement('div');


    let label_year = document.createElement('label');
    let year = document.createElement('div');

    let image = document.createElement('IMG');
    let comments = document.createElement("INPUT");
    

    let button = document.createElement('BUTTON');
    let button_text = document.createTextNode('Add Comment');
    let big_div = document.createElement('div');
    let small_div = document.createElement('div');
    let small_div2 = document.createElement('div');

    image.setAttribute("id", "pic");
    label_desc.textContent= "Image Description: ";
    label_artists.textContent= "Artists: ";
    label_year.textContent= "Year: ";
    desc.setAttribute("id", "desc");
    comments.setAttribute("id", "comments");
    button.setAttribute("id", "button");
    big_div.setAttribute("id", "big_div");
    small_div.setAttribute("id", "small_div");
    small_div2.setAttribute("id", "small_div2");
    comments.setAttribute("type", "text");

    li.setAttribute("id", "list");
    li.setAttribute('data-id', doc.id);
    desc.textContent = doc.data().desc;
    artists.textContent = doc.data().artists;
    year.textContent = doc.data().year;


    image.src = doc.data().source;
    comments.placeholder = doc.data().comments;
    button.appendChild(button_text);

    var br = document.createElement("br");

    small_div.appendChild(label_desc);
    small_div.appendChild(desc);
    small_div.appendChild(br); // br 
    small_div.appendChild(label_artists);
    small_div.appendChild(artists);
    small_div.appendChild(br); // br 
    small_div.appendChild(label_year);
    small_div.appendChild(year);
    small_div.appendChild(br);  // br


    small_div2.appendChild(comments);
    small_div2.appendChild(button);

    big_div.appendChild(image);
    big_div.appendChild(small_div);
    big_div.appendChild(small_div2);

    li.appendChild(big_div);
    picList.appendChild(li);


    // update the comment when click the 'button'
    button.addEventListener('click', (e) => {
        e.stopPropagation();
        let id = e.target.parentElement.parentElement.parentElement.getAttribute('data-id');
        console.log(id);
        var comment_text = document.getElementById("comments").value;
        console.log(comment_text);
        // console.log(db.collection('pictures').doc(id));
        db.collection('pictures').doc(id).update({
            comments: comment_text
        })
    })

}

// get the collection from firebase
db.collection('pictures').get().then((snapshot) => {
    //console.log(snapshot.docs);

    // get the data of the doc
    snapshot.docs.forEach(doc => {
        //console.log(doc.data)
        renderPics(doc);
    })
})
});        


// to do:
// function such as adding slides, deleting slides

    
           