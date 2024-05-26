# Search Algorithms Overview

This README provides an overview of various search algorithms along with their time complexities, space complexities, completeness, optimality, and heuristic functions (if applicable).

## Basic Search Algorithms

| Algorithm | Time Complexity | Space Complexity | Completeness | Optimality |
| --------- | --------------- | ---------------- | ------------ | ---------- |
| BFS       | O(V + E)        | O(V)             | Yes          | Yes        |
| DFS       | O(V + E)        | O(V)             | No           | No         |
| IDDFS     | O(b^d)          | O(bd)            | Yes          | Yes        |
| DLS       | O(b^l)          | O(bl)            | No           | No         |

**Explanation:**

- **BFS (Breadth-First Search):** Explores all neighbor nodes at the present depth prior to moving on to nodes at the next depth level. It guarantees the shortest path from the root to the goal if the path exists.
- **DFS (Depth-First Search):** Explores as far as possible along each branch before backtracking. It is not complete or optimal, as it may get stuck in infinite loops and may not find the shortest path.
- **IDDFS (Iterative Deepening Depth-First Search):** Repeatedly applies DFS with increasing depth limits until the goal is found. It combines the benefits of BFS (completeness) and DFS (less memory usage).
- **DLS (Depth-Limited Search):** A variant of DFS that limits the maximum depth of search. It is not complete or optimal.

## Informed Search Algorithms

| Algorithm | Time Complexity | Space Complexity | Completeness | Optimality | Heuristic Function |
| --------- | --------------- | ---------------- | ------------ | ---------- | ------------------ |
| BestFS    | O(b^m)          | O(b^m)           | No           | No         | Yes                |
| A\*       | O(b^d)          | O(b^d)           | Yes          | Yes        | Yes                |
| AO\*      | O(b^d)          | O(b^d)           | Yes          | Yes        | Yes                |

**Explanation:**

- **BestFS (Best-First Search):** Expands the most promising node, based on some evaluation function. It is not complete or optimal.
- **A\* (A-Star):** Evaluates nodes by combining the cost to reach the node and an estimate of the cost to get from the node to the goal. It is both complete and optimal.
- **AO* (Anytime Optimized A*):** An extension of A\* that finds an optimal solution while continuously improving it until a time limit is reached. It is both complete and optimal.
