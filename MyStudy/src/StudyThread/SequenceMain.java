package StudyThread;

public class SequenceMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SequenceNumber sn=new SequenceNumber();
		SequenceThread s1=new SequenceThread(sn);
		SequenceThread s2=new SequenceThread(sn);
		SequenceThread s3=new SequenceThread(sn);
		
		s1.start();
		s2.start();
		s3.start();
	}

}
