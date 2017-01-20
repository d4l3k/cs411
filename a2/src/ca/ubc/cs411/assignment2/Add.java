package ca.ubc.cs411.assignment2;

public class Add extends ABE {
  public ABE lhs;
  public ABE rhs;

  public Add(ABE lhs, ABE rhs) {
    this.lhs = lhs;
    this.rhs = rhs;
  }

  @Override
  public <X> X accept(Visitor<X> v) {
      return v.visit(this); // calls Visitor<X>.visit(Add) because "this" has type Add
  }

  @Override
  public String toString() {
      return "Add(" + lhs + ", " + rhs + ')';
  }
}
