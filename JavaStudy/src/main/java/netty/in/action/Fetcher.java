package netty.in.action;

public interface Fetcher {
	void fetchData(FetcherCallback callback);
}
