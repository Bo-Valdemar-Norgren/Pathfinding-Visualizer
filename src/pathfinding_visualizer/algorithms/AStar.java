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
        this.traversalStrategy = new VerticalHorizontal(); // VH by default.;
    }

    public void startSearch() {

        PriorityQueue<DefaultNode> openNodes = new PriorityQueue<>();
        DefaultNode startNode = board.getStartNode();
        DefaultNode endNode = board.getEndNode();

        openNodes.add(startNode);
        while (!openNodes.isEmpty()) {
            DefaultNode currentNode = openNodes.poll();
            if (currentNode.equals(endNode)) {
                reconstructPath(currentNode);
                return;
            }
            ArrayList<DefaultNode> neighbours = traversalStrategy.getNeighbours(board, currentNode);
            for (DefaultNode neighbour: neighbours) {
                int temp_g = neighbour.getG() + 1;
                if (temp_g < neighbour.getG()) {
                    neighbour.setParent(currentNode);
                    neighbour.setG(temp_g);
                    neighbour.setF(temp_g + traversalStrategy.h(neighbour.getCoordinates(), endNode
                    .getCoordinates()));
                    if (!openNodes.contains(neighbour)) {
                        openNodes.add(neighbour);
                    }
                }
            }
            if (currentNode.getNodeType() != NodeType.START) {
                board.changeNodeType(currentNode, NodeType.VISITED);
            }
        }
    }

    private void reconstructPath(DefaultNode endNode) {
        DefaultNode currentNode = endNode.getParent();
        while (currentNode != currentNode.getParent() && currentNode.getNodeType() != NodeType.START) {
            board.changeNodeType(currentNode, NodeType.PATH);
            currentNode = currentNode.getParent();
        }
    }

    public void setTraversalStrategy (TraversalStrategy traversalStrategy) {
        this.traversalStrategy = traversalStrategy;
    }
}
