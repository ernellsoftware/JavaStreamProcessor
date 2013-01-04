import se.ernell.java.io.IStreamObjectRecievable;

public class AnExampleObject implements IStreamObjectRecievable {

    @Override
    public void put(char[] line, int line_length) {

	// example use: add myString to a database
	String myString = new String(line, 0, line_length);
	System.out.println("ExampleObject: [" + line_length + "] " + myString);

    }

}