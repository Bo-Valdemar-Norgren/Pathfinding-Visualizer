package pathfinding_visualizer.algorithms;

import pathfinding_visualizer.Board;
import pathfinding_visualizer.algorithms.configurations.TraversalStrategy;
import pathfinding_visualizer.algorithms.implementations.AStar;
import pathfinding_visualizer.algorithms.implementations.BreadthFirstSearch;
import pathfinding_visualizer.algorithms.implementations.DepthFirstSearch;
import pathfinding_visualizer.algorithms.implementations.Dijkstra;

public class AlgorithmFactory
{
    public Algorithm createAlgorithm(Board board, AlgorithmType algoType, TraversalStrategy traversalStrategy) {
        switch (algoType) {
	    case ASTAR:
		return new AStar(board, traversalStrategy);
	    case DIJKSTRA:
	        return new Dijkstra(board, traversalStrategy);
	    case BFS:
	        return new BreadthFirstSearch(board, traversalStrategy);
	    case DFS:
	        return new DepthFirstSearch(board, traversalStrategy);
	    default:
	        throw new IllegalArgumentException("No such algorithm exists.");
	}
    }
}
