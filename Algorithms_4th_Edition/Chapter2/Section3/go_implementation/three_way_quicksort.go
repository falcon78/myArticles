package main

import (
	"algorithms/quicksort/utils"
)

const (
	// N number of trails
	N = 100
	// SIZE size of each array
	SIZE = 10000
)

func main() {
	for i := 0; i < N; i++ {
		arr := utils.UnsortedSlice(SIZE)
		ThreeWayQuickSort(arr)
		utils.CheckIsSorted(arr)
	}
}

// ThreeWayQuickSort : Implementation of three way quick sort method
func ThreeWayQuickSort(arr []int) {
	utils.ShuffleArray(arr)
	_ThreeWayQuickSort(arr)
}

func _ThreeWayQuickSort(arr []int) {
	left := 0
	i := 1
	right := len(arr) - 1
	pivotValue := arr[0]

	for {
		if i > right {
			break
		}

		if arr[i] < pivotValue {
			utils.Swap(arr, i, left)
			i++
			left++
		} else if arr[i] == pivotValue {
			i++
		} else {
			utils.Swap(arr, i, right)
			right--
		}
	}

	leftArraySize := left - 1
	if leftArraySize > 0 {
		ThreeWayQuickSort(arr[0:left])
	}

	rightArraySize := (len(arr) - 1) - (right + 1)
	if rightArraySize > 0 {
		ThreeWayQuickSort(arr[right+1 : len(arr)])
	}
}
