/* Generated By:JavaCC: Do not edit this line. MiniJavaParser.java */
  package javacc.d.jtb;
  public class MiniJavaParser implements MiniJavaParserConstants {

/************************************ * The MiniJava Grammar Starts Here * ************************************/
  static final public void Goal() throws ParseException {
    MainClass();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CLASS:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      TypeDeclaration();
    }
    jj_consume_token(0);
  }

  static final public void MainClass() throws ParseException {
    jj_consume_token(CLASS);
    Identifier();
    jj_consume_token(LBRACE);
    jj_consume_token(PUBLIC);
    jj_consume_token(STATIC);
    jj_consume_token(VOID);
    jj_consume_token(MAIN);
    jj_consume_token(LPAREN);
    jj_consume_token(STRING);
    jj_consume_token(LSQPAREN);
    jj_consume_token(RSQPAREN);
    Identifier();
    jj_consume_token(RPAREN);
    jj_consume_token(LBRACE);
    PrintStatement();
    jj_consume_token(RBRACE);
    jj_consume_token(RBRACE);
  }

  static final public void TypeDeclaration() throws ParseException {
    if (jj_2_1(3)) {
      ClassDeclaration();
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CLASS:
        ClassExtendsDeclaration();
        break;
      default:
        jj_la1[1] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

  static final public void ClassDeclaration() throws ParseException {
    jj_consume_token(CLASS);
    Identifier();
    jj_consume_token(LBRACE);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case BOOLEAN:
      case INTEGER:
      case IDENTIFIER:
        ;
        break;
      default:
        jj_la1[2] = jj_gen;
        break label_2;
      }
      VarDeclaration();
    }
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PUBLIC:
        ;
        break;
      default:
        jj_la1[3] = jj_gen;
        break label_3;
      }
      MethodDeclaration();
    }
    jj_consume_token(RBRACE);
  }

  static final public void ClassExtendsDeclaration() throws ParseException {
    jj_consume_token(CLASS);
    Identifier();
    jj_consume_token(EXTENDS);
    Identifier();
    jj_consume_token(LBRACE);
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case BOOLEAN:
      case INTEGER:
      case IDENTIFIER:
        ;
        break;
      default:
        jj_la1[4] = jj_gen;
        break label_4;
      }
      VarDeclaration();
    }
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PUBLIC:
        ;
        break;
      default:
        jj_la1[5] = jj_gen;
        break label_5;
      }
      MethodDeclaration();
    }
    jj_consume_token(RBRACE);
  }

  static final public void VarDeclaration() throws ParseException {
    Type();
    Identifier();
    jj_consume_token(SEMICOLON);
  }

  static final public void MethodDeclaration() throws ParseException {
    jj_consume_token(PUBLIC);
    Type();
    Identifier();
    jj_consume_token(LPAREN);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BOOLEAN:
    case INTEGER:
    case IDENTIFIER:
      FormalParameterList();
      break;
    default:
      jj_la1[6] = jj_gen;
      ;
    }
    jj_consume_token(RPAREN);
    jj_consume_token(LBRACE);
    label_6:
    while (true) {
      if (jj_2_2(2)) {
        ;
      } else {
        break label_6;
      }
      VarDeclaration();
    }
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LBRACE:
      case IF:
      case WHILE:
      case PRINT:
      case IDENTIFIER:
        ;
        break;
      default:
        jj_la1[7] = jj_gen;
        break label_7;
      }
      Statement();
    }
    jj_consume_token(RETURN);
    Expression();
    jj_consume_token(SEMICOLON);
    jj_consume_token(RBRACE);
  }

  static final public void FormalParameterList() throws ParseException {
    FormalParameter();
    label_8:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 47:
        ;
        break;
      default:
        jj_la1[8] = jj_gen;
        break label_8;
      }
      FormalParameterRest();
    }
  }

  static final public void FormalParameter() throws ParseException {
    Type();
    Identifier();
  }

  static final public void FormalParameterRest() throws ParseException {
    jj_consume_token(47);
    FormalParameter();
  }

  static final public void Type() throws ParseException {
    if (jj_2_3(3)) {
      ArrayType();
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case BOOLEAN:
        BooleanType();
        break;
      case INTEGER:
        IntegerType();
        break;
      case IDENTIFIER:
        Identifier();
        break;
      default:
        jj_la1[9] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

  static final public void ArrayType() throws ParseException {
    jj_consume_token(INTEGER);
    jj_consume_token(LSQPAREN);
    jj_consume_token(RSQPAREN);
  }

  static final public void BooleanType() throws ParseException {
    jj_consume_token(BOOLEAN);
  }

  static final public void IntegerType() throws ParseException {
    jj_consume_token(INTEGER);
  }

  static final public void Statement() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LBRACE:
      Block();
      break;
    default:
      jj_la1[10] = jj_gen;
      if (jj_2_4(2)) {
        AssignmentStatement();
      } else if (jj_2_5(2)) {
        ArrayAssignmentStatement();
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case IF:
          IfStatement();
          break;
        case WHILE:
          WhileStatement();
          break;
        case PRINT:
          PrintStatement();
          break;
        default:
          jj_la1[11] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    }
  }

  static final public void Block() throws ParseException {
    jj_consume_token(LBRACE);
    label_9:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LBRACE:
      case IF:
      case WHILE:
      case PRINT:
      case IDENTIFIER:
        ;
        break;
      default:
        jj_la1[12] = jj_gen;
        break label_9;
      }
      Statement();
    }
    jj_consume_token(RBRACE);
  }

  static final public void AssignmentStatement() throws ParseException {
    Identifier();
    jj_consume_token(ASSIGN);
    Expression();
    jj_consume_token(SEMICOLON);
  }

  static final public void ArrayAssignmentStatement() throws ParseException {
    Identifier();
    jj_consume_token(LSQPAREN);
    Expression();
    jj_consume_token(RSQPAREN);
    jj_consume_token(ASSIGN);
    Expression();
    jj_consume_token(SEMICOLON);
  }

  static final public void IfStatement() throws ParseException {
    jj_consume_token(IF);
    jj_consume_token(LPAREN);
    Expression();
    jj_consume_token(RPAREN);
    Statement();
    jj_consume_token(ELSE);
    Statement();
  }

  static final public void WhileStatement() throws ParseException {
    jj_consume_token(WHILE);
    jj_consume_token(LPAREN);
    Expression();
    jj_consume_token(RPAREN);
    Statement();
  }

  static final public void PrintStatement() throws ParseException {
    jj_consume_token(PRINT);
    jj_consume_token(LPAREN);
    Expression();
    jj_consume_token(RPAREN);
    jj_consume_token(SEMICOLON);
  }

  static final public void Expression() throws ParseException {
    if (jj_2_6(2147483647)) {
      AndExpression();
    } else if (jj_2_7(2147483647)) {
      CompareExpression();
    } else if (jj_2_8(2147483647)) {
      PlusExpression();
    } else if (jj_2_9(2147483647)) {
      MinusExpression();
    } else if (jj_2_10(2147483647)) {
      TimesExpression();
    } else if (jj_2_11(2147483647)) {
      ArrayLookup();
    } else if (jj_2_12(2147483647)) {
      ArrayLength();
    } else if (jj_2_13(2147483647)) {
      MessageSend();
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case LPAREN:
      case NOT:
      case FALSE:
      case NEW:
      case THIS:
      case TRUE:
      case INTEGER_LITERAL:
      case IDENTIFIER:
        PrimaryExpression();
        break;
      default:
        jj_la1[13] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

  static final public void AndExpression() throws ParseException {
    PrimaryExpression();
    jj_consume_token(AND);
    PrimaryExpression();
  }

  static final public void CompareExpression() throws ParseException {
    PrimaryExpression();
    jj_consume_token(LT);
    PrimaryExpression();
  }

  static final public void PlusExpression() throws ParseException {
    PrimaryExpression();
    jj_consume_token(PLUS);
    PrimaryExpression();
  }

  static final public void MinusExpression() throws ParseException {
    PrimaryExpression();
    jj_consume_token(MINUS);
    PrimaryExpression();
  }

  static final public void TimesExpression() throws ParseException {
    PrimaryExpression();
    jj_consume_token(48);
    PrimaryExpression();
  }

  static final public void ArrayLookup() throws ParseException {
    PrimaryExpression();
    jj_consume_token(LSQPAREN);
    PrimaryExpression();
    jj_consume_token(RSQPAREN);
  }

  static final public void ArrayLength() throws ParseException {
    PrimaryExpression();
    jj_consume_token(DOT);
    jj_consume_token(LENGTH);
  }

  static final public void MessageSend() throws ParseException {
    PrimaryExpression();
    jj_consume_token(DOT);
    Identifier();
    jj_consume_token(LPAREN);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LPAREN:
    case NOT:
    case FALSE:
    case NEW:
    case THIS:
    case TRUE:
    case INTEGER_LITERAL:
    case IDENTIFIER:
      ExpressionList();
      break;
    default:
      jj_la1[14] = jj_gen;
      ;
    }
    jj_consume_token(RPAREN);
  }

  static final public void ExpressionList() throws ParseException {
    Expression();
    label_10:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 47:
        ;
        break;
      default:
        jj_la1[15] = jj_gen;
        break label_10;
      }
      ExpressionRest();
    }
  }

  static final public void ExpressionRest() throws ParseException {
    jj_consume_token(47);
    Expression();
  }

  static final public void PrimaryExpression() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INTEGER_LITERAL:
      IntegerLiteral();
      break;
    case TRUE:
      TrueLiteral();
      break;
    case FALSE:
      FalseLiteral();
      break;
    case IDENTIFIER:
      Identifier();
      break;
    case THIS:
      ThisExpression();
      break;
    default:
      jj_la1[16] = jj_gen;
      if (jj_2_14(3)) {
        ArrayAllocationExpression();
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case NEW:
          AllocationExpression();
          break;
        case NOT:
          NotExpression();
          break;
        case LPAREN:
          BracketExpression();
          break;
        default:
          jj_la1[17] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    }
  }

  static final public void IntegerLiteral() throws ParseException {
    jj_consume_token(INTEGER_LITERAL);
  }

  static final public void TrueLiteral() throws ParseException {
    jj_consume_token(TRUE);
  }

  static final public void FalseLiteral() throws ParseException {
    jj_consume_token(FALSE);
  }

  static final public void Identifier() throws ParseException {
    jj_consume_token(IDENTIFIER);
  }

  static final public void ThisExpression() throws ParseException {
    jj_consume_token(THIS);
  }

  static final public void ArrayAllocationExpression() throws ParseException {
    jj_consume_token(NEW);
    jj_consume_token(INTEGER);
    jj_consume_token(LSQPAREN);
    Expression();
    jj_consume_token(RSQPAREN);
  }

  static final public void AllocationExpression() throws ParseException {
    jj_consume_token(NEW);
    Identifier();
    jj_consume_token(LPAREN);
    jj_consume_token(RPAREN);
  }

  static final public void NotExpression() throws ParseException {
    jj_consume_token(NOT);
    Expression();
  }

  static final public void BracketExpression() throws ParseException {
    jj_consume_token(LPAREN);
    Expression();
    jj_consume_token(RPAREN);
  }

  static private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  static private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  static private boolean jj_2_3(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  static private boolean jj_2_4(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  static private boolean jj_2_5(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_5(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(4, xla); }
  }

  static private boolean jj_2_6(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_6(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(5, xla); }
  }

  static private boolean jj_2_7(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_7(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(6, xla); }
  }

  static private boolean jj_2_8(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_8(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(7, xla); }
  }

  static private boolean jj_2_9(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_9(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(8, xla); }
  }

  static private boolean jj_2_10(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_10(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(9, xla); }
  }

  static private boolean jj_2_11(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_11(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(10, xla); }
  }

  static private boolean jj_2_12(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_12(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(11, xla); }
  }

  static private boolean jj_2_13(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_13(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(12, xla); }
  }

  static private boolean jj_2_14(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_14(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(13, xla); }
  }

  static private boolean jj_3R_45() {
    if (jj_3R_46()) return true;
    return false;
  }

  static private boolean jj_3R_16() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(43)) {
    jj_scanpos = xsp;
    if (jj_scan_token(40)) {
    jj_scanpos = xsp;
    if (jj_scan_token(28)) {
    jj_scanpos = xsp;
    if (jj_scan_token(44)) {
    jj_scanpos = xsp;
    if (jj_scan_token(39)) {
    jj_scanpos = xsp;
    if (jj_3_14()) {
    jj_scanpos = xsp;
    if (jj_3R_19()) {
    jj_scanpos = xsp;
    if (jj_3R_20()) {
    jj_scanpos = xsp;
    if (jj_3R_21()) return true;
    }
    }
    }
    }
    }
    }
    }
    }
    return false;
  }

  static private boolean jj_3_2() {
    if (jj_3R_12()) return true;
    return false;
  }

  static private boolean jj_3R_46() {
    if (jj_scan_token(47)) return true;
    if (jj_3R_25()) return true;
    return false;
  }

  static private boolean jj_3R_44() {
    if (jj_3R_25()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_45()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  static private boolean jj_3R_43() {
    if (jj_3R_44()) return true;
    return false;
  }

  static private boolean jj_3R_42() {
    if (jj_3R_16()) return true;
    if (jj_scan_token(DOT)) return true;
    if (jj_scan_token(44)) return true;
    if (jj_scan_token(LPAREN)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_43()) jj_scanpos = xsp;
    if (jj_scan_token(RPAREN)) return true;
    return false;
  }

  static private boolean jj_3R_12() {
    if (jj_3R_18()) return true;
    if (jj_scan_token(44)) return true;
    return false;
  }

  static private boolean jj_3R_15() {
    if (jj_scan_token(44)) return true;
    if (jj_scan_token(LSQPAREN)) return true;
    return false;
  }

  static private boolean jj_3R_24() {
    if (jj_scan_token(LPAREN)) return true;
    if (jj_3R_25()) return true;
    if (jj_scan_token(RPAREN)) return true;
    return false;
  }

  static private boolean jj_3R_41() {
    if (jj_3R_16()) return true;
    if (jj_scan_token(DOT)) return true;
    if (jj_scan_token(LENGTH)) return true;
    return false;
  }

  static private boolean jj_3R_14() {
    if (jj_scan_token(44)) return true;
    if (jj_scan_token(ASSIGN)) return true;
    return false;
  }

  static private boolean jj_3R_23() {
    if (jj_scan_token(NOT)) return true;
    if (jj_3R_25()) return true;
    return false;
  }

  static private boolean jj_3R_40() {
    if (jj_3R_16()) return true;
    if (jj_scan_token(LSQPAREN)) return true;
    if (jj_3R_16()) return true;
    if (jj_scan_token(RSQPAREN)) return true;
    return false;
  }

  static private boolean jj_3R_22() {
    if (jj_scan_token(NEW)) return true;
    if (jj_scan_token(44)) return true;
    if (jj_scan_token(LPAREN)) return true;
    if (jj_scan_token(RPAREN)) return true;
    return false;
  }

  static private boolean jj_3R_11() {
    if (jj_scan_token(CLASS)) return true;
    if (jj_scan_token(44)) return true;
    if (jj_scan_token(LBRACE)) return true;
    return false;
  }

  static private boolean jj_3R_39() {
    if (jj_3R_16()) return true;
    if (jj_scan_token(48)) return true;
    if (jj_3R_16()) return true;
    return false;
  }

  static private boolean jj_3R_17() {
    if (jj_scan_token(NEW)) return true;
    if (jj_scan_token(INTEGER)) return true;
    if (jj_scan_token(LSQPAREN)) return true;
    if (jj_3R_25()) return true;
    if (jj_scan_token(RSQPAREN)) return true;
    return false;
  }

  static private boolean jj_3R_38() {
    if (jj_3R_16()) return true;
    if (jj_scan_token(MINUS)) return true;
    if (jj_3R_16()) return true;
    return false;
  }

  static private boolean jj_3_1() {
    if (jj_3R_11()) return true;
    return false;
  }

  static private boolean jj_3_5() {
    if (jj_3R_15()) return true;
    return false;
  }

  static private boolean jj_3_4() {
    if (jj_3R_14()) return true;
    return false;
  }

  static private boolean jj_3R_37() {
    if (jj_3R_16()) return true;
    if (jj_scan_token(PLUS)) return true;
    if (jj_3R_16()) return true;
    return false;
  }

  static private boolean jj_3R_36() {
    if (jj_3R_16()) return true;
    if (jj_scan_token(LT)) return true;
    if (jj_3R_16()) return true;
    return false;
  }

  static private boolean jj_3_13() {
    if (jj_3R_16()) return true;
    if (jj_scan_token(DOT)) return true;
    if (jj_scan_token(44)) return true;
    if (jj_scan_token(LPAREN)) return true;
    return false;
  }

  static private boolean jj_3R_35() {
    if (jj_3R_16()) return true;
    if (jj_scan_token(AND)) return true;
    if (jj_3R_16()) return true;
    return false;
  }

  static private boolean jj_3_12() {
    if (jj_3R_16()) return true;
    if (jj_scan_token(DOT)) return true;
    if (jj_scan_token(LENGTH)) return true;
    return false;
  }

  static private boolean jj_3_11() {
    if (jj_3R_16()) return true;
    if (jj_scan_token(LSQPAREN)) return true;
    return false;
  }

  static private boolean jj_3R_34() {
    if (jj_3R_16()) return true;
    return false;
  }

  static private boolean jj_3R_13() {
    if (jj_scan_token(INTEGER)) return true;
    if (jj_scan_token(LSQPAREN)) return true;
    if (jj_scan_token(RSQPAREN)) return true;
    return false;
  }

  static private boolean jj_3_10() {
    if (jj_3R_16()) return true;
    if (jj_scan_token(48)) return true;
    return false;
  }

  static private boolean jj_3R_33() {
    if (jj_3R_42()) return true;
    return false;
  }

  static private boolean jj_3_9() {
    if (jj_3R_16()) return true;
    if (jj_scan_token(MINUS)) return true;
    return false;
  }

  static private boolean jj_3R_32() {
    if (jj_3R_41()) return true;
    return false;
  }

  static private boolean jj_3_8() {
    if (jj_3R_16()) return true;
    if (jj_scan_token(PLUS)) return true;
    return false;
  }

  static private boolean jj_3R_31() {
    if (jj_3R_40()) return true;
    return false;
  }

  static private boolean jj_3_7() {
    if (jj_3R_16()) return true;
    if (jj_scan_token(LT)) return true;
    return false;
  }

  static private boolean jj_3R_21() {
    if (jj_3R_24()) return true;
    return false;
  }

  static private boolean jj_3R_30() {
    if (jj_3R_39()) return true;
    return false;
  }

  static private boolean jj_3_6() {
    if (jj_3R_16()) return true;
    if (jj_scan_token(AND)) return true;
    return false;
  }

  static private boolean jj_3R_20() {
    if (jj_3R_23()) return true;
    return false;
  }

  static private boolean jj_3_3() {
    if (jj_3R_13()) return true;
    return false;
  }

  static private boolean jj_3R_18() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_3()) {
    jj_scanpos = xsp;
    if (jj_scan_token(23)) {
    jj_scanpos = xsp;
    if (jj_scan_token(31)) {
    jj_scanpos = xsp;
    if (jj_scan_token(44)) return true;
    }
    }
    }
    return false;
  }

  static private boolean jj_3R_29() {
    if (jj_3R_38()) return true;
    return false;
  }

  static private boolean jj_3R_19() {
    if (jj_3R_22()) return true;
    return false;
  }

  static private boolean jj_3R_28() {
    if (jj_3R_37()) return true;
    return false;
  }

  static private boolean jj_3_14() {
    if (jj_3R_17()) return true;
    return false;
  }

  static private boolean jj_3R_27() {
    if (jj_3R_36()) return true;
    return false;
  }

  static private boolean jj_3R_25() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_26()) {
    jj_scanpos = xsp;
    if (jj_3R_27()) {
    jj_scanpos = xsp;
    if (jj_3R_28()) {
    jj_scanpos = xsp;
    if (jj_3R_29()) {
    jj_scanpos = xsp;
    if (jj_3R_30()) {
    jj_scanpos = xsp;
    if (jj_3R_31()) {
    jj_scanpos = xsp;
    if (jj_3R_32()) {
    jj_scanpos = xsp;
    if (jj_3R_33()) {
    jj_scanpos = xsp;
    if (jj_3R_34()) return true;
    }
    }
    }
    }
    }
    }
    }
    }
    return false;
  }

  static private boolean jj_3R_26() {
    if (jj_3R_35()) return true;
    return false;
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public MiniJavaParserTokenManager token_source;
  static JavaCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private Token jj_scanpos, jj_lastpos;
  static private int jj_la;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[18];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x1000000,0x1000000,0x80800000,0x0,0x80800000,0x0,0x80800000,0x60002000,0x0,0x80800000,0x2000,0x60000000,0x60002000,0x10400200,0x10400200,0x0,0x10000000,0x400200,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x1000,0x8,0x1000,0x8,0x1000,0x1200,0x8000,0x1000,0x0,0x200,0x1200,0x1984,0x1984,0x8000,0x1980,0x4,};
   }
  static final private JJCalls[] jj_2_rtns = new JJCalls[14];
  static private boolean jj_rescan = false;
  static private int jj_gc = 0;

  /** Constructor with InputStream. */
  public MiniJavaParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public MiniJavaParser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new JavaCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new MiniJavaParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public MiniJavaParser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new JavaCharStream(stream, 1, 1);
    token_source = new MiniJavaParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public MiniJavaParser(MiniJavaParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(MiniJavaParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  static final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  static private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;
  static private int[] jj_lasttokens = new int[100];
  static private int jj_endpos;

  static private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[49];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 18; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 49; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

  static private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 14; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
            case 3: jj_3_4(); break;
            case 4: jj_3_5(); break;
            case 5: jj_3_6(); break;
            case 6: jj_3_7(); break;
            case 7: jj_3_8(); break;
            case 8: jj_3_9(); break;
            case 9: jj_3_10(); break;
            case 10: jj_3_11(); break;
            case 11: jj_3_12(); break;
            case 12: jj_3_13(); break;
            case 13: jj_3_14(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  static private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

                               }
