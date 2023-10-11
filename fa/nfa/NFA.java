package fa.nfa;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import fa.State;

public class NFA implements NFAInterface {

    private Set<NFAState> states;
    private Set<NFAState> finalStates;
    private NFAState startState;
    private Set<Character> alphabet;

    public NFA() {
        startState = null;
        states = new LinkedHashSet<>();
        finalStates = new LinkedHashSet<>();
        alphabet = new LinkedHashSet<>();
    }

    @Override
    public boolean addState(String name) {
        if(states.contains(getState(name))) {
            return false;
        }
        NFAState newState = new NFAState(name);
        states.add(newState);
        return true;
    }

    @Override
    public boolean setFinal(String name) {
        for(NFAState c: states) {
            if(c.getName().equals(name)) {
                finalStates.add(c);
                return true;
            }
        }
        return true;
    }

    @Override
    public boolean setStart(String name) {
        if(states.contains(getState(name))) {
            startState = (NFAState) getState(name);
            return true;
        }
        return false;
    }

    @Override
    public void addSigma(char symbol) {
        alphabet.add(symbol);
    }

    @Override
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

    @Override
    public Set<Character> getSigma() {
        return alphabet;
    }

    @Override
    public State getState(String name) {
        for(NFAState checking: states) {
            if(Objects.equals(checking.getName(), name)) {
                return checking;
            }
        }
        return null;
    }

    @Override
    public boolean isFinal(String name) {
        for(NFAState c: finalStates) {
            if(c.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isStart(String name) {
        return startState.getName().equals(name);
    }

    @Override
    public Set<NFAState> getToState(NFAState from, char onSymb) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getToState'");
    }

    //FIXME
    @Override
    public Set<NFAState> eClosure(NFAState s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eClosure'");
    }

    //FIXME
    @Override
    public int maxCopies(String s) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'maxCopies'");
    }

    @Override
    public boolean addTransition(String fromState, Set<String> toStates, char onSymb) {
        if(!alphabet.contains(onSymb)) {return false;}
        if(!states.contains(getState(fromState))) {return false;}
        if(!states.contains(getState(toState))) {return false;}
        for (NFAState state : states) {
            if(state.getName().equals(fromState)) {
                state.addToState(onSymb, (NFAState) getState(toState));
                return true;
            }
        }
        return false;
    }

    //FIXME
    @Override
    public boolean isDFA() {
        // TODO Auto-generated method stub
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