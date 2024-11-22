package org.app.Object;

public class User {
    private String username;
    private String password;
    private String name;
    private String phoneNumber;
    private String address;
    private String email;
    private String birthday;

    private User(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.name = builder.name;
        this.phoneNumber = builder.phoneNumber;
        this.address = builder.address;
        this.email = builder.email;
        this.birthday = builder.birthday;
    }

    public static class Builder {
        private String username;
        private String password;
        private String name;
        private String phoneNumber;
        private String address;
        private String email;
        private String birthday;

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setBirthday(String birthday) {
            this.birthday = birthday;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthday() {
        return birthday;
    }
}

