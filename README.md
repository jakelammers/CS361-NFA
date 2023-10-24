# Project #2: NFA

* Author: Jake Lammers, Munib Ahmed
* Class: CS361 Section # 001
* Semester: Fall 2023

## Overview
This project involves building and managing nondeterministic finite automata (NFAs) using Java, focusing on a detailed representation of their behaviors, states, and transitions. It offers a structured method for simulating and analyzing NFAs within a defined framework.

## Reflection

### Contents
```bash
|-- fa
| |-- FAInterface.java
| |-- State.java
| |-- nfa
| |-- NFAInterface.java
|-- test/nfa
|-- NFATest.java
```
### Compiling and Using
Navigate to the top of the directory that holds the project:

To compile Junit and NFATest on Onyx use the following command:

    javac -cp .:/usr/share/java/junit.jar ./test/nfa/NFATest.java

To run the tests to check functionality use this command on a single line:

    java -cp .:/usr/share/java/junit.jar:/usr/share/java/hamcrest/core.jar org.junit.runner.JUnitCore test.nfa.NFATest


## Sources used

