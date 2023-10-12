package fa.nfa;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import fa.State;

public class NFA implements NFAInterface {

    private Set<NFAState> states;
    private Set<NFAState> finalStates;
    private NFAState startState;
    private Set<Character> alphabet;

    /**
     *Constructs an empty DFA object that is ready to be built.
     */
    public NFA() {
        startState = null;
        states = new LinkedHashSet<>();
        finalStates = new LinkedHashSet<>();
        alphabet = new LinkedHashSet<>();
    }

    /**
	 * Adds a a state to the FA instance
	 * @param name is the label of the state 
	 * @return true if a new state created successfully and false if there is already state with such name
	 */
    public boolean addState(String name) {
        for(NFAState state: states) {
            if(state.getName().equals(name)) {
                return false;
            }
        }
        NFAState newState = new NFAState(name);
        states.add(newState);
        return true;
    }

    /**
	 * Marks an existing state as an accepting state
	 * @param name is the label of the state
	 * @return true if successful and false if no state with such name exists
	 */
    public boolean setFinal(String name) {
        for(NFAState c: states) {
            if(c.getName().equals(name)) {
                finalStates.add(c);
                return true;
            }
        }
        return true;
    }

    /**
	 * Adds the initial state to the FA instance
	 * @param name is the label of the start state
	 * @return true if successful and false if no state with such name exists
	 */
    public boolean setStart(String name) {
        for(NFAState state: states) {
            if(state.getName().equals(name)) {
                startState = state;
                return true;
            }
        }
        return false;
    }

    /**
	 * Adds a symbol to Sigma
	 * @param symbol to add to the alphabet set
	 */
    public void addSigma(char symbol) {
        alphabet.add(symbol);
    }

    /**
	 * Simulates a FA on input s to determine
	 * whether the FA accepts s.
	 * @param s - the input string
	 * @return true if s in the language of the FA and false otherwise
	 */
    public boolean accepts(String s) {
        NFAState currentState = startState;
        for(int i=0; i<s.length(); i++) {
            currentState = currentState.getToState(s.charAt(i));
        }
        if(finalStates.contains(currentState)) {
            return true;
        }
        return false;
    }

    /**
	 * Getter for Sigma
	 * @return the alphabet of FA
	 */
    public Set<Character> getSigma() {
        return alphabet;
    }

    /**
	 * Returns state with the given name, or null if none exists
	 * @param name of a state
	 * @return state object or null
	 */
    public State getState(String name) {
        for(NFAState state: states) {
            if(state.getName().equals(name)) {
                return state;
            }
        }
        return null;
    }

    /**
	 * Determines if a state with a given name is final
	 * @param name the name of the state
	 * @return true if a state with that name exists and it is final
	 */
    public boolean isFinal(String name) {
        for(NFAState c: finalStates) {
            if(c.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
	 * Determines if a state with name is final
	 * @param name the name of the state
	 * @return true if a state with that name exists and it is the start state
	 */
    public boolean isStart(String name) {
        return startState.getName().equals(name);
    }

    /**
	 * Return delta entries
	 * @param from - the source state
	 * @param onSymb - the label of the transition
	 * @return a set of sink states
	 */
    public Set<NFAState> getToState(NFAState from, char onSymb) {
        HashMap<Character, NFAState> temp = from.getTransitionList();
        Set<NFAState> rSet = new LinkedHashSet<>();
        for (char symb: temp.keySet()) {
            if(symb == onSymb) {
                rSet.add(temp.get(onSymb));
            }
        }
        return rSet;
    }

    
    /**
	 * Traverses all epsilon transitions and determine
	 * what states can be reached from s through e
	 * @param s
	 * @return set of states that can be reached from s on epsilon trans.
	 */
    public Set<NFAState> eClosure(NFAState s) {
        //FIXME
        throw new UnsupportedOperationException("Unimplemented method 'eClosure'");
    }

    
    /**
	 * Determines the maximum number of NFA copies
	 * created when processing string s
	 * @param s - the input string
	 * @return - the maximum number of NFA copies created.
	 */
    public int maxCopies(String s) {
        //FIXME
        throw new UnsupportedOperationException("Unimplemented method 'maxCopies'");
    }

    /**
	 * Adds the transition to the NFA's delta data structure
	 * @param fromState is the label of the state where the transition starts
	 * @param toState is the set of labels of the states where the transition ends
	 * @param onSymb is the symbol from the NFA's alphabet.
	 * @return true if successful and false if one of the states don't exist or the symbol in not in the alphabet
	 */
    public boolean addTransition(String fromState, Set<String> toStates, char onSymb) {
        if(!alphabet.contains(onSymb)) {return false;}
        if(!states.contains(getState(fromState))) {return false;}
        if(!states.contains(getState(toStates))) {return false;}
        for (NFAState state : states) {
            if(state.getName().equals(fromState)) {
                state.addToState(onSymb, (NFAState) getState(toState));
                return true;
            }
        }
        return false;
    }

    
    /**
	 * Determines if NFA is an instance of a DFA
	 * @return - true if NFA's transition function has DFA's properties.
	 */
    public boolean isDFA() {
        //FIXME
        throw new UnsupportedOperationException("Unimplemented method 'isDFA'");
    }

    /**
     * Construct the textual representation of the DFA, for example
     * A simple two state DFA
     * Q = { a b }
     * Sigma = { 0 1 }
     * delta =
     *		0	1
     *	a	a	b
     *	b	a	b
     * q0 = a
     * F = { b }
     *
     * The order of the states and the alphabet is the order
     * in which they were instantiated in the DFA.
     * @return String representation of the DFA
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();

        //Generating Q
        builder.append(" Q = {");
        for(NFAState c: states) {
            builder.append(" " + c.getName());
        }
        builder.append(" }\n");

        //Generating Sigma
        builder.append("Sigma = {");
        for(Character c: alphabet) {
            builder.append(" " + c);
        }
        builder.append(" }\n");

        //Generating Delta
        builder.append("delta =" + "\n");
        builder.append("\t");
        for(Character c: alphabet) {
            builder.append("\t" + c);
        }
        builder.append("\t\n");

        for(NFAState c: states) {
            builder.append("\t" + c.getName());
            for(Character d: alphabet) {
                //find transitions for all aphabet here and loop
                builder.append("\t" + c.getToState(d));
            }
            builder.append("\n");
        }

        //Generating q0
        builder.append("q0 =");
        builder.append(" " + startState.getName() + "\n");

        //Generating F
        builder.append("F = {");
        for(NFAState c: finalStates) {
            builder.append(" " + c.getName());
        }
        builder.append(" }");

        return builder.toString();
    }

}