package complier.book.construction.s21.CI4;

import java.util.ArrayList;
import java.util.Stack;

public class CI4CodeGen implements CI4Constants {
	int lindex = 0;

	private ArrayList<Integer> scode; // 存放s-code
	private Stack<Integer> s; // s-machine 使用的栈
	int[] vtab; // 变量的表
	private int pc; // 程序ctr(下一个指令的索引)
	private int opcode; // 当前指令的opcode;

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
