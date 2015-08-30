/**
 * 查询窗口滚动条的位置
 * 以一个对象的x和y属性的方式返回滚动条的偏移量
 * @param w
 * @returns
 */
function getScrollOffsets(w){
	w=w||window;
	if(w.pageXOffset!=null)
		return {x:w.pageXOffset,y:w.pageYOffset};
	
	var d=w.document;
	if(document.compatMode=="CSS1Compat")
		return {x:d.documentElement.scrollLeft,y:d.documentElement.scrollTop};
	
	return {x:d.body.scrollLeft,y:d.body.scrollTop};
}

/**
 * 查询窗口的视口尺寸
 * @param w
 * @returns
 */
function getViewportSize(w){
	w=w||window;
	
	if(w.innderWidth!=null)
		return {w:w.innerWidth,h:w.innerHeight};
		
	var d=w.document;
	if(document.compatMode=="CSS1Compat")
		return {w:d.documentElement.clientWidth,h:d.documentElement.clientHeight};
		
	return {w:d.body.clientWidth,h:d.body.clientWidth};
}

/**
 * 滚动浏览器到文档最下面的页面可见
 */
function toDocBottom(){
	var documentHeight=document.documentElement.offsetHeight;
	var viewportHeight=window.innerHeight;
	window.scrollTo(0,documentHeight-viewportHeight);
}