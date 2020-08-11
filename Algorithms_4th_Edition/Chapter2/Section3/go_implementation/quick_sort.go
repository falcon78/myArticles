package main

import (
	"algorithms/quicksort/utils"
)

const (
	size = 10000
	// N is number of test trails
	N = 100
)

func main() {
	for i := 0; i < N; i++ {
		arr := utils.UnsortedSlice(size)
		QuickSort(arr)
		utils.CheckIsSorted(arr)
	}
}

// QuickSort Sorts array using quicksort method
func QuickSort(a []int) {
	utils.ShuffleArray(a)
	_QuickSort(a)
}

func _QuickSort(a []int) {
	if len(a) < 2 {
		return
	}

	if len(a) < 12 {
		utils.InsertionSort(a)
		return
	}

	p := partition(a)
	_QuickSort(a[0:p])
	_QuickSort(a[p+1:])
}

func partition(a []int) int {
	pivotValue := a[0]
	i := 0
	j := len(a) - 1

	for {
		for i <= len(a)-1 && pivotValue >= a[i] {
			i++
		}
		for j >= 0 && pivotValue <= a[j] {
			if j < i {
				break
			}
			j--
		}
		if j <= i {
			break
		}
		utils.Swap(a, i, j)
	}
	utils.Swap(a, 0, j)

	return j
}
