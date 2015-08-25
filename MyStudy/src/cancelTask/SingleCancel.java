package cancelTask;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class SingleCancel {
	boolean checkMail(Set<String> hosts,long timeout,TimeUnit unit) throws InterruptedException
	{
		ExecutorService exec=Executors.newCachedThreadPool();
		final AtomicBoolean hasNewMail=new AtomicBoolean(false);
		try{
			for(final String host:hosts)
				exec.execute(new Runnable() {
					
					@Override
					public void run() {
						if(checkMail(host))
							hasNewMail.set(true);
					}

					private boolean checkMail(String host) {
						// TODO Auto-generated method stub
						return false;
					}
				});
		}finally{
			exec.shutdown();
			exec.awaitTermination(timeout, unit);
		}
		return hasNewMail.get();
	}
}
