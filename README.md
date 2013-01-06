# Java Stream Processor
## A stream reader with filter options

### package se.ernell.java.streamprocessor
Read a stream with an highly speed optimized reader and apply 
multiple search filters like 'begins with', 'ends with', 'contains' 
and some anagramming filters. It is designed to search for words in 
dictionary files. This library will be a part of my Android app 
'Wordlist Pro' that can be found on Google Play <a href="http://play.google.com/store/apps/details?id=com.ernell.wordpro">link</a><br>
The filters are sequentially executed, so if 2 filters are used, the 
second filter gives a subset of the first filter result.
Checkout ExampleRunner.java + StreamSearchExample.java<br>
<i>Note: the library is not yet finished</i>

### package se.ernell.java.OptmizedInputStreamReader
A class that takes any InputStream as argument.
Use a custom object implementing IStreamObjectRecievable together with 
the streamreader. Example is included in the source.
Checkout ExampleRunner.java + MyUrlReaderExample.java
