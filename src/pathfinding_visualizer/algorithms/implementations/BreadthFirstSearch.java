package pathfinding_visualizer.algorithms.implementations;

import pathfinding_visualizer.Board;
import pathfinding_visualizer.algorithms.Algorithm;
import pathfinding_visualizer.algorithms.configurations.TraversalStrategy;
import pathfinding_visualizer.nodes.DefaultNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch implements Algorithm
{
    private Board board;
    private TraversalStrategy traversalStrategy;
    public BreadthFirstSearch(Board board, TraversalStrategy traversalStrategy) {
        this.board = board;
        this.traversalStrategy = traversalStrategy;
    }

    public ArrayList<DefaultNode> startSearch() {
        ArrayList<DefaultNode> visitedNodes = new ArrayList<>();
        Queue<DefaultNode> openNodes = new LinkedList<>();
        DefaultNode startNode = board.getStartNode();

        openNodes.add(startNode);
        while (!openNodes.isEmpty()) {
            DefaultNode currentNode = openNodes.remove();
            if (!currentNode.isStartNode()) {
                visitedNodes.add(currentNode);
            }
            if (currentNode.isEndNode()) {
                return visitedNodes;
            }
            ArrayList<DefaultNode> neighbours = traversalStrategy.getNeighbours(board, currentNode);
            for (DefaultNode neighbour: neighbours) {
                    if (!visitedNodes.contains(neighbour)) {
                        neighbour.setParent(currentNode);
                        if (!neighbour.isStartNode()) {
                            visitedNodes.add(neighbour);
                        }
                        if (neighbour.isEndNode()) {
                            return visitedNodes;
                        }
                        openNodes.add(neighbour);
                    }
                }
            }
        return visitedNodes; // No path found.
    }

    public void setTraversalStrategy(TraversalStrategy traversalStrategy) {
        this.traversalStrategy = traversalStrategy;
    }

    public TraversalStrategy getTraversalStrategy() {
        return traversalStrategy;
    }
}
