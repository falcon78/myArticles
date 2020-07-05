// Sieve of Eratosthenes implemeneted in go.
// https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes

package main

func Generate(ch chan<- int) {
	for i := 2; ; i++ {
		ch <- i
	}
}

func Filter(in <-chan int, out chan<- int, prime int) {
	for {
		currentNum := <-in
		if currentNum%prime != 0 {
			out <- currentNum
		}
	}
}

func main() {
	src := make(chan int)
	go Generate(src)
	for i := 0; i < 10; i++ {
		prime := <-src
		println(prime)
		dst := make(chan int)
		go Filter(src, dst, prime)
		src = dst
	}
}
