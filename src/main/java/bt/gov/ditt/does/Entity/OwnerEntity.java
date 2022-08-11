package bt.gov.ditt.does.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Tandin on 6/19/2020.
 */

@Entity
@Table(name="t_building_ownership_details")
public class OwnerEntity {

    @Id
    @Column(name = "Application_Id")
    private String Application_Id;

    private String Building_No;
    private String Owner_CID_No;
    private String Owner_Name;
    private String Owner_Date_Of_Birth;
    private String Owner_Email_Id;
    private String Owner_Mobile_No;
    private float Owner_Percentage_Share;
    private String Created_By;
    private String Updated_By;

    public OwnerEntity() {
    }

    public OwnerEntity(String application_Id, String building_No, String owner_CID_No, String owner_Name, String owner_Date_Of_Birth, String owner_Email_Id, String owner_Mobile_No, float owner_Percentage_Share, String created_By, String updated_By) {
        Application_Id = application_Id;
        Building_No = building_No;
        Owner_CID_No = owner_CID_No;
        Owner_Name = owner_Name;
        Owner_Date_Of_Birth = owner_Date_Of_Birth;
        Owner_Email_Id = owner_Email_Id;
        Owner_Mobile_No = owner_Mobile_No;
        Owner_Percentage_Share = owner_Percentage_Share;
        Created_By = created_By;
        Updated_By = updated_By;
    }

    public String getApplication_Id() {
        return Application_Id;
    }

    public void setApplication_Id(String application_Id) {
        Application_Id = application_Id;
    }

    public String getBuilding_No() {
        return Building_No;
    }

    public void setBuilding_No(String building_No) {
        Building_No = building_No;
    }

    public String getOwner_CID_No() {
        return Owner_CID_No;
    }

    public void setOwner_CID_No(String owner_CID_No) {
        Owner_CID_No = owner_CID_No;
    }

    public String getOwner_Name() {
        return Owner_Name;
    }

    public void setOwner_Name(String owner_Name) {
        Owner_Name = owner_Name;
    }

    public String getOwner_Date_Of_Birth() {
        return Owner_Date_Of_Birth;
    }

    public void setOwner_Date_Of_Birth(String owner_Date_Of_Birth) {
        Owner_Date_Of_Birth = owner_Date_Of_Birth;
    }

    public String getOwner_Email_Id() {
        return Owner_Email_Id;
    }

    public void setOwner_Email_Id(String owner_Email_Id) {
        Owner_Email_Id = owner_Email_Id;
    }

    public String getOwner_Mobile_No() {
        return Owner_Mobile_No;
    }

    public void setOwner_Mobile_No(String owner_Mobile_No) {
        Owner_Mobile_No = owner_Mobile_No;
    }

    public float getOwner_Percentage_Share() {
        return Owner_Percentage_Share;
    }

    public void setOwner_Percentage_Share(float owner_Percentage_Share) {
        Owner_Percentage_Share = owner_Percentage_Share;
    }

    public String getCreated_By() {
        return Created_By;
    }

    public void setCreated_By(String created_By) {
        Created_By = created_By;
    }

    public String getUpdated_By() {
        return Updated_By;
    }

    public void setUpdated_By(String updated_By) {
        Updated_By = updated_By;
    }
}