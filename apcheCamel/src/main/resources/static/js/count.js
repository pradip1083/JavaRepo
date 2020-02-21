/**
 * Demo of JavaScript Closures
 */
function hello(){
	alert("Hello, I am in alert Box..!!");
}

var add=(function(){
 var counter=0;
 return function(){
	 return counter += 1;
 }

})();

function count(){
	//alert("in Count");
	document.getElementById("countMe").innerHTML=add();
}


