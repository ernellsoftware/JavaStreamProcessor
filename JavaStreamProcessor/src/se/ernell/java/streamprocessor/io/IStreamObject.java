package se.ernell.java.streamprocessor.io;

/*
 * Copyright (C) 2013 Robert Andersson <http://www.ernell.se>
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
 * Implement this interface to your class. Pass the object when calling the
 * reader. The object's put(...) method will be called from the reader for each
 * new textline found.
 */
public interface IStreamObject {

    public IStreamObject getItem();

    public String getLine();

    public int getLength();

    public int[] getWovCons();

    public int getScore();

}
