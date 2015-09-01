package sjp.util.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestExecutor extends Thread {
	private int index;

	public TestExecutor(int index) {
		super();
		this.index = index;
	}

	@Override
	public void run() {
		System.out.println("[" + this.index + "]start");
		try {
			Thread.sleep((int) (Math.random() * 10000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("[" + this.index + "]end");
	}

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(4);
		for (int i = 0; i < 10; i++) {
			executorService.execute(new TestExecutor(i));
		}
		System.out.println("submit finish");
		executorService.shutdown();
	}
}
