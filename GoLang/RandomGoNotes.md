### Multithreading example

```go {cmd='go' args=['run']} 
package main

import (
    "fmt"
    "runtime"
    "sync"
    "time"
)

func main() {
    runtime.GOMAXPROCS(2)

    var wg sync.WaitGroup
    wg.Add(2)

    fmt.Println("Starting Go Routines")
    go func() {
        defer wg.Done()

        for char := 'a'; char < 'a'+26; char++ {
            time.Sleep(5000 * time.Microsecond)
            fmt.Printf("%c ", char)
        }
    }()

    go func() {
        defer wg.Done()

        for number := 1; number < 27; number++ {
            time.Sleep(5000 * time.Microsecond)
            fmt.Printf("%d ", number)
        }
    }()

    fmt.Println("Waiting To Finish")
    wg.Wait()

    fmt.Println("\nTerminating Program")
}
```