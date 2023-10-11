package fa.nfa;
import fa.State;

public class NFAState extends State {

    public State() {
		
	}
	
	/**
	 * All concrete constructors must
	 * invoke this one as super(name).
	 * This way name instance variable is 
	 * correctly set.
	 */
	public State(String name) {
		this.name = name;
	}
	
	/**
	 * getter for the string label
	 * @return returns the state label.
	 */
	public String getName(){
		return name;
	}
	
	@Override
	public String toString(){
		return name;
	}
    
}
