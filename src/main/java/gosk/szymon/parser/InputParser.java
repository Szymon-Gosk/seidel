package gosk.szymon.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Function;

public interface InputParser<T, U, R> {

    T parse(InputStream inputFile, Function<U, R> mapper) throws IOException;

}
