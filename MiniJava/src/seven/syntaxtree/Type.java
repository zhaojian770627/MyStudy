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
 * f0 -> ArrayType()
 *       | BooleanType()
 *       | IntegerType()
 *       | Identifier()
 */
public class Type implements Node {
   public NodeChoice f0;

   public Type(NodeChoice n0) {
      f0 = n0;
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
