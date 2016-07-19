package practice;

import java.io.Serializable;

public class Student implements Serializable{
	private int age;
	private String name;
	private transient String Id;
	private String sex;

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

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Student(String name, String sex) {
		super();
		this.name = name;
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "Student [age=" + age + ", name=" + name + ", Id=" + Id
				+ ", sex=" + sex + "]";
	}

}
