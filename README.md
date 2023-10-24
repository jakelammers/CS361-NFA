# Project #2: NFA

* Author: Jake Lammers, Munib Ahmed
* Class: CS361 Section # 001
* Semester: Fall 2023

## Overview


## Reflection
### Contents
```bash
├── Makefile
├── README.md
├── fa
│   ├── FAInterface.java
│   ├── State.java
│   ├── dfa
│   │   ├── DFA.java
│   │   ├── DFAInterface.java
│   │   └── DFAState.java
│   └── nfa
│       ├── NFA.java
│       ├── NFADriver.java
│       ├── NFAInterface.java
│       └── NFAState.java
├── p2.pdf
└── tests
    ├── p2tc0.txt
    ├── p2tc1.txt
    ├── p2tc2.txt
    └── p2tc3.txt
```

### Compiling and Using
First navigate to the top-level of the directory that holds the project:

To compile Junit and NFATest on onyx use the following command:

    javac -cp .:/usr/share/java/junit.jar ./test/nfa/NFATest.java

To run the tests to check functionality use this command on a single line:

    java -cp .:/usr/share/java/junit.jar:/usr/share/java/hamcrest/core.jar org.junit.runner.JUnitCore test.nfa.NFATest


## Sources used

