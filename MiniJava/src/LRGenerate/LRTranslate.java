package LRGenerate;

public class LRTranslate {
	LRState sourceState;
	Symbol tranSymbol;
	LRState targetState;

	String getkey() {
		return sourceState.getKey() + "~" + tranSymbol.getId() + "~"
				+ targetState.getKey();
	}

	public LRTranslate(LRState source, Symbol sym, LRState target) {
		this.sourceState = source;
		this.tranSymbol = sym;
		this.targetState = target;
	}

	public LRState getSourceState() {
		return sourceState;
	}

	public void setSourceState(LRState sourceState) {
		this.sourceState = sourceState;
	}

	public Symbol getTranSymbol() {
		return tranSymbol;
	}

	public void setTranSymbol(Symbol tranSymbol) {
		this.tranSymbol = tranSymbol;
	}

	public LRState getTargetState() {
		return targetState;
	}

	public void setTargetState(LRState targetState) {
		this.targetState = targetState;
	}
}
