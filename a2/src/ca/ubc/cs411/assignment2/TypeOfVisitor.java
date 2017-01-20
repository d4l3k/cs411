package ca.ubc.cs411.assignment2;

public class TypeOfVisitor implements Visitor<Type> {
  @Override
  public Type visit(Num n) {
    return Type.INT;
  }

  @Override
  public Type visit(Add ad) {
    Type t1 = ad.lhs.accept(this);
    Type t2 = ad.rhs.accept(this);
    if (t1 == Type.INT && t2 == Type.INT) {
      return Type.INT;
    } else {
      throw new Error("Add arguments must be INT");
    }
  }

  @Override
  public Type visit(Sub sb) {
    Type t1 = sb.lhs.accept(this);
    Type t2 = sb.rhs.accept(this);
    if (t1 == Type.INT && t2 == Type.INT) {
      return Type.INT;
    } else {
      throw new Error("Add arguments must be INT");
    }
  }

  @Override
  public Type visit(True v) {
    return Type.BOOL;
  }

  @Override
  public Type visit(False v) {
    return Type.BOOL;
  }

  @Override
  public Type visit(If v) {
    Type t1 = v.pred.accept(this);
    Type t2 = v.conseq.accept(this);
    Type t3 = v.altern.accept(this);

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
