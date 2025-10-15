package models;

import java.util.Date;

public class Staff {
	 private String fullname;
	    private int age;
	    private boolean gender;
	    private Date recruit;
	    private String country;
	    private String photo;
	    private int[] hobbies;
	    private double salary;

	    public Staff() {}

	    // Getters and setters
	    public String getFullname() { return fullname; }
	    public void setFullname(String fullname) { this.fullname = fullname; }

	    public int getAge() { return age; }
	    public void setAge(int age) { this.age = age; }

	    public boolean isGender() { return gender; }
	    public void setGender(boolean gender) { this.gender = gender; }

	    public Date getRecruit() { return recruit; }
	    public void setRecruit(Date recruit) { this.recruit = recruit; }

	    public String getCountry() { return country; }
	    public void setCountry(String country) { this.country = country; }

	    public String getPhoto() { return photo; }
	    public void setPhoto(String photo) { this.photo = photo; }

	    public int[] getHobbies() { return hobbies; }
	    public void setHobbies(int[] hobbies) { this.hobbies = hobbies; }

	    public double getSalary() { return salary; }
	    public void setSalary(double salary) { this.salary = salary; }

}