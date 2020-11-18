package com.ces;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.MissingOptionException;
import org.apache.commons.cli.ParseException;

import java.io.File;

import org.apache.commons.io.FilenameUtils;

public class ProcessCliArgs {

    public String run(String[] args) {
        CommandLine commandLine = parseArguments(args);

        return commandLine.getOptionValue("file");
    }

    private CommandLine parseArguments(String[] args) {
        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine commandLine = null;
        Options options = cliOptions();

        try {
            commandLine = commandLineParser.parse(options, args);

            if (commandLine.hasOption("help") || commandLine.hasOption("version")) {
                cliHelp();

                System.exit(0);
            } else if (commandLine.hasOption("file")) {
                try {
                    String filePath = commandLine.getOptionValue("file");
                    String fileExtension = FilenameUtils.getExtension(filePath);
                    File file = new File(filePath);
                    if (file.isDirectory() || !fileExtension.equals("csv")) {
                        System.err.println("Please input a single file with .csv extension.");

                        System.exit(1);
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());

                    System.exit(1);
                }
            }
        } catch (MissingOptionException | MissingArgumentException e) {
            System.err.println(e.getMessage());
            cliHelp();

            System.exit(1);
        } catch (ParseException e) {
            System.err.println("Unknown Error. Failed to process cli arguments.");
            System.err.println(e.toString());
            cliHelp();

            System.exit(1);
        }

        return commandLine;
    }

    private Options cliOptions() {
        Options options = new Options();
        OptionGroup optionGroup = new OptionGroup();

        optionGroup.addOption(
                Option.builder("f")
                        .longOpt("file")
                        .desc("Full path to CSV file to parse.")
                        .hasArg()
                        .argName("FILE")
                        .build()
        );
        optionGroup.addOption(
                Option.builder("h")
                        .longOpt("help")
                        .desc("Display this help message.")
                        .build()
        );
        optionGroup.addOption(
                Option.builder("v")
                        .longOpt("version")
                        .desc("Display the application version.")
                        .build()
        );

        optionGroup.setRequired(true);
        options.addOptionGroup(optionGroup);

        return options;
    }

    private void cliHelp() {
        Options options = cliOptions();
        HelpFormatter helpFormatter = new HelpFormatter();

        String header = "Java App to parse a Product CSV file and output JSON.\n\nOptions:";
        String footer = "\nVersion: 1.0\nSource & Docs: https://github.com/obsirdian/product-parser";
        helpFormatter.printHelp("ProductParser", header, options, footer, true);
    }
}
