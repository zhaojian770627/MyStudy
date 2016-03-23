package openframework.memcached;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class TestMemcached {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MemCachedClient client = new MemCachedClient();
		String[] addr = { "127.0.0.1:11211", "20.10.130.26:11211" };
		// String[] addr = { "20.10.130.26:11211" };
		// Integer[] weights = { 1, 3 };
		SockIOPool pool = SockIOPool.getInstance();
		pool.setServers(addr);
		// pool.setWeights(weights);
		pool.setHashingAlg(SockIOPool.CONSISTENT_HASH);
		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(200);
		pool.setMaxIdle(1000 * 30 * 30);
		pool.setMaintSleep(30);
		pool.setNagle(false);
		pool.setSocketTO(30);
		pool.setSocketConnectTO(0);
		pool.initialize();

		// client.setCompressEnable(true);
		// client.setCompressThreshold(1000*1024);

		for (int i = 0; i < 100000; i++) {
			System.out.println(i);
			long a = System.currentTimeMillis();
			client.add("name" + i, "zhaojian" + i);
			// Object o = client.get("zj" + 1);
			long b = System.currentTimeMillis();
			// System.out.println(i + " Timer:" + (b - a));
			// System.out.println(o.toString() + " Timer:" + (b - a));
			// System.out.println("End:" + b);
			// System.out.println("Use:" + (b - a));
		}
		// System.out.println(client.get("name" + 1));

		System.out.println("OK");
		// client.get("a");
	}

}
