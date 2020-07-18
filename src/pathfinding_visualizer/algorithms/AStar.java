package pathfinding_visualizer.algorithms;

import pathfinding_visualizer.Board;
import pathfinding_visualizer.nodes.Node;
import pathfinding_visualizer.nodes.DefaultNode;
import pathfinding_visualizer.nodes.NodeType;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.PriorityQueue;

public class AStar implements Algorithm
{
    private Board board;
    private Node[][] nodeGrid;
    private PriorityQueue<DefaultNode> openNodes;
    private boolean[][] closedNodes;
    private int gridRows;
    private int gridCols;
    private DefaultNode startNode;
    private DefaultNode endNode;

    public AStar(Board board, DefaultNode startNode, DefaultNode endNode) {
        this.board = board;
        this.nodeGrid = board.getNodeGrid();
        this.startNode = Objects.requireNonNull(startNode, "There must exist a start node.");
        this.endNode = Objects.requireNonNull(endNode, "There must exist an end node.");

        gridRows = nodeGrid.length;
        gridCols = nodeGrid[0].length;
        closedNodes = new boolean[gridRows][gridCols];
        openNodes = new PriorityQueue<>();
        openNodes.add(startNode);
    }

    public void startSearch() {
        System.out.println("Started search.");

        while (!openNodes.isEmpty()) {
            DefaultNode currentNode = openNodes.poll();

            if (currentNode.equals(endNode)) {
                // Make path
                return;
            }

            ArrayList<DefaultNode> neighbours = getNeighbours(currentNode);

            for (DefaultNode neighbour: neighbours) {
                int temp_g = neighbour.getG() + 1;

                if (temp_g < neighbour.getG()) {
                    neighbour.setG(temp_g);
                    neighbour.setF(temp_g + h(neighbour.getCartesianCoordinates(), endNode
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

    //Diagonal allowed.
    private ArrayList<DefaultNode> getNeighbours(DefaultNode node) {
        Point nodeCoordinates = node.getCartesianCoordinates();
        int nodeX = nodeCoordinates.x;
        int nodeY = nodeCoordinates.y;

        ArrayList<DefaultNode> neighbours = new ArrayList<>();

        for (int y = nodeY-1; y<=nodeY+1; y++) {
            for (int x = nodeX-1; x<=nodeX+1; x++) {
                if (y >= 0 && y < gridCols && x >= 0 && x < gridRows) { //if in bounds
                    Point currentCoordinates = new Point(x, y);
                    Node currentNode = board.getNodeAt(currentCoordinates);
                    NodeType currentNodeType = currentNode.getNodeType();
                    if (!nodeCoordinates.equals(currentCoordinates) && currentNodeType != NodeType.WALL) {
                        neighbours.add((DefaultNode) currentNode);
                    }
                }
            }
        }

        return neighbours;
    }


    //No Diagonals
    /*public ArrayList<DefaultNode> getNeighbours(DefaultNode node) {
        Point nodeCoordinates = node.getCartesianCoordinates();
        int nodeX = nodeCoordinates.x;
        int nodeY = nodeCoordinates.y;

        ArrayList<DefaultNode> neighbours = new ArrayList<>();

        if (nodeX - 1 >= 0 && board.getNodeAt(new Point(nodeX - 1, nodeY)).getNodeType() != NodeType.WALL) { //LEFT
            neighbours.add((DefaultNode) board.getNodeAt(new Point(nodeX - 1, nodeY)));
        }

        if (nodeX + 1 < gridRows && board.getNodeAt(new Point(nodeX + 1, nodeY)).getNodeType() != NodeType.WALL) { //RIGHT
            neighbours.add((DefaultNode) board.getNodeAt(new Point(nodeX + 1, nodeY)));
        }

        if (nodeY - 1 >= 0 && board.getNodeAt(new Point(nodeX, nodeY - 1)).getNodeType() != NodeType.WALL) { //UP
            neighbours.add((DefaultNode) board.getNodeAt(new Point(nodeX, nodeY - 1)));
        }

        if (nodeY + 1 < gridCols && board.getNodeAt(new Point(nodeX, nodeY + 1)).getNodeType() != NodeType.WALL) { //DOWN
            neighbours.add((DefaultNode) board.getNodeAt(new Point(nodeX, nodeY + 1)));
        }

        System.out.println(neighbours.size());

        return neighbours;
    }*/

    //Diagonal allowed.
    private int h( Point coordinatesOne, Point coordinatesTwo) { // Uniform cost diagonal distance.
        int dx = Math.abs(coordinatesOne.x - coordinatesTwo.x);
        int dy = Math.abs(coordinatesOne.y - coordinatesTwo.y);

        return Math.max(dx, dy);
    }

    //No Diagonals
    /*private int h( Point coordinatesOne, Point coordinatesTwo) { //Manhattan distance.
        int dx = Math.abs(coordinatesOne.x - coordinatesTwo.x);
        int dy = Math.abs(coordinatesOne.y - coordinatesTwo.y);

        return dx + dy;
    }*/
}
