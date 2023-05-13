package Factories;

import model.AbstractUser;
import model.Premium;
import model.Standar;

import java.util.Calendar;

public class UserFactory {
    public UserFactory() {
    }

    /**
     * The function creates a new user object based on the given ID, name, and type
     * of user.
     * 
     * @param ID         A unique identifier for the user being created.
     * @param name       The name of the user being created.
     * @param typeOfUser An integer value representing the type of user to be
     *                   created. A value of 1
     *                   represents a standard user, while a value of 2 represents a
     *                   premium user.
     * @return An instance of the AbstractUser class, which can be either a Standar
     *         or Premium object
     *         depending on the value of the typeOfUser parameter.
     */
    public AbstractUser createUser(String ID, String name, int typeOfUser) {
        AbstractUser user = null;
        Calendar actualDate = Calendar.getInstance();
        if (typeOfUser == 1) {
            user = new Standar(ID, name, actualDate);
        } else if (typeOfUser == 2) {
            user = new Premium(ID, name, actualDate);
        }
        return user;

    }
}
