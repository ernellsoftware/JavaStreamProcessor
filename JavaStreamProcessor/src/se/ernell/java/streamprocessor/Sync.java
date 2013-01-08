package se.ernell.java.streamprocessor;

/**
 * Class used for synchronization & thread object(s)
 * 
 * @author rob@ernell.se
 * 
 */
public class Sync {

    public static final Object lock = new Object();

    public static boolean processing_done;

}
