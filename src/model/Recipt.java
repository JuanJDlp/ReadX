package model;

import java.util.Calendar;

public class Recipt {
    private Calendar trsacctionDate;
    private Double amount;

    public Recipt(Double amount) {
        this.trsacctionDate = Calendar.getInstance();
        this.amount = amount;
    }

    public Calendar getTrsacctionDate() {
        return trsacctionDate;
    }

    public void setTrsacctionDate(Calendar trsacctionDate) {
        this.trsacctionDate = trsacctionDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}
