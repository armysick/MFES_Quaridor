package MFES_Quaridor;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class oldBoard {
  private VDMSeq matrix;
  private VDMSeq piece1coords;
  private VDMSeq piece2coords;
  private VDMSeq visited;
  private Stack stck;
  private Boolean path1;
  private Boolean path2;

  public void cg_init_oldBoard_2(final VDMSeq mateste) {

    matrix = Utils.copy(mateste);
    piece1coords = SeqUtil.seq(1L, 9L);
    piece2coords = SeqUtil.seq(17L, 9L);
  }

  public void cg_init_oldBoard_1() {

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

  public oldBoard() {

    cg_init_oldBoard_1();
  }

  public oldBoard(final VDMSeq mateste) {

    cg_init_oldBoard_2(Utils.copy(mateste));
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
    testPathExistance1(Utils.copy(piece1coords));
    path2 = false;
    stck = new Stack(10000L);
    testPathExistance2(Utils.copy(piece2coords));
  }

  public void testPathExistance1(final VDMSeq coords) {

    if (Utils.equals(((Number) Utils.get(coords, 1L)), 17L)) {
      path1 = true;
    } else {
      stck.push(Utils.copy(coords));
    }

    if (((Number) Utils.get(coords, 1L)).longValue() + 2L < 18L) {
      Boolean andResult_40 = false;

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
          andResult_40 = true;
        }
      }

      if (andResult_40) {
        testPathExistance1(
            SeqUtil.seq(
                ((Number) Utils.get(coords, 1L)).longValue() + 2L,
                ((Number) Utils.get(coords, 2L))));
      }
    }

    if (((Number) Utils.get(coords, 2L)).longValue() - 2L > 0L) {
      Boolean andResult_41 = false;

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
          andResult_41 = true;
        }
      }

      if (andResult_41) {
        testPathExistance1(
            SeqUtil.seq(
                ((Number) Utils.get(coords, 1L)),
                ((Number) Utils.get(coords, 2L)).longValue() - 2L));
      }
    }

    if (((Number) Utils.get(coords, 2L)).longValue() + 2L < 18L) {
      Boolean andResult_42 = false;

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
          andResult_42 = true;
        }
      }

      if (andResult_42) {
        testPathExistance1(
            SeqUtil.seq(
                ((Number) Utils.get(coords, 1L)),
                ((Number) Utils.get(coords, 2L)).longValue() + 2L));
      }
    }

    if (((Number) Utils.get(coords, 1L)).longValue() - 2L > 0L) {
      Boolean andResult_43 = false;

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
          andResult_43 = true;
        }
      }

      if (andResult_43) {
        testPathExistance1(
            SeqUtil.seq(
                ((Number) Utils.get(coords, 1L)).longValue() - 2L,
                ((Number) Utils.get(coords, 2L))));
      }
    }
  }

  public void testPathExistance2(final VDMSeq coords) {

    if (Utils.equals(((Number) Utils.get(coords, 1L)), 1L)) {
      path2 = true;
    } else {
      stck.push(Utils.copy(coords));
    }

    if (((Number) Utils.get(coords, 1L)).longValue() - 2L > 0L) {
      Boolean andResult_44 = false;

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
          andResult_44 = true;
        }
      }

      if (andResult_44) {
        testPathExistance2(
            SeqUtil.seq(
                ((Number) Utils.get(coords, 1L)).longValue() - 2L,
                ((Number) Utils.get(coords, 2L))));
      }
    }

    if (((Number) Utils.get(coords, 2L)).longValue() - 2L > 0L) {
      Boolean andResult_45 = false;

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
          andResult_45 = true;
        }
      }

      if (andResult_45) {
        testPathExistance2(
            SeqUtil.seq(
                ((Number) Utils.get(coords, 1L)),
                ((Number) Utils.get(coords, 2L)).longValue() - 2L));
      }
    }

    if (((Number) Utils.get(coords, 2L)).longValue() + 2L < 18L) {
      Boolean andResult_46 = false;

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
          andResult_46 = true;
        }
      }

      if (andResult_46) {
        testPathExistance2(
            SeqUtil.seq(
                ((Number) Utils.get(coords, 1L)),
                ((Number) Utils.get(coords, 2L)).longValue() + 2L));
      }
    }

    if (((Number) Utils.get(coords, 1L)).longValue() + 2L < 18L) {
      Boolean andResult_47 = false;

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
          andResult_47 = true;
        }
      }

      if (andResult_47) {
        testPathExistance2(
            SeqUtil.seq(
                ((Number) Utils.get(coords, 1L)).longValue() + 2L,
                ((Number) Utils.get(coords, 2L))));
      }
    }
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

  public VDMSeq getMatrix() {

    return Utils.copy(matrix);
  }

  public String toString() {

    return "oldBoard{"
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
        + "}";
  }
}
