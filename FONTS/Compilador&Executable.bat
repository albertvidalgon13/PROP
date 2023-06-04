@echo off

javac -cp .;forms-1.0.7.jar;junit-4.12.jar;hamcrest-core-1.3.jar ./controladors/*.java ./domini/*.java ./exceptions/*.java ./presencia/*.java ./tests/*.java
jar cfe project.jar domini.main ./controladors/*.class ./domini/*.class ./exceptions/*.class ./presencia/*.class ./tests/*.class
java -cp project.jar domini.main
