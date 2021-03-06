package se.ernell.java.streamprocessor.config;

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
import java.net.URL;

/**
 * Class for a search config with manual settings
 * 
 * @author rob@ernell.se
 */
public class ProcessorConfiguration extends ProcessorBaseConfiguration {

    public ProcessorConfiguration(URL arg_url) {
	super(arg_url);
	setDefaults();

    }

    /**
     * @inheritDoc
     */
    @Override
    public void setDefaults() {
	super.setDefaults();
	// set custom values here

    }

}
