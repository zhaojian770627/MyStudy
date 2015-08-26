package CodeDesigner.ui.sheet;

/**
 * 属性单接口<br>
 * @author 雷军 2004-5-6
 */
public interface PropertySheet {

  //平铺
  public final static int VIEW_AS_FLAT_LIST = 0;

  //分组
  public final static int VIEW_AS_CATEGORIES = 1;

  public void setProperties(Property[] properties);

  public Property[] getProperties();

}
