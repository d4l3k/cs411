package ca.ubc.cs411.assignment2;

public class Sub extends ABE {
  public ABE lhs;
  public ABE rhs;

  public Sub(ABE lhs, ABE rhs) {
    this.lhs = lhs;
    this.rhs = rhs;
  }

  @Override
  public <X> X accept(Visitor<X> v) {
    return v.visit(this);
  }
}
