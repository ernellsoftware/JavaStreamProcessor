package se.ernell.java.streamprocessor.comparators;

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

import java.util.Comparator;

import se.ernell.java.streamprocessor.io.IStreamObject;

public class ConsonantDescendingComparator implements Comparator<IStreamObject> {

    @Override
    public int compare(IStreamObject object1, IStreamObject object2) {

	int s1 = object1.getWovCons()[1];
	int s2 = object2.getWovCons()[1];
	if (s2 > s1)
	    return 1;
	else if (s2 < s1)
	    return -1;
	else
	    return object1.getLine().compareTo(object2.getLine());

    };

}
