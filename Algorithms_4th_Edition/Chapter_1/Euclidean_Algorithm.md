# Euclidean Algorithm or Euclid's Algorithm

Textbook Page 4
[Explanation](https://en.wikipedia.org/wiki/Euclidean_algorithm)

- Algorithm to find **greatest common divisor** (GCD)

- GCD can be expressed as a sum of the two original numbers each multiplied by a positive or negative integer, $ e.g., 21 = 5 × 105 + (−2) × 252. $ [Bézout's identity. ](https://en.wikipedia.org/wiki/B%C3%A9zout%27s_identity)

- If gcd(a, b) = 1, then a and b are said to be co-prime (or relatively prime). (ex. 6 and 35)

- A 24-by-60 rectangle is covered with ten 12-by-12 square tiles, where 12 is the GCD of 24 and 60. More generally, an a-by-b rectangle can be covered with square tiles of side-length c only if c is a common divisor of a and b.

  ![GCD](https://upload.wikimedia.org/wikipedia/commons/thumb/7/74/24x60.svg/170px-24x60.svg.png)

## JS Implementation

  ```js
  function gcd(p, q) {
      if (q === 0) return p
      r = p % q
      return gcd(p, r)
  }
  ```