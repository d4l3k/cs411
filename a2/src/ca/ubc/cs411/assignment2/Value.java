package ca.ubc.cs411.assignment2;

public abstract class Value {
  public int ToNum() {
    throw new Error("to-num: Bad number: " + this);
  }

  public boolean ToBool() {
    throw new Error("to-bool: Bad Boolean: " + this);
  }
}
