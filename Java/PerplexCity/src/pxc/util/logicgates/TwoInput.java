package pxc.util.logicgates;

public abstract class TwoInput extends Gate {

	protected boolean blnInputB = false;
	protected Gate GateInputB = null;

	public TwoInput () {
		super();
	}
	
	public TwoInput(boolean pInputAValue, boolean pInputBValue) {
		super();
		
		blnInputA = pInputAValue;
		blnInputB = pInputBValue;
	}
	
	public TwoInput(Gate pInputAGate, Gate pInputBGate, Gate pOutputGate) {
		super(pInputAGate,pOutputGate);
		GateInputB = pInputBGate;
	}

	public void InputBValue (boolean pValue) {
		blnInputB = pValue;
	}

	public boolean InputBValue () {
		return blnInputB;
	}
	
	public void InputBGate (Gate pGate) {
		GateInputB = pGate;
	}
	
}
