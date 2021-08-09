Solution for exercise 12.1 (Simple Grep)
========================================

Compile & run
-------------

``` shell
> javac SimpleGrep.java
> java SimpleGrep
```

Sample output
-------------

``` shell
> java SimpleGrep --help
Usage: java SimpleGrep regexp [inFile]
    If inFile is omitted, the standard input is used.
    The charset used for both input and output is "UTF8".
```

Test with the included test file:
``` shell
> java SimpleGrep '[Bb]e' test.txt
1: [(3,4:be), (16,17:be)]
3: [(2,3:be)]
4: [(0,1:Be), (12,13:be), (22,23:be), (35,36:Be)]
```
