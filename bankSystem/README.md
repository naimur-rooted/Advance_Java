# BankSystem

This project is part of the Advance Java tasks. It demonstrates **Object-Oriented Programming (OOP)** concepts in Java using a simple banking system.

##  Classes
- **Account**  
  Base class with account number, holder name, balance, deposit and withdraw methods.
- **SavingsAccount**  
  Extends `Account`, adds interest rate.
- **FixedAccount**  
  Extends `Account`, adds tenure year.
- **Bank**  
  Holds references to both `SavingsAccount` and `FixedAccount`.
- **Start**  
  Driver class with `main()` method to run the program.

## ⚙️ How to Run
1. Compile all `.java` files inside `com.mycompany.banksystem`.
2. Run `Start.java`.
3. The program will print details of the bank, savings account, and fixed account.

## 🖥️ Sample Output
Bank Name: DBBL
Savings Account Holder: OOP1
Savings Account Number: 11111111
Savings Balance: 10000.0
Interest Rate: 7.5
Fixed Account Holder: OOP1
Fixed Account Number: 11111112
Fixed Balance: 100000.0
Tenure Year: 10
