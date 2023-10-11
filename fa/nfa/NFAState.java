package fa.nfa;
import java.util.HashMap;

import fa.State;

public class NFAState extends State {
	private HashMap<Character,NFAState> transitionList;

    public NFAState() {
		//super(name);
		
	}
	
	/**
	 * All concrete constructors must
	 * invoke this one as super(name).
	 * This way name instance variable is 
	 * correctly set.
	 */
	public NFAState(String name) {
		this.name = name;
        transitionList = new HashMap<Character,NFAState>();
	}
	
	/**
	 * getter for the string label
	 * @return returns the state label.
	 */
	public String getName(){
		return "";
	}

	/**
     * Adds a transition to the DFAState's transition list.
     * 
     * @param symb - Character to transition on
     * @param state - State to transition to
     */
    public void addToState(char symb, NFAState state) {
        transitionList.put(symb, state);
    }
    
    /**
     * Returns the toState in the HashMap associated with the imput transition character.
     *  
     * @param symb - Transition character
     * @return - The state that is paired to symb in the HashMap
     */
    public NFAState getToState(char symb) {
        NFAState rval = transitionList.get(symb);
        return rval;
    }

    /**
     * Returns the TransitionList for the associated DFAState.
     * @return - HashMap<Character, DFAState> transitionList
     */
    public HashMap<Character,NFAState> getTransitionList() {
        return this.transitionList;
	}
	
	@Override
	public String toString(){
		return "";
	}
    
}
