package pathfinding_visualizer.algorithms.implementations;

import pathfinding_visualizer.Board;
import pathfinding_visualizer.algorithms.Algorithm;
import pathfinding_visualizer.algorithms.configurations.TraversalStrategy;
import pathfinding_visualizer.nodes.DefaultNode;

import java.util.ArrayList;

public class Dijkstra implements Algorithm
{
    private Board board;
    private TraversalStrategy traversalStrategy;
    public Dijkstra(Board board) {
        this.board = board;
    }

    public ArrayList<DefaultNode> startSearch() {
        return null;
    }

    public void setTraversalStrategy(final TraversalStrategy traversalStrategy) {
        this.traversalStrategy = traversalStrategy;
    }
}
