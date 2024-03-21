package com.paymentgateway.gpay.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymentgateway.gpay.bankService.entity.Account;
import com.paymentgateway.gpay.bankService.response.BankServiceResponse;
import com.paymentgateway.gpay.exception.CustomException;
import com.paymentgateway.gpay.request.PaymentRequest;
import org.springframework.http.HttpStatus;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

public class Helper {

    private static final String SECRET_KEY = "PEAL33550099GOEScatriiendKETTLE001UNITED";
    private static final String SALT = "stdsxcitymanjoehhhhh!!!!waya";

    private static Long generateRandom(){
        Random random = new Random();
        char[] digits = new char[9];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < 9; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }
        return Long.parseLong(new String(digits));
    }

    public static String generateSessionId(String bankCode){
       return bankCode.concat(new SimpleDateFormat("yyMMddHHmmss").format(new Date()))
                .concat("000").concat(String.valueOf(generateRandom()));
    }

    private String generateSessionId() {
        long randomNum = (long) Math.floor(Math.random() * 9_000_000_000_00L) + 1_000_000_000_00L;
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss")) + Long.toString(randomNum);
    }


    public static String encrypt(String strToEncrypt) {
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String decrypt(String strToDecrypt) {
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }


    public static String processEncryption(Object data){
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(data);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return encrypt(jsonString);
    }


    public static String processDecryption(String encryptData){

        return decrypt(encryptData);
    }



    public static BankServiceResponse simpleFraudCheck(Account accountDebit){
        try {
            String secureDebit = hashDecrypt(accountDebit.getHashed_no());
            String[] keyDebit = secureDebit.split(Pattern.quote("|"));
            if ((!keyDebit[0].equals(accountDebit.getUser()))
                    || (!keyDebit[2].equals(accountDebit.getAccountNo()))
                    || (!keyDebit[3].equals(accountDebit.getAcct_crncy_code()))) {
                return new BankServiceResponse("12", "DJGO|DEBIT ACCOUNT DATA INTEGRITY ISSUE", null);
            }

            if (accountDebit.getFrez_code() != null) {
                if (accountDebit.getFrez_code().equals("D"))
                    return new BankServiceResponse("12", "DJGO|DEBIT ACCOUNT IS ON DEBIT FREEZE", null);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new BankServiceResponse();

    }


    public static PaymentRequest jsonToObject(String data){
        ObjectMapper objectMapper = new ObjectMapper();
        PaymentRequest paymentRequest = null;
        try {
            paymentRequest = objectMapper.readValue(data, PaymentRequest.class);
            System.out.println(" Payment=>" + paymentRequest);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return paymentRequest;
    }

    public static String generateMastercardNumber() {
        StringBuilder mastercardNumber = new StringBuilder("5"); // Mastercard IIN (Issuer Identification Number) starts with 5

        // Generate remaining 15 digits
        Random random = new Random();
        for (int i = 0; i < 13; i++) {
            mastercardNumber.append(random.nextInt(10)); // Add random digits
        }

        // Add a placeholder for the final digit
        mastercardNumber.append('0');

        // Calculate the Luhn checksum
        int total = 0;
        for (int i = 0; i < mastercardNumber.length(); i++) {
            int digit = Character.getNumericValue(mastercardNumber.charAt(i));
            if (i % 2 == 0) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            total += digit;
        }

        // Find the checksum value that, when added to the total, is a multiple of 10
        int checksum = (10 - (total % 10)) % 10;

        // Replace the placeholder with the calculated checksum
        mastercardNumber.setCharAt(13, (char)(checksum + '0'));

        return mastercardNumber.toString();
    }


    public static String hashEncrypt(String pText) throws Exception {
        String authHash = Base64.getEncoder().encodeToString(pText.getBytes(StandardCharsets.UTF_8));
        return authHash;
    }

    public static String hashDecrypt(String encryptText) throws Exception {
        byte[] asBytes = Base64.getDecoder().decode(encryptText);
        String output = new String(asBytes);
        return output;
    }


}
