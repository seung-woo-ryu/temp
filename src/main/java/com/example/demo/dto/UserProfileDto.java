package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserProfileDto {
    public String id;
    public String email;
    public String name;
    public String mobileNum;
    public String birthdate;
    public Long status;
    public String lang;
    public String country;
    public Boolean social;
    public Boolean password;
    public String passwordDate;
    public String pinDate;
    public String signUpDate;



}
