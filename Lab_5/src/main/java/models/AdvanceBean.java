package models;

import java.util.Date;

public class AdvanceBean {
private Integer[] hobby;
private Date recruitDate;
private String photo;
public AdvanceBean () {};
//getters and setters
public Integer[] getHobby() { return hobby; }
public void setHobby(Integer[] hobby) { this.hobby = hobby; }

public Date getDate() { return recruitDate; }
public void setDate(Date recruitDate) { this.recruitDate = recruitDate; }

public String getPhoto(){ return photo; }
public void setPhoto(String photo) { this.photo = photo; }


}
