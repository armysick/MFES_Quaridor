package MFES_Quaridor;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Tests extends MyTestCase {
  public void testBadMatrix() {

    VDMSeq mat =
        SeqUtil.seq(
            SeqUtil.seq(0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 1L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L),
            SeqUtil.seq(2L, 2L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L),
            SeqUtil.seq(0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L),
            SeqUtil.seq(2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L),
            SeqUtil.seq(0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L),
            SeqUtil.seq(2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L),
            SeqUtil.seq(0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L),
            SeqUtil.seq(2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L),
            SeqUtil.seq(0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L),
            SeqUtil.seq(2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L),
            SeqUtil.seq(0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L),
            SeqUtil.seq(2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L),
            SeqUtil.seq(0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L),
            SeqUtil.seq(2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L),
            SeqUtil.seq(0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L),
            SeqUtil.seq(2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L),
            SeqUtil.seq(0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 5L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L));
    Board b2 = new Board();
    Board b = new Board(Utils.copy(mat));
    assertEqual(1L, ((Number) Utils.get(((VDMSeq) Utils.get(b2.getMatrix(), 1L)), 2L)));
  }

  public void testGoodMatrix() {

    Board b2 = new Board();
    assertEqual(1L, ((Number) Utils.get(((VDMSeq) Utils.get(b2.getMatrix(), 1L)), 9L)));
  }

  public void invalidDirectionMoving() {

    Board b = new Board();
    b.movePiece("U", 1L);
  }

  public void invalidWallPlacement() {

    Player p1 = new Player(0L);
    Player p2 = new Player(0L);
    Game game = new Game(p1, p2);
    game.placeWall(SeqUtil.seq(1L, 2L), SeqUtil.seq(1L, 4L));
  }

  public void emptyWallStack() {

    Board b = new Board();
    Player p1 = new Player(0L);
    Player p2 = new Player(0L);
    Game game = new Game(p1, p2);
    game.placeWall(SeqUtil.seq(1L, 2L), SeqUtil.seq(3L, 2L));
  }

  public void invalidPieceMovementAfterGameEnd() {

    Player p1 = new Player();
    Player p2 = new Player();
    Game game = new Game(p1, p2);
    game.setGameState(1L);
    game.movePiece("N");
  }

  public void removeFromStack() {

    Player p = new Player();
    p.RemoveFromStack();
    assertEqual(9L, p.GetNumberOfWallsOnStack());
  }

  public void validMovement() {

    Player p1 = new Player(0L);
    Player p2 = new Player(0L);
    Game game = new Game(p1, p2);
    game.movePiece("S");
    game.movePiece("N");
    game.movePiece("O");
    game.movePiece("E");
    game.movePiece("N");
    game.movePiece("S");
    game.movePiece("E");
    game.movePiece("O");
    assertEqual(
        ((Number) Utils.get(((VDMSeq) Utils.get(game.getBoard().getMatrix(), 1L)), 9L)), 1L);
    assertEqual(
        ((Number) Utils.get(((VDMSeq) Utils.get(game.getBoard().getMatrix(), 17L)), 9L)), 5L);
  }

  public void validWallPlacement() {

    Player p1 = new Player();
    Player p2 = new Player();
    Game game = new Game(p1, p2);
    VDMSeq mat =
        SeqUtil.seq(
            SeqUtil.seq(0L, 2L, 0L, 2L, 0L, 3L, 0L, 2L, 1L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L),
            SeqUtil.seq(2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 3L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L),
            SeqUtil.seq(0L, 2L, 0L, 3L, 0L, 3L, 0L, 3L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L),
            SeqUtil.seq(2L, 4L, 2L, 4L, 3L, 4L, 3L, 4L, 3L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L),
            SeqUtil.seq(0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L),
            SeqUtil.seq(2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L),
            SeqUtil.seq(0L, 3L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L),
            SeqUtil.seq(2L, 4L, 3L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L),
            SeqUtil.seq(0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L),
            SeqUtil.seq(2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L),
            SeqUtil.seq(0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L),
            SeqUtil.seq(2L, 4L, 3L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L),
            SeqUtil.seq(0L, 3L, 0L, 3L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L),
            SeqUtil.seq(2L, 4L, 2L, 4L, 2L, 4L, 3L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L),
            SeqUtil.seq(0L, 3L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L),
            SeqUtil.seq(2L, 4L, 2L, 4L, 3L, 4L, 3L, 4L, 3L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L),
            SeqUtil.seq(0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 5L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L));
    Board b = new Board(Utils.copy(mat));
    game.placeWall(SeqUtil.seq(1L, 2L), SeqUtil.seq(3L, 2L));
    assertEqual(
        ((Number) Utils.get(((VDMSeq) Utils.get(game.getBoard().getMatrix(), 1L)), 2L)), 3L);
    game.placeWall(SeqUtil.seq(6L, 1L), SeqUtil.seq(6L, 3L));
    assertEqual(
        ((Number) Utils.get(((VDMSeq) Utils.get(game.getBoard().getMatrix(), 6L)), 1L)), 3L);
    b.placeWall(SeqUtil.seq(6L, 1L), SeqUtil.seq(6L, 3L));
  }

  public void testAll() {

    removeFromStack();
    validMovement();
    validWallPlacement();
    testGoodMatrix();
  }

  public Tests() {}

  public String toString() {

    return "Tests{}";
  }
}
