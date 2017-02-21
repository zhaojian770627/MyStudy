package complier.book.construction.s21.CI4;

import java.util.ArrayList;
import java.util.Stack;

public class CI4CodeGen implements CI4Constants {
	int lindex = 0;

	private ArrayList<Integer> scode; // ���s-code
	private Stack<Integer> s; // s-machine ʹ�õ�ջ
	int[] vtab; // �����ı�
	private int pc; // ����ctr(��һ��ָ�������)
	private int opcode; // ��ǰָ���opcode;

	public CI4CodeGen() {
		scode = new ArrayList<Integer>();
		s = new Stack<Integer>();
		pc = 0;
	}

	public void makevtab(int size) {
		vtab = new int[size];
	}

	public void emit(int inst) {
		scode.add(inst); // emit instruction to scode
	}

	public void interpret() // interprets s-code in scode
	{
		boolean doAgain = true;
		int right;

		do {
			// fetch the opcode of the next instruction
			opcode = scode.get(pc++);

			// decode opcode and execute instruction
			switch (opcode) {
			case PRINTLN:
				System.out.println(s.pop());
				break;
			case ASSIGN:
				vtab[scode.get(pc++)] = s.pop();
				break;
			case PLUS:
				right = s.pop();
				s.push(s.pop() + right);
				break;
			case TIMES:
				right = s.pop();
				s.push(s.pop() * right);
				break;
			case PUSHCONSTANT:
				s.push(scode.get(pc++));
				break;
			case PUSH:
				s.push(vtab[scode.get(pc++)]);
				break;
			case HALT:
				doAgain = false;
				break;
			default:
				doAgain = false;
				break;
			}
		} while (doAgain);
	}
}
