package StudyThread;

public class SequenceThread extends Thread {	
	private SequenceNumber sn;
	public SequenceThread(SequenceNumber sn){
		this.sn=sn;
	}
	
	@Override
	public void run() {
		for(int i=0;i<3;i++)
			System.out.println("Thread["+Thread.currentThread().getName()+"]sn["+sn.getNextNum()+"]");
	}
}
