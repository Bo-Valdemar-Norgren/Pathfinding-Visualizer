package pathfinding_visualizer.nodes;

import java.awt.*;

public abstract class Node
{
    protected Point cartesianCoordinates;
    protected NodeType nodeType;

    public Node(Point cartesianCoordinates, NodeType nodeType) {
	this.cartesianCoordinates = cartesianCoordinates;
	this.nodeType = nodeType;
    }

    public NodeType getNodeType() {
	return nodeType;
    }

    public Point getCartesianCoordinates() {
	return cartesianCoordinates;
    }
}
