package com.junior.technical;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.junior.technical.exception.SimulationException;
import com.junior.technical.lawn.LawnMowingSimulation;
import com.junior.technical.lawn.builder.LawnBuilder;
import com.junior.technical.lawn.parser.MowerFileReader;


public class Application {

    private final LawnBuilder lawnBuilder;

    public Application(LawnBuilder lawnBuilder) {
        this.lawnBuilder = lawnBuilder;
    }

    public void run(String strPath) {
        MowerFileReader fileReader = new MowerFileReader();
        URL systemResource = ClassLoader.getSystemResource("file-test.txt");
        if (strPath != null && !strPath.isEmpty()) {
            try {
                if (strPath.startsWith("classpath:")) {
                    systemResource = ClassLoader.getSystemResource(strPath.substring(10));
                } else {
                    systemResource = new File(strPath).toURL();
                }
            } catch (MalformedURLException ex) {
                throw new SimulationException("Couldn't find file or path : " + strPath);
            }
        }
        try (Stream<String> lines = fileReader.getLines(systemResource)) {
            LawnMowingSimulation simulation = lawnBuilder.buildSimulation(lines.collect(Collectors.toList()));
            simulation.startSimulation();
        } catch (IOException | URISyntaxException ex) {
            throw new SimulationException("Couldn't find file: " + strPath, ex);
        }
    }

    public static void main(String[] args) {
        String filePath = null;
        if (args.length > 0) {
            filePath = args[0];
        }

        new Application(new LawnBuilder()).run(filePath);
    }

}
