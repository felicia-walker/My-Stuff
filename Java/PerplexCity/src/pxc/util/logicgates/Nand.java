package pxc.util.logicgates;

public class Nand extends TwoInput {

	public Nand() {
		super();
	}

	public Nand(Gate pInputAGate, Gate pInputBGate, Gate pOutputGate) {
		super(pInputAGate,pInputBGate,pOutputGate);
	}
	
	public Nand(boolean pInputAValue, boolean pInputBValue) {
		super(pInputAValue,pInputBValue);
	}
	
	public boolean OutputValue() {
		boolean blnA = false;
		boolean blnB = false;
		
		if (GateInputA == null) {
			blnA = blnInputA;
		} else {
			blnA = GateInputA.OutputValue();
		}
		
		if (GateInputB == null) {
			blnB = blnInputB;
		} else {
			blnB = GateInputB.OutputValue();
		}
		
		return !(blnA & blnB);
		
	}

}
