package test;

import java.util.Arrays;
import java.util.Random;

public class Lotto {
	public static void main(String[] args) {
		boolean[] pool = new boolean[34];
		int red[] = new int[6];
		System.out.println(Arrays.toString(pool));
		System.out.println(Arrays.toString(red));
		int blue;
		int i = 0;
		Random ran = new Random();
		while (i != 6) {
			int a = ran.nextInt(33) + 1;
			System.out.println(a);
			if (pool[a]) {
				System.out.println("continue");
				continue;
			} else {
				pool[a] = true;
				red[i] = a;
			}
			i++;
		}

		blue = ran.nextInt(16) + 1;
		System.out.println("红球:" + Arrays.toString(red) + " 蓝球:" + blue);
	}

}
