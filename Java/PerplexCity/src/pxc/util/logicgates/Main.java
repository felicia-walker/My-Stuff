package pxc.util.logicgates;

public class Main {

	private static Nand myNand = new Nand();
	
	public static void main(String[] args) {
		
		System.out.println("A       B        Output");
		myNand = new Nand(false,false);
		System.out.println(myNand.InputAValue() + " " + myNand.InputBValue() + " " + myNand.OutputValue());
		myNand = new Nand(true,false);
		System.out.println(myNand.InputAValue() + " " + myNand.InputBValue() + " " + myNand.OutputValue());
		myNand = new Nand(false,true);
		System.out.println(myNand.InputAValue() + " " + myNand.InputBValue() + " " + myNand.OutputValue());
		myNand = new Nand(true,true);
		System.out.println(myNand.InputAValue() + " " + myNand.InputBValue() + " " + myNand.OutputValue());
		
	}
}
