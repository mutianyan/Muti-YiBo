const picList = document.querySelector('#pic-list');

// for pictures

function renderPics(doc){
	let li = document.createElement('li');
	let desc = document.createElement('span');
	let image = document.createElement('IMG');
	let comments = document.createElement('span');

	let button = document.createElement('BUTTON');
	let button_text = document.createTextNode('Add Comment');


	li.setAttribute('data-id', doc.id);
	desc.textContent = doc.data().desc;
	image.src = doc.data().source;
	comments.textContent = doc.data().comments;
	button.appendChild(button_text);

	li.appendChild(desc);
	li.appendChild(image);
	li.appendChild(comments);
	li.appendChild(button);

	picList.appendChild(li);



	// update the comment when click the 'button'
	button.addEventListener('click', (e) => {
		e.stopPropagation();
		let id = e.target.parentElement.getAttribute('data-id');
		var comment_text = document.getElementById("add_comment").value;
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

// ---- end of pictures


// get a reference of that form 
// const form = document.querySelector("#add-cafe-form");
// const form2= document.querySelector("add-comment-form")

// form.addEventListener('submit', (e)=>{
// 	e.preventDefault(); // prevent fresh the page
// 	db.collection('pictures').add({
// 		name: form.name.value, // grab value by name tag
// 		city: form.city.value 	
// 	});
// 	// clear the fields
// 	form.name.value ='';
// 	form.city.value = '';




// })















