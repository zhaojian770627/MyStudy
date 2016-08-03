package complier.book.construction.Fig0612;

public class ArgsTokenMgr {
	private int index;
	String input;

	public ArgsTokenMgr(String[] args) {
		if (args.length > 0)
			input = args[0];
		else
			input = "";
		index = 0;
		System.out.println("input=" + input);
	}

	public char getNextToken()
	{
		if(index<input.length())
			return input.charAt(index++);	// 返回下一个字符
		else
			return '#';
	}
}
