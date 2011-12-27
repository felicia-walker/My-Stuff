package pxc.util.logicgates;

public class Not extends Gate {

	public Not() {
		super();
	}
	
	public Not(Gate pInputAGate, Gate pOutputGate) {
		super(pInputAGate,pOutputGate);		
	}
	
	public boolean OutputValue() {
		if (GateInputA == null) {
			//System.out.println("A");
			return !blnInputA;
		} else {
			//System.out.println("B");
			return !GateInputA.OutputValue();
		}
	}

}
