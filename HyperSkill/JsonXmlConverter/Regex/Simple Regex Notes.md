# Simple Regex Notes

#### Simple Matching

In java, strings have built-in regex capabilities. Strings have a special method called `matches` that accepts regex as arguments. `matches` methods only return true when the whole strings match.

```java
String aleRegex = "ale";

"ale".matches(aleRegex); // true
"pale".matches(aleRegex); // false , has extra character "p"
"ALE".matches(aleRegex); // false
```

#### The dot character

The dot character matches any single character except `\n`.

```java
String learnRegex = "Learn.Regex";
 
"Learn Regex".matches(learnRegex); // true
"Learn.Regex".matches(learnRegex); // true
"Learn, Regex".matches(learnRegex); // false
```

#### The question mark

Make the character before `?` optional.

```java
String pattern = "behaviou?r";
 
"behaviour".matches(pattern); // true
"behavior".matches(pattern);  // true
```

#### The set of characters

Only match one character from a set.

```java
String pattern = "ab[abc]"; // it matches strings like "aba", "abb", "abc", but not "abd"
 
"abc".matches(pattern); // true
"abd".matches(pattern); // false
```

#### Range of characters

```java
String anyLetterPattern = "[a-zA-Z]"; // matches any letter "a", "b", ..., "A", "B", ...
String anyDigitPattern = "[0-9]"; // matches any digit from 0 to 9
```

#### Excepting characters

Do not match characters ( `^` ).

```java
String regex = "[^abc]";
 
"a".matches(regex); // false
"b".matches(regex); // false
"c".matches(regex); // false
"d".matches(regex); // true
```

#### Alternations

```java
String pattern = "(abc|def|xyz)"; // matches "abc", "def", "xyz", but not "a" or "b"
 
"abc".matches(pattern); // true
```

#### Escape character in Java

Escape character in java is `\\` not `\` like some other languages.

#### Shorthand**s

`\\d`  any digit  `[0-9]`

`\\D`  a non-digit  `[^0-9]` 

`\\s`  a whitespace character including tab and newline  `[ \\t\\n\\x0B\\f\\r]`

`\\S`  a non-whitespace character  `[^\\s]`

`\\w`  an alphanumeric character  `[a-zA-Z_0-9]`

`\\W`  a non-alphanumeric character  `[^\\w]`

`\\b`  a word boundary   `(^\w|\w$|\W\w|\w\W)`

`\\B`  a non-word boundary  `[^\\b]`

```java
String regex = "\\s\\w\\d\\s";
 
" A5 ".matches(regex); // true
" 33 ".matches(regex); // true
"\tA4\t".matches(regex); // true, because it matches tabs as well
 
"q18q".matches(regex); // false, 'q' is not a space
" AB ".matches(regex); // false, 'B' is not a digit
" -1 ".matches(regex); // false, '-' is not an alphanumeric character, but '_' is OK. 
 
String startRegex = "\\bcat"; // Matches the part of the word that starts with "cat"
String endRegex = "cat\\b"; // Matches the part of the word that ends with "cat"
String wholeRegex = "\\bcat\\b"; // Matches the whole word "cat"
```
