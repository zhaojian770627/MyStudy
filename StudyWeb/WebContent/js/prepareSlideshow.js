function prepareSlideshow(){
	if(!document.getElementsByName) return false;
	if(!document.getElementById) return false;
	
	if(!document.getElementById("linklist")) return false;
	if(!document.getElementById("preview")) return false;
	
	var preview=document.getElementById("preview");
	preview.style.position="absolute";
	
	var list=document.getElementById("linklist");
	var links=list.getElementsByTagName("a");
	links[0].onmouseover=function(){
		moveElement("preview",-103,0,10);
	}
	
	links[1].onmouseover=function(){
		moveElement("preview",-206,0,10);
	}
	
	links[2].onmouseover=function(){
		moveElement("preview",-309,0,10);
	}
}
addLoadEvent(prepareSlideshow);