package configuration.model;


import lombok.Data;

@Data
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String birthDate;

    public static final class UserBuilder {
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String birthDate;

        public UserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder birthDate(String birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public User userBuilder() {
            if (firstName.isEmpty()) {
                throw new IllegalStateException("Firstname cannot be empty");
            }
            if (lastName.isEmpty()) {
                throw new IllegalStateException("lastname cannot be empty");
            }
            if (email.isEmpty()) {
                throw new IllegalStateException("email cannot be empty");
            }
            if (password.isEmpty()) {
                throw new IllegalStateException("password cannot be empty");
            }
            if (birthDate.isEmpty()) {
                throw new IllegalStateException("birthdate cannot be empty");
            }
            User user = new User();
            user.firstName = this.firstName;
            user.lastName = this.lastName;
            user.email = this.email;
            user.password = this.password;
            user.birthDate = this.birthDate;
            return user;
        }
    }

}
