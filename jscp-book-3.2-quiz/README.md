Solution for exercise 3.2 (Quiz)
============================================================================================

Compile & run
-------------

``` shell
> javac -d ./out/ ./src/net/madand/Main.java
> java -cp ./out/ net.madand.Main <arguments>
```

Substitute `<arguments>` with the actual answers for questions. Answers are
processed in a case insensitive manner (e.g. `a` and `A` are treated as equal).

Sample output
-------------

``` shell
> java -cp ./out/ net.madand.Main c b b d b c a x
| Question | Submitted Ans. | Correct Ans. | Result     |
|        1 |              C |            C | CORRECT    |
|        2 |              B |            A | WRONG      |
|        3 |              B |            B | CORRECT    |
|        4 |              D |            D | CORRECT    |
|        5 |              B |            B | CORRECT    |
|        6 |              C |            C | CORRECT    |
|        7 |              A |            C | WRONG      |
|        8 |              X |            A | UNANSWERED |
No. of correct answers:      5
No. of wrong answers:        2
No. of questions unanswered: 1
The candidate PASSED.
```
