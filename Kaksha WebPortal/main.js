var config = {
    apiKey: "AIzaSyCZEk6ae-lwN4nQ5hrh5EmFN84YOSUAjWs",
    authDomain: "web-login-af73e.firebaseapp.com",
    databaseURL: "https://web-login-af73e.firebaseio.com",
    projectId: "web-login-af73e",
    storageBucket: "web-login-af73e.appspot.com",
    messagingSenderId: "196665620533"
  };
  firebase.initializeApp(config);

var messagesRef = firebase.database().ref('messages');

console.log(5+6);

document.getElementById('contactForm').addEventListener('submit', submitForm);

console.log(5+6);

function submitForm(e){
	e.preventDefault();
	console.log(5+6);



	var fullname = getInputVal('fullname');
	var adhar = getInputVal('adhar');
	var email = getInputVal('email');
	var collegename = getInputVal('collegename');
	
	var year = getInputVal('year');
	var branch = getInputVal('branch');

	saveMessage(fullname, adhar, email, collegename,  year, branch);
	// Show alert
  
  // Hide alert after 3 seconds
  
  // Clear form
  document.getElementById('contactForm').reset();

}



function getInputVal(id){
	return document.getElementById(id).value;

}

function saveMessage(fullname, adhar, email, collegename,  year, branch){
	var newMessageRef = messagesRef.child('100');
	newMessageRef.set({
		fullname:fullname,
		adhar:adhar,
		email:email,
		collegename:collegename,
		
		year:year,
		branch:branch,
	});
}