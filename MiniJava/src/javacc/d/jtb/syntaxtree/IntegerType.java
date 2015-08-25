//
// Generated by JTB 1.3.2
//

package javacc.d.jtb.syntaxtree;

import javacc.d.jtb.visitor.GJNoArguVisitor;
import javacc.d.jtb.visitor.GJVisitor;
import javacc.d.jtb.visitor.GJVoidVisitor;
import javacc.d.jtb.visitor.Visitor;

/**
 * Grammar production:
 * f0 -> "int"
 */
public class IntegerType implements Node {
   public NodeToken f0;

   public IntegerType(NodeToken n0) {
      f0 = n0;
   }

   public IntegerType() {
      f0 = new NodeToken("int");
   }

   public void accept(Visitor v) {
      v.visit(this);
   }
   public <R,A> R accept(GJVisitor<R,A> v, A argu) {
      return v.visit(this,argu);
   }
   public <R> R accept(GJNoArguVisitor<R> v) {
      return v.visit(this);
   }
   public <A> void accept(GJVoidVisitor<A> v, A argu) {
      v.visit(this,argu);
   }
}

