package pathfinding_visualizer.nodes;

import java.awt.*;

public class WallNode extends AbstractNode
{
    public WallNode(Point cartesianCoordinates) {
	super(cartesianCoordinates, NodeType.WALL);
    }
}
