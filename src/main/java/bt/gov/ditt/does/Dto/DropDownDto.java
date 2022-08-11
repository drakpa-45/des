package bt.gov.ditt.does.Dto;

import java.io.Serializable;

/**
 * Created by Tandin on 6/9/2020.
 */
public class DropDownDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private int dropdownId;
    private String dropdownName;

    public int getDropdownId() {
        return dropdownId;
    }

    public void setDropdownId(int dropdownId) {
        this.dropdownId = dropdownId;
    }

    public String getDropdownName() {
        return dropdownName;
    }

    public void setDropdownName(String dropdownName) {
        this.dropdownName = dropdownName;
    }
}