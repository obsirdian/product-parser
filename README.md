# Product Parser

A java utility to parse product csv files into protobuf objects and output to JSON.

### Requirements
* JDK 1.8

* Maven 3.6.3 (command line or IntelliJ plugin)

* All other dependencies managed by maven

### Development
* Get the code:
    `git clone git@github.com:obsirdian/product-parser.git`
* Open the project in IntelliJ
* In the Maven tool window reload the project to download all dependencies

### Build
These commands can be run from the cli, or the integrated IntelliJ maven plugin.
* `mvn compile` To generate the required protobuf classes
* `mvn install` * will generate the following jar files in `<project-root>/target/`
  * `product-parser-[VERSION].jar`
  * `product-parser-[VERSION]-jar-with-dependencies.jar`

### Usage
`product-parser-[VERSION]-jar-with-dependencies.jar` is portable and can be run anywhere that Java 1.8 is installed.

```
java -jar /path/to/jarfile.jar

Options:
 -f,--file <FILE>   Full path to CSV file to parse.
 -h,--help          Display this help message.
 -v,--version       Display the application version.
````

### Releases
* https://github.com/obsirdian/product-parser/releases/tag/v1.0
