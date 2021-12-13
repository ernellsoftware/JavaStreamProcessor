import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class ExampleRunner {

    private static final String TEXT_TESTHTTP = "http://www.google.com/";
    private static final String TEXT_TESTFILE1 = "twl.utf8.txt";
    private static final String TEXT_TESTFILE2 = "csw.utf8.txt";

    /**
     * @param args
     */
    public static void main(String[] args) {

	// Uncomment the example to run

	// example_test_url_file();
	// example_test_url_http();
	example_streamsearch();

    }

    public static void example_streamsearch() {
	new StreamSearchExample();
    }

    /**
     * Example use of IStreamObjectRecievable
     */
    public static void example_test_url_file() {

	File file = new File(TEXT_TESTFILE2);
	try {
	    URL url = file.toURI().toURL();
	    MyUrlReaderExample url_reader = new MyUrlReaderExample(url);
	    url_reader.print2Console();
	} catch (MalformedURLException e) {
	    System.out.println("Error! Malformed URL.");
	}

	System.out.println("Test File URL finished.");

    }

    /**
     * Example use of IStreamObjectRecievable
     */
    public static void example_test_url_http() {

	try {
	    URL url = new URL(TEXT_TESTHTTP);
	    MyUrlReaderExample url_reader = new MyUrlReaderExample(url);
	    url_reader.print2Console();
	} catch (MalformedURLException e) {
	    System.out.println("Error! Malformed URL.");
	}

	System.out.println("Test HTTP URL finished.");

    }
}
