package de.tum.in.ase;

import java.math.BigInteger;

public class Bank {

    /**
     * Prints "Valid!" if isValid is true, else it prints "Invalid!"
     * @param isValid Indicates the result of an IBAN-Validity-Check
     */
    public static void printValidity(boolean isValid) {
        System.out.println(isValid ? "Valid!" : "Invalid!");
    }

    /**
     * Checks the length and sign of an IBAN
     * @param iban IBAN to check for length and sign
     * @return True if the length and sign of the IBAN is correct and if result of the IBAN-Validity-Check is true, else false.
     */
    public static boolean checkLengthAndSignWhenValidating(BigInteger iban) {
        if (iban.compareTo(BigInteger.TEN.pow(20)) >= 0 || iban.compareTo(BigInteger.TEN.pow(19)) < 0) {
            return false;
        } else {
            // TODO: What needs to be returned here? Hint: Have a look at what "BigInteger checkLengthAndSignWhenGenerating(BigInteger accountNumber)" returns in a similar situation and check the Javadoc above.
            return false;
        }
    }

    /**
     * Checks the length and sign of an account number
     * @param accountNumber Account number to check for length and sign
     * @return IBAN if the length and sign of the account number is correct, else null.
     */
    public static BigInteger checkLengthAndSignWhenGenerating(BigInteger accountNumber) {
        if (accountNumber.compareTo(BigInteger.TEN.pow(18)) >= 0 || accountNumber.compareTo(BigInteger.TEN.pow(17)) < 0) {
            return null;
        } else {
            return generateIBAN(accountNumber);
        }
    }

    public static void main(String[] args) {
        //You can test your implemented functions here.
        BigInteger accountNumber = new BigInteger("123456789012345678");
        BigInteger validIBAN = new BigInteger("43123456789012345678");
    }

    public static boolean validateIBAN(BigInteger iban) {
        //Hint: first, think about the return type
        //TODO: IBAN: DE43123456789012345678
        //TODO: Rearrange: 123456789012345678DE43
        //TODO: Convert characters to numbers by using: A=10, B=11, C=12,…: 123456789012345678131443
        //TODO: Compute remainder for 97: 123456789012345678131443 mod 97 = x
        //TODO: If the remainder x is 1 the check passed and the IBAN might be valid

      	return false;
    }

    public static BigInteger generateIBAN(BigInteger accountNumber) {
        //TODO: Set checksum to 00: DE00123456789012345678
        //TODO: Rearrange: 123456789012345678DE00
        //TODO: Convert characters to numbers by using: A=10, B=11, C=12,…: 123456789012345678131400
        //TODO: Calculate remainder for 97: 123456789012345678131400 mod 97 = x
        //TODO: Subtract x from 98 for the checksum: 98 - x = 43
        //TODO: Add checksum to IBAN: DE43123456789012345678

        return null;
    }

}
