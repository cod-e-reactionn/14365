
  

  var textarea1 = document.getElementById("textarea1");
  var submit = document.getElementById("submit");

console.log(5+6);
  function submitClick(){

console.log(5+6);
  	var firebaseRef = firebase.database().ref();

  	var messageText = textarea1.value;

  	firebaseRef.push().set(messageText);

  }