package pxc.util.logicgates;

public class Or extends TwoInput {

	public Or() {
		super();
	}

	public Or(Gate pInputAGate, Gate pInputBGate, Gate pOutputGate) {
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
		
		return (blnA | blnB);
		
	}

}
