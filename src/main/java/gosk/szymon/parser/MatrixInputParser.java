package gosk.szymon.parser;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MatrixInputParser<T> extends AbstractInputParser<List<List<List<T>>>, String, T> {

    @Override
    public List<List<List<T>>> parse(InputStream inputStream, Function<String, T> mapper) throws IOException {
        List<List<List<T>>> out = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            List<List<T>> entry = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                if(StringUtils.isBlank(line) || line.startsWith("#")) {
                    continue;
                }
                line = line.trim();
                if (line.startsWith("{")) {
                    entry = new ArrayList<>();
                } else if (line.startsWith("}")) {
                    out.add(entry);
                } else {
                    entry.add(parseLine(line, mapper));
                }
            }
        }
        return out;
    }

    private List<T> parseLine(String line, Function<String, T> mapper) {
        return Arrays.stream(StringUtils.substringBetween(line, "[", "]").split(","))
                .map(String::trim)
                .map(mapper)
                .collect(Collectors.toList());
    }

}
