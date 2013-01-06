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
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Class describing a configuration for searching
 * 
 * @author rob@ernell.se
 */
public abstract class ProcessorBaseConfiguration implements
	IProcessorConfiguration {

    /** if length min/max of line is to be used */
    // public boolean use_length_settings;

    /** line min/max length */
    public int min_length;
    public int max_length;

    /** result limit */
    public int max_list_size;

    /** name of the url/stream/file */
    public String stream_name;

    /** url of the url/stream/file */
    public URL stream_url;

    /** the size of the streamreader buffer. default=8192 (bytes) */
    public int buffer_size;

    /** the size of the longest line expected to find in the stream */
    public int char_buffer_size;

    /** show a # after the line */
    public boolean unique;

    /** calculate a letterscore */
    public boolean show_score;

    /** use colors */
    public boolean use_colors;

    /** upper/lower case fonts */
    public boolean font_case;

    /** Constructor */
    public ProcessorBaseConfiguration(URL arg_url) {
	stream_url = arg_url;
	setDefaults();
    }

    /** @inheritDoc */
    @Override
    public void setDefaults() {

	// set default values
	buffer_size = 8192;
	char_buffer_size = 1024;
	stream_name = "no_name_stream";
	// use_length_settings = false;
	min_length = 0;
	max_length = 16;
	show_score = false;
	font_case = false;
	unique = false;
	use_colors = false;

    };

    // @Deprecated
    // public void setUseLengthSettings(boolean flag) {
    // use_length_settings = flag;
    // }

    /** @inheritDoc */
    @Override
    public void setMinLength(int min_length) {
	this.min_length = min_length;
    }

    /** @inheritDoc */
    @Override
    public void setMaxLength(int max_length) {
	this.max_length = max_length;
    }

    /** @inheritDoc */
    @Override
    public int getMinLength() {
	return min_length;
    }

    /** @inheritDoc */
    @Override
    public int getBufferSize() {
	return buffer_size;
    }

    /** @inheritDoc */
    @Override
    public int getCharBufferSize() {
	return char_buffer_size;
    }

    /** @inheritDoc */
    @Override
    public int getMaxLength() {
	return max_length;
    }

    /** @inheritDoc */
    @Override
    public void setMaxListSize(int max_list_size) {
	this.max_list_size = max_list_size;
    }

    /** @inheritDoc */
    @Override
    public void setStreamName(String stream_name) {
	this.stream_name = stream_name;
    }

    /** @inheritDoc */
    @Override
    public URL getStreamURL() {
	return stream_url;
    }

    public void setUnique(boolean flag) {
	unique = flag;
    }

    public void setShowScore(boolean show_score) {
	this.show_score = show_score;
    }

    public void setUseColors(boolean use_colors) {
	this.use_colors = use_colors;
    }

    public void setFontCase(boolean font_case) {
	this.font_case = font_case;
    }

    /** convert string to URL */
    public static URL toURL(String string_url) {
	final URL new_url;
	try {
	    new_url = new URL(string_url);
	    return new_url;
	} catch (MalformedURLException e) {
	    System.out.println("MalformedURLException: " + e.getMessage());
	}
	return null;
    }

}
