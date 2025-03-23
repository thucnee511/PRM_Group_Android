package vn.edu.fpt.electricalconponents.apis.authentication.dto;

public class SignIgnRequest {
    private String email;
    private String password;

    public SignIgnRequest() {
    }

    public SignIgnRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "SignIgnRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
