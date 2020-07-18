package pathfinding_visualizer.nodes;

import java.awt.*;

public class WallNode extends Node
{
    public WallNode(Point coordinates) {
	super(coordinates, NodeType.WALL);
    }
}
