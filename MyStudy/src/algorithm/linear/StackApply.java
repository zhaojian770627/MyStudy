package algorithm.linear;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Javaջ��Ӧ��
 * 
 * @author zhaojian
 * 
 */
public class StackApply {

	/**
	 * ƽ��ʽ
	 */
	public void balance() {
		Map<String, String> openSys = new HashMap<String, String>();
		Map<String, String> closeSys = new HashMap<String, String>();

		// ���ŷ���
		openSys.put("(", ")");
		openSys.put("[", "]");
		openSys.put("{", "}");

		// ��շ���
		closeSys.put(")", "(");
		closeSys.put("]", "[");
		closeSys.put("}", "{");

		Stack<String> stk = new Stack<String>();
		String inputStr = "()[()]()";
		String[] str = inputStr.split("");
		for (String s : str) {
			if (openSys.containsKey(s)) {
				stk.push(s);
				continue;
			} else if (closeSys.containsKey(s)) {
				if (stk.isEmpty()) {
					System.out.println("ƥ�����");
					break;
				} else {
					String o = stk.pop();
					if (o.equals(closeSys.get(s)))
						continue;
					else {
						System.out.println("ƥ�����");
						break;
					}
				}
			}
			System.out.println(s);
		}
		if (stk.isEmpty())
			System.out.println("OK");
		else
			System.out.println("ƥ�����");
	}

	/**
	 * �����׺���ʽ��ֵ
	 */
	private void compute() {
		String inputStr = "156*+23*4+9+3*+";//"6523+8*+3+*";
		Stack<String> stk = new Stack<String>();
		String[] str = inputStr.split("");
		for (String s : str) {
			if (s.equals(""))
				continue;
			char c = s.charAt(0);
			if (Character.isDigit(c))
				stk.push(s);
			else {
				String sa = stk.pop();
				String sb = stk.pop();
				int a = Integer.valueOf(sa);
				int b = Integer.valueOf(sb);
				int r = 0;
				switch (c) {
				case '+':
					r = a + b;
					break;
				case '-':
					r = a - b;
					break;
				case '*':
					r = a * b;
					break;
				case '/':
					r = a / b;
					break;
				}
				stk.push(String.valueOf(r));
			}
		}
		System.out.print(stk.pop());
	}

	/**
	 * ��׺���ʽ����׺���ʽ��ת��
	 */
	private void translate() {
		String inputStr = "1+5*6+((2*3+4)+9)*3";
		Stack<String> stk = new Stack<String>();
		String[] str = inputStr.split("");
		String out = "";
		// ���ȼ�
		Map<String, Integer> priMap = new HashMap<String, Integer>();
		priMap.put("+", 0);
		priMap.put("-", 0);
		priMap.put("*", 1);
		priMap.put("/", 1);
		priMap.put(")", 2);
		priMap.put("(", 2);

		for (String s : str) {
			if (s.equals(""))
				continue;

			char c = s.charAt(0);
			if (Character.isDigit(c))
				out = out + s;
			else {
				if (stk.isEmpty() || s.equals("(")) {
					stk.push(s);
					continue;
				}

				if (s.equals(")")) {
					String t = stk.pop();
					while (!t.equals("(")) {
						out = out + t;
						t = stk.pop();
					}
				} else {
					String top = stk.peek();

					while (priMap.get(top) >= priMap.get(s)) {
						if (!top.equals("(")) {
							out = out + stk.pop();
							if (!stk.isEmpty())
								top = stk.peek();
							else
								break;
						} else
							break;
					}
					stk.push(s);
				}
			}
		}
		while (!stk.isEmpty())
			out = out + stk.pop();
		System.out.println(out);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StackApply apply = new StackApply();
		// apply.balance();
		// apply.compute();
		apply.translate();
		System.exit(0);
	}

}
