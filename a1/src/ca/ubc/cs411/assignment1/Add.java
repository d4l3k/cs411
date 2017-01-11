package ca.ubc.cs411.assignment1;

public class Add extends ABE {
  private ABE lhs;
  private ABE rhs;
  public Add(ABE lhs, ABE rhs) {
    this.lhs = lhs;
    this.rhs = rhs;
  }

  public Value interp() {
    // This will throw an exception if lhs.interp() or rhs.interp() are not
    // of type NVal.
    NVal lhs = (NVal)(this.lhs.interp());
    NVal rhs = (NVal)(this.rhs.interp());
    return new NVal(lhs.n + rhs.n);
  }

  public Type typeOf() {
    Type t1 = this.lhs.typeOf();
    Type t2 = this.lhs.typeOf();
    if (t1 == Type.INT && t2 == Type.INT) {
      return Type.INT;
    } else {
      throw new Error("Add arguments must be INT");
    }
  }
}
