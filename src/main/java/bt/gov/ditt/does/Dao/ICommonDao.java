package bt.gov.ditt.does.Dao;

import bt.gov.ditt.does.Dto.*;
import bt.gov.ditt.does.Entity.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Pema Drakpa on 6/8/2020.
 */

public interface ICommonDao {
    UserRolePrivilegeDto populateUserRolePrivilegeHierarchy(String username, String pass);

    Object getDropdown(String dropdownType, String param1, String param2);

    String saveWorkflow(WorkflowEntity workFlowEntity);

    String savebuildingDetails(BuildingEntity buildingEntity);

    String saveTasklist(TasklistEntity taskDetailsEntity);

    String generateApplicationNumber(BuildingDto dto);

    String generateInstanceId(String nextTask);

    String saveOwnerDetails(OwnerEntity ownerList);

    List<TasklistDto> getGroupTasklist(String user_role, int juris_type, int juris_id);

    List<TasklistDto> getMyTasklist(String user_role, int juris_type, int juris_id);

    String claimTasklistApplication(String applicationNo, String taskId, String user_name);

    String unClaimTasklistApplication(String applicationNo, String taskId, String user_name);

    Object openTasklistApplication(String applicationNo, String taskId, String detailsType);

    List<OwnerEntity> getApplicationOwnerList(String applicationNo);

    int updateTaskList(TasklistEntity taskDetailsEntity);

    List<TasklistDto> applicationStatus(String applicationNo);

    String uploadSupportingDocument(ApplicationDocEntity applicationDocEntity);
    public ApplicationDocDto getDocumentDetailsByDocId(String uploadDocId);

    int countVerifier(String application_id, int juris_type);

    int checkDesArchitect(String application_id);

    List<BuildingDto> getReport(String usr_role, String from_date, String to_date);

    int countCheck(String application_id, int juris_type);

    public List<BuildingDto> getDocument(String applicationNo);

    int countAll(String application_id, String des_notesheet);

    public Object getTaskStage(String applicationNo, String taskId, String status);

    public Object getHoldStatus(String applicationNo, String taskId, String holdstatus);

    public String unHoldTasklistApplication(String applicationNo, String taskId, String user_name);

    List<BuildingDto> countApplications(String locationID, String countapplication);

    int getLocationId(String application_id);

    public DropDownDto getDzongkhagByUserLocation(String locationID);
}
