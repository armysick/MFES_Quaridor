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

    Board b = new Board();
    b.placeWall(SeqUtil.seq(1L, 2L), SeqUtil.seq(1L, 4L));
  }

  public void emptyWallStack() {

    Board b = new Board();
    Player p1 = new Player(0L);
    Player p2 = new Player(0L);
    Game game = new Game(p1, p2);
    game.placeWall(SeqUtil.seq(1L, 2L), SeqUtil.seq(3L, 2L));
  }

  public void invalidPieceMovementAfterGameEnd() {

    Board b = new Board();
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

    Board b = new Board();
    b.movePiece("S", 1L);
    assertEqual(((Number) Utils.get(((VDMSeq) Utils.get(b.getMatrix(), 3L)), 9L)), 1L);
  }

  public void testAll() {

    invalidDirectionMoving();
    testBadMatrix();
    invalidPieceMovementAfterGameEnd();
    emptyWallStack();
    invalidWallPlacement();
    removeFromStack();
    validMovement();
    testGoodMatrix();
  }

  public Tests() {}

  public String toString() {

    return "Tests{}";
  }
}
