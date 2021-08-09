Solution for exercise 12.2 (CSV Reader)
=======================================

Compile & run
-------------

``` shell
> javac CSVReader.java
> java CSVReader
```

Sample output
-------------

``` shell
> java CSVReader --help
Usage: java CSVReader csvFile numOfFields
    csvFile      is an input file in CSV format.
    numOfFields  is a number of fields on every line in the csvFile.
The charset used for both input and output is "UTF-8".
```

Test with the included test file:
``` shell
> java CSVReader test.txt 3
[2.5, 25, 250]
[Hi, Hello, Howdy]
[2008, 2009, 2010]
[one, two, three]
```

Test with the included invalid test file:
``` shell
> java CSVReader test-invalid.txt 3
[2.5, 25, 250]
Error: Line 2 has an invalid number of fields.
```
