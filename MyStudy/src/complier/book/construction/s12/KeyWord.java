package complier.book.construction.s12;

import java.util.HashMap;
import java.util.Map;

public class KeyWord implements S1Constants {
	String key;
	Integer value;
	static Map<String, Integer> keyWordMap = new HashMap<String, Integer>();

	static
	{
		keyWordMap.put("print", PRINT);
		keyWordMap.put("println", PRINTLN);
		keyWordMap.put("readint", READINT);
		keyWordMap.put("while", WHILE);
		keyWordMap.put("if", IF);
		keyWordMap.put("else", ELSE);
	}

	public KeyWord(String key, Integer value) {
		this.key = key;
		this.value = value;
	}

	static KeyWord getKeyWord(String key) {
		if (keyWordMap.containsKey(key))
			return new KeyWord(key, keyWordMap.get(key));
		return null;
	}
}
