package pathfinding_visualizer;

import pathfinding_visualizer.algorithms.Algorithm;
import pathfinding_visualizer.algorithms.AlgorithmFactory;
import pathfinding_visualizer.algorithms.AlgorithmType;
import pathfinding_visualizer.algorithms.configurations.Diagonal;
import pathfinding_visualizer.algorithms.configurations.TraversalStrategy;
import pathfinding_visualizer.algorithms.configurations.VerticalHorizontal;
import pathfinding_visualizer.nodes.DefaultNode;
import pathfinding_visualizer.nodes.NodeType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class GUI extends JFrame {
    private Board board;
    private AlgorithmFactory algorithmFactory;
    private NodeType selectedNodeType;
    private Algorithm algorithm;
    private TraversalStrategy traversalStrategy;
    private int mx;
    private int my;

    public GUI() {
        this.board = new Board();
        this.add(board);
        this.traversalStrategy = new VerticalHorizontal(); //VH by default
        this.selectedNodeType = NodeType.WALL;
        this.algorithmFactory = new AlgorithmFactory();
        setAlgorithm(AlgorithmType.ASTAR);

        JMenuBar menuBar = createMenu();
        this.setJMenuBar(menuBar);

        MouseMoves move = new MouseMoves();
        this.addMouseMotionListener(move);

        MouseClick click = new MouseClick();
        this.addMouseListener(click);


        this.setTitle("Pathfinding Algorithm Visualizer - ASTAR");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public class MouseMoves implements MouseMotionListener
    {

        @Override public void mouseDragged(final MouseEvent mouseEvent) {
            updateCursorCoordinates(mouseEvent);
            if (SwingUtilities.isLeftMouseButton(mouseEvent)) {
                updateSelectedNode();
            }
            else if (SwingUtilities.isRightMouseButton(mouseEvent)) {
                replaceSelectedNodeWithDefaultNode();
            }

        }

        @Override public void mouseMoved(final MouseEvent mouseEvent) {
            updateCursorCoordinates(mouseEvent);
        }
    }

    public class MouseClick implements MouseListener
    {

        @Override public void mouseClicked(final MouseEvent mouseEvent) {
            if (SwingUtilities.isLeftMouseButton(mouseEvent)) {
                updateSelectedNode();
            }
            else if (SwingUtilities.isRightMouseButton(mouseEvent)) {
                replaceSelectedNodeWithDefaultNode();
            }
        }

        @Override public void mousePressed(final MouseEvent mouseEvent) {

        }

        @Override public void mouseReleased(final MouseEvent mouseEvent) {

        }

        @Override public void mouseEntered(final MouseEvent mouseEvent) {

        }

        @Override public void mouseExited(final MouseEvent mouseEvent) {

        }
    }

    private JMenuBar createMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu nodeMenu = new JMenu("Nodes"), algoMenu = new JMenu("Algorithms"), startMenu = new JMenu("Start");
        JMenuItem wallNode = new JMenuItem("Wall node"), startNode = new JMenuItem("Start node"), endNode = new JMenuItem("End node");

        wallNode.addActionListener(e->setSelectedNodeType(NodeType.WALL));
        startNode.addActionListener(e->setSelectedNodeType(NodeType.START));
        endNode.addActionListener(e->setSelectedNodeType(NodeType.END));

        nodeMenu.add(wallNode);
        nodeMenu.add(startNode);
        nodeMenu.add(endNode);

        JMenuItem start = new JMenuItem("Start"), reset = new JMenuItem("Reset");

        reset.addActionListener(actionEvent -> board.resetBoard());
        start.addActionListener(actionEvent -> {
            try {
                ArrayList<DefaultNode> visitedNodes = algorithm.startSearch();
                board.showResults(visitedNodes);
            } catch (Exception e) {
                System.out.println("Couldn't run algorithm: " + algorithm.getClass());
            }

        });

        startMenu.add(start);
        startMenu.add(reset);

        JMenuItem AStar = new JMenuItem("A* Search"), dijkstra = new JMenuItem("Dijkstra's algorithm");
        JMenuItem BFS = new JMenuItem("Breadth First Search"), DFS = new JMenuItem("Depth First Search");

        AStar.addActionListener(actionEvent -> setAlgorithm(AlgorithmType.ASTAR));
        dijkstra.addActionListener(actionEvent -> setAlgorithm(AlgorithmType.DIJKSTRA));
        BFS.addActionListener(actionEvent -> setAlgorithm(AlgorithmType.BFS));
        DFS.addActionListener(actionEvent -> setAlgorithm(AlgorithmType.DFS));

        algoMenu.add(AStar);
        algoMenu.add(dijkstra);
        algoMenu.add(BFS);
        algoMenu.add(DFS);

        menuBar.add(startMenu);
        menuBar.add(nodeMenu);
        menuBar.add(algoMenu);

        JCheckBox traversalStrategyBox = new JCheckBox("Allow diagonals");
        traversalStrategyBox.addItemListener(itemEvent -> {
            if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                traversalStrategy = new Diagonal();
                algorithm.setTraversalStrategy(traversalStrategy);
            } else if (itemEvent.getStateChange() == ItemEvent.DESELECTED) {
                traversalStrategy = new VerticalHorizontal();
                algorithm.setTraversalStrategy(traversalStrategy);
            }
        });
        menuBar.add(traversalStrategyBox);

        JSlider algorithmSpeed = new JSlider(JSlider.HORIZONTAL, 10, 350, Timer.DEFAULT_TIMER_DELAY);
        algorithmSpeed.setInverted(true);
        algorithmSpeed.setToolTipText("Adjust this slider to change the algorithm speed.");
        algorithmSpeed.addChangeListener(changeEvent -> board.setTimer(algorithmSpeed.getValue()));
        menuBar.add(algorithmSpeed);
        return menuBar;
    }

    private void setAlgorithm(AlgorithmType algoType) {
        algorithm = algorithmFactory.createAlgorithm(board, algoType, traversalStrategy);
        this.setTitle("Pathfinding Algorithm Visualizer - " + algoType);
    }

    private void updateSelectedNode() {
        if (cursorOnNode()) {
            Point coordinates = getSelectedNodeCoordinates();
            board.setNode(coordinates, selectedNodeType);
        }
    }

    private void replaceSelectedNodeWithDefaultNode() {
        if (cursorOnNode()) {
            Point coordinates = getSelectedNodeCoordinates();
            board.setNode(coordinates, NodeType.UNVISITED);
        }
    }

    private boolean cursorOnNode() {
        Point point = getSelectedNodeCoordinates();
        return point.x != -1 && point.y != -1;
    }

    private Point getSelectedNodeCoordinates() {
        int boardHeight = board.getBoardHeight();
        int boardWidth = board.getBoardWidth();
        int squareSize = Board.SQUARESIZE;

        for (int x = 0; x < boardHeight; x++) {
            for (int y = 0; y < boardWidth; y++) {
                if (mx >= x*squareSize+getInsets().left && mx < x*squareSize+squareSize+getInsets().right && my >= y*squareSize+getInsets().bottom && my < y*squareSize+squareSize+getInsets().top+22) { //Magic number represents the height of the menu bar.
                    return new Point(x,y);
                }
            }
        }
        return new Point(-1,-1);
    }
    public void updateCursorCoordinates(MouseEvent mouseEvent) {
        mx = mouseEvent.getX();
        my = mouseEvent.getY();
    }
    private void setSelectedNodeType(NodeType nodeType) {
        selectedNodeType = nodeType;
    }
}
