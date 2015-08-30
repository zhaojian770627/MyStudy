function getNewContent() {
	var request = getHTTPObject();
	if (request) {
		request.open("GET", "ajax.txt", true);
		request.onreadystatechange = function() {
			if (request.readyState == 4) {
				var para = document.createElement("p");
				var txt = document.createTextNode(request.responseText);
				para.appendChild(txt);
				document.getElementById('new').appendChild(para);
				document.getElementById('new').innerHTML=request.responseText;
			}
		};
		request.send(null);
	} else {
		alert('Sorry,your browser doesn\'t support XMLHttpRequest');
	}
}
//addLoadEvent(getNewContent);