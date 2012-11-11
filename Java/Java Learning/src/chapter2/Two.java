package chapter2;

public class Two {

	public static void main(String[] args) {
		double var = 11223344.567898765;
		long var_int = (long)var;
		short var_fract = (short)((var - var_int) * 10000);
		
		System.out.println("Var = " + var + " or = " + var_int + "." + var_fract);
	}

}
