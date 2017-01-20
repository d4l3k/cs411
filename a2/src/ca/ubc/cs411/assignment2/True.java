package ca.ubc.cs411.assignment2;

public class True extends ABE {
  @Override
  public <X> X accept(Visitor<X> v) {
    return v.visit(this);
  }
}
