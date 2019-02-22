const cafeList = document.querySelector('#cafe-list');

// create element and render cafe
// watch JS DOM manipulation

function renderCafe(doc){
	let li = document.createElement('li');
	let name = document.createElement('span');
	let city = document.createElement('span');

	li.setAttribute('data-id', doc.id);
	name.textContent = doc.data().name;
	city.textContent = doc.data().city;

	li.appendChild(name);
	li.appendChild(city);
	cafeList.appendChild(li);

}

// get the collection from firebase
db.collection('cafes').get().then((snapshot) => {
	//console.log(snapshot.docs);

	// get the data of the doc
	snapshot.docs.forEach(doc => {
		//console.log(doc.data)
		renderCafe(doc);
	})
})



// get a reference of that form 
const form = document.querySelector("#add-cafe-form");

form.addEventListener('submit', (e)=>{
	e.preventDefault(); // prevent fresh the page
	db.collection('cafes').add({
		name: form.name.value, // grab value by name tag
		city: form.city.value 	
	});
	// clear the fields
	form.name.value ='';
	form.city.value = '';




})















