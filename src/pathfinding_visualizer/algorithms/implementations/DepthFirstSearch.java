package pathfinding_visualizer.algorithms.implementations;

import pathfinding_visualizer.Board;
import pathfinding_visualizer.algorithms.Algorithm;
import pathfinding_visualizer.algorithms.configurations.TraversalStrategy;
import pathfinding_visualizer.nodes.DefaultNode;

import java.util.ArrayList;

public class DepthFirstSearch implements Algorithm
{
    private Board board;
    public DepthFirstSearch(Board board) {
        this.board = board;
    }

    @Override public ArrayList<DefaultNode> startSearch() {
	return null;
    }

    @Override public void setTraversalStrategy(final TraversalStrategy traversalStrategy) {

    }
}
