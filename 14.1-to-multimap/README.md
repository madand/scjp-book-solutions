Solution for exercise 14.1 (ToMultiMap)
====================================

Compile & run
-------------

``` shell
> javac ToMultiMap.java
> java ToMultiMap
```

Sample output
-------------

``` shell
> java ToMultiMap --help
Usage: java ToMultiMap key value [key2 value2]...
```

``` shell
> java ToMultiMap --help a apple b orange c apple d lemon e cherry f apple g lemon h apple
Input map: {a=apple, b=orange, c=apple, d=lemon, e=cherry, f=apple, g=lemon, h=apple}
Multi map: {orange=[b], apple=[a, c, f, h], cherry=[e], lemon=[d, g]}
```
