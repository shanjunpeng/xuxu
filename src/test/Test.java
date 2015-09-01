package test;


public class Test {

	private static Test instance;

	public static Test getInstance() {
		if (instance == null) {
			instance = new Test();
		}
		return instance;
	}

	public static void main(String[] args) {
		Test test = new Test();

		Test test2 = Test.getInstance();

	}
}
