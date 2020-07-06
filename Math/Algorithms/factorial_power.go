package main

// MaximumPower Find maximum power of a number that divides a factorial
// TODO: currently only works for prime numbers, improve this so it works on all numbers.
func MaximumPower(number, factorial int) int {
	currentNumber := number
	maximumPower := 0
	for factorial/currentNumber >= 1 {
		maximumPower += factorial / currentNumber
		currentNumber = currentNumber * number
	}
	return maximumPower
}

func main() {
	println(MaximumPower(2, 100))
}
