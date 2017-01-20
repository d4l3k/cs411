package ca.ubc.cs411.assignment2;

public class If extends ABE {
  public ABE pred;
  public ABE conseq;
  public ABE altern;

  public If(ABE pred, ABE conseq, ABE altern) {
    this.pred = pred;
    this.conseq = conseq;
    this.altern = altern;
  }

  @Override
  public <X> X accept(Visitor<X> v) {
    return v.visit(this);
  }

  @Override
  public String toString() {
    return "If(" + pred + ", " + conseq + "," + altern + ')';
  }
}
