package ca.ubc.cs411.assignment2;

public class InterpVisitor implements Visitor<Value> {
  @Override
  public Value visit(Num n) {
    return new NVal(n.n);
  }

  @Override
  public Value visit(Add ad) {
    NVal lhs = (NVal)(ad.lhs.accept(this));
    NVal rhs = (NVal)(ad.rhs.accept(this));
    return new NVal(lhs.n + rhs.n);
  }

  @Override
  public Value visit(Sub sb) {
    NVal lhs = (NVal)(sb.lhs.accept(this));
    NVal rhs = (NVal)(sb.rhs.accept(this));
    return new NVal(lhs.n - rhs.n);
  }

  @Override
  public Value visit(True v) {
    return new TVal();
  }

  @Override
  public Value visit(False v) {
    return new FVal();
  }

  @Override
  public Value visit(If v) {
    Value pred = v.pred.accept(this);
    if (pred instanceof TVal) {
      return v.conseq.accept(this);
    } else if (pred instanceof FVal) {
      return v.altern.accept(this);
    } else {
      throw new Error("If requires TVal or FVal");
    }
  }

}
