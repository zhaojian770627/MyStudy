package complier.book.construction.Fig0612;

import java.util.Stack;

public class Fig0612Parser {
	private ArgsTokenMgr tm;	// 单词符号管理器
	private Stack<Character> stk;	// 词法分析器的栈
	private char currentToken;
	
	public Fig0612Parser(ArgsTokenMgr tm) {
		this.tm=tm;
		advance();
		stk=new Stack<>();
		stk.push('$');
		stk.push('S');
	}

	private void advance() {
		// 得到下一个单词符号,并保存于currentToken
		currentToken=tm.getNextToken();
	}

	public void parse() {
		boolean done=false;
		while(!done)
		{
			switch (stk.peek()) {
			case 'S':
				if(currentToken=='b')
				{
					stk.pop();	// 使用产生式1
					stk.push('A');
					stk.push('c');
					stk.push('S');
					advance();
				}
				else if(currentToken=='c')
				{
					stk.pop();	// 使用产生式2
					stk.push('d');
					stk.push('b');
					advance();
				}
				else
					done=true;	//	因到达拒绝状态而退出
				break;

			case 'A':
				if(currentToken=='b')
				{
					stk.push('c');	// 使用产生式3
				}
				else if(currentToken=='d')
				{
					stk.pop();	// 使用产生式4
					advance();
				}else
					done=true;	// 因到达拒绝状态而退出
				break;
			case 'b':
			case 'c':
			case 'd':
				if(stk.peek().charValue()==currentToken)
				{
					stk.pop();	// 放弃栈上的终结符
					advance();	// 放弃匹配输入
				}
				else
					done=true;	// 因到达拒绝状态而退出
				break;
			case '$':
				done=true;
				break;
			}		// switch 结束
		}			// while结束
		
		// 测试是否接受状态
		if(currentToken=='#' && stk.peek()=='$')
			System.out.println("accept");
		else
			System.out.println("reject");
	}

}
