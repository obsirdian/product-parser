package com.ces.Product.Proto;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;

public class ProductParser {

    public static void main(String[] args) {
        ProcessCliArgs processCliArgs = new ProcessCliArgs();
        String filePath = processCliArgs.run(args);

        ParseCsvToProto parseCsvToProto = new ParseCsvToProto();
        ProductProto.Products products = parseCsvToProto.run(filePath);

        try {
            String str = JsonFormat.printer().print(products);
            System.out.println(str);
        } catch (InvalidProtocolBufferException e) {
            System.err.println(e.getMessage());

            System.exit(1);
        }
    }
}
