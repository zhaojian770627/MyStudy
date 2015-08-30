/**
 * ��ѯ���ڹ�������λ��
 * ��һ�������x��y���Եķ�ʽ���ع�������ƫ����
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
 * ��ѯ���ڵ��ӿڳߴ�
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
 * ������������ĵ��������ҳ��ɼ�
 */
function toDocBottom(){
	var documentHeight=document.documentElement.offsetHeight;
	var viewportHeight=window.innerHeight;
	window.scrollTo(0,documentHeight-viewportHeight);
}