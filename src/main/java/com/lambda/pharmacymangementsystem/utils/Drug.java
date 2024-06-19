package com.lambda.pharmacymangementsystem.utils;

public class Drug {
    static String domain = "0123456789";

    /**
     * This function takes the most recent drug code and updates the count while ensuring that the characters in the domain serve as neighbors to each other
     *
     * @param drugCode - the most recent drug code from memory
     * @return next unique drug code
     */
    public static String generateNextDrugCode(String drugCode) {
        String uniqueCode = drugCode.split("-")[1];
        String nextValue = "";
        boolean carry = true;
        for (int i = uniqueCode.length() - 1; i >= 0; i--) {
            char current = uniqueCode.charAt(i);
            if (carry) {
                char nextCharacter = getNextCharacter(current);
                carry = nextCharacter == '0';
                nextValue = nextCharacter + nextValue;
            } else {
                nextValue = current + nextValue;
            }
        }

        // add a new character to the domain when the limit is reached
        if (carry) {
            nextValue = '1' + nextValue;
        }
        return "MED-" + nextValue;
    }

    /**
     * Takes a character from the domain and return the next character in the sequence as if the elements were placed in a circular queue
     *
     * @param value (as char)
     * @return nextCharacter
     */
    private static char getNextCharacter(char value) {
        int index = domain.indexOf(value);
        int nextIndex = (index + 1) % domain.length();
        return domain.charAt(nextIndex);
    }
}
