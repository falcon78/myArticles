# Chapter 1.3 Bags, Queues, and Stacks

## Autoboxing

Converting primitive types to reference types and vice versa.

```java
list.push(17); // <-- auto-boxing (int -> Integer)
int a = list.get(1); // <-- auto-unboxing (Integer -> int)
```

## Iterable Collections

Objects that allow you to iterate through them.

## Data Types

- **Bags**

  A bag is a collection where removing items is not supported and the order in which the items are processed is immaterial.

- **FIFO Queues**

  First in, First out. Pops items in the order they are pushed.

- **Pagedown Stacks (Stack)**

  Last in first out

## Arithmetic expression

- **Dijkstra's Two-Stack Algorithm**

  - Push operands onto the operand stack.
  - Push operators onto the operator stack.
  - Ignore left parentheses.
  - On encountering a right parenthesis, pop an operator, pop the requisite numbers (operands), and push onto the operand stack the result of applying that operator to those operands.

## Generics

```java
public class FixedCapacityStack<T> {
    private T[] a;
    private int N;

    public FixedCapacityStack(int N) {
        a = (T[]) new Object[](N);
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public boolean size() {
        return N;
    }

    public void push(Item item) {
        a[N++] = item
    }

    public T pop() {
        return a[--N];
    }
}
```

- **Why are generic arrays disallowed in java?**

  > It's because Java's arrays (unlike generics) contain, at runtime, information about its component type. So you must know the component type when you create the array. Since you don't know what T is at runtime, you can't create the array.

  [Source](https://stackoverflow.com/questions/2927391/whats-the-reason-i-cant-create-generic-array-types-in-java)

## Loitering

Holding a reference to an item that is no longer needed is known as **Loitering**.

## Iterables and Iterator

```java
import java.util.iterator;

public someclass<T> {

  public Iterator<T> iterator() {
    return new CustomIterator();
  }

  private class CustomIterator implements Iterator<T> {
    private int currentCounter = 0;

    public boolean hasNext() {
      // return bool if there is next element
    }

    public <T> next() {
      // return next item and increment counter
    }

    public void remove() {

    }
  }
}
```

## Enable assertion check 

- Put `-ea` in VM options.
