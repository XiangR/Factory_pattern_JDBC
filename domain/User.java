package jdbc.domain;

import java.util.Date;

// create a domain Object
public class User {
	
	// 作为每一个实体都要有一个  真是的对象  在这里user 与 数据库里的  user表对应
	private int id;
	private String name;
	private Date birthday;
	private float money;

	public User() {
		
	}
	
	public User(String name) {
		this.name = name;
	}
	
	public String toString() {
		return " id = " + this.id + " name = " + this.name + " birthday = " + this.birthday + " money = " + this.money ;
 	}
	
	public void showName() {
		System.out.println(this.name);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	
}
