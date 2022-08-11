package bt.gov.ditt.does.Dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Deepak on 1/24/2020.
 */
public class CitizenDtlsDTO implements Serializable {
    private String cid_Number;
    private String first_Name;
    private String middle_Name;
    private String last_Name;
    private String gender;
    private String dob;
    private Date date_of_birth;
    private String place_of_birth;
    private Integer dzongkhag_Serial_No;
    private String dzongkhag_Name;
    private Integer gewog_Serial_No;
    private String gewog_Name;
    private Integer village_Serial_No;
    private String village_Name;
    private String full_Name;


    private String father_Name;
    private String father_CID_No;
    private String mother_Name;
    private String mother_CID_No;
    private String spouse_Name;
    private String spouse_CID_No;
    private String marital_Status;
    private String nationality_Desc;
    private String occupation_Desc;
    private String age;

    public CitizenDtlsDTO() {
    }

    public CitizenDtlsDTO(String cid_Number, String first_Name, String middle_Name, String last_Name, String gender, String dob, Date date_of_birth, String place_of_birth, Integer dzongkhag_Serial_No, String dzongkhag_Name, Integer gewog_Serial_No, String gewog_Name, Integer village_Serial_No, String village_Name, String full_Name, String father_Name, String father_CID_No, String mother_Name, String mother_CID_No, String spouse_Name, String spouse_CID_No, String marital_Status, String nationality_Desc, String occupation_Desc, String age) {
        this.cid_Number = cid_Number;
        this.first_Name = first_Name;
        this.middle_Name = middle_Name;
        this.last_Name = last_Name;
        this.gender = gender;
        this.dob = dob;
        this.date_of_birth = date_of_birth;
        this.place_of_birth = place_of_birth;
        this.dzongkhag_Serial_No = dzongkhag_Serial_No;
        this.dzongkhag_Name = dzongkhag_Name;
        this.gewog_Serial_No = gewog_Serial_No;
        this.gewog_Name = gewog_Name;
        this.village_Serial_No = village_Serial_No;
        this.village_Name = village_Name;
        this.full_Name = full_Name;
        this.father_Name = father_Name;
        this.father_CID_No = father_CID_No;
        this.mother_Name = mother_Name;
        this.mother_CID_No = mother_CID_No;
        this.spouse_Name = spouse_Name;
        this.spouse_CID_No = spouse_CID_No;
        this.marital_Status = marital_Status;
        this.nationality_Desc = nationality_Desc;
        this.occupation_Desc = occupation_Desc;
        this.age = age;
    }

    public String getCid_Number() {
        return cid_Number;
    }

    public void setCid_Number(String cid_Number) {
        this.cid_Number = cid_Number;
    }

    public String getFirst_Name() {
        return first_Name;
    }

    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public String getMiddle_Name() {
        return middle_Name;
    }

    public void setMiddle_Name(String middle_Name) {
        this.middle_Name = middle_Name;
    }

    public String getLast_Name() {
        return last_Name;
    }

    public void setLast_Name(String last_Name) {
        this.last_Name = last_Name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getPlace_of_birth() {
        return place_of_birth;
    }

    public void setPlace_of_birth(String place_of_birth) {
        this.place_of_birth = place_of_birth;
    }

    public Integer getDzongkhag_Serial_No() {
        return dzongkhag_Serial_No;
    }

    public void setDzongkhag_Serial_No(Integer dzongkhag_Serial_No) {
        this.dzongkhag_Serial_No = dzongkhag_Serial_No;
    }

    public String getDzongkhag_Name() {
        return dzongkhag_Name;
    }

    public void setDzongkhag_Name(String dzongkhag_Name) {
        this.dzongkhag_Name = dzongkhag_Name;
    }

    public Integer getGewog_Serial_No() {
        return gewog_Serial_No;
    }

    public void setGewog_Serial_No(Integer gewog_Serial_No) {
        this.gewog_Serial_No = gewog_Serial_No;
    }

    public String getGewog_Name() {
        return gewog_Name;
    }

    public void setGewog_Name(String gewog_Name) {
        this.gewog_Name = gewog_Name;
    }

    public Integer getVillage_Serial_No() {
        return village_Serial_No;
    }

    public void setVillage_Serial_No(Integer village_Serial_No) {
        this.village_Serial_No = village_Serial_No;
    }

    public String getVillage_Name() {
        return village_Name;
    }

    public void setVillage_Name(String village_Name) {
        this.village_Name = village_Name;
    }

    public String getFull_Name() {
        return full_Name;
    }

    public void setFull_Name(String full_Name) {
        this.full_Name = full_Name;
    }

    public String getFather_Name() {
        return father_Name;
    }

    public void setFather_Name(String father_Name) {
        this.father_Name = father_Name;
    }

    public String getFather_CID_No() {
        return father_CID_No;
    }

    public void setFather_CID_No(String father_CID_No) {
        this.father_CID_No = father_CID_No;
    }

    public String getMother_Name() {
        return mother_Name;
    }

    public void setMother_Name(String mother_Name) {
        this.mother_Name = mother_Name;
    }

    public String getMother_CID_No() {
        return mother_CID_No;
    }

    public void setMother_CID_No(String mother_CID_No) {
        this.mother_CID_No = mother_CID_No;
    }

    public String getSpouse_Name() {
        return spouse_Name;
    }

    public void setSpouse_Name(String spouse_Name) {
        this.spouse_Name = spouse_Name;
    }

    public String getSpouse_CID_No() {
        return spouse_CID_No;
    }

    public void setSpouse_CID_No(String spouse_CID_No) {
        this.spouse_CID_No = spouse_CID_No;
    }

    public String getMarital_Status() {
        return marital_Status;
    }

    public void setMarital_Status(String marital_Status) {
        this.marital_Status = marital_Status;
    }

    public String getNationality_Desc() {
        return nationality_Desc;
    }

    public void setNationality_Desc(String nationality_Desc) {
        this.nationality_Desc = nationality_Desc;
    }

    public String getOccupation_Desc() {
        return occupation_Desc;
    }

    public void setOccupation_Desc(String occupation_Desc) {
        this.occupation_Desc = occupation_Desc;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
