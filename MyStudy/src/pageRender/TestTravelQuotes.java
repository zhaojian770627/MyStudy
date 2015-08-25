package pageRender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TestTravelQuotes {
	private final ExecutorService executor;
	
	TestTravelQuotes(ExecutorService executor){
		this.executor=executor;
	}
	/*
	 * 在预订时间内请求旅游报价，演示了ExecutorService中invokeAll的使用
	 */
	public List<TravelQuote> getRankedTravelQuotes(TravelInfo travelInfo,
			Set<TravelCompany> companies, Comparator<TravelQuote> ranking,
			long time, TimeUnit unit) throws InterruptedException {
		List<QuoteTask> tasks=new ArrayList<QuoteTask>();
		for(TravelCompany company:companies)
			tasks.add(new QuoteTask(company, travelInfo));
		
		List<Future<TravelQuote>> futures=executor.invokeAll(tasks,time,unit);
		List<TravelQuote> quotes=new ArrayList<TravelQuote>(tasks.size());
		Iterator<QuoteTask> taskIter=tasks.iterator();
		for(Future<TravelQuote> f:futures){
			QuoteTask task=taskIter.next();
			try{
				quotes.add(f.get());
			}catch(ExecutionException e){
				quotes.add(task.getFailureQuote(e.getCause()));
			}catch(CancellationException e){
				quotes.add(task.getTimeoutQuote(e));
			}
		}
		Collections.sort(quotes,ranking);
		return quotes;
	}
}