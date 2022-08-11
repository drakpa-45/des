package bt.gov.ditt.does.Dto;


import bt.gov.ditt.does.Entity.OwnerEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Created by Tandin on 6/10/2020.
 */
public class BuildingDto  implements Serializable {

    private String Application_Id;
    private int Service_Id;
    private String Is_Private_Building;
    private String Is_E0xisting_Application;
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
    private String Created_By;
    private String Appl_comments;
    private String Updated_By;
    private int Status_id;
    private String Assign_Architect;
    private int Str_Stutas_Id;
    private int Ele_Stutas_Id;
    private String Structural_Person_Name;
    private String workflow_instance_Id;
    private String taskInstanceId;
    private String Electrical_Person_Name;
    private String Dzongkhag_Name;
    private String Gewog_Name;
    private String Village_Name;
    private String Unit_Name;
    private String isArchitectVerificationReq;
    private String isStrucVerificationReq;
    private String isElecVerificationReq;
    private String taskId;
    private BigInteger rowCount;
    private int number_of_Floor;
    private String reasonId;
    private String reasonDesc;
    private String buildingUseString;
    private String constructionTypeString;
    private String architectRemarks;
    private String applicationType;
    private int projectTitleId;
    private String projectTitle;
    private String structural_Graduation_Year;
    private String electrical_Graduation_Year;
    private String architect_Contact_No;
    private String document_path;
    private String document_id;
    private String document_Type;
    private String document_Name;
    private MultipartFile appUploadFile;
    private MultipartFile appUploadFileDES;
    private String Is_Architect_Forwarded;
    private String Is_Structural_Forwarded;
    private String Is_Electrical_Forwarded;
    private OwnerEntity[] ownerList;
    private Date Created_on;
    private Date Modified_On;
    private String Task_Stage_Name;
    private String Modified_By;
    private String Owner_Name;
    private String Owner_Contact_No;
    private String Building_Use_Name;
    private String location_Id;
    private String Remarks;
    private Date Forwarded_On;
    private Date Claimed_On;
    private Date Approved_On;
    private Date Rejcted_On;
    private String stringForwarded_On;
    private String stringClaimed_On;
    private String stringApproved_On;
    private String stringRejcted_On;
    private String stringCreated_on;
    private String stringModified_On;
    private String stringHold_On;
    private String stringUnHold_On;
    private String UnHoldReason;
    private String HoldReason;
    private String RejectReason;
    private BigInteger rowCountTotal;
    private BigInteger rowCountClaimed;
    private BigInteger rowCountApproved;
    private BigInteger rowCountRejected;
    private BigInteger rowCountForwarded;
    private BigInteger rowCountHold;
    private BigInteger rowCountInitiated;
    private String ForwardReason;
    private int juris_id;
    private List<OwnerDetailsDto> ownerDetailsDtos;



    public BuildingDto() {
    }

    public BuildingDto(String application_Id, int service_Id, String is_Private_Building, String is_E0xisting_Application, String previous_Application_Id, String institution_Name, String institution_Registration_No, String details, String thram_Nos, String plot_Nos, int precinct_Type_Id, float site_Area, int site_Area_Unit, int dzonkhag_Id, int gewog_Id, int village_Id, String contact_Address, String contact_Email_Id, String contact_Mobile_No, String architect_Name, String architect_Registration_No, String structural_Name, String structural_ID, String electrical_Name, String electrical_ID, int building_Application_Category_Id, int building_Use_Id, String created_By, String appl_comments, String updated_By, int status_id, String assign_Architect, int str_Stutas_Id, int ele_Stutas_Id, String structural_Person_Name, String workflow_instance_Id, String taskInstanceId, String electrical_Person_Name, String dzongkhag_Name, String gewog_Name, String village_Name, String unit_Name, String isArchitectVerificationReq, String isStrucVerificationReq, String isElecVerificationReq, String taskId, BigInteger rowCount, int number_of_Floor, String reasonId, String reasonDesc, String buildingUseString, String constructionTypeString, String architectRemarks, String applicationType, int projectTitleId, String projectTitle, String structural_Graduation_Year, String electrical_Graduation_Year, String architect_Contact_No, String document_path, String document_id, String document_Type, String document_Name, MultipartFile appUploadFile, MultipartFile appUploadFileDES, String is_Architect_Forwarded, String is_Structural_Forwarded, String is_Electrical_Forwarded, OwnerEntity[] ownerList, Date created_on, Date modified_On, String task_Stage_Name, String modified_By, String owner_Name, String owner_Contact_No, String building_Use_Name, String location_Id, String remarks, Date forwarded_On, Date claimed_On, Date approved_On, Date rejcted_On, String stringForwarded_On, String stringClaimed_On, String stringApproved_On, String stringRejcted_On, String stringCreated_on, String stringModified_On, String stringHold_On, String stringUnHold_On, String unHoldReason, String holdReason, String rejectReason, BigInteger rowCountTotal, BigInteger rowCountClaimed, BigInteger rowCountApproved, BigInteger rowCountRejected, BigInteger rowCountForwarded, BigInteger rowCountHold, BigInteger rowCountInitiated, String forwardReason, int juris_id, List<OwnerDetailsDto> ownerDetailsDtos) {
        Application_Id = application_Id;
        Service_Id = service_Id;
        Is_Private_Building = is_Private_Building;
        Is_E0xisting_Application = is_E0xisting_Application;
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
        Created_By = created_By;
        Appl_comments = appl_comments;
        Updated_By = updated_By;
        Status_id = status_id;
        Assign_Architect = assign_Architect;
        Str_Stutas_Id = str_Stutas_Id;
        Ele_Stutas_Id = ele_Stutas_Id;
        Structural_Person_Name = structural_Person_Name;
        this.workflow_instance_Id = workflow_instance_Id;
        this.taskInstanceId = taskInstanceId;
        Electrical_Person_Name = electrical_Person_Name;
        Dzongkhag_Name = dzongkhag_Name;
        Gewog_Name = gewog_Name;
        Village_Name = village_Name;
        Unit_Name = unit_Name;
        this.isArchitectVerificationReq = isArchitectVerificationReq;
        this.isStrucVerificationReq = isStrucVerificationReq;
        this.isElecVerificationReq = isElecVerificationReq;
        this.taskId = taskId;
        this.rowCount = rowCount;
        this.number_of_Floor = number_of_Floor;
        this.reasonId = reasonId;
        this.reasonDesc = reasonDesc;
        this.buildingUseString = buildingUseString;
        this.constructionTypeString = constructionTypeString;
        this.architectRemarks = architectRemarks;
        this.applicationType = applicationType;
        this.projectTitleId = projectTitleId;
        this.projectTitle = projectTitle;
        this.structural_Graduation_Year = structural_Graduation_Year;
        this.electrical_Graduation_Year = electrical_Graduation_Year;
        this.architect_Contact_No = architect_Contact_No;
        this.document_path = document_path;
        this.document_id = document_id;
        this.document_Type = document_Type;
        this.document_Name = document_Name;
        this.appUploadFile = appUploadFile;
        this.appUploadFileDES = appUploadFileDES;
        Is_Architect_Forwarded = is_Architect_Forwarded;
        Is_Structural_Forwarded = is_Structural_Forwarded;
        Is_Electrical_Forwarded = is_Electrical_Forwarded;
        this.ownerList = ownerList;
        Created_on = created_on;
        Modified_On = modified_On;
        Task_Stage_Name = task_Stage_Name;
        Modified_By = modified_By;
        Owner_Name = owner_Name;
        Owner_Contact_No = owner_Contact_No;
        Building_Use_Name = building_Use_Name;
        this.location_Id = location_Id;
        Remarks = remarks;
        Forwarded_On = forwarded_On;
        Claimed_On = claimed_On;
        Approved_On = approved_On;
        Rejcted_On = rejcted_On;
        this.stringForwarded_On = stringForwarded_On;
        this.stringClaimed_On = stringClaimed_On;
        this.stringApproved_On = stringApproved_On;
        this.stringRejcted_On = stringRejcted_On;
        this.stringCreated_on = stringCreated_on;
        this.stringModified_On = stringModified_On;
        this.stringHold_On = stringHold_On;
        this.stringUnHold_On = stringUnHold_On;
        UnHoldReason = unHoldReason;
        HoldReason = holdReason;
        RejectReason = rejectReason;
        this.rowCountTotal = rowCountTotal;
        this.rowCountClaimed = rowCountClaimed;
        this.rowCountApproved = rowCountApproved;
        this.rowCountRejected = rowCountRejected;
        this.rowCountForwarded = rowCountForwarded;
        this.rowCountHold = rowCountHold;
        this.rowCountInitiated = rowCountInitiated;
        ForwardReason = forwardReason;
        this.juris_id = juris_id;
        this.ownerDetailsDtos = ownerDetailsDtos;
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

    public String getIs_E0xisting_Application() {
        return Is_E0xisting_Application;
    }

    public void setIs_E0xisting_Application(String is_E0xisting_Application) {
        Is_E0xisting_Application = is_E0xisting_Application;
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

    public String getWorkflow_instance_Id() {
        return workflow_instance_Id;
    }

    public void setWorkflow_instance_Id(String workflow_instance_Id) {
        this.workflow_instance_Id = workflow_instance_Id;
    }

    public String getTaskInstanceId() {
        return taskInstanceId;
    }

    public void setTaskInstanceId(String taskInstanceId) {
        this.taskInstanceId = taskInstanceId;
    }

    public String getElectrical_Person_Name() {
        return Electrical_Person_Name;
    }

    public void setElectrical_Person_Name(String electrical_Person_Name) {
        Electrical_Person_Name = electrical_Person_Name;
    }

    public String getDzongkhag_Name() {
        return Dzongkhag_Name;
    }

    public void setDzongkhag_Name(String dzongkhag_Name) {
        Dzongkhag_Name = dzongkhag_Name;
    }

    public String getGewog_Name() {
        return Gewog_Name;
    }

    public void setGewog_Name(String gewog_Name) {
        Gewog_Name = gewog_Name;
    }

    public String getVillage_Name() {
        return Village_Name;
    }

    public void setVillage_Name(String village_Name) {
        Village_Name = village_Name;
    }

    public String getUnit_Name() {
        return Unit_Name;
    }

    public void setUnit_Name(String unit_Name) {
        Unit_Name = unit_Name;
    }

    public String getIsArchitectVerificationReq() {
        return isArchitectVerificationReq;
    }

    public void setIsArchitectVerificationReq(String isArchitectVerificationReq) {
        this.isArchitectVerificationReq = isArchitectVerificationReq;
    }

    public String getIsStrucVerificationReq() {
        return isStrucVerificationReq;
    }

    public void setIsStrucVerificationReq(String isStrucVerificationReq) {
        this.isStrucVerificationReq = isStrucVerificationReq;
    }

    public String getIsElecVerificationReq() {
        return isElecVerificationReq;
    }

    public void setIsElecVerificationReq(String isElecVerificationReq) {
        this.isElecVerificationReq = isElecVerificationReq;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public BigInteger getRowCount() {
        return rowCount;
    }

    public void setRowCount(BigInteger rowCount) {
        this.rowCount = rowCount;
    }

    public int getNumber_of_Floor() {
        return number_of_Floor;
    }

    public void setNumber_of_Floor(int number_of_Floor) {
        this.number_of_Floor = number_of_Floor;
    }

    public String getReasonId() {
        return reasonId;
    }

    public void setReasonId(String reasonId) {
        this.reasonId = reasonId;
    }

    public String getReasonDesc() {
        return reasonDesc;
    }

    public void setReasonDesc(String reasonDesc) {
        this.reasonDesc = reasonDesc;
    }

    public String getBuildingUseString() {
        return buildingUseString;
    }

    public void setBuildingUseString(String buildingUseString) {
        this.buildingUseString = buildingUseString;
    }

    public String getConstructionTypeString() {
        return constructionTypeString;
    }

    public void setConstructionTypeString(String constructionTypeString) {
        this.constructionTypeString = constructionTypeString;
    }

    public String getArchitectRemarks() {
        return architectRemarks;
    }

    public void setArchitectRemarks(String architectRemarks) {
        this.architectRemarks = architectRemarks;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public int getProjectTitleId() {
        return projectTitleId;
    }

    public void setProjectTitleId(int projectTitleId) {
        this.projectTitleId = projectTitleId;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getStructural_Graduation_Year() {
        return structural_Graduation_Year;
    }

    public void setStructural_Graduation_Year(String structural_Graduation_Year) {
        this.structural_Graduation_Year = structural_Graduation_Year;
    }

    public String getElectrical_Graduation_Year() {
        return electrical_Graduation_Year;
    }

    public void setElectrical_Graduation_Year(String electrical_Graduation_Year) {
        this.electrical_Graduation_Year = electrical_Graduation_Year;
    }

    public String getArchitect_Contact_No() {
        return architect_Contact_No;
    }

    public void setArchitect_Contact_No(String architect_Contact_No) {
        this.architect_Contact_No = architect_Contact_No;
    }

    public String getDocument_path() {
        return document_path;
    }

    public void setDocument_path(String document_path) {
        this.document_path = document_path;
    }

    public String getDocument_id() {
        return document_id;
    }

    public void setDocument_id(String document_id) {
        this.document_id = document_id;
    }

    public String getDocument_Type() {
        return document_Type;
    }

    public void setDocument_Type(String document_Type) {
        this.document_Type = document_Type;
    }

    public String getDocument_Name() {
        return document_Name;
    }

    public void setDocument_Name(String document_Name) {
        this.document_Name = document_Name;
    }

    public MultipartFile getAppUploadFile() {
        return appUploadFile;
    }

    public void setAppUploadFile(MultipartFile appUploadFile) {
        this.appUploadFile = appUploadFile;
    }

    public MultipartFile getAppUploadFileDES() {
        return appUploadFileDES;
    }

    public void setAppUploadFileDES(MultipartFile appUploadFileDES) {
        this.appUploadFileDES = appUploadFileDES;
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

    public OwnerEntity[] getOwnerList() {
        return ownerList;
    }

    public void setOwnerList(OwnerEntity[] ownerList) {
        this.ownerList = ownerList;
    }

    public Date getCreated_on() {
        return Created_on;
    }

    public void setCreated_on(Date created_on) {
        Created_on = created_on;
    }

    public Date getModified_On() {
        return Modified_On;
    }

    public void setModified_On(Date modified_On) {
        Modified_On = modified_On;
    }

    public String getTask_Stage_Name() {
        return Task_Stage_Name;
    }

    public void setTask_Stage_Name(String task_Stage_Name) {
        Task_Stage_Name = task_Stage_Name;
    }

    public String getModified_By() {
        return Modified_By;
    }

    public void setModified_By(String modified_By) {
        Modified_By = modified_By;
    }

    public String getOwner_Name() {
        return Owner_Name;
    }

    public void setOwner_Name(String owner_Name) {
        Owner_Name = owner_Name;
    }

    public String getOwner_Contact_No() {
        return Owner_Contact_No;
    }

    public void setOwner_Contact_No(String owner_Contact_No) {
        Owner_Contact_No = owner_Contact_No;
    }

    public String getBuilding_Use_Name() {
        return Building_Use_Name;
    }

    public void setBuilding_Use_Name(String building_Use_Name) {
        Building_Use_Name = building_Use_Name;
    }

    public String getLocation_Id() {
        return location_Id;
    }

    public void setLocation_Id(String location_Id) {
        this.location_Id = location_Id;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public Date getForwarded_On() {
        return Forwarded_On;
    }

    public void setForwarded_On(Date forwarded_On) {
        Forwarded_On = forwarded_On;
    }

    public Date getClaimed_On() {
        return Claimed_On;
    }

    public void setClaimed_On(Date claimed_On) {
        Claimed_On = claimed_On;
    }

    public Date getApproved_On() {
        return Approved_On;
    }

    public void setApproved_On(Date approved_On) {
        Approved_On = approved_On;
    }

    public Date getRejcted_On() {
        return Rejcted_On;
    }

    public void setRejcted_On(Date rejcted_On) {
        Rejcted_On = rejcted_On;
    }

    public String getStringForwarded_On() {
        return stringForwarded_On;
    }

    public void setStringForwarded_On(String stringForwarded_On) {
        this.stringForwarded_On = stringForwarded_On;
    }

    public String getStringClaimed_On() {
        return stringClaimed_On;
    }

    public void setStringClaimed_On(String stringClaimed_On) {
        this.stringClaimed_On = stringClaimed_On;
    }

    public String getStringApproved_On() {
        return stringApproved_On;
    }

    public void setStringApproved_On(String stringApproved_On) {
        this.stringApproved_On = stringApproved_On;
    }

    public String getStringRejcted_On() {
        return stringRejcted_On;
    }

    public void setStringRejcted_On(String stringRejcted_On) {
        this.stringRejcted_On = stringRejcted_On;
    }

    public String getStringCreated_on() {
        return stringCreated_on;
    }

    public void setStringCreated_on(String stringCreated_on) {
        this.stringCreated_on = stringCreated_on;
    }

    public String getStringModified_On() {
        return stringModified_On;
    }

    public void setStringModified_On(String stringModified_On) {
        this.stringModified_On = stringModified_On;
    }

    public String getStringHold_On() {
        return stringHold_On;
    }

    public void setStringHold_On(String stringHold_On) {
        this.stringHold_On = stringHold_On;
    }

    public String getStringUnHold_On() {
        return stringUnHold_On;
    }

    public void setStringUnHold_On(String stringUnHold_On) {
        this.stringUnHold_On = stringUnHold_On;
    }

    public String getUnHoldReason() {
        return UnHoldReason;
    }

    public void setUnHoldReason(String unHoldReason) {
        UnHoldReason = unHoldReason;
    }

    public String getHoldReason() {
        return HoldReason;
    }

    public void setHoldReason(String holdReason) {
        HoldReason = holdReason;
    }

    public String getRejectReason() {
        return RejectReason;
    }

    public void setRejectReason(String rejectReason) {
        RejectReason = rejectReason;
    }

    public BigInteger getRowCountTotal() {
        return rowCountTotal;
    }

    public void setRowCountTotal(BigInteger rowCountTotal) {
        this.rowCountTotal = rowCountTotal;
    }

    public BigInteger getRowCountClaimed() {
        return rowCountClaimed;
    }

    public void setRowCountClaimed(BigInteger rowCountClaimed) {
        this.rowCountClaimed = rowCountClaimed;
    }

    public BigInteger getRowCountApproved() {
        return rowCountApproved;
    }

    public void setRowCountApproved(BigInteger rowCountApproved) {
        this.rowCountApproved = rowCountApproved;
    }

    public BigInteger getRowCountRejected() {
        return rowCountRejected;
    }

    public void setRowCountRejected(BigInteger rowCountRejected) {
        this.rowCountRejected = rowCountRejected;
    }

    public BigInteger getRowCountForwarded() {
        return rowCountForwarded;
    }

    public void setRowCountForwarded(BigInteger rowCountForwarded) {
        this.rowCountForwarded = rowCountForwarded;
    }

    public BigInteger getRowCountHold() {
        return rowCountHold;
    }

    public void setRowCountHold(BigInteger rowCountHold) {
        this.rowCountHold = rowCountHold;
    }

    public BigInteger getRowCountInitiated() {
        return rowCountInitiated;
    }

    public void setRowCountInitiated(BigInteger rowCountInitiated) {
        this.rowCountInitiated = rowCountInitiated;
    }

    public String getForwardReason() {
        return ForwardReason;
    }

    public void setForwardReason(String forwardReason) {
        ForwardReason = forwardReason;
    }

    public int getJuris_id() {
        return juris_id;
    }

    public void setJuris_id(int juris_id) {
        this.juris_id = juris_id;
    }

    public List<OwnerDetailsDto> getOwnerDetailsDtos() {
        return ownerDetailsDtos;
    }

    public void setOwnerDetailsDtos(List<OwnerDetailsDto> ownerDetailsDtos) {
        this.ownerDetailsDtos = ownerDetailsDtos;
    }
}
