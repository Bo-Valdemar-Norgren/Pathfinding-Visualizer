package pathfinding_visualizer.algorithms.configurations;

import pathfinding_visualizer.Board;
import pathfinding_visualizer.algorithms.configurations.TraversalStrategy;
import pathfinding_visualizer.nodes.DefaultNode;
import pathfinding_visualizer.nodes.NodeType;

import java.awt.*;
import java.util.ArrayList;

public class VerticalHorizontal implements TraversalStrategy
{
    public ArrayList<DefaultNode> getNeighbours(Board board, DefaultNode node) {
	Point nodeCoordinates = node.getCoordinates();
	int nodeX = nodeCoordinates.x;
	int nodeY = nodeCoordinates.y;

	int gridRows = board.getBoardHeight();
	int gridCols = board.getBoardWidth();

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

	return neighbours;
    }

    public int h(Point coordinatesOne, Point coordinatesTwo) { //Manhattan distance.
	int dx = Math.abs(coordinatesOne.x - coordinatesTwo.x);
	int dy = Math.abs(coordinatesOne.y - coordinatesTwo.y);

	return dx + dy;
	}
    }
