package MFES_Quaridor;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Player {
  private String name = SeqUtil.toStr(SeqUtil.seq());
  private Number numberofwallsonstack;

  public void cg_init_Player_3(final Number nrWalls) {

    name = "NotSpecified";
    numberofwallsonstack = nrWalls;
    return;
  }

  public void cg_init_Player_2(final String playerName) {

    name = playerName;
    numberofwallsonstack = 10L;
    return;
  }

  public void cg_init_Player_1() {

    name = "NotSpecified";
    numberofwallsonstack = 10L;
    return;
  }

  public Player() {

    cg_init_Player_1();
  }

  public Player(final String playerName) {

    cg_init_Player_2(playerName);
  }

  public Player(final Number nrWalls) {

    cg_init_Player_3(nrWalls);
  }

  public String GetName() {

    return name;
  }

  public Number GetNumberOfWallsOnStack() {

    return numberofwallsonstack;
  }

  public void RemoveFromStack() {

    numberofwallsonstack = numberofwallsonstack.longValue() - 1L;
  }

  public String toString() {

    return "Player{"
        + "name := "
        + Utils.toString(name)
        + ", numberofwallsonstack := "
        + Utils.toString(numberofwallsonstack)
        + "}";
  }
}
