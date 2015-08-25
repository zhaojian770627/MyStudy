package seven.Tree;

public interface ResultVisitor<R> {
    public R visit(SEQ n);
    public R visit(LABEL n);
    public R visit(JUMP n);
    public R visit(CJUMP n);
    public R visit(MOVE n);
    public R visit(EXP n);
    public R visit(BINOP n);
    public R visit(MEM n);
    public R visit(TEMP n);
    public R visit(ESEQ n);
    public R visit(NAME n);
    public R visit(CONST n);
    public R visit(CALL n);
}