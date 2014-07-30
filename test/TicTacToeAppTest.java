import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TicTacToeAppTest {
    private PrintStream out;
    private BufferedReader br;
    private TicTacToeApp game;

    @Before
    public void setUp() throws Exception {
        this.out = mock(PrintStream.class);
        this.br = mock(BufferedReader.class);
        this.game = new TicTacToeApp(out,br);
    }

    @Test
    public void shouldDrawBoardWhenRequested(){
        game.drawBoard();
        verify(out,times(3)).println("  |   |  ");
        verify(out,times(2)).println("---------");

    }

//    @Test
//    public void getPlayerOneMove(){
//        TicTacToeApp game = new TicTacToeApp(out);
//        assertThat(game.playerOneMove(1), is(1));
//    }

    @Test
    public void GetPlayerInputAsOne() throws IOException {
        when(this.br.readLine()).thenReturn("1");
        assertThat(game.getMove(br),is(1));
    }

    @Test
    public void playerOneMovesXToDesiredLocation(){
        game.playerOneMove(2);
        game.drawBoard();
        verify(out).println("  |X  |  ");
        verify(out,times(2)).println("  |   |  ");
        verify(out,times(2)).println("---------");
    }

    @Test
    public void playerTwoMovesOToDesiredLocation(){
        game.playerTwoMove(3);
        game.drawBoard();
        verify(out).println("  |   | O");
        verify(out,times(2)).println("  |   |  ");
        verify(out,times(2)).println("---------");
    }

    @Test
    public void bothPlayersMoveAndMovesShownOnBoard(){
        game.playerOneMove(1);
        game.drawBoard();
        game.playerTwoMove(2);
        game.drawBoard();
        verify(out).println("X |O  |  ");
        verify(out,atLeastOnce()).println("  |   |  ");
        verify(out,atLeastOnce()).println("---------");
    }

    @Test
    public void bothPlayersMoveToSameSpotButOnlyMostRecentPlayerPlaysThere() throws IOException {
        when(this.br.readLine()).thenReturn("5");
        game.playerOneMove(2);
        game.playerTwoMove(2);
        game.drawBoard();
        verify(out).println("  |X  |  ");
    }

    @Test
    public void stopsPlayWhenBoardIsFull(){
        game.untilFull();
        game.drawBoard();
        verify(out).println("X |O  | X");
        verify(out).println("O |O  | X");
        verify(out).println("X |X  | O");
    }
}