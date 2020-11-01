# Chapter 25: Learning to count

> Page 221

## Counting

- Number of integer between 0 and n exclusive is $n-1$.
  - Number of integer between a and b exclusive is: $(b-a)-1$
  - Number of integer between a and b inclusive is: $(b-a)+1$

## Multiplication

- Number of ways six coins come up when flipped. (**Independent**)

  - Possibility with one coin: 2 #(Heads or tails)
  - With six coins:  $2^6 = 64$ ways.
  - ($a^b$ where a #a is number of possibilities and #b is trial count.)
  -  Each **independent** contributor adds a factor of its number of possibilities to the overall product.


## Example: The Number of Divisors

- Finding how many divisors a certain number has.
  - (We will use 540 as an example.)
  - First find prime factorization of the number
    - 540 = $2^2*3^3*5$
  - Thus all divisors of 540 must be of form $2^a*3^b*5^c$
  - Since our choices of $a, b, c$ are **Independent** there are $(3)(4)(2)$ = 24 factors of 540.
  - The general formula is:
    - $$\large{n = p_1^{e1}*p_2^{e2}...p_k^{ek}}$$
    - $$\text{Factors of N} = (e^1+1) *(e^2 + 1)* ... * (e^k+1)$$
  - The number of divisors of positive integer is often denoted $d(n)$

## Restrictions on Multiplication

- If the actions are not **Independent** then the Multiplication methods starts to break down.

## Permutations, Arrangements, and $!$

### Permutations

- $_nP_k$ = $\dfrac{n!}{(n-k)!}$
- P: Number of permutations.
- n: Total number of objects in the set.
- k: Number of choosing objects from the set.
  - Example: In how many ways can a row of k seats be filled from a set of n people.

- How many possible ways are there to lay things in a **circle**.

  - Unlike lines, with objects arranged in circle the rotation doesn't matter.
  - $\dfrac{n!}{n} = (n-1)!$

- How many possible ways are there to lay things in a **bracelet**.

  - You have to divide by an extra factor of two, since both rotation and flipped over companion are indistinguishable.
    - $\dfrac{n!}{n} * \dfrac{1}{2} = \dfrac{(n-1)!}{2}$

- Finding trailing zeros in end of a factorial.
  $$\large{f(n) = \sum_{i=1}^{k} \lfloor{\frac{n}{5^i}{}}\rfloor}$$
  $$\text{where k} = \lfloor{\log_5{n}}\rfloor$$

### Combinations

- $_nC_k = \dfrac{_nP_k}{k!}$
  - (ex. How many ways can you pick k people from set of n people.)

### Combinatorial Identity

- $\begin{pmatrix} 8\\ 5\\ \end{pmatrix}$ = $\begin{pmatrix} 8\\ 3\\ \end{pmatrix}$
- Generally
  - $\begin{pmatrix} n\\ k\\ \end{pmatrix}$ = $\begin{pmatrix} n\\ n-k\\ \end{pmatrix}$
    - Choosing `k` thing is the same as choosing `n - k` things we don't want.