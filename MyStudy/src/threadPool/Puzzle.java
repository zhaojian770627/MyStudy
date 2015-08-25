package threadPool;

import java.util.Set;

/**
 * 类似于“搬箱子”谜题的抽象
 * 
 * @author zhaojianc
 * 
 * @param <P>
 * @param <M>
 */
public interface Puzzle<P, M> {
	/*
	 * 初始化第一个位置
	 */
	P initialPosition();

	/*
	 * 判断给定的位置是否为目标位置
	 */
	boolean isGoal(P position);

	/*
	 * 根据位置返回有效的移动集合
	 */
	Set<M> legalMoves(P position);

	/*
	 * 根据给定位置Pos执行移动Move，并返回新位置
	 */
	P move(P position, M move);
}
