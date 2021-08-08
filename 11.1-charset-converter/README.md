Solution for exercise 11.1 (Charset Converter)
==============================================

Compile & run
-------------

``` shell
> javac CharsetConverter.java Main.java
> java Main
```

Sample output
-------------

``` shell
> java Main --help
Usage: java Main [inCharset] [outCharset] [inFile] [outFile]
If you want to skip an argument, specify "" as its value.
The default values for arguments are: 
  inCharset   = 8859_1
  outCharset  = UTF8
  inFile      = standard input
  outFile     = standard output
```

Test converting from `UTF8` to `CP1251`:
``` shell
> java Main utf8 cp1251 example-utf8.txt output-cp1251.txt
# No output, but the output-cp1251.txt file gets created.
# Now we can use md5 checksums to esnusure the output is right:
> md5sum example-cp1251.txt output-cp1251.txt
023c5c67eb9379a2754129d7d928780c  example-cp1251.txt
023c5c67eb9379a2754129d7d928780c  output-cp1251.txt
```

Test converting from `CP1251` to `UTF8`:
``` shell
> java Main cp1251 utf8 example-cp1251.txt output-utf8.txt
# No output, but the output-utf8.txt file gets created.
# Now we can use md5 checksums to esnusure the output is right:
> md5sum example-utf8.txt output-utf8.txt
81e61cb53c540d03a58549dcab8f7403  example-utf8.txt
81e61cb53c540d03a58549dcab8f7403  output-utf8.txt
```
