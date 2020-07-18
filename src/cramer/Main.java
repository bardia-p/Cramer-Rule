package cramer;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<int[]> numbers = new ArrayList<int[]>();
		int[] solution;
		ArrayList<Integer> sol = new ArrayList<Integer>();
		
		Matrix m = new Matrix();
		
		
		System.out.println("How many variables");
		Scanner scan = new Scanner(System.in); 
		int n = scan.nextInt();
		scan.nextLine();
		
		System.out.println("Enter the coefficients");
		
		for (int i=0;i<n;i++) {
			String[] row = scan.nextLine().split("\\s");
			int[] new_row = new int[row.length];
			for (int j=0;j<row.length;j++) {
				new_row[j]=Integer.parseInt(row[j]);
			}
			numbers.add(new_row);
		}
		
		System.out.println("Enter the solutions");
		solution = new int[n];
		String[] row = scan.nextLine().split("\\s");
		for (int j=0;j<row.length;j++) {
			solution[j]=Integer.parseInt(row[j]);
			}
		scan.close();
		
		m.setNumbers(numbers);
		m.setSolution(solution);
		
		sol = m.solve();
		
	}

}
