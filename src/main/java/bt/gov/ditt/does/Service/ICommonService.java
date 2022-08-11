package bt.gov.ditt.does.Service;

import bt.gov.ditt.does.Dto.*;
import bt.gov.ditt.does.Entity.OwnerEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by Pema Drakpa on 6/8/2020.
 */
public interface ICommonService {
    public  UserRolePrivilegeDto populateUserRolePrivilegeHierarchy(String username, String pass);

    public  List<DropDownDto> getDropdown(String dropdownType, String param1, String param2);

    public String saveApplication(String type, BuildingDto dto, HttpServletRequest request);

    public List<TasklistDto> getGroupTask(String user_role, int juris_type, int juris_id);

    public List<TasklistDto> getMyTask(String user_role, int juris_type, int juris_id);

    public String claimTasklistApplication(String applicationNo, String taskId, String user_name);

    public String unClaimTasklistApplication(String applicationNo, String taskId, String user_name);

    public Object openTasklistApplication(String applicationNo, String taskId, String detailsType);

    public List<OwnerEntity> getApplicationOwnerList(String applicationNo);

    public String verfication(BuildingDto dto, String verifierType, HttpServletRequest request);

    public String rejectApplication(BuildingDto dto, HttpServletRequest request, String type);

    public List<TasklistDto> applicationStatus(String applicationNo);

    public DropDownDto getCitizenDetails(String cidNo);

    public ApplicationDocDto getDocumentDetailsByDocId(String uploadDocId);

    public List<BuildingDto> getReport(String usr_role, String from_date, String to_date);

    public List<BuildingDto> getDocuments(String applicationNo);

    public Object getTaskStage(String applicationNo, String taskId, String status);

    public Object getHoldStatus(String applicationNo, String taskId, String holdstatus);

    public String unHoldTasklistApplication(String applicationNo, String taskId, String user_name);

    public List<BuildingDto> countApplications(String locationID, String countapplication);

    public DropDownDto getDzongkhagByUserLocation(String locationID);
}
