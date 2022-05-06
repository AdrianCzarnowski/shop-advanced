package configuration.factory;

import configuration.model.User;

public class UserFactory {

    public User getRandomUser() {

        User user = new User.UserBuilder()
                .firstName(FakeDataFactory.getFakeFirstName())
                .lastName(FakeDataFactory.getFakeLastName())
                .email(FakeDataFactory.getFakeEmail())
                .password(FakeDataFactory.getPassword())
                .birthDate(FakeDataFactory.getFakeBirthDate()).userBuilder();
        return user;
    }

    public User getAlreadyRegisterUser() {
        User user = new User.UserBuilder()
                .firstName("Jan")
                .lastName("Kowalski")
                .email("jankowalski@gmail.com")
                .password("1qaz@WSX")
                .birthDate("01/01/1999").userBuilder();
        return user;

    }
}
