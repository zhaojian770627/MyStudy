package complier.book.construction.s18;

public interface G1Constants {
    // 标识单词符号类别的整数
    int EORE=0;	 	// 正则表达式结束
    int CHAR=1;
    int PERIOD=2;		// PERIOD 匹配任意字符
    int LEFTPAREN=3;
    int RIGHTPAREN=4;
    int OR=5;
    int STAR=6;
    int ERROR=7;

    int CONCAT=8;		// 没有对应的单词符号

    // tokenImage 为每个单词符号提供字符串
    String[] tokenImage = { 
	"<EORE>",
	"<CHAR>",
	"\".\"",
	"\"(\"",
	"\")\"",
	"\"|\"",
	"\"*\"",
	"<ERROR>"
    };
}
