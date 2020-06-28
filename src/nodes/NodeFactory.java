package nodes;

import java.awt.*;

public class NodeFactory
{
    public AbstractNode createNode(Point coordinates, NodeType nodeType) {
	switch(nodeType) {
	    case DEFAULT_UNVISITED:
	        return new DefaultNode(coordinates, nodeType);
	    case WALL:
	        return new WallNode(coordinates, nodeType);
	    case START:
	        return new StartNode(coordinates, nodeType);
	    case END:
	        return new EndNode(coordinates, nodeType);
	    default:
	        throw new IllegalArgumentException("No such node is allowed to be created.");
	}
    }
}
