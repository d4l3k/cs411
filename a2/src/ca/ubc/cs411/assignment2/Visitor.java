package ca.ubc.cs411.assignment2;

/**
 * Created by ronaldgarcia on 2017-01-12.
 */

public interface Visitor<X> {
    X visit(Num nm);
    X visit(Add ad);
    X visit(Sub sb);
    X visit(True v);
    X visit(False v);
    X visit(If v);
}
