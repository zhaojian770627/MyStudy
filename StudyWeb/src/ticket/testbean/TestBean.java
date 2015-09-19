package ticket.testbean;

public class TestBean implements ITestBean {
	int age;
	String name;
	ITestBean spouse;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ITestBean getSpouse() {
		return spouse;
	}

	public void setSpouse(ITestBean spouse) {
		this.spouse = spouse;
	}

}
