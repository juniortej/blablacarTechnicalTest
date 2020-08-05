package com.junior.technical.lawn.parser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class MowerFileReader {
    public Stream<String> getLines(URL url) throws URISyntaxException, IOException {
        return Files.lines(Paths.get(url.toURI()));
    }
}
