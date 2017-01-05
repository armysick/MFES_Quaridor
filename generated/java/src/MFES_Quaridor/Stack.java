package MFES_Quaridor;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Stack {
  private VDMSeq contents = SeqUtil.seq();
  private Number capacity;
  private Stack tempstck;

  public void cg_init_Stack_1(final Number c) {

    capacity = c;
    return;
  }

  public Stack(final Number c) {

    cg_init_Stack_1(c);
  }

  public void clear() {

    contents = SeqUtil.seq();
  }

  public void push(final VDMSeq x) {

    contents = SeqUtil.conc(SeqUtil.seq(Utils.copy(x)), Utils.copy(contents));
  }

  public void pop() {

    contents = SeqUtil.tail(Utils.copy(contents));
  }

  public VDMSeq top() {

    return Utils.copy(((VDMSeq) contents.get(0)));
  }

  public Boolean contains(final VDMSeq coords) {

    tempstck = new Stack(10000L);
    tempstck.setContents(Utils.copy(contents));
    Boolean whileCond_1 = true;
    while (whileCond_1) {
      whileCond_1 = tempstck.notEmpty();
      if (!(whileCond_1)) {
        break;
      }

      {
        if (Utils.equals(tempstck.top(), coords)) {
          return true;

        } else {
          tempstck.pop();
        }
      }
    }

    return false;
  }

  public void setContents(final VDMSeq cont) {

    contents = Utils.copy(cont);
  }

  public Boolean notEmpty() {

    if (Utils.equals(contents.size(), 0L)) {
      return false;

    } else {
      return true;
    }
  }

  public Stack() {}

  public String toString() {

    return "Stack{"
        + "contents := "
        + Utils.toString(contents)
        + ", capacity := "
        + Utils.toString(capacity)
        + ", tempstck := "
        + Utils.toString(tempstck)
        + "}";
  }
}
