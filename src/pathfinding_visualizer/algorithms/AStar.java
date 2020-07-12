package pathfinding_visualizer.algorithms;

import pathfinding_visualizer.Board;
import pathfinding_visualizer.nodes.AbstractNode;
import pathfinding_visualizer.nodes.DefaultNode;
import pathfinding_visualizer.nodes.NodeType;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.PriorityQueue;

public class AStar implements Algorithm
{
    private Board board;
    private AbstractNode[][] nodeGrid;
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

    private ArrayList<DefaultNode> getNeighbours(DefaultNode node) {
        Point nodeCoordinates = node.getCartesianCoordinates();
        int nodeX = nodeCoordinates.x;
        int nodeY = nodeCoordinates.y;

        ArrayList<DefaultNode> neighbours = new ArrayList<>();

        for (int y = nodeY-1; y<=nodeY+1; y++) {
            for (int x = nodeX-1; x<=nodeX+1; x++) {
                if (y >= 0 && y < gridCols && x >= 0 && x < gridRows) { //if in bounds
                    Point currentCoordinates = new Point(x, y);
                    AbstractNode currentNode = board.getNodeAt(currentCoordinates);
                    NodeType currentNodeType = currentNode.getNodeType();
                    if (!nodeCoordinates.equals(currentCoordinates) && currentNodeType != NodeType.WALL) {
                        neighbours.add((DefaultNode) currentNode);
                    }
                }
            }
        }

        return neighbours;
    }

    public void startSearch() {
        System.out.println("Started search.");
    }
}
