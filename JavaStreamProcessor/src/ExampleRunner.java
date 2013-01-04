import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class ExampleRunner {

    private static final String TEXT_TESTHTTP = "http://www.google.com/";
    private static final String TEXT_TESTFILE1 = "twl.utf8.txt";
    private static final String TEXT_TESTFILE2 = "csw.utf8.txt";
    private static long totalTime = 0;
    public static boolean done = false;

    /**
     * @param args
     */
    public static void main(String[] args) {

	// Uncomment the example to run
	// example_test_url_file();
	// example_test_url_http();
	// example_temperature();
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

    /**
     * Example use of Temperature Library
     */
    /*
     * public static void example_temperature() { Temperature t1 =
     * Temperature.fromCelsius(-273.15); Temperature t2 =
     * Temperature.fromKelvin(0.0); System.out.println("Kelvin degrees    : " +
     * t1.toKelvin() + "\n" + "Celsius degrees   : " + t1.toCelsius() + "\n" +
     * "Delisle degrees   : " + t1.toDelisle() + "\n" + "Newton degrees    : " +
     * t1.toNewton() + "\n" + "Rankine degrees   : " + t1.toRankine() + "\n" +
     * "Reaumur degrees   : " + t1.toReaumur() + "\n" + "Fahrenheit degrees: " +
     * t1.toFahrenheit() + "\n" + "Romer degrees     : " + t1.toRomer() + "\n");
     * System.out.println("Kelvin degrees    : " + t2.toKelvin() + "\n" +
     * "Celsius degrees   : " + t2.toCelsius() + "\n" + "Delisle degrees   : " +
     * t2.toDelisle() + "\n" + "Newton degrees    : " + t2.toNewton() + "\n" +
     * "Rankine degrees   : " + t2.toRankine() + "\n" + "Reaumur degrees   : " +
     * t2.toReaumur() + "\n" + "Fahrenheit degrees: " + t2.toFahrenheit() + "\n"
     * + "Romer degrees     : " + t2.toRomer() + "\n"); }
     */

}