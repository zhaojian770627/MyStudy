package CodeDesigner.ui.sheet;

/**
 * ���Ե��ӿ�<br>
 * @author �׾� 2004-5-6
 */
public interface PropertySheet {

  //ƽ��
  public final static int VIEW_AS_FLAT_LIST = 0;

  //����
  public final static int VIEW_AS_CATEGORIES = 1;

  public void setProperties(Property[] properties);

  public Property[] getProperties();

}
