/** The CashCard class represents the key
 *  which the user can utilize in order to
 *  access the ATM machines and conduct
 *  transactions with their bank account.
 *
 *  This class is used to store critical
 *  information regarding the card and account,
 *  namely the card number, the password for the account,
 *  and the expiration date for the card.
 *
 */

import java.util.GregorianCalendar;

public class CashCard {

    private String cardNumber;
    private String password;
    private int balance;
    private GregorianCalendar expDate;

    public CashCard (String cardNum, String pass, int bal, int year, int month, int day) {
        this.cardNumber = cardNum;
        this.password = pass;
        this.balance = bal;
        expDate = new GregorianCalendar();
        expDate.set(year, month, day);
    }

    /**
     * Accessor
     * @return the given CashCard's number
     */
    public String getCardNumber() {
        return this.cardNumber;
    }

    /**
     * Accessor
     * @return the password for the CashCard's
     * associate account
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Accessor
     * @return the current balance of the account
     */
    public int getBalance() {
        return this.balance;
    }

    /**
     * Accessor
     * @return the expiration date of the CashCard
     */
    public GregorianCalendar getExpDate() {
        return this.expDate;
    }

    /**
     * Mutator that alters the account balance
     * @param n the amount the account balance should
     *          be changed to
     */
    public void setBalance(int n) {
        this.balance = n;
    }


}
