package pathfinding_visualizer.algorithms;

import pathfinding_visualizer.Board;
import pathfinding_visualizer.algorithms.configurations.TraversalStrategy;
import pathfinding_visualizer.algorithms.configurations.VerticalHorizontal;
import pathfinding_visualizer.nodes.DefaultNode;
import pathfinding_visualizer.nodes.NodeType;

import java.util.ArrayList;
import java.util.Objects;
import java.util.PriorityQueue;

public class AStar implements Algorithm
{
    private Board board;
    private TraversalStrategy traversalStrategy;
    private PriorityQueue<DefaultNode> openNodes;
    private ArrayList<DefaultNode> closedNodes;
    private DefaultNode startNode;
    private DefaultNode endNode;

    public AStar(Board board, DefaultNode startNode, DefaultNode endNode) {
        this.board = board;
        this.traversalStrategy = new VerticalHorizontal();
        this.startNode = Objects.requireNonNull(startNode, "There must exist a start node.");
        this.endNode = Objects.requireNonNull(endNode, "There must exist an end node.");
        this.closedNodes = new ArrayList<>();
        this.openNodes = new PriorityQueue<>();
        openNodes.add(startNode);
    }

    public void startSearch() {
        System.out.println("Started search.");

        while (!openNodes.isEmpty()) {
            DefaultNode currentNode = openNodes.poll();
            closedNodes.add(currentNode);

            if (currentNode.equals(endNode)) {
                // Make path
                return;
            }

            ArrayList<DefaultNode> neighbours = traversalStrategy.getNeighbours(board, currentNode);

            for (DefaultNode neighbour: neighbours) {

                if (closedNodes.contains(neighbour)) {
                    continue;
                }

                int temp_g = neighbour.getG() + 1;

                if (temp_g < neighbour.getG()) {
                    neighbour.setG(temp_g);
                    neighbour.setF(temp_g + traversalStrategy.h(neighbour.getCartesianCoordinates(), endNode
                    .getCartesianCoordinates()));

                    if (!openNodes.contains(neighbour)) {
                        openNodes.add(neighbour);
                    }

                }
            }

            if (currentNode.getNodeType() != NodeType.START) {
                board.changeNodeType(currentNode, NodeType.DEFAULT_VISITED);
            }
        }
    }
}
