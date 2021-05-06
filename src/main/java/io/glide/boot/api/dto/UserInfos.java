package io.glide.boot.api.dto;

import java.util.List;

public class UserInfos {
    private String firstName;

    private String lastName;

    private String department;

    /**
     * List of all known user formatted addresses.<br>
     * Example of formatted address: "23 rue de voltaire, 75015 PARIS, FRANCE"
     */
    private List<AddressDto> adresses;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<AddressDto> getAdresses() {
        return adresses;
    }

    public void setAdresses(List<AddressDto> adresses) {
        this.adresses = adresses;
    }

    public UserInfos(String firstName, String lastName, String department, List<AddressDto> adresses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.adresses = adresses;
    }

    public UserInfos() {
    }
}
