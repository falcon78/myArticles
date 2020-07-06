package main

import (
	"reflect"
	"testing"
)

func TestPrimeSieve(t *testing.T) {
	testSets := [][]int{
		[]int{10, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29},
		[]int{25, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97},
	}

	for _, test := range testSets {
		result := PrimeSieve(test[0])
		if !reflect.DeepEqual(result, test[1:]) {
			t.Errorf("%v expected, got: %v", test[1:], result)
		}
	}
}
