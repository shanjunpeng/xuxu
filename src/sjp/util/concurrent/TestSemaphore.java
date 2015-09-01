package sjp.util.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class TestSemaphore extends Thread {
	Semaphore position;
	private int id;

	public TestSemaphore(Semaphore position, int id) {
		super();
		this.position = position;
		this.id = id;
	}

	@Override
	public void run() {
		if (position.availablePermits() > 0) {
			System.out.println("顾客[" + this.id + "]进入厕所，有空位");

		} else {
			System.out.println("顾客[" + this.id + "]进入厕所，没空位，排队");
		}
		try {
			position.acquire();// 请求许可，如果没有可用的许可，则堵塞
			System.out.println("顾客[" + this.id + "]获得坑位");
			Thread.sleep((int) (Math.random() * 10000));
			System.out.println("顾客[" + this.id + "]使用完毕");
			position.release();// 释放
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Semaphore semaphore = new Semaphore(2);
		for (int i = 0; i < 10; i++) {
			executorService.submit(new TestSemaphore(semaphore, i));
		}
		executorService.shutdown();
		semaphore.acquireUninterruptibly(2);
		System.out.println("===使用完毕");
		semaphore.release(2);
	}

}
