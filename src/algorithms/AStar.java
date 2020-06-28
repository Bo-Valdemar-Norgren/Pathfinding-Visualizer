package algorithms;

import nodes.AbstractNode;
import nodes.AbstractTraversableNode;
import nodes.DefaultNode;
import nodes.EndNode;
import nodes.StartNode;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class AStar
{
    private static int DEFAULT_HV_COST = 10;
    private static int DEFAULT_DIAGONAL_COST = 14;

    private AbstractNode[][] nodeGrid;
    private PriorityQueue<AbstractNode> openNodes;
    private boolean[][] closedNodes;
    private StartNode startNode;
    private EndNode endNode;

    public AStar(AbstractNode[][] nodeGrid, StartNode startNode, EndNode endNode) {
        this.nodeGrid = nodeGrid;
        this.startNode = startNode;
        this.endNode = endNode;

        int gridRows = nodeGrid.length;
        int gridCols = nodeGrid[0].length;
        closedNodes = new boolean[gridRows][gridCols];
        openNodes = new PriorityQueue<>();
        openNodes.add(startNode);
    }

    private ArrayList<DefaultNode> getNeighbours(DefaultNode node) { //TODO: Write method.
        return new ArrayList<>();
    }

    private int getFinalScore(AbstractTraversableNode node) {
        return node.getCostFromStart() + getHeuristicScore(node);
    }

    private int getHeuristicScore(AbstractTraversableNode node) { //TODO: Write method.
        return 0;
    }






}
