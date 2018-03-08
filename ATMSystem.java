/** HW1 Solution
 *  author: Edrees Osman
 *  version: 1.0
 *  copyright
 *
 *
 *  This class contains the main() method for the project.
 *  It allows the user to interact with the ATMs and
 *  access their accounts. Currently, the only ability
 *  the user has is to withdraw money from their account
 *  up to a certain limit.
 *
 */


import java.util.GregorianCalendar;
import java.util.Scanner;

public class ATMSystem {

    public static void main(String args[]) {

        // Date to be referenced when checking for Cash Card expiration dates
        GregorianCalendar accessDate = new GregorianCalendar();
        accessDate.set(18, 4, 10);

        ATM ATM_A1 = new ATM("A", 50);
        ATM ATM_A2 = new ATM("A", 50);

        ATM ATM_B1 = new ATM("B", 50);
        ATM ATM_B2 = new ATM("B", 50);

        Bank bankA = new Bank("A", ATM_A1, ATM_A2);
        Bank bankB = new Bank("B", ATM_B1, ATM_B2);

        // CashCards associated with accounts in Bank A
        CashCard c1 = new CashCard("11", "mypassword", 150, 10, 3, 16);
        CashCard c2 = new CashCard("12", "mypassword", 200, 20, 5, 4);
        CashCard c3 = new CashCard("13", "mypassword", 400, 18, 5, 18);

        // CashCards associated with accounts in Bank B
        CashCard c4 = new CashCard("111", "mypassword", 80, 19, 7, 25);
        CashCard c5 = new CashCard("122", "mypassword", 350, 18, 4, 8);
        CashCard c6 = new CashCard("133", "mypassword", 1000, 18, 3, 1);

        // Populate bankA's accountList
        bankA.getAccountList().add(c1);
        bankA.getAccountList().add(c2);
        bankA.getAccountList().add(c3);

        // Populate bankB's accountList
        bankB.getAccountList().add(c4);
        bankB.getAccountList().add(c5);
        bankB.getAccountList().add(c6);

        Scanner sc = new Scanner(System.in);

        String atm, cardNum, pass, input;
        int amt;
        int total = 0;
        CashCard card;
        ATM machine;
        Bank bank;
        boolean isTransactionOver = false;
        boolean isDone = false;
        boolean incorrectPassword = true;

        while(!isDone) {
            System.out.println("Enter your choice of ATM.\n");
            atm = sc.nextLine();

            if (atm.equals("ATM_A1")){
                machine = new ATM(ATM_A1.getBankId(), ATM_A1.getDailyLimit());
                bank = bankA;
            }
            else if(atm.equals("ATM_A2")) {
                machine = new ATM(ATM_A2.getBankId(), ATM_A2.getDailyLimit());
                bank = bankA;
            }
            else if(atm.equals("ATM_B1")) {
                machine = new ATM(ATM_B1.getBankId(), ATM_B1.getDailyLimit());
                bank = bankB;
            }
            else if(atm.equals("ATM_B2")) {
                machine = new ATM(ATM_B2.getBankId(), ATM_B2.getDailyLimit());
                bank = bankB;
            }
            else {
                System.out.println("That is not a valid ATM. Try again.");
                continue;
            }

            while(!isTransactionOver) {

                System.out.println("Enter your card.\n");
                cardNum = sc.nextLine();
                String id = cardNum.substring(0,1);
                if(!(id.toUpperCase().equals(machine.getBankId()))) {
                    System.out.println("That card is not registered in this bank.\n" +
                            "Try creating a new account on a later date!");
                    continue;
                }

                if (machine.isValidId(cardNum)) {

                    cardNum = cardNum.substring(1);
                    cardNum = cardNum.trim();
                    card = bank.getCard(cardNum);

                    if (card != null) {

                        if (bank.isValidCard(cardNum, accessDate)) {
                            System.out.println("The card is accepted. Please enter your password.\n");

                            while(incorrectPassword) {

                                pass = sc.nextLine();

                                if (bank.isValidPass(card, pass)) {
                                    System.out.println("Authorization complete.");
                                    System.out.println("Please enter the amount to withdraw or quit.");

                                    do {

                                        input = sc.nextLine();
                                        if (input.toLowerCase().equals("quit")) {
                                            break;
                                        }
                                        else {
                                            amt = Integer.parseInt(input);
                                            int flag = machine.withdraw(card, amt, total);
                                            if (flag == -1) {
                                                System.out.println("That amount exceeds the maximum amount you can withdraw " +
                                                        "per transaction. Enter a new amount or quit.");
                                                amt = 0;
                                            }
                                            else if (flag == 1) {
                                                System.out.println("$" + amt + " is withdrawn from your account."
                                                        + " The remaining balance is $" + card.getBalance() + ".");
                                                total += amt;

                                                if (total < machine.getDailyLimit()) {
                                                    System.out.println("If you have more transactions, enter the amount or quit.");
                                                }
                                                else{
                                                    System.out.println("You have reached the daily limit " +
                                                            "for withdrawing money. Visit us again tomorrow to use " +
                                                            "our services!");
                                                }
                                            }
                                            else if(flag == 2) {
                                                int moneyLeft = machine.getDailyLimit() - total;
                                                System.out.println("That amount will put your total above the " +
                                                        "daily limit.\n" +
                                                        "You can currently withdraw up to $" + moneyLeft + " more.");
                                            }
                                            else {
                                                System.out.println("The account exceeds the current balance. " +
                                                        "Enter a new amount or quit.");
                                            }
                                        }
                                    } while (total < machine.getDailyLimit()
                                            && amt <= machine.getDailyLimit()
                                            && amt <= card.getBalance());

                                    isTransactionOver = true;
                                }
                                else {
                                    System.out.println("That password is incorrect. Try again.");
                                }
                                incorrectPassword = false;
                            }
                        }
                        else {
                            System.out.println("That card is expired and returned to you.");
                        }
                    }

                }

            }

            isDone = true;

        }

    }
}
