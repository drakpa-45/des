package bt.gov.ditt.does.Dto;

import java.io.Serializable;

/**
 * Created by Tandin on 9/17/2020.
 */
public class ArchitectDetailsDto implements Serializable {
    private String architectRegNo;
    private String fullName;

    public ArchitectDetailsDto() {
    }

    public ArchitectDetailsDto(String architectRegNo, String fullName) {
        this.architectRegNo = architectRegNo;
        this.fullName = fullName;
    }

    public String getArchitectRegNo() {
        return architectRegNo;
    }

    public void setArchitectRegNo(String architectRegNo) {
        this.architectRegNo = architectRegNo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
