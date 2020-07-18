package pathfinding_visualizer.nodes;

import java.awt.*;

public class DefaultNode extends Node implements Comparable<DefaultNode>
{
    private int g;
    private int f;

    public DefaultNode(Point cartesianCoordinates, NodeType nodeType) {
        super(cartesianCoordinates, nodeType);
        this.g = Integer.MAX_VALUE;
        this.f = Integer.MAX_VALUE;
    }

    @Override public int compareTo(final DefaultNode node) {
        return this.f - node.f;
    }

    public void setNodeType(NodeType nodeType) {
       this.nodeType = nodeType;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getF() {
        return f;
    }

    public void setF(final int f) {
        this.f = f;
    }
}
