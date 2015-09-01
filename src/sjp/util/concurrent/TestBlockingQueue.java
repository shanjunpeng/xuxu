package sjp.util.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class TestBlockingQueue extends Thread {
	public static BlockingQueue<String> queue = new LinkedBlockingQueue<String>(
			3);
	private int index;

	public static final ReentrantLock LOCK = new ReentrantLock();

	public TestBlockingQueue(int index) {
		super();
		this.index = index;
	}

	@Override
	public void run() {
		try {

			queue.put(String.valueOf(index));
			LOCK.lock();
			System.out.println(index + " in queue");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			LOCK.unlock();
		}

	}

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			executorService.submit(new TestBlockingQueue(i));
		}

		Thread thread = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep((int) (Math.random() * 1000));
						if (!TestBlockingQueue.queue.isEmpty()) {
							LOCK.lock();
							String str = TestBlockingQueue.queue.take();
							System.out.println("====== " + str + " has take");
							LOCK.unlock();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		};
		executorService.submit(thread);
		executorService.shutdown();
	}

}
