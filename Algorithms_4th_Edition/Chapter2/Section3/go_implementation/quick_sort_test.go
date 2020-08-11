package main

import (
	"math/rand"
	"testing"
)

func assertIsSorted(arr []int, t *testing.T) {
	for i := 1; i < len(arr); i++ {
		if arr[i] < arr[i-1] {
			t.Error("Not in ascending order")
		}
	}
}

func TestQuickSort(t *testing.T) {
	for i := 0; i < N; i++ {
		arr := [size]int{}
		for j := 0; j < size; j++ {
			arr[j] = rand.Int()
		}
		QuickSort(arr[:])
		assertIsSorted(arr[:], t)
	}
}

func TestInsertionSort(t *testing.T) {
	for i := 0; i < 10; i++ {
		arr := [100]int{}
		for j := 0; j < 100; j++ {
			arr[j] = rand.Intn(10)
		}
		InsertionSort(arr[:])
		assertIsSorted(arr[:], t)
	}
}
