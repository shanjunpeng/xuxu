package sjp.util.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ThreadPoolService<V> {
	public static final int DEFAULT_POOL_SIZE = 5;
	public static final long DEFAULT_TIMEOUT = 1000;
	private int poolSize = DEFAULT_POOL_SIZE;

	private ExecutorService executorService;

	public ThreadPoolService(int poolSize) {
		setPoolSize(poolSize);
	}

	private void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
		createExecutorService();

	}

	public List<V> invokeAll(List<Callable<V>> tasks) {
		return invokeAll(tasks, DEFAULT_TIMEOUT);
	}

	public List<V> invokeAll(List<Callable<V>> tasks, long timeout) {
		List<V> results = new ArrayList<V>(tasks.size());
		try {
			List<Future<V>> futrues = executorService.invokeAll(tasks, timeout,
					TimeUnit.MILLISECONDS);
			for (Future<V> future : futrues) {
				try {
					results.add(future.get());
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return results;
	}

	private void createExecutorService() {
		destroyExecutorService(DEFAULT_TIMEOUT);
		executorService = Executors.newFixedThreadPool(poolSize);
	}

	private void destroyExecutorService(long timeout) {
		if (executorService != null && !executorService.isShutdown()) {
			try {
				executorService
						.awaitTermination(timeout, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		executorService.shutdown();
	}

	public void execute(Runnable task) {
		executorService.execute(task);
	}

}
