package CodeDesigner.ui.sheet;

/**
 * 用于Combox编辑器的值对象
 * @author 雷军 created on 2004-12-15
 */
public final class ValueTag {

  private Object _value;
  private Object _visualValue;

  public ValueTag(Object value, Object visualValue) {
    this._value = value;
    this._visualValue = visualValue;
  }

  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (_value == o || (_value != null && _value.equals(o)))
      return true;
    return false;
  }

  public int hashCode() {
    return _value == null ? 0 : _value.hashCode();
  }

  //lj+
  public Object getValue() {
    return _value;
  }

  public Object getVisualValue() {
    return _visualValue;
  }
  
  public String toString(){
  	return String.valueOf(_visualValue);
  }
}