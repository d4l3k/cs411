package ca.ubc.cs411.assignment2;

public abstract class ABE {
  public abstract <X> X accept(Visitor<X> v);
}
