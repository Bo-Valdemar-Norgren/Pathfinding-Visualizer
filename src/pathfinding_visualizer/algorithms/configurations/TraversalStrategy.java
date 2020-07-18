package pathfinding_visualizer.algorithms.configurations;

import pathfinding_visualizer.Board;
import pathfinding_visualizer.nodes.DefaultNode;

import java.awt.*;
import java.util.ArrayList;

public interface TraversalStrategy
{
    ArrayList<DefaultNode> getNeighbours(Board board, DefaultNode node);
    int h(Point coordinatesOne, Point coordinatesTwo);
}
