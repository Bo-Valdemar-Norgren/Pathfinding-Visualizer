package Nodes;

import java.awt.*;

public class NodeFactory
{
    public Node createNode(Point coordinates, NodeType nodeType) {
	switch(nodeType) {
	    case UNVISITED:
	        return new DefaultNode(coordinates, nodeType, false);
	    case VISITED:
	        return new DefaultNode(coordinates, nodeType, true);
	    case WALL:
	        return new WallNode(coordinates, nodeType);
	    case START:
	        return new StartNode(coordinates, nodeType);
	    case END:
	        return new EndNode(coordinates, nodeType);
	    default:
	        throw new IllegalArgumentException("No such node type exists.");
	}
    }
}
