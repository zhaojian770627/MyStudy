/* Generated By:JavaCC: Do not edit this line. ijConstants.java */
package org.apache.derby.impl.tools.ij;

public interface ijConstants {

  int EOF = 0;
  int SINGLE_LINE_SQLCOMMENT = 7;
  int ABSOLUTE = 14;
  int AFTER = 15;
  int ALIASES = 16;
  int ALL = 17;
  int AS = 18;
  int ASYNC = 19;
  int ATTRIBUTES = 20;
  int AUTOCOMMIT = 21;
  int BANG = 22;
  int BEFORE = 23;
  int CLOSE = 24;
  int COMMIT = 25;
  int CONNECT = 26;
  int CONNECTION = 27;
  int CONNECTIONS = 28;
  int CURRENT = 29;
  int CURSOR = 30;
  int DESCRIBE = 31;
  int DISCONNECT = 32;
  int DRIVER = 33;
  int ELAPSEDTIME = 34;
  int ENABLED_ROLES = 35;
  int END = 36;
  int EQUALS_OPERATOR = 37;
  int EXECUTE = 38;
  int EXIT = 39;
  int EXPECT = 40;
  int FAIL = 41;
  int FIRST = 42;
  int FOR = 43;
  int FROM = 44;
  int GET = 45;
  int GETCURRENTROWNUMBER = 46;
  int HOLD = 47;
  int HELP = 48;
  int IN = 49;
  int INDEXES = 50;
  int INSENSITIVE = 51;
  int INTO = 52;
  int LAST = 53;
  int LOCALIZEDDISPLAY = 54;
  int MAXIMUMDISPLAYWIDTH = 55;
  int NAME = 56;
  int NEXT = 57;
  int NOHOLD = 58;
  int NOHOLDFORCONNECTION = 59;
  int OFF = 60;
  int ON = 61;
  int PASSWORD = 62;
  int PERIOD = 63;
  int PREPARE = 64;
  int PREVIOUS = 65;
  int PROCEDURE = 66;
  int PROCEDURES = 67;
  int PROPERTIES = 68;
  int PROTOCOL = 69;
  int QUIT = 70;
  int READONLY = 71;
  int RELATIVE = 72;
  int REMOVE = 73;
  int RESOURCE = 74;
  int ROLES = 75;
  int ROLLBACK = 76;
  int RUN = 77;
  int TO = 78;
  int SAVEPOINT = 79;
  int SCHEMAS = 80;
  int SCROLL = 81;
  int SENSITIVE = 82;
  int SET = 83;
  int SETTABLE_ROLES = 84;
  int SHOW = 85;
  int SHUTDOWN = 86;
  int STATEMENT = 87;
  int SYNONYMS = 88;
  int TABLES = 89;
  int USER = 90;
  int USING = 91;
  int VIEWS = 92;
  int WAIT = 93;
  int WITH = 94;
  int XA_1PHASE = 95;
  int XA_2PHASE = 96;
  int XA_DATASOURCE = 97;
  int XA_CONNECT = 98;
  int XA_COMMIT = 99;
  int XA_DISCONNECT = 100;
  int XA_END = 101;
  int XA_ENDRSCAN = 102;
  int XA_FAIL = 103;
  int XA_FORGET = 104;
  int XA_GETCONNECTION = 105;
  int XA_JOIN = 106;
  int XA_NOFLAGS = 107;
  int XA_PREPARE = 108;
  int XA_RECOVER = 109;
  int XA_RESUME = 110;
  int XA_ROLLBACK = 111;
  int XA_START = 112;
  int XA_STARTRSCAN = 113;
  int XA_SUCCESS = 114;
  int XA_SUSPEND = 115;
  int DATASOURCE = 116;
  int CP_DATASOURCE = 117;
  int CP_CONNECT = 118;
  int CP_GETCONNECTION = 119;
  int CP_DISCONNECT = 120;
  int WORK = 121;
  int COMMA = 122;
  int LEFT_PAREN = 123;
  int RIGHT_PAREN = 124;
  int DOUBLE_QUOTE = 125;
  int HASH = 126;
  int MINUS_SIGN = 127;
  int PLUS_SIGN = 128;
  int IDENTIFIER = 129;
  int LETTER = 130;
  int DIGIT = 131;
  int INTEGER = 132;
  int STRING = 133;

  int DEFAULT = 0;
  int IN_BRACKETED_COMMENT = 1;
  int IN_NESTED_BRACKETED_COMMENT = 2;

  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\t\"",
    "\"\\r\\n\"",
    "\"\\n\"",
    "\"\\r\"",
    "\"\\f\"",
    "<SINGLE_LINE_SQLCOMMENT>",
    "\"/*\"",
    "\"/*\"",
    "\"*/\"",
    "\"/*\"",
    "\"*/\"",
    "<token of kind 13>",
    "\"absolute\"",
    "\"after\"",
    "\"aliases\"",
    "\"all\"",
    "\"as\"",
    "\"async\"",
    "\"attributes\"",
    "\"autocommit\"",
    "\"!\"",
    "\"before\"",
    "\"close\"",
    "\"commit\"",
    "\"connect\"",
    "\"connection\"",
    "\"connections\"",
    "\"current\"",
    "\"cursor\"",
    "\"describe\"",
    "\"disconnect\"",
    "\"driver\"",
    "\"elapsedtime\"",
    "\"enabled_roles\"",
    "\"end\"",
    "\"=\"",
    "\"execute\"",
    "\"exit\"",
    "\"expect\"",
    "\"fail\"",
    "\"first\"",
    "\"for\"",
    "\"from\"",
    "\"get\"",
    "\"getcurrentrownumber\"",
    "\"hold\"",
    "\"help\"",
    "\"in\"",
    "\"indexes\"",
    "\"insensitive\"",
    "\"into\"",
    "\"last\"",
    "\"localizeddisplay\"",
    "\"maximumdisplaywidth\"",
    "\"name\"",
    "\"next\"",
    "\"nohold\"",
    "\"noholdforconnection\"",
    "\"off\"",
    "\"on\"",
    "\"password\"",
    "\".\"",
    "\"prepare\"",
    "\"previous\"",
    "\"procedure\"",
    "\"procedures\"",
    "\"properties\"",
    "\"protocol\"",
    "\"quit\"",
    "\"readonly\"",
    "\"relative\"",
    "\"remove\"",
    "\"resource\"",
    "\"roles\"",
    "\"rollback\"",
    "\"run\"",
    "\"to\"",
    "\"savepoint\"",
    "\"schemas\"",
    "\"scroll\"",
    "\"sensitive\"",
    "\"set\"",
    "\"settable_roles\"",
    "\"show\"",
    "\"shutdown\"",
    "\"statement\"",
    "\"synonyms\"",
    "\"tables\"",
    "\"user\"",
    "\"using\"",
    "\"views\"",
    "\"wait\"",
    "\"with\"",
    "\"XA_1phase\"",
    "\"XA_2phase\"",
    "\"XA_datasource\"",
    "\"XA_connect\"",
    "\"XA_commit\"",
    "\"XA_disconnect\"",
    "\"XA_end\"",
    "\"XA_endrscan\"",
    "\"XA_fail\"",
    "\"XA_forget\"",
    "\"XA_getconnection\"",
    "\"XA_join\"",
    "\"XA_noflags\"",
    "\"XA_prepare\"",
    "\"XA_recover\"",
    "\"XA_resume\"",
    "\"XA_rollback\"",
    "\"XA_start\"",
    "\"XA_startrscan\"",
    "\"XA_success\"",
    "\"XA_suspend\"",
    "\"datasource\"",
    "\"CP_datasource\"",
    "\"CP_connect\"",
    "\"CP_getconnection\"",
    "\"CP_disconnect\"",
    "\"work\"",
    "\",\"",
    "\"(\"",
    "\")\"",
    "\"\\\"\"",
    "\"#\"",
    "\"-\"",
    "\"+\"",
    "<IDENTIFIER>",
    "<LETTER>",
    "<DIGIT>",
    "<INTEGER>",
    "<STRING>",
  };

}
