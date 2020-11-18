package com.ces;

public class ProductParser {

    public static void main(String[] args) {
        ProcessCliArgs processCliArgs = new ProcessCliArgs();
        String filePath = processCliArgs.run(args);
    }
}
