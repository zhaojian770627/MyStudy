package javacc.c.jtb.syntaxtree;
import javacc.c.jtb.visitor.TypeVisitor;
import javacc.c.jtb.visitor.Visitor;

public class Identifier {
  public String s;

  public Identifier(String as) { 
    s=as;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }

  public String toString(){
    return s;
  }
}