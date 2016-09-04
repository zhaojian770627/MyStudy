package complier.book.construction.Fig0804;

import java.util.Stack;

import complier.book.construction.Fig0612.ArgsTokenMgr;

/**
 * ��������ķ������϶��±�����ջʽ�﷨������ 
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
		// ���쵥�ʷ��Ź�����
		ArgsTokenMgr tm = new ArgsTokenMgr(args);
		Fig0804Parser parser = new Fig0804Parser(tm);
		parser.parse();
	}
}

interface DataPart {
	// ʵ������ӿڵ������ۿɷ�����Щ����
	String tokens = "bcdef#"; // ���ʷ����б�
	String nonTerms = "SBCD"; // ���ս���б�

	// ����ʽ�Ҷ�������
	String[] pTab = { "CBf", "bb", "DC", "Cc", "", "Dd", "e" };
	/*
	 * -1 ��ζ���� �Ǹ����ǲ���ʽ���
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
		this.tm = tm; // ����tm
		advance(); // ���currentToken
		stk = new Stack<Character>(); // ����ջ
		stk.push('S'); // �ÿ�ʼ���ų�ʼ��ջ
	}

	private void advance() {
		currentToken = tm.getNextToken();
	}

	public void parse() {
		int nonTermIndex, tokenIndex, pNumber;
		while (true) {
			// ת����ǰ���ʷ��ŵ��±�
			tokenIndex = tokens.indexOf(currentToken);

			// ��ⵥ�ʷ����Ƿ��д���stk�Ƿ�Ϊ��
			if (tokenIndex == -1 || stk.empty())
				break;

			// ת��ջ�����ŵ��±�
			// ���ջ�����ս������õ�-1
			nonTermIndex = nonTerms.indexOf(stk.peek());
			if (nonTermIndex >= 0) // stkջ���Ƿ�Ϊ���ս����
			{
				// �õ�����ʽ���
				pNumber = parseTable[nonTermIndex][tokenIndex];

				if (pNumber < 0) // -1 ��ζ�žܾ�
					break;

				// ʹ�ñ����pNumber�Ĳ���ʽ
				stk.pop();
				for (int i = 0; i < pTab[pNumber].length(); i++)
					stk.push(pTab[pNumber].charAt(i));
			} else
				// ջ�������Ƿ�ƥ�䵱ǰ���ʷ���
				if (currentToken == stk.peek()) {
					stk.pop(); // ����ջ������
					advance(); // ����ƥ�䵱ǰ���ʷ���
				} else
					break;
		}
		
		// �����Ƿ�Ϊ����״̬
		if(currentToken=='#' && stk.empty())
			System.out.println("accept");
		else
			System.out.println("reject");
	}
}