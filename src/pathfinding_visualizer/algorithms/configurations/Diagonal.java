package pathfinding_visualizer.algorithms.configurations;

import pathfinding_visualizer.Board;
import pathfinding_visualizer.algorithms.configurations.TraversalStrategy;
import pathfinding_visualizer.nodes.DefaultNode;
import pathfinding_visualizer.nodes.Node;
import pathfinding_visualizer.nodes.NodeType;

import java.awt.*;
import java.util.ArrayList;

public class Diagonal implements TraversalStrategy

{
    public ArrayList<DefaultNode> getNeighbours(Board board, DefaultNode node) {
	Point nodeCoordinates = node.getCartesianCoordinates();
	int nodeX = nodeCoordinates.x;
	int nodeY = nodeCoordinates.y;

	int gridRows = board.getBoardHeight();
	int gridCols = board.getBoardWidth();

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

    public int h(Point coordinatesOne, Point coordinatesTwo) { // Uniform cost diagonal distance.
	int dx = Math.abs(coordinatesOne.x - coordinatesTwo.x);
	int dy = Math.abs(coordinatesOne.y - coordinatesTwo.y);

	return Math.max(dx, dy);
    }
}
