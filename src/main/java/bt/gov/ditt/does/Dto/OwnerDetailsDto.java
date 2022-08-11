package bt.gov.ditt.does.Dto;

import java.io.Serializable;

/**
 * Created by Tandin on 9/16/2020.
 */
public class OwnerDetailsDto implements Serializable {
    private String ownerCid;
    private String ownerName;
    private String ownerContact;
    private String Application_Id;

    public OwnerDetailsDto() {
    }

    public OwnerDetailsDto(String ownerCid, String ownerName, String ownerContact, String application_Id) {
        this.ownerCid = ownerCid;
        this.ownerName = ownerName;
        this.ownerContact = ownerContact;
        Application_Id = application_Id;
    }

    public String getApplication_Id() {
        return Application_Id;
    }

    public void setApplication_Id(String application_Id) {
        Application_Id = application_Id;
    }

    public String getOwnerCid() {
        return ownerCid;
    }

    public void setOwnerCid(String ownerCid) {
        this.ownerCid = ownerCid;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerContact() {
        return ownerContact;
    }

    public void setOwnerContact(String ownerContact) {
        this.ownerContact = ownerContact;
    }
}
