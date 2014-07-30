import java.io.*;

public class TicTacToeApp {


    public TicTacToeApp(PrintStream out, BufferedReader br) {
        this.out = out;
        this.br = br;
    }

    public static void main(String args[]){
        TicTacToeApp game = new TicTacToeApp(System.out,new BufferedReader(new InputStreamReader(System.in)));
        game.start();
    }

    private void start() {
        untilFull();
    }

    private PrintStream out;
    private BufferedReader br;
    private String [] positions = {" "," "," "," "," "," "," "," "," "};

    public void drawBoard() {
     out.println(positions[0]+" |"+positions[1]+"  | "+positions[2]);
        out.println("---------");
        out.println(positions[3]+" |"+positions[4]+"  | "+positions[5]);
        out.println("---------");
        out.println(positions[6]+" |"+positions[7]+"  | "+positions[8]);
    }

    public void playerOneMove(int move) {
        while(positions[move-1]!=" "){
            out.println("That spot is taken, try again");
            move = getMove(br);
        }
        positions[move-1]="X";
    }

    public int getMove(BufferedReader br) {
        String inputLn = "";

        try {
            inputLn = br.readLine();
            if(inputLn.length()==0){
                return -1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(inputLn);
    }

    public void playerTwoMove(int move) {
        while(positions[move-1]!=" ") {
            out.println("That spot is taken, try again");
            move = getMove(br);
        }
        positions[move-1] = "O";
    }

    public void untilFull() {
        boolean play = !isFull();
        while(play) {
            drawBoard();
            playerOneMove(getMove(br));
            drawBoard();
            playerTwoMove(getMove(br));
            drawBoard();
            play=!isFull();
        }
    }

    private boolean isFull() {
        for(int i =0;i<positions.length;i++) {
            if(positions[i].equals(" "))
            return false;
        }
        return true;
    }
}
