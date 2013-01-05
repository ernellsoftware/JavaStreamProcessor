package se.ernell.java.streamprocessor.filters;

public abstract class BaseFilter implements IFilter {

    /** pattern to look for */
    protected String pattern;

    /** length of the pattern to look for */
    protected int pattern_length;

    /** Constructor */
    public BaseFilter(String arg_pattern, int arg_length) {
	pattern = arg_pattern;
	pattern_length = pattern.length();
    }

    /** @inheritDoc */
    @Override
    public abstract boolean match(char[] line, int line_length);

}
