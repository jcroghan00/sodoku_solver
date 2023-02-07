import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * demo board
 *
 *          return new int[][]{
 *                 {0, 0, 6, 0, 0, 0, 8, 0, 0},
 *                 {7, 0, 0, 3, 0, 0, 0, 6, 4},
 *                 {0, 0, 0, 0, 0, 4, 9, 0, 0},
 *                 {0, 0, 7, 0, 0, 8, 0, 1, 2},
 *                 {3, 0, 0, 0, 5, 0, 0, 0, 0},
 *                 {0, 0, 0, 0, 0, 0, 6, 0, 0},
 *                 {0, 0, 1, 0, 0, 2, 0, 4, 7},
 *                 {0, 2, 0, 8, 0, 0, 0, 0, 0},
 *                 {0, 0, 0, 0, 0, 0, 0, 0, 9}
 *         };
 */

public class Main {
    static UI ui;
    private static final int GRID_SIZE = 9;
    private static int placements;

    public static void main(String[] args) {
        int[][] board = get_board();

        placements = 0;

        long time1 = System.currentTimeMillis();
        solveBoard(board);
        long time2 = System.currentTimeMillis();

        ui = new UI(board);

        System.out.println("Time in ms: " + (time2 - time1));
        System.out.println("Total Placements Attempted: " + placements);

        ui.setVisible(true);
    }

    private static int[][] get_board() {
        //Document doc = null;
        
        //try {
            //doc = Jsoup.connect("https://www.nytimes.com/puzzles/sudoku/hard").get();
        //} catch (IOException e) {
            //e.printStackTrace();
            //System.exit(1);
        //}

        //System.out.println(doc.html());

        //Element table = doc.select("div.su-board").first();

        //assert table != null;
        //Elements cells = table.select("div[data-cell");

        //for (Element cell : cells) {
            //System.out.println(cell.attr("aria-label"));
        //}

        // https://stackoverflow.com/questions/54101049/jsoup-doesnt-load-the-whole-html

        //WebClient webClient = new WebClient(BrowserVersion.CHROME);
        //webClient.getOptions().setJavaScriptEnabled(true); // enable javascript
        //webClient.getOptions().setThrowExceptionOnScriptError(false); //even if there is error in js continue
        //webClient.waitForBackgroundJavaScript(5000); // important! wait until javascript finishes rendering
        //HtmlPage page = webClient.getPage(url);

        return new int[][]{
                {9, 0, 5, 3, 0, 0, 0, 0, 0},
                {0, 0, 8, 0, 0, 0, 9, 0, 0},
                {0, 4, 0, 0, 0, 0, 0, 1, 3},
                {5, 0, 1, 0, 8, 0, 0, 6, 0},
                {0, 0, 7, 0, 9, 0, 0, 0, 0},
                {0, 0, 0, 4, 7, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 9, 0},
                {0, 8, 0, 9, 6, 0, 0, 3, 0},
                {7, 0, 0, 0, 0, 0, 0, 0, 5}
        };
    }

    private static boolean isNumberInRow(int[][] board, int number, int row) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInCol(int[][] board, int number, int col) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][col] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInBox(int[][] board, int number, int col, int row) {
        int localBoxRow = row - row % 3;
        int localBoxCol = col - col % 3;

        for (int i = localBoxRow; i < localBoxRow + 3; i++) {
            for (int j = localBoxCol; j < localBoxCol + 3; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isValidPlacement(int[][] board, int number, int row, int col) {
        return !isNumberInRow(board, number, row) && !isNumberInCol(board, number, col) && !isNumberInBox(board, number, col, row);
    }

    private static boolean solveBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= GRID_SIZE; num++) {
                        if (isValidPlacement(board, num, row, col)) {
                            board[row][col] = num;
                            placements++;
                            if (solveBoard(board)) {
                                return true;
                            }
                            else {
                                board[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
