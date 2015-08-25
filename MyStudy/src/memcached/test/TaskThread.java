package memcached.test;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class TaskThread implements Runnable {
	MemCachedClient client;

	public TaskThread() {
		client = new MemCachedClient();
		String[] addr = { "127.0.0.1:11211", "20.10.130.26:11211" };
		//String[] addr = { "20.10.130.26:11211" };
		Integer[] weights = { 1 };
		SockIOPool pool = SockIOPool.getInstance();
		pool.setServers(addr);
		pool.setWeights(weights);
		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(200);
		pool.setMaxIdle(1000 * 30 * 30);
		pool.setMaintSleep(30);
		pool.setNagle(false);
		pool.setSocketTO(30);
		pool.setSocketConnectTO(0);
		pool.initialize();
		// pool.setHashingAlg(SockIOPool.CONSISTENT_HASH);
	}

	@Override
	public void run() {
		Object o = client.get("name1");
	}
}
