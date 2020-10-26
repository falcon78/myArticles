# Sets

- The Collections framework provides `Set<E>` interface to represent a set as an abstract
  data type.
- Sets inherit all methods from `Collection<E>` interface, but doesn't add any new ones.
- Common methods of sets are:
  - `containes(Object o)` returns true only specified object is in the collection.
  - `add(E element)` adds a new element to the set.
  - `addAll(Collection<E> coll)` adds all elements from other collections to this set.
  - `retainAll(Collection<E> coll)` retains only the elements in this set that are contained in the specified collection.
  - `removeAll(Collection<E> coll)` removes from this set all elements that are contained in the specified collection.
  - `size()` size of the collection.
  - `isEmpty()`
  - `clear()` Removes all elements.

## Mutable and immutable sets

> ## Mutable sets

- `HashSet`
- `TreeSet`
- `LinkedHashSet`

> ## Immutable Sets

- One of the most used operation of a set is checking wether a set contains an element.
- Can only invoke `contains`, `size`, `isEmpty` methods.
- Can't change the set. Use `HashSet`, `TreeSet`, `LinkedHashSet` instead.
- Iteration order is not fixed.

```java
Set<String> emptySet = Set.of();
Set<String> persons = Set.of("A", "B", "personC");
Set<Integer> nums = Set.of(1,2,3);
```

```java
emptySet.contains("hello") // false
emptySet.persons("A") // true
```

> ## HashSet

- Uses hash table.
- No iteration order guarantee.
- O(1) performance for `add`, `remove`, `contains` operation.
- `contains` method is highly optimized.

```java
Set<Integer> set= HashSet<>();
set.add(1);
set.add(2);
System.out.println(set) // [2,1]
set.contains(1) // true
```

> ## TreeSet

- Preserves order of elements inserted by their natural order. (using element's `Comparable` interface or by a custom Comparator implementation)
- Implements `SortedSet` Interface.
	- `Comparator<? super E> comparator()` returns the comparator used to order elements in the set or null if the set uses the natural ordering of its elements;
	- `E first()` returns the first (lowest) element in the set;
	- `E last()` returns the last (highest) element in the set;
	- `SortedSet<E> headSet(E toElement)` returns a subset containing elements that are strictly less than toElement;
	- `SortedSet<E> tailSet(E fromElement)` returns a subset containing elements that are greater than or equal to fromElement;
	- `SortedSet<E> subSet(E fromElement, E toElement)` returns a subset containing elements in the range fromElement (inclusive) toElement (exclusive).
