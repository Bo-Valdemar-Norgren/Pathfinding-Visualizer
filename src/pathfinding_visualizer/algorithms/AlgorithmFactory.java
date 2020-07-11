package pathfinding_visualizer.algorithms;

import pathfinding_visualizer.Board;
import pathfinding_visualizer.nodes.DefaultNode;

public class AlgorithmFactory
{

    public Algorithm createAlgorithm(Board board, AlgorithmType algoType) {
        switch (algoType) {
	    case ASTAR:
		DefaultNode startNode = board.getStartNode();
		DefaultNode endNode = board.getEndNode();
		return new AStar(board, startNode, endNode);
	    default:
	        throw new IllegalArgumentException("No such algorithm exists.");
	}
    }
}
