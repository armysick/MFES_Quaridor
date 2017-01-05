package MFES_Quaridor;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Board {
  private VDMSeq matrix;
  private VDMSeq piece1coords;
  private VDMSeq piece2coords;
  private VDMSeq visited;
  private Stack stck;
  private Boolean path1;
  private Boolean path2;
  private Boolean ignore;

  public void cg_init_Board_2(final VDMSeq mateste) {

    matrix = Utils.copy(mateste);
    piece1coords = SeqUtil.seq(1L, 9L);
    piece2coords = SeqUtil.seq(17L, 9L);
  }

  public void cg_init_Board_1() {

    matrix =
        SeqUtil.seq(
            SeqUtil.seq(0L, 2L, 0L, 2L, 0L, 2L, 0L, 3L, 1L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L),
            SeqUtil.seq(2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 3L, 4L, 3L, 4L, 2L, 4L, 2L, 4L, 2L),
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
    piece1coords = SeqUtil.seq(1L, 9L);
    piece2coords = SeqUtil.seq(17L, 9L);
  }

  public Board() {

    cg_init_Board_1();
  }

  public Board(final VDMSeq mateste) {

    cg_init_Board_2(Utils.copy(mateste));
  }

  public void placeWall(final VDMSeq coords1, final VDMSeq coords2) {

    Utils.mapSeqUpdate(
        ((VDMSeq) Utils.get(matrix, ((Number) Utils.get(coords1, 1L)))),
        ((Number) Utils.get(coords1, 2L)),
        3L);
    Utils.mapSeqUpdate(
        ((VDMSeq) Utils.get(matrix, ((Number) Utils.get(coords2, 1L)))),
        ((Number) Utils.get(coords2, 2L)),
        3L);
    path1 = false;
    stck = new Stack(10000L);
    ignore = testPathExistance1(Utils.copy(piece1coords));
    path2 = false;
    stck = new Stack(10000L);
    ignore = testPathExistance2(Utils.copy(piece2coords));
  }

  public Boolean testValidWall(final VDMSeq coords1, final VDMSeq coords2) {

    if (Utils.equals(((Number) Utils.get(coords1, 1L)), ((Number) Utils.get(coords2, 1L)))) {
      if (((Number) Utils.get(coords1, 2L)).longValue()
          > ((Number) Utils.get(coords2, 2L)).longValue()) {
        if (Utils.equals(
            ((Number)
                Utils.get(
                    ((VDMSeq) Utils.get(matrix, ((Number) Utils.get(coords1, 1L)))),
                    ((Number) Utils.get(coords1, 2L)).longValue() - 1L)),
            4L)) {
          return true;
        }

      } else if (Utils.equals(
          ((Number)
              Utils.get(
                  ((VDMSeq) Utils.get(matrix, ((Number) Utils.get(coords1, 1L)))),
                  ((Number) Utils.get(coords1, 2L)).longValue() + 1L)),
          4L)) {
        return true;
      }

      return false;
    }

    if (Utils.equals(((Number) Utils.get(coords1, 2L)), ((Number) Utils.get(coords2, 2L)))) {
      if (((Number) Utils.get(coords1, 1L)).longValue()
          > ((Number) Utils.get(coords2, 1L)).longValue()) {
        if (Utils.equals(
            ((Number)
                Utils.get(
                    ((VDMSeq)
                        Utils.get(matrix, ((Number) Utils.get(coords1, 1L)).longValue() - 1L)),
                    ((Number) Utils.get(coords1, 2L)))),
            4L)) {
          return true;
        }

      } else if (Utils.equals(
          ((Number)
              Utils.get(
                  ((VDMSeq) Utils.get(matrix, ((Number) Utils.get(coords1, 1L)).longValue() + 1L)),
                  ((Number) Utils.get(coords1, 2L)))),
          4L)) {
        return true;
      }

      return false;
    }

    return false;
  }

  public Boolean testPathExistance1(final VDMSeq coords) {

    if (Utils.equals(((Number) Utils.get(coords, 1L)), 17L)) {
      path1 = true;
      return true;

    } else {
      stck.push(Utils.copy(coords));
    }

    if (((Number) Utils.get(coords, 1L)).longValue() + 2L < 18L) {
      Boolean andResult_15 = false;

      if (Utils.equals(
          ((Number)
              Utils.get(
                  ((VDMSeq) Utils.get(matrix, ((Number) Utils.get(coords, 1L)).longValue() + 1L)),
                  ((Number) Utils.get(coords, 2L)))),
          2L)) {
        if (!(stck.contains(
            SeqUtil.seq(
                ((Number) Utils.get(coords, 1L)).longValue() + 2L,
                ((Number) Utils.get(coords, 2L)))))) {
          andResult_15 = true;
        }
      }

      if (andResult_15) {
        return testPathExistance1(
            SeqUtil.seq(
                ((Number) Utils.get(coords, 1L)).longValue() + 2L,
                ((Number) Utils.get(coords, 2L))));
      }
    }

    if (((Number) Utils.get(coords, 2L)).longValue() - 2L > 0L) {
      Boolean andResult_16 = false;

      if (Utils.equals(
          ((Number)
              Utils.get(
                  ((VDMSeq) Utils.get(matrix, ((Number) Utils.get(coords, 1L)))),
                  ((Number) Utils.get(coords, 2L)).longValue() - 1L)),
          2L)) {
        if (!(stck.contains(
            SeqUtil.seq(
                ((Number) Utils.get(coords, 1L)),
                ((Number) Utils.get(coords, 2L)).longValue() - 2L)))) {
          andResult_16 = true;
        }
      }

      if (andResult_16) {
        return testPathExistance1(
            SeqUtil.seq(
                ((Number) Utils.get(coords, 1L)),
                ((Number) Utils.get(coords, 2L)).longValue() - 2L));
      }
    }

    if (((Number) Utils.get(coords, 2L)).longValue() + 2L < 18L) {
      Boolean andResult_17 = false;

      if (Utils.equals(
          ((Number)
              Utils.get(
                  ((VDMSeq) Utils.get(matrix, ((Number) Utils.get(coords, 1L)))),
                  ((Number) Utils.get(coords, 2L)).longValue() + 1L)),
          2L)) {
        if (!(stck.contains(
            SeqUtil.seq(
                ((Number) Utils.get(coords, 1L)),
                ((Number) Utils.get(coords, 2L)).longValue() + 2L)))) {
          andResult_17 = true;
        }
      }

      if (andResult_17) {
        return testPathExistance1(
            SeqUtil.seq(
                ((Number) Utils.get(coords, 1L)),
                ((Number) Utils.get(coords, 2L)).longValue() + 2L));
      }
    }

    if (((Number) Utils.get(coords, 1L)).longValue() - 2L > 0L) {
      Boolean andResult_18 = false;

      if (Utils.equals(
          ((Number)
              Utils.get(
                  ((VDMSeq) Utils.get(matrix, ((Number) Utils.get(coords, 1L)).longValue() - 1L)),
                  ((Number) Utils.get(coords, 2L)))),
          2L)) {
        if (!(stck.contains(
            SeqUtil.seq(
                ((Number) Utils.get(coords, 1L)).longValue() - 2L,
                ((Number) Utils.get(coords, 2L)))))) {
          andResult_18 = true;
        }
      }

      if (andResult_18) {
        return testPathExistance1(
            SeqUtil.seq(
                ((Number) Utils.get(coords, 1L)).longValue() - 2L,
                ((Number) Utils.get(coords, 2L))));
      }
    }

    return false;
  }

  public Boolean testPathExistance2(final VDMSeq coords) {

    if (Utils.equals(((Number) Utils.get(coords, 1L)), 1L)) {
      path2 = true;
      return true;

    } else {
      stck.push(Utils.copy(coords));
    }

    if (((Number) Utils.get(coords, 1L)).longValue() - 2L > 0L) {
      Boolean andResult_19 = false;

      if (Utils.equals(
          ((Number)
              Utils.get(
                  ((VDMSeq) Utils.get(matrix, ((Number) Utils.get(coords, 1L)).longValue() - 1L)),
                  ((Number) Utils.get(coords, 2L)))),
          2L)) {
        if (!(stck.contains(
            SeqUtil.seq(
                ((Number) Utils.get(coords, 1L)).longValue() - 2L,
                ((Number) Utils.get(coords, 2L)))))) {
          andResult_19 = true;
        }
      }

      if (andResult_19) {
        return testPathExistance2(
            SeqUtil.seq(
                ((Number) Utils.get(coords, 1L)).longValue() - 2L,
                ((Number) Utils.get(coords, 2L))));
      }
    }

    if (((Number) Utils.get(coords, 2L)).longValue() - 2L > 0L) {
      Boolean andResult_20 = false;

      if (Utils.equals(
          ((Number)
              Utils.get(
                  ((VDMSeq) Utils.get(matrix, ((Number) Utils.get(coords, 1L)))),
                  ((Number) Utils.get(coords, 2L)).longValue() - 1L)),
          2L)) {
        if (!(stck.contains(
            SeqUtil.seq(
                ((Number) Utils.get(coords, 1L)),
                ((Number) Utils.get(coords, 2L)).longValue() - 2L)))) {
          andResult_20 = true;
        }
      }

      if (andResult_20) {
        return testPathExistance2(
            SeqUtil.seq(
                ((Number) Utils.get(coords, 1L)),
                ((Number) Utils.get(coords, 2L)).longValue() - 2L));
      }
    }

    if (((Number) Utils.get(coords, 2L)).longValue() + 2L < 18L) {
      Boolean andResult_21 = false;

      if (Utils.equals(
          ((Number)
              Utils.get(
                  ((VDMSeq) Utils.get(matrix, ((Number) Utils.get(coords, 1L)))),
                  ((Number) Utils.get(coords, 2L)).longValue() + 1L)),
          2L)) {
        if (!(stck.contains(
            SeqUtil.seq(
                ((Number) Utils.get(coords, 1L)),
                ((Number) Utils.get(coords, 2L)).longValue() + 2L)))) {
          andResult_21 = true;
        }
      }

      if (andResult_21) {
        return testPathExistance2(
            SeqUtil.seq(
                ((Number) Utils.get(coords, 1L)),
                ((Number) Utils.get(coords, 2L)).longValue() + 2L));
      }
    }

    if (((Number) Utils.get(coords, 1L)).longValue() + 2L < 18L) {
      Boolean andResult_22 = false;

      if (Utils.equals(
          ((Number)
              Utils.get(
                  ((VDMSeq) Utils.get(matrix, ((Number) Utils.get(coords, 1L)).longValue() + 1L)),
                  ((Number) Utils.get(coords, 2L)))),
          2L)) {
        if (!(stck.contains(
            SeqUtil.seq(
                ((Number) Utils.get(coords, 1L)).longValue() + 2L,
                ((Number) Utils.get(coords, 2L)))))) {
          andResult_22 = true;
        }
      }

      if (andResult_22) {
        return testPathExistance2(
            SeqUtil.seq(
                ((Number) Utils.get(coords, 1L)).longValue() + 2L,
                ((Number) Utils.get(coords, 2L))));
      }
    }

    return false;
  }

  public void movePiece(final String direction, final Number nrjogador) {

    if (Utils.equals(nrjogador, 1L)) {
      if (Utils.equals(direction, "N")) {
        Utils.mapSeqUpdate(
            ((VDMSeq) Utils.get(matrix, ((Number) Utils.get(piece1coords, 1L)))),
            ((Number) Utils.get(piece1coords, 2L)),
            0L);
        Utils.mapSeqUpdate(
            ((VDMSeq) Utils.get(matrix, ((Number) Utils.get(piece1coords, 1L)).longValue() - 2L)),
            ((Number) Utils.get(piece1coords, 2L)),
            1L);
        Utils.mapSeqUpdate(
            piece1coords, 1L, ((Number) Utils.get(piece1coords, 1L)).longValue() - 2L);

      } else if (Utils.equals(direction, "O")) {
        Utils.mapSeqUpdate(
            ((VDMSeq) Utils.get(matrix, ((Number) Utils.get(piece1coords, 1L)))),
            ((Number) Utils.get(piece1coords, 2L)),
            0L);
        Utils.mapSeqUpdate(
            ((VDMSeq) Utils.get(matrix, ((Number) Utils.get(piece1coords, 1L)))),
            ((Number) Utils.get(piece1coords, 2L)).longValue() - 2L,
            1L);
        Utils.mapSeqUpdate(
            piece1coords, 2L, ((Number) Utils.get(piece1coords, 2L)).longValue() - 2L);

      } else if (Utils.equals(direction, "S")) {
        Utils.mapSeqUpdate(
            ((VDMSeq) Utils.get(matrix, ((Number) Utils.get(piece1coords, 1L)))),
            ((Number) Utils.get(piece1coords, 2L)),
            0L);
        Utils.mapSeqUpdate(
            ((VDMSeq) Utils.get(matrix, ((Number) Utils.get(piece1coords, 1L)).longValue() + 2L)),
            ((Number) Utils.get(piece1coords, 2L)),
            1L);
        Utils.mapSeqUpdate(
            piece1coords, 1L, ((Number) Utils.get(piece1coords, 1L)).longValue() + 2L);

      } else if (Utils.equals(direction, "E")) {
        Utils.mapSeqUpdate(
            ((VDMSeq) Utils.get(matrix, ((Number) Utils.get(piece1coords, 1L)))),
            ((Number) Utils.get(piece1coords, 2L)),
            0L);
        Utils.mapSeqUpdate(
            ((VDMSeq) Utils.get(matrix, ((Number) Utils.get(piece1coords, 1L)))),
            ((Number) Utils.get(piece1coords, 2L)).longValue() + 2L,
            1L);
        Utils.mapSeqUpdate(
            piece1coords, 2L, ((Number) Utils.get(piece1coords, 2L)).longValue() + 2L);
      }

    } else if (Utils.equals(nrjogador, 2L)) {
      if (Utils.equals(direction, "N")) {
        Utils.mapSeqUpdate(
            ((VDMSeq) Utils.get(matrix, ((Number) Utils.get(piece2coords, 1L)))),
            ((Number) Utils.get(piece2coords, 2L)),
            0L);
        Utils.mapSeqUpdate(
            ((VDMSeq) Utils.get(matrix, ((Number) Utils.get(piece2coords, 1L)).longValue() - 2L)),
            ((Number) Utils.get(piece2coords, 2L)),
            1L);
        Utils.mapSeqUpdate(
            piece2coords, 1L, ((Number) Utils.get(piece2coords, 1L)).longValue() - 2L);

      } else if (Utils.equals(direction, "O")) {
        Utils.mapSeqUpdate(
            ((VDMSeq) Utils.get(matrix, ((Number) Utils.get(piece2coords, 1L)))),
            ((Number) Utils.get(piece2coords, 2L)),
            0L);
        Utils.mapSeqUpdate(
            ((VDMSeq) Utils.get(matrix, ((Number) Utils.get(piece2coords, 1L)))),
            ((Number) Utils.get(piece2coords, 2L)).longValue() - 2L,
            1L);
        Utils.mapSeqUpdate(
            piece2coords, 2L, ((Number) Utils.get(piece2coords, 2L)).longValue() - 2L);

      } else if (Utils.equals(direction, "S")) {
        Utils.mapSeqUpdate(
            ((VDMSeq) Utils.get(matrix, ((Number) Utils.get(piece2coords, 1L)))),
            ((Number) Utils.get(piece2coords, 2L)),
            0L);
        Utils.mapSeqUpdate(
            ((VDMSeq) Utils.get(matrix, ((Number) Utils.get(piece2coords, 1L)).longValue() + 2L)),
            ((Number) Utils.get(piece2coords, 2L)),
            1L);
        Utils.mapSeqUpdate(
            piece2coords, 1L, ((Number) Utils.get(piece2coords, 1L)).longValue() + 2L);

      } else if (Utils.equals(direction, "E")) {
        Utils.mapSeqUpdate(
            ((VDMSeq) Utils.get(matrix, ((Number) Utils.get(piece2coords, 1L)))),
            ((Number) Utils.get(piece2coords, 2L)),
            0L);
        Utils.mapSeqUpdate(
            ((VDMSeq) Utils.get(matrix, ((Number) Utils.get(piece2coords, 1L)))),
            ((Number) Utils.get(piece2coords, 2L)).longValue() + 2L,
            1L);
        Utils.mapSeqUpdate(
            piece2coords, 2L, ((Number) Utils.get(piece2coords, 2L)).longValue() + 2L);
      }
    }
  }

  public Boolean checkPlayerOOB(final String direction, final Number nrjogador) {

    if (Utils.equals(nrjogador, 1L)) {
      if (Utils.equals(direction, "N")) {
        if (((Number) Utils.get(piece1coords, 1L)).longValue() - 2L < 0L) {
          return false;
        }

      } else if (Utils.equals(direction, "O")) {
        if (((Number) Utils.get(piece1coords, 2L)).longValue() - 2L < 0L) {
          return false;
        }

      } else if (Utils.equals(direction, "S")) {
        if (((Number) Utils.get(piece1coords, 1L)).longValue() + 2L > 17L) {
          return false;
        }

      } else if (Utils.equals(direction, "E")) {
        if (((Number) Utils.get(piece1coords, 2L)).longValue() + 2L > 17L) {
          return false;
        }
      }

    } else if (Utils.equals(nrjogador, 2L)) {
      if (Utils.equals(direction, "N")) {
        if (((Number) Utils.get(piece2coords, 1L)).longValue() - 2L < 0L) {
          return false;
        }

      } else if (Utils.equals(direction, "O")) {
        if (((Number) Utils.get(piece2coords, 2L)).longValue() - 2L < 0L) {
          return false;
        }

      } else if (Utils.equals(direction, "S")) {
        if (((Number) Utils.get(piece2coords, 1L)).longValue() + 2L > 17L) {
          return false;
        }

      } else if (Utils.equals(direction, "E")) {
        if (((Number) Utils.get(piece2coords, 2L)).longValue() + 2L > 17L) {
          return false;
        }
      }
    }

    return true;
  }

  public Number checkGameOver() {

    if (Utils.equals(((Number) Utils.get(piece1coords, 1L)), 17L)) {
      return 1L;

    } else if (Utils.equals(((Number) Utils.get(piece2coords, 1L)), 1L)) {
      return 2L;

    } else {
      return 3L;
    }
  }

  public VDMSeq getMatrix() {

    return Utils.copy(matrix);
  }

  public String toString() {

    return "Board{"
        + "matrix := "
        + Utils.toString(matrix)
        + ", piece1coords := "
        + Utils.toString(piece1coords)
        + ", piece2coords := "
        + Utils.toString(piece2coords)
        + ", visited := "
        + Utils.toString(visited)
        + ", stck := "
        + Utils.toString(stck)
        + ", path1 := "
        + Utils.toString(path1)
        + ", path2 := "
        + Utils.toString(path2)
        + ", ignore := "
        + Utils.toString(ignore)
        + "}";
  }
}
