package org.example;

public class Employee {

    private String mail;
    private String employeeName;

    public Employee(String mail) {
        this.mail = mail;
        this.employeeName = mail.substring(0, mail.indexOf("@"));
    }

    public String getName() {
        return employeeName;
    }

    public String getEmailId() {
        return mail;
    }
}
