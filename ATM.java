/** The ATM class represents the medium
 *  by which the user interacts with their
 *  Bank and CashCard account.
 *  It allows the user to access their account
 *  and withdraw money, given they are able
 *  to authenticate their identity.
 *
 *
 *
 */


public class ATM {

    private int dailyLimit;
    private String bankId;

    public ATM(String id, int limit) {
        this.bankId = id;
        this.dailyLimit = limit;
    }

    /**
     * Accessor
     * @return the bank ID associated with this ATM
     */
    public String getBankId() {
        return this.bankId;
    }

    /**
     * Accessor
     * @return the maximum amount of money that may be
     * withdrawn from any given account per day
     */
    public int getDailyLimit() {
        return this.dailyLimit;
    }

    /**
     * Checks if this ATM can be accessed by the given card
     * by cross-referencing the CashCard number and the ATM's
     * bankId
     * @param s the CashCard number
     * @return TRUE if CashCard number matches with
     *         associated bankId;
     *         FALSE otherwise
     */
    public boolean isValidId(String s) {
        String cardId = s.substring(0,1);
        cardId = cardId.toUpperCase();
        if (cardId.equals(this.bankId)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Allows user to withdraw money from their account
     * assuming they have not already met the daily
     * limit for the associated ATM
     * @param c CashCard used to retrieve account balance
     *          and withdraw money
     * @param n amount of money to be withdrawn from account
     * @return a number of values (i.e. -1, 1, 0)
     *          |-1| represents an unsuccessful withdrawal due to
     *          a value of money that would go above the
     *          daily withdrawal limit at the given ATM.
     *          |1| represents a successful withdrawal of money.
     *          |2| represents an unsuccessful withdrawal  due
     *          to an amount of money that would go over the daily
     *          withdrawal limit at the given ATM, taking into
     *          account money that was withdrawn from previous
     *          transactions during the day
     *          |0| represents an unsuccessful withdrawal due
     *          to insufficient funds.
     */
    public int withdraw(CashCard c, int requested, int currentTotal) {
        if (requested > this.dailyLimit) {
            return -1;
        }
        else if (c.getBalance() > 0 && (requested + currentTotal) <= dailyLimit) {
            c.setBalance(c.getBalance() - requested);
            return 1;

        }
        else if (c.getBalance() > 0 && (requested + currentTotal) > dailyLimit) {
            return 2;
        }
        else {
            return 0;
        }
    }

}
