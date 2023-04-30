import javax.swing.JFrame;

public class MineSweeperGUI extends JFrame {
    public MineSweeperGUI() {
        JFrame frame = new JFrame("Minesweeper");
        MineSweeperJpanel minesweeper = new MineSweeperJpanel();
        frame.add(minesweeper);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
