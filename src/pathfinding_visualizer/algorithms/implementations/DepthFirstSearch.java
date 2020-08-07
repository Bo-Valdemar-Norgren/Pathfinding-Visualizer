package pathfinding_visualizer.algorithms.implementations;

import pathfinding_visualizer.Board;
import pathfinding_visualizer.algorithms.Algorithm;
import pathfinding_visualizer.algorithms.configurations.TraversalStrategy;
import pathfinding_visualizer.nodes.DefaultNode;

import java.util.ArrayList;
import java.util.Stack;

public class DepthFirstSearch implements Algorithm
{
    private Board board;
    private TraversalStrategy traversalStrategy;
    public DepthFirstSearch(Board board, TraversalStrategy traversalStrategy) {
        this.board = board;
        this.traversalStrategy = traversalStrategy;
    }

    public ArrayList<DefaultNode> startSearch() {
        ArrayList<DefaultNode> visitedNodes = new ArrayList<>();
        Stack<DefaultNode> openNodes = new Stack<>();
        DefaultNode startNode = board.getStartNode();

        openNodes.push(startNode);
        while (!openNodes.isEmpty()) {
            DefaultNode currentNode = openNodes.pop();
            if (!currentNode.isStartNode() && !visitedNodes.contains(currentNode)) {
                visitedNodes.add(currentNode);
            }
            if (currentNode.isEndNode()) {
                return visitedNodes;
            }
            ArrayList<DefaultNode> neighbours = traversalStrategy.getNeighbours(board, currentNode);
            for (DefaultNode neighbour: neighbours) {
                if (!visitedNodes.contains(neighbour)) {
                    openNodes.push(neighbour);
                    neighbour.setParent(currentNode);
                    if (!neighbour.isStartNode()) {
                        visitedNodes.add(neighbour);
                    }
                    if (neighbour.isEndNode()) {
                        return visitedNodes;
                    }
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
