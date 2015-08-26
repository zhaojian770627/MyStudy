package CodeDesigner.ui.model;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import CodeDesigner.ui.CodeElement;
import CodeDesigner.ui.IElementPaint;
import CodeDesigner.ui.LableElement;

/**
 * 数据模型
 * @author zhaojianc
 *
 */
public class DataModel {
	List<IElementPaint> elements;
	public List<IElementPaint> getElements() {
		return elements;
	}
	
	public DataModel()
	{
		elements=new ArrayList<IElementPaint>();
		
		LableElement lb1=new LableElement();
		lb1.setSize(new Dimension(50, 20));
		Data ld1=new Data();
		ld1.setDataType(DataType.String);
		ld1.setValue("abcd");
		lb1.setLocation(new Point(10,10));
		Format ft=new Format();
		Font font = new Font("Serif", Font.PLAIN, 12);
		ft.setFont(font);
		lb1.setFormat(ft);
		elements.add(lb1);
		
		LableElement lb2=new LableElement();
		lb2.setSize(new Dimension(50, 20));
		Data ld2=new Data();
		ld2.setDataType(DataType.String);
		ld2.setValue("abcd");
		lb2.setLocation(new Point(80,10));
		ft.setFont(font);
		lb2.setFormat(ft);
		elements.add(lb2);
		
		CodeElement ce=new CodeElement();
		ce.setSize(new Dimension(100,20));		
		ce.setLocation(new Point(80,50));
		elements.add(ce);
	}
	
	
}
