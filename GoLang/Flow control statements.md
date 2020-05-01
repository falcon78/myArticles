# Flow control statements
#goLang/basics/flow-control-statemets

### Basic for loop

You don't need to write parenthesis in the for loop and `{}` are always required.

```go
package main
import "fmt"

func main() {
  sum := 0;
  for i := 0; i < 10; i++ {
  	sum += i;
  }
  fmt.Println(sum)
}
```

### `For` is go's `while`

Go does not have a `while` statement. You can, however, use for as a replacement for `while`. And you can drop the semicolons!

```go
func whileInFor() {
  sum := 1
  for sum < 1000 {
  	sum += sum
  }
}
```

And for infinite loop you can do the following:

```go
for {
  // infinite loop
}
```

### If

Go's `if` statements are like `for` loops; you don't need parenthesis and `{}` is mandatory.

```go
if a == 0 {
}
```

##### If with a short statement

You can start `if` with a short statement.

```go
func pow(x, n, lim float64) float64 {
	if v := math.Pow(x, n); v < lim {
		return v
	}
	return lim
}
```

Variables declared inside `if` statement are also accessible inside `else` blocks.

```go
func isEven(num int) (string) {
  if num % 2 == 0 {
    a := true
  } else {
  	a = false
  }
  return a;
}
```

### Switch

Unlike other programming go `switch` statements don't need `break`

```go
func main() {
  fmt.Print("Go runs on")
  
  switch os := runtime.GOOS; os {
    case "darwin":
    	fmt.Println("OS X.")
    case "linux":
    	fmt.Println("Linux.")
  	default:
    	fmt.Printf("%s. \n", os)
  }
}
```

##### Switch with no condition

`Switch` with no condition is same as `switch true`

```go
func main() {
	t := time.Now()
	switch {
	case t.Hour() < 12:
		fmt.Println("Good morning!")
	case t.Hour() < 17:
		fmt.Println("Good afternoon.")
	default:
		fmt.Println("Good evening.")
	}
}
```

### Defer

A `defer` statement defers the execution of a function until the surrounding function returns. The deferred call's arguments are evaluated immediately.

```go
func main() {
  defet fmt.Println("world")
  
  fmt.Println("hello")
}
```

##### Stacking defers

Deferred function calls are pushed onto the stack and executed in last-in-first-out order.

```go
func main() {
  for i := 0; i < 10; i++ {
    defer fmt.Print(i)
  }
  fmt.Print("done ")
}

// above function prints the following:
"done 9 8 7 6 5 4 3 2 1"
```
