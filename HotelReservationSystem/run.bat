@echo off
cls
echo ================================
echo  Hotel Reservation System Run
echo ================================

REM Step 1: Delete old class files (safe cleanup)
del /S /Q *.class

REM Step 2: Compile all Java source files
javac -cp .;mysql-connector-j-9.7.0.jar -d . frames\*.java entity\*.java interfaces\*.java repository\*.java util\*.java Start.java

REM Step 3: Run the main class
java -cp .;mysql-connector-j-9.7.0.jar Start

pause
