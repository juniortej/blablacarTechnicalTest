package com.junior.technicaltest.parser;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.junior.technical.lawn.parser.MowerFileReader;

public class MowerFileReaderTest {
    @Test
    public void should_read_file_from_classpath() throws Exception {
        // GIVEN
        MowerFileReader reader = new MowerFileReader();

        // WHEN

        // THEN
        String file = reader.getLines(ClassLoader.getSystemResource("file-test.txt")).collect(Collectors.joining());
        assertThat(file).contains("5 5", "1 2 N", "3 3 E");
    }
}
