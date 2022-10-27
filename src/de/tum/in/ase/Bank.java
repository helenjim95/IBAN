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
            // TODO: What needs to be returned here?
            //  Hint: Have a look at what "BigInteger checkLengthAndSignWhenGenerating(BigInteger accountNumber)" returns in a similar situation and check the Javadoc above.
            return validateIBAN(iban);
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
        checkLengthAndSignWhenGenerating(accountNumber);
        checkLengthAndSignWhenValidating(validIBAN);
    }

    public static boolean validateIBAN(BigInteger iban) {
        //Hint: first, think about the return type
//        rearrange IBAN
        BigInteger rearranged_iban = rearrangeIBAN(String.valueOf(iban));

//        Compute remainder for 97: 123456789012345678131443 mod 97 = x
        BigInteger remainder = calculateRemainder(rearranged_iban);

        //Validate IBAN: If the remainder x is 1 the check passed and the IBAN might be valid, else invalid
        if (remainder.compareTo(BigInteger.ONE) == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static BigInteger generateIBAN(BigInteger accountNumber) {
        //Set checksum to 00: DE00123456789012345678
        String iban_string = String.valueOf("00") + String.valueOf(accountNumber);
        //Rearrange: 123456789012345678DE00
        BigInteger rearranged_iban = rearrangeIBAN(iban_string);
        //Calculate remainder for 97: 123456789012345678131400 mod 97 = x
        BigInteger remainder = calculateRemainder(rearranged_iban);
        //Generate new check sum
        BigInteger new_check_sum = generateCheckSum(remainder);
        //Add new checksum to IBAN: DE43123456789012345678
        BigInteger new_iban = new BigInteger( String.valueOf(new_check_sum) + String.valueOf(accountNumber));
        return new_iban;
    }

    public static BigInteger calculateRemainder(BigInteger iban) {
        //Compute remainder for 97: 123456789012345678131443 mod 97 = x
        BigInteger DENOMINATOR_97 = new BigInteger("97");
        BigInteger remainder = iban.mod(DENOMINATOR_97);
        return remainder;
    }

    public static BigInteger generateCheckSum(BigInteger remainder) {
        //Generate new check sum: Subtract x from 98 for the checksum: 98 - x = 43
//        Subtraction: Minuend - Subtrahend (Math terms)
        BigInteger MINUEND_98 = new BigInteger("98");
        BigInteger new_check_sum = MINUEND_98.subtract(remainder);
        return new_check_sum;
    }

    public static String convertCharacterToNumberString(String letters) {
        String letter_one = String.valueOf(Character.getNumericValue(letters.charAt(0)));
        String letter_two = String.valueOf(Character.getNumericValue(letters.charAt(1)));
        return letter_one + letter_two;
    }

    public static BigInteger rearrangeIBAN(String iban) {
        //add DE to front of IBAN: 43123456789012345678 -> DE43123456789012345678
        String LETTERS = "DE";
        String check_sum = iban.substring(0,2);
        String account_number = iban.substring(2,20);
        //Rearrange: 123456789012345678DE43
//        Convert characters into numeric value by using: A=10, B=11, C=12,…: 123456789012345678131443
        String letters_number_string = convertCharacterToNumberString(LETTERS);
//        Rearrange IBAN in the order of "account_number, letters (into numeric value), check_sum" and convert back into type BigInteger
        BigInteger rearranged_iban = new BigInteger(account_number + letters_number_string + check_sum);
        return rearranged_iban;
    }

}
