import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class UI extends JFrame {
    JLabel[][] labels;

    public UI(int[][] board) {
        initFrame();
        addLabels(board);
    }

    private void initFrame() {
        JPanel panel = new JPanel();

        this.setTitle("Sudoku Solver");
        this.setIconImage((new ImageIcon("src/img/download.png")).getImage());
        this.setResizable(false);
        this.setLocation(300, 300);
        getContentPane().add(panel);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
    }

    private void addLabels(int[][] board) {
        labels = new JLabel[9][9];

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                JLabel label;
                if(board[j][i] != 0) {
                    label = new JLabel(String.valueOf(board[j][i]));
                }
                else {
                    label = new JLabel(" ");
                }
                int x = 61 + (i * 400 / 9);
                int y = 36 + (j * 400 / 9);
                // System.out.println(x + " " + y);

                label.setBounds(x, y, 10, 10);
                getContentPane().add(label);
                labels[j][i] = label;
            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;

        g2D.setStroke(new BasicStroke(3));
        for(int i = 0; i < 4; i++) {
            g2D.draw(new Line2D.Float((float) (50 + (i * 400 / 3)), 50, (float) (50 + (i * 400 / 3)), 450));
            g2D.draw(new Line2D.Float(50, (float) (50 + (i * 400 / 3)), 450, (float) (50 + (i * 400 / 3))));
        }

        g2D.setStroke(new BasicStroke(1));
        for(int i = 0; i < 10; i++) {
            g2D.draw(new Line2D.Float((float) (50 + (i * 400 / 9)), 50, (float) (50 + (i * 400 / 9)), 450));
            g2D.draw(new Line2D.Float(50, (float) (50 + (i * 400 / 9)), 450, (float) (50 + (i * 400 / 9))));
        }
    }

    public void updateLabel(int i, int j, int num) throws InterruptedException {
        if (num != 0) {
            labels[j][i].setText(String.valueOf(num));
            Thread.sleep(1);
        }
        else {
            labels[j][i].setText(" ");
        }
    }
}
