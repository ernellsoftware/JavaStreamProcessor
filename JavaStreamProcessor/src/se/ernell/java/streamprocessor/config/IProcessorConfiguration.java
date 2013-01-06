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

public interface IProcessorConfiguration {

    /** Set default values for the configuration */
    public void setDefaults();

    /** return the URL of the stream */
    public URL getStreamURL();

    /** return minimum length of lines to find */
    public int getMinLength();

    /** set minimum length of lines to find */
    public void setMinLength(int min_length);

    /** return maximum length of lines to find */
    public int getMaxLength();

    /** set maximum length of lines to find */
    public void setMaxLength(int max_length);

    /** return size of the streamreader char-buffer where a line must fit */
    public int getCharBufferSize();

    /** return size of the streamreader buffer */
    public int getBufferSize();

    public void setMaxListSize(int max_list_size);

    public void setStreamName(String stream_name);

}
