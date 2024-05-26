package UninformedSearch.BFS;

import java.util.*;

public class MissionariesAndCannibalsProblem {
    private static final int MISSIONARIES = 3, CANNIBALS = 3;
    private static final int BOAT_CAPACITY = 2;

    public static void main(String[] args) {
        MissionariesAndCannibalsProblem problem = new MissionariesAndCannibalsProblem();
        System.out.println("BFS:");
        printResult(problem.bfs(), problem.bfsStates);
    }

    static class State {
        int missionariesLeft, cannibalsLeft, missionariesRight, cannibalsRight;
        boolean isBoatOnLeft;
        String action;

        State(int missionariesLeft, int cannibalsLeft, int missionariesRight, int cannibalsRight, boolean isBoatOnLeft,
                String action) {
            this.missionariesLeft = missionariesLeft;
            this.cannibalsLeft = cannibalsLeft;
            this.missionariesRight = missionariesRight;
            this.cannibalsRight = cannibalsRight;
            this.isBoatOnLeft = isBoatOnLeft;
            this.action = action;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            State state = (State) o;
            return missionariesLeft == state.missionariesLeft && cannibalsLeft == state.cannibalsLeft &&
                    missionariesRight == state.missionariesRight && cannibalsRight == state.cannibalsRight &&
                    isBoatOnLeft == state.isBoatOnLeft;
        }

        @Override
        public int hashCode() {
            return Objects.hash(missionariesLeft, cannibalsLeft, missionariesRight, cannibalsRight, isBoatOnLeft);
        }
    }

    int bfsStates = 0;

    private List<State> getNeighbors(State state) {
        List<State> neighbors = new ArrayList<>();
        int missionariesLeft = state.missionariesLeft, cannibalsLeft = state.cannibalsLeft;
        int missionariesRight = state.missionariesRight, cannibalsRight = state.cannibalsRight;
        boolean isBoatOnLeft = state.isBoatOnLeft;

        if (isBoatOnLeft) {
            // Boat going from left to right
            for (int m = 0; m <= BOAT_CAPACITY; m++) {
                for (int c = 0; c <= BOAT_CAPACITY - m; c++) {
                    if (m + c >= 1 && m + c <= BOAT_CAPACITY) {
                        int newMissionariesLeft = missionariesLeft - m;
                        int newCannibalsLeft = cannibalsLeft - c;
                        int newMissionariesRight = missionariesRight + m;
                        int newCannibalsRight = cannibalsRight + c;
                        if (isValidState(newMissionariesLeft, newCannibalsLeft) &&
                                isValidState(newMissionariesRight, newCannibalsRight)) {
                            neighbors.add(new State(newMissionariesLeft, newCannibalsLeft,
                                    newMissionariesRight, newCannibalsRight,
                                    false, "Move " + m + " missionaries and " + c + " cannibals from left to right"));
                        }
                    }
                }
            }
        } else {
            // Boat going from right to left
            for (int m = 0; m <= BOAT_CAPACITY; m++) {
                for (int c = 0; c <= BOAT_CAPACITY - m; c++) {
                    if (m + c >= 1 && m + c <= BOAT_CAPACITY) {
                        int newMissionariesLeft = missionariesLeft + m;
                        int newCannibalsLeft = cannibalsLeft + c;
                        int newMissionariesRight = missionariesRight - m;
                        int newCannibalsRight = cannibalsRight - c;
                        if (isValidState(newMissionariesLeft, newCannibalsLeft) &&
                                isValidState(newMissionariesRight, newCannibalsRight)) {
                            neighbors.add(new State(newMissionariesLeft, newCannibalsLeft,
                                    newMissionariesRight, newCannibalsRight,
                                    true, "Move " + m + " missionaries and " + c + " cannibals from right to left"));
                        }
                    }
                }
            }
        }
        return neighbors;
    }

    private boolean isValidState(int missionaries, int cannibals) {
        // Check if the number of cannibals does not outnumber the missionaries on both
        // sides
        if ((missionaries > 0 && missionaries < cannibals) ||
                (MISSIONARIES - missionaries > 0 && MISSIONARIES - missionaries < CANNIBALS - cannibals))
            return false;
        // Check if the number of missionaries and cannibals are within the valid range
        return missionaries >= 0 && missionaries <= MISSIONARIES && cannibals >= 0 && cannibals <= CANNIBALS;
    }

    private List<State> constructPath(Map<State, State> parentMap, State goal) {
        List<State> path = new ArrayList<>();
        for (State at = goal; at != null; at = parentMap.get(at))
            path.add(at);
        Collections.reverse(path);
        return path;
    }

    public List<State> bfs() {
        Queue<State> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>();
        Map<State, State> parentMap = new HashMap<>();
        State initialState = new State(MISSIONARIES, CANNIBALS, 0, 0, true, "Initial State");
        queue.add(initialState);
        visited.add(initialState);
        while (!queue.isEmpty()) {
            State current = queue.poll();
            bfsStates++;
            if (current.missionariesLeft == 0 && current.cannibalsLeft == 0)
                return constructPath(parentMap, current);
            for (State neighbor : getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                }
            }
        }
        return Collections.emptyList();
    }

    private static void printResult(List<State> result, int statesExplored) {
        if (result.isEmpty()) {
            System.out.println("No solution found.");
        } else {
            for (int i = 0; i < result.size(); i++) {
                State state = result.get(i);
                System.out.println("Step " + (i + 1) + ": " + state.action);
            }
            System.out.println("Total steps: " + result.size());
            System.out.println("Total states explored: " + statesExplored);
        }
    }
}
