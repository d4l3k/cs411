package ca.ubc.cs411.assignment2;

public class Num extends ABE {
  public int n;
  public Num(int n) {
    this.n = n;
  }

  @Override
  public <X> X accept(Visitor<X> v) {
    return v.visit(this);
  }
}
