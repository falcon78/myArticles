package utils

import (
	"math/rand"
)

// Swap swaps element at index 'i' and 'j' in an given array 'arr'
func Swap(a []int, i int, j int) {
	temp := a[i]
	a[i] = a[j]
	a[j] = temp
}

// CheckIsSorted checks wether a given array is sorted and prints error if not sorted
func CheckIsSorted(arr []int) {
	for i := 1; i < len(arr); i++ {
		if arr[i] < arr[i-1] {
			println("Not in ascending order")
			break
		}
	}
}

// InsertionSort sorts given array with insertion sort algorithm.
func InsertionSort(arr []int) {
	for i := 0; i < len(arr)-1; i++ {
		j := i + 1
		key := arr[j]
		for j > 0 && key < arr[j-1] {
			arr[j] = arr[j-1]
			j--
		}
		arr[j] = key
	}
}

// UnsortedSlice returns an unsorted array with specified length
func UnsortedSlice(N int) []int {
	arr := []int{}
	for i := 0; i < N; i++ {
		arr = append(arr, rand.Intn(10))
	}
	return arr
}

// ShuffleArray Randomly shuffle array with Knuth Shuffle algorithm.
func ShuffleArray(arr []int) {
	for i := len(arr) - 1; i > 0; i-- {
		randomIndex := rand.Intn(i + 1)
		temp := arr[randomIndex]
		arr[randomIndex] = arr[i]
		arr[i] = temp
	}
}
