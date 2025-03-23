package vn.edu.fpt.electricalconponents.apis.user.dto;

public class UpdateUserRequest {
    private String fullname;
    private String phoneNumber;
    private String avatar;

    public UpdateUserRequest(String fullname, String phoneNumber, String avatar) {
        this.fullname = fullname;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
    }

    public UpdateUserRequest() {
    }

    @Override
    public String toString() {
        return "UpdateUserRequest{" +
                "fullname='" + fullname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
