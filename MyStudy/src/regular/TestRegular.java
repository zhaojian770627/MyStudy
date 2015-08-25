package regular;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegular {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String source1 = "pk_billtypecode='null' AND enablestate!='3' AND (pk_trantypecode = '1001D410000000002G0L' AND creater = '1001D410000000004IT8')";
		String source2 = "pk_billtypecode='null' AND enablestate!='3' AND (pk_trantypecode in ('1001D410000000002G0L', '0001D410000000007YJB','0001D4100000000020FH') AND creater = '1001D410000000004IT8')";
		String patternStrin = "pk_trantypecode(\\s)+in(\\s)*[\\(0-9A-Z'\\,\\s]+\\)";
		String patternStreq = "pk_trantypecode(\\s)+=\\s'[0-9A-Z]+'";
		String patternStr2 = "'.*'";
		// String patternStr="pk_billtype\\sin\\s\\)$";

		Pattern pattern1 = Pattern.compile(patternStrin);
		Matcher matcher1 = pattern1.matcher(source2);
		if (matcher1.find()) {
			String r = matcher1.group(0);
			processIn(r);
			Pattern pattern2 = Pattern.compile(patternStr2);
			// Matcher matcher
			Matcher matcher2 = pattern2.matcher(r);
			if (matcher2.find()) {
				String s = matcher2.group(0);

			}
		}

		Pattern pattern2 = Pattern.compile(patternStreq);
		Matcher matcher2 = pattern2.matcher(source2);
		if (matcher2.find()) {
			String r = matcher2.group(0);
			processEq(r);
		}
	}

	private static void processEq(String r) {
		String patternPK = "'[0-9A-Z]+'";
		Pattern pattern = Pattern.compile(patternPK);
		Matcher matcher = pattern.matcher(r);
		if (matcher.find()) {
			String opk = matcher.group(0);
			String pk = opk.substring(1, 21);
			System.out.println(opk);
			System.out.println(pk);
		}
	}

	private static void processIn(String r) {
		System.out.println(r);
		String patternPK = "'[0-9A-Z]+'";
		Pattern pattern = Pattern.compile(patternPK);
		Matcher matcher = pattern.matcher(r);
		Map<String, String> rMap = new HashMap<String, String>();
		while (matcher.find()) {
			String opk = matcher.group(0);
			String pk = opk.substring(1, 21);
			System.out.println(opk);
			System.out.println(pk);
			rMap.put(pk, "");
		}

		
	}
}
