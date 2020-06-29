package pathfinding_visualizer.algorithms;

import pathfinding_visualizer.Board;
import pathfinding_visualizer.nodes.AbstractNode;
import pathfinding_visualizer.nodes.AbstractTraversableNode;
import pathfinding_visualizer.nodes.DefaultNode;
import pathfinding_visualizer.nodes.EndNode;
import pathfinding_visualizer.nodes.NodeType;
import pathfinding_visualizer.nodes.StartNode;

import java.awt.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class AStar implements Algorithm
{
    private static int DEFAULT_HV_COST = 10;
    private static int DEFAULT_DIAGONAL_COST = 14;

    private Board board;
    private AbstractNode[][] nodeGrid;
    private PriorityQueue<AbstractTraversableNode> openNodes;
    private boolean[][] closedNodes;
    private int gridRows;
    private int gridCols;
    private StartNode startNode;
    private EndNode endNode;

    public AStar(Board board) {
        this.board = board;
        this.nodeGrid = board.getNodeGrid();
        this.startNode = board.getStartNode();
        this.endNode = board.getEndNode();

        gridRows = nodeGrid.length;
        gridCols = nodeGrid[0].length;
        closedNodes = new boolean[gridRows][gridCols];
        openNodes = new PriorityQueue<>();
        try {
            openNodes.add(startNode);
        } catch (NullPointerException e) {
            System.out.println("No start node exists.");
        }
    }

    public ArrayList<AbstractTraversableNode> getNeighbours(AbstractTraversableNode node) {
        Point nodeCoordinates = node.getCartesianCoordinates();
        int nodeX = nodeCoordinates.x;
        int nodeY = nodeCoordinates.y;

        ArrayList<AbstractTraversableNode> neighbours = new ArrayList<>();

        for (int y = nodeY-1; y<=nodeY+1; y++) {
            for (int x = nodeX-1; x<=nodeX+1; x++) {
                if (y >= 0 && y < gridCols && x >= 0 && x < gridRows) { //if in bounds
                    Point currentCoordinates = new Point(x, y);
                    AbstractNode currentNode = board.getNodeAt(currentCoordinates);
                    NodeType currentNodeType = currentNode.getNodeType();
                    if (!nodeCoordinates.equals(currentCoordinates) && currentNodeType != NodeType.WALL) {
                        neighbours.add((AbstractTraversableNode) currentNode);
                    }
                }
            }
        }

        return neighbours;
    }

    private int getFinalCost(AbstractTraversableNode node) {
        return node.getCostFromStart() + getHeuristicCost(node);
    }

    private int getHeuristicCost(AbstractTraversableNode node) { //TODO: Write method.
        return 0;
    }


    @Override public void startSearch() {

    }
}
