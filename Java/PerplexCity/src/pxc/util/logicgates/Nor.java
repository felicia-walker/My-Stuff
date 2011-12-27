package pxc.util.logicgates;

public class Nor extends TwoInput {

	public Nor() {
		super();
	}

	public Nor(Gate pInputAGate, Gate pInputBGate, Gate pOutputGate) {
		super(pInputAGate,pInputBGate,pOutputGate);
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
		
		return !(blnA | blnB);
		
	}

}
