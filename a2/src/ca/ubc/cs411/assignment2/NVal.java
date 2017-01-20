package ca.ubc.cs411.assignment2;

public class NVal extends Value {
  public int n;
  public NVal(int n) {
    this.n = n;
  }

  @Override
  public int ToNum() {
    return n;
  }

  @Override
  public String toString() {
      return "NVal(" + n + ')';
  }
}
