package LRGenerate;

import java.util.ArrayList;
import java.util.List;

public class LRState {
	List<LRProduction> lstrp = new ArrayList<LRProduction>();
	String key;

	public List<LRProduction> getLstrp() {
		return lstrp;
	}

	public void setLstrp(List<LRProduction> lstrp) {
		this.lstrp = lstrp;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
