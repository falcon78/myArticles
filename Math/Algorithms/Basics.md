# Sum of multiples of x from 1 to n
[Explanation](https://betterexplained.com/articles/techniques-for-adding-the-numbers-1-to-100/)

- ex . Where x is 3 and n is 100

```js {cmd="node"}
const x = 3;
const n = 100;

const lastNumber = Math.ceil(n - (n % x));
const repeatingValue = x + lastNumber;
const sum = repeatingValue * (Math.floor(n / x) / 2);

console.log(sum);
```
