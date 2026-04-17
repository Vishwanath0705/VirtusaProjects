
package models;

public class Student {
    private int id;
    private String name;
    private String rollNo;
    private String email;
    private String phoneNumber;
    private int marks;
    private int totalQuestions;
    private double percentage;
    
    public Student(){}
    
    public Student(String name, String rollNo, String email, String phoneNumber) {
        this.name = name;
        this.rollNo = rollNo;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    
    public Student(String name, String rollNo, String email, String phoneNumber,
                   int marks, int totalQuestions, double percentage) {
        this.name = name;
        this.rollNo = rollNo;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.marks = marks;
        this.totalQuestions = totalQuestions;
        this.percentage = percentage;
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

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
    
    @Override
    public String toString(){
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rollNo='" + rollNo + '\'' +
                ", marks=" + marks +
                ", percentage=" + percentage +
                '}';
    }
}
