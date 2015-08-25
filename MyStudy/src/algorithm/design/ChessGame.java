package algorithm.design;

/**
 * һ����
 * 
 * @author zhaojian
 * 
 */
public class ChessGame {

	enum MaxOrMin {
		Max, Min
	}

	class MoveInfo {
		public int movex;
		public int movey;
		public int value;
		// -ƽ��
		public String result;
	}

	int LOSS = Integer.MIN_VALUE;
	int WIN = Integer.MAX_VALUE;

	String[][] board = new String[3][3];

	void init() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				// ~ ��ʾ������
				board[i][j] = "~";
	}

	String[][] boardClone(String[][] board) {
		String[][] newBoard = new String[board.length][board[0].length];
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[i].length; j++)
				newBoard[i][j] = board[i][j];
		return newBoard;
	}

	/**
	 * ��������
	 * 
	 * @param board
	 * @param mm
	 * @param curLevel
	 * @param maxLevel
	 * @param players
	 * @param player
	 * @return
	 */
	MoveInfo findGoodMove(String[][] board, MaxOrMin mm, int curLevel,
			int maxLevel, String[] players, int player) {
		int value, responseValue = 0;
		int bestMoveX = -1, bestMoveY = -1;
		MoveInfo quickWinInfo = new MoveInfo();
		if (fullBoard(board)) {
			quickWinInfo.result = "-";
			return quickWinInfo;
		}
		// else if (immediaChessWin(board, players[player])) {
		// quickWinInfo.result = "+";
		// return quickWinInfo;
		// }

		if (mm == MaxOrMin.Max)
			value = LOSS;
		else
			value = WIN;
		
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				// û������
				if (board[i][j] == "~") {
					// ��������
					board[i][j] = players[player];

					// ������������Ӯ
					if (immediaChessWin(board, players[player])) {
						responseValue = WIN;
						bestMoveX = i;
						bestMoveY = j;

						MoveInfo mi = new MoveInfo();
						mi.movex = bestMoveX;
						mi.movey = bestMoveY;
						mi.value = responseValue;
						mi.result = "WIN";
						return mi;
					}

					// �ն˽ڵ㣬����÷ֲ�����
					if (curLevel == maxLevel) {
						responseValue = getBoardValue(board, players[player],
								players[player]);
						// Max�ڵ�
						if (mm == MaxOrMin.Max) {
							if (responseValue > value) {
								value = responseValue;
								bestMoveX = i;
								bestMoveY = j;
							}
						}
						// Min�ڵ�
						else {
							if (responseValue < value) {
								value = responseValue;
								bestMoveX = i;
								bestMoveY = j;
							}
						}

					} else
					// ���ն˽ڵ�
					{
						MaxOrMin mm2;
						if (mm == MaxOrMin.Max)
							mm2 = MaxOrMin.Min;
						else
							mm2 = MaxOrMin.Max;

						responseValue = findGoodMove(board, mm2, curLevel + 1,
								maxLevel, players, player).value;

						// Max�ڵ�
						if (mm == MaxOrMin.Max) {
							if (responseValue > value) {
								value = responseValue;
								bestMoveX = i;
								bestMoveY = j;
							}
						}
						// Min�ڵ�
						else {
							if (responseValue < value) {
								value = responseValue;
								bestMoveX = i;
								bestMoveY = j;
							}
						}

					}
					board[i][j] = "~";
				}
			}
		MoveInfo mi = new MoveInfo();
		mi.movex = bestMoveX;
		mi.movey = bestMoveY;
		mi.value = responseValue;
		return mi;
	}

	String translatePlayer(String s, String player) {
		return s.replaceAll("~", player);
	}

	// ���ۺ���
	int getBoardValue(String[][] board, String player1, String player2) {
		// ����player1�ķ���
		int value1 = 0, value2 = 0;
		String winflag1 = player1 + player1 + player1;
		String winflag2 = player2 + player2 + player2;
		// ��һ��
		String line1player1 = translatePlayer(board[0][0] + board[0][1]
				+ board[0][2], player1);
		if (line1player1.equals(winflag1))
			value1++;

		String line1player2 = translatePlayer(board[0][0] + board[0][1]
				+ board[0][2], player2);
		if (line1player2.equals(winflag2))
			value2++;
		// �ڶ���
		String line2player1 = translatePlayer(board[1][0] + board[1][1]
				+ board[1][2], player1);
		if (line2player1.equals(winflag1))
			value1++;

		String line2player2 = translatePlayer(board[1][0] + board[1][1]
				+ board[1][2], player2);
		if (line2player2.equals(winflag2))
			value2++;

		// ������
		String line3player1 = translatePlayer(board[2][0] + board[2][1]
				+ board[2][2], player1);
		if (line3player1.equals(winflag1))
			value1++;

		String line3player2 = translatePlayer(board[2][0] + board[2][1]
				+ board[2][2], player2);
		if (line3player2.equals(winflag2))
			value2++;

		// ��һ��
		String col1player1 = translatePlayer(board[0][0] + board[1][0]
				+ board[2][0], player1);
		if (col1player1.equals(winflag1))
			value1++;

		String col1player2 = translatePlayer(board[0][0] + board[1][0]
				+ board[2][0], player2);
		if (col1player2.equals(winflag2))
			value2++;

		// �ڶ���
		String col2player1 = translatePlayer(board[0][1] + board[1][1]
				+ board[2][1], player1);
		if (col2player1.equals(winflag1))
			value1++;

		String col2player2 = translatePlayer(board[0][1] + board[1][1]
				+ board[2][1], player2);
		if (col2player2.equals(winflag2))
			value2++;

		// ������
		String col3player1 = translatePlayer(board[0][2] + board[1][2]
				+ board[2][2], player1);
		if (col3player1.equals(winflag1))
			value1++;

		String col3player2 = translatePlayer(board[0][2] + board[1][2]
				+ board[2][2], player2);
		if (col3player2.equals(winflag2))
			value2++;

		// ���Խ���
		String zdjplayer1 = translatePlayer(board[0][0] + board[1][1]
				+ board[2][2], player1);
		if (zdjplayer1.equals(winflag1))
			value1++;

		String zdjplayer2 = translatePlayer(board[0][0] + board[1][1]
				+ board[2][2], player2);
		if (zdjplayer2.equals(winflag2))
			value2++;

		// ���Խ���
		String fdjplayer1 = translatePlayer(board[0][2] + board[1][1]
				+ board[2][0], player1);
		if (fdjplayer1.equals(winflag1))
			value1++;

		String fdjplayer2 = translatePlayer(board[0][2] + board[1][1]
				+ board[2][0], player2);
		if (fdjplayer2.equals(winflag2))
			value2++;

		return value1 - value2;

	}

	/**
	 * ���ñ������ж���Ӯ
	 * 
	 * @param board
	 * @param player
	 * @return
	 */
	private boolean immediaChessWin(String[][] board, String player) {
		String winflag = player + player + player;
		// ---
		// ��һ��
		String line1 = board[0][0] + board[0][1] + board[0][2];
		if (line1.equals(winflag))
			return true;
		// �ڶ���
		String line2 = board[1][0] + board[1][1] + board[1][2];
		if (line2.equals(winflag))
			return true;
		// ������
		String line3 = board[2][0] + board[2][1] + board[2][2];
		if (line3.equals(winflag))
			return true;
		// ��һ��
		String col1 = board[0][0] + board[1][0] + board[2][0];
		if (col1.equals(winflag))
			return true;
		// �ڶ���
		String col2 = board[0][1] + board[1][1] + board[2][1];
		if (col2.equals(winflag))
			return true;
		// ������
		String col3 = board[0][2] + board[1][2] + board[2][2];
		if (col3.equals(winflag))
			return true;
		// ���Խ���
		String zdj = board[0][0] + board[1][1] + board[2][2];
		if (zdj.equals(winflag))
			return true;
		// ���Խ���
		String fdj = board[0][2] + board[1][1] + board[2][0];
		if (fdj.equals(winflag))
			return true;
		return false;
	}

	private boolean fullBoard(String[][] board) {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == "~")
					return false;
			}
		return true;
	}

	private void StartGame() {
		// ��λ������
		String[] players = new String[] { "X", "O" };
		init();
		// ����player1���֣���������
		int curPlayer = 0;
		MoveInfo mi = findGoodMove(board, MaxOrMin.Max, 0, 1, players,
				curPlayer);
		board[mi.movex][mi.movey] = players[curPlayer];
		while (!("-".endsWith(mi.result) || "WIN".equals(mi.result))) {
			System.out.println("Current Player:" + players[curPlayer]);
			printCurBoard(board);
			// ����������
			curPlayer = curPlayer == 0 ? 1 : 0;
			mi = findGoodMove(board, MaxOrMin.Max, 0, 1, players, curPlayer);
		}
		board[mi.movex][mi.movey] = players[curPlayer];
		if (mi.result.equals("-"))
			System.out.println("ƽ��");
		else
			System.out.println(players[curPlayer] + "Ӯ��");
		System.out.println("------------------");
		printCurBoard(board);
	}

	/**
	 * ��ӡ��ǰ���
	 * 
	 * @param board
	 */
	void printCurBoard(String[][] board) {
		System.out.println("-------");
		System.out.println("|" + board[0][0] + "|" + board[0][1] + "|"
				+ board[0][2] + "|");
		System.out.println("-------");
		System.out.println("|" + board[1][0] + "|" + board[1][1] + "|"
				+ board[1][2] + "|");
		System.out.println("-------");
		System.out.println("|" + board[2][0] + "|" + board[2][1] + "|"
				+ board[2][2] + "|");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ChessGame game = new ChessGame();
		game.StartGame();
	}

}