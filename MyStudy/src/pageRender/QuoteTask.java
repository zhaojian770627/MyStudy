package pageRender;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;

public class QuoteTask implements Callable<TravelQuote> {
	private final TravelCompany company;
	private final TravelInfo travelInfo;
	
	public QuoteTask(TravelCompany company,TravelInfo travelInfo)
	{
		this.company=company;
		this.travelInfo=travelInfo;
	}
	
	@Override
	public TravelQuote call() throws Exception {
		return company.solicitQuote(travelInfo);
	}

	public TravelQuote getFailureQuote(Throwable cause) {
		// TODO Auto-generated method stub
		return null;
	}

	public TravelQuote getTimeoutQuote(CancellationException e) {
		// TODO Auto-generated method stub
		return null;
	}

}
