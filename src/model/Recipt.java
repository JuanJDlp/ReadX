package model;

import java.util.Calendar;
import java.util.ArrayList;
import Utils.Utils;

public class Recipt {

    private String content;
    private Calendar dateOfIssuance;

    public Recipt(ArrayList<BibliographicProduct> array) {
        content = " ";
        for (int i = 0; i < array.size(); i++) {
            content += "\n" + "Name: " + array.get(i).getName() +
                    " \nValue: " + array.get(i).getValue();
        }
        dateOfIssuance = Calendar.getInstance();
    }

    public String getContent() {
        return "==========================================\n" +
                "                   BILL                  \n " +
                "==========================================\n" +
                "Date of issuance: " + Utils.format(dateOfIssuance.getTime()) + "\n" +
                "==========================================\n" +
                this.content;
    }

    public String toString() {
        return getContent();
    }
}
