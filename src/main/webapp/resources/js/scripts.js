/*!
* Start Bootstrap - Shop Homepage v5.0.0 (https://startbootstrap.com/template/shop-homepage)
* Copyright 2013-2021 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-shop-homepage/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project
function postinfo(url, table, time) {
	method = "post";
	var form1 = document.getElementById("hiddenform");
	form1.setAttribute("method", method);
	form1.setAttribute("action", url);
	
	var hiddenField1 = document.createElement("input");
	hiddenField1.setAttribute("type", "hidden");
    hiddenField1.setAttribute("name", "tableOid");
    hiddenField1.setAttribute("value", table);
    
    var hiddenField2 = document.createElement("input");
	hiddenField2.setAttribute("type", "hidden");
    hiddenField2.setAttribute("name", "time");
    hiddenField2.setAttribute("value", time);
    
    var hiddenField3 = document.createElement("input");
	hiddenField3.setAttribute("type", "hidden");
    hiddenField3.setAttribute("name", "covers");
    var s = document.getElementById("covers")
    hiddenField3.setAttribute("value", s.options[s.selectedIndex].value);
    
    
    form1.appendChild(hiddenField1);
    form1.appendChild(hiddenField2);
    form1.appendChild(hiddenField3);
    document.body.appendChild(form1);
    form1.submit();
}

function logout() {
	document.cookie = 'booksysSid' + '=; expires=Thu, 01 Jan 1999 00:00:10 GMT;';
	location.reload(true);
}