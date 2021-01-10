## CommandLineInterface

Minimalistic command-line-interface

## Maven

```xml
<dependency>
  <groupId>com.github.hawolt</groupId>
  <artifactId>command-line-interface</artifactId>
  <version>c5aeb3c0cc</version>
</dependency>
```
```xml
<repository>
  <id>jitpack.io</id>
  <url>https://jitpack.io</url>
</repository>
```

## Example Usage

```java
import com.hawolt.Argument;
import com.hawolt.BaseParser;
import com.hawolt.Parser;
import com.hawolt.ParserException;

public class ParserTest {
    /*
     * Program Arguments: '-b val1 --cc val2 -d -g -g --hh "unique non-mandatory value" -e unique-flag'
     */
    public static void main(String[] args) {
        Parser parser = new Parser();
        parser.add(Argument.create("a", "aa", "For a-testing", false, false, false));
        parser.add(Argument.create("b", "bb", "For b-testing", true, false, false));
        parser.add(Argument.create("c", "cc", "For c-testing", true, true, false));
        parser.add(Argument.create("d", "dd", "For d-testing", true, true, true));
        parser.add(Argument.create("e", "ee", "For e-testing", false, true, true));
        parser.add(Argument.create("f", "ff", "For f-testing", false, false, true));
        parser.add(Argument.create("g", "gg", "For g-testing", true, false, true));
        parser.add(Argument.create("h", "hh", "For h-testing", false, true, false));
        try {
            BaseParser base = parser.check(args);
            boolean isG = base.has("g");
            String hh = base.getValue("hh");
        } catch (ParserException e) {
            System.err.println(e.getMessage());
            parser.print();
        }
    }
}
```
