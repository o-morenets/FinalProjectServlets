package ua.training.admission.security;

import org.apache.log4j.Logger;
import ua.training.admission.view.Messages;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * EncryptPassword
 */
public class EncryptPassword {
    /**
     * Logger for logging errors
     */
    private static final Logger LOG = Logger.getLogger(EncryptPassword.class);

    /**
     * Algorithm name used for encryption
     */
    private static final String ALGORITHM = "MD5";
    private static final int RADIX = 16;
    private static final int SUFFIX = 0xff;
    private static final int PREFIX = 0x100;

    /**
     * Encryption method
     *
     * @param passwordToHash password which will be hashed
     * @return encrypted password
     */
    public static String encrypt(String passwordToHash) {
        String generatedPassword;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance(ALGORITHM);
            md.update(passwordToHash.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & SUFFIX) + PREFIX, RADIX).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            LOG.error(Messages.ERROR_ENCRYPT_ALGORITHM);
            throw new RuntimeException(e);
        }
        return generatedPassword;
    }
}
