package complier.book.construction.Fig0612;

import java.util.Stack;

public class Fig0612Parser {
	private ArgsTokenMgr tm;	// ���ʷ��Ź�����
	private Stack<Character> stk;	// �ʷ���������ջ
	private char currentToken;
	
	public Fig0612Parser(ArgsTokenMgr tm) {
		this.tm=tm;
		advance();
		stk=new Stack<>();
		stk.push('$');
		stk.push('S');
	}

	private void advance() {
		// �õ���һ�����ʷ���,��������currentToken
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
					stk.pop();	// ʹ�ò���ʽ1
					stk.push('A');
					stk.push('c');
					stk.push('S');
					advance();
				}
				else if(currentToken=='c')
				{
					stk.pop();	// ʹ�ò���ʽ2
					stk.push('d');
					stk.push('b');
					advance();
				}
				else
					done=true;	//	�򵽴�ܾ�״̬���˳�
				break;

			case 'A':
				if(currentToken=='b')
				{
					stk.push('c');	// ʹ�ò���ʽ3
				}
				else if(currentToken=='d')
				{
					stk.pop();	// ʹ�ò���ʽ4
					advance();
				}else
					done=true;	// �򵽴�ܾ�״̬���˳�
				break;
			case 'b':
			case 'c':
			case 'd':
				if(stk.peek().charValue()==currentToken)
				{
					stk.pop();	// ����ջ�ϵ��ս��
					advance();	// ����ƥ������
				}
				else
					done=true;	// �򵽴�ܾ�״̬���˳�
				break;
			case '$':
				done=true;
				break;
			}		// switch ����
		}			// while����
		
		// �����Ƿ����״̬
		if(currentToken=='#' && stk.peek()=='$')
			System.out.println("accept");
		else
			System.out.println("reject");
	}

}
