package ca.ubc.cs411.assignment2;

public class InterpVisitor implements Visitor<Value> {
  @Override
  public Value visit(Num n) {
    return new NVal(n.n);
  }

  @Override
  public Value visit(Add ad) {
    Value lhs = ad.lhs.accept(this);
    Value rhs = ad.rhs.accept(this);
    return new NVal(lhs.ToNum() + rhs.ToNum());
  }

  @Override
  public Value visit(Sub sb) {
    Value lhs = sb.lhs.accept(this);
    Value rhs = sb.rhs.accept(this);
    return new NVal(lhs.ToNum() - rhs.ToNum());
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
    if (pred.ToBool()) {
      return v.conseq.accept(this);
    } else {
      return v.altern.accept(this);
    }
  }
}
