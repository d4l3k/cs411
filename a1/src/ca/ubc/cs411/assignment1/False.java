package ca.ubc.cs411.assignment1;

public class False extends ABE {
  public Value interp() {
    return new TVal();
  }

  public Type typeOf() {
    return Type.BOOL;
  }
}
