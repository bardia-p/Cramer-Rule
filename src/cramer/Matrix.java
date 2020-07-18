package cramer;

import java.util.*;
import java.lang.Math; 

public class Matrix {
    ArrayList<int[]> numbers = new ArrayList<int[]>();
	int[] solution;

	public void setNumbers (ArrayList<int[]> numbers) {
		this.numbers=numbers;
	}
	
	public void setSolution(int[] solution) {
		this.solution=solution;
	}
	
	public ArrayList<int[]> remove_row_and_column (int x, int y, ArrayList<int[]> m){
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

	public int determinant (ArrayList<int[]> m){
		if (m.size()==1) {
			return m.get(0)[0];
		}
		int s = 0;
		for (int i=0;i<m.get(0).length;i++) {
			s+= Math.pow(-1, i)*m.get(0)[i]*determinant(remove_row_and_column(0,i,m));
		}
		return s;
	}
	
	public ArrayList<int[]> replace (ArrayList<int[]> m, int c){
		ArrayList<int[]> new_m = new ArrayList<int[]>();
		for (int i=0; i<m.size();i++) {
			int[] new_row = Arrays.copyOf(m.get(i), m.get(i).length);
			new_row[c] = this.solution[i];
			new_m.add(new_row);
		}
		return new_m;
	}
	
	public ArrayList<Integer> solve (){
		ArrayList <Integer> sol = new ArrayList <Integer>();
		
		boolean has_answer=true;
		for (int i=0;i<this.numbers.size();i++) {
			if (has_answer) {
				ArrayList<int[]> new_m = replace (this.numbers,i);
				int new_det = determinant(new_m);
				int det = determinant(this.numbers);
				
				try {
					System.out.println("x"+String.valueOf(i+1)+": "+String.valueOf(new_det/det));
					sol.add(new_det/det);
				} catch (Exception e) {
					has_answer=false;
					System.out.println("System has 0/infinite solutions");
				}
			}		
		}
		return sol;
	}
}