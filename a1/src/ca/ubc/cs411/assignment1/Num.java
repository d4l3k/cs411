package ca.ubc.cs411.assignment1;

public class Num extends ABE {
  private int n;
  public Num(int n) {
    this.n = n;
  }

  public Value interp() {
    return new NVal(this.n);
  }

  public Type typeOf() {
    return Type.INT;
  }
}
