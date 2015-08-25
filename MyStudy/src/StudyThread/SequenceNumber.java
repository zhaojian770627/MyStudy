package StudyThread;

public class SequenceNumber {
	private static ThreadLocal<Integer> seqNum=new ThreadLocal<Integer>(){
		public Integer initialValue(){
			return 0;
		}
	};
	
	public int getNextNum(){
		seqNum.set(seqNum.get()+1);
		return seqNum.get();
	}
}
