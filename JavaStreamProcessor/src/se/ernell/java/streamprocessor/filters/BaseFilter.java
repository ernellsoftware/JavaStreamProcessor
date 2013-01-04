package se.ernell.java.streamprocessor.filters;

public abstract class BaseFilter implements IFilter {

    protected String pattern;
    protected int pattern_length;

    public BaseFilter(String arg_pattern, int arg_length) {
	pattern = arg_pattern;
	pattern_length = pattern.length();
    }

    @Override
    public abstract boolean match(char[] line, int line_length);

}
