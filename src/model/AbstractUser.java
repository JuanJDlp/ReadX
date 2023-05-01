package model;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public abstract class AbstractUser {
    protected String ID;
    protected String name;
    protected Calendar dateOfEntering;
    private SimpleDateFormat sdf;

    public AbstractUser(String ID, String name, Calendar dateOfEntering) {
        this.ID = ID;
        this.name = name;
        this.dateOfEntering = dateOfEntering;
        sdf = new SimpleDateFormat("dd/MM/yyyy");
    }

    public Calendar getDateOfEntering() {
        return dateOfEntering;
    }

    public void setDateOfEntering(Calendar dateOfEntering) {
        this.dateOfEntering = dateOfEntering;
    }

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "ID: " + ID + "\nName: " + name + "\nDate of entering: " + sdf.format(dateOfEntering.getTime());
    }
}
