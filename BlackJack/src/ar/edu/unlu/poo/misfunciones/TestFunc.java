package ar.edu.unlu.poo.misfunciones;

public class TestFunc {

	public static void main(String[] args) {
		
		Rand random = new Rand();
		int[] k = new int[10];
		
		
		k = random.randomList(10);	
		
		for (int i : k) {
			
			System.out.println(i);
			
		}
		
		
		//System.out.println(random.randomNum(1, 10));

	}

}
