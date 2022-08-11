package bt.gov.ditt.does.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Tandin on 6/10/2020.
 */
@Entity
@Table(name="t_building_application_details")
public class BuildingEntity {
    @Id
    @Column(name = "Application_Id")
    private String Application_Id;
    private int Service_Id;
    private String Is_Private_Building;
    private String Is_Existing_Application;
    private String Previous_Application_Id;
    private String Institution_Name;
    private String Institution_Registration_No;
    private String Details;
    private String Thram_Nos;
    private String Plot_Nos;
    private int Precinct_Type_Id;
    private float Site_Area;
    private int Site_Area_Unit;
    private int Dzonkhag_Id;
    private int Gewog_Id;
    private int Village_Id;
    private String Contact_Address;
    private String Contact_Email_Id;
    private String Contact_Mobile_No;
    private String Architect_Name;
    private String Architect_Registration_No;
    private String Structural_Name;
    private String Structural_ID;
    private String Electrical_Name;
    private String Electrical_ID;
    private int Building_Application_Category_Id;
    private int Building_Use_Id;
    private int Construction_Type;
    private String Created_By;
    private String Appl_comments;
    private String Updated_By;
    private int Status_id;
    private String Assign_Architect;
    private int Str_Stutas_Id;
    private int Ele_Stutas_Id;
    private String Structural_Person_Name;
    private String Electrical_Person_Name;
    private int Project_Title_Id;
    private String Electrical_Graduation_Year;
    private String Architect_Contact_No;
    private String Structural_Graduation_Year;
    private int Number_of_Floor;
    private String Is_Architect_Forwarded;
    private String Is_Structural_Forwarded;
    private String Is_Electrical_Forwarded;
    private int juris_id;
    private int juris_type;


    public BuildingEntity() {
    }

    public BuildingEntity(String application_Id, int service_Id, String is_Private_Building, String is_Existing_Application, String previous_Application_Id, String institution_Name, String institution_Registration_No, String details, String thram_Nos, String plot_Nos, int precinct_Type_Id, float site_Area, int site_Area_Unit, int dzonkhag_Id, int gewog_Id, int village_Id, String contact_Address, String contact_Email_Id, String contact_Mobile_No, String architect_Name, String architect_Registration_No, String structural_Name, String structural_ID, String electrical_Name, String electrical_ID, int building_Application_Category_Id, int building_Use_Id, int construction_Type, String created_By, String appl_comments, String updated_By, int status_id, String assign_Architect, int str_Stutas_Id, int ele_Stutas_Id, String structural_Person_Name, String electrical_Person_Name, int project_Title_Id, String electrical_Graduation_Year, String architect_Contact_No, String structural_Graduation_Year, int number_of_Floor, String is_Architect_Forwarded, String is_Structural_Forwarded, String is_Electrical_Forwarded, int juris_id, int juris_type) {
        Application_Id = application_Id;
        Service_Id = service_Id;
        Is_Private_Building = is_Private_Building;
        Is_Existing_Application = is_Existing_Application;
        Previous_Application_Id = previous_Application_Id;
        Institution_Name = institution_Name;
        Institution_Registration_No = institution_Registration_No;
        Details = details;
        Thram_Nos = thram_Nos;
        Plot_Nos = plot_Nos;
        Precinct_Type_Id = precinct_Type_Id;
        Site_Area = site_Area;
        Site_Area_Unit = site_Area_Unit;
        Dzonkhag_Id = dzonkhag_Id;
        Gewog_Id = gewog_Id;
        Village_Id = village_Id;
        Contact_Address = contact_Address;
        Contact_Email_Id = contact_Email_Id;
        Contact_Mobile_No = contact_Mobile_No;
        Architect_Name = architect_Name;
        Architect_Registration_No = architect_Registration_No;
        Structural_Name = structural_Name;
        Structural_ID = structural_ID;
        Electrical_Name = electrical_Name;
        Electrical_ID = electrical_ID;
        Building_Application_Category_Id = building_Application_Category_Id;
        Building_Use_Id = building_Use_Id;
        Construction_Type = construction_Type;
        Created_By = created_By;
        Appl_comments = appl_comments;
        Updated_By = updated_By;
        Status_id = status_id;
        Assign_Architect = assign_Architect;
        Str_Stutas_Id = str_Stutas_Id;
        Ele_Stutas_Id = ele_Stutas_Id;
        Structural_Person_Name = structural_Person_Name;
        Electrical_Person_Name = electrical_Person_Name;
        Project_Title_Id = project_Title_Id;
        Electrical_Graduation_Year = electrical_Graduation_Year;
        Architect_Contact_No = architect_Contact_No;
        Structural_Graduation_Year = structural_Graduation_Year;
        Number_of_Floor = number_of_Floor;
        Is_Architect_Forwarded = is_Architect_Forwarded;
        Is_Structural_Forwarded = is_Structural_Forwarded;
        Is_Electrical_Forwarded = is_Electrical_Forwarded;
        this.juris_id = juris_id;
        this.juris_type = juris_type;
    }

    public int getJuris_id() {
        return juris_id;
    }

    public void setJuris_id(int juris_id) {
        this.juris_id = juris_id;
    }

    public int getJuris_type() {
        return juris_type;
    }

    public void setJuris_type(int juris_type) {
        this.juris_type = juris_type;
    }

    public String getApplication_Id() {
        return Application_Id;
    }

    public void setApplication_Id(String application_Id) {
        Application_Id = application_Id;
    }

    public int getService_Id() {
        return Service_Id;
    }

    public void setService_Id(int service_Id) {
        Service_Id = service_Id;
    }

    public String getIs_Private_Building() {
        return Is_Private_Building;
    }

    public void setIs_Private_Building(String is_Private_Building) {
        Is_Private_Building = is_Private_Building;
    }

    public String getIs_Existing_Application() {
        return Is_Existing_Application;
    }

    public void setIs_Existing_Application(String is_Existing_Application) {
        Is_Existing_Application = is_Existing_Application;
    }

    public String getPrevious_Application_Id() {
        return Previous_Application_Id;
    }

    public void setPrevious_Application_Id(String previous_Application_Id) {
        Previous_Application_Id = previous_Application_Id;
    }

    public String getInstitution_Name() {
        return Institution_Name;
    }

    public void setInstitution_Name(String institution_Name) {
        Institution_Name = institution_Name;
    }

    public String getInstitution_Registration_No() {
        return Institution_Registration_No;
    }

    public void setInstitution_Registration_No(String institution_Registration_No) {
        Institution_Registration_No = institution_Registration_No;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public String getThram_Nos() {
        return Thram_Nos;
    }

    public void setThram_Nos(String thram_Nos) {
        Thram_Nos = thram_Nos;
    }

    public String getPlot_Nos() {
        return Plot_Nos;
    }

    public void setPlot_Nos(String plot_Nos) {
        Plot_Nos = plot_Nos;
    }

    public int getPrecinct_Type_Id() {
        return Precinct_Type_Id;
    }

    public void setPrecinct_Type_Id(int precinct_Type_Id) {
        Precinct_Type_Id = precinct_Type_Id;
    }

    public float getSite_Area() {
        return Site_Area;
    }

    public void setSite_Area(float site_Area) {
        Site_Area = site_Area;
    }

    public int getSite_Area_Unit() {
        return Site_Area_Unit;
    }

    public void setSite_Area_Unit(int site_Area_Unit) {
        Site_Area_Unit = site_Area_Unit;
    }

    public int getDzonkhag_Id() {
        return Dzonkhag_Id;
    }

    public void setDzonkhag_Id(int dzonkhag_Id) {
        Dzonkhag_Id = dzonkhag_Id;
    }

    public int getGewog_Id() {
        return Gewog_Id;
    }

    public void setGewog_Id(int gewog_Id) {
        Gewog_Id = gewog_Id;
    }

    public int getVillage_Id() {
        return Village_Id;
    }

    public void setVillage_Id(int village_Id) {
        Village_Id = village_Id;
    }

    public String getContact_Address() {
        return Contact_Address;
    }

    public void setContact_Address(String contact_Address) {
        Contact_Address = contact_Address;
    }

    public String getContact_Email_Id() {
        return Contact_Email_Id;
    }

    public void setContact_Email_Id(String contact_Email_Id) {
        Contact_Email_Id = contact_Email_Id;
    }

    public String getContact_Mobile_No() {
        return Contact_Mobile_No;
    }

    public void setContact_Mobile_No(String contact_Mobile_No) {
        Contact_Mobile_No = contact_Mobile_No;
    }

    public String getArchitect_Name() {
        return Architect_Name;
    }

    public void setArchitect_Name(String architect_Name) {
        Architect_Name = architect_Name;
    }

    public String getArchitect_Registration_No() {
        return Architect_Registration_No;
    }

    public void setArchitect_Registration_No(String architect_Registration_No) {
        Architect_Registration_No = architect_Registration_No;
    }

    public String getStructural_Name() {
        return Structural_Name;
    }

    public void setStructural_Name(String structural_Name) {
        Structural_Name = structural_Name;
    }

    public String getStructural_ID() {
        return Structural_ID;
    }

    public void setStructural_ID(String structural_ID) {
        Structural_ID = structural_ID;
    }

    public String getElectrical_Name() {
        return Electrical_Name;
    }

    public void setElectrical_Name(String electrical_Name) {
        Electrical_Name = electrical_Name;
    }

    public String getElectrical_ID() {
        return Electrical_ID;
    }

    public void setElectrical_ID(String electrical_ID) {
        Electrical_ID = electrical_ID;
    }

    public int getBuilding_Application_Category_Id() {
        return Building_Application_Category_Id;
    }

    public void setBuilding_Application_Category_Id(int building_Application_Category_Id) {
        Building_Application_Category_Id = building_Application_Category_Id;
    }

    public int getBuilding_Use_Id() {
        return Building_Use_Id;
    }

    public void setBuilding_Use_Id(int building_Use_Id) {
        Building_Use_Id = building_Use_Id;
    }

    public int getConstruction_Type() {
        return Construction_Type;
    }

    public void setConstruction_Type(int construction_Type) {
        Construction_Type = construction_Type;
    }

    public String getCreated_By() {
        return Created_By;
    }

    public void setCreated_By(String created_By) {
        Created_By = created_By;
    }

    public String getAppl_comments() {
        return Appl_comments;
    }

    public void setAppl_comments(String appl_comments) {
        Appl_comments = appl_comments;
    }

    public String getUpdated_By() {
        return Updated_By;
    }

    public void setUpdated_By(String updated_By) {
        Updated_By = updated_By;
    }

    public int getStatus_id() {
        return Status_id;
    }

    public void setStatus_id(int status_id) {
        Status_id = status_id;
    }

    public String getAssign_Architect() {
        return Assign_Architect;
    }

    public void setAssign_Architect(String assign_Architect) {
        Assign_Architect = assign_Architect;
    }

    public int getStr_Stutas_Id() {
        return Str_Stutas_Id;
    }

    public void setStr_Stutas_Id(int str_Stutas_Id) {
        Str_Stutas_Id = str_Stutas_Id;
    }

    public int getEle_Stutas_Id() {
        return Ele_Stutas_Id;
    }

    public void setEle_Stutas_Id(int ele_Stutas_Id) {
        Ele_Stutas_Id = ele_Stutas_Id;
    }

    public String getStructural_Person_Name() {
        return Structural_Person_Name;
    }

    public void setStructural_Person_Name(String structural_Person_Name) {
        Structural_Person_Name = structural_Person_Name;
    }

    public String getElectrical_Person_Name() {
        return Electrical_Person_Name;
    }

    public void setElectrical_Person_Name(String electrical_Person_Name) {
        Electrical_Person_Name = electrical_Person_Name;
    }

    public int getProject_Title_Id() {
        return Project_Title_Id;
    }

    public void setProject_Title_Id(int project_Title_Id) {
        Project_Title_Id = project_Title_Id;
    }

    public String getElectrical_Graduation_Year() {
        return Electrical_Graduation_Year;
    }

    public void setElectrical_Graduation_Year(String electrical_Graduation_Year) {
        Electrical_Graduation_Year = electrical_Graduation_Year;
    }

    public String getArchitect_Contact_No() {
        return Architect_Contact_No;
    }

    public void setArchitect_Contact_No(String architect_Contact_No) {
        Architect_Contact_No = architect_Contact_No;
    }

    public String getStructural_Graduation_Year() {
        return Structural_Graduation_Year;
    }

    public void setStructural_Graduation_Year(String structural_Graduation_Year) {
        Structural_Graduation_Year = structural_Graduation_Year;
    }

    public int getNumber_of_Floor() {
        return Number_of_Floor;
    }

    public void setNumber_of_Floor(int number_of_Floor) {
        Number_of_Floor = number_of_Floor;
    }

    public String getIs_Architect_Forwarded() {
        return Is_Architect_Forwarded;
    }

    public void setIs_Architect_Forwarded(String is_Architect_Forwarded) {
        Is_Architect_Forwarded = is_Architect_Forwarded;
    }

    public String getIs_Structural_Forwarded() {
        return Is_Structural_Forwarded;
    }

    public void setIs_Structural_Forwarded(String is_Structural_Forwarded) {
        Is_Structural_Forwarded = is_Structural_Forwarded;
    }

    public String getIs_Electrical_Forwarded() {
        return Is_Electrical_Forwarded;
    }

    public void setIs_Electrical_Forwarded(String is_Electrical_Forwarded) {
        Is_Electrical_Forwarded = is_Electrical_Forwarded;
    }
}
