# More types: structs, slices, and maps
#golang/basics/pointers-structs-slices-maps

# Pointers
The type `*T` holds the memory address of a value. The zero value of a pointer is `nil`.
```go
var p *int
```

The `&` the operator generates a pointer to its operand.
```go
i := 42;
pointer = &i;
```

The `*` operator denotes the pointer's underlying value.
```go
fmt.Println(*p) // read i through the pointer p
*p = 20 // set i through the pointer p
```
Unlike `C`, go has no pointer arithmetic.

# Structs
A `struct` is a collection of fields.
```go
type Vertex struct {
	X int
	Y int
}

func main() {
  v := Vertex{1,2}
  fmt.Println(v)
}
```

You can access a struct field with a dot.
```go
v := Vertex{1,2}
v.X = 4
fmt.Println(v.Y)
```

### Pointers to struct
To access to a struct member through pointer you can use `(*p).X` or to make it simple you could just do `p.X` without explicit dereference.
```go
v := Vertex{1,2}
p := &v
fmt.Println(p.X) // 1
p.Y = 90
```

### Struct literals
Manually assigning to structs
```go
v1 := Vertex{X: 1, Y: 2}
v2 := Vertex{X: 1} // Y ==> 0 (default value is automatically assigned)
v3 := Vertex{} // X: 0, Y: 0
```
The prefix `&` return pointer to the struct value.
```go
pointer := &Vertex{1,2}
```

# Arrays
Arrays cannot be resized.
```go
var a [2]string
a[0] = “Hello”
a[1] = “World”
fmt.Println(a)
```
```go
primes := [6]int{2,3,5,7,11,13}
primes := [...]int{2,3,5,7,11,13} // auto count length
```

# Slices
[Go Slices: usage and internals - The Go Blog](https://blog.golang.org/slices-intro)

Slices are dynamically sized, flexible view into the elements of an array. A slice if formed by specifying start and end indices. `a[low:high]`  The last element is excluded.
```go
primes := [5]{2,3,5,7,11}

var s []int = primes[1:4]
fmt.Println(s) // 3,5,7
```

### Slices are like references to arrays
A slice does not store any data, but holds a reference to the underlying array. Changes to slices will also propogate to the underlying arrays. ~Other slices that share the same array will also see those changes.~

### Slice literals
A  slice literal is like an array literal without the length.
```go
[3]bool{true, false, true, false} // array literal
```
```go
[]bool{true, true, false} // slice literal
```
Struct literal with slice literal
```go
s := []struct { 
	i int
	b bool
}{
	{2, true},
	{3, false}
}

fmt.Println(s)
```

### Slice defaults
When slicing you can omit the high or low bounds.
For the array `var a [10]int` these slices are equivalent.
```go
a[0:10]
a[:10]
a[0:]
a[:]
```

### Slice length and capacity.
A slice has length and capacity. 
`len(s)` - Number of elements a slice contains.
`cap(s)` - Number of elements in underlying array, ~counting from the first element in slice.~
```go
s := []int{2, 3, 5, 7, 11, 13} // len=6, cap=6
s = s[:0] // len=0, cap=6
s = s[:4] // len=4, cap=6
s = s[2:] // len=2, cap=4
```

### Nil Slices
The zero value of a slice is `nil`
```go
var s []int // len = 0, cap = 0
```

### Creating slices with make
The `make` function allocates zeroed array and returns a slice that refers to that array.
```go
a := make([]int, 5) // len=5, cap=5 // [0,0,0,0,0]
```
To specify a capacity, pass a third argument.
```go
a := make([]int, 0, 5) // len=0, cap=5, []
```

### Slices of slices
Slices may contain any type, including other slices.
```go
board := [][]string{
	[]string{“_”,”_”,”_”},
	[]string{“_”,”_”,”_”},
	[]string{“_”,”_”,”_”},
}

fmt.Print(board) // [[_ _ _] [_ _ _] [_ _ _]]
```

### Appending to a slice
You can append new elements to a slice using the `append` function. The `append` function returns a new slice. If the backing array of a slice is too small to fit all given elements, new array will be allocated. The returned slice will point to the newly allocated array.
```go
var s []int
// append to a nil slice
s = append(s, 0) // len=1, cap=2, [0]
s = append(s, 1) // len=2, cap=2, [0, 1]
s = append(s, 2, 3, 4) // len=5, cap=8, [0, 1, 2, 3, 4]
```

