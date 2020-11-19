package com.ces.Product.Proto;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;

/**
 * Main application class
 */
public class ProductParser {

    /**
     * Main entry point for the application
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        // process cli args and check if they are valid
        ProcessCliArgs processCliArgs = new ProcessCliArgs();
        String filePath = processCliArgs.run(args);

        // get input csv file and return products proto object
        ParseCsvToProto parseCsvToProto = new ParseCsvToProto();
        ProductProto.Products products = parseCsvToProto.run(filePath);

        try {
            // format products object to json and output to cli
            String str = JsonFormat.printer().print(products);
            System.out.println(str);
        } catch (InvalidProtocolBufferException e) {
            System.err.println(e.getMessage());

            System.exit(1);
        }
    }
}
