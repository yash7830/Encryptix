import java.util.Scanner;

// Class to represent the user's bank account
class BankAccount {
    private double balance;
    private final String pin;

    public BankAccount(double initialBalance, String pin) {
        this.balance = initialBalance;
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited $" + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrew $" + amount);
            return true;
        } else if (amount > balance) {
            System.out.println("Insufficient balance for the withdrawal.");
            return false;
        } else {
            System.out.println("Withdrawal amount must be positive.");
            return false;
        }
    }

    public void checkBalance() {
        System.out.println("Current balance: $" + balance);
    }

    public boolean validatePin(String inputPin) {
        return pin.equals(inputPin);
    }
}

// Class to represent the ATM machine
class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    public void run() {
        if (!authenticateUser()) {
            System.out.println("Authentication failed. Exiting.");
            return;
        }

        boolean running = true;

        while (running) {
            displayMenu();
            System.out.print("Choose an option: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    account.checkBalance();
                    break;
                case "2":
                    handleDeposit();
                    break;
                case "3":
                    handleWithdraw();
                    break;
                case "4":
                    running = false;
                    System.out.println("Exiting ATM. Thank you for using our services!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private boolean authenticateUser() {
        System.out.print("Enter your ATM PIN: ");
        String inputPin = scanner.nextLine();
        return account.validatePin(inputPin);
    }

    private void handleDeposit() {
        System.out.print("Enter deposit amount: ");
        try {
            double depositAmount = Double.parseDouble(scanner.nextLine());
            account.deposit(depositAmount);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }

    private void handleWithdraw() {
        System.out.print("Enter withdrawal amount: ");
        try {
            double withdrawAmount = Double.parseDouble(scanner.nextLine());
            account.withdraw(withdrawAmount);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }
}

// Main class to run the program
public class ATMSystem {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.00, "1234"); // Initial balance of $1000 and PIN "1234"
        ATM atm = new ATM(account);
        atm.run();
    }
}