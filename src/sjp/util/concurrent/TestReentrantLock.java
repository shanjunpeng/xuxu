package sjp.util.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLock extends Thread {

	private ReentrantLock lock;
	private int id;

	public TestReentrantLock(ReentrantLock lock, int id) {
		super();
		this.lock = lock;
		this.id = id;
	}

	@Override
	public void run() {

		lock.lock();
		// System.out.println(lock.isHeldByCurrentThread());
		// System.out.println(lock.getHoldCount());

		System.out.println(id + "获得lock");
		try {
			Thread.sleep((int) (Math.random() * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println(id + "释放lock");
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		ReentrantLock lock = new ReentrantLock();
		for (int i = 0; i < 10; i++) {
			executorService.submit(new TestReentrantLock(lock, i));
		}
		System.out.println("===========");
		executorService.shutdown();
	}
}
