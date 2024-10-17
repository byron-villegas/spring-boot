package cl.villegas.util;

import cl.villegas.constants.Constants;
import java.security.SecureRandom;

public class PasswordUtil {
    public static String generateByLengthOfLettersAndLengthOfNumbers(int lengthLetters, int lengthNumbers) {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < lengthLetters; i++) {
            int randomIndex = secureRandom.nextInt(Constants.Password.LETTERS.length());
            stringBuilder.append(Constants.Password.LETTERS.charAt(randomIndex));
        }
        for (int i = 0; i < lengthNumbers; i++) {
            int randomIndex = secureRandom.nextInt(Constants.Password.NUMBERS.length());
            stringBuilder.append(Constants.Password.NUMBERS.charAt(randomIndex));
        }
        return stringBuilder.toString();
    }
}