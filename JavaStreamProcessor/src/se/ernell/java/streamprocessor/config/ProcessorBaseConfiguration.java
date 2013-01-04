package se.ernell.java.streamprocessor.config;

import java.net.MalformedURLException;
import java.net.URL;

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
 * Class describing a configuration for searching
 * 
 * @author rob@ernell.se
 */
public abstract class ProcessorBaseConfiguration implements
	IProcessorConfiguration {

    /** if length min/max of line is to be used */
    public boolean use_length_settings;

    /** line min/max length */
    public int min_length;
    public int max_length;

    /** result limit */
    public int max_list_size;

    /** name of the url/stream/file */
    public String stream_name;

    /** url of the url/stream/file */
    public URL stream_url;

    /** the size of the longest line expected to find in the stream */
    public int buffer_size;

    /** show a # after the line */
    public boolean unique;

    /** calculate a letterscore */
    public boolean show_score;

    /** use colors */
    public boolean use_colors;

    /** upper/lower case fonts */
    public boolean font_case;

    public ProcessorBaseConfiguration(URL arg_url) {
	stream_url = arg_url;
	setDefaults();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setDefaults() {
	// set some defaults
	stream_name = "no_name_stream";
	buffer_size = 1024;
	use_length_settings = false;
	min_length = 0;
	max_length = 16;
	show_score = false;
	font_case = false;
	unique = false;
	use_colors = false;
    };

    /**
     * @param use_length_settings
     *            the use_length_settings to set
     */
    public void setUseLengthSettings(boolean use_length_settings) {
	this.use_length_settings = use_length_settings;
    }

    /**
     * @param min_length
     *            the min_length to set
     */
    public void setMinLength(int min_length) {
	this.min_length = min_length;
    }

    /**
     * @param max_length
     *            the max_length to set
     */
    public void setMaxLength(int max_length) {
	this.max_length = max_length;
    }

    /**
     * @return the min_length
     */
    @Override
    public int getMin_length() {
	return min_length;
    }

    /**
     * @return the max_length
     */
    @Override
    public int getMax_length() {
	return max_length;
    }

    /**
     * @param max_list_size
     *            the max_list_size to set
     */
    public void setMaxListSize(int max_list_size) {
	this.max_list_size = max_list_size;
    }

    /**
     * @param stream_name
     *            the stream_name
     */
    public void setStreamName(String stream_name) {
	this.stream_name = stream_name;
    }

    /**
     * @param stream_url
     *            the stream URL
     */
    public void setStreamUrl(URL stream_url) {
	this.stream_url = stream_url;
    }

    /**
     * @return the stream_url
     */
    @Override
    public URL getStream_url() {
	return stream_url;
    }

    /**
     * @param unique
     *            the unique to set
     */
    public void setUnique(boolean unique) {
	this.unique = unique;
    }

    /**
     * @param show_score
     *            the show_score to set
     */
    public void setShowScore(boolean show_score) {
	this.show_score = show_score;
    }

    /**
     * @param use_colors
     *            the use_colors to set
     */
    public void setUseColors(boolean use_colors) {
	this.use_colors = use_colors;
    }

    /**
     * @param font_case
     *            the font_case to set
     */
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
