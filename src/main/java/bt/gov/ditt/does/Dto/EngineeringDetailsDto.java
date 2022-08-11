package bt.gov.ditt.does.Dto;

/**
 * Created by Tandin on 9/18/2020.
 */
public class EngineeringDetailsDto {
    private String Employee_ID;
    private String Name;

    public EngineeringDetailsDto() {
    }

    public EngineeringDetailsDto(String employee_ID, String name) {
        Employee_ID = employee_ID;
        Name = name;
    }

    public String getEmployee_ID() {
        return Employee_ID;
    }

    public void setEmployee_ID(String employee_ID) {
        Employee_ID = employee_ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
