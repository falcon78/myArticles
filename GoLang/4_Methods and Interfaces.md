# Methods And Interfaces

## Methods

Go does not have classes but you can define methods on types.
A method is a function with a special receiver argument.

```go {cmd='go' args=['run']}
package main

import "math"

type Vertex struct {
    X, Y float64
}

func (v Vertex) Abs() float64 {
    return math.Sqrt(v.X*v.X + v.Y*v.Y)
}
func main() {
    v := Vertex{3,4}
    println(v.Abs())
}
```

You can declare methods on non-struct types too.

```go
type MyFloat float64

func (f MyFloat) Abs() float64 {
    // do something
}
```

Method can only be declared on types which are defined in current package.
You can't declare methods for types defined in other packages (like built-in types such as `int`)

## Pointer Receivers

You use pointer receivers when you need to change the value of the receiver.

```go {cmd='go' args=['run']}
package main

type MyInt int

func (i *MyInt) triple() {
    //Manipulate the receiver's value
    *i = *i * MyInt(3)
}

func main() {
    i := MyInt(3)
    i.triple()
    print(i) // 9
}
```

- Methods and pointer indirection

If a function has a pointer argument ex.`func someFunc(a *int)` you have to pass a pointer to it, passing a value in this case yields an error.
But methods with pointer receiver takes either pointer or value.

```go
func (v *Vertex) Scale(f float64) {
    v.X = v.X * f
    v.Y = v.Y * f
}

func main() {
    v := Vertex{3, 4}
    v.Scale(2) // passing value

    p := &Vertex{4, 3}
    p.Scale(3) // passing pointer

    // both works!
}
```

This works because Go interprets the statement `v.Scale(2)` as `(&v).Scale(2)` since the `Scale` method has a pointer receiver.
_The equivalent thing also happens in reverse direction._ If the method of a type has a value receiver, it can take either a pointer or a value.

- When to use pointer receiver and value receiver

If you have to change the value of the receiver then use pointer. And if the receiver is large (ex. struct with huge amount of data) then using pointer is a lot more effective for performance.
Other wise use value receiver.

> In general, all methods of a given type should have either value or pointer receivers, but not a mixture of both.

# Interfaces

An interface type is defined as a set of method signatures. A value of interface type can hold any value that implements those methods.

```go
type Abser interface {
    Abs() float64
}

func main() {
    var a Abser
    f := MyFloat(-math.Sqrt2)
    v := Vertex{3, 4}

    a = f  // a MyFloat implements Abser
    a = &v // a *Vertex implements Abser

    // In the following line, v is a Vertex (not *Vertex)
    // and does NOT implement Abser.
    a = v

    fmt.Println(a.Abs())
}

type MyFloat float64

func (f MyFloat) Abs() float64 {
    return math.Abs(f)
}

type Vertex struct {
    X, Y float64
}

func (v *Vertex) Abs() float64 {
    return math.Sqrt(v.X*v.X + v.Y*v.Y)
}
```

There is an error in the example code `a = v` because `Vertex` doesn't implement `Abser` because the `Abs` method is defined only on `*Vertex`(the pointer type)

- Interfaces are implemented implicitly

A type implements an interface by implementing its methods. There is no explicit declaration of intent, no "implements" keyword.

```go
type I interface {
    M()
}

type T struct {
    S string
}

// This method means type T implements the interface I,
// but we don't need to explicitly declare that it does so.
func (t T) M() {
    fmt.Println(t.S)
}

func main() {
    var i I = T{"hello"}
    i.M()
}
```

- Interface values

Under the hood interface is a tuple of value and concrete type.
`(value, type)`

```go
type I interface {
    M()
}

type T struct {
    S string
}

func (t *T) M() {
    fmt.Println(t.S)
}

func main() {
    var i I

    i = &T{"Hello"}
    describe(i)
    i.M()
}

func describe(i I) {
    fmt.Printf("(%v, %T)\n", i, i) // (&{Hello}, *main.T)
}
```

- Interface values with nil underlying values

> When a interface holds a nil value of a certain type, calling a method of that type gives the method a nil receiver.

```go
type I interface {
    M()
}

type T struct {
    S string
}

func (t *T) M() {
    if t == nil {
        fmt.Println("<nil>")
        return
    }
    fmt.Println(t.S)
}

func main() {
    var i I
    // The interface is nil and calling on nil interface
    // yields an runtime error
    describe(i) // (<nil>, <nil>)

    var t *T
    i = t
    // The interface holds a nil value and is itself not nil
    describe(i) // (<nil>, *main.T)
    i.M() // (<nil>)

    i = &T{"hello"}
    describe(i) // (&{hello}, *main.T)
    i.M() // hello
}

func describe(i I) {
    fmt.Printf("(%v, %T)\n", i, i)
}
```

### The empty Interface

```go
interface {}
```

An empty interface can hold any data types because every type implements at least zero methods.

They are used by codes that deal with unknown type. Eg, fmt.Print takes any number of arguments of type `interface{}`

```go
func main() {
	var i interface{}
	describe(i) // (<nil>, <nil>)

	i = 42
	describe(i) // (42, int)

	i = "hello"
	describe(i) // (hello, string)
}

func describe(i interface{}) {
	fmt.Printf("(%v, %T)\n", i, i)
}

```

## Type Assertion

A type assertion provides access to an interface value's underlying concrete type.

`t := i.(T)`

The statement asserts that `i` holds value of concrete type `T` and assigns the value to t. If the type of `i` is not `T`, it will trigger a panic.

```go
var i interface{} = "string"
v := i.(string) // ok
f := i.(float64) // not ok , panics !!!
```

You can also receive `ok` to prevent panic

```go
var i interface{} = "string"
v, ok := i.(int) // v --> 0, ok --> false
```
