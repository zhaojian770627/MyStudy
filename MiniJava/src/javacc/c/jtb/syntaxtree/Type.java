package javacc.c.jtb.syntaxtree;
import javacc.c.jtb.visitor.TypeVisitor;
import javacc.c.jtb.visitor.Visitor;

public abstract class Type {
  public abstract void accept(Visitor v);
  public abstract Type accept(TypeVisitor v);
}