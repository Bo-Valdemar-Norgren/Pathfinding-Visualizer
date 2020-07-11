package pathfinding_visualizer.nodes;

import java.awt.*;

public class NodeFactory
{
    public AbstractNode createNode(Point coordinates, NodeType nodeType) {
	switch(nodeType) {
	    case DEFAULT_UNVISITED:
	    case START:
	    case END:
		return new DefaultNode(coordinates, nodeType);
	    case WALL:
	        return new WallNode(coordinates, nodeType);
	    default:
	        throw new IllegalArgumentException("No such node exists.");
	}
    }
}
