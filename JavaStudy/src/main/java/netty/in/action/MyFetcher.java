package netty.in.action;

public class MyFetcher implements Fetcher {

	final private Data data;

	public MyFetcher(Data data) {
		this.data = data;
	}

	public void fetchData(FetcherCallback callback) {
		try {
			callback.onData(data);
		} catch (Exception e) {
			callback.onError(e);
		}
	}

}
