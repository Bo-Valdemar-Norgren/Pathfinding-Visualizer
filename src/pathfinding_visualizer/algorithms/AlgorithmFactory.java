package pathfinding_visualizer.algorithms;

import pathfinding_visualizer.Board;

public class AlgorithmFactory
{
    public Algorithm createAlgorithm(Board board, AlgorithmType algoType) {
        switch (algoType) {
	    case ASTAR:
		return new AStar(board);
	    case DIJKSTRA:
	        return new Dijkstra(board);
	    default:
	        throw new IllegalArgumentException("No such algorithm exists.");
	}
    }
}
