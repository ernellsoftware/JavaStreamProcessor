/*
 * Copyright (C) 2012 Robert Andersson <http://www.ernell.se>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import se.ernell.java.io.IStreamObjectRecievable;
import se.ernell.java.io.OptmizedInputStreamReader;
import se.ernell.java.time.SimpleTimer;

public class MyUrlReaderExample implements IStreamObjectRecievable {

    private URL url;
    private boolean isUrlValid;
    private int line_counter;

    /** Constructor */
    public MyUrlReaderExample(URL arg_url) {
	url = arg_url;
	isUrlValid = true;
	line_counter = 0;
    }

    /** Constructor */
    public MyUrlReaderExample(String string_url) {

	try {
	    url = new URL(string_url);
	    isUrlValid = true;
	} catch (MalformedURLException e) {
	    System.out.println("URL is not valid.");
	    isUrlValid = false;
	}

    }

    /**
     * Read from the URL
     * Here 'this' is sent to the reader and
     * this class implements the IStreamObjectRecievable interface
     */
    public void print2Console() {
	if (isUrlValid) {
	    SimpleTimer time = new SimpleTimer();
	    System.out.println("Attempting to read [" + url.toString() + "]");
	    try {

		InputStream is = url.openStream();

		time.start();
		OptmizedInputStreamReader myOISR = new OptmizedInputStreamReader(this);
		myOISR.readLinesFromStream(is, 4096, 16384);
		time.stop();

		myOISR.printStats();

	    } catch (IOException e) {
		System.out.println("IOException!");
		e.printStackTrace();
	    }
	} else {
	    System.out.println("URL is not valid.");
	}
    }

    @Override
    public void put(char[] line, int line_length) {
	if (isUrlValid) {
	    // String myString = new String(line, 0, line_length);
	    System.out.println(line_counter + ">DATA [" + line_length + "]:" + String.valueOf(line));
	    line_counter++;
	}
    }

    /**
     * @return the url
     */
    public URL getUrl() {
	return url;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(URL url) {
	this.url = url;
    }

}
