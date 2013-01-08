package se.ernell.java;

/**
 * Here we define various character sets to be used.<br>
 * Example:
 * 
 * <pre>
 * public final static String my_custom_alphabet = &quot;ABCDEFGHIJKLMNOPQRSTUVWXYZÅÄÖ&quot;;
 * </pre>
 * 
 * @author rob@ernell.se
 * 
 */
public class Alpha {

    /** [A-Z] */
    public final static String SET_UC_ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZÅÄÖ";

    /** [a-z] */
    public final static String SET_LC_ALPHA = "abcdefghijklmnopqrstuvwxyz";

    /** [a-z,A-Z,1-9,*,?] */
    public final static String SET_UC_ALPHA_AQ19 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ*?123456789";

    /** [a-z,A-Z,1-9,?] */
    public final static String SET_UC_ALPHA_Q19 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ?123456789";

    /** [0-9] */
    public final static String SET_NUM = "0123456789";

}
