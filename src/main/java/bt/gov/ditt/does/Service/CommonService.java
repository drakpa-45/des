package bt.gov.ditt.does.Service;

import bt.gov.ditt.does.Controller.CommonNotificationUtil;
import bt.gov.ditt.does.Dao.CommonDao;
import bt.gov.ditt.does.Dao.ICommonDao;
import bt.gov.ditt.does.Dto.*;
import bt.gov.ditt.does.Entity.*;
import com.squareup.okhttp.OkHttpClient;
import org.codehaus.groovy.runtime.powerassert.SourceText;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.wso2.client.api.DCRC_CitizenDetailsAPI.DefaultApi;
import org.wso2.client.model.DCRC_CitizenDetailsAPI.CitizenDetailsResponse;
import org.wso2.client.model.DCRC_CitizenDetailsAPI.CitizendetailsObj;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class CommonService implements ICommonService
{
    @Autowired
    ICommonDao commonDao;

    @Override
    @Transactional
    public UserRolePrivilegeDto populateUserRolePrivilegeHierarchy(String username, String pass)
    {
        return commonDao.populateUserRolePrivilegeHierarchy(username,pass);
    }

    @Override
    @Transactional
    public List<DropDownDto> getDropdown(String dropdownType, String param1, String param2)
    {
        List<DropDownDto> dropdownList = new ArrayList<DropDownDto>();
        try
        {
             dropdownList = (List<DropDownDto>) commonDao.getDropdown(dropdownType, param1, param2);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return dropdownList;
    }

    @Override
    @Transactional
    public String saveApplication(String type, BuildingDto dto, HttpServletRequest request)
    {
        ResponseMessage responseMessage = new ResponseMessage();
        String status = "Failed";
        try
        {
                String applicationNo = commonDao.generateApplicationNumber(dto);
                dto.setApplication_Id(applicationNo);
                HttpSession sess = request.getSession();
                UserRolePrivilegeDES userdet = (UserRolePrivilegeDES) sess.getAttribute("UserRolePrivilege");
            System.out.println("------------ usename -----" +userdet.getFullName());
            System.out.println("------------ mobile no  -----" +userdet.getMobileNo());
            System.out.println("------------ telephone no -----" +userdet.getTelephoneNo());
                WorkflowDto workflow = new WorkflowDto();
                TasklistDto tasklist = new TasklistDto();
                BuildingEntity buildingEntity = new BuildingEntity();

                workflow.setServiceId(Integer.parseInt("821"));
                workflow.setRemarks(dto.getAppl_comments());
                WorkflowEntity workFlowEntity = new WorkflowEntity();
                workFlowEntity.setApplicationId(dto.getApplication_Id());
                workFlowEntity.setApplicationStatus("Application Submitted");
                workflow.setTaskInstanceId(commonDao.generateInstanceId("nextTask"));
                workFlowEntity.setWorkFlowInstanceId(commonDao.generateInstanceId("workflow"));
                status = commonDao.saveWorkflow(workFlowEntity);

                if(status.equalsIgnoreCase("Success"))
                {
                    TasklistEntity taskDetailsEntity = new TasklistEntity();
                    taskDetailsEntity.setJuris_id(Integer.parseInt(userdet.getJurisdictions()[0].getLocationID()));
                    taskDetailsEntity.setJuris_type(Integer.parseInt(userdet.getJurisdictions()[0].getJurisdiction()));
                    if (dto.getIs_Electrical_Forwarded()==null || dto.getIs_Electrical_Forwarded()=="") {
                      dto.setIs_Electrical_Forwarded("N");
                    }
                    if (dto.getIs_Architect_Forwarded()==null || dto.getIs_Architect_Forwarded()=="") {
                        dto.setIs_Architect_Forwarded("N");
                    }
                    if (dto.getIs_Structural_Forwarded()==null || dto.getIs_Structural_Forwarded()=="") {
                        dto.setIs_Structural_Forwarded("N");
                    }
                    taskDetailsEntity.setIs_Electrical_Forwarded(dto.getIs_Electrical_Forwarded());
                    taskDetailsEntity.setIs_Architect_Forwarded(dto.getIs_Architect_Forwarded());
                    taskDetailsEntity.setIs_Structural_Forwarded(dto.getIs_Structural_Forwarded());
                System.out.print("-------juri id---"+userdet.getJurisdictions()[0].getJurisdiction() + "--------type------"+type);
                   /* if(userdet.getJurisdictions()[0].getJurisdiction().equalsIgnoreCase("01") && type.equalsIgnoreCase("SAVE"))
                    {
                        taskDetailsEntity.setTaskId("1");
                        taskDetailsEntity.setApplicationStatus("Application Submitted");
                      //  taskDetailsEntity.setApplicationNo(dto.getApplication_Id());
                        taskDetailsEntity.setApplicationNo(applicationNo);
                        taskDetailsEntity.setTaskInstanceId(workflow.getTaskInstanceId());
                        taskDetailsEntity.setCurrentTaskStageId("TS001");
                        taskDetailsEntity.setCreatedBy(userdet.getFullName());
                        taskDetailsEntity.setModifiedBy(userdet.getFullName());
                        taskDetailsEntity.setCreatedOn(new Date());
                        taskDetailsEntity.setModifiedOn(new Date());
                        taskDetailsEntity.setWorkflowInstanceId(workflow.getWorkFlowInstanceId());
                        status = commonDao.saveTasklist(taskDetailsEntity);
                    }*/

                 //   if(userdet.getJurisdictions()[0].getJurisdiction().equalsIgnoreCase("01") && type.equalsIgnoreCase("SAVE")) {
                    if(type.equalsIgnoreCase("SAVE")) {
                        taskDetailsEntity.setTaskId("15");
                        taskDetailsEntity.setApplicationStatus("Dzongkhag Architect Verification Required");
                      //  taskDetailsEntity.setApplicationNo(dto.getApplication_Id());
                        taskDetailsEntity.setApplicationNo(applicationNo);
                        taskDetailsEntity.setTaskInstanceId(workflow.getTaskInstanceId());
                        taskDetailsEntity.setCurrentTaskStageId("TS001");
                        taskDetailsEntity.setCreatedBy(userdet.getFullName());
                        taskDetailsEntity.setModifiedBy(userdet.getFullName());
                        taskDetailsEntity.setCreatedOn(new Date());
                        taskDetailsEntity.setModifiedOn(new Date());
                        taskDetailsEntity.setWorkflowInstanceId(workflow.getWorkFlowInstanceId());
                        taskDetailsEntity.setRemarks(dto.getAppl_comments());
                        status = commonDao.saveTasklist(taskDetailsEntity);
                    }
                    if(userdet.getJurisdictions()[0].getJurisdiction().equalsIgnoreCase("14") && type.equalsIgnoreCase("SAVE")) {
                        taskDetailsEntity.setTaskId("1");
                        taskDetailsEntity.setApplicationStatus("Application Submitted");
                        taskDetailsEntity.setApplicationNo(dto.getApplication_Id());
                        taskDetailsEntity.setTaskInstanceId(workflow.getTaskInstanceId());
                        taskDetailsEntity.setCurrentTaskStageId("TS001");
                        taskDetailsEntity.setCreatedBy(userdet.getFullName());
                        taskDetailsEntity.setModifiedBy(userdet.getFullName());
                        taskDetailsEntity.setCreatedOn(new Date());
                        taskDetailsEntity.setModifiedOn(new Date());
                        taskDetailsEntity.setWorkflowInstanceId(workflow.getWorkFlowInstanceId());
                        status = commonDao.saveTasklist(taskDetailsEntity);
                    }

                    BeanUtils.copyProperties(dto,buildingEntity);
                    buildingEntity.setProject_Title_Id(dto.getProjectTitleId());
                    buildingEntity.setIs_Structural_Forwarded(dto.getIs_Structural_Forwarded());
                    buildingEntity.setIs_Electrical_Forwarded(dto.getIs_Electrical_Forwarded());
                    buildingEntity.setIs_Architect_Forwarded(dto.getIs_Architect_Forwarded());
                    buildingEntity.setJuris_type(Integer.parseInt(userdet.getJurisdictions()[0].getJurisdiction()));
                    buildingEntity.setJuris_id(Integer.parseInt(userdet.getJurisdictions()[0].getLocationID()));
                    System.out.println("-------- before savebuildingDetails------------");
                    status = commonDao.savebuildingDetails(buildingEntity);
                    System.out.println("-------- after savebuildingDetails------------");
                    if(type.equalsIgnoreCase("OPERATOR_FORWARD_TO_DES")){
                        ApplicationDocDto applicationDocDto = new ApplicationDocDto();
                        MultipartFile supportingFile2 = dto.getAppUploadFile();
                        System.out.println("-------- file name from dto------------"+ dto.getAppUploadFile().getOriginalFilename());
                        if (!supportingFile2.isEmpty()) {
                            String fileName = supportingFile2.getOriginalFilename();
                            String supportingFileExt = fileName.substring(fileName.lastIndexOf("."));
                            applicationDocDto.setDocument_name(fileName);
                            responseMessage = FileUploadToExternalLocation.fileUploader(supportingFile2, fileName, "attachFile.properties", request);
                            FileUploadDTO fileUploadDTO = FileUploadToExternalLocation.fileUploadPathRetriever(request);
                            String uploadedDirectory = fileUploadDTO.getUploadFilePath().concat(fileName);
                            applicationDocDto.setDocument_path(uploadedDirectory);
                            System.out.println("-----------app no------"+applicationNo);
                            applicationDocDto.setApplication_id(applicationNo);
                            applicationDocDto.setDocument_id(String.valueOf(UUID.randomUUID()));
                            String taskInstanceId = taskDetailsEntity.getTaskInstanceId();
                            ApplicationDocEntity applicationDocEntity = convertUploadDtoToUploadEntity(applicationDocDto, "Attached Files",
                                    taskDetailsEntity.getApplicationNo(), taskInstanceId);
                            applicationDocEntity.setApplication_id(applicationNo);
                            // To save supporting document
                            commonDao.uploadSupportingDocument(applicationDocEntity);
                        }
                    }

                if(status.equalsIgnoreCase("Success")) {
                    for(OwnerEntity ownerList : dto.getOwnerList()) {
                        OwnerEntity ownerDtls = new OwnerEntity();
                        ownerList.setApplication_Id(applicationNo);
                        BeanUtils.copyProperties(ownerList,ownerDtls);
                        status=commonDao.saveOwnerDetails(ownerDtls);

                        if(status.equalsIgnoreCase("Success")) {
                            ArrayList<String> contactNo=new ArrayList<String>();
                            contactNo.add(ownerDtls.getOwner_Mobile_No());
                            String phoneNo = userdet.getMobileNo();
                            String officeTel = userdet.getTelephoneNo();
                            System.out.println("------------- office telephone number --------"+officeTel);
                            String[] MobileNo=contactNo.toArray(new String[contactNo.size()]);

                            //Added Reason for Rejection
                            /*boolean isSend = CommonNotificationUtil.notifyOnSubmission(dto.getApplication_Id(), MobileNo);*/
                            boolean isSend = CommonNotificationUtil.notifyOnSubmission(dto.getApplication_Id(), MobileNo, phoneNo, officeTel);
                        }
                    }
                    status = applicationNo;
                }
            }
        } catch(Exception e) {
            System.out.print("Exception in CommonService # saveApplication: " + e);
        }

        return status;
    }

    private ApplicationDocEntity convertUploadDtoToUploadEntity(ApplicationDocDto applicationDocDto, String building_cons_appl_prepared_notesheet, String applicationNo, String taskInstanceId)
    {
        ApplicationDocEntity applicationDocEntity = new ApplicationDocEntity();
        System.out.printf("---------application number inside upload entity-------"+applicationNo);
        applicationDocEntity.setApplication_id(applicationNo);
        applicationDocEntity.setTask_instance_id(taskInstanceId);
        applicationDocEntity.setDocument_id(String.valueOf(UUID.randomUUID()));
        applicationDocEntity.setDocument_type(building_cons_appl_prepared_notesheet);
        applicationDocEntity.setDocument_name(applicationDocDto.getDocument_name());
        applicationDocEntity.setDocument_path(applicationDocDto.getDocument_path());
        applicationDocEntity.setCreated_on_date(new Date());
        applicationDocEntity.setIs_active("Y");
        return applicationDocEntity;
    }

    @Override
    @Transactional
    public List<TasklistDto> getGroupTask(String user_role, int juris_type, int juris_id) {
        List<TasklistDto> tasklist = new ArrayList<TasklistDto>();
        try {
            tasklist = commonDao.getGroupTasklist(user_role,juris_type,juris_id);
        } catch(Exception e) {
            System.out.print("Exception in CommonService # getGroupTask: " + e);

        }
        return tasklist;
    }

    @Override
    @Transactional
    public List<TasklistDto> getMyTask(String user_role, int juris_type, int juris_id) {
        List<TasklistDto> tasklist = new ArrayList<TasklistDto>();
        try {
            tasklist = commonDao.getMyTasklist(user_role,juris_type,juris_id);

        }
        catch(Exception e) {
            System.out.print("Exception in CommonService # getGroupTask: " + e);

        }
        return tasklist;
    }

    @Override
    @Transactional
    public String claimTasklistApplication(String applicationNo, String taskId, String user_name) {
       String result = "Failed";
        try {
            result = commonDao.claimTasklistApplication(applicationNo, taskId,user_name);
        } catch(Exception e) {
            System.out.print("Exception in claimTasklistApplication # getGroupTask: " + e);

        }
        return result;
    }

    @Override
    @Transactional
    public String unClaimTasklistApplication(String applicationNo, String taskId, String user_role) {
        String result = "Failed";
        try {
            result = commonDao.unClaimTasklistApplication(applicationNo, taskId, user_role);
        } catch(Exception e) {
            System.out.print("Exception in unClaimTasklistApplication # getMyTask: " + e);
        }
        return result;
    }


    @Override
    @Transactional
    public Object openTasklistApplication(String applicationNo, String taskId, String detailsType)
    {
        return commonDao.openTasklistApplication(applicationNo, taskId, detailsType);

    }

    @Override
    @Transactional
    public List<OwnerEntity> getApplicationOwnerList(String applicationNo)
    {
        return commonDao.getApplicationOwnerList(applicationNo);
    }

    @Override
    @Transactional
    public String verfication(BuildingDto dto, String verifierType, HttpServletRequest request)
    {
        String status = "Failed";
        try{
            HttpSession sess = request.getSession();
            UserRolePrivilegeDES userdet = (UserRolePrivilegeDES) sess.getAttribute("UserRolePrivilege");
            TasklistEntity taskDetailsEntity = new TasklistEntity();

            if (dto.getIs_Electrical_Forwarded() == null || dto.getIs_Electrical_Forwarded() == "") {
                dto.setIs_Electrical_Forwarded("N");
            }
            if (dto.getIs_Structural_Forwarded() == null || dto.getIs_Structural_Forwarded() == "") {
                dto.setIs_Structural_Forwarded("N");
            }
            if (dto.getIs_Architect_Forwarded() == null || dto.getIs_Architect_Forwarded() == "") {
                dto.setIs_Architect_Forwarded("N");
            }
            taskDetailsEntity.setIs_Architect_Forwarded(dto.getIs_Architect_Forwarded());
            taskDetailsEntity.setIs_Structural_Forwarded(dto.getIs_Structural_Forwarded());
            taskDetailsEntity.setIs_Electrical_Forwarded(dto.getIs_Electrical_Forwarded());


            if (verifierType.equalsIgnoreCase("STRUCTURAL_FORWARD")) {
                taskDetailsEntity.setApplicationNo(dto.getApplication_Id());
                taskDetailsEntity.setTaskInstanceId(dto.getTaskInstanceId());
                taskDetailsEntity.setCurrentTaskStageId("TS001");
                taskDetailsEntity.setCreatedBy(userdet.getFullName());
                taskDetailsEntity.setModifiedBy(userdet.getFullName());
                taskDetailsEntity.setCreatedOn(new Date());
                taskDetailsEntity.setModifiedOn(new Date());
                taskDetailsEntity.setRemarks(dto.getAppl_comments());
                taskDetailsEntity.setTaskInstanceId(commonDao.generateInstanceId("nextTask"));
                if (dto.getIs_Structural_Forwarded() != null && dto.getIs_Structural_Forwarded().equalsIgnoreCase("Y")) {
                    taskDetailsEntity.setJuris_type(Integer.parseInt(userdet.getJurisdictions()[0].getJurisdiction()));
                    taskDetailsEntity.setJuris_id(Integer.parseInt(userdet.getJurisdictions()[0].getLocationID()));
                    taskDetailsEntity.setApplicationStatus("DES Structural Verification Required");
                    taskDetailsEntity.setTaskId("18");
                } else {
                    String jurisType="1";
                    int juris_id=commonDao.getLocationId(dto.getApplication_Id());
                    taskDetailsEntity.setJuris_id(juris_id);
                    //taskDetailsEntity.setJuris_type(Integer.parseInt(jurisType));
                    taskDetailsEntity.setJuris_type(juris_id);
                    taskDetailsEntity.setApplicationStatus("Dzongkhag Structural Verification Required");
                    taskDetailsEntity.setTaskId("4");
                }
                status = commonDao.saveTasklist(taskDetailsEntity);
            } else if (verifierType.equalsIgnoreCase("ELECTRICAL_FORWARD")) {
                    taskDetailsEntity.setApplicationNo(dto.getApplication_Id());
                    taskDetailsEntity.setTaskInstanceId(dto.getTaskInstanceId());
                    taskDetailsEntity.setCurrentTaskStageId("TS001");
                    taskDetailsEntity.setCreatedBy(userdet.getFullName());
                    taskDetailsEntity.setModifiedBy(userdet.getFullName());
                    taskDetailsEntity.setCreatedOn(new Date());
                    taskDetailsEntity.setModifiedOn(new Date());
                    taskDetailsEntity.setRemarks(dto.getAppl_comments());
                    taskDetailsEntity.setTaskInstanceId(commonDao.generateInstanceId("nextTask"));

                    if (dto.getIs_Electrical_Forwarded() != null && dto.getIs_Electrical_Forwarded().equalsIgnoreCase("Y")) {
                        taskDetailsEntity.setJuris_id(Integer.parseInt(userdet.getJurisdictions()[0].getLocationID()));
                        taskDetailsEntity.setJuris_type(Integer.parseInt(userdet.getJurisdictions()[0].getJurisdiction()));
                        taskDetailsEntity.setApplicationStatus("DES Electrical Verification Required");
                        taskDetailsEntity.setTaskId("19");
                    } else {
                        String jurisType="1";
                        int juris_id=commonDao.getLocationId(dto.getApplication_Id());
                        taskDetailsEntity.setJuris_id(juris_id);
                       // taskDetailsEntity.setJuris_type(Integer.parseInt(jurisType));
                        taskDetailsEntity.setJuris_type(juris_id);
                        taskDetailsEntity.setApplicationStatus("Dzongkhag Electrical Verification Required");
                        taskDetailsEntity.setTaskId("3");
                    }
                    status = commonDao.saveTasklist(taskDetailsEntity);

                } else {
                    if (dto.getTaskId() != null && dto.getTaskId().equals("17")) {
                        taskDetailsEntity.setIs_Architect_Forwarded(dto.getIs_Architect_Forwarded());
                        if (dto.getIs_Structural_Forwarded() != null && dto.getIs_Structural_Forwarded().equalsIgnoreCase("N")) {
                            TasklistEntity taskEntity1 = new TasklistEntity();
                            String jurisType="1";
                            int juris_id=commonDao.getLocationId(dto.getApplication_Id());
                            taskEntity1.setJuris_id(juris_id);
                            taskEntity1.setJuris_type(Integer.parseInt(jurisType));
                            taskEntity1.setTaskId("4");
                            taskEntity1.setApplicationNo(dto.getApplication_Id());
                            taskEntity1.setTaskInstanceId(dto.getTaskInstanceId());
                            taskEntity1.setCurrentTaskStageId("TS001");
                            taskEntity1.setCreatedBy(userdet.getFullName());
                            taskEntity1.setModifiedBy(userdet.getFullName());
                            taskEntity1.setCreatedOn(new Date());
                            taskEntity1.setModifiedOn(new Date());
                            taskEntity1.setRemarks(dto.getAppl_comments());
                            taskEntity1.setTaskInstanceId(commonDao.generateInstanceId("nextTask"));
                            taskEntity1.setApplicationStatus("Dzongkhag Structural Verification Required");
                            status = commonDao.saveTasklist(taskEntity1);
                        }
                        if (dto.getIs_Electrical_Forwarded() != null && dto.getIs_Electrical_Forwarded().equalsIgnoreCase("N")) {
                            TasklistEntity taskEntity2 = new TasklistEntity();
                            String jurisType="1";
                            int juris_id=commonDao.getLocationId(dto.getApplication_Id());
                            taskEntity2.setJuris_id(juris_id);
                            taskEntity2.setJuris_type(Integer.parseInt(jurisType));
                            taskEntity2.setTaskId("3");
                            taskEntity2.setApplicationNo(dto.getApplication_Id());
                            taskEntity2.setTaskInstanceId(dto.getTaskInstanceId());
                            taskEntity2.setCurrentTaskStageId("TS001");
                            taskEntity2.setCreatedBy(userdet.getFullName());
                            taskEntity2.setModifiedBy(userdet.getFullName());
                            taskEntity2.setCreatedOn(new Date());
                            taskEntity2.setModifiedOn(new Date());
                            taskEntity2.setRemarks(dto.getAppl_comments());
                            taskEntity2.setApplicationStatus("Dzongkhag Electrical Verification Required");
                            taskEntity2.setTaskInstanceId(commonDao.generateInstanceId("nextTask"));
                            status = commonDao.saveTasklist(taskEntity2);

                        }
                    }

                    taskDetailsEntity.setApplicationNo(dto.getApplication_Id());
                    taskDetailsEntity.setCreatedBy(userdet.getFullName());
                    taskDetailsEntity.setModifiedBy(userdet.getFullName());
                    taskDetailsEntity.setCreatedOn(new Date());
                    taskDetailsEntity.setModifiedOn(new Date());
                    taskDetailsEntity.setTaskInstanceId(dto.getTaskInstanceId());
                    taskDetailsEntity.setRemarks(dto.getAppl_comments());


                    if (userdet.getCurrentRole().getRoleName().equalsIgnoreCase("DOES Operater")) {
                        taskDetailsEntity.setCurrentTaskStageId("TS003");
                    } else if (userdet.getCurrentRole().getRoleName().equalsIgnoreCase("DOES Architect")
                            || userdet.getCurrentRole().getRoleName().equalsIgnoreCase("DOES Structural Engineer")
                            || userdet.getCurrentRole().getRoleName().equalsIgnoreCase("DOES Electrical Engineer"))
                    {
                        taskDetailsEntity.setCurrentTaskStageId("TS003");
                        taskDetailsEntity.setIs_Architect_Forwarded(dto.getIs_Architect_Forwarded());
                        taskDetailsEntity.setIs_Electrical_Forwarded(dto.getIs_Electrical_Forwarded());
                        taskDetailsEntity.setIs_Structural_Forwarded(dto.getIs_Structural_Forwarded());
                    } else {
                        taskDetailsEntity.setCurrentTaskStageId("TS003");
                            for (OwnerDetailsDto ownerList : dto.getOwnerDetailsDtos()) {
                                ownerList.setApplication_Id(dto.getApplication_Id());
                                ownerList.setOwnerContact(ownerList.getOwnerContact());
                                if (dto.getTaskId().equals("3")) {
                                    String phoneNo = userdet.getMobileNo();
                                    String officeTel = userdet.getTelephoneNo();
                                    String User_Role = "Electrical Engineering Department";
                                    ArrayList<String> contactNo = new ArrayList<String>();
                                    contactNo.add(ownerList.getOwnerContact());
                                    String[] MobileNo = contactNo.toArray(new String[contactNo.size()]);
                                    //Added Reason for HOLD
                                    boolean isSend = CommonNotificationUtil.notifyOnPowerClearance(dto.getApplication_Id(), MobileNo, phoneNo, officeTel, User_Role);
                                }
                            }
                    }
                    taskDetailsEntity.setJuris_id(Integer.parseInt(userdet.getJurisdictions()[0].getLocationID()));
                    taskDetailsEntity.setJuris_type(Integer.parseInt(userdet.getJurisdictions()[0].getJurisdiction()));
                    commonDao.updateTaskList(taskDetailsEntity);

                    if (dto.getTaskId().equals("3") || dto.getTaskId().equals("4") || dto.getTaskId().equals("18") || dto.getTaskId().equals("17")
                            || dto.getTaskId().equals("19") || dto.getTaskId().equals("16") || dto.getTaskId().equals("22") || dto.getTaskId().equals("23")
                            || dto.getTaskId().equals("15") || dto.getTaskId().equals("25") || dto.getTaskId().equals("24")
                            || dto.getTaskId().equals("20"))
                    {

                        if (dto.getTaskId().equals("17") || dto.getTaskId().equals("18") || dto.getTaskId().equals("19")) {
                            int countVerifier = 0;
                            countVerifier = commonDao.countVerifier(dto.getApplication_Id(), Integer.parseInt(userdet.getJurisdictions()[0].getJurisdiction()));
                            if (countVerifier == 0 && userdet.getJurisdictions()[0].getJurisdiction().equalsIgnoreCase("20")) {
                                if (dto.getIs_Architect_Forwarded() != null && dto.getIs_Architect_Forwarded().equalsIgnoreCase("Y")
                                        && dto.getIs_Electrical_Forwarded() != null && dto.getIs_Electrical_Forwarded().equalsIgnoreCase("Y")
                                        && dto.getIs_Structural_Forwarded() != null && dto.getIs_Structural_Forwarded().equalsIgnoreCase("Y"))
                                {
                                    int checkDesArchitect = commonDao.checkDesArchitect(dto.getApplication_Id());
                                    if (checkDesArchitect > 0) {
                                        taskDetailsEntity.setTaskInstanceId(commonDao.generateInstanceId("nextTask"));
                                        taskDetailsEntity.setTaskId("20");
                                        taskDetailsEntity.setApplicationNo(dto.getApplication_Id());
                                        taskDetailsEntity.setCurrentTaskStageId("TS001");
                                        taskDetailsEntity.setRemarks(dto.getAppl_comments());
                                        taskDetailsEntity.setApplicationStatus("DES Architect Note sheet preparation");
                                        status = commonDao.saveTasklist(taskDetailsEntity);
                                    }
                                } else if (dto.getTaskId().equals("17") && dto.getIs_Architect_Forwarded().equalsIgnoreCase("Y")) {
                                    int checkDesArchitect = commonDao.checkDesArchitect(dto.getApplication_Id());
                                    if (checkDesArchitect > 0) {
                                        taskDetailsEntity.setTaskInstanceId(commonDao.generateInstanceId("nextTask"));
                                        taskDetailsEntity.setTaskId("20");
                                        taskDetailsEntity.setApplicationNo(dto.getApplication_Id());
                                        taskDetailsEntity.setCurrentTaskStageId("TS001");
                                        taskDetailsEntity.setRemarks(dto.getAppl_comments());
                                        taskDetailsEntity.setApplicationStatus("DES Architect Note sheet preparation");
                                        status = commonDao.saveTasklist(taskDetailsEntity);
                                    }

                                    if(status.equalsIgnoreCase("Success")) {
                                        for (OwnerDetailsDto ownerList : dto.getOwnerDetailsDtos()) {
                                            ownerList.setApplication_Id(dto.getApplication_Id());
                                            ownerList.setOwnerContact(ownerList.getOwnerContact());
                                            String phoneNo = userdet.getMobileNo();
                                            String officeTel = userdet.getTelephoneNo();
                                            String User_Role = "DES Architectural Department";
                                            ArrayList<String> contactNo = new ArrayList<String>();
                                            contactNo.add(ownerList.getOwnerContact());
                                            String[] MobileNo = contactNo.toArray(new String[contactNo.size()]);
                                            //Added Reason for HOLD
                                            boolean isSend = CommonNotificationUtil.notifyOnDESApprovalOfApplication(dto.getApplication_Id(), MobileNo, phoneNo, officeTel, User_Role);
                                        }

                                    }

                                } else if (dto.getTaskId().equals("19") && dto.getIs_Architect_Forwarded().equalsIgnoreCase("N")) {
                                    taskDetailsEntity.setTaskInstanceId(commonDao.generateInstanceId("nextTask"));
                                    taskDetailsEntity.setTaskId("24");
                                    taskDetailsEntity.setCurrentTaskStageId("TS001");
                                    taskDetailsEntity.setApplicationNo(dto.getApplication_Id());
                                    taskDetailsEntity.setRemarks(dto.getAppl_comments());
                                    taskDetailsEntity.setApplicationStatus("DES Electrical Note sheet preparation Required");
                                    status = commonDao.saveTasklist(taskDetailsEntity);

                                    if(status.equalsIgnoreCase("Success")) {
                                        for (OwnerDetailsDto ownerList : dto.getOwnerDetailsDtos()) {
                                            ownerList.setApplication_Id(dto.getApplication_Id());
                                            ownerList.setOwnerContact(ownerList.getOwnerContact());
                                            String phoneNo = userdet.getMobileNo();
                                            String officeTel = userdet.getTelephoneNo();
                                            String User_Role = "DES Electrical Engineering Department";
                                            ArrayList<String> contactNo = new ArrayList<String>();
                                            contactNo.add(ownerList.getOwnerContact());
                                            String[] MobileNo = contactNo.toArray(new String[contactNo.size()]);
                                            //Added Reason for HOLD
                                            boolean isSend = CommonNotificationUtil.notifyOnDESApproval(dto.getApplication_Id(), MobileNo, phoneNo, officeTel, User_Role);
                                        }
                                    }
                                } else if (dto.getTaskId().equals("18") && dto.getIs_Architect_Forwarded().equalsIgnoreCase("N")) {
                                    taskDetailsEntity.setTaskInstanceId(commonDao.generateInstanceId("nextTask"));
                                    taskDetailsEntity.setTaskId("25");
                                    taskDetailsEntity.setCurrentTaskStageId("TS001");
                                    taskDetailsEntity.setApplicationNo(dto.getApplication_Id());
                                    taskDetailsEntity.setRemarks(dto.getAppl_comments());
                                    taskDetailsEntity.setApplicationStatus("DES Structural Note sheet preparation Required");
                                    status = commonDao.saveTasklist(taskDetailsEntity);

                                    if(status.equalsIgnoreCase("Success")) {
                                        for (OwnerDetailsDto ownerList : dto.getOwnerDetailsDtos()) {
                                            ownerList.setApplication_Id(dto.getApplication_Id());
                                            ownerList.setOwnerContact(ownerList.getOwnerContact());
                                            String phoneNo = userdet.getMobileNo();
                                            String officeTel = userdet.getTelephoneNo();
                                            String User_Role = "DES Structural Engineering Department";
                                            ArrayList<String> contactNo = new ArrayList<String>();
                                            contactNo.add(ownerList.getOwnerContact());
                                            String[] MobileNo = contactNo.toArray(new String[contactNo.size()]);
                                            //Added Reason for HOLD
                                            boolean isSend = CommonNotificationUtil.notifyOnDESApproval(dto.getApplication_Id(), MobileNo, phoneNo, officeTel, User_Role);
                                        }

                                    }
                                }
                            }
                        } else if (dto.getTaskId().equals("20") || dto.getTaskId().equals("24") || dto.getTaskId().equals("25")) {
                            int countVerifier1 = commonDao.countCheck(dto.getApplication_Id(), Integer.parseInt(userdet.getJurisdictions()[0].getJurisdiction()));
                            if (countVerifier1 == 0) {
                                TasklistEntity taskEntityA = new TasklistEntity();
                                taskEntityA.setCreatedBy(userdet.getFullName());
                                taskEntityA.setModifiedBy(userdet.getFullName());
                                taskEntityA.setCreatedOn(new Date());
                                taskEntityA.setModifiedOn(new Date());
                                taskEntityA.setApplicationNo(taskDetailsEntity.getApplicationNo());
                                taskEntityA.setJuris_type(Integer.parseInt(userdet.getJurisdictions()[0].getJurisdiction()));
                                taskEntityA.setJuris_id(Integer.parseInt(userdet.getJurisdictions()[0].getLocationID()));
                                taskEntityA.setTaskId("21");
                                taskEntityA.setTaskInstanceId(commonDao.generateInstanceId("nextTask"));
                                taskEntityA.setCurrentTaskStageId("TS001");
                                taskEntityA.setRemarks(dto.getAppl_comments());
                                taskEntityA.setApplicationStatus("Note Sheet Prepared by DES");
                                status = commonDao.saveTasklist(taskEntityA);
                            }
                            ResponseMessage responseMessage = new ResponseMessage();
                            ApplicationDocDto applicationDocDto = new ApplicationDocDto();
                            MultipartFile supportingFile = dto.getAppUploadFile();
                            if (!supportingFile.isEmpty()) {
                                String fileName = supportingFile.getOriginalFilename();
                                String supportingFileExt = fileName.substring(fileName.lastIndexOf("."));
                                applicationDocDto.setDocument_name(fileName);
                                responseMessage = FileUploadToExternalLocation.fileUploader(supportingFile, fileName, "attachFile.properties", request);
                                FileUploadDTO fileUploadDTO = FileUploadToExternalLocation.fileUploadPathRetriever(request);
                                String uploadedDirectory = fileUploadDTO.getUploadFilePath().concat(fileName);
                                applicationDocDto.setDocument_path(uploadedDirectory);
                                applicationDocDto.setDocument_id(String.valueOf(UUID.randomUUID()));
                                String taskInstanceId = taskDetailsEntity.getTaskInstanceId();
                                ApplicationDocEntity applicationDocEntity = convertUploadDtoToUploadEntity(applicationDocDto, "Attached Files",
                                        taskDetailsEntity.getApplicationNo(), taskInstanceId);
                                commonDao.uploadSupportingDocument(applicationDocEntity);
                            }
                        } else if (dto.getTaskId().equals("15") || dto.getTaskId().equals("3") || dto.getTaskId().equals("4")) {
                            int countCheckDzoNotesheet = commonDao.countCheck(dto.getApplication_Id(), Integer.parseInt(userdet.getJurisdictions()[0].getJurisdiction()));
                            if (countCheckDzoNotesheet == 0) {
                                if (userdet.getCurrentRole().getRoleName().equalsIgnoreCase("DZO_ARCHITECT")) {
                                  //  taskDetailsEntity.setTaskId("5");
                                    taskDetailsEntity.setTaskId("28");
                                    taskDetailsEntity.setTaskInstanceId(commonDao.generateInstanceId("nextTask"));
                                    taskDetailsEntity.setCurrentTaskStageId("TS001");
                                    taskDetailsEntity.setApplicationNo(dto.getApplication_Id());
                                    taskDetailsEntity.setRemarks(dto.getAppl_comments());
                                    taskDetailsEntity.setApplicationStatus("Notesheet Preparation Required from Dzongkhag");
                                    status = commonDao.saveTasklist(taskDetailsEntity);

                                    if(status.equalsIgnoreCase("Success")) {
                                        for (OwnerDetailsDto ownerList : dto.getOwnerDetailsDtos()) {
                                            ownerList.setApplication_Id(dto.getApplication_Id());
                                            ownerList.setOwnerContact(ownerList.getOwnerContact());
                                            String phoneNo = userdet.getMobileNo();
                                            String officeTel = userdet.getTelephoneNo();
                                            String User_Role = "Dzongkhag Architectural Department";
                                            ArrayList<String> contactNo = new ArrayList<String>();
                                            contactNo.add(ownerList.getOwnerContact());
                                            String[] MobileNo = contactNo.toArray(new String[contactNo.size()]);
                                            //Added Reason for HOLD
                                           /* boolean isSend = CommonNotificationUtil.notifyOnAllDrawingsApprove(dto.getApplication_Id(), MobileNo);*/
                                            boolean isSend = CommonNotificationUtil.notifyOnAllDrawingsApprove(dto.getApplication_Id(), MobileNo, phoneNo, officeTel, User_Role);
                                        }
                                    }
                                } else if (userdet.getCurrentRole().getRoleName().equalsIgnoreCase("DZO_EE")) {
                                    taskDetailsEntity.setTaskId("5");
                                    taskDetailsEntity.setTaskInstanceId(commonDao.generateInstanceId("nextTask"));
                                    taskDetailsEntity.setCurrentTaskStageId("TS001");
                                    taskDetailsEntity.setRemarks(dto.getAppl_comments());
                                    taskDetailsEntity.setApplicationStatus("Notesheet Preparation Required from Dzongkhag");
                                    status = commonDao.saveTasklist(taskDetailsEntity);

                                    if(status.equalsIgnoreCase("Success")) {
                                        for (OwnerDetailsDto ownerList : dto.getOwnerDetailsDtos()) {
                                            ownerList.setApplication_Id(dto.getApplication_Id());
                                            ownerList.setOwnerContact(ownerList.getOwnerContact());
                                            String phoneNo = userdet.getMobileNo();
                                            String officeTel = userdet.getTelephoneNo();
                                            String User_Role = "Dzongkhag Electrical Engineering Department";
                                            ArrayList<String> contactNo = new ArrayList<String>();
                                            contactNo.add(ownerList.getOwnerContact());
                                            String[] MobileNo = contactNo.toArray(new String[contactNo.size()]);
                                            //Added Reason for HOLD
                                            boolean isSend = CommonNotificationUtil.notifyOnAllDrawingsApprove(dto.getApplication_Id(), MobileNo, phoneNo, officeTel, User_Role);
                                        }
                                    }
                                } else {
                                    taskDetailsEntity.setTaskId("5");
                                    taskDetailsEntity.setTaskInstanceId(commonDao.generateInstanceId("nextTask"));
                                    taskDetailsEntity.setCurrentTaskStageId("TS001");
                                    taskDetailsEntity.setRemarks(dto.getAppl_comments());
                                    taskDetailsEntity.setApplicationStatus("Notesheet Preparation Required from Dzongkhag");
                                    status = commonDao.saveTasklist(taskDetailsEntity);

                                    if(status.equalsIgnoreCase("Success")) {
                                        for (OwnerDetailsDto ownerList : dto.getOwnerDetailsDtos()) {
                                            ownerList.setApplication_Id(dto.getApplication_Id());
                                            ownerList.setOwnerContact(ownerList.getOwnerContact());
                                            String phoneNo = userdet.getMobileNo();
                                            String officeTel = userdet.getTelephoneNo();
                                            String User_Role = "Dzongkhag Structural Engineering Department";
                                            ArrayList<String> contactNo = new ArrayList<String>();
                                            contactNo.add(ownerList.getOwnerContact());
                                            String[] MobileNo = contactNo.toArray(new String[contactNo.size()]);
                                            //Added Reason for HOLD
                                            boolean isSend = CommonNotificationUtil.notifyOnAllDrawingsApprove(dto.getApplication_Id(), MobileNo, phoneNo, officeTel, User_Role);
                                        }
                                    }
                                }
                                int countCheckDesNotesheet = commonDao.countAll(dto.getApplication_Id(), "DES_NOTESHEET");
                                if (countCheckDesNotesheet >= 0) {
                                    if (dto.getIs_Architect_Forwarded().equalsIgnoreCase("Y") || dto.getIs_Structural_Forwarded().equalsIgnoreCase("Y")
                                            || dto.getIs_Electrical_Forwarded().equalsIgnoreCase("Y")) {
                                        TasklistEntity taskEntityA = new TasklistEntity();
                                        taskEntityA.setIs_Architect_Forwarded(dto.getIs_Architect_Forwarded());
                                        taskEntityA.setIs_Structural_Forwarded(dto.getIs_Structural_Forwarded());
                                        taskEntityA.setIs_Electrical_Forwarded(dto.getIs_Electrical_Forwarded());
                                        taskEntityA.setCreatedBy(userdet.getFullName());
                                        taskEntityA.setModifiedBy(userdet.getFullName());
                                        taskEntityA.setCreatedOn(new Date());
                                        taskEntityA.setModifiedOn(new Date());
                                        taskEntityA.setApplicationNo(taskDetailsEntity.getApplicationNo());
                                        taskEntityA.setTaskInstanceId(taskDetailsEntity.getTaskInstanceId());
                                        taskEntityA.setJuris_type(Integer.parseInt(userdet.getJurisdictions()[0].getJurisdiction()));
                                        taskEntityA.setJuris_id(Integer.parseInt(userdet.getJurisdictions()[0].getLocationID()));
                                        taskEntityA.setTaskId("21");
                                        taskEntityA.setTaskInstanceId(commonDao.generateInstanceId("nextTask"));
                                        taskEntityA.setCurrentTaskStageId("TS001");
                                        taskEntityA.setRemarks(dto.getAppl_comments());
                                        taskEntityA.setApplicationStatus("Note Sheet Prepared by DES");
                                        status = commonDao.saveTasklist(taskEntityA);
                                    }
                                }
                            }
                        }

                    }
                }
        } catch(Exception e) {
            System.out.print("Exception in CommonService # saveApplication: " + e);
            }
        return status;
    }

    @Override
    @Transactional
    public String rejectApplication(BuildingDto dto, HttpServletRequest request, String type)
    {
        HttpSession sess = request.getSession();
        UserRolePrivilegeDES userdet = (UserRolePrivilegeDES) sess.getAttribute("UserRolePrivilege");
        String status = "Failed";
        try
        {
            String taskStageId = "";
            TasklistEntity taskDetailsEntity = new TasklistEntity();
            if (dto.getIs_Electrical_Forwarded() == null || dto.getIs_Electrical_Forwarded() == "")
            {
                dto.setIs_Electrical_Forwarded("N");
            }
            if (dto.getIs_Architect_Forwarded() == null || dto.getIs_Architect_Forwarded() == "")
            {
                dto.setIs_Architect_Forwarded("N");
            }
            if (dto.getIs_Structural_Forwarded() == null || dto.getIs_Structural_Forwarded() == "")
            {

                dto.setIs_Structural_Forwarded("N");
            }
            taskDetailsEntity.setIs_Architect_Forwarded(dto.getIs_Architect_Forwarded());
            taskDetailsEntity.setIs_Electrical_Forwarded(dto.getIs_Electrical_Forwarded());
            taskDetailsEntity.setIs_Structural_Forwarded(dto.getIs_Structural_Forwarded());


            if(type.equalsIgnoreCase("HOLD")) {
                taskStageId = "TS005";

                    for (OwnerDetailsDto ownerList : dto.getOwnerDetailsDtos())
                    {
                        ownerList.setApplication_Id(dto.getApplication_Id());
                        ownerList.setOwnerContact(ownerList.getOwnerContact());

                        if (dto.getTaskId().equals("15"))
                        {
                            ArrayList<String> contactNo = new ArrayList<String>();
                            String phoneNo = userdet.getMobileNo();
                            String officeTel = userdet.getTelephoneNo();
                            String User_Role = "Dzongkhag Architectural Department";
                            contactNo.add(ownerList.getOwnerContact());
                            String[] MobileNo = contactNo.toArray(new String[contactNo.size()]);
                            //Added Reason for HOLD
                            boolean isSend = CommonNotificationUtil.notifyOnArchitectHold(dto.getApplication_Id(), MobileNo, phoneNo, officeTel, User_Role);

                    } else if (dto.getTaskId().equals("3")) {
                        ArrayList<String> contactNo = new ArrayList<String>();
                            String phoneNo = userdet.getMobileNo();
                            String officeTel = userdet.getTelephoneNo();
                            String User_Role = "Dzongkhag Electrical Engineering Department";
                            contactNo.add(ownerList.getOwnerContact());
                        String[] MobileNo = contactNo.toArray(new String[contactNo.size()]);

                        //Added Reason for HOLD
                        boolean isSend = CommonNotificationUtil.notifyOnElectricalHold(dto.getApplication_Id(), MobileNo, phoneNo, officeTel, User_Role);


                    } else if (dto.getTaskId().equals("4")) {
                        ArrayList<String> contactNo = new ArrayList<String>();
                            String phoneNo = userdet.getMobileNo();
                            String officeTel = userdet.getTelephoneNo();
                            String User_Role = "Dzongkhag Structural Engineering Department";
                            contactNo.add(ownerList.getOwnerContact());
                        String[] MobileNo = contactNo.toArray(new String[contactNo.size()]);

                        //Added Reason for HOLD
                        boolean isSend = CommonNotificationUtil.notifyOnStructuralHold(dto.getApplication_Id(), MobileNo, phoneNo, officeTel, User_Role);

                    } else if (dto.getTaskId().equals("17")) {
                        ArrayList<String> contactNo = new ArrayList<String>();
                            String phoneNo = userdet.getMobileNo();
                            String officeTel = userdet.getTelephoneNo();
                            String User_Role = "DES Architectural Department";
                            contactNo.add(ownerList.getOwnerContact());
                        String[] MobileNo = contactNo.toArray(new String[contactNo.size()]);

                        //Added Reason for HOLD
                        boolean isSend = CommonNotificationUtil.notifyOnDESArchitectHold(dto.getApplication_Id(), MobileNo, phoneNo, officeTel, User_Role);

                    } else if (dto.getTaskId().equals("18")) {
                        ArrayList<String> contactNo = new ArrayList<String>();
                            String phoneNo = userdet.getMobileNo();
                            String officeTel = userdet.getTelephoneNo();
                            String User_Role = "DES Structural Engineering Department";
                            contactNo.add(ownerList.getOwnerContact());
                        String[] MobileNo = contactNo.toArray(new String[contactNo.size()]);

                        //Added Reason for HOLD
                        boolean isSend = CommonNotificationUtil.notifyOnDESStructuralHold(dto.getApplication_Id(), MobileNo, phoneNo, officeTel, User_Role);

                    } else if (dto.getTaskId().equals("19")) {
                        ArrayList<String> contactNo = new ArrayList<String>();
                            String phoneNo = userdet.getMobileNo();
                            String officeTel = userdet.getTelephoneNo();
                            String User_Role = "DES Electrical Engineering Department";
                            contactNo.add(ownerList.getOwnerContact());
                        String[] MobileNo = contactNo.toArray(new String[contactNo.size()]);

                        //Added Reason for HOLD
                        boolean isSend = CommonNotificationUtil.notifyOnDESElectricalHold(dto.getApplication_Id(), MobileNo, phoneNo, officeTel, User_Role);

                    }
            }

            }
            else if(type.equalsIgnoreCase("UNHOLD"))
            {
                taskStageId = "TS009";
            }
            else if(type.equalsIgnoreCase("REJECT"))
            {
                taskStageId = "TS004";

                    for (OwnerDetailsDto ownerList : dto.getOwnerDetailsDtos())
                    {
                        ownerList.setApplication_Id(dto.getApplication_Id());
                        ownerList.setOwnerContact(ownerList.getOwnerContact());

                            if (dto.getTaskId().equals("15")) {
                                ArrayList<String> contactNo = new ArrayList<String>();
                                String phoneNo = userdet.getMobileNo();
                                String officeTel = userdet.getTelephoneNo();
                                String User_Role = "Dzongkhag Architectural Department";
                                contactNo.add(ownerList.getOwnerContact());
                                String[] MobileNo = contactNo.toArray(new String[contactNo.size()]);

                                //Added Reason for Rejection
                                boolean isSend = CommonNotificationUtil.notifyOnArchitectReject(dto.getApplication_Id(), MobileNo, phoneNo, officeTel, User_Role);

                            } else if (dto.getTaskId().equals("3")) {
                                ArrayList<String> contactNo = new ArrayList<String>();
                                String phoneNo = userdet.getMobileNo();
                                String officeTel = userdet.getTelephoneNo();
                                String User_Role = "Dzongkhag Electrical Engineering Department";
                                contactNo.add(ownerList.getOwnerContact());
                                String[] MobileNo = contactNo.toArray(new String[contactNo.size()]);

                                //Added Reason for Rejection
                                boolean isSend = CommonNotificationUtil.notifyOnElectricalReject(dto.getApplication_Id(), MobileNo, phoneNo, officeTel, User_Role);


                            } else if (dto.getTaskId().equals("4")) {
                                ArrayList<String> contactNo = new ArrayList<String>();
                                String phoneNo = userdet.getMobileNo();
                                String officeTel = userdet.getTelephoneNo();
                                String User_Role = "Dzongkhag Structural Engineering Department";
                                contactNo.add(ownerList.getOwnerContact());
                                String[] MobileNo = contactNo.toArray(new String[contactNo.size()]);

                                //Added Reason for Rejection
                                boolean isSend = CommonNotificationUtil.notifyOnStructuralReject(dto.getApplication_Id(), MobileNo, phoneNo, officeTel, User_Role);

                            } else if (dto.getTaskId().equals("17")) {
                                ArrayList<String> contactNo = new ArrayList<String>();
                                String phoneNo = userdet.getMobileNo();
                                String officeTel = userdet.getTelephoneNo();
                                String User_Role = "DES Architectural Department";
                                contactNo.add(ownerList.getOwnerContact());
                                String[] MobileNo = contactNo.toArray(new String[contactNo.size()]);

                                //Added Reason for Rejection
                                boolean isSend = CommonNotificationUtil.notifyOnDESArchitectReject(dto.getApplication_Id(), MobileNo, phoneNo, officeTel, User_Role);

                            } else if (dto.getTaskId().equals("18")) {
                                ArrayList<String> contactNo = new ArrayList<String>();
                                String phoneNo = userdet.getMobileNo();
                                String officeTel = userdet.getTelephoneNo();
                                String User_Role = "DES Structural Engineering Department";
                                contactNo.add(ownerList.getOwnerContact());
                                String[] MobileNo = contactNo.toArray(new String[contactNo.size()]);

                                //Added Reason for Rejection
                                boolean isSend = CommonNotificationUtil.notifyOnDESStructuralReject(dto.getApplication_Id(), MobileNo, phoneNo, officeTel, User_Role);

                            } else if (dto.getTaskId().equals("19"))
                            {
                                ArrayList<String> contactNo = new ArrayList<String>();
                                String phoneNo = userdet.getMobileNo();
                                String officeTel = userdet.getTelephoneNo();
                                String User_Role = "DES Electrical Engineering Department";
                                contactNo.add(ownerList.getOwnerContact());
                                String[] MobileNo = contactNo.toArray(new String[contactNo.size()]);

                                //Added Reason for Rejection
                                boolean isSend = CommonNotificationUtil.notifyOnDESElectricalReject(dto.getApplication_Id(), MobileNo, phoneNo, officeTel, User_Role);
                            }
                    }
            }
            else if(type.equalsIgnoreCase("FORWARD"))
            {
                taskStageId = "TS006";
            }
            else if (type.equalsIgnoreCase("DZONGKHAG_TO_DES"))
            {
                if (dto.getTaskId().equals("15")) {
                    if (dto.getIs_Structural_Forwarded().equalsIgnoreCase("Y") && dto.getIs_Architect_Forwarded().equalsIgnoreCase("Y") &&
                            dto.getIs_Electrical_Forwarded().equalsIgnoreCase("Y")){
                        taskStageId = "TS007";
                     //   taskStageId = "TS001";
                    }
                    else
                    {
                        taskStageId = "TS002";
                    }
                }
                else
                {
                    taskStageId = "TS003";
                }

            }
            else if (type.equalsIgnoreCase("FORWARD_DRAWING"))
            {
                taskStageId = "TS003";
            }
            else if (type.equalsIgnoreCase("FORWARD_DRAWING_TO_DES"))
            {
                taskStageId = "TS003";
            }
            else if(type.equalsIgnoreCase("DZO_TO_DES"))
            {
               // taskStageId = "TS007";
                taskStageId = "TS001";
                if(dto.getTaskId().equals("4"))
                {
                    if (dto.getIs_Structural_Forwarded() != null || dto.getIs_Structural_Forwarded() == "N")
                    {
                        dto.setIs_Structural_Forwarded("Y");
                    }
                    if (dto.getIs_Electrical_Forwarded()!= null || dto.getIs_Electrical_Forwarded() == "Y") {

                        dto.setIs_Electrical_Forwarded("N");
                    }


                }else
                {
                    if (dto.getIs_Electrical_Forwarded()!= null || dto.getIs_Electrical_Forwarded() == "N") {

                        dto.setIs_Electrical_Forwarded("Y");
                    }
                    if (dto.getIs_Structural_Forwarded() != null || dto.getIs_Structural_Forwarded() == "Y")
                    {
                        dto.setIs_Structural_Forwarded("N");
                    }
                }
                taskDetailsEntity.setIs_Electrical_Forwarded(dto.getIs_Electrical_Forwarded());

                taskDetailsEntity.setIs_Structural_Forwarded(dto.getIs_Structural_Forwarded());
            }
            else if(type.equalsIgnoreCase("OPERATOR_FORWARD_TO_DES"))
            {
                taskStageId = "TS003";
            }
            else
            {
                taskStageId = "TS003";
            }
                taskDetailsEntity.setTaskInstanceId(dto.getTaskInstanceId());
                taskDetailsEntity.setApplicationNo(dto.getApplication_Id());
                taskDetailsEntity.setCurrentTaskStageId(taskStageId);
                taskDetailsEntity.setCreatedBy(userdet.getCurrentRole().getRoleName());
                taskDetailsEntity.setModifiedBy(userdet.getCurrentRole().getRoleName());
                taskDetailsEntity.setCreatedOn(new Date());
                taskDetailsEntity.setModifiedOn(new Date());
                taskDetailsEntity.setIs_Architect_Forwarded(dto.getIs_Architect_Forwarded());
                taskDetailsEntity.setIs_Electrical_Forwarded(dto.getIs_Electrical_Forwarded());
                taskDetailsEntity.setIs_Structural_Forwarded(dto.getIs_Structural_Forwarded());
                taskDetailsEntity.setRemarks(dto.getAppl_comments());
                taskDetailsEntity.setReason_id(dto.getReasonId());
                taskDetailsEntity.setJuris_type(Integer.parseInt(userdet.getJurisdictions()[0].getJurisdiction()));
                taskDetailsEntity.setJuris_id(Integer.parseInt(userdet.getJurisdictions()[0].getLocationID()));
                commonDao.updateTaskList(taskDetailsEntity);

                if (type.equalsIgnoreCase("FORWARD") || type.equalsIgnoreCase("FORWARD_DRAWING") || type.equalsIgnoreCase("OPERATOR_FORWARD_TO_DES")) {
                    int countVerifier = commonDao.countVerifier(dto.getApplication_Id(), Integer.parseInt(userdet.getJurisdictions()[0].getJurisdiction()));
                    if(countVerifier==0) {
                        System.out.println("---------------type1 -----" + type);
                        if (type.equalsIgnoreCase("OPERATOR_FORWARD_TO_DES")) {
                            taskDetailsEntity.setTaskId("16");
                            taskDetailsEntity.setApplicationStatus("Forwarded To DES - Operator from Dzongkhag");
                            taskDetailsEntity.setTaskInstanceId(commonDao.generateInstanceId("nextTask"));
                            taskDetailsEntity.setCurrentTaskStageId("TS001");
                          //  taskDetailsEntity.setCurrentTaskStageId("TS007");
                            taskDetailsEntity.setJuris_id(Integer.parseInt("20"));
                            taskDetailsEntity.setJuris_type(Integer.parseInt("20"));
                            taskDetailsEntity.setCreatedBy(userdet.getFullName());
                            taskDetailsEntity.setModifiedBy(userdet.getFullName());
                            status = commonDao.saveTasklist(taskDetailsEntity);

                            for (OwnerDetailsDto ownerList : dto.getOwnerDetailsDtos())
                            {
                                ownerList.setApplication_Id(dto.getApplication_Id());
                                ownerList.setOwnerContact(dto.getOwner_Contact_No());

                                ArrayList<String> contactNo = new ArrayList<String>();
                                String phoneNo = userdet.getMobileNo();
                                String officeTel = userdet.getTelephoneNo();
                                String User_Role = "Dzongkhag Operator";
                                contactNo.add(ownerList.getOwnerContact());
                                String[] MobileNo = contactNo.toArray(new String[contactNo.size()]);
                                //Added Reason for Rejection
                                boolean isSend = CommonNotificationUtil.notifyOnAllDrawingForwarded(dto.getApplication_Id(), MobileNo, phoneNo, officeTel, User_Role);
                            }
                        } else if (type.equalsIgnoreCase("FORWARD_DRAWING")) {
                            if (dto.getIs_Architect_Forwarded().equalsIgnoreCase("Y")) {
                                taskDetailsEntity.setCreatedBy(userdet.getFullName());
                                taskDetailsEntity.setModifiedBy(userdet.getFullName());
                                taskDetailsEntity.setCreatedOn(new Date());
                                taskDetailsEntity.setModifiedOn(new Date());
                                taskDetailsEntity.setCurrentTaskStageId("TS001");
                                taskDetailsEntity.setRemarks(dto.getAppl_comments());
                                taskDetailsEntity.setTaskId("17");
                                taskDetailsEntity.setApplicationNo(dto.getApplication_Id());
                                taskDetailsEntity.setApplicationStatus("Forwarded To DES Architect from Dzongkhag");
                                taskDetailsEntity.setTaskInstanceId(commonDao.generateInstanceId("nextTask"));
                                status = commonDao.saveTasklist(taskDetailsEntity);

                            } else if (dto.getIs_Structural_Forwarded().equalsIgnoreCase("Y") && dto.getIs_Electrical_Forwarded().equalsIgnoreCase("Y")) {
                                if (dto.getIs_Structural_Forwarded().equalsIgnoreCase("Y")) {
                                    if (dto.getIs_Electrical_Forwarded() == null || dto.getIs_Electrical_Forwarded() == "") {
                                        dto.setIs_Electrical_Forwarded("N");
                                    }
                                    if (dto.getIs_Architect_Forwarded() == null || dto.getIs_Architect_Forwarded() == "") {
                                        dto.setIs_Architect_Forwarded("N");
                                    }
                                    if (dto.getIs_Structural_Forwarded() == null || dto.getIs_Structural_Forwarded() == "") {
                                        dto.setIs_Structural_Forwarded("N");
                                    }

                                    TasklistEntity taskEntity3 = new TasklistEntity();
                                    taskEntity3.setIs_Architect_Forwarded(dto.getIs_Architect_Forwarded());
                                    taskEntity3.setIs_Electrical_Forwarded(dto.getIs_Electrical_Forwarded());
                                    taskEntity3.setIs_Structural_Forwarded(dto.getIs_Structural_Forwarded());
                                    taskEntity3.setApplicationNo(dto.getApplication_Id());
                                    taskEntity3.setTaskInstanceId(dto.getTaskInstanceId());
                                    taskEntity3.setJuris_type(Integer.parseInt(userdet.getJurisdictions()[0].getJurisdiction()));
                                    taskEntity3.setJuris_id(Integer.parseInt(userdet.getJurisdictions()[0].getLocationID()));
                                    taskEntity3.setCreatedBy(userdet.getFullName());
                                    taskEntity3.setModifiedBy(userdet.getFullName());
                                    taskEntity3.setCreatedOn(new Date());
                                    taskEntity3.setModifiedOn(new Date());
                                    taskEntity3.setCurrentTaskStageId("TS001");
                                    taskEntity3.setRemarks(dto.getAppl_comments());
                                    taskEntity3.setTaskId("18");
                                    taskEntity3.setApplicationStatus("Forwarded To DES Structural from Dzongkhag");
                                    taskEntity3.setTaskInstanceId(commonDao.generateInstanceId("nextTask"));
                                    status = commonDao.saveTasklist(taskEntity3);
                                }
                                if (dto.getIs_Electrical_Forwarded().equalsIgnoreCase("Y")) {
                                    if (dto.getIs_Electrical_Forwarded() == null || dto.getIs_Electrical_Forwarded() == "") {
                                        dto.setIs_Electrical_Forwarded("N");
                                    }
                                    if (dto.getIs_Architect_Forwarded() == null || dto.getIs_Architect_Forwarded() == "") {
                                        dto.setIs_Architect_Forwarded("N");
                                    }
                                    if (dto.getIs_Structural_Forwarded() == null || dto.getIs_Structural_Forwarded() == "") {
                                        dto.setIs_Structural_Forwarded("N");
                                    }
                                    TasklistEntity taskEntity4 = new TasklistEntity();
                                    taskEntity4.setIs_Architect_Forwarded(dto.getIs_Architect_Forwarded());
                                    taskEntity4.setIs_Electrical_Forwarded(dto.getIs_Electrical_Forwarded());
                                    taskEntity4.setIs_Structural_Forwarded(dto.getIs_Structural_Forwarded());

                                    taskEntity4.setApplicationNo(dto.getApplication_Id());
                                    taskEntity4.setTaskInstanceId(dto.getTaskInstanceId());
                                    taskEntity4.setCreatedBy(userdet.getFullName());
                                    taskEntity4.setModifiedBy(userdet.getFullName());
                                    taskEntity4.setJuris_type(Integer.parseInt(userdet.getJurisdictions()[0].getJurisdiction()));
                                    taskEntity4.setJuris_id(Integer.parseInt(userdet.getJurisdictions()[0].getLocationID()));
                                    taskEntity4.setCreatedOn(new Date());
                                    taskEntity4.setModifiedOn(new Date());
                                    taskEntity4.setCurrentTaskStageId("TS001");
                                    taskEntity4.setRemarks(dto.getAppl_comments());
                                    taskEntity4.setTaskId("19");
                                    taskEntity4.setApplicationStatus("Forwarded To DES Electrical from Dzongkhag");
                                    taskEntity4.setTaskInstanceId(commonDao.generateInstanceId("nextTask"));
                                    status = commonDao.saveTasklist(taskEntity4);
                                }
                            } else if (dto.getIs_Electrical_Forwarded().equalsIgnoreCase("Y")) {
                                TasklistEntity taskEntity4 = new TasklistEntity();
                                taskEntity4.setIs_Architect_Forwarded(dto.getIs_Architect_Forwarded());
                                taskEntity4.setIs_Electrical_Forwarded(dto.getIs_Electrical_Forwarded());
                                taskEntity4.setIs_Structural_Forwarded(dto.getIs_Structural_Forwarded());

                                taskEntity4.setApplicationNo(dto.getApplication_Id());
                                taskEntity4.setTaskInstanceId(dto.getTaskInstanceId());
                                taskEntity4.setCreatedBy(userdet.getFullName());
                                taskEntity4.setModifiedBy(userdet.getFullName());
                                taskEntity4.setJuris_type(Integer.parseInt(userdet.getJurisdictions()[0].getJurisdiction()));
                                taskEntity4.setJuris_id(Integer.parseInt(userdet.getJurisdictions()[0].getLocationID()));
                                taskEntity4.setCreatedOn(new Date());
                                taskEntity4.setModifiedOn(new Date());
                                taskEntity4.setCurrentTaskStageId("TS001");
                                taskEntity4.setRemarks(dto.getAppl_comments());
                                taskEntity4.setTaskId("19");
                                taskEntity4.setApplicationStatus("Forwarded To DES Electrical from Dzongkhag");
                                taskEntity4.setTaskInstanceId(commonDao.generateInstanceId("nextTask"));
                                status = commonDao.saveTasklist(taskEntity4);

                            } else if (dto.getIs_Structural_Forwarded().equalsIgnoreCase("Y")) {
                                TasklistEntity taskEntity3 = new TasklistEntity();
                                taskEntity3.setIs_Architect_Forwarded(dto.getIs_Architect_Forwarded());
                                taskEntity3.setIs_Electrical_Forwarded(dto.getIs_Electrical_Forwarded());
                                taskEntity3.setIs_Structural_Forwarded(dto.getIs_Structural_Forwarded());
                                taskEntity3.setApplicationNo(dto.getApplication_Id());
                                taskEntity3.setTaskInstanceId(dto.getTaskInstanceId());
                                taskEntity3.setJuris_type(Integer.parseInt(userdet.getJurisdictions()[0].getJurisdiction()));
                                taskEntity3.setJuris_id(Integer.parseInt(userdet.getJurisdictions()[0].getLocationID()));
                                taskEntity3.setCreatedBy(userdet.getFullName());
                                taskEntity3.setModifiedBy(userdet.getFullName());
                                taskEntity3.setCreatedOn(new Date());
                                taskEntity3.setModifiedOn(new Date());
                                taskEntity3.setCurrentTaskStageId("TS001");
                                taskEntity3.setRemarks(dto.getAppl_comments());
                                taskEntity3.setTaskId("18");
                                taskEntity3.setApplicationStatus("Forwarded To DES Structural from Dzongkhag");
                                taskEntity3.setTaskInstanceId(commonDao.generateInstanceId("nextTask"));
                                status = commonDao.saveTasklist(taskEntity3);
                            }
                        }
                    else {
                        if (!type.equalsIgnoreCase("OPERATOR_FORWARD_TO_DES") && !type.equalsIgnoreCase("FORWARD")) {
                            taskDetailsEntity.setTaskInstanceId(commonDao.generateInstanceId("nextTask"));
                            taskDetailsEntity.setCurrentTaskStageId("TS007");
                            status = commonDao.saveTasklist(taskDetailsEntity);
                        }
                    }

                    ResponseMessage responseMessage = new ResponseMessage();
                    ApplicationDocDto applicationDocDto = new ApplicationDocDto();
                        System.out.println("------------ document from dto -----------"+dto.getAppUploadFile().getOriginalFilename());
                    MultipartFile supportingFile = dto.getAppUploadFile();
                    if (!supportingFile.isEmpty()) {
                        String fileName = supportingFile.getOriginalFilename();
                        System.out.println("------------filename ------------"+fileName);
                        String supportingFileExt = fileName.substring(fileName.lastIndexOf("."));
                        applicationDocDto.setDocument_name(fileName);
                        responseMessage = FileUploadToExternalLocation.fileUploader(supportingFile, fileName, "attachFile.properties", request);
                        FileUploadDTO fileUploadDTO = FileUploadToExternalLocation.fileUploadPathRetriever(request);
                        String uploadedDirectory = fileUploadDTO.getUploadFilePath().concat(fileName);
                        applicationDocDto.setDocument_path(uploadedDirectory);
                        applicationDocDto.setDocument_id(String.valueOf(UUID.randomUUID()));
                        String taskInstanceId = taskDetailsEntity.getTaskInstanceId();
                        ApplicationDocEntity applicationDocEntity = convertUploadDtoToUploadEntity(applicationDocDto, "Attached Files",
                                taskDetailsEntity.getApplicationNo(), taskInstanceId);
                        // To save supporting document
                        commonDao.uploadSupportingDocument(applicationDocEntity);
                    }
                }
            }
            else  if (type.equalsIgnoreCase("DZONGKHAG_TO_DES")) {
              System.out.print("------------- type 2--" +type);
                if(dto.getIs_Electrical_Forwarded().equalsIgnoreCase("Y") || dto.getIs_Structural_Forwarded().equalsIgnoreCase("Y")
                        || dto.getIs_Architect_Forwarded().equalsIgnoreCase("Y")) {
                    taskDetailsEntity.setTaskId("28");
                    taskDetailsEntity.setApplicationStatus("Forwarded To DES - Operator from Dzongkhag");
                    taskDetailsEntity.setTaskInstanceId(commonDao.generateInstanceId("nextTask"));
                  //  taskDetailsEntity.setCurrentTaskStageId("TS007");
                    taskDetailsEntity.setJuris_id(Integer.parseInt("20"));
                    taskDetailsEntity.setJuris_type(Integer.parseInt("20"));
                    taskDetailsEntity.setCurrentTaskStageId("TS001");
                    status = commonDao.saveTasklist(taskDetailsEntity);
                }

                ResponseMessage responseMessage = new ResponseMessage();
                ApplicationDocDto applicationDocDto = new ApplicationDocDto();
                MultipartFile supportingFile = dto.getAppUploadFile();
                    System.out.print("-------supportingFile name ------"+supportingFile);
              /*  if (!supportingFile.isEmpty() || supportingFile !=null)
                {
                    System.out.print("-------file name ------"+supportingFile.getOriginalFilename());
                    String fileName = "";
                    fileName =supportingFile.getOriginalFilename();
                    String supportingFileExt = fileName.substring(fileName.lastIndexOf("."));
                    applicationDocDto.setDocument_name(fileName);
                    responseMessage = FileUploadToExternalLocation.fileUploader(supportingFile, fileName, "attachFile.properties", request);
                    FileUploadDTO fileUploadDTO = FileUploadToExternalLocation.fileUploadPathRetriever(request);
                    String uploadedDirectory = fileUploadDTO.getUploadFilePath().concat(fileName);
                    applicationDocDto.setDocument_path(uploadedDirectory);
                    applicationDocDto.setDocument_id(String.valueOf(UUID.randomUUID()));
                    String taskInstanceId = taskDetailsEntity.getTaskInstanceId();
                    ApplicationDocEntity applicationDocEntity = convertUploadDtoToUploadEntity(applicationDocDto, "Attached Files",
                            taskDetailsEntity.getApplicationNo(), taskInstanceId);
                    // To save supporting document
                    commonDao.uploadSupportingDocument(applicationDocEntity);
                }*/
            }
            else if(type.equalsIgnoreCase("DZO_TO_DES"))
            {
                if (dto.getIs_Structural_Forwarded().equalsIgnoreCase("Y"))
                {
                    taskDetailsEntity.setTaskId("26");
                    taskDetailsEntity.setApplicationStatus("Forwarded To DES - Structural from Dzongkhag");
                    taskDetailsEntity.setTaskInstanceId(commonDao.generateInstanceId("nextTask"));
                    taskDetailsEntity.setCurrentTaskStageId("TS001");
                    status = commonDao.saveTasklist(taskDetailsEntity);
                } else
                {
                    taskDetailsEntity.setTaskId("27");
                    taskDetailsEntity.setApplicationStatus("Forwarded To DES - Electrical from Dzongkhag");
                    taskDetailsEntity.setTaskInstanceId(commonDao.generateInstanceId("nextTask"));
                    taskDetailsEntity.setCurrentTaskStageId("TS001");
                    status = commonDao.saveTasklist(taskDetailsEntity);
                }

                ResponseMessage responseSMS = new ResponseMessage();
                ApplicationDocDto applicationDocDto = new ApplicationDocDto();
                MultipartFile supportingFile1 = dto.getAppUploadFile();
                if (!supportingFile1.isEmpty() || supportingFile1!=null)
                {
                    String fileName = supportingFile1.getOriginalFilename();
                    String supportingFileExt = fileName.substring(fileName.lastIndexOf("."));
                    applicationDocDto.setDocument_name(fileName);
                    responseSMS = FileUploadToExternalLocation.fileUploader(supportingFile1, fileName, "attachFile.properties", request);
                    FileUploadDTO fileUploadDTO = FileUploadToExternalLocation.fileUploadPathRetriever(request);
                    String uploadedDirectory = fileUploadDTO.getUploadFilePath().concat(fileName);
                    applicationDocDto.setDocument_path(uploadedDirectory);
                    applicationDocDto.setDocument_id(String.valueOf(UUID.randomUUID()));
                    String taskInstanceId = taskDetailsEntity.getTaskInstanceId();
                    ApplicationDocEntity applicationDocEntity = convertUploadDtoToUploadEntity(applicationDocDto, "Attached Files",
                            taskDetailsEntity.getApplicationNo(), taskInstanceId);
                    // To save supporting document
                    commonDao.uploadSupportingDocument(applicationDocEntity);
                }
            }
                else if (type.equalsIgnoreCase("FORWARD_DRAWING_TO_DES")) {
                    taskDetailsEntity.setCurrentTaskStageId("TS001");
                    taskDetailsEntity.setRemarks(dto.getAppl_comments());
                    if (dto.getTaskId().equals("27") && dto.getIs_Electrical_Forwarded().equalsIgnoreCase("Y"))
                    {
                        taskDetailsEntity.setTaskId("19");
                        taskDetailsEntity.setApplicationStatus("Forwarded To DES Electrical from Dzongkhag");
                        taskDetailsEntity.setTaskInstanceId(commonDao.generateInstanceId("nextTask"));
                        status = commonDao.saveTasklist(taskDetailsEntity);

                    } else if (dto.getTaskId().equals("26") && dto.getIs_Structural_Forwarded().equalsIgnoreCase("Y")) {
                        taskDetailsEntity.setTaskId("18");
                        taskDetailsEntity.setApplicationStatus("Forwarded To DES Structural from Dzongkhag");
                        taskDetailsEntity.setTaskInstanceId(commonDao.generateInstanceId("nextTask"));
                        status = commonDao.saveTasklist(taskDetailsEntity);
                    } else if (dto.getTaskId().equals("28")) {
                        if (dto.getIs_Architect_Forwarded().equalsIgnoreCase("Y")) {
                            TasklistEntity taskEntity20 = new TasklistEntity();
                            taskEntity20.setIs_Architect_Forwarded(dto.getIs_Architect_Forwarded());
                            taskEntity20.setIs_Electrical_Forwarded(dto.getIs_Electrical_Forwarded());
                            taskEntity20.setIs_Structural_Forwarded(dto.getIs_Structural_Forwarded());
                            taskEntity20.setCreatedBy(userdet.getFullName());
                            taskEntity20.setModifiedBy(userdet.getFullName());
                            System.out.println("------------app no -------"+dto.getApplication_Id());
                            taskEntity20.setApplicationNo(dto.getApplication_Id());
                            taskEntity20.setCreatedOn(new Date());
                            taskEntity20.setModifiedOn(new Date());
                            taskEntity20.setCurrentTaskStageId("TS001");
                            taskEntity20.setRemarks(dto.getAppl_comments());
                            taskEntity20.setTaskId("17");
                            taskEntity20.setApplicationStatus("Forwarded To DES Architect from Dzongkhag");
                            taskEntity20.setTaskInstanceId(commonDao.generateInstanceId("nextTask"));
                            status = commonDao.saveTasklist(taskEntity20);

                        }
                        if (dto.getIs_Structural_Forwarded().equalsIgnoreCase("Y")){
                            TasklistEntity taskEntity18 = new TasklistEntity();
                            taskEntity18.setIs_Structural_Forwarded(dto.getIs_Structural_Forwarded());
                            taskEntity18.setApplicationNo(dto.getApplication_Id());
                            taskEntity18.setTaskInstanceId(dto.getTaskInstanceId());
                            taskEntity18.setCreatedBy(userdet.getFullName());
                            taskEntity18.setModifiedBy(userdet.getFullName());
                            taskEntity18.setJuris_type(Integer.parseInt(userdet.getJurisdictions()[0].getJurisdiction()));
                            taskEntity18.setJuris_id(Integer.parseInt(userdet.getJurisdictions()[0].getLocationID()));
                            taskEntity18.setCreatedOn(new Date());
                            taskEntity18.setModifiedOn(new Date());
                            taskEntity18.setCurrentTaskStageId("TS001");
                            taskEntity18.setRemarks(dto.getAppl_comments());
                            taskEntity18.setTaskId("18");
                            taskEntity18.setApplicationStatus("Forwarded To DES Structural from Dzongkhag");
                            taskEntity18.setTaskInstanceId(commonDao.generateInstanceId("nextTask"));
                            status = commonDao.saveTasklist(taskEntity18);

                        }
                        if (dto.getIs_Electrical_Forwarded().equalsIgnoreCase("Y"))
                        {
                            TasklistEntity taskEntity19 = new TasklistEntity();
                            taskEntity19.setIs_Electrical_Forwarded(dto.getIs_Electrical_Forwarded());
                            taskEntity19.setApplicationNo(dto.getApplication_Id());
                            taskEntity19.setTaskInstanceId(dto.getTaskInstanceId());
                            taskEntity19.setCreatedBy(userdet.getFullName());
                            taskEntity19.setModifiedBy(userdet.getFullName());
                            taskEntity19.setJuris_type(Integer.parseInt(userdet.getJurisdictions()[0].getJurisdiction()));
                            taskEntity19.setJuris_id(Integer.parseInt(userdet.getJurisdictions()[0].getLocationID()));
                            taskEntity19.setCreatedOn(new Date());
                            taskEntity19.setModifiedOn(new Date());
                            taskEntity19.setCurrentTaskStageId("TS001");
                            taskEntity19.setRemarks(dto.getAppl_comments());
                            taskEntity19.setTaskId("19");
                            taskEntity19.setApplicationStatus("Forwarded To DES Electrical from Dzongkhag");
                            taskEntity19.setTaskInstanceId(commonDao.generateInstanceId("nextTask"));
                            status = commonDao.saveTasklist(taskEntity19);
                        }
                    }
                    ResponseMessage responseMessage = new ResponseMessage();
                    ApplicationDocDto applicationDocDto = new ApplicationDocDto();
                    MultipartFile supportingFile2 = dto.getAppUploadFile();
                    if (!supportingFile2.isEmpty()) {
                        String fileName = supportingFile2.getOriginalFilename();
                        String supportingFileExt = fileName.substring(fileName.lastIndexOf("."));
                        applicationDocDto.setDocument_name(fileName);
                        responseMessage = FileUploadToExternalLocation.fileUploader(supportingFile2, fileName, "attachFile.properties", request);
                        FileUploadDTO fileUploadDTO = FileUploadToExternalLocation.fileUploadPathRetriever(request);
                        String uploadedDirectory = fileUploadDTO.getUploadFilePath().concat(fileName);
                        applicationDocDto.setDocument_path(uploadedDirectory);
                        applicationDocDto.setDocument_id(String.valueOf(UUID.randomUUID()));
                        String taskInstanceId = taskDetailsEntity.getTaskInstanceId();
                        ApplicationDocEntity applicationDocEntity = convertUploadDtoToUploadEntity(applicationDocDto, "Attached Files",
                                taskDetailsEntity.getApplicationNo(), taskInstanceId);
                        // To save supporting document
                        commonDao.uploadSupportingDocument(applicationDocEntity);
                    }
                }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
    public  List<TasklistDto> applicationStatus(String applicationNo)
    {
        return commonDao.applicationStatus(applicationNo);
    }

    @Override
    @Transactional
    public DropDownDto getCitizenDetails(String cidNo)
    {
        DropDownDto dto = new DropDownDto();
        try
        {
            OkHttpClient httpClient = new OkHttpClient();
            org.wso2.client.api.ApiClient apiClient = new org.wso2.client.api.ApiClient();

            httpClient.setConnectTimeout(10000, TimeUnit.MILLISECONDS);
            httpClient.setReadTimeout(10000, TimeUnit.MILLISECONDS);

            apiClient.setHttpClient(httpClient);
            apiClient.setBasePath("https://staging-datahub-apim.dit.gov.bt/dcrc_citizen_details_api/1.0.0");
            apiClient.setAccessToken("1a1e7391-07c4-3763-b4eb-2c30c810d80d");

            DefaultApi api = new DefaultApi(apiClient);

            CitizenDetailsResponse citizenDetailsResponse = api.citizendetailsCidGet(String.valueOf(cidNo));

            if (citizenDetailsResponse.getCitizenDetailsResponse().getCitizenDetail() != null &&
                    !citizenDetailsResponse.getCitizenDetailsResponse().getCitizenDetail().isEmpty())
            {
                CitizendetailsObj citizendetailsObj =
                        citizenDetailsResponse.getCitizenDetailsResponse().getCitizenDetail().get(0);

                dto.setDropdownName(citizendetailsObj.getFirstName() + " " + citizendetailsObj.getMiddleName() + citizendetailsObj.getLastName());

            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return dto;
    }
    @Override
    @Transactional
    public ApplicationDocDto getDocumentDetailsByDocId(String uploadDocId)
    {
        ApplicationDocDto dto=commonDao.getDocumentDetailsByDocId(uploadDocId);
        return dto;
    }

    @Override
    @Transactional
    public List<BuildingDto> getReport(String usr_role, String from_date, String to_date)
    {
        List<BuildingDto> reportDto = new ArrayList<BuildingDto>();
        try
        {
            reportDto = commonDao.getReport(usr_role, from_date,to_date);
        }
        catch(Exception e)
        {
            System.out.print("Exception in CommonService # getReport: " + e);

        }
        return reportDto;
    }

    @Override
    @Transactional
    public List<BuildingDto> getDocuments(String applicationNo)
    {
        List<BuildingDto> dto = new ArrayList<BuildingDto>();

        return dto = commonDao.getDocument(applicationNo);
    }

    @Override
    @Transactional
    public Object getTaskStage(String applicationNo, String taskId, String status) {
        return commonDao.getTaskStage(applicationNo, taskId, status);
    }

    @Override
    @Transactional
    public Object getHoldStatus(String applicationNo, String taskId, String holdstatus) {
        return commonDao.getHoldStatus(applicationNo, taskId, holdstatus);
    }

    @Override
    @Transactional
    public String unHoldTasklistApplication(String applicationNo, String taskId, String user_name) {
        String result = "Failed";
        try
        {
            result = commonDao.unHoldTasklistApplication(applicationNo, taskId, user_name);
        }
        catch(Exception e)
        {
            System.out.print("Exception in unHoldTasklistApplication # getMyTask: " + e);

        }
        return result;
    }

    @Override
    @Transactional
    public List<BuildingDto> countApplications(String locationID, String countapplication) {
        List<BuildingDto> countTotal = new ArrayList<BuildingDto>();
        try {
            countTotal = commonDao.countApplications(locationID, countapplication);
        }
        catch (Exception e){
            System.out.print("Exception in countApplication # getMyTask: " + e);
        }

        return countTotal;
    }

    @Override
    @Transactional
    public DropDownDto getDzongkhagByUserLocation(String locationID) {
        return commonDao.getDzongkhagByUserLocation(locationID);
    }

}

