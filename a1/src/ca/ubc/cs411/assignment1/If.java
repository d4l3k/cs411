package ca.ubc.cs411.assignment1;

public class If extends ABE {
  private ABE pred;
  private ABE conseq;
  private ABE altern;

  public If(ABE pred, ABE conseq, ABE altern) {
    this.pred = pred;
    this.conseq = conseq;
    this.altern = altern;
  }

  public Value interp() {
    Value pred = this.pred.interp();
    if (pred instanceof TVal) {
      return this.conseq.interp();
    } else if (pred instanceof FVal) {
      return this.altern.interp();
    } else {
      throw new Error("If requires TVal or FVal");
    }
  }

  public Type typeOf() {
    Type t1 = this.pred.typeOf();
    Type t2 = this.conseq.typeOf();
    Type t3 = this.altern.typeOf();

    if (t1 == Type.BOOL) {
      if (t2 == t3) {
        return t2;
      } else {
        throw new Error("type mismatch for if branches");
      }
    } else {
      throw new Error("non-Bool predicate type");
    }
  }
}
