/**
 * @author jake lammers
 * @author munib ahmed
 * 10/24/23
 * 
 * This program implements a nondeterministic finite automaton (NFA) 
 * with definitions for the automaton's structure and behaviors.
 */
package fa.nfa;

// Necessary imports for the functionality of the NFA class.
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

// Importing the State class which is used in this file.
import fa.State;

// The NFA class implements the NFAInterface, indicating it uses methods specific to nondeterministic finite automata.
public class NFA implements NFAInterface {


// Class member variables representing the sets of states, final states, the start state, and the alphabet for the NFA.
    private Set<NFAState> states;
    private Set<NFAState> states;
    private Set<NFAState> finalStates;
    private NFAState startState;
    private Set<Character> alphabet;

    /**
     *Constructs an empty DFA object that is ready to be built.
     */
    public NFA() {
	
	// Initialization of the NFA components
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
        return false;
    }

    /**
	 * Adds the initial state to the FA instance
	 * @param name is the label of the start state
	 * @return true if successful and false if no state with such name exists
	 */
    public boolean setStart(String name) {
        if(states.contains(getState(name))) {
            startState = (NFAState) getState(name);
            return true;
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
    
    public boolean accepts(String input) {
        NFAState current = startState;
        List<NFAState> visited = new ArrayList<>(); // To track state configurations
    
        visited.add(current);
    
        // Loop through the input string
        for (char symbol : input.toCharArray()) {
            // Create a new set to store next states
            Set<NFAState> nextStates = new LinkedHashSet<>();
    
            // For each current state, find transitions for the symbol
            for (NFAState state : visited) {
                Set<NFAState> stateTransitions = getToState(state, symbol);
                nextStates.addAll(stateTransitions);
            }
    
            if (nextStates.isEmpty()) {
                // If there are no transitions, the NFA rejects the input string
                return false;
            }
    
            // Update the current state with the set of next states
           for(NFAState state: nextStates){

            current = state;
           }
           // current = nextStates.iterator().next();
    
            visited.addAll(nextStates);
        }
    
        // After processing the entire input string, check for acceptance
        NFAState finalState = visited.get(visited.size() - 1);
    
        if (isFinal(finalState.getName())) {
            return true; // The NFA accepts the input string
        }
    
        return false; // The NFA rejects the input string
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
	 * @param state - NFA state to search for epsilon transitions
	 * @return set of states that can be reached from s on epsilon trans.
	 */
    public Set<NFAState> eClosure(NFAState state) {
        Set<NFAState> eClosureSet = new LinkedHashSet<>();// Instead of this, use a HashSet.
        Stack<NFAState> stack = new Stack<>();

        // Initialize the stack with the initial state 's'
        stack.push(state);

        // Perform DFS to find epsilon closures
        while (!stack.isEmpty()) {
            NFAState currentState = stack.pop();

            // Add the current state to the epsilon closure set
            eClosureSet.add(currentState);

            // Get the epsilon transitions of the current state
            HashMap<Character,NFAState> tl = currentState.getTransitionList();
            for(char c: tl.keySet()) {
                if(c == 'e') {
                    // Ad the state to the vector itself; don't do anything else.
                    Set<NFAState> temp = getToState(currentState, c);
                    // for(NFAState nfa: temp) {
                    //     eClosureSet.add(nfa);
                    // }
                    eClosureSet.addAll(temp);
                    for (NFAState nfa: temp) stack.push(nfa);
                }
            }
        }
        return eClosureSet;
    }

    /**
	 * Determines the maximum number of NFA copies
	 * created when processing string s
	 * @param s - the input string
	 * @return - the maximum number of NFA copies created.
	 */
    public int maxCopies(String input) {
        return 0;

    }

    /**
	 * Adds the transition to the NFA's delta data structure
	 * @param fromState is the label of the state where the transition starts
	 * @param toState is the set of labels of the states where the transition ends
	 * @param onSymb is the symbol from the NFA's alphabet.
	 * @return true if successful and false if one of the states don't exist or the symbol in not in the alphabet
	 */
    public boolean addTransition(String fromState, Set<String> toStates, char onSymb) {
        if(onSymb == 'e' || !alphabet.contains(onSymb)) {alphabet.add('e');}
        if(!alphabet.contains(onSymb)) {return false;}
        if(!states.contains(getState(fromState))) {return false;}
        for(String stateName: toStates) {
            if(!states.contains(getState(stateName))) {
                return false;
            }
        }
        for (NFAState state : states) {
            for(String toStateName : toStates) {
                if(state.getName().equals(fromState)) {
                    state.addToState(onSymb,(NFAState)getState(toStateName));
                    return true;
                }
            }
        }
        return false;
    }

    
    /**
	 * Determines if NFA is an instance of a DFA
	 * @return - true if NFA's transition function has DFA's properties.
	 */
    public boolean isDFA() {
        for(NFAState state: states) {
            HashMap<Character, NFAState> temp = state.getTransitionList();
            for (char symb: temp.keySet()) {
                int flag = 0;
                if(temp.containsKey(symb)) {
                    flag +=1;
                } else {
                    flag = 1;
                } 
                if(symb == 'e') {
                    return false;
                }
                if(flag >=2) {
                    return false;
                }
            }
        }
        return true;
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
