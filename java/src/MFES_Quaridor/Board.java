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
  private Object temp;

  public void cg_init_Board_2(final VDMSeq mateste) {

    matrix = Utils.copy(mateste);
    piece1coords = SeqUtil.seq(1L, 9L);
    piece2coords = SeqUtil.seq(17L, 9L);
  }

  public void cg_init_Board_1() {

    matrix =
        SeqUtil.seq(
            SeqUtil.seq(0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 1L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L),
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
            SeqUtil.seq(0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L),
            SeqUtil.seq(2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L, 4L, 2L),
            SeqUtil.seq(0L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 5L, 2L, 0L, 2L, 0L, 2L, 0L, 2L, 0L));
    piece1coords = SeqUtil.seq(1L, 9L);
    piece2coords = SeqUtil.seq(17L, 9L);
    temp = false;
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
    testPathExistance1(Utils.copy(piece1coords));
    path2 = false;
    stck = new Stack(10000L);
    testPathExistance2(Utils.copy(piece2coords));
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

  public Object testPathExistance1(final VDMSeq coords) {

    temp = false;
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
        testPathExistance1(
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
        testPathExistance1(
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
        testPathExistance1(
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
        testPathExistance1(
            SeqUtil.seq(
                ((Number) Utils.get(coords, 1L)).longValue() - 2L,
                ((Number) Utils.get(coords, 2L))));
      }
    }
    return false;
  }

  public Object testPathExistance2(final VDMSeq coords) {

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
        testPathExistance2(
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
        testPathExistance2(
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
        testPathExistance2(
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
        testPathExistance2(
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
            5L);
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
            5L);
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
            5L);
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
            5L);
        Utils.mapSeqUpdate(
            piece2coords, 2L, ((Number) Utils.get(piece2coords, 2L)).longValue() + 2L);
      }
    }
  }

  public Boolean checkPlayerOOB(final String direction, final Number nrjogador) {

    if (Utils.equals(nrjogador, 1L)) {
      if (Utils.equals(direction, "N")) {
        Boolean orResult_6 = false;

        if (((Number) Utils.get(piece1coords, 1L)).longValue() - 2L < 0L) {
          orResult_6 = true;
        } else {
          orResult_6 =
              Utils.equals(
                  ((Number)
                      Utils.get(
                          ((VDMSeq)
                              Utils.get(
                                  matrix, ((Number) Utils.get(piece1coords, 1L)).longValue() - 1L)),
                          ((Number) Utils.get(piece1coords, 2L)))),
                  3L);
        }

        if (orResult_6) {
          return false;
        }

      } else if (Utils.equals(direction, "O")) {
        Boolean orResult_7 = false;

        if (((Number) Utils.get(piece1coords, 2L)).longValue() - 2L < 0L) {
          orResult_7 = true;
        } else {
          orResult_7 =
              Utils.equals(
                  ((Number)
                      Utils.get(
                          ((VDMSeq) Utils.get(matrix, ((Number) Utils.get(piece1coords, 1L)))),
                          ((Number) Utils.get(piece1coords, 2L)).longValue() - 1L)),
                  3L);
        }

        if (orResult_7) {
          return false;
        }

      } else if (Utils.equals(direction, "S")) {
        Boolean orResult_8 = false;

        if (((Number) Utils.get(piece1coords, 1L)).longValue() + 2L > 17L) {
          orResult_8 = true;
        } else {
          orResult_8 =
              Utils.equals(
                  ((Number)
                      Utils.get(
                          ((VDMSeq)
                              Utils.get(
                                  matrix, ((Number) Utils.get(piece1coords, 1L)).longValue() + 1L)),
                          ((Number) Utils.get(piece1coords, 2L)))),
                  3L);
        }

        if (orResult_8) {
          return false;
        }

      } else if (Utils.equals(direction, "E")) {
        Boolean orResult_9 = false;

        if (((Number) Utils.get(piece1coords, 2L)).longValue() + 2L > 17L) {
          orResult_9 = true;
        } else {
          orResult_9 =
              Utils.equals(
                  ((Number)
                      Utils.get(
                          ((VDMSeq) Utils.get(matrix, ((Number) Utils.get(piece1coords, 1L)))),
                          ((Number) Utils.get(piece1coords, 2L)).longValue() + 1L)),
                  3L);
        }

        if (orResult_9) {
          return false;
        }
      }

    } else if (Utils.equals(nrjogador, 2L)) {
      if (Utils.equals(direction, "N")) {
        Boolean orResult_10 = false;

        if (((Number) Utils.get(piece2coords, 1L)).longValue() - 2L < 0L) {
          orResult_10 = true;
        } else {
          orResult_10 =
              Utils.equals(
                  ((Number)
                      Utils.get(
                          ((VDMSeq)
                              Utils.get(
                                  matrix, ((Number) Utils.get(piece2coords, 1L)).longValue() - 1L)),
                          ((Number) Utils.get(piece2coords, 2L)))),
                  3L);
        }

        if (orResult_10) {
          return false;
        }

      } else if (Utils.equals(direction, "O")) {
        Boolean orResult_11 = false;

        if (((Number) Utils.get(piece2coords, 2L)).longValue() - 2L < 0L) {
          orResult_11 = true;
        } else {
          orResult_11 =
              Utils.equals(
                  ((Number)
                      Utils.get(
                          ((VDMSeq) Utils.get(matrix, ((Number) Utils.get(piece2coords, 1L)))),
                          ((Number) Utils.get(piece2coords, 2L)).longValue() - 1L)),
                  3L);
        }

        if (orResult_11) {
          return false;
        }

      } else if (Utils.equals(direction, "S")) {
        Boolean orResult_12 = false;

        if (((Number) Utils.get(piece2coords, 1L)).longValue() + 2L > 17L) {
          orResult_12 = true;
        } else {
          orResult_12 =
              Utils.equals(
                  ((Number)
                      Utils.get(
                          ((VDMSeq)
                              Utils.get(
                                  matrix, ((Number) Utils.get(piece2coords, 1L)).longValue() + 1L)),
                          ((Number) Utils.get(piece2coords, 2L)))),
                  3L);
        }

        if (orResult_12) {
          return false;
        }

      } else {
        Boolean orResult_13 = false;

        if (Utils.equals(direction, "E")) {
          orResult_13 = true;
        } else {
          orResult_13 =
              Utils.equals(
                  ((Number)
                      Utils.get(
                          ((VDMSeq) Utils.get(matrix, ((Number) Utils.get(piece2coords, 1L)))),
                          ((Number) Utils.get(piece2coords, 2L)).longValue() + 1L)),
                  3L);
        }

        if (orResult_13) {
          if (((Number) Utils.get(piece2coords, 2L)).longValue() + 2L > 17L) {
            return false;
          }
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
        + ", temp := "
        + Utils.toString(temp)
        + "}";
  }
  
  public void printMatrix(){
	  //System.out.println(this.matrix.toArray()[1]);
	  Object[] matriz = this.matrix.toArray();
	  
	  boolean evenline = true;
	  System.out.println("\n");
	  System.out.println("     ABCDEFGHIJKLMNOPQ\n");
	  
	  int counter = 1;
	  for(Object linha : matriz){
		  System.out.print(counter + "   ");
		  if(counter < 10)
			  System.out.print(" ");
		  counter++;
		  evenline = !evenline;
		  String line = linha.toString();
		  for (int i = 0; i < line.length(); i++){
			    char element = line.charAt(i); 
			    if(element == '2')
			    	System.out.print(" ");
			    else if(element== '3' && !evenline)
			    	System.out.print("|");
			    else if(element== '3' && evenline)
			    	System.out.print("-");
			    else if(element=='0')
			    	System.out.print("X");
			    else if(element=='1')
			    	System.out.print("1");
			    else if(element=='5')
			    	System.out.print("2");
			    else if(element=='4')
			    	System.out.print(" ");
			}
		  System.out.print("\n");
	  }
  }
  
}
