package algorithm.os;

public class BufferHeader {
	// �豸��
	int devno;

	// ���
	int blkno;

	// ״̬
	int status;

	// ��������
	Buffer bfArray;

	// �����б���¸�������
	BufferHeader nextFreeBuffer;

	// �����б���ϸ�������
	BufferHeader previousFreeBuffer;


}
