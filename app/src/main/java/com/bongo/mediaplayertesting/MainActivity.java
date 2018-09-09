package com.bongo.mediaplayertesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import com.scottyab.aescrypt.AESCrypt;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {
    private static final String ALGO = "AES";

    static byte[] IV = "0a03nb83bbo39s8x".getBytes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //encryptdata();
        try {
            encrypt("Bongo","BDFHJLNPpnljhfdb");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String Data,String secretKeyPhrase) throws Exception {

        Key key = new SecretKeySpec(secretKeyPhrase.getBytes(), ALGO);

        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = Base64.encodeToString(encVal, Base64.DEFAULT);// new BASE64Encoder().encode(encVal);
        encryptedValue = URLEncoder.encode(encryptedValue);
        Log.d("Encryptedvalue",encryptedValue);
        return encryptedValue;
    }

   public void encryptdata(){
       byte[] encrpt= new byte[0];
       try {
           encrpt = "Bongo".getBytes("UTF-8");
       } catch (UnsupportedEncodingException e) {
           e.printStackTrace();
       }
       String base64 = Base64.encodeToString(encrpt, Base64.DEFAULT);

       Log.d("Encryptedvalue",base64);
   }


    private String sendtoEncript(String saltKey, String value) {


        byte[] valuebyte = value.getBytes();
        byte[] saltKeybyte = saltKey.getBytes();

        String encodedString = "";

        try {


            SecretKeySpec key = new SecretKeySpec("BDFHJLNPpnljhfdb".getBytes("UTF-8"), "AES");  // Getting the secretkeyspec by saltkey
            //Log.d("encrypted with IV", AESCrypt.encrypt(key, IV, valuebyte).toString());


            AESCrypt.encrypt(key,IV, valuebyte);  //Library Method for AES Encryption

            //Log.d("encrypted with IV base", portalID);

            encodedString =  URLEncoder.encode(Base64.encodeToString(AESCrypt.encrypt(key, IV, valuebyte),
                    Base64.NO_WRAP), "UTF-8");
                    /* Base64.encodeToString(AESCrypt.encrypt(key, IV, valuebyte),
                    Base64.NO_WRAP);*/ //Converting the Encrpted String to Base64




        } catch (Exception e) {
            e.printStackTrace();
        }

        return encodedString;
    }

}
