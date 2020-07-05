# Patterns and Matcher
[[Regex.md]]

# Packages

- java.util.regex.Pattern
- java.util.regex.Matcher

# Regex match until
- Match until a.
    - `.+?(?=a)`

# Matching a regex

```java
String text = "We use Java to write modern applications.";
Pattern pattern = Pattern.compile(".*[Jj]ava.*");
Matcher matcher = pattern.matcher(text);

boolean matches = matcher.matches(); // true
```

- If you write it this way the regex will have to be compiled every time you use it.

```java
Pattern.matches(".*[Jj]ava.*", "We use Java to....")
```

# Pattern flags

- Example (Match case insensitive)

```java
Pattern pattern = Pattern.compile(".*java.*", Pattern.CASE_INSENSITIVE)
```

# Match case insensitive (Regex)

```java
Pattern.matches("(?i)java", "JaVa") // true, because case insensitive.
```

## Matches and Find methods.

```java
String text = "Regex is a powerful tool for programmers";

Pattern pattern = Pattern.compile("tool");
Matcher matcher = pattern.matcher(text);

System.out.println(matcher.matches()); // false, must match whole string.
System.out.println(matcher.find()); // true, substring match is sufficient.
```
