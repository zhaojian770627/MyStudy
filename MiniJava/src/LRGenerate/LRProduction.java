package LRGenerate;

public class LRProduction {
	Production production;
	int dotPos;

	public String getKey() {
		return production.getKey() + "-" + String.valueOf(dotPos);
	}

	public LRProduction(Production pro, int dotpos) {
		this.production = pro;
		this.dotPos = dotpos;
	}

	public Production getProduction() {
		return production;
	}

	public void setProduction(Production production) {
		this.production = production;
	}

	public int getDotPos() {
		return dotPos;
	}

	public void setDotPos(int dotPos) {
		this.dotPos = dotPos;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		LRProduction lr = new LRProduction(this.production, this.dotPos);
		return lr;
	}

	public void print() {
		System.out.print(production.getLeft().getId() + "->");
		int i = 0;
		for (Symbol s : production.getRight()) {
			if (i == this.dotPos)
				System.out.print(".");
			System.out.print(s.getId());
			i++;
		}
		if (production.getRight().size() == this.dotPos)
			System.out.print(".");

	}
}
