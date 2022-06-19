package gosk.szymon.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Function;

public abstract class AbstractInputParser<T, U, R> implements InputParser<T, U, R> {

    @Override
    public abstract T parse(InputStream inputFile, Function<U, R> mapper) throws IOException;

    public InputStream loadResource(String path) {
        return AbstractInputParser.class.getClassLoader().getResourceAsStream(path);
    }

}
