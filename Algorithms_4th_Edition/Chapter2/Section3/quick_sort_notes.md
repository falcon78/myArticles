# Different partition methods of quicksort

- Hoare partition scheme
- Lomuto partition scheme
- Random partition index
- Three way split partition scheme
  - Also known as Dutch National Flag Problem
  - Optimal for arrays with lots of duplicate values
    - Bentley and Mcllory partition is the improved version of this algorithm.
- Median of three partition scheme 
- Java system sort uses Bentley and Mcllory scheme with Turkey Ninther partition scheme. And 
  it also cuts off to Insertion sort for smaller arrays.