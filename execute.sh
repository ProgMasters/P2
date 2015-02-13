#!/bin/bash

javac src/P2/*.java -d classes

java -cp P2.Compiler < program.l > program.code

java -cp P2.Interpreter < program.code
