package bt.gov.ditt.does.Dto;

/**
 * Created by Pema Drakpa on 5/25/2020.
 */
public class UserRolePrivilegeDto {

    private String user_name;
    private String user_role;
    private int juris_id;
    private int juris_type;

    public UserRolePrivilegeDto() {
    }

    public UserRolePrivilegeDto(String user_name, String user_role, int juris_id, int juris_type) {
        this.user_name = user_name;
        this.user_role = user_role;
        this.juris_id = juris_id;
        this.juris_type = juris_type;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
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
}
