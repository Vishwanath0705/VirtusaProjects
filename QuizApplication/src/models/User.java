package models;

public class User {
    private int id;
    private String username;
    private String email;
    private String mobile;
    private String password;
    private String userType;
    
    public User(){};
    
    public User(String username, String email, String mobile, String password, String userType) {
        this.username = username;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.userType = userType;
    }   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    
    @Override
    public String toString(){
        return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            ", mobile='" + mobile + '\'' +
            ", userType='" + userType + '\'' +
            '}';
    }
}
