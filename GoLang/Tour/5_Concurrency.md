# Goroutines

A go routines is a lightweight thread managed by the Go runtime.

`go f(x,y,z)`

The evaluation of f,x,y happens in the current goroutine and the execution of `f` happens in the new goroutine.

Goroutines run in the same address space, so access to shared memory must be synchronized. The sync package provides useful primitives, although you won't need them much in Go as there are other primitives.

## Channels

Channels are a typed conduit through which you can send and receive values with the channel operator, <-.

```go
ch <- v    // Send v to channel ch.
v := <-ch  // Receive from ch, and // assign value to v.
```

- Channels must be created before use.

```go
ch := make(chan int)
```

By default, sends and receives block until the other side is ready. This allows goroutines to synchronize without explicit locks or condition variables.

### Buffered channels

Channels can be buffered. (pass the buffer length as second argument to make)

```go
ch := make(chan int, 5)
```

Sends to a buffered channel block only when the buffer is full. Receives block when the buffer is empty.

### Range and Close

A sender can `close` a channel to indicate no more values will be sent. Receivers can test wether a channel has been closed by assigning a second parameter to receive expression.

```go
v, ok := <-ch
// `ok` is false when channel is closed.
```

- `for range` loops will run until a channel is closed.

```go
for i := range ch {
    // code
}
```

> Receiver should never close a channel, as sending to close channel will cause a panic.

> Closing a channel is only necessary when the receiver must be told there are no more values coming, such as to terminate a range loop.

### Select

The select statement lets a goroutine wait on multiple communication operations.

A select blocks until one of its cases can run, then it executes that case. It chooses one at random if multiple are ready.

```go
func fibonacci(c, quit chan int) {
    x, y := 0, 1
    for {
        select {
        case c <- x:
            x, y = y, x+y
        case <-quit:
            fmt.Println("quit")
            return
        }
    }
}

func main() {
    c := make(chan int)
    quit := make(chan int)
    go func() {
        for i := 0; i < 10; i++ {
            fmt.Println(<-c)
        }
        quit <- 0
    }()
    fibonacci(c, quit)
}
```

- Default Selection

The default case in a select is run if no other case is ready.

```go
select {
case i := <-c:
    // use i
default:
    // receiving from c would block
}
```

### sync.Mutex

Go provides `sync.Mutex` to sync data read and write across go routines. (**mutual exclusion**)

`sync.Mutex` has two Methods : `Lock` and `Unlock`

We can define a block of code to be executed in mutual exclusion by surrounding it with a call to Lock and Unlock.

We can also use defer to ensure the mutex will be unlocked.

```go {cmd='go' args=['run']}
package main

import (
	"fmt"
	"sync"
	"time"
)

// SafeCounter is safe to use concurrently.
type SafeCounter struct {
	v   map[string]int
	mux sync.Mutex
}

// Inc increments the counter for the given key.
func (c *SafeCounter) Inc(key string) {
	c.mux.Lock()
	// Lock so only one goroutine at a time can access the map c.v.
	c.v[key]++
	c.mux.Unlock()
}

// Value returns the current value of the counter for the given key.
func (c *SafeCounter) Value(key string) int {
	c.mux.Lock()
	// Lock so only one goroutine at a time can access the map c.v.
	defer c.mux.Unlock()
	return c.v[key]
}

func main() {
	c := SafeCounter{v: make(map[string]int)}
	for i := 0; i < 1000; i++ {
		go c.Inc("somekey")
	}

	time.Sleep(time.Second)
	fmt.Println(c.Value("somekey"))
}

```
