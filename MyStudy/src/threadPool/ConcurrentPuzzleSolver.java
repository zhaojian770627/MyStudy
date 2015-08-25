package threadPool;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;

import threadPool.SequentialPuzzleSolver.Node;

/**
 * 并发版的谜题解决者
 * 
 * @author zhaojianc
 * 
 * @param <P>
 * @param <M>
 */
public class ConcurrentPuzzleSolver<P, M> {
	private final Puzzle<P, M> puzzle;
	private final ExecutorService exec;
	private final ConcurrentMap<P, Boolean> seen;
	final ValueLatch<Node<P, M>> solution = new ValueLatch<Node<P, M>>();

	ConcurrentPuzzleSolver(Puzzle<P, M> puzzle, ExecutorService exec,
			ConcurrentMap<P, Boolean> seen) {
		this.puzzle = puzzle;
		this.exec = exec;
		this.seen = seen;
	}

	public List<M> solve() throws InterruptedException {
		try {
			P p = puzzle.initialPosition();
			exec.execute(newTask(p, null, null));
			// 阻塞，直到发现一个方案
			Node<P, M> solnNode = solution.getValue();
			return (solnNode == null) ? null : solnNode.asMoveList();
		} finally {
			exec.shutdown();
		}
	}

	protected Runnable newTask(P p, M m, Node<P, M> n) {
		return new SolverTask(p, m, n);
	}

	class SolverTask extends Node<P, M> implements Runnable {

		SolverTask(P pos, M move, Node<P, M> prev) {
			super(pos, move, prev);
		}

		@Override
		public void run() {
			if (solution.isSet() || seen.putIfAbsent(pos, true) != null)
				return; // 已找到一个解决方案，或者该位置曾经到达过
			if (puzzle.isGoal(pos))
				solution.setValue(this);
			else
				for (M m : puzzle.legalMoves(pos))
					exec.execute(newTask(puzzle.move(pos, m), m, this));
		}
	}
}
