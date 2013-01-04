/*
 * This class is derived from Jack Shirazi's fast file reader 
 * found in the book 'Java Performance Tuning'.
 * 
 * 2012 Robert Andersson <http://www.ernell.se>
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
package se.ernell.java.io;

import java.io.IOException;
import java.io.InputStream;

public class OptmizedInputStreamReader {

    private static final char MAP3[] = { '\200', '\201', '\202', '\203', '\204', '\205', '\206', '\207', '\210', '\211', '\212', '\213', '\214', '\215',
	    '\216', '\217', '\220', '\221', '\222', '\223', '\224', '\225', '\226', '\227', '\230', '\231', '\232', '\233', '\234', '\235', '\236', '\237',
	    '\240', '\241', '\242', '\243', '\244', '\245', '\246', '\247', '\250', '\251', '\252', '\253', '\254', '\255', '\256', '\257', '\260', '\261',
	    '\262', '\263', '\264', '\265', '\266', '\267', '\270', '\271', '\272', '\273', '\274', '\275', '\276', '\277', '\300', '\301', '\302', '\303',
	    '\304', '\305', '\306', '\307', '\310', '\311', '\312', '\313', '\314', '\315', '\316', '\317', '\320', '\321', '\322', '\323', '\324', '\325',
	    '\326', '\327', '\330', '\331', '\332', '\333', '\334', '\335', '\336', '\337', '\340', '\341', '\342', '\343', '\344', '\345', '\346', '\347',
	    '\350', '\351', '\352', '\353', '\354', '\355', '\356', '\357', '\360', '\361', '\362', '\363', '\364', '\365', '\366', '\367', '\370', '\371',
	    '\372', '\373', '\374', '\375', '\376', '\377', 0, '\001', '\002', '\003', '\004', '\005', '\006', '\007', '\b', '\t', '\n', '\013', '\f', '\r',
	    '\016', '\017', '\020', '\021', '\022', '\023', '\024', '\025', '\026', '\027', '\030', '\031', '\032', '\033', '\034', '\035', '\036', '\037',
	    ' ', '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', '<',
	    '=', '>', '?', '@', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
	    'Z', '[', '\\', ']', '^', '_', '`', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
	    'w', 'x', 'y', 'z', '{', '|', '}', '~', '\177' };

    /** Windows EOL = CR+LF */
    private final static char CR = 0x0d;// \r
    private final static char LF = 0x0a;// \n

    /** Number of bytes converted each loop */
    private static final int CONVERTER_CHUNK = 10;

    /**
     * Maximum length of a line. Change if you know the lines are longer than default
     */
    private static final int DEFAULT_MAX_LINE_LENGTH = 256;

    /** memory buffer size */
    private static final int DEFAULT_BUFFER_SIZE = 8192;

    static IStreamObjectRecievable object;

    /** read lines from the stream */
    public final static void readLinesFromStream(InputStream is) throws IOException {
	readLinesFromStream(is, DEFAULT_MAX_LINE_LENGTH, DEFAULT_BUFFER_SIZE);
    }

    /** read lines from the stream */
    public final static void readLinesFromStream(InputStream is, int maxLineLength) throws IOException {
	readLinesFromStream(is, maxLineLength, DEFAULT_BUFFER_SIZE);
    }

    public static int nlines = 0;
    public static int tchars = 0;

    public OptmizedInputStreamReader(IStreamObjectRecievable arg_object) {
	object = arg_object;
    }

    protected static void process(char in_buf[], int offs, int length) {

	nlines++;
	tchars += length;

	char[] line = new char[length];
	System.arraycopy(in_buf, offs, line, 0, length);
	object.put(line, length);

    }

    /** Print info to console */
    public void printStats() {
	System.out.println("Chars read from stream: " + tchars + " (linebreaks excluded)");
	System.out.println("Lines read from stream: " + nlines);
    }

    /**
     * Read lines from the stream.
     * One major flaw with this function is that the last line of the content must
     * be terminated with a linebreak.
     * You also have to know the maximum line length of the content.
     */
    public final static void readLinesFromStream(InputStream is, int maxLineLength, int defaultBufferSize) throws IOException {

	if (is == null)
	    throw new IOException("InputStream == null");

	try {

	    // char[] lineBuffer = new char[maxLineLength];
	    char[] char_buffer = new char[defaultBufferSize];
	    byte[] byte_buffer = new byte[defaultBufferSize];

	    char c;
	    int nLeftOver;
	    int length_read;
	    int nStartLineIndex = 0;
	    int nextChar = 0;
	    int nLineLength;

	    // fill buffer
	    int nChars = is.read(byte_buffer, 0, defaultBufferSize);

	    // convert
	    convert(byte_buffer, 0, nChars, char_buffer, 0, nChars, MAP3);

	    boolean bCheckFirst = false;

	    // main loop
	    for (;;) {

		// work through the buffer
		for (; nextChar < nChars; nextChar++) {

		    c = char_buffer[nextChar];
		    if (c == LF || c == CR) {

			// EOL found. do something with the content
			nLineLength = nextChar - nStartLineIndex;
			process(char_buffer, nStartLineIndex, nLineLength);

			if (c == CR) {
			    if (nextChar == nChars - 1)
				bCheckFirst = true;
			    else if (char_buffer[nextChar + 1] == LF)
				nextChar++;
			}
			nStartLineIndex = nextChar;
			nStartLineIndex++;

		    }
		}

		nLeftOver = 0;
		if (nStartLineIndex < nChars) {
		    nLeftOver = nChars - nStartLineIndex;
		    System.arraycopy(char_buffer, nStartLineIndex, char_buffer, 0, nLeftOver);
		}

		// refill the byte buffer from stream
		do {
		    length_read = is.read(byte_buffer, nLeftOver, byte_buffer.length - nLeftOver);
		} while (length_read == 0);

		if (length_read > 0) {

		    nextChar -= nChars;
		    nChars = nLeftOver + length_read;
		    nStartLineIndex = nextChar;

		    convert(byte_buffer, nLeftOver, nChars, char_buffer, nLeftOver, nChars, MAP3);

		    if (bCheckFirst) {
			bCheckFirst = false;
			if (char_buffer[0] == LF) {
			    nextChar++;
			    nStartLineIndex = nextChar;
			}
		    }

		} else {
		    // End of stream. Close it.
		    is.close();
		    return;
		}

	    }// end for

	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private static int convert(byte byte_buffer[], int i, int j, char char_buffer[], int k, int l, char MAP_ARRAY[]) throws Exception {
	int i1 = j;
	boolean flag = false;
	if (j - i > l - k) {
	    i1 = i + l - k;
	    flag = true;
	}
	int j1 = k;
	if (i1 - i > 10) {
	    i1 -= 10;
	    int k1;
	    for (k1 = i; k1 < i1;) {
		char_buffer[j1++] = MAP_ARRAY[byte_buffer[k1++] + 128];
		char_buffer[j1++] = MAP_ARRAY[byte_buffer[k1++] + 128];
		char_buffer[j1++] = MAP_ARRAY[byte_buffer[k1++] + 128];
		char_buffer[j1++] = MAP_ARRAY[byte_buffer[k1++] + 128];
		char_buffer[j1++] = MAP_ARRAY[byte_buffer[k1++] + 128];
		char_buffer[j1++] = MAP_ARRAY[byte_buffer[k1++] + 128];
		char_buffer[j1++] = MAP_ARRAY[byte_buffer[k1++] + 128];
		char_buffer[j1++] = MAP_ARRAY[byte_buffer[k1++] + 128];
		char_buffer[j1++] = MAP_ARRAY[byte_buffer[k1++] + 128];
		char_buffer[j1++] = MAP_ARRAY[byte_buffer[k1++] + 128];
	    }

	    for (i1 += 10; k1 < i1;)
		char_buffer[j1++] = MAP_ARRAY[byte_buffer[k1++] + 128];

	} else {
	    for (int l1 = i; l1 < i1;)
		char_buffer[j1++] = MAP_ARRAY[byte_buffer[l1++] + 128];

	}
	if (flag)
	    throw new Exception();
	else
	    return j1 - k;
    }

}