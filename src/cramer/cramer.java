package cramer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.Math; 

public class cramer {
	static int n;
	static ArrayList<int[]> numbers = new ArrayList<int[]>();
	static int[] solution;


	public static ArrayList<int[]> remove_row_and_column (int x, int y, ArrayList<int[]> m){
		ArrayList<int[]> new_m = new ArrayList<int[]>();
		for (int i=0; i<m.size(); i++) {
			if (i!=x) {
				int [] row = m.get(i);
				int[] new_row = new int[row.length - 1];
				int c=0;
				for (int j=0; j<row.length; j++) {
					if (j!=y) {
						new_row[c]=row[j];
						c++;
					}
				}
				new_m.add(new_row);
			}
		}
		return new_m;
	}

	public static int determinant (ArrayList<int[]> m){
		if (m.size()==1) {
			return m.get(0)[0];
		}
		int s = 0;
		for (int i=0;i<m.get(0).length;i++) {
			s+= Math.pow(-1, i)*m.get(0)[i]*determinant(remove_row_and_column(0,i,m));
		}
		return s;
	}
	
	public static ArrayList<int[]> replace (ArrayList<int[]> m, int c){
		ArrayList<int[]> new_m = new ArrayList<int[]>();
		for (int i=0; i<m.size();i++) {
			int[] new_row = Arrays.copyOf(m.get(i), m.get(i).length);
			new_row[c] = solution[i];
			new_m.add(new_row);
		}
		return new_m;
	}
	
	public static void solve (){
		boolean has_answer=true;
		for (int i=0;i<numbers.size();i++) {
			if (has_answer) {
				ArrayList<int[]> new_m = replace (numbers,i);
				int new_det = determinant(new_m);
				int det = determinant(numbers);
				
				try {
					System.out.println("x"+String.valueOf(i+1)+": "+String.valueOf(new_det/det));
				} catch (Exception e) {
					has_answer=false;
					System.out.println("System has 0/infinite solutions");
				}
			}		
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("How many variables");
		Scanner scan = new Scanner(System.in); 
		n = scan.nextInt();
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
		
		
		solve();
	}

}
