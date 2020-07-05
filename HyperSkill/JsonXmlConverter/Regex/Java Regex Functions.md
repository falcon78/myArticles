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
 
// Returns a new string.
System.out.println(matcher.replaceAll("#"));   // ab##c##abc#
System.out.println(matcher.replaceFirst("#")); // ab#3c80abc9
```

## Getting match results.

- Don't try to invoke matcher.group() without invoking matcher.find() method first.
```java 
String javaText = "Java supports regular expressions. LET'S USE JAVA!!!";
 
Pattern javaPattern = Pattern.compile("java", Pattern.CASE_INSENSITIVE);
Matcher matcher = javaPattern.matcher(javaText);

// runs twice because there are two matches.
while (matcher.find()) {
    System.out.println(matcher.start()); // first: 0, second: 45
    System.out.println(matcher.group()); // first: java, second: JAVA
}
```

- There is a special object MatchResult that represents all the results together:

```java
MatchResult result = matcher.toMatchResult(); // a special object containing match results
        
System.out.println(result.start()); // 0
System.out.println(result.end());   // 4
System.out.println(result.group()); // "Java"
```

