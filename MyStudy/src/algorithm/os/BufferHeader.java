package algorithm.os;

public class BufferHeader {
	// 设备号
	int devno;

	// 块号
	int blkno;

	// 状态
	int status;

	// 数据区域
	Buffer bfArray;

	// 空闲列表的下个缓冲区
	BufferHeader nextFreeBuffer;

	// 空闲列表的上个缓冲区
	BufferHeader previousFreeBuffer;


}
