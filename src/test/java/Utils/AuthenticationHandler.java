package Utils;

import org.apache.commons.codec.binary.Base64;

public class AuthenticationHandler {

    public static String getEncodeCredentialString(String email, String token){
        if(email == null || token == null){
            throw new IllegalArgumentException("[ERROR] - email and token can NOT be NULL");
        }
        String cred = email.concat(":").concat(token);
        byte[] encodedCred = Base64.encodeBase64(cred.getBytes());
        return new String(encodedCred);
    }
}
