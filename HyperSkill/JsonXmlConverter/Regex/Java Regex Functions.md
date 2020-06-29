# Java String and Regex methods
[[Regex.md]]

## Replacing characters

- Using string method.
```java {cmd} 
String digitRegex = "\\d";
String str = "word123";

String result1 = str.replaceAll(digitRegex, "#")  // word###
String result2 = str.replaceFirst(digitRegex, "#") // word#23
 ```

- Using Matcher method.
```java {cmd} 
Pattern pattern = Pattern.compile("\\d"); // a regex to match a digit
 
String str = "ab73c80abc9"; // a string consisting of letters and digits
 
Matcher matcher = pattern.matcher(str);
 
System.out.println(matcher.replaceAll("#"));   // ab##c##abc#
System.out.println(matcher.replaceFirst("#")); // ab#3c80abc9
```