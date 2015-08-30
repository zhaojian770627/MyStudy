function displayAbbreviations() {
	var abbreviations=document.getElementsByTagName("abbr");
	if(abbreviations.length<1)
		return false;
	var defs=new Array();
	for(var i=0;i<abbreviations.length;i++){
		var current_abbr=abbreviations[i];
		if(current_abbr.childNodes.length<1) continue;
		var definition=current_abbr.getAttribute("title");
		var key=current_abbr.lastChild.nodeValue;
		defs[key]=definition;		
	}
	var dlist=document.createElement("dl");
	for(key in defs){
		var definition=defs[key];
		var dtitle=document.createElement("dt");
		var dtitle_text=document.createTextNode(key);
		dtitle.appendChild(dtitle_text);
		var ddesc=document.createElement("dd");
		var ddesc_text=document.createTextNode(definition);
		ddesc.appendChild(ddesc_text);
		dlist.appendChild(dtitle);
		dlist.appendChild(ddesc);
	}
	if(dlist.childNodes.length<1) return false;
	var header=document.createElement("h2");
	var header_text=document.createTextNode("Abbreviations");
	header.appendChild(header_text);
	document.body.appendChild(header);
	document.body.appendChild(dlist);
}
function displayCitations(){
	var quotes=document.getElementsByTagName("blockquote");
	for(var i=0;i<quotes.length;i++){
		if(!quotes[i].getAttribute("cite")) continue;
		var url=quotes[i].getAttribute("cite");
		var quoteChildren=quotes[i].getElementsByTagName('*');
		if(quoteChildren.length<1) continue;
		var elem=quoteChildren[quoteChildren.length-1];
		var link=document.createElement("a");
		var link_text=document.createTextNode("source");
		link.appendChild(link_text);
		link.setAttribute("href", url);
		var superscript=document.createElement("sup");
		superscript.appendChild(link);
		elem.appendChild(superscript);
	}
}
addLoadEvent(displayAbbreviations);
addLoadEvent(displayCitations());