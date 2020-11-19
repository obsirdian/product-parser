package com.ces.Product.Proto;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ParseCsvToProto {

    public ProductProto.Products run(String args) {
        ProductProto.Products.Builder products = ProductProto.Products.newBuilder();

        try {
            BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(args));
            CSVReader csvReader = new CSVReaderBuilder(bufferedReader).build();

            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {

                int columnCount = nextLine.length;
                if (columnCount != 4) {
                    System.err.println("Line number: " + csvReader.getLinesRead() + " has " + columnCount + "columns, there should be 4.");

                    System.exit(1);
                }

                ProductProto.Product.Builder product = ProductProto.Product.newBuilder();
                int i = 0;

                for (String e : nextLine) {
                    switch (i) {
                        case 0:
                            product.setProductID(Integer.parseInt(e));
                        case 1:
                            product.setCatalog(e);
                        case 2:
                            product.setStockcode(e);
                        case 3:
                            product.setProductDescription(e);
                    }

                    i++;
                }

                products.addProducts(product);
            }

            csvReader.close();
        } catch (Exception e) {
            System.err.println(e.toString());

            System.exit(1);
        }

        return products.build();
    }
}
