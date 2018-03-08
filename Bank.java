/** The Bank class manages the CashCard accounts
 *  and validates the user's information with
 *  respect to certain parameters in order to grant
 *  them access to their account.
 *
 */


import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Bank {

    private String name;
    private ATM atm1;
    private ATM atm2;
    private ArrayList<CashCard> accountList;

    public Bank (String n, ATM a1, ATM a2) {
        this.name = n;
        atm1 = a1;
        atm2 = a2;
        accountList = new ArrayList<CashCard>();
    }

    /**
     * Accessor
     * @return an ArrayList of CashCards
     */
    public ArrayList<CashCard> getAccountList() {
        return accountList;
    }

    /**
     * Accessor
     * @param cardNum the given name of the CashCard to be found in the list
     * @return a CashCard or null if it is not found in the list
     */
    public CashCard getCard(String cardNum) {
        for (CashCard c: accountList) {
            if (c.getCardNumber().equals(cardNum)) {
                return c;
            }
        }
        return null;
    }

    /**
     * To determine whether the user-entered CashCard is in the list
     * and whether the card hs expired.
     * @param cardNum the given name of the CashCard to be found in the list
     * @param exp the date the ATM is accessed;
     *            to be referenced when determining
     *            whether the CashCard is expired or not.
     * @return TRUE if the entered CashCard is in the list and the
     *         card is still functional (i.e. not expired);
     *         FALSE otherwise
     */
    public boolean isValidCard(String cardNum, GregorianCalendar exp) {
        for (CashCard c: accountList) {
            if (c.getCardNumber().equals(cardNum)) {
                if (c.getExpDate().get(Calendar.YEAR) > exp.get(Calendar.YEAR)) {
                    return true;
                }
                else if(c.getExpDate().get(Calendar.YEAR) == exp.get(Calendar.YEAR)) {
                    if (c.getExpDate().get(Calendar.MONTH) > exp.get(Calendar.MONTH)) {
                        return true;
                    }
                    else if(c.getExpDate().get(Calendar.MONTH) == exp.get(Calendar.MONTH)) {
                        if (c.getExpDate().get(Calendar.DAY_OF_MONTH) > exp.get(Calendar.DAY_OF_MONTH)) {
                            return true;
                        }
                    }
                    else {
                        return false;
                    }
                }
                else {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Determines whether the password entered by the user
     * matches up to the password on the CashCard account
     * @param c the CashCard to be referenced for retrieving
     *          a password for the associated account
     * @param input the password entered by the user
     * @return TRUE if user enters correct password;
     *         FALSE otherwise
     */
    public boolean isValidPass(CashCard c, String input) {
        if (input.equals(c.getPassword())){
            return true;
        }
        return false;
    }



}
