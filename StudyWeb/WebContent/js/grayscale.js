function convertToGS(img){
	// ����������֧�� <canvas> �ͷ���
	if(!Modernizr.canvas)
	{
		alert('no html5');
		return;
	}
	// �洢ԭʼ�Ĳ�ɫ��
	img.color=img.src;
	
	// �����ҶȰ�
	img.grayscale=createGSCanvas(img);
	
	// ��mouseover/out�¼�����ʱ�л�ͼƬ
	img.onmouseover=function(){
		this.src=this.color;
	}
	
	img.onmouseout=function(){
		this.src=this.grayscale;
	}
	
	img.onmouseout();
}

function createGSCanvas(img){
	var canvas=document.createElement("canvas");
	canvas.width=img.width;
	canvas.height=img.height;
	
	var ctx=canvas.getContext("2d");
	ctx.drawImage(img,0,0);
	
	// ע�⣺getImageDataֻ�ܲ�����ű�λ��ͬһ�����е�ͼƬ
	var c=ctx.getImageData(0,0,img.width,img.height);
	for(i=0;i<c.height;i++){
		for(j=0;j<c.width;j++){
			var x=(i*4)*c.width+(j*4);
			var r=c.data[x];
			var g=c.data[x+1];
			var b=c.data[x+2];
			c.data[x]=c.data[x+1]=c.data[x+2]=(r+g+b)/3;
		}
	}
	ctx.putImageData(c,0,0,0,0,c.width,c.height);
	return canvas.toDataURL();
}
window.onload=function(){
	convertToGS(document.getElementById('avatar'));
}