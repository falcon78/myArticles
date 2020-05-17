# Sum of multiples of x from 1 to n

[Explanation](https://betterexplained.com/articles/techniques-for-adding-the-numbers-1-to-100/)

- ex . Where x is 3 and n is 100

```python {cmd}
import math

x = 3
n = 100

lastNumber = n - math.ceil(n % x)
repeatingNumber = lastNumber + x
repeatCount = n // x

sum = int((repeatingNumber * repeatCount) / 2)

print(sum)
```

# Recursion Factorial

```python {cmd}
def ln(n):
    if (n == 1 or n == 0):
        return 1
    return n * ln(n-1)

print(ln(10))
```
