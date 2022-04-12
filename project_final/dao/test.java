package project_final.dao;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class test {
	public static void main(String[] args) {
		String[] strs = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
				"t", "u", "v", "w", "x", "y", "z" };

		List<String> list = Arrays.asList(strs);
		Scanner sc = new Scanner(System.in);
		
		String answer = "";

		for (int i = 0; i < 7; i++) {
			Collections.shuffle(list);
			System.out.print(list.get(i) + "  ");
			answer += list.get(i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		System.out.println();
		String ran = sc.nextLine();

		if (ran.equals(answer)) {
			System.out.println("맞았습니다.");
		} else {
			System.out.println("틀렸습니다.");
		}
	}
}
