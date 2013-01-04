package se.ernell.java.time;

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

/**
 * A very simple timer class Example use:
 * 
 * <pre>
 * 	SimpleTimer time = new SimpleTimer();
 * 	time.start();
 * 	// ... some task ...
 * 	time.stop();
 * 
 * 	Output to console when stop() is called.
 * 	Use startN()/stopN() for nanos.
 * </pre>
 * 
 * @author Robert Andersson <rob@ernell.se> <http://www.ernell.se>
 */
public class SimpleTimer {

    private long startTime, endTime;

    public SimpleTimer() {

    }

    /** Start Timer - MilliSeconds */
    public void start() {
	startTime = System.currentTimeMillis();
    }

    /** Start Timer - NanoSeconds */
    public void startN() {
	startTime = System.nanoTime();
    }

    /** Stop Timer - MilliSeconds */
    public void stop() {
	endTime = System.currentTimeMillis();
	System.out.println(toString() + "ms");
    }

    /** Stop Timer - MilliSeconds + custom message */
    public void stop(String message) {
	endTime = System.currentTimeMillis();
	System.out.println(toString() + "ms " + message);
    }

    /** Stop Timer - NanoSeconds */
    public void stopN() {
	endTime = System.nanoTime();
	System.out.println(toString() + "ns");
    }

    /** return time difference */
    public long getTime() {
	return endTime - startTime;
    }

    @Override
    public String toString() {
	long result = getTime();
	return "Time elapsed: " + result;
    }

}
