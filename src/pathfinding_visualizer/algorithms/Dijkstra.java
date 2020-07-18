package pathfinding_visualizer.algorithms;

import pathfinding_visualizer.Board;
import pathfinding_visualizer.algorithms.configurations.TraversalStrategy;

public class Dijkstra implements Algorithm
{
    private Board board;
    private TraversalStrategy traversalStrategy;
    public Dijkstra(Board board) {
        this.board = board;
    }

    public void startSearch() {

    }

    public void setTraversalStrategy(final TraversalStrategy traversalStrategy) {
        this.traversalStrategy = traversalStrategy;
    }
}
