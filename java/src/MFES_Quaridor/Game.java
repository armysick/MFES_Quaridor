package MFES_Quaridor;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Game {
  private Player player1;
  private Player player2;
  private Board board;
  private Boolean p1turn;
  private Number gameState;

  public void cg_init_Game_1(final Player p1, final Player p2) {

    player1 = p1;
    player2 = p2;
    board = new Board();
    p1turn = true;
    gameState = 3L;
  }
  
  public Game(final Player p1, final Player p2) {

    cg_init_Game_1(p1, p2);
  }

  public void movePiece(final String direction) {

    if (p1turn) {
      board.movePiece(direction, 1L);
    } else {
      board.movePiece(direction, 2L);
    }

    p1turn = !(p1turn);
    checkGameState();
  }

  public void placeWall(final VDMSeq coords1, final VDMSeq coords2) {

    if (p1turn) {
      player1.RemoveFromStack();
    } else {
      player2.RemoveFromStack();
    }

    board.placeWall(Utils.copy(coords1), Utils.copy(coords2));
    p1turn = !(p1turn);
  }

  public Boolean hasWallsToUse() {

    if (p1turn) {
      if (player1.GetNumberOfWallsOnStack().longValue() > 0L) {
        return true;
      }

    } else {
      if (player2.GetNumberOfWallsOnStack().longValue() > 0L) {
        return true;
      }
    }

    return false;
  }

  public void checkGameState() {

    gameState = board.checkGameOver();
  }

  public Number getGameState() {

    return gameState;
  }

  public void setGameState(final Number state) {

    gameState = state;
  }

  public Board getBoard() {

    return board;
  }

  public Game() {}

  public String toString() {

    return "Game{"
        + "player1 := "
        + Utils.toString(player1)
        + ", player2 := "
        + Utils.toString(player2)
        + ", board := "
        + Utils.toString(board)
        + ", p1turn := "
        + Utils.toString(p1turn)
        + ", gameState := "
        + Utils.toString(gameState)
        + "}";
  }
  
  
  public void printBoard(){
	  this.board.printMatrix();
	  
	  System.out.println("\n" + player1.GetName() + "\'s remaining walls: " + player1.GetNumberOfWallsOnStack());
	  System.out.println(player2.GetName() + "\'s remaining walls: " + player2.GetNumberOfWallsOnStack());
  }
}
