package pathfinding_visualizer.algorithms;

import pathfinding_visualizer.Board;
import pathfinding_visualizer.algorithms.configurations.TraversalStrategy;
import pathfinding_visualizer.algorithms.configurations.VerticalHorizontal;
import pathfinding_visualizer.nodes.DefaultNode;
import pathfinding_visualizer.nodes.NodeType;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class AStar implements Algorithm
{
    private Board board;
    private TraversalStrategy traversalStrategy;

    public AStar(Board board) {
        this.board = board;
        this.traversalStrategy = new VerticalHorizontal(); // VH by default.
    }

    public int startSearch() {
        PriorityQueue<DefaultNode> openNodes = new PriorityQueue<>();
        DefaultNode startNode = board.getStartNode();
        DefaultNode endNode = board.getEndNode();

        startNode.setG(0);
        startNode.setF(traversalStrategy.h(startNode, endNode));

        openNodes.add(startNode);
        while (!openNodes.isEmpty()) {
            DefaultNode currentNode = openNodes.poll();
            if (currentNode.isEndNode()) {
                return reconstructPath(currentNode);
            }
            ArrayList<DefaultNode> neighbours = traversalStrategy.getNeighbours(board, currentNode);
            for (DefaultNode neighbour: neighbours) {
                int tentativeG = currentNode.getG() + 1;
                if (tentativeG < neighbour.getG()) {
                    neighbour.setParent(currentNode);
                    neighbour.setG(tentativeG);
                    neighbour.setF(tentativeG + traversalStrategy.h(neighbour, endNode));
                    if (!openNodes.contains(neighbour)) {
                        openNodes.add(neighbour);
                    }
                }
            }
            if (!currentNode.isStartNode()) {
                currentNode.setNodeType(NodeType.VISITED);
                board.boardChanged();
            }
        }
        return -1; // No path found.
    }

    private int reconstructPath(DefaultNode endNode) {
        int length = 0;
        DefaultNode currentNode = endNode.getParent();
        while (currentNode != null && currentNode.getNodeType() != NodeType.START) {
            currentNode.setNodeType(NodeType.PATH);
            board.boardChanged();
            currentNode = currentNode.getParent();
            length += 1;
        }
        System.out.println(length);
        return length;
    }

    public void setTraversalStrategy (TraversalStrategy traversalStrategy) {
        this.traversalStrategy = traversalStrategy;
    }
}
