package ca.ubc.cs411.assignment2;

public class False extends ABE {
  @Override
  public <X> X accept(Visitor<X> v) {
    return v.visit(this);
  }

  @Override
  public String toString() {
      return "False()";
  }
}
