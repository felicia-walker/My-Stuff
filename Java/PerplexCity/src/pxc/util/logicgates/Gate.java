package pxc.util.logicgates;

public abstract class Gate {

	protected boolean blnInputA = false;
	protected Gate GateInputA = null;
	protected Gate GateOutput = null;
		
	public Gate() {	
		//Print();
	}
	
	public Gate(Gate pInputAGate, Gate pOutputGate) {
		GateInputA = pInputAGate;
		GateOutput = pOutputGate;
		//Print();
	}
	
	public void InputAValue (boolean pValue) {
		blnInputA = pValue;
	}
	
	public boolean InputAValue () {
		return blnInputA;
	}
	
	public void InputAGate (Gate pGate) {
		GateInputA = pGate;
	}
	
	public abstract boolean OutputValue();
	
	public void OutputGate (Gate pGate) {
		GateOutput = pGate;
	}
	
	public void Print() {
		System.out.println("blnInputA: " + blnInputA);
		System.out.println("GateInputA: " + GateInputA);
	}
}
