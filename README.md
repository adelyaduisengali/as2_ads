# Custom Data Structures (ArrayList & LinkedList)

## Description
This project implements custom versions of:
- MyArrayList (dynamic array)
- MyLinkedList (doubly linked list)

Both structures implement the MyList interface.

## Features
- add / addFirst / addLast
- get / getFirst / getLast
- remove / removeFirst / removeLast
- indexOf / exists
- toArray
- sort
- iterator support

## Implementation Details
- MyArrayList uses dynamic resizing (grow method)
- MyLinkedList uses Node (prev/next references)
- Optimized traversal depending on index position

## Testing
All methods are tested in Main.java
## Time complexity
| Operation      | MyArrayList | MyLinkedList |
|----------------|-------------|--------------|
| get(index)     | O(1)        | O(n)         |
| addLast        | O(1)*       | O(1)         |
| addFirst       | O(n)        | O(1)         |
| removeFirst    | O(n)        | O(1)         |

*amortized, thanks to dynamic resizing

## Tech stack
Java · Generics · Iterator · IntelliJ IDEA

## How to run
Open the project in IntelliJ IDEA and run `Main.java`.

## Author
Duisengaliyeva Adel ST-2502
