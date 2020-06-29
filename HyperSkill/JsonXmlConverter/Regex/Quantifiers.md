# Quantifiers
[[Regex.md]]

* Quantifiers define how often a character can occur in a sequence.
* It should be written after a regular or special character.

## The list of quantifiers

* `+` matches one or more repetitions of the preceding character;
* `*` matches zero or more repetitions of the preceding character;
* `{n}` matches exactly n repetitions of the preceding character;
* `{n,m}` matches at least n but not more than m repetitions of the preceding character;
* `{n,}` matches at least n repetitions of the preceding character;

# The plus quantifier

```java
String regex = "ca+b";
 
"cab".matches(regex); // true
"caaaaab".matches(regex); // true
"cb".matches(regex); // false, because it does not have at least one repetition of 'a'
```

# The star quantifier

```java
String regex = "A[0-3]*";
 
"A".matches(regex);  // true, because it matches zero or more repetitions
"A0".matches(regex); // true
"A000111222333".matches(regex); // true
```

# Specifying the number of repetitions

```java
String regex = "[0-9]{4}"; // four digits
"6342".matches(regex);  // true, because its a 4-digit number.
"182".matches(regex);   // false, because its a 5-digit number.

String regex = "ab{4,}";
"abb".matches(regex); // false, not enough 'b'
"abbbb".matches(regex); // true
"abbbbbbb".matches(regex); // true
```
# Question
There is a set of rules for identifiers (names) of variables:

    It can include lower and upper letters, digits and the underscore character _;
    It can only start with a letter or underscore;
    If an identifier starts with the underscore, the second character must be a letter or digit, but not the underscore;

```java
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfVariables = Integer.parseInt(scanner.nextLine());

        String regex = "(^_{1,1}[a-zA-Z0-9]+)|^[a-zA-Z]+[0-9]*_{0,}.*";

        for (int i = 0; i < numberOfVariables; i++) {
            String identifier = scanner.nextLine();
            if (!identifier.matches(regex)) {
                System.out.println(identifier);
            }
        }
    }
}

