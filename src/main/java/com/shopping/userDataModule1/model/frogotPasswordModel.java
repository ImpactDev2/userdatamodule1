package com.shopping.userDataModule1.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.validation.constraints.Pattern.Flag;

//@Data
//@Entity

public class frogotPasswordModel {
	@NotNull
    @NotEmpty(message = "Email not empty")
    @Size(min = 5, max = 50,message = "Email Length must be between 5 to 50")
    @Email(message = "The email address is not valid",flags = { Flag.CASE_INSENSITIVE })
    private String email;
    @NotNull
    @NotEmpty(message = "Passord is required")
    @Size(min = 8, max = 20,message = "Password Length Should be 8 to 20")
    private String password;
    @NotEmpty(message = "Passord is required")
    @Size(min = 8, max = 20,message = "Password Length Should be 8 to 20")
    @NotNull
    private String confPassword;

    public frogotPasswordModel(String email, String password, String confPassword) {
        this.email = email;
        this.password = password;
        this.confPassword = confPassword;
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

    public String getConfPassword() {
        return confPassword;
    }

    public void setConfPassword(String confPassword) {
        this.confPassword = confPassword;
    }

    public frogotPasswordModel() {
        super();
    }

    @Override
    public String toString() {
        return "frogotPasswordModel{" +
                "Email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confPassword='" + confPassword + '\'' +
                '}';
    }
}
