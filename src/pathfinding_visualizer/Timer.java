package pathfinding_visualizer;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Timer
{
    public static int DEFAULT_TIMER_DELAY = 50;
    private javax.swing.Timer timer;
    private Board board;
    public Timer(Board board) {
        final Action paintNextNodeFromAlgoResult = new AbstractAction()
        {
            @Override public void actionPerformed(final ActionEvent actionEvent) {
                board.nextResultNode();
            }
        };
        this.timer = new javax.swing.Timer(DEFAULT_TIMER_DELAY, paintNextNodeFromAlgoResult);
        this.board = board;
        timer.setCoalesce(false);
    }

    public void changeTimerDelay(int newDelay) {
        timer.setDelay(newDelay);
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }


}
