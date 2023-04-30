public class ShowGame {
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {

                MineSweeperGUI gui = new MineSweeperGUI();

            }
        });

    }

}
