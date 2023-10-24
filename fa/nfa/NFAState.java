package fa.nfa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import fa.State;

public class NFAState extends State {

    // Changed to store a Set of NFAStates for each character
    private HashMap<Character, Set<NFAState>> transitionList;

    public NFAState(String name) {
        super(name);
        this.transitionList = new HashMap<>();
    }

    // Add a transition from this state to another state
    public void addTransition(char symb, NFAState state) {
        // Get the current set of states for this symbol
        Set<NFAState> stateSet = transitionList.get(symb);

        if (stateSet == null) {
            // If no current set, create one
            stateSet = new HashSet<>();
            transitionList.put(symb, stateSet);
        }

        // Add the new state to the set of states for this symbol
        stateSet.add(state);
    }

    // Get the set of states that this state transitions to on the given symbol
    public Set<NFAState> getToStates(char symb) {
        // This method now returns a Set<NFAState> because there can be multiple
        // destination states
        return transitionList.getOrDefault(symb, new HashSet<>()); // return an empty set if no transitions exist
    }

    /**
     * Returns the TransitionList for the associated NFAState.
     * 
     * @return - HashMap<Character, DFAState> transitionList
     */
    public HashMap<Character, Set<NFAState>> getTransitionList() {
        return this.transitionList;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}