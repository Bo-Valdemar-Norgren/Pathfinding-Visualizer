package nodes;

import java.awt.*;

public abstract class AbstractNode
{
    protected Point cartesianCoordinates;
    protected NodeType nodeType;

    public AbstractNode(Point cartesianCoordinates, NodeType nodeType) {
	this.cartesianCoordinates = cartesianCoordinates;
	this.nodeType = nodeType;
    }

    public NodeType getNodeType() {
	return nodeType;
    }

    public Point getCartesianCoordinates() {
	return cartesianCoordinates;
    }

    public void setCartesianCoordinates(Point cartesianCoordinates) {
	this.cartesianCoordinates = cartesianCoordinates;
    }
}
