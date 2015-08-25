package algorithm.tree;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class BPlusTreeNode {
	/** �Ƿ�ΪҶ�ӽڵ� */
	protected boolean isLeaf;

	/** �Ƿ�Ϊ���ڵ� */
	protected boolean isRoot;

	/** ���ڵ� */
	protected BPlusTreeNode parent;

	/** Ҷ�ڵ��ǰ�ڵ� */
	protected BPlusTreeNode previous;

	/** Ҷ�ڵ�ĺ�ڵ� */
	protected BPlusTreeNode next;

	/** �ڵ�Ĺؼ��� */
	protected List<Entry<Comparable, Object>> keyEntries;

	/** �ӽڵ� */
	protected List<BPlusTreeNode> children;

	public BPlusTreeNode getPrevious() {
		return previous;
	}

	public void setPrevious(BPlusTreeNode previous) {
		this.previous = previous;
	}

	public BPlusTreeNode getNext() {
		return next;
	}

	public void setNext(BPlusTreeNode next) {
		this.next = next;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public BPlusTreeNode getParent() {
		return parent;
	}

	public void setParent(BPlusTreeNode parent) {
		this.parent = parent;
	}

	public List<Entry<Comparable, Object>> getKeyEntries() {
		return keyEntries;
	}

	public void setKeyEntries(List<Entry<Comparable, Object>> entries) {
		this.keyEntries = entries;
	}

	public List<BPlusTreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<BPlusTreeNode> children) {
		this.children = children;
	}

	public boolean isRoot() {
		return isRoot;
	}

	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("BPlusTree");
		return sb.toString();

	}

	public BPlusTreeNode(boolean isLeaf) {
		this.isLeaf = isLeaf;
		keyEntries = new ArrayList<Entry<Comparable, Object>>();

		if (!isLeaf) {
			children = new ArrayList<BPlusTreeNode>();
		}
	}

	public BPlusTreeNode(boolean isLeaf, boolean isRoot) {
		this(isLeaf);
		this.isRoot = isRoot;
	}

	public Object get(Comparable key) {
		// �����Ҷ�ӽڵ�
		if (isLeaf) {
			for (Entry<Comparable, Object> entry : keyEntries) {
				if (entry.getKey().compareTo(key) == 0) {
					// �����ҵ��Ķ���
					return entry.getValue();
				}
			}
			// δ�ҵ���Ҫ��ѯ�Ķ���
			return null;
		} else {
			// ���keyС�ڵ��ڽڵ�����ߵ�key���ص�һ���ӽڵ�������� ???
			if (key.compareTo(keyEntries.get(0).getKey()) <= 0) {
				// �Ƿ�Ӧ�÷���null
				return children.get(0).get(key);
			}
			// ���key���ڽڵ����ұߵ�key�������һ���ӽڵ��������
			else if (key.compareTo(keyEntries.get(keyEntries.size() - 1)
					.getKey()) >= 0) {
				return children.get(children.size() - 1).get(key);
			}
			// �����ر�key���ǰһ���ӽڵ��������
			else {
				for (int i = 0; i < keyEntries.size(); i++) {
					if (keyEntries.get(i).getKey().compareTo(key) <= 0
							&& keyEntries.get(i + 1).getKey().compareTo(key) > 0) {
						return children.get(i).get(key);
					}
				}
			}
		}
		return null;
	}

	public void insertOrUpdate(Comparable key, Object obj, BPlusTree tree) {
		// �����Ҷ�ӽڵ�
		if (isLeaf) {
			// ����Ҫ���ѣ�ֱ�Ӳ�������
			if (contains(key) || keyEntries.size() < tree.getOrder()) {
				insertOrUpdate(key, obj);
				if (parent != null) {
					// ���¸��ڵ� --�Ƿ����ȥ��
					parent.updateInsert(tree);
				}
			}
			// ��Ҫ����
			else {
				// ���ѳ����������ڵ�
				BPlusTreeNode left = new BPlusTreeNode(true);
				BPlusTreeNode right = new BPlusTreeNode(true);

				// ��������
				if (previous != null) {
					previous.setNext(left);
					left.setPrevious(previous);
				}

				if (next != null) {
					next.setPrevious(right);
					right.setNext(next);
				}

				if (previous == null) {
					tree.setHead(left);
				}

				left.setNext(right);
				right.setPrevious(left);
				previous = null;
				next = null;

				// ���������ڵ�ؼ��ֳ���
				int leftSize = (tree.getOrder() + 1) / 2
						+ (tree.getOrder() + 1) % 2;
				int rightSize = (tree.getOrder() + 1) / 2;

				// ����ԭ�ڵ�ؼ��ֵ����ѳ������½ڵ�
				insertOrUpdate(key, obj);
				for (int i = 0; i < leftSize; i++) {
					left.getKeyEntries().add(keyEntries.get(i));
				}
				for (int i = 0; i < rightSize; i++) {
					right.getKeyEntries().add(keyEntries.get(leftSize + i));
				}

				// ������Ǹ��ڵ�
				if (parent != null) {
					// �������ӽڵ��ϵ
					int index = parent.getChildren().indexOf(this);
					parent.getChildren().remove(this);
					left.setParent(parent);
					right.setParent(parent);
					parent.getChildren().add(index, left);
					parent.getChildren().add(index + 1, right);
					setKeyEntries(null);
					setChildren(null);

					// ���ڵ�������¹ؼ���
					parent.updateInsert(tree);
					setParent(null);

				}
				// ����Ǹ��ڵ�
				else {
					isRoot = false;
					BPlusTreeNode parent = new BPlusTreeNode(false, true);
					tree.setRoot(parent);
					left.setParent(parent);
					right.setParent(parent);
					parent.getChildren().add(left);
					parent.getChildren().add(right);
					setKeyEntries(null);
					setChildren(null);

					// ���¸��ڵ�
					parent.updateInsert(tree);
				}
			}
		}
		// �������Ҷ�ӽڵ�
		else {
			// ���keyС�ڵ��ڽڵ�����ߵ�key���ص�һ���ӽڵ��������
			if (key.compareTo(keyEntries.get(0).getKey()) <= 0) {
				children.get(0).insertOrUpdate(key, obj, tree);
				// ���key���ڽڵ����ұߵ�key�������һ���ӽڵ��������
			} else if (key.compareTo(keyEntries.get(keyEntries.size() - 1)
					.getKey()) >= 0) {
				children.get(children.size() - 1)
						.insertOrUpdate(key, obj, tree);
			}
			// �����ر�key���ǰһ���ӽڵ��������
			else {
				for (int i = 0; i < keyEntries.size(); i++) {
					if (keyEntries.get(i).getKey().compareTo(key) <= 0
							&& keyEntries.get(i + 1).getKey().compareTo(key) > 0) {
						children.get(i).insertOrUpdate(key, obj, tree);
						break;
					}
				}
			}
		}
	}

	/** ����ڵ���м�ڵ�ĸ��� */
	private void updateInsert(BPlusTree tree) {
		adjustNodeKey(this, tree);

		// ����ӽڵ�����������������Ҫ���Ѹýڵ�
		if (children.size() > tree.getOrder()) {
			// ���ѳ����������ڵ�
			BPlusTreeNode left = new BPlusTreeNode(false);
			BPlusTreeNode right = new BPlusTreeNode(false);

			// ���������ڵ�ؼ��ֳ���
			int leftSize = (tree.getOrder() + 1) / 2 + (tree.getOrder() + 1)
					% 2;
			int rightSize = (tree.getOrder() + 1) / 2;

			// �����ӽڵ㵽���ѳ������½ڵ㣬�����¹ؼ���
			for (int i = 0; i < leftSize; i++) {
				left.getChildren().add(children.get(i));
				left.getKeyEntries().add(
						new SimpleEntry(children.get(i).getKeyEntries().get(0)
								.getKey(), null));
				children.get(i).setParent(left);
			}

			for (int i = 0; i < rightSize; i++) {
				right.getChildren().add(children.get(leftSize + i));
				right.getKeyEntries().add(
						new SimpleEntry(children.get(leftSize + i)
								.getKeyEntries().get(0).getKey(), null));
				children.get(leftSize + i).setParent(right);
			}

			// ������Ǹ��ڵ�
			if (parent != null) {
				// �������ӽڵ��ϵ
				int index = parent.getChildren().indexOf(this);
				parent.getChildren().remove(this);
				left.setParent(parent);
				right.setParent(parent);
				parent.getChildren().add(index, left);
				parent.getChildren().add(index + 1, right);
				setKeyEntries(null);
				setChildren(null);

				// ���ڵ���¹ؼ���
				parent.updateInsert(tree);
				setParent(null);
			}
			// ����Ǹ��ڵ�
			else {
				isRoot = false;
				BPlusTreeNode parent = new BPlusTreeNode(false, true);
				tree.setRoot(parent);
				left.setParent(parent);
				right.setParent(parent);
				parent.getChildren().add(left);
				parent.getChildren().add(right);
				setKeyEntries(null);
				setChildren(null);

				// ���¸��ڵ�
				parent.updateInsert(tree);
			}
		}
	}

	/** �����ڵ�ؼ��� */
	private void adjustNodeKey(BPlusTreeNode node, BPlusTree tree) {
		// ����ؼ��ָ������ӽڵ������ͬ
		if (node.getKeyEntries().size() == node.getChildren().size()) {
			for (int i = 0; i < node.getKeyEntries().size(); i++) {
				Comparable key = node.getChildren().get(i).getKeyEntries()
						.get(0).getKey();
				if (node.getKeyEntries().get(i).getKey().compareTo(key) != 0) {
					node.getKeyEntries().remove(i);
					node.getKeyEntries().add(i, new SimpleEntry(key, null));
					if (!node.isRoot()) {
						adjustNodeKey(node.getParent(), tree);
					}
				}
			}
		}// ����ӽڵ��������ڹؼ��ָ������Դ���M / 2����С��M�����Ҵ���2
		else if (node.isRoot() && node.getChildren().size() >= 2
				|| node.getChildren().size() >= tree.getOrder() / 2
				&& node.getChildren().size() <= tree.getOrder()
				&& node.getChildren().size() >= 2) {
			node.getKeyEntries().clear();
			for (int i = 0; i < node.getChildren().size(); i++) {
				Comparable key = node.getChildren().get(i).getKeyEntries()
						.get(0).getKey();
				node.getKeyEntries().add(new SimpleEntry(key, null));
				if (!node.isRoot()) {
					adjustNodeKey(node.getParent(), tree);
				}
			}
		}

	}

	/** ���뵽��ǰ�ڵ�Ĺؼ����� */
	protected void insertOrUpdate(Comparable key, Object obj) {
		Entry<Comparable, Object> entry = new SimpleEntry<Comparable, Object>(
				key, obj);
		// ����ؼ����б���Ϊ0����ֱ�Ӳ���
		if (keyEntries.size() == 0) {
			keyEntries.add(entry);
			return;
		}

		// ��������б�
		for (int i = 0; i < keyEntries.size(); i++) {
			// ����ùؼ��ּ�ֵ�Ѵ��ڣ������
			if (keyEntries.get(i).getKey().compareTo(key) == 0) {
				keyEntries.get(i).setValue(obj);
				return;
			} else if (keyEntries.get(i).getKey().compareTo(key) > 0) {
				// ���뵽���� ??? ���Ժϲ�
				if (i == 0) {
					keyEntries.add(0, entry);
					return;
					// ���뵽�м�
				} else {
					keyEntries.add(i, entry);
					return;
				}
			}
		}
		// ���뵽ĩβ
		keyEntries.add(keyEntries.size(), entry);
	}

	/** ɾ���ڵ���м�ڵ�ĸ��� */
	protected void updateRemove(BPlusTree tree) {
		adjustNodeKey(this, tree);
		// ����ӽڵ���С��M / 2����С��2������Ҫ�ϲ��ڵ�
		if (children.size() < tree.getOrder() / 2 || children.size() < 2) {
			if (isRoot) {
				// ����Ǹ��ڵ㲢���ӽڵ������ڵ���2��OK
				if (children.size() >= 2) {
					return;
				}
				// �������ӽڵ�ϲ�
				else {
					BPlusTreeNode root = children.get(0);
					tree.setRoot(root);
					root.setParent(null);
					root.setRoot(true);
					setKeyEntries(null);
					setChildren(null);
				}
			} else {
				// ����ǰ��ڵ�
				int currIdx = parent.getChildren().indexOf(this);
				int prevIdx = currIdx - 1;
				int nextIdx = currIdx + 1;
				BPlusTreeNode previous = null, next = null;

				if (prevIdx >= 0) {
					previous = parent.getChildren().get(prevIdx);
				}
				if (nextIdx < parent.getChildren().size()) {
					next = parent.getChildren().get(nextIdx);
				}

				// ���ǰ�ڵ��ӽڵ�������M / 2���Ҵ���2������䴦�貹
				if (previous != null
						&& previous.getChildren().size() > tree.getOrder() / 2
						&& previous.getChildren().size() > 2) {
					// ǰҶ�ӽڵ�ĩβ�ڵ���ӵ���λ
					int idx = previous.getChildren().size() - 1;
					BPlusTreeNode borrow = previous.getChildren().get(idx);
					previous.getChildren().remove(idx);
					borrow.setParent(this);
					children.add(0, borrow);
					adjustNodeKey(previous, tree);
					adjustNodeKey(this, tree);
					parent.updateRemove(tree);
				} else if (next != null
						&& next.getChildren().size() > tree.getOrder() / 2
						&& next.getChildren().size() > 2) {
					// ��Ҷ�ӽڵ���λ��ӵ�ĩβ
					BPlusTreeNode borrow = next.getChildren().get(0);
					next.getChildren().remove(0);
					borrow.setParent(this);
					children.add(borrow);
					adjustNodeKey(next, tree);
					adjustNodeKey(this, tree);
					parent.updateRemove(tree);
				} else {
					// ͬǰ��ڵ�ϲ�
					if (previous != null
							&& (previous.getChildren().size() <= tree
									.getOrder() / 2 || previous.getChildren()
									.size() <= 2)) {

						for (int i = previous.getChildren().size() - 1; i >= 0; i--) {
							BPlusTreeNode child = previous.getChildren().get(i);
							children.add(0, child);
							child.setParent(this);
						}
						previous.setChildren(null);
						previous.setKeyEntries(null);
						previous.setParent(null);
						parent.getChildren().remove(previous);
						adjustNodeKey(this, tree);
						parent.updateRemove(tree);

						// ͬ����ڵ�ϲ�
					} else if (next != null
							&& (next.getChildren().size() <= tree.getOrder() / 2 || next
									.getChildren().size() <= 2)) {

						for (int i = 0; i < next.getChildren().size(); i++) {
							BPlusTreeNode child = next.getChildren().get(i);
							children.add(child);
							child.setParent(this);
						}
						next.setChildren(null);
						next.setKeyEntries(null);
						next.setParent(null);
						parent.getChildren().remove(next);
						adjustNodeKey(this, tree);
						parent.updateRemove(tree);
					}
				}
			}
		}
	}

	public void remove(Comparable key, BPlusTree tree) {
		// �����Ҷ�ӽڵ�
		if (isLeaf) {
			// ����������ùؼ��֣���ֱ�ӷ���
			if (!contains(key)) {
				return;
			}

			// �������Ҷ�ӽڵ����Ǹ��ڵ㣬ֱ��ɾ��
			if (isRoot) {
				remove(key);
			} else {
				// ����ؼ���������M / 2��ֱ��ɾ��
				if (keyEntries.size() > tree.getOrder() / 2
						&& keyEntries.size() > 2) {
					remove(key);
				} else {
					// �������ؼ�����С��M / 2������ǰ�ڵ�ؼ���������M / 2������䴦�貹
					if (previous != null
							&& previous.getKeyEntries().size() > tree
									.getOrder() / 2
							&& previous.getKeyEntries().size() > 2
							&& previous.getParent() == parent) {
						int size = previous.getKeyEntries().size();
						Entry<Comparable, Object> entry = previous
								.getKeyEntries().get(size - 1);
						previous.getKeyEntries().remove(entry);
						// ��ӵ���λ
						keyEntries.add(0, entry);
						remove(key);

					}
					// �������ؼ�����С��M / 2�����Һ�ڵ�ؼ���������M / 2������䴦�貹
					else if (next != null
							&& next.getKeyEntries().size() > tree.getOrder() / 2
							&& next.getKeyEntries().size() > 2
							&& next.getParent() == parent) {
						Entry<Comparable, Object> entry = next.getKeyEntries()
								.get(0);
						next.getKeyEntries().remove(entry);
						// ��ӵ�ĩβ
						keyEntries.add(entry);
						remove(key);
					}
					// ������Ҫ�ϲ�Ҷ�ӽڵ�
					else {
						// ͬǰ��ڵ�ϲ�
						if (previous != null
								&& (previous.getKeyEntries().size() <= tree
										.getOrder() / 2 || previous
										.getKeyEntries().size() <= 2)
								&& previous.getParent() == parent) {
							for (int i = previous.getKeyEntries().size() - 1; i >= 0; i--) {
								// ��ĩβ��ʼ��ӵ���λ
								keyEntries.add(0,
										previous.getKeyEntries().get(i));
							}
							remove(key);
							previous.setParent(null);
							previous.setKeyEntries(null);
							parent.getChildren().remove(previous);
							// ��������
							if (previous.getPrevious() != null) {
								BPlusTreeNode temp = previous;
								temp.getPrevious().setNext(this);
								previous = temp.getPrevious();
								temp.setPrevious(null);
								temp.setNext(null);
							} else {
								tree.setHead(this);
								previous.setNext(null);
								previous = null;
							}
						} else if (next != null
								&& (next.getKeyEntries().size() <= tree
										.getOrder() / 2 || next.getKeyEntries()
										.size() <= 2)
								&& next.getParent() == parent) {
							for (int i = 0; i < next.getKeyEntries().size(); i++) {
								// ����λ��ʼ��ӵ�ĩβ
								keyEntries.add(next.getKeyEntries().get(i));
							}
							remove(key);
							next.setParent(null);
							next.setKeyEntries(null);
							parent.getChildren().remove(next);
							// ��������
							if (next.getNext() != null) {
								BPlusTreeNode temp = next;
								temp.getNext().setPrevious(this);
								next = temp.getNext();
								temp.setPrevious(null);
								temp.setNext(null);
							} else {
								next.setPrevious(null);
								next = null;
							}
						}
					}
				}
				parent.updateRemove(tree);
			}
		}
		// �������Ҷ�ӽڵ�
		else {
			// ���keyС�ڵ��ڽڵ�����ߵ�key���ص�һ���ӽڵ��������
			if (key.compareTo(keyEntries.get(0).getKey()) <= 0) {
				children.get(0).remove(key, tree);

			}
			// ���key���ڽڵ����ұߵ�key�������һ���ӽڵ��������
			else if (key.compareTo(keyEntries.get(keyEntries.size() - 1)
					.getKey()) >= 0) {
				children.get(children.size() - 1).remove(key, tree);
			}
			// �����ر�key���ǰһ���ӽڵ��������
			else {
				for (int i = 0; i < keyEntries.size(); i++) {
					if (keyEntries.get(i).getKey().compareTo(key) <= 0
							&& keyEntries.get(i + 1).getKey().compareTo(key) > 0) {
						children.get(i).remove(key, tree);
						break;
					}
				}
			}
		}
	}

	/** ɾ���ڵ� */
	protected void remove(Comparable key) {
		int index = -1;
		for (int i = 0; i < keyEntries.size(); i++) {
			if (keyEntries.get(i).getKey().compareTo(key) == 0) {
				index = i;
				break;
			}
		}
		if (index != -1) {
			keyEntries.remove(index);
		}
	}

	/** �жϵ�ǰ�ڵ��Ƿ�����ùؼ��� */
	protected boolean contains(Comparable key) {
		for (Entry<Comparable, Object> entry : keyEntries) {
			if (entry.getKey().compareTo(key) == 0) {
				return true;
			}
		}
		return false;
	}

}