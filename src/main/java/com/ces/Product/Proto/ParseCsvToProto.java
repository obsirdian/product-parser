package com.ces.Product.Proto;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Class to parse csv and build proto objects
 */
public class ParseCsvToProto {

    /**
     * Parse the csv and build product object(s)
     *
     * @param args String Full path to the csv file
     * @return ProductProto.Products object
     */
    public ProductProto.Products run(String args) {
        // get products proto builder
        ProductProto.Products.Builder products = ProductProto.Products.newBuilder();

        try {
            // open csv file for reading
            BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(args));
            CSVReader csvReader = new CSVReaderBuilder(bufferedReader).build();

            String[] nextLine;
            // iterate through lines in csv file
            while ((nextLine = csvReader.readNext()) != null) {

                // if the row doesn't have exactly 4 columns print error and exit
                int columnCount = nextLine.length;
                if (columnCount != 4) {
                    System.err.println("Line number: " + csvReader.getLinesRead() + " has " + columnCount + "columns, there should be 4.");

                    System.exit(1);
                }

                // individual product proto object
                ProductProto.Product.Builder product = ProductProto.Product.newBuilder();
                int i = 0;

                // populate fields of product object
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

                // add product to products object
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
