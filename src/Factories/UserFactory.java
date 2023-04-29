package Factories;

import model.AbstractUser;
import model.Premium;
import model.Standar;

import java.util.Calendar;

public class UserFactory {
    public UserFactory() {
    }

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
