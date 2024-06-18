package com.tr.minibanking.generator;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Locale;

import org.springframework.stereotype.Component;

@Component
public class IbanGenerator {

  private static final SecureRandom RANDOM = new SecureRandom();
  private static final String COUNTRY_CODE = "TR";
  private static final String BANK_CODE = "00123";
  private static final String BRANCH_CODE = "00001";
  private static final int ACCOUNT_NUMBER_LENGTH = 16;

  public static String generateIban() {
    String accountNumber = generateRandomAccountNumber(ACCOUNT_NUMBER_LENGTH);
    String partialIban = BANK_CODE + BRANCH_CODE + accountNumber + COUNTRY_CODE + "00";
    String checkDigits = calculateCheckDigits(partialIban);
    return COUNTRY_CODE + checkDigits + BANK_CODE + BRANCH_CODE + accountNumber;
  }

  private static String generateRandomAccountNumber(int length) {
    StringBuilder accountNumber = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      accountNumber.append(RANDOM.nextInt(10));
    }
    return accountNumber.toString();
  }

  private static String calculateCheckDigits(String iban) {
    StringBuilder numericIban = new StringBuilder();
    for (char character : iban.toCharArray()) {
      if (Character.isDigit(character)) {
        numericIban.append(character);
      } else {
        numericIban.append(Character.getNumericValue(character));
      }
    }
    BigInteger ibanNumber = new BigInteger(numericIban.toString());
    int mod97 = ibanNumber.mod(BigInteger.valueOf(97)).intValue();
    int checkDigits = 98 - mod97;
    return String.format(Locale.US, "%02d", checkDigits);
  }
}
