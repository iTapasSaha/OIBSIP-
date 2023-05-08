import java.util.ArrayList;
import java.util.Scanner;

class User {
    private String username;
    private String password;
    private double balance;
    private ArrayList<String> transactions;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0.0;
        this.transactions = new ArrayList<String>();
    }

    public String getUsername() {
        return this.username;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public double getBalance() {
        return this.balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
        this.transactions.add(String.format("Deposited %.2f", amount));
    }

    public void withdraw(double amount) {
        if (this.balance < amount) {
            System.out.println("Insufficient balance.");
        } else {
            this.balance -= amount;
            this.transactions.add(String.format("Withdrew %.2f", amount));
        }
    }

    public void transfer(User recipient, double amount) {
        if (this.balance < amount) {
            System.out.println("Insufficient balance.");
        } else {
            this.balance -= amount;
            recipient.deposit(amount);
            this.transactions.add(String.format("Transferred %.2f to %s", amount, recipient.getUsername()));
        }
    }

    public void printTransactions() {
        System.out.println("Transaction history:");
        for (String transaction : this.transactions) {
            System.out.println(transaction);
        }
    }
}

class ATM {
    private ArrayList<User> users;
    private User currentUser;
    private Scanner scanner;

    public ATM() {
        this.users = new ArrayList<User>();
        this.currentUser = null;
        this.scanner = new Scanner(System.in);
    }

    public void register() {
        System.out.println("Enter a username:");
        String username = scanner.nextLine();
        System.out.println("Enter a password:");
        String password = scanner.nextLine();
        User newUser = new User(username, password);
        this.users.add(newUser);
        System.out.println("Registration successful.");
    }

    public void login() {
        System.out.println("Enter your username:");
        String username = scanner.nextLine();
        System.out.println("Enter your password:");
        String password = scanner.nextLine();
        for (User user : this.users) {
            if (user.getUsername().equals(username)) {
                if (user.authenticate(password)) {
                    this.currentUser = user;
                    System.out.println("Login successful.");
                    return;
                } else {
                    System.out.println("Incorrect password.");
                    return;
                }
            }
        }
        System.out.println("Username not found.");
    }

    public void displayMenu() {
        System.out.println("ATM Menu:");
        System.out.println("1. Transactions History");
        System.out.println("2. Withdraw");
        System.out.println("3. Check balance");
        System.out.println("4. Deposit");
        System.out.println("5. Transfer");
        System.out.println("6. Quit");
    }

    public void performTransaction() {
        System.out.println("Select an option:");
        String input = scanner.nextLine();
        switch (input) {
            case "1":
                this.currentUser.printTransactions();
                break;
            case "2":
                System.out.println("Enter amount to withdraw:");
                double withdrawAmount = Double.parseDouble(scanner.nextLine());
                this.currentUser.withdraw(withdrawAmount);
                break;
            case "3":
                System.out.printf("Your balance is %.2f\n", this.currentUser.getBalance());
                break;
            case "4":
                System.out.println("Enter amount to deposit:");
                double depositAmount = Double.parseDouble(scanner.nextLine());
                this.currentUser.deposit(depositAmount);
                break;
            case "5":
                System.out.println("Enter recipient's username:");
                String recipientUsername = scanner.nextLine();
                User recipient = null;
                for (User user : this.users) {
                    if (user.getUsername().equals(recipientUsername)) {
                        recipient = user;
                        break;
                    }
                }
                if (recipient == null) {
                    System.out.println("Recipient not found.");
                } else {
                    System.out.println("Enter amount to transfer:");
                    double transferAmount = Double.parseDouble(scanner.nextLine());
                    this.currentUser.transfer(recipient, transferAmount);
                }
                break;
            case "6":
                System.out.println("Thank you for using the ATM. Goodbye!");
                System.exit(0);
            default:
                System.out.println("Invalid option.");
        }
    }

    public void run() {
        while (true) {
            if (this.currentUser == null) {
                System.out.println("Welcome to the ATM. Select an option:");
                System.out.println("1. Register");
                System.out.println("2. Login");
                String input = scanner.nextLine();
                if (input.equals("1")) {
                    this.register();
                } else if (input.equals("2")) {
                    this.login();
                } else {
                    System.out.println("Invalid option.");
                }
            } else {
                this.displayMenu();
                this.performTransaction();
            }
        }
    }
}

public class atmInterface {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.run();
    }
}

