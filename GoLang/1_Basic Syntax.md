# Basic Syntax

### Packages

Every program starts with its package name. Programs start running in package `main` .

```go
package main
```

### Importing Packages

You can import function and codes from other packages using `import`. Like so

```go
import (
	"fmt"
  	"math/rand"
)
```

```go
import "fmt"
```

### Exported Names

In go, a name is exported only if it Begins with a capital letter. Ex. `func Export()`

When importing a package, you can only access its exported names (those that begin with a capital letter)

### Functions

```go
func add(x int, y int) int {
	return x+y
}
```

If two or more consecutive function parameters share same type, they can be omitted from all but the last parameter.

```go
func add(x, y int) int {
	return x + y
}
```

Functions can return **any** numbers of results.

```go
package main 

import "fmt"

func swap(x, y string) (string, string) {
	return y,x
}

func main (){
  a, b := swap("hello", "world")
  fmt.Println(a,b)
}
```

##### Named return value and naked return

Return values of a function may be named. If so they are treated as variable defined at the top of the function. Named values can be returned with `return` statement without any arguments.

```go
func doubleTriple(num int) (double, triple int) {
	double = num * 2;
  	triple = num * 3;
  	return;
}

func main() {
  fmt.Print(doubleTriple(3)); // 6, 9
}
```

### Declaring Variables

Variables can be declared with the `var` keyword. Type comes after the variable name just like function parameters. You can also omit the type from all but the last variable if two or more consecutive variables are of same type.

A `var` statement can be package level or function level.

```go
package main 
import "fmt"

var c, python, java bool

func main() {
	var i int
}
```

When initializing `var` , type can be omitted

```go
var i int
var i, j, k int = 1,2,3
// omit type
var i = 1
var i, j, k = 1,2,3
var c, python, java = true, false, "no!"
```

##### Short variable declarations (Inside a function)

Only inside a function, the short variable declaration `:=` can be used instead of `var`.

```go
func main() {
  //var i = 0
  i := 0
}
```

`var` declarations can be "factored" into blocks, just like with import.

```go
var (
	ToBe   bool       = false
	MaxInt uint64     = 1<<64 - 1
	z      complex128 = cmplx.Sqrt(-5 + 12i)
)
```

### Basic Types

```go
bool

string

int  int8  int16  int32  int64
uint uint8 uint16 uint32 uint64 uintptr

byte // alias for uint8

rune // alias for int32
     // represents a Unicode code point

float32 float64

complex64 complex128
```

```go
func main() {
	fmt.Printf("Type: %T Value: %v\n", ToBe, ToBe)
	fmt.Printf("Type: %T Value: %v\n", MaxInt, MaxInt)
	fmt.Printf("Type: %T Value: %v\n", z, z)
}
```

##### Get type of an object

```go
import "reflect"

func main() {
  a := 'a'
  t := reflect.TypeOf(a)
}
```

##### Zero Values

Variables declared without being initialized are implicitly given their zero value.

`0` for numeric types, `false` for boolean type, `""` for strings

### Type conversions

`T(v)` expression can be used to convert the value `v` to type `T`

```go
i := 42
f := float64(i)
u := uint(f)
```

There is no implicit type conversion in go.

### Constants

I still don't know enough about this topic, read here : [https://blog.golang.org/constants](https://blog.golang.org/constants)

Constants are declared like variables, with `const` keyword. Constants can be character, string, or numeric values. They cannot be declared with `:=`. You don't have to define type for `const` , it is inferred by its context. Context without a defined type is `untyped` and is converted to the type it is assigned to.

```go
const Pi = 3.14
const World = "Hello World"
```

### Numeric constants

Numeric constants are high-precision values. Untyped constants takes the type required by the context. Untyped constant can hold huge numbers, but you cant assign it or print it if it doesn't fit in the assigned variable’s type.

```go
const (
	// Create a huge number by shifting a 1 bit left 100 places.
	// In other words, the binary number that is 1 followed by 100 zeroes.
  	// This number is so big, you cant even fit it in int64, unint64, or float64 ..etc
	Big = 1 << 100
	// Shift it right again 99 places, so we end up with 1<<1, or 2.
	Small = Big >> 99
	pi = 3.14
)

func main() {
  a := Big * 0.0001 // OK
  b := Big // Bad !!!! Overflows
}
```

The value of `math.Pi` is `3.14159265358979323846264338327950288419716939937510582097494459` which is so big it doesn’t even fit in float64. So when assigning `math.Pi` to a variable the value is truncated. Having so many digits means that operations like `math.Pi / 2` can carry more precision until the result is assigned.