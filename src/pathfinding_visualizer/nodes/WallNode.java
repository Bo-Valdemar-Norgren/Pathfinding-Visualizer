package pathfinding_visualizer.nodes;

import java.awt.*;

public class WallNode extends AbstractNode
{
    public WallNode(final Point cartesianCoordinates, final NodeType nodeType) {
	super(cartesianCoordinates, nodeType);
    }
}
