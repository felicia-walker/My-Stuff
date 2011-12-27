package pxc.puzzles.escape;

public enum Direction {
	N, E, S, W;
	
	public static Direction next(Direction current, boolean turnCW)
	{
		Direction ret_val = null;
		switch(current)
		{
			case N:
				ret_val = turnCW ? E : W;
				break;
			case E:
				ret_val = turnCW ? S : N;
				break;
			case S:
				ret_val = turnCW ? W : E;
				break;
			case W:
				ret_val = turnCW ? N : S;
		}
		
		return ret_val;
	}
	
	public static Direction fromChar(char dir)
	{
		Direction ret_val = null;
		switch(dir)
		{
			case 'N':
			case 'n':
				ret_val = N;
				break;
				
			case 'E':
			case 'e':
				ret_val = E;
				break;
				
			case 'S':
			case 's':
				ret_val = S;
				break;
				
			case 'W':
			case 'w':
				ret_val = W;
				break;
				
			default:
				// Ignore
				//throw new AssertionError("Invalid direction value");
		}
		
		return ret_val;
	}

}
