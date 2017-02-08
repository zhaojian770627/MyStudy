package complier.book.construction.s19.R1d;

import java.util.HashMap;
import java.util.Map;

public class KeyWord implements R1dConstants {
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
		keyWordMap.put("do", DO);
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
