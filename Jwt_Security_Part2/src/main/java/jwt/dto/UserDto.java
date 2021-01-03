package jwt.dto;



import com.fasterxml.jackson.annotation.JsonInclude;
import jwt.Entity.User;



@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private int userId;
    private String name;
    private String password;
    private String email;
    private String role;
    private String nic;
    private String address;
    private String country;
    private String passportNumber;

    private int securityKey;

    public UserDto(User user) {

    }

    public UserDto() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public int getSecurityKey() {
        return securityKey;
    }

    public void setSecurityKey(int securityKey) {
        this.securityKey = securityKey;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UserDto) {
            return this.getUserId() == ((UserDto) obj).getUserId();
        }
        return false;
    }
}