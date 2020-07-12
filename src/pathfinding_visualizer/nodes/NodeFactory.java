package pathfinding_visualizer.nodes;

import java.awt.*;

public class NodeFactory
{
    public Node createNode(Point coordinates, NodeType nodeType) {
	switch(nodeType) {
	    case DEFAULT_UNVISITED: case START: case END:
		return new DefaultNode(coordinates, nodeType);
	    case WALL:
	        return new WallNode(coordinates);
	    default:
	        throw new IllegalArgumentException("No such node exists.");
	}
    }
}
