
public class Edge {

	private int start;
	private int terminal;

	public Edge(int s, int t) {
		setStart(s);
		setTerminal(t);
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getTerminal() {
		return terminal;
	}

	public void setTerminal(int terminal) {
		this.terminal = terminal;
	}
}
