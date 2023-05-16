package io.lightfeather.springtemplate.model;

import jakarta.validation.constraints.*;

public class SupervisorNotification {

    @NotNull(message = "First Name is required")
    @NotBlank(message = "First Name can not be empty")
    @Pattern(regexp="[a-zA-Z]+",message="first name must only contain letters, no numbers")
    private String firstName;

    @NotNull(message = "Last Name is required")
    @NotBlank(message = "Last Name can not be empty")
    @Pattern(regexp="[a-zA-Z]+",message="last name must only contain letters, no numbers")
    private String lastName;

    @NotBlank(message = "Email can not be empty")
    @Email(message="Enter valid Email Id.")
    private String email;

    @NotBlank(message = "Number can not be empty")
    @Digits(message="Number should contain 10 digits.", fraction = 0, integer = 10)
    private String phoneNumber;

    @NotBlank(message = "Supervisor can not be empty")
    @NotNull(message = "Supervisor is required")
    private String supervisor;

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

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }
}
