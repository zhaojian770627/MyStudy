package threadPool;

import java.util.Set;

/**
 * �����ڡ������ӡ�����ĳ���
 * 
 * @author zhaojianc
 * 
 * @param <P>
 * @param <M>
 */
public interface Puzzle<P, M> {
	/*
	 * ��ʼ����һ��λ��
	 */
	P initialPosition();

	/*
	 * �жϸ�����λ���Ƿ�ΪĿ��λ��
	 */
	boolean isGoal(P position);

	/*
	 * ����λ�÷�����Ч���ƶ�����
	 */
	Set<M> legalMoves(P position);

	/*
	 * ���ݸ���λ��Posִ���ƶ�Move����������λ��
	 */
	P move(P position, M move);
}
