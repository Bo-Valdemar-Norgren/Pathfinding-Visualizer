package pathfinding_visualizer.nodes;

import java.awt.*;

public class DefaultNode extends AbstractTraversableNode
{
    public DefaultNode(final Point cartesianCoordinates, final NodeType nodeType) {
	super(cartesianCoordinates, nodeType);
    }
    public void setNodeTypeVisited() {
        this.nodeType = NodeType.DEFAULT_VISITED;
    }
}
