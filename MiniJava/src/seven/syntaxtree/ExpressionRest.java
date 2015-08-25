//
// Generated by JTB GJ1.1.2
//

package seven.syntaxtree;

import seven.visitor.GJNoArguVisitor;
import seven.visitor.GJVisitor;
import seven.visitor.GJVoidVisitor;
import seven.visitor.Visitor;

/**
 * Grammar production:
 * f0 -> ","
 * f1 -> Expression()
 */
public class ExpressionRest implements Node {
   public NodeToken f0;
   public Expression f1;

   public ExpressionRest(NodeToken n0, Expression n1) {
      f0 = n0;
      f1 = n1;
   }

   public ExpressionRest(Expression n0) {
      f0 = new NodeToken(",");
      f1 = n0;
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
