package algorithm.design;

/**
 * Ëæ»úÊı
 * @author zhaojian
 *
 */
public class Random {
	private static final int A=48271;
	private static final int M=2147483647;
	private static final int Q=M/A;
	private static final int R=M%A;
	
	public Random()
	{
		state=(int) (System.currentTimeMillis()%Integer.MAX_VALUE);
	}
	
	public int randomInt()
	{
		int tmpState=A*(state%Q)-R*(state/Q);
		if(tmpState>=0)
			state=tmpState;
		else 
			state=tmpState+M;
		
		return state;
	}
	
	public int randomIntWRONG()
	{
		return state=(A*state)%M;
	}
	
	public double random0_1(){
		return (double)randomInt()/M;
	}
	private int state;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Random r=new Random();
		for(int i=0;i<10;i++)
			System.out.println(r.randomInt());
	}
}
