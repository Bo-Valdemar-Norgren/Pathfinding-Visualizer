package pathfinding_visualizer.algorithms.configurations;

import pathfinding_visualizer.Board;
import pathfinding_visualizer.nodes.DefaultNode;

import java.util.ArrayList;

public interface TraversalStrategy
{
    ArrayList<DefaultNode> getNeighbours(Board board, DefaultNode node);
    int h(DefaultNode node, DefaultNode targetNode);
}
