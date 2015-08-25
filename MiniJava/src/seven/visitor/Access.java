//package Translate;
package seven.visitor;

public class Access {
	Level home;
	seven.Frame.Access acc;

	Access(Level h, seven.Frame.Access a) {
		home = h;
		acc = a;
	}

	public String toString() {
		return "[" + home.frame.name.toString() + "," + acc.toString() + "]";
	}
}