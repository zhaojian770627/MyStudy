package complier.book.construction.Fig0804;

import java.util.Stack;

import complier.book.construction.Fig0612.ArgsTokenMgr;

/**
 * 针对以下文法的自上而下表驱动栈式语法分析器 
 * 				Selection set 
 * 0) S->fBC 	{f} 
 * 1) B->bb 	{b} 
 * 2) B->CD     {c,d,e} 
 * 3) C->cC 	{c}
 * 4) C->lambda {d,e,#} 
 * 5) D->dD 	{d} 
 * 6) D->e 		{e}
 * 
 * @author zhaojianc
 *
 */
public class Fig0804 {

	public static void main(String[] args) {
		// 构造单词符号管理器
		ArgsTokenMgr tm = new ArgsTokenMgr(args);
		Fig0804Parser parser = new Fig0804Parser(tm);
		parser.parse();
	}
}

interface DataPart {
	// 实现这个接口的任意累可访问这些常量
	String tokens = "bcdef#"; // 单词符号列表
	String nonTerms = "SBCD"; // 非终结符列表

	// 产生式右端逆序存放
	String[] pTab = { "CBf", "bb", "DC", "Cc", "", "Dd", "e" };
	/*
	 * -1 意味出错 非负数是产生式编号
	 */
	int[][] parseTable = { 
			{ -1, -1, -1, -1, 0, -1 }, 
			{ 1, 2, 2, 2, -1, -1 }, 
			{ -1, 3, 4, 4, -1, 4 },
			{ -1, -1, 5, 6, -1, -1 } 
			};
}

class Fig0804Parser implements DataPart {
	private ArgsTokenMgr tm;
	private Stack<Character> stk;
	private char currentToken;

	Fig0804Parser(ArgsTokenMgr tm) {
		this.tm = tm; // 保存tm
		advance(); // 填充currentToken
		stk = new Stack<Character>(); // 创建栈
		stk.push('S'); // 用开始符号初始化栈
	}

	private void advance() {
		currentToken = tm.getNextToken();
	}

	public void parse() {
		int nonTermIndex, tokenIndex, pNumber;
		while (true) {
			// 转换当前单词符号到下标
			tokenIndex = tokens.indexOf(currentToken);

			// 检测单词符号是否有错，或stk是否为空
			if (tokenIndex == -1 || stk.empty())
				break;

			// 转换栈顶符号到下标
			// 如果栈顶是终结符，则得到-1
			nonTermIndex = nonTerms.indexOf(stk.peek());
			if (nonTermIndex >= 0) // stk栈顶是否为非终结符项
			{
				// 得到产生式编号
				pNumber = parseTable[nonTermIndex][tokenIndex];

				if (pNumber < 0) // -1 意味着拒绝
					break;

				// 使用编号是pNumber的产生式
				stk.pop();
				for (int i = 0; i < pTab[pNumber].length(); i++)
					stk.push(pTab[pNumber].charAt(i));
			} else
				// 栈顶的项是否匹配当前单词符号
				if (currentToken == stk.peek()) {
					stk.pop(); // 放弃栈顶的项
					advance(); // 放弃匹配当前单词符号
				} else
					break;
		}
		
		// 测试是否为接受状态
		if(currentToken=='#' && stk.empty())
			System.out.println("accept");
		else
			System.out.println("reject");
	}
}