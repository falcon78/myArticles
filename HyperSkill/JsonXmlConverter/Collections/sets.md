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

- Mutable sets

  - `HashSet`
  - `TreeSet`
  - `LinkedHashSet`

- Immutable Sets

    ```java
    Set<String> emptySet = Set.of();
    Set<String> persons = Set.of("A", "B", "personC");
    Set<Integer> nums = Set.of(1,2,3);
    ```
    
    - One of the most used operation of a set is checking wether a set contains an element.
    ```java
    emptySet.contains("hello") // false
    emptySet.persons("A") // true
    ```