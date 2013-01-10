# Java Stream Processor
## A stream reader with filter options primarily designed to search for words in dictionary files.

### package se.ernell.java.streamprocessor
Read a stream with a speed optimized reader and apply multiple filters like 'begins with', 'ends with', 'contains' and some anagramming filters. 
This library will be a part of my Android app 'Wordlist Pro' that can be found on Google Play <a target="_blank" href="http://play.google.com/store/apps/details?id=com.ernell.wordpro">link</a><br>
The current version is very speedy, but the code looks like shit. Thats why I had to make this library, to clean things up. With this I can add more features to the app (or add features later more easily) that makes it one of a kind.

The filters are sequentially executed, so if 2 filters are used, the second filter gives a subset of the first filter result.
Checkout ExampleRunner.java + StreamSearchExample.java for some example use.<br>
<i>Note: the library is not yet finished. Work in progress.</i>

### package se.ernell.java.OptmizedInputStreamReader
A class that takes any InputStream as argument.
Use a custom object implementing IStreamObjectRecievable together with 
the streamreader. Example is included in the source.
Checkout ExampleRunner.java + MyUrlReaderExample.java
