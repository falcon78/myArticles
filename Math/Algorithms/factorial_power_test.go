package main

import "testing"

func TestMaximumPower(t *testing.T) {
	factorial := 100

	testSets := [][]int{
		[]int{2, 97},
		[]int{5, 24},
		// currently only supports prime numbers.
		// []int{10, 24},
	}

	for _, set := range testSets {
		if result := MaximumPower(set[0], factorial); result != set[1] {
			t.Errorf("For %d expected: %d, got: %d", set[0], set[1], result)
		}
	}
}
