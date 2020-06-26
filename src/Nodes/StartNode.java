package Nodes;

import Nodes.AbstractNode;

import java.awt.*;

public class StartNode extends AbstractNode
{

    public StartNode(final Point cartesianCoordinates, final NodeType nodeType) {
	super(cartesianCoordinates, nodeType);
    }

    @Override public NodeType getNodeType() {
	return nodeType;
    }

    @Override public Point getCartesianCoordinates() {
	return cartesianCoordinates;
    }
}
