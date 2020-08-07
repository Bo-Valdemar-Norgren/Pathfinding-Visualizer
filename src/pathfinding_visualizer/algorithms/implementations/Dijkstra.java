package pathfinding_visualizer.algorithms.implementations;

import pathfinding_visualizer.Board;
import pathfinding_visualizer.algorithms.Algorithm;
import pathfinding_visualizer.algorithms.configurations.TraversalStrategy;
import pathfinding_visualizer.algorithms.configurations.VerticalHorizontal;
import pathfinding_visualizer.nodes.DefaultNode;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Dijkstra implements Algorithm
{
    private Board board;
    private TraversalStrategy traversalStrategy;
    public Dijkstra(Board board) {
        this.board = board;
        this.traversalStrategy = new VerticalHorizontal(); // VH by default.
    }

    public ArrayList<DefaultNode> startSearch() {
        ArrayList<DefaultNode> visitedNodes = new ArrayList<>();
        PriorityQueue<DefaultNode> openNodes = new PriorityQueue<>();
        DefaultNode startNode = board.getStartNode();
        DefaultNode endNode = board.getEndNode();

        startNode.setG(0);
        startNode.setF(traversalStrategy.h(startNode, endNode));

        openNodes.add(startNode);
        while (!openNodes.isEmpty()) {
            DefaultNode currentNode = openNodes.poll();
            if (!currentNode.isStartNode()) {
                visitedNodes.add(currentNode);
            }
            if (currentNode.isEndNode()) {
                return visitedNodes;
            }
            ArrayList<DefaultNode> neighbours = traversalStrategy.getNeighbours(board, currentNode);
            for (DefaultNode neighbour: neighbours) {
                int tentativeG = currentNode.getG() + 1;
                if (tentativeG < neighbour.getG()) {
                    neighbour.setParent(currentNode);
                    neighbour.setG(tentativeG);
                    neighbour.setF(tentativeG);
                    if (!openNodes.contains(neighbour)) {
                        openNodes.add(neighbour);
                    }
                }
            }
        }
        return visitedNodes; // No path found.
    }

    public void setTraversalStrategy(final TraversalStrategy traversalStrategy) {
        this.traversalStrategy = traversalStrategy;
    }
}
