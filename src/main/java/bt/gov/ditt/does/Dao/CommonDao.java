package bt.gov.ditt.does.Dao;

import bt.gov.ditt.does.Dto.*;
import bt.gov.ditt.does.Entity.*;
import bt.gov.ditt.does.base.BaseDao;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Pema Drakpa on 6/8/2020.
 */
@Repository
public class CommonDao extends BaseDao implements ICommonDao{
    private String GET_DOCUMENT_DTLS_BY_UUID = "SELECT d.Document_Name document_name,d.Document_Path document_path,d.Document_Id document_id FROM t_application_documents d WHERE d.Document_Id=? ";

    @Transactional
    public UserRolePrivilegeDto populateUserRolePrivilegeHierarchy(String username, String pass) {
        UserRolePrivilegeDto dto = new UserRolePrivilegeDto();
        try {
            String sql = "SELECT a.user_first_name AS user_name,b.role AS user_role,juris_id," +
                    "juris_type FROM t_user a " +
                    "LEFT JOIN t_role b ON a.user_role_id=b.role_id WHERE a.user_name=? AND a.password=?";
            Query query = sqlQuery(sql, UserRolePrivilegeDto.class).setParameter(1, username).setParameter(2, pass);
            if (query.list().size() > 0) {
                dto = (UserRolePrivilegeDto) query.list().get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public List<DropDownDto> getDropdown(String dropdownType, String param1, String param2) {
        List<DropDownDto> dropdownList = new ArrayList<DropDownDto>();
        try {
            String sqlQuery = null;
            if (dropdownType.equalsIgnoreCase("DZONGKHAG_LIST")) {
                sqlQuery = "SELECT a.Dzongkhag_Serial_No dropdownId,a.Dzongkhag_Name dropdownName FROM t_dzongkhag_lookup a";
            } else if (dropdownType.equalsIgnoreCase("GEWOG_LIST")) {
                sqlQuery = "SELECT a.Gewog_Serial_No dropdownId,a.Gewog_Name dropdownName FROM t_gewog_lookup a WHERE a.Dzongkhag_Serial_No=" + param1;
            } else if (dropdownType.equalsIgnoreCase("VILLAGE_LIST")) {
                sqlQuery = "SELECT a.Village_Serial_No dropdownId,a.Village_Name dropdownName FROM t_village_master a WHERE a.Gewog_Serial_No=" + param1;
            } else if (dropdownType.equalsIgnoreCase("BUILDING_CONST_TYPE")) {
                sqlQuery = "SELECT a.id dropdownId,a.construction_type dropdownName FROM t_building_construction_type a";
            } else if (dropdownType.equalsIgnoreCase("BUILDING_USE")) {
                sqlQuery = "SELECT a.Building_Use_Id dropdownId,a.Building_Use_Name dropdownName FROM t_building_use a";
            } else if (dropdownType.equalsIgnoreCase("REASON")) {
                sqlQuery = "SELECT a.reason_id dropdownId,a.reason_title dropdownName FROM t_reason a";
            } else if (dropdownType.equalsIgnoreCase("PROJECT_TITLE")) {
                sqlQuery = "SELECT Project_Title_Id dropdownId,Title dropdownName FROM t_project_title";
            }
            Query query = sqlQuery(sqlQuery, DropDownDto.class);
            dropdownList = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dropdownList;
    }

    @Transactional
    public String saveWorkflow(WorkflowEntity workFlowEntity) {
        try {
            saveOrUpdate(workFlowEntity);
            return "Success";
        } catch (Exception e) {
            System.out.print("Exception in CommonDao # saveWorkflow: " + e);
            return "Exception from saveWorkflow+" + e;
        }
    }

    @Transactional
    public String savebuildingDetails(BuildingEntity buildingEntity) {
        String result = "Failed";
        try {
            saveOrUpdate(buildingEntity);
            return  "Success";
        } catch (Exception e) {
            System.out.print("Exception in CommonDao # savebuildingDetails: " + e);
        }
        return result;
    }


    @Transactional
    public String saveTasklist(TasklistEntity taskDetailsEntity) {
        String result = "Failed";
        try {
            saveOrUpdate(taskDetailsEntity);
            return "Success";
        } catch (Exception e) {
            System.out.print("Exception in CommonDao # saveTasklist: " + e);
        }
        return result;
    }

    @Transactional
    public String generateApplicationNumber(BuildingDto dto) {
        Calendar calendar = Calendar.getInstance();
        Integer currentYear = calendar.get(Calendar.YEAR);
        Integer currentMonth = calendar.get(Calendar.MONTH) + 1;
        Integer currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        String applicationDay = currentDay.toString().length() == 1 ? "0" + currentDay.toString() : currentDay.toString();
        String applicationMonth = currentMonth.toString().length() == 1 ? "0" + currentMonth.toString() : currentMonth.toString();
        String applicationYear = currentYear.toString();
        String ApplicationNo = "";
        String locationName = "";
        int seqNo = 0;
        try {
            String serialNo = "";
            String sql = "SELECT appl_seq_No FROM application_sequence WHERE service_id =? AND year=? AND month=? AND Location_Id=?";
            Query query = sqlQuery(sql).setParameter(1, "821").setParameter(2, applicationYear).setParameter(3, applicationMonth).setParameter(4, dto.getDzonkhag_Id());
            if (query.list().size() > 0) {
                seqNo = Integer.parseInt((String) query.list().get(0));
            }
            seqNo++;
            if (seqNo <= 1) {
                Query query1 = sqlQuery("INSERT INTO application_sequence (appl_seq_No,service_id,day,month,year,location_Id) VALUES (?,?,?,?,?,?)")
                        .setParameter(1, seqNo)
                        .setParameter(2, "821")
                        .setParameter(3, applicationDay)
                        .setParameter(4, applicationMonth)
                        .setParameter(5, applicationYear)
                        .setParameter(6, dto.getDzonkhag_Id());
                query1.executeUpdate();
            } else {
                Query query1 = sqlQuery("UPDATE application_sequence SET appl_seq_No =? WHERE service_id =? AND year=? AND month=? AND location_Id=?");
                query1.setParameter(1, seqNo);
                query1.setParameter(2, "821");
                query1.setParameter(3, applicationYear);
                query1.setParameter(4, applicationMonth);
                query1.setParameter(5, dto.getDzonkhag_Id());
                query1.executeUpdate();
            }
            int serial = String.valueOf(seqNo).length();
            if (serial == 1) {
                serialNo = "00" + seqNo;
            } else if (serial == 2) {
                serialNo = "0" + seqNo;
            } else {
                serialNo = String.valueOf(seqNo);
            }
            String sql2 = "SELECT Dzongkhag_Name FROM t_dzongkhag_lookup WHERE Dzongkhag_Serial_No=?";
            Query query2 = sqlQuery(sql2).setParameter(1, dto.getDzonkhag_Id());
            locationName = (String) query2.list().get(0);
            ApplicationNo = "821" + "/" + applicationYear + "/" + applicationMonth + "/" + locationName + "/" + serialNo;
        } catch (Exception e) {
            System.out.print("Exception in CommonDaoImpl # generateApplicationNumber: " + e);
        }
        return ApplicationNo;
    }

    @Transactional
    public String generateInstanceId(String type) {
        String serialNumber = null;
        String instanceId = "", index, findex = "";
        int Lindex, nextId;
        try {
            if (type.equalsIgnoreCase("tasklist")) {
                findex = "TI";
                String sql = "SELECT MAX(Workflow_Instance_Id) FROM t_task_dtls";
                Query query2 = sqlQuery(sql);
                String maxNo = query2.list().get(0).toString().substring(2, 10);
                Lindex = Integer.parseInt(maxNo);
                Lindex = Lindex + 1;
            } else if (type.equalsIgnoreCase("nextTask")) {
                findex = "TI";
                //String sql = "SELECT MAX(Workflow_Instance_Id) FROM t_task_dtls";
                String sql = "SELECT MAX(Task_instance_Id) FROM t_task_dtls";
                Query query2 = sqlQuery(sql);
                String maxNo = query2.list().get(0).toString().substring(2, 10);
                Lindex = Integer.parseInt(maxNo);
                Lindex = Lindex + 3;
            } else {
                findex = "WI";
                String sql = "SELECT MAX(Task_instance_Id) FROM t_task_dtls";
                Query query2 = sqlQuery(sql);
                String maxNo = query2.list().get(0).toString().substring(2, 10);
                Lindex = Integer.parseInt(maxNo);
                Lindex = Lindex + 1;
            }
            index = Integer.toString(Lindex);
            if (index.length() == 1) {
                serialNumber = "0000000" + index;
            } else if (index.length() == 2) {
                serialNumber = "000000" + index;
            } else if (index.length() == 3) {
                serialNumber = "00000" + index;
            } else if (index.length() == 4) {
                serialNumber = "0000" + index;
            } else if (index.length() == 5) {
                serialNumber = "000" + index;
            } else if (index.length() == 6) {
                serialNumber = "00" + index;
            } else if (index.length() == 7) {
                serialNumber = "0" + index;
            } else {
                serialNumber = index;
            }
            instanceId = findex + serialNumber;
        } catch (Exception e) {
            System.out.print("Exception in CommonThromdeDaoImpl # setWorkflowInstanceId: " + e);
        }
        return instanceId;
    }

    @Transactional
    public String saveOwnerDetails(OwnerEntity ownerList) {
        try {
            String query = "INSERT INTO t_building_ownership_details\n" +
                    "(Application_Id,\n" +
                    "Owner_CID_No,\n" +
                    "Owner_Name,\n" +
                    "Owner_Mobile_No,\n" +
                    "Owner_Percentage_Share )\n" +
                    "VALUES (?,?,?,?,?) ";
            Query query1 = sqlQuery(query)
                    .setParameter(1, ownerList.getApplication_Id())
                    .setParameter(2, ownerList.getOwner_CID_No())
                    .setParameter(3, ownerList.getOwner_Name())
                    .setParameter(4, ownerList.getOwner_Mobile_No())
                    .setParameter(5, ownerList.getOwner_Percentage_Share());
            query1.executeUpdate();
            return "Success";
        } catch (Exception e) {
            System.out.print("Exception in CommonDao # saveWorkflow: " + e);
            return "Exception from saveWorkflow+" + e;
        }
    }

    @Transactional
    public List<TasklistDto> getGroupTasklist(String user_role, int juris_type, int juris_id) {
        List<TasklistDto> tasklist = new ArrayList<TasklistDto>();
        try {
            String sqlQuery = "";
            if (user_role!=null && user_role.equalsIgnoreCase("DOES Operater")) {
                sqlQuery = "SELECT \n" +
                        "  a.Application_Id applicationNo,\n" +
                        "  a.Application_Status applicationStatus,\n" +
                        "  a.Created_By createdBy,\n" +
                        "  a.Task_Id,\n" +
                        "  DATE_FORMAT(a.Created_on, '%d/%m/%Y') stringCreatedOn \n" +
                        "FROM\n" +
                        "  t_task_dtls a \n" +
                        "WHERE a.Task_Id IN (26, 27, 28, 16) \n" +
                        "  AND a.juris_id = "+ juris_id +" \n" +
                        "  AND a.juris_type = "+ juris_type +" \n" +
                        "  AND a.Current_Task_Stage_Id IN ('TS001', 'TS007') \n" +
                        "ORDER BY a.Task_Id ASC ";
            }
            else if (user_role.equalsIgnoreCase("DOES Architect")) {
                sqlQuery = "SELECT \n" +
                        "a.Application_Id applicationNo,a.Application_Status applicationStatus,a.Task_Id," +
                        "DATE_FORMAT(a.Created_on,'%d/%m/%Y') stringCreatedOn,a.Created_By createdBy " +
                        " FROM t_task_dtls a  " +
                        "  LEFT JOIN t_user b \n" +
                        "    ON a.Modified_By = b.user_first_name \n" +
                        "  LEFT JOIN t_role c \n" +
                        "    ON a.Task_Id = c.role_id \n" +
                        " WHERE  a.juris_id = "+ juris_id +" \n" +
                        "  AND a.juris_type = "+ juris_type +"  \n" +
                        "  AND a.Task_Id in (20,17) \n" +
                        "  AND a.Current_Task_Stage_Id='TS001'\n" +
                        " GROUP BY a.Application_Id ORDER BY a.Task_Id ASC";
            }
            else if (user_role!=null && user_role.equalsIgnoreCase("DZO_OPERATOR")) {
                sqlQuery = "SELECT \n" +
                        "a.Application_Id applicationNo,a.Application_Status applicationStatus,a.Task_Id," +
                        "DATE_FORMAT(a.Created_on,'%d/%m/%Y') stringCreatedOn, a.Created_By createdBy" +
                        " FROM t_task_dtls a  " +
                        "  LEFT JOIN t_user b \n" +
                        "    ON a.Created_By = b.user_first_name \n" +
                        "  LEFT JOIN t_role c \n" +
                        "    ON a.Task_Id = c.role_id \n" +
                        " WHERE  a.juris_id = "+ juris_id +" \n" +
                        "  AND a.juris_type = "+ juris_type +"  and a.Task_Id IN (21,5) \n" +
                        "  AND a.Current_Task_Stage_Id='TS001'\n" +
                        " GROUP BY a.Application_Id ORDER BY a.Task_Id ASC";
            }
            else {
                sqlQuery = "SELECT  " +
                        "a.Application_Id applicationNo,a.Application_Status applicationStatus,a.Task_Id," +
                        "DATE_FORMAT(a.Created_on,'%d/%m/%Y') stringCreatedOn, a.Created_By createdBy" +
                        " FROM t_task_dtls a  " +
                        "LEFT JOIN t_user b ON a.Created_By=b.user_first_name " +
                        "LEFT JOIN t_role c ON a.Task_Id=c.role_id " +
                        " WHERE a.Current_Task_Stage_Id='TS001' AND c.role ='" + user_role + "'  AND a.juris_id=" + juris_id + " AND a.juris_type=" + juris_type + " GROUP BY a.Application_Id";
            }
            Query query = sqlQuery(sqlQuery, TasklistDto.class);
            tasklist = query.list();

        } catch (Exception e) {
            System.out.print("Exception in CommonDao # getGroupTasklist: " + e);

        }
        return tasklist;
    }

    @Transactional
    public List<TasklistDto> getMyTasklist(String user_role, int juris_type, int juris_id) {
        List<TasklistDto> tasklist = new ArrayList<TasklistDto>();
        try {
            String sqlQuery = "";
            if (user_role!=null && user_role.equalsIgnoreCase("DOES Operater")) {
                sqlQuery = "SELECT \n" +
                        "  d.Task_Stage_Name,\n" +
                        "  a.Application_Id applicationNo,\n" +
                        "  a.Application_Status applicationStatus,\n" +
                        "  a.Created_By createdBy,\n" +
                        "   a.Task_Id,\n" +
                        "  DATE_FORMAT(a.Modified_On, '%d/%m/%Y') AS stringModifiedOn \n" +
                        "FROM\n" +
                        "  t_task_dtls a \n" +
                        "  LEFT JOIN t_task_stages d \n" +
                        "    ON a.Current_Task_Stage_Id = d.Task_Stage_Id \n" +
                        "WHERE a.Task_Id IN (16, 27, 26, 28) \n" +
                        "  AND a.Current_Task_Stage_Id IN ('TS002', 'TS005', 'TS009') \n" +
                        "  AND a.juris_id = "+ juris_id +" \n" +
                        "  AND a.juris_type = "+ juris_type +" ;";
            }

            else if (user_role!=null && user_role.equalsIgnoreCase("DZO_OPERATOR")) {
                sqlQuery = "SELECT d.Task_Stage_Name," +
                        " a.Application_Id applicationNo,a.Application_Status applicationStatus,a.Task_Id," +
                        " DATE_FORMAT(a.Modified_On, '%d/%m/%Y') AS stringModifiedOn," +
                        " a.Created_By createdBy" +
                        " FROM t_task_dtls a  " +
                        " LEFT JOIN t_user b ON a.Created_By=b.user_first_name " +
                        " LEFT JOIN t_role c ON a.Task_Id=c.role_id " +
                        " LEFT JOIN t_task_stages d ON a.Current_Task_Stage_Id=d.Task_Stage_Id " +
                        " WHERE a.Current_Task_Stage_Id IN ('TS002','TS005','TS009' ) AND a.Task_Id IN (21,5)" +
                        " AND a.juris_id=" + juris_id + " AND a.juris_type=" + juris_type + " GROUP BY a.Application_Id";

            }

            else if (user_role.equalsIgnoreCase("DOES Architect")) {
                sqlQuery = "SELECT d.Task_Stage_Name," +
                        " a.Application_Id applicationNo,a.Application_Status applicationStatus,a.Task_Id," +
                        " DATE_FORMAT(a.Modified_On, '%d/%m/%Y') AS stringModifiedOn," +
                        " a.Created_By createdBy" +
                        " FROM t_task_dtls a  " +
                        " LEFT JOIN t_user b ON a.Created_By=b.user_first_name " +
                        " LEFT JOIN t_role c ON a.Task_Id=c.role_id " +
                        " LEFT JOIN t_task_stages d ON a.Current_Task_Stage_Id=d.Task_Stage_Id " +
                        " WHERE a.Task_Id IN (17,20) AND a.Current_Task_Stage_Id IN ('TS002','TS005','TS009' ) AND c.role  ='" + user_role + "'" +
                        "   AND a.juris_id=" + juris_id + " AND a.juris_type=" + juris_type + " GROUP BY a.Application_Id";

            }
            else {
                sqlQuery = "SELECT d.Task_Stage_Name," +
                        " a.Application_Id applicationNo,a.Application_Status applicationStatus,a.Task_Id," +
                        " DATE_FORMAT(a.Modified_On, '%d/%m/%Y') AS stringModifiedOn," +
                        " a.Created_By createdBy" +
                        " FROM t_task_dtls a  " +
                        " LEFT JOIN t_user b ON a.Created_By=b.user_first_name " +
                        " LEFT JOIN t_role c ON a.Task_Id=c.role_id " +
                        " LEFT JOIN t_task_stages d ON a.Current_Task_Stage_Id=d.Task_Stage_Id " +
                        " WHERE a.Current_Task_Stage_Id IN ('TS002','TS005','TS009' ) AND c.role  ='" + user_role + "' " +
                        "  AND a.juris_id=" + juris_id + " AND a.juris_type=" + juris_type + " GROUP BY a.Application_Id";


            }

            Query query = sqlQuery(sqlQuery, TasklistDto.class);
            tasklist = query.list();

        } catch (Exception e) {
            System.out.print("Exception in CommonDao # getGroupTasklist: " + e);

        }
        return tasklist;
    }


    @Transactional
    public String claimTasklistApplication(String applicationNo, String taskId, String user_name) {
        String result = "Failed";
        try {
            Query query1 = sqlQuery("UPDATE t_task_dtls a SET a.Owner ='" + user_name + "' ,a.Current_Task_Stage_Id='TS002' WHERE a.Application_Id ='" + applicationNo + "' AND a.Task_Id ='" + taskId + "'");
            query1.executeUpdate();

        } catch (Exception e) {
            System.out.print("Exception in CommonDao # claimTasklistApplication: " + e);

        }
        return result;
    }

    @Transactional
        public String unClaimTasklistApplication(String applicationNo, String taskId, String user_role) {
        String result = "Failed";

        try {
            String stageTaskId = "TS001";
            if(user_role.equalsIgnoreCase("DOES Operater")){
                stageTaskId = "TS007";
            }
            Query query1 = sqlQuery("UPDATE t_task_dtls a SET a.Owner ='' ,a.Current_Task_Stage_Id ='" + stageTaskId + "'" +
                    " WHERE a.Application_Id ='" + applicationNo + "' " +
                    " AND a.Task_Id ='" + taskId + "'");
            query1.executeUpdate();
        } catch (Exception e) {
            System.out.print("Exception in CommonDao # unClaimTasklistApplication: " + e);
        }
        return result;
    }

    @Transactional
    public Object openTasklistApplication(String applicationNo, String taskId, String detailsType) {
        BuildingDto dto = new BuildingDto();
        try {
            String sql = null;
            if (detailsType.equalsIgnoreCase("BUILDING_DETAILS")) {
                sql = "SELECT \n" +
                        "  (SELECT \n" +
                        "    u.Remarks \n" +
                        "  FROM\n" +
                        "    t_task_dtls u \n" +
                        "  WHERE u.Application_Id = '  applicationNo  ' \n" +
                        "    AND u.Task_Id = 15 \n" +
                        "  LIMIT 1) architectRemarks,\n" +
                        "  t_building_application_details.Application_Id,\n" +
                        "  t_building_application_details.Service_Id,\n" +
                        "  t_building_application_details.Is_Private_Building,\n" +
                        "  t_building_application_details.Institution_Name,\n" +
                        "  t_building_application_details.Institution_Registration_No Details,\n" +
                        "  t_building_application_details.Thram_Nos,\n" +
                        "  t_building_application_details.Plot_Nos,\n" +
                        "  t_building_application_details.Precinct_Type_Id,\n" +
                        "  t_building_application_details.Site_Area,\n" +
                        "  t_building_application_details.Site_Area_Unit,\n" +
                        "  t_building_application_details.Dzonkhag_Id,\n" +
                        "  t_building_application_details.Gewog_Id,\n" +
                        "  t_building_application_details.Village_Id,\n" +
                        "  t_building_application_details.Contact_Address,\n" +
                        "  t_building_application_details.Contact_Email_Id,\n" +
                        "  t_building_application_details.Contact_Mobile_No,\n" +
                        "  t_building_application_details.Architect_Name,\n" +
                        "  t_building_application_details.Architect_Registration_No,\n" +
                        "  t_building_application_details.Structural_Name,\n" +
                        "  t_building_application_details.Structural_ID,\n" +
                        "  t_building_application_details.Electrical_Name,\n" +
                        "  t_building_application_details.Electrical_ID,\n" +
                        "  t_building_application_details.Building_Application_Category_Id,\n" +
                        "  t_building_application_details.Building_Use_Id,\n" +
                        "  (SELECT \n" +
                        "    Building_Use_Name \n" +
                        "  FROM\n" +
                        "    t_building_use \n" +
                        "  WHERE building_use_id = t_building_application_details.Building_Use_Id) buildingUseString,\n" +
                        "  (SELECT \n" +
                        "    construction_type \n" +
                        "  FROM\n" +
                        "    t_building_construction_type \n" +
                        "  WHERE id = t_building_application_details.Construction_Type) constructionTypeString,\n" +
                        "  t_building_application_details.Appl_comments,\n" +
                        "  t_building_application_details.Str_Stutas_Id,\n" +
                        "  t_building_application_details.Ele_Stutas_Id,\n" +
                        "  t_building_application_details.Structural_Person_Name,\n" +
                        "  t_building_application_details.Electrical_Person_Name,\n" +
                        "  t_building_application_details.Electrical_Graduation_Year,\n" +
                        "  t_building_application_details.Structural_Graduation_Year,\n" +
                        "  t_building_application_details.Architect_Contact_No,\n" +
                        "  t_building_application_details.Number_of_Floor,\n" +
                        "  t_building_application_details.Is_Architect_Forwarded,\n" +
                        "  t_building_application_details.Is_Electrical_Forwarded,\n" +
                        "  t_building_application_details.Is_Structural_Forwarded,\n" +
                        "  (SELECT \n" +
                        "    t_task_dtls.Task_Instance_Id \n" +
                        "  FROM\n" +
                        "    t_task_dtls \n" +
                        "  WHERE t_task_dtls.Application_Id = t_building_application_details.Application_Id \n" +
                        "    AND t_task_dtls.Task_Id = '"+taskId+"'\n" +
                        "  ORDER BY Created_on DESC \n" +
                        "  LIMIT 1) taskInstanceId,\n" +
                        "  a.Dzongkhag_Name,\n" +
                        "  b.Gewog_Name,\n" +
                        "  c.Village_Name,\n" +
                        "  d.Unit_Name,\n" +
                        "  IF(\n" +
                        "    (SELECT \n" +
                        "      e.Current_Task_Stage_Id \n" +
                        "    FROM\n" +
                        "      t_task_dtls e \n" +
                        "    WHERE e.Application_Id = Application_Id \n" +
                        "    ORDER BY Modified_On DESC \n" +
                        "    LIMIT 1) = 'TS005',\n" +
                        "    (SELECT \n" +
                        "      e.Remarks \n" +
                        "    FROM\n" +
                        "      t_task_dtls e \n" +
                        "    WHERE e.Application_Id = Application_Id \n" +
                        "    ORDER BY Modified_On DESC \n" +
                        "    LIMIT 1),\n" +
                        "    ''\n" +
                        "  ) reasonDesc,\n" +
                        "  j.Title projectTitle,\n" +
                        "  p.Document_Id document_id \n" +
                        "FROM\n" +
                        "  t_building_application_details \n" +
                        "  LEFT JOIN t_dzongkhag_lookup a \n" +
                        "    ON t_building_application_details.Dzonkhag_Id = a.Dzongkhag_Id \n" +
                        "  LEFT JOIN t_gewog_lookup b \n" +
                        "    ON t_building_application_details.Gewog_Id = b.Gewog_Serial_No \n" +
                        "  LEFT JOIN t_village_master c \n" +
                        "    ON t_building_application_details.Village_Id = c.Village_Serial_No \n" +
                        "  LEFT JOIN t_unit_area_master d \n" +
                        "    ON t_building_application_details.Site_Area_Unit = d.Unit_Area_ID \n" +
                        "  LEFT JOIN t_project_title j \n" +
                        "    ON t_building_application_details.Project_Title_Id = j.Project_Title_Id \n" +
                        "  LEFT JOIN t_application_documents p \n" +
                        "    ON t_building_application_details.Application_Id = p.Application_Id \n" +
                        " WHERE t_building_application_details.Application_Id = ?";
            } else if (detailsType.equalsIgnoreCase("IS_ELECTRICAL_FORWARDED")) {
                sql = "SELECT COUNT(*) rowCount, a.is_Electrical_Forwarded FROM t_task_dtls a WHERE a.Application_Id=? and a.task_id IN (3,19)";
            } else if (detailsType.equalsIgnoreCase("IS_STRUCTURAL_FORWARDED")) {
                sql = "SELECT COUNT(*) rowCount, a.is_Structural_Forwarded FROM t_task_dtls a WHERE a.Application_Id=? and a.task_id IN (4,18)";
            } else if (detailsType.equalsIgnoreCase("PLANNER_DETAILS")) {
                sql = "SELECT \n" +
                        "IF((SELECT COUNT(*) FROM t_task_dtls b WHERE b.task_id='2' AND b.Application_Id='" + applicationNo + "')>0,\n" +
                        "'Architect Verification Needed','') is_Architect_Forwarded,\n" +
                        "IF((SELECT COUNT(*) FROM t_task_dtls b WHERE b.task_id='3' AND b.Application_Id='" + applicationNo + "')>0,\n" +
                        "'Electrical Verification Required','') is_Electrical_Forwarded,\n" +
                        "IF((SELECT COUNT(*) FROM t_task_dtls b WHERE b.task_id='4' AND b.Application_Id='" + applicationNo + "')>0,\n" +
                        "'Structual Verification Needed','') is_Structural_Forwarded,\n" +
                        "(SELECT c.Remarks FROM t_task_dtls c WHERE c.Application_Id = '" + applicationNo + "' AND c.task_id=15  ORDER BY c.Created_on DESC LIMIT 1) Appl_comments\n" +
                        "\n" +
                        " FROM t_building_application_details a WHERE a.Application_Id=?";
            } else if (detailsType.equalsIgnoreCase("COUNT_VERIFIER_STATUS")) {

                sql = "SELECT COUNT(*) rowCount FROM t_task_dtls a WHERE \n" +
                        "a.Application_Id = ? AND a.Current_Task_Stage_Id='TS003' AND \n" +
//                        "a.Task_Id IN (2,3,4)";
                        "a.Task_Id IN (2,3,4,18,19)";
            } else if (detailsType.equalsIgnoreCase("FORWARD_REASON")) {
                sql = "SELECT \n" +
                        "(SELECT b.reason_desc FROM t_reason b WHERE b.reason_id=a.reason_id) reasonDesc,\n" +
                        "a.Remarks appl_comments" +
                        " FROM t_task_dtls a WHERE a.Application_Id=? AND a.Task_Id='" + taskId + "'";
            } else if (detailsType.equalsIgnoreCase("IS_ARCHIECT_DRAWING")) {
                sql = "SELECT" +
                        " a.is_Architect_Forwarded" +
                        " FROM" +
                        " t_task_dtls a" +
                        " LEFT JOIN t_building_application_details b ON a.Application_Id = b.Application_Id" +
                        " WHERE a.Application_Id=? AND a.Task_Id=16";
            } else if (detailsType.equalsIgnoreCase("IS_STRUCTURAL_DRAWING")) {
                sql = "SELECT" +
                        " a.is_Structural_Forwarded" +
                        " FROM" +
                        " t_task_dtls a" +
                        " LEFT JOIN t_building_application_details b ON a.Application_Id = b.Application_Id" +
                        " WHERE a.Application_Id=? AND a.Task_Id=16";
            } else if (detailsType.equalsIgnoreCase("IS_ELECTRICAL_DRAWING")) {
                sql = "SELECT" +
                        " a.is_Electrical_Forwarded" +
                        " FROM" +
                        " t_task_dtls a" +
                        " LEFT JOIN t_building_application_details b ON a.Application_Id = b.Application_Id" +
                        " WHERE a.Application_Id=? AND a.Task_Id=16";
            }
            Query query = sqlQuery(sql, BuildingDto.class).setParameter(1, applicationNo);
            dto = (BuildingDto) query.list().get(0);
        } catch (Exception e) {
            System.out.print("Exception in CommonDao # openTasklistApplication: " + e);
        }
        return dto;
    }

    @Transactional
    public List<OwnerEntity> getApplicationOwnerList(String applicationNo) {
        List<OwnerEntity> ownerList = new ArrayList<OwnerEntity>();
        try {
            String sql = null;
            sql = "SELECT a.Owner_CID_No,a.Owner_Name,a.Owner_Mobile_No,a.Owner_Percentage_Share  "
                    + " FROM t_building_ownership_details a WHERE a.Application_Id='" + applicationNo + "'";
            Query query = sqlQuery(sql, OwnerEntity.class);
            if (query.list().size() > 0) {
                ownerList = query.list();
            }
        } catch (Exception e) {
            System.out.print("Exception in CommonDao # openTasklistApplication: " + e);
        }
        return ownerList;
    }

    @Transactional
    public int updateTaskList(TasklistEntity dto) {
        int count = 0;
        String query = null;
        try {

            String sqlQuery =  "UPDATE \n" +
                    "    t_task_dtls \n" +
                    "  SET \n" +
                    "    Current_Task_Stage_Id = ?,\n" +
                    "    Remarks = ?,\n" +
                    "    Modified_By = ?,\n" +
                    "    Modified_On = ? \n" +
                    "  WHERE Task_instance_Id = ?";
            Query hQuery = (Query) hibernateQuery(sqlQuery, TasklistEntity.class)
                    .setParameter(1, dto.getCurrentTaskStageId())
                    .setParameter(2, dto.getRemarks())
                    .setParameter(3, dto.getModifiedBy())
                    .setParameter(4, dto.getModifiedOn())
                    .setParameter(5, dto.getTaskInstanceId());
            count =hQuery.executeUpdate();
            if(count>0) {

                    if (dto.getIs_Architect_Forwarded()!=null && dto.getIs_Architect_Forwarded().equalsIgnoreCase("Y")) {
                    query = "UPDATE \n" +
                            "t_building_application_details a \n" +
                            "LEFT JOIN t_task_dtls b \n" +
                            "ON a.Application_Id = b.Application_Id \n" +
                            "SET " +
                            "a.Is_Architect_Forwarded =?\n" +
                            "WHERE a.Application_Id = ?";
                    Query query3 = sqlQuery(query)
                            .setParameter(1, dto.getIs_Architect_Forwarded())
                            .setParameter(2, dto.getApplicationNo());
                    count = query3.executeUpdate();
                }
                if (dto.getIs_Electrical_Forwarded()!=null && dto.getIs_Electrical_Forwarded().equalsIgnoreCase("Y")) {
                    query = "UPDATE \n" +
                            "t_building_application_details a \n" +
                            "LEFT JOIN t_task_dtls b \n" +
                            "ON a.Application_Id = b.Application_Id \n" +
                            "SET \n" +
                            "a.Is_Electrical_Forwarded = ? \n" +
                            "WHERE a.Application_Id = ?";
                    Query query4 = sqlQuery(query)
                            .setParameter(1, dto.getIs_Electrical_Forwarded())
                            .setParameter(2, dto.getApplicationNo());
                    count = query4.executeUpdate();
                } if (dto.getIs_Structural_Forwarded()!=null && dto.getIs_Structural_Forwarded().equalsIgnoreCase("Y")){
                    query = "UPDATE \n" +
                            "t_building_application_details a \n" +
                            "LEFT JOIN t_task_dtls b \n" +
                            "ON a.Application_Id = b.Application_Id \n" +
                            "SET \n" +
                            "a.Is_Structural_Forwarded = ?\n" +
                            "WHERE a.Application_Id = ?";
                    Query query5 = sqlQuery(query)
                            .setParameter(1, dto.getIs_Structural_Forwarded())
                            .setParameter(2, dto.getApplicationNo());
                    count = query5.executeUpdate();
                }
            }

        } catch (Exception ex) {

            System.out.print("Exception in CommonThromdeDao # updateTaskList: " + ex);
        }

        return count;
    }

    @Transactional
    public String uploadSupportingDocument(ApplicationDocEntity applicationDocEntity) {
        String result = "Failed";
        try {
            saveOrUpdate(applicationDocEntity);
            result = "Success";
        } catch (Exception e) {
            System.out.print("Exception in CommonDao # savebuildingDetails: " + e);
        }
        return result;
    }

    @Transactional
    public List<TasklistDto> applicationStatus(String applicationNo) {
        List<TasklistDto> result = new ArrayList<TasklistDto>();
        try {
            String sql = null;
            sql = "\n" +
                    "SELECT \n" +
                    "IF(a.Current_Task_Stage_Id='TS001',DATE_FORMAT(a.Created_on,'%d/%m/%Y'),DATE_FORMAT(a.Modified_On,'%d/%m/%Y'))stringCreatedOn,\n" +
                    "IF(a.Current_Task_Stage_Id='TS001',a.Created_By,a.Owner)createdBy,c.Task_Stage_Name ,a.Application_Status AS applicationStatus \n" +
                    "FROM t_task_dtls a\n" +
                    "LEFT JOIN t_task b ON a.Task_Id=b.Task_Id \n" +
                    "LEFT JOIN t_task_stages c ON a.Current_Task_Stage_Id=c.Task_Stage_Id WHERE a.Application_Id=?\n" +
                    "UNION  \n" +
                    "SELECT \n" +
                    "IF(a.Current_Task_Stage_Id='TS001',DATE_FORMAT(a.Created_on,'%d/%m/%Y'),DATE_FORMAT(a.Modified_On,'%d/%m/%Y'))stringCreatedOn,\n" +
                    "IF(a.Current_Task_Stage_Id='TS001',a.Created_By,a.Owner)createdBy,c.Task_Stage_Name ,a.Application_Status AS applicationStatus \n" +
                    "FROM t_task_dtls_audit a\n" +
                    "LEFT JOIN t_task b ON a.Task_Id=b.Task_Id \n" +
                    "LEFT JOIN t_task_stages c ON a.Current_Task_Stage_Id=c.Task_Stage_Id\n" +
                    "WHERE a.Application_Id=?";
            Query query = sqlQuery(sql, TasklistDto.class).setParameter(1,applicationNo).setParameter(2,applicationNo);
            if (query.list().size() > 0) {
                result = query.list();
            }
        } catch (Exception e) {
            System.out.print("Exception in CommonDao # applicationStatus: " + e);
        }
        return result;
    }

    @Transactional
    public ApplicationDocDto getDocumentDetailsByDocId(String uploadDocId) {
        ApplicationDocDto dto = new ApplicationDocDto();
        try {
            Query query = sqlQuery(GET_DOCUMENT_DTLS_BY_UUID, ApplicationDocDto.class).setParameter(1, uploadDocId);
            dto = (ApplicationDocDto) query.list().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    @Transactional
    public int countVerifier(String application_id, int juris_type) {
        int count = 0;
        try {
            BuildingDto dto = new BuildingDto();
            String sql ="SELECT " +
                    "  COUNT(*) rowCount " +
                    " FROM " +
                    "  t_task_dtls a " +
                    " WHERE a.Application_Id =? " +
                    "  AND a.Current_Task_Stage_Id IN ('TS001','TS002','TS007')" +
                    "  AND a.Task_Id IN (17,18,19)" +
                    "  AND a.juris_type=?";
            Query query = sqlQuery(sql, BuildingDto.class).setParameter(1, application_id).setParameter(2, juris_type);
            dto =  (BuildingDto) query.list().get(0);
            count = dto.getRowCount().intValue();
        } catch (Exception ex) {
            System.out.print("Exception in CommonThromdeDao # updateTaskList: " + ex);
        }
        return count;
    }

    @Transactional
    public int countAll(String application_id, String checkType) {
        int count = 0;
        try
        {
            BuildingDto dto = new BuildingDto();
            String sql ="SELECT \n" +
                    "  COUNT(*) rowCount \n" +
                    "FROM\n" +
                    "  t_task_dtls a \n" +
                    "WHERE a.Application_Id = ?\n" +
                    "  AND a.Current_Task_Stage_Id IN ('TS007')\n" +
                    "  AND a.Task_Id NOT IN (17,18,19,25,24,20)";
            if(checkType.equalsIgnoreCase("DES_NOTESHEET"))
                sql = sql + " AND Task_Id NOT IN (23,22,5)";
            Query query = sqlQuery(sql, BuildingDto.class).setParameter(1, application_id);
            dto =  (BuildingDto) query.list().get(0);
            count = dto.getRowCount().intValue();
        } catch (Exception ex) {
            System.out.print("Exception in CommonThromdeDao # updateTaskList: " + ex);
        }
        return count;
    }

    @Transactional
    public Object getTaskStage(String applicationNo, String taskId, String status) {
        String sql = null;
        TasklistDto statusdto = new TasklistDto();
        try {
            if(status.equalsIgnoreCase("STATUS")){
                 sql  = "SELECT \n" +
                        "  COUNT(*) rowCount \n" +
                        " FROM\n" +
                        "  t_task_dtls a \n" +
                        " WHERE a.Application_Id = ? \n" +
                        "  AND a.Current_Task_Stage_Id NOT IN ('TS003','TS007') \n" +
                        "  AND a.Task_Id IN (18, 19, 3, 4)";
            }
        Query query = sqlQuery(sql, TasklistDto.class).setParameter(1,applicationNo);
        statusdto = (TasklistDto) query.list().get(0);
    }catch (Exception e){
        System.out.print("Exception in CommonDao # getMyStage: " + e);
    }
    return statusdto;

    }

    @Transactional
    public Object getHoldStatus(String applicationNo, String taskId, String holdstatus) {
        TasklistDto status = new TasklistDto();
        try
        {
            String sql = null;
            if(holdstatus.equalsIgnoreCase("HOLDSTATUS"))
            {
                sql = "SELECT \n" +
                        "  d.Task_Stage_Name,\n" +
                        " a.Application_Id applicationNo,\n" +
                        " a.Task_Id\n" +
                        "FROM\n" +
                        " t_task_dtls a\n" +
                        "  LEFT JOIN t_task_stages d\n" +
                        "    ON a.Current_Task_Stage_Id = d.Task_Stage_Id\n" +
                        "   WHERE a.Application_Id= ? \n" +
                        "   AND a.Task_Id = ?\n" +
                        "  AND a.Current_Task_Stage_Id IN ('TS005','TS002','TS009')";
            }
            Query query = sqlQuery(sql, TasklistDto.class).setParameter(1,applicationNo)
                    .setParameter(2, taskId);
            status = (TasklistDto) query.list().get(0);
        }
        catch (Exception e)
        {
            System.out.print("Exception in CommonDao # getMyStage: " + e);
        }
        return status;
    }

    @Transactional
    public String unHoldTasklistApplication(String applicationNo, String taskId, String user_name) {
        String result = "Failed";
        try {
            Query query1 = sqlQuery("UPDATE t_task_dtls a SET" +
                    " a.Modified_On =CURRENT_DATE,Modified_By='"+user_name+"' " +
                    ",a.Current_Task_Stage_Id='TS009' WHERE " +
                    "a.Application_Id ='" + applicationNo + "' " +
                    "AND " +
                    "a.Task_Id ='" + taskId + "'");
            query1.executeUpdate();
        } catch (Exception e) {
            System.out.print("Exception in CommonDao # unHoldTasklistApplication: " + e);
        }
        return result;
    }

    @Transactional
    public List<BuildingDto> countApplications(String locationID, String countapplication) {
        List<BuildingDto> dtoCount = new ArrayList<BuildingDto>();
        String sql=null;
        try{
            if (countapplication.equalsIgnoreCase("ArchitectCount")){
                 sql = "SELECT COUNT(*) AS rowCountTotal, (SELECT COUNT(*) FROM t_task_dtls a WHERE a.Task_Id = 15 AND a.Current_Task_Stage_Id ='TS001' AND a.juris_id=?) AS rowCountInitiated, (SELECT COUNT(*) FROM t_task_dtls a WHERE a.Task_Id = 15 AND a.Current_Task_Stage_Id IN ('TS002', 'TS009') AND a.juris_id=?) AS rowCountClaimed, (SELECT COUNT(*) FROM t_task_dtls a WHERE a.Task_Id = 15 AND a.Current_Task_Stage_Id = 'TS003' AND a.juris_id=?) AS rowCountApproved, (SELECT COUNT(*) FROM t_task_dtls a WHERE a.Task_Id = 15 AND a.Current_Task_Stage_Id = 'TS004' AND a.juris_id=?) AS rowCountRejected, (SELECT COUNT(*) FROM t_task_dtls a WHERE a.Task_Id = 15 AND a.Current_Task_Stage_Id = 'TS005' AND a.juris_id=?) AS rowCountHold, (SELECT COUNT(*) FROM t_task_dtls a WHERE a.Task_Id = 15 AND a.Current_Task_Stage_Id = 'TS007' AND a.juris_id=?) AS rowCountForwarded FROM t_task_dtls a WHERE a.Task_Id = 15 AND a.juris_id = ?";
            }
            else if (countapplication.equalsIgnoreCase("ElectricalCount")){
                 sql = "SELECT COUNT(*) AS rowCountTotal, (SELECT COUNT(*) FROM t_task_dtls a WHERE a.Task_Id IN (3, 23) AND a.Current_Task_Stage_Id ='TS001' AND a.juris_id=?) AS rowCountInitiated, (SELECT COUNT(*) FROM t_task_dtls a WHERE a.Task_Id IN (3, 23) AND a.Current_Task_Stage_Id IN ('TS002', 'TS009') AND a.juris_id=?) AS rowCountClaimed, (SELECT COUNT(*) FROM t_task_dtls a WHERE a.Task_Id IN (3, 23) AND a.Current_Task_Stage_Id = 'TS003' AND a.juris_id=?) AS rowCountApproved, (SELECT COUNT(*) FROM t_task_dtls a WHERE a.Task_Id IN (3, 23) AND a.Current_Task_Stage_Id = 'TS004' AND a.juris_id=?) AS rowCountRejected, (SELECT COUNT(*) FROM t_task_dtls a WHERE a.Task_Id IN (3, 23) AND a.Current_Task_Stage_Id = 'TS005' AND a.juris_id=?) AS rowCountHold, (SELECT COUNT(*) FROM t_task_dtls a WHERE a.Task_Id IN (3, 23) AND a.Current_Task_Stage_Id = 'TS007' AND a.juris_id=?) AS rowCountForwarded FROM t_task_dtls a WHERE a.Task_Id IN (3, 23) AND a.juris_id = ?";
            }
            else if (countapplication.equalsIgnoreCase("DESelectricalCount")){
                 sql = "SELECT \n" +
                         "  COUNT(*) AS rowCountTotal,\n" +
                         " (SELECT \n" +
                         "    COUNT(*) \n" +
                         "  FROM\n" +
                         "    t_task_dtls a \n" +
                         "  WHERE a.Task_Id = 19 \n" +
                         "    AND a.Current_Task_Stage_Id ='TS001'" +
                         "  AND a.juris_id=?) AS rowCountInitiated,"+
                         "  (SELECT \n" +
                         "    COUNT(*) \n" +
                         "  FROM\n" +
                         "    t_task_dtls a \n" +
                         "  WHERE a.Task_Id = 19 \n" +
                         "    AND a.Current_Task_Stage_Id IN ('TS002', 'TS009')" +
                         "  AND a.juris_id=?) AS rowCountClaimed,\n" +
                         "  (SELECT \n" +
                         "    COUNT(*) \n" +
                         "  FROM\n" +
                         "    t_task_dtls a \n" +
                         "  WHERE a.Task_Id = 19 \n" +
                         "    AND a.Current_Task_Stage_Id = 'TS003'" +
                         "  AND a.juris_id=?) AS rowCountApproved,\n" +
                         "  (SELECT \n" +
                         "    COUNT(*) \n" +
                         "  FROM\n" +
                         "    t_task_dtls a \n" +
                         "  WHERE a.Task_Id = 19 \n" +
                         "    AND a.Current_Task_Stage_Id = 'TS004'" +
                         "  AND a.juris_id=?) AS rowCountRejected,\n" +
                         "  (SELECT \n" +
                         "    COUNT(*) \n" +
                         "  FROM\n" +
                         "    t_task_dtls a \n" +
                         "  WHERE a.Task_Id = 19 \n" +
                         "    AND a.Current_Task_Stage_Id = 'TS005'" +
                         "  AND a.juris_id=?) AS rowCountHold,\n" +
                         "  (SELECT \n" +
                         "    COUNT(*) \n" +
                         "  FROM\n" +
                         "    t_task_dtls a \n" +
                         "  WHERE a.Task_Id = 19 \n" +
                         "    AND a.Current_Task_Stage_Id = 'TS007'" +
                         "  AND a.juris_id=?) AS rowCountForwarded \n" +
                         "FROM\n" +
                         "  t_task_dtls a \n" +
                         "WHERE a.Task_Id = 19 \n" +
                         "AND a.juris_id = ?";
            }
            else if (countapplication.equalsIgnoreCase("DESarchitectCount")){
                 sql = "SELECT \n" +
                         "  COUNT(*) AS rowCountTotal,\n" +
                         " (SELECT \n" +
                         "    COUNT(*) \n" +
                         "  FROM\n" +
                         "    t_task_dtls a \n" +
                         "  WHERE a.Task_Id IN (17, 20) \n" +
                         "    AND a.Current_Task_Stage_Id ='TS001'" +
                         "  AND a.juris_id=?) AS rowCountInitiated,"+
                         "  (SELECT \n" +
                         "    COUNT(*) \n" +
                         "  FROM\n" +
                         "    t_task_dtls a \n" +
                         "  WHERE a.Task_Id IN (17, 20) \n" +
                         "    AND a.Current_Task_Stage_Id IN ('TS002', 'TS009')" +
                         "  AND a.juris_id=?) AS rowCountClaimed,\n" +
                         "  (SELECT \n" +
                         "    COUNT(*) \n" +
                         "  FROM\n" +
                         "    t_task_dtls a \n" +
                         "  WHERE a.Task_Id IN (17, 20) \n" +
                         "    AND a.Current_Task_Stage_Id = 'TS003'" +
                         "  AND a.juris_id=?) AS rowCountApproved,\n" +
                         "  (SELECT \n" +
                         "    COUNT(*) \n" +
                         "  FROM\n" +
                         "    t_task_dtls a \n" +
                         "  WHERE a.Task_Id IN (17, 20) \n" +
                         "    AND a.Current_Task_Stage_Id = 'TS004'" +
                         "  AND a.juris_id=?) AS rowCountRejected,\n" +
                         "  (SELECT \n" +
                         "    COUNT(*) \n" +
                         "  FROM\n" +
                         "    t_task_dtls a \n" +
                         "  WHERE a.Task_Id IN (17, 20) \n" +
                         "    AND a.Current_Task_Stage_Id = 'TS005'" +
                         "  AND a.juris_id=?) AS rowCountHold,\n" +
                         "  (SELECT \n" +
                         "    COUNT(*) \n" +
                         "  FROM\n" +
                         "    t_task_dtls a \n" +
                         "  WHERE a.Task_Id IN (17, 20) \n" +
                         "    AND a.Current_Task_Stage_Id = 'TS007'" +
                         "  AND a.juris_id=?) AS rowCountForwarded \n" +
                         "FROM\n" +
                         "  t_task_dtls a \n" +
                         "WHERE a.Task_Id IN (17, 20) \n" +
                         "AND a.juris_id = ?";
            }
            else if (countapplication.equalsIgnoreCase("DESstructuralCount")){
                 sql = "SELECT \n" +
                         "  COUNT(*) AS rowCountTotal,\n" +
                         " (SELECT \n" +
                         "    COUNT(*) \n" +
                         "  FROM\n" +
                         "    t_task_dtls a \n" +
                         "  WHERE a.Task_Id = 18 \n" +
                         "    AND a.Current_Task_Stage_Id ='TS001'" +
                         "  AND a.juris_id=?) AS rowCountInitiated,"+
                         "  (SELECT \n" +
                         "    COUNT(*) \n" +
                         "  FROM\n" +
                         "    t_task_dtls a \n" +
                         "  WHERE a.Task_Id = 18 \n" +
                         "    AND a.Current_Task_Stage_Id IN ('TS002', 'TS009')" +
                         "  AND a.juris_id=?) AS rowCountClaimed,\n" +
                         "  (SELECT \n" +
                         "    COUNT(*) \n" +
                         "  FROM\n" +
                         "    t_task_dtls a \n" +
                         "  WHERE a.Task_Id = 18 \n" +
                         "    AND a.Current_Task_Stage_Id = 'TS003'" +
                         "  AND a.juris_id=?) AS rowCountApproved,\n" +
                         "  (SELECT \n" +
                         "    COUNT(*) \n" +
                         "  FROM\n" +
                         "    t_task_dtls a \n" +
                         "  WHERE a.Task_Id = 18 \n" +
                         "    AND a.Current_Task_Stage_Id = 'TS004'" +
                         "  AND a.juris_id=?) AS rowCountRejected,\n" +
                         "  (SELECT \n" +
                         "    COUNT(*) \n" +
                         "  FROM\n" +
                         "    t_task_dtls a \n" +
                         "  WHERE a.Task_Id = 18 \n" +
                         "    AND a.Current_Task_Stage_Id = 'TS005'" +
                         "  AND a.juris_id=?) AS rowCountHold,\n" +
                         "  (SELECT \n" +
                         "    COUNT(*) \n" +
                         "  FROM\n" +
                         "    t_task_dtls a \n" +
                         "  WHERE a.Task_Id = 18 \n" +
                         "    AND a.Current_Task_Stage_Id = 'TS007'" +
                         "  AND a.juris_id=?) AS rowCountForwarded \n" +
                         "FROM\n" +
                         "  t_task_dtls a \n" +
                         "WHERE a.Task_Id = 18 \n" +
                         "AND a.juris_id = ?";
            }
            else if (countapplication.equalsIgnoreCase("StructuralCount")){
                 sql = "SELECT \n" +
                         "  COUNT(*) AS rowCountTotal,\n" +
                         " (SELECT \n" +
                         "    COUNT(*) \n" +
                         "  FROM\n" +
                         "    t_task_dtls a \n" +
                         "  WHERE a.Task_Id IN (4,22) \n" +
                         "    AND a.Current_Task_Stage_Id ='TS001'" +
                         "  AND a.juris_id=?) AS rowCountInitiated," +
                         "  (SELECT \n" +
                         "    COUNT(*) \n" +
                         "  FROM\n" +
                         "    t_task_dtls a \n" +
                         "  WHERE a.Task_Id IN (4,22) \n" +
                         "    AND a.Current_Task_Stage_Id IN ('TS002', 'TS009')" +
                         "  AND a.juris_id=?) AS rowCountClaimed,\n" +
                         "  (SELECT \n" +
                         "    COUNT(*) \n" +
                         "  FROM\n" +
                         "    t_task_dtls a \n" +
                         "  WHERE a.Task_Id IN (4,22) \n" +
                         "    AND a.Current_Task_Stage_Id = 'TS003'" +
                         "  AND a.juris_id=?) AS rowCountApproved,\n" +
                         "  (SELECT \n" +
                         "    COUNT(*) \n" +
                         "  FROM\n" +
                         "    t_task_dtls a \n" +
                         "  WHERE a.Task_Id IN (4,22) \n" +
                         "    AND a.Current_Task_Stage_Id = 'TS004'" +
                         "  AND a.juris_id=?) AS rowCountRejected,\n" +
                         "  (SELECT \n" +
                         "    COUNT(*) \n" +
                         "  FROM\n" +
                         "    t_task_dtls a \n" +
                         "  WHERE a.Task_Id IN (4,22) \n" +
                         "    AND a.Current_Task_Stage_Id = 'TS005'" +
                         "  AND a.juris_id=?) AS rowCountHold,\n" +
                         "  (SELECT \n" +
                         "    COUNT(*) \n" +
                         "  FROM\n" +
                         "    t_task_dtls a \n" +
                         "  WHERE a.Task_Id IN (4,22) \n" +
                         "    AND a.Current_Task_Stage_Id = 'TS007'" +
                         "  AND a.juris_id=?) AS rowCountForwarded \n" +
                         "FROM\n" +
                         "  t_task_dtls a \n" +
                         "WHERE a.Task_Id IN (4,22) \n" +
                         "AND a.juris_id = ?";
            }
            else if (countapplication.equalsIgnoreCase("DesOperatorCount")){
                 sql = "SELECT \n" +
                         "  COUNT(*) AS rowCountTotal,\n" +
                         " (SELECT \n" +
                         "    COUNT(*) \n" +
                         "  FROM\n" +
                         "    t_task_dtls a \n" +
                         "  WHERE a.Task_Id IN (16,26,27,28) \n" +
                         "    AND a.Current_Task_Stage_Id ='TS001'" +
                         "  AND a.juris_id=?) AS rowCountInitiated,"+
                         "  (SELECT \n" +
                         "    COUNT(*) \n" +
                         "  FROM\n" +
                         "    t_task_dtls a \n" +
                         "  WHERE a.Task_Id IN (16,26,27,28) \n" +
                         "    AND a.Current_Task_Stage_Id IN ('TS002', 'TS009')" +
                         "  AND a.juris_id=?) AS rowCountClaimed,\n" +
                         "  (SELECT \n" +
                         "    COUNT(*) \n" +
                         "  FROM\n" +
                         "    t_task_dtls a \n" +
                         "  WHERE a.Task_Id IN (16,26,27,28) \n" +
                         "    AND a.Current_Task_Stage_Id = 'TS003'" +
                         "  AND a.juris_id=?) AS rowCountApproved,\n" +
                         "  (SELECT \n" +
                         "    COUNT(*) \n" +
                         "  FROM\n" +
                         "    t_task_dtls a \n" +
                         "  WHERE a.Task_Id IN (16,26,27,28) \n" +
                         "    AND a.Current_Task_Stage_Id = 'TS004'" +
                         "  AND a.juris_id=?) AS rowCountRejected,\n" +
                         "  (SELECT \n" +
                         "    COUNT(*) \n" +
                         "  FROM\n" +
                         "    t_task_dtls a \n" +
                         "  WHERE a.Task_Id IN (16,26,27,28) \n" +
                         "    AND a.Current_Task_Stage_Id = 'TS005'" +
                         "  AND a.juris_id=?) AS rowCountHold,\n" +
                         "  (SELECT \n" +
                         "    COUNT(*) \n" +
                         "  FROM\n" +
                         "    t_task_dtls a \n" +
                         "  WHERE a.Task_Id IN (16,26,27,28) \n" +
                         "    AND a.Current_Task_Stage_Id = 'TS007'" +
                         "  AND a.juris_id=?) AS rowCountForwarded \n" +
                         "FROM\n" +
                         "  t_task_dtls a \n" +
                         "WHERE a.Task_Id IN (16,26,27,28)\n" +
                         "AND a.juris_id = ?";
            }
            else if (countapplication.equalsIgnoreCase("DzoOperatorCount")){
                 sql = "SELECT \n" +
                         "  COUNT(*) AS rowCountTotal,\n" +
                         " (SELECT \n" +
                         "    COUNT(*) \n" +
                         "  FROM\n" +
                         "    t_task_dtls a \n" +
                         "  WHERE a.Task_Id IN (21,5) \n" +
                         "    AND a.Current_Task_Stage_Id ='TS001'" +
                         "  AND a.juris_id=?) AS rowCountInitiated,"+
                         "  (SELECT \n" +
                         "    COUNT(*) \n" +
                         "  FROM\n" +
                         "    t_task_dtls a \n" +
                         "  WHERE a.Task_Id IN (21,5) \n" +
                         "    AND a.Current_Task_Stage_Id IN ('TS002', 'TS009')" +
                         "  AND a.juris_id=?) AS rowCountClaimed,\n" +
                         "  (SELECT \n" +
                         "    COUNT(*) \n" +
                         "  FROM\n" +
                         "    t_task_dtls a \n" +
                         "  WHERE a.Task_Id IN (21,5) \n" +
                         "    AND a.Current_Task_Stage_Id = 'TS003'" +
                         "  AND a.juris_id=?) AS rowCountApproved,\n" +
                         "  (SELECT \n" +
                         "    COUNT(*) \n" +
                         "  FROM\n" +
                         "    t_task_dtls a \n" +
                         "  WHERE a.Task_Id IN (21,5) \n" +
                         "    AND a.Current_Task_Stage_Id = 'TS004'" +
                         "  AND a.juris_id=?) AS rowCountRejected,\n" +
                         "  (SELECT \n" +
                         "    COUNT(*) \n" +
                         "  FROM\n" +
                         "    t_task_dtls a \n" +
                         "  WHERE a.Task_Id IN (21,5) \n" +
                         "    AND a.Current_Task_Stage_Id = 'TS005'" +
                         "  AND a.juris_id=?) AS rowCountHold,\n" +
                         "  (SELECT \n" +
                         "    COUNT(*) \n" +
                         "  FROM\n" +
                         "    t_task_dtls a \n" +
                         "  WHERE a.Task_Id IN (21,5) \n" +
                         "    AND a.Current_Task_Stage_Id = 'TS007'" +
                         "  AND a.juris_id=?) AS rowCountForwarded \n" +
                         "FROM\n" +
                         "  t_task_dtls a \n" +
                         "WHERE a.Task_Id IN (21,5) \n" +
                         "AND a.juris_id =?";
            }
            Query query = sqlQuery(sql, BuildingDto.class).setParameter(1, locationID).setParameter(2,locationID).setParameter(3,locationID)
                    .setParameter(4, locationID).setParameter(5, locationID).setParameter(6,locationID).setParameter(7,locationID);
            dtoCount = query.list();
        }catch (Exception e){
            System.out.print("Exception in CommonThromdeDao # updateTaskList: " + e);

        }
        return dtoCount;
    }

    @Transactional
    public int getLocationId(String application_id) {
        int juris_id = 0;
        BuildingDto dto = new BuildingDto();
        try {
            String sql = "SELECT juris_id FROM t_building_application_details a WHERE a.Application_Id='" + application_id + "'";
            Query query = sqlQuery(sql, BuildingDto.class);
            dto = (BuildingDto) query.list().get(0);
            juris_id = dto.getJuris_id();
        }
        catch (Exception e){
            System.out.print("Exception in CommonDao # getLocationID: " + e);
        }
        return juris_id;
    }

    @Override
    public DropDownDto getDzongkhagByUserLocation(String locationID) {
        DropDownDto dto = new DropDownDto();
        try {
            String sql = "SELECT d.Dzongkhag_Serial_No dropdownId,d.Dzongkhag_Name dropdownName FROM t_dzongkhag_lookup d WHERE d.Dzongkhag_Serial_No=?";
            Query query = sqlQuery(sql, DropDownDto.class).setParameter(1,locationID);
            dto = (DropDownDto) query.list().get(0);
        }
        catch (Exception e){
            System.out.print("Exception in CommonDao # getLocationID: " + e);
        }
        return dto;
    }


    @Transactional
    public int countCheck(String application_id, int juris_type) {
        int count = 0;
        try {
            BuildingDto dto = new BuildingDto();
            String sql ="SELECT " +
                    "  COUNT(*) rowCount " +
                    " FROM " +
                    "  t_task_dtls a " +
                    " WHERE a.Application_Id =? " +
                    "  AND a.Current_Task_Stage_Id IN ('TS001','TS002', 'TS005')" +
                    "  AND a.Task_Id IN (15,3,4)";
            Query query = sqlQuery(sql, BuildingDto.class)
                    .setParameter(1, application_id);
            dto =  (BuildingDto) query.list().get(0);
            count = dto.getRowCount().intValue();
        } catch (Exception ex) {
            System.out.print("Exception in CommonThromdeDao # updateTaskList: " + ex);
        }
        return count;
    }

    @Transactional
    public int checkDesArchitect(String application_id) {
        BuildingDto dto = new BuildingDto();
        int count = 0;
        try {
            String sql ="SELECT COUNT(*) rowCount FROM t_task_dtls a WHERE a.Application_Id=? AND a.Task_Id='17'";
            Query query = sqlQuery(sql, BuildingDto.class)
                    .setParameter(1, application_id);
            dto =  (BuildingDto) query.list().get(0);
            count = dto.getRowCount().intValue();

        } catch (Exception ex) {

            System.out.print("Exception in CommonThromdeDao # updateTaskList: " + ex);
        }

        return count;
    }

    @Transactional
    public List<BuildingDto> getReport(String usr_role, String from_date, String to_date) {
        List<BuildingDto> reportDto = new ArrayList<BuildingDto>();
        if (usr_role.equalsIgnoreCase("DZO_ARCHITECT")) {
            try {
                String query = "SELECT \n" +
                        "  a.Application_Id,\n" +
                        "  (SELECT \n" +
                        "    GROUP_CONCAT(l.Owner_Name) \n" +
                        "  FROM\n" +
                        "    t_building_ownership_details l \n" +
                        "  WHERE l.Application_Id = d.Application_Id) AS Owner_Name,\n" +
                        "  IF(\n" +
                        "    d.Current_Task_Stage_Id = 'TS001',\n" +
                        "    DATE_FORMAT(d.Created_on, '%d/%m/%Y'),\n" +
                        "    (SELECT \n" +
                        "      DATE_FORMAT(k.Created_on, '%d/%m/%Y') \n" +
                        "    FROM\n" +
                        "      t_task_dtls_audit k \n" +
                        "    WHERE k.Application_Id = d.Application_Id \n" +
                        "      AND k.Task_Id = d.Task_Id \n" +
                        "      AND k.Current_Task_Stage_Id = 'TS001' \n" +
                        "    ORDER BY k.Modified_On DESC \n" +
                        "    LIMIT 1)\n" +
                        "  ) AS stringCreated_on,\n" +
                        "  IF(\n" +
                        "    d.Current_Task_Stage_Id = 'TS002',\n" +
                        "    DATE_FORMAT(d.Modified_On, '%d/%m/%Y'),\n" +
                        "    (SELECT \n" +
                        "      DATE_FORMAT(k.Modified_On, '%d/%m/%Y') \n" +
                        "    FROM\n" +
                        "      t_task_dtls_audit k \n" +
                        "    WHERE k.Application_Id = d.Application_Id \n" +
                        "      AND k.Task_Id = d.Task_Id \n" +
                        "      AND k.Current_Task_Stage_Id = 'TS002' \n" +
                        "    ORDER BY k.Modified_On DESC \n" +
                        "    LIMIT 1)\n" +
                        "  ) AS stringClaimed_On,\n" +
                        "  i.Title AS projectTitle,\n" +
                        "  c.Building_Use_Name,\n" +
                        "  a.Number_of_Floor,\n" +
                        "  CONCAT(\n" +
                        "    h.Village_Name,\n" +
                        "    ', ',\n" +
                        "    g.Gewog_Name,\n" +
                        "    ', ',\n" +
                        "    f.Dzongkhag_Name\n" +
                        "  ) location_Id,\n" +
                        "  a.Thram_Nos,\n" +
                        "  a.Plot_Nos,\n" +
                        "  CONCAT(\n" +
                        "    a.Architect_Name\n" +
                        "  ) Architect_Name,\n" +
                        "  d.Modified_By,\n" +
                        "  IF(\n" +
                        "    (SELECT \n" +
                        "      COUNT(*) \n" +
                        "    FROM\n" +
                        "      t_task_dtls z \n" +
                        "    WHERE z.Application_Id = a.Application_Id \n" +
                        "      AND z.Current_Task_Stage_Id = 'TS004') > 0,\n" +
                        "    'Rejected',\n" +
                        "    e.Task_Stage_Name\n" +
                        "  ) Task_Stage_Name,\n" +
                        "  DATE_FORMAT(d.Modified_On, '%d/%m/%Y') AS stringModified_On,\n" +
                        "  IF(\n" +
                        "    d.Current_Task_Stage_Id = 'TS003',\n" +
                        "    DATE_FORMAT(d.Modified_On, '%d/%m/%Y'),\n" +
                        "    ''\n" +
                        "  ) AS stringApproved_On,\n" +
                        "  IF(\n" +
                        "    d.Current_Task_Stage_Id = 'TS004',\n" +
                        "    DATE_FORMAT(d.Modified_On, '%d/%m/%Y'),\n" +
                        "    ''\n" +
                        "  ) AS stringRejcted_On,\n" +
                        "  IF(\n" +
                        "    d.Current_Task_Stage_Id = 'TS005',\n" +
                        "    DATE_FORMAT(d.Modified_On, '%d/%m/%Y'),\n" +
                        "    (SELECT \n" +
                        "      DATE_FORMAT(k.Modified_On, '%d/%m/%Y') \n" +
                        "    FROM\n" +
                        "      t_task_dtls_audit k \n" +
                        "    WHERE k.Application_Id = d.Application_Id \n" +
                        "      AND k.Task_Id = d.Task_Id \n" +
                        "      AND k.Current_Task_Stage_Id = 'TS005' \n" +
                        "    ORDER BY DATE_FORMAT(k.Modified_On, '%d/%m/%Y') DESC \n" +
                        "    LIMIT 1)\n" +
                        "    ) AS stringHold_On,\n" +
                        "    IF(\n" +
                        "    d.Current_Task_Stage_Id = 'TS009',\n" +
                        "    DATE_FORMAT(d.Modified_On, '%d/%m/%Y'),\n" +
                        "    (SELECT \n" +
                        "      DATE_FORMAT(k.Modified_On, '%d/%m/%Y') \n" +
                        "    FROM\n" +
                        "      t_task_dtls_audit k \n" +
                        "    WHERE k.Application_Id = d.Application_Id \n" +
                        "      AND k.Task_Id = d.Task_Id \n" +
                        "      AND k.Current_Task_Stage_Id = 'TS009' \n" +
                        "    ORDER BY DATE_FORMAT(k.Modified_On, '%d/%m/%Y') DESC \n" +
                        "    LIMIT 1)\n" +
                        "  ) AS stringUnHold_On,\n" +
                        "   IF(\n" +
                        "    d.Current_Task_Stage_Id = 'TS007',\n" +
                        "    DATE_FORMAT(d.Modified_On, '%d/%m/%Y'),\n" +
                        "    (SELECT \n" +
                        "      DATE_FORMAT(k.Modified_On, '%d/%m/%Y') \n" +
                        "    FROM\n" +
                        "      t_task_dtls_audit k \n" +
                        "    WHERE k.Application_Id = d.Application_Id \n" +
                        "      AND k.Task_Id = d.Task_Id \n" +
                        "      AND k.Current_Task_Stage_Id = 'TS007' \n" +
                        "    ORDER BY DATE_FORMAT(k.Modified_On, '%d/%m/%Y') DESC \n" +
                        "    LIMIT 1)\n" +
                        "  ) AS stringForwarded_On,\n" +
                        "  (SELECT \n" +
                        "      k.Remarks\n" +
                        "    FROM\n" +
                        "      t_task_dtls_audit k \n" +
                        "    WHERE k.Application_Id = d.Application_Id \n" +
                        "      AND k.Task_Id = d.Task_Id \n" +
                        "      AND k.Current_Task_Stage_Id = 'TS009' \n" +
                        "   GROUP BY k.Current_Task_Stage_Id DESC\n" +
                        "    LIMIT 1) AS UnHoldReason,\n" +
                        "   (SELECT \n" +
                        "      k.Remarks\n" +
                        "    FROM\n" +
                        "      t_task_dtls_audit k \n" +
                        "    WHERE k.Application_Id = d.Application_Id \n" +
                        "      AND k.Task_Id = d.Task_Id \n" +
                        "      AND k.Current_Task_Stage_Id = 'TS005' \n" +
                        "   ) AS HoldReason,\n" +
                        "   (SELECT \n" +
                        "      k.Remarks\n" +
                        "    FROM\n" +
                        "      t_task_dtls_audit k \n" +
                        "    WHERE k.Application_Id = d.Application_Id \n" +
                        "      AND k.Task_Id = d.Task_Id \n" +
                        "      AND k.Current_Task_Stage_Id = 'TS004' \n" +
                        "   ) AS RejectReason,\n" +
                        "   (SELECT \n" +
                        "      k.Remarks\n" +
                        "    FROM\n" +
                        "      t_task_dtls_audit k \n" +
                        "    WHERE k.Application_Id = d.Application_Id \n" +
                        "      AND k.Task_Id = d.Task_Id \n" +
                        "      AND k.Current_Task_Stage_Id = 'TS007' \n" +
                        "   ) AS ForwardReason,\n" +
                        "   d.Remarks \n" +
                        "FROM\n" +
                        "  t_building_application_details a \n" +
                        "  LEFT JOIN t_building_use c \n" +
                        "    ON a.Building_Use_Id = c.Building_Use_Id \n" +
                        "  LEFT JOIN t_task_dtls d \n" +
                        "    ON d.Application_Id = a.Application_Id \n" +
                        "  LEFT JOIN t_task_stages e \n" +
                        "    ON e.Task_Stage_Id = d.Current_Task_Stage_Id \n" +
                        "  LEFT JOIN t_dzongkhag_lookup f \n" +
                        "    ON a.Dzonkhag_Id = f.Dzongkhag_Serial_No \n" +
                        "  LEFT JOIN t_gewog_lookup g \n" +
                        "    ON a.Gewog_Id = g.Gewog_Serial_No \n" +
                        "  LEFT JOIN t_village_master h \n" +
                        "    ON a.Village_Id = h.Village_Serial_No \n" +
                        "  LEFT JOIN t_project_title i \n" +
                        "    ON a.Project_Title_Id = i.Project_Title_Id \n" +
                        "  LEFT JOIN t_role k \n" +
                        "    ON d.Task_Id = k.role_id \n" +
                        "WHERE d.Created_on BETWEEN ? \n" +
                        "  AND ? \n" +
                        "  AND k.role = 'DZO_ARCHITECT' \n" +
                        "GROUP BY a.Application_Id \n" +
                        "ORDER BY d.Modified_On DESC ";

                Query query1 = sqlQuery(query, BuildingDto.class).setParameter(1, from_date).setParameter(2, to_date);
                reportDto = query1.list();
            } catch (Exception e) {
                System.out.print("Exception in CommonDao # ReportGeneration: " + e);
            }
        } else if (usr_role.equalsIgnoreCase("DZO_EE")) {
            try {
                String query = "SELECT \n" +
                        "  a.Application_Id,\n" +
                        "  (SELECT \n" +
                        "    GROUP_CONCAT(l.Owner_Name) \n" +
                        "  FROM\n" +
                        "    t_building_ownership_details l \n" +
                        "  WHERE l.Application_Id = d.Application_Id) AS Owner_Name,\n" +
                        "  IF(\n" +
                        "    d.Current_Task_Stage_Id = 'TS001',\n" +
                        "    DATE_FORMAT(d.Created_on, '%d/%m/%Y'),\n" +
                        "    (SELECT \n" +
                        "      DATE_FORMAT(k.Created_on, '%d/%m/%Y') \n" +
                        "    FROM\n" +
                        "      t_task_dtls_audit k \n" +
                        "    WHERE k.Application_Id = d.Application_Id \n" +
                        "      AND k.Task_Id = d.Task_Id \n" +
                        "      AND k.Current_Task_Stage_Id = 'TS001' \n" +
                        "    ORDER BY k.Modified_On DESC \n" +
                        "    LIMIT 1)\n" +
                        "  ) AS stringCreated_on,\n" +
                        "  IF(\n" +
                        "    d.Current_Task_Stage_Id = 'TS002',\n" +
                        "    DATE_FORMAT(d.Modified_On, '%d/%m/%Y'),\n" +
                        "    (SELECT \n" +
                        "      DATE_FORMAT(k.Modified_On, '%d/%m/%Y') \n" +
                        "    FROM\n" +
                        "      t_task_dtls_audit k \n" +
                        "    WHERE k.Application_Id = d.Application_Id \n" +
                        "      AND k.Task_Id = d.Task_Id \n" +
                        "      AND k.Current_Task_Stage_Id = 'TS002' \n" +
                        "    ORDER BY k.Modified_On DESC \n" +
                        "    LIMIT 1)\n" +
                        "  ) AS stringClaimed_On,\n" +
                        "  i.Title AS projectTitle,\n" +
                        "  c.Building_Use_Name,\n" +
                        "  a.Number_of_Floor,\n" +
                        "  CONCAT(\n" +
                        "    h.Village_Name,\n" +
                        "    ', ',\n" +
                        "    g.Gewog_Name,\n" +
                        "    ', ',\n" +
                        "    f.Dzongkhag_Name\n" +
                        "  ) location_Id,\n" +
                        "  a.Thram_Nos,\n" +
                        "  a.Plot_Nos,\n" +
                        "  CONCAT(\n" +
                        "    a.Electrical_Name\n" +
                        "  ) Electrical_Name,\n" +
                        "  d.Modified_By,\n" +
                        "  IF(\n" +
                        "    (SELECT \n" +
                        "      COUNT(*) \n" +
                        "    FROM\n" +
                        "      t_task_dtls z \n" +
                        "    WHERE z.Application_Id = a.Application_Id \n" +
                        "      AND z.Current_Task_Stage_Id = 'TS004') > 0,\n" +
                        "    'Rejected',\n" +
                        "    e.Task_Stage_Name\n" +
                        "  ) Task_Stage_Name,\n" +
                        "  DATE_FORMAT(d.Modified_On, '%d/%m/%Y') AS stringModified_On,\n" +
                        "  IF(\n" +
                        "    d.Current_Task_Stage_Id = 'TS003',\n" +
                        "    DATE_FORMAT(d.Modified_On, '%d/%m/%Y'),\n" +
                        "    ''\n" +
                        "  ) AS stringApproved_On,\n" +
                        "  IF(\n" +
                        "    d.Current_Task_Stage_Id = 'TS004',\n" +
                        "    DATE_FORMAT(d.Modified_On, '%d/%m/%Y'),\n" +
                        "    ''\n" +
                        "  ) AS stringRejcted_On,\n" +
                        "  IF(\n" +
                        "    d.Current_Task_Stage_Id = 'TS005',\n" +
                        "    DATE_FORMAT(d.Modified_On, '%d/%m/%Y'),\n" +
                        "    (SELECT \n" +
                        "      DATE_FORMAT(k.Modified_On, '%d/%m/%Y') \n" +
                        "    FROM\n" +
                        "      t_task_dtls_audit k \n" +
                        "    WHERE k.Application_Id = d.Application_Id \n" +
                        "      AND k.Task_Id = d.Task_Id \n" +
                        "      AND k.Current_Task_Stage_Id = 'TS005' \n" +
                        "    ORDER BY DATE_FORMAT(k.Modified_On, '%d/%m/%Y') DESC \n" +
                        "    LIMIT 1)\n" +
                        "  ) AS stringHold_On,\n" +
                        "  IF(\n" +
                        "    d.Current_Task_Stage_Id = 'TS009',\n" +
                        "    DATE_FORMAT(d.Modified_On, '%d/%m/%Y'),\n" +
                        "    (SELECT \n" +
                        "      DATE_FORMAT(k.Modified_On, '%d/%m/%Y') \n" +
                        "    FROM\n" +
                        "      t_task_dtls_audit k \n" +
                        "    WHERE k.Application_Id = d.Application_Id \n" +
                        "      AND k.Task_Id = d.Task_Id \n" +
                        "      AND k.Current_Task_Stage_Id = 'TS009' \n" +
                        "    ORDER BY DATE_FORMAT(k.Modified_On, '%d/%m/%Y') DESC \n" +
                        "    LIMIT 1)\n" +
                        "  ) AS stringUnHold_On,\n" +
                        "  IF(\n" +
                        "    d.Current_Task_Stage_Id = 'TS007',\n" +
                        "    DATE_FORMAT(d.Modified_On, '%d/%m/%Y'),\n" +
                        "    (SELECT \n" +
                        "      DATE_FORMAT(k.Modified_On, '%d/%m/%Y') \n" +
                        "    FROM\n" +
                        "      t_task_dtls_audit k \n" +
                        "    WHERE k.Application_Id = d.Application_Id \n" +
                        "      AND k.Task_Id = d.Task_Id \n" +
                        "      AND k.Current_Task_Stage_Id = 'TS007' \n" +
                        "    ORDER BY DATE_FORMAT(k.Modified_On, '%d/%m/%Y') DESC \n" +
                        "    LIMIT 1)\n" +
                        "  ) AS stringForwarded_On,\n" +
                        "  (SELECT \n" +
                        "      k.Remarks\n" +
                        "    FROM\n" +
                        "      t_task_dtls_audit k \n" +
                        "    WHERE k.Application_Id = d.Application_Id \n" +
                        "      AND k.Task_Id = d.Task_Id \n" +
                        "      AND k.Current_Task_Stage_Id = 'TS009' \n" +
                        "   GROUP BY k.Current_Task_Stage_Id DESC\n" +
                        "    LIMIT 1) AS UnHoldReason,\n" +
                        "   (SELECT \n" +
                        "      k.Remarks\n" +
                        "    FROM\n" +
                        "      t_task_dtls_audit k \n" +
                        "    WHERE k.Application_Id = d.Application_Id \n" +
                        "      AND k.Task_Id = d.Task_Id \n" +
                        "      AND k.Current_Task_Stage_Id = 'TS005' \n" +
                        "   ) AS HoldReason,\n" +
                        "   (SELECT \n" +
                        "      k.Remarks\n" +
                        "    FROM\n" +
                        "      t_task_dtls_audit k \n" +
                        "    WHERE k.Application_Id = d.Application_Id \n" +
                        "      AND k.Task_Id = d.Task_Id \n" +
                        "      AND k.Current_Task_Stage_Id = 'TS004' \n" +
                        "   ) AS RejectReason,\n" +
                        "   (SELECT \n" +
                        "      k.Remarks\n" +
                        "    FROM\n" +
                        "      t_task_dtls_audit k \n" +
                        "    WHERE k.Application_Id = d.Application_Id \n" +
                        "      AND k.Task_Id = d.Task_Id \n" +
                        "      AND k.Current_Task_Stage_Id = 'TS007' \n" +
                        "   ) AS ForwardReason, \n" +
                        "  d.Remarks "+
                        "FROM\n" +
                        "  t_building_application_details a \n" +
                        "  LEFT JOIN t_building_use c \n" +
                        "    ON a.Building_Use_Id = c.Building_Use_Id \n" +
                        "  LEFT JOIN t_task_dtls d \n" +
                        "    ON d.Application_Id = a.Application_Id \n" +
                        "  LEFT JOIN t_task_stages e \n" +
                        "    ON e.Task_Stage_Id = d.Current_Task_Stage_Id \n" +
                        "  LEFT JOIN t_dzongkhag_lookup f \n" +
                        "    ON a.Dzonkhag_Id = f.Dzongkhag_Serial_No \n" +
                        "  LEFT JOIN t_gewog_lookup g \n" +
                        "    ON a.Gewog_Id = g.Gewog_Serial_No \n" +
                        "  LEFT JOIN t_village_master h \n" +
                        "    ON a.Village_Id = h.Village_Serial_No \n" +
                        "  LEFT JOIN t_project_title i \n" +
                        "    ON a.Project_Title_Id = i.Project_Title_Id \n" +
                        "  LEFT JOIN t_role k \n" +
                        "    ON d.Task_Id = k.role_id \n" +
                        "WHERE d.Created_on BETWEEN ? \n" +
                        "  AND ? \n" +
                        "  AND k.role = 'DZO_EE' \n" +
                        "GROUP BY a.Application_Id \n" +
                        "ORDER BY d.Modified_On DESC ";
                Query query1 = sqlQuery(query, BuildingDto.class).setParameter(1, from_date).setParameter(2, to_date);
                reportDto = query1.list();
            } catch (Exception e) {
                System.out.print("Exception in CommonDao # ReportGeneration: " + e);
            }
            } else if(usr_role.equalsIgnoreCase("DOES Architect")) {
                try {
                    String query = "SELECT \n" +
                            "  a.Application_Id,\n" +
                            "  (SELECT \n" +
                            "    GROUP_CONCAT(l.Owner_Name) \n" +
                            "  FROM\n" +
                            "    t_building_ownership_details l \n" +
                            "  WHERE l.Application_Id = d.Application_Id) AS Owner_Name,\n" +
                            "  IF(\n" +
                            "    d.Current_Task_Stage_Id = 'TS001',\n" +
                            "    DATE_FORMAT(d.Created_on, '%d/%m/%Y'),\n" +
                            "    (SELECT \n" +
                            "      DATE_FORMAT(k.Created_on, '%d/%m/%Y') \n" +
                            "    FROM\n" +
                            "      t_task_dtls_audit k \n" +
                            "    WHERE k.Application_Id = d.Application_Id \n" +
                            "      AND k.Task_Id = d.Task_Id \n" +
                            "      AND k.Current_Task_Stage_Id = 'TS001' \n" +
                            "    ORDER BY k.Modified_On DESC \n" +
                            "    LIMIT 1)\n" +
                            "  ) AS stringCreated_on,\n" +
                            "  IF(\n" +
                            "    d.Current_Task_Stage_Id = 'TS002',\n" +
                            "    DATE_FORMAT(d.Modified_On, '%d/%m/%Y'),\n" +
                            "    (SELECT \n" +
                            "      DATE_FORMAT(k.Modified_On, '%d/%m/%Y') \n" +
                            "    FROM\n" +
                            "      t_task_dtls_audit k \n" +
                            "    WHERE k.Application_Id = d.Application_Id \n" +
                            "      AND k.Task_Id = d.Task_Id \n" +
                            "      AND k.Current_Task_Stage_Id = 'TS002' \n" +
                            "    ORDER BY k.Modified_On DESC \n" +
                            "    LIMIT 1)\n" +
                            "  ) AS stringClaimed_On,\n" +
                            "  i.Title AS projectTitle,\n" +
                            "  c.Building_Use_Name,\n" +
                            "  a.Number_of_Floor,\n" +
                            "  CONCAT(\n" +
                            "    h.Village_Name,\n" +
                            "    ', ',\n" +
                            "    g.Gewog_Name,\n" +
                            "    ', ',\n" +
                            "    f.Dzongkhag_Name\n" +
                            "  ) location_Id,\n" +
                            "  a.Thram_Nos,\n" +
                            "  a.Plot_Nos,\n" +
                            "  CONCAT(\n" +
                            "    a.Architect_Name\n" +
                            "  ) Architect_Name,\n" +
                            "  d.Modified_By,\n" +
                            "  IF(\n" +
                            "    (SELECT \n" +
                            "      COUNT(*) \n" +
                            "    FROM\n" +
                            "      t_task_dtls z \n" +
                            "    WHERE z.Application_Id = a.Application_Id \n" +
                            "      AND z.Current_Task_Stage_Id = 'TS004') > 0,\n" +
                            "    'Rejected',\n" +
                            "    e.Task_Stage_Name\n" +
                            "  ) Task_Stage_Name,\n" +
                            "  DATE_FORMAT(d.Modified_On, '%d/%m/%Y') AS stringModified_On,\n" +
                            "  IF(\n" +
                            "    d.Current_Task_Stage_Id = 'TS003',\n" +
                            "    DATE_FORMAT(d.Modified_On, '%d/%m/%Y'),\n" +
                            "    ''\n" +
                            "  ) AS stringApproved_On,\n" +
                            "  IF(\n" +
                            "    d.Current_Task_Stage_Id = 'TS004',\n" +
                            "    DATE_FORMAT(d.Modified_On, '%d/%m/%Y'),\n" +
                            "    ''\n" +
                            "  ) AS stringRejcted_On,\n" +
                            "  IF(\n" +
                            "    d.Current_Task_Stage_Id = 'TS005',\n" +
                            "    DATE_FORMAT(d.Modified_On, '%d/%m/%Y'),\n" +
                            "    (SELECT \n" +
                            "      DATE_FORMAT(k.Modified_On, '%d/%m/%Y') \n" +
                            "    FROM\n" +
                            "      t_task_dtls_audit k \n" +
                            "    WHERE k.Application_Id = d.Application_Id \n" +
                            "      AND k.Task_Id = d.Task_Id \n" +
                            "      AND k.Current_Task_Stage_Id = 'TS005' \n" +
                            "    ORDER BY DATE_FORMAT(k.Modified_On, '%d/%m/%Y') DESC \n" +
                            "    LIMIT 1)\n" +
                            "  ) AS stringHold_On,\n" +
                            "  IF(\n" +
                            "    d.Current_Task_Stage_Id = 'TS009',\n" +
                            "    DATE_FORMAT(d.Modified_On, '%d/%m/%Y'),\n" +
                            "    (SELECT \n" +
                            "      DATE_FORMAT(k.Modified_On, '%d/%m/%Y') \n" +
                            "    FROM\n" +
                            "      t_task_dtls_audit k \n" +
                            "    WHERE k.Application_Id = d.Application_Id \n" +
                            "      AND k.Task_Id = d.Task_Id \n" +
                            "      AND k.Current_Task_Stage_Id = 'TS009' \n" +
                            "    ORDER BY DATE_FORMAT(k.Modified_On, '%d/%m/%Y') DESC \n" +
                            "    LIMIT 1)\n" +
                            "  ) AS stringUnHold_On,\n" +
                            "  (SELECT \n" +
                            "      k.Remarks\n" +
                            "    FROM\n" +
                            "      t_task_dtls_audit k \n" +
                            "    WHERE k.Application_Id = d.Application_Id \n" +
                            "      AND k.Task_Id = d.Task_Id \n" +
                            "      AND k.Current_Task_Stage_Id = 'TS009' \n" +
                            "   GROUP BY k.Current_Task_Stage_Id DESC\n" +
                            "    LIMIT 1) AS UnHoldReason,\n" +
                            "   (SELECT \n" +
                            "      k.Remarks\n" +
                            "    FROM\n" +
                            "      t_task_dtls_audit k \n" +
                            "    WHERE k.Application_Id = d.Application_Id \n" +
                            "      AND k.Task_Id = d.Task_Id \n" +
                            "      AND k.Current_Task_Stage_Id = 'TS005' \n" +
                            "   ) AS HoldReason,\n" +
                            "   (SELECT \n" +
                            "      k.Remarks\n" +
                            "    FROM\n" +
                            "      t_task_dtls_audit k \n" +
                            "    WHERE k.Application_Id = d.Application_Id \n" +
                            "      AND k.Task_Id = d.Task_Id \n" +
                            "      AND k.Current_Task_Stage_Id = 'TS004' \n" +
                            "   ) AS RejectReason,\n" +
                            "   (SELECT \n" +
                            "      k.Remarks\n" +
                            "    FROM\n" +
                            "      t_task_dtls_audit k \n" +
                            "    WHERE k.Application_Id = d.Application_Id \n" +
                            "      AND k.Task_Id = d.Task_Id \n" +
                            "      AND k.Current_Task_Stage_Id = 'TS007' \n" +
                            "   ) AS ForwardReason,\n" +
                            "   d.Remarks \n" +
                            "FROM\n" +
                            "  t_building_application_details a \n" +
                            "  LEFT JOIN t_building_use c \n" +
                            "    ON a.Building_Use_Id = c.Building_Use_Id \n" +
                            "  LEFT JOIN t_task_dtls d \n" +
                            "    ON d.Application_Id = a.Application_Id \n" +
                            "  LEFT JOIN t_task_stages e \n" +
                            "    ON e.Task_Stage_Id = d.Current_Task_Stage_Id \n" +
                            "  LEFT JOIN t_dzongkhag_lookup f \n" +
                            "    ON a.Dzonkhag_Id = f.Dzongkhag_Serial_No \n" +
                            "  LEFT JOIN t_gewog_lookup g \n" +
                            "    ON a.Gewog_Id = g.Gewog_Serial_No \n" +
                            "  LEFT JOIN t_village_master h \n" +
                            "    ON a.Village_Id = h.Village_Serial_No \n" +
                            "  LEFT JOIN t_project_title i \n" +
                            "    ON a.Project_Title_Id = i.Project_Title_Id \n" +
                            "  LEFT JOIN t_role k \n" +
                            "    ON d.Task_Id = k.role_id \n" +
                            "WHERE d.Created_on BETWEEN ? \n" +
                            "  AND ? \n" +
                            "  AND k.role = 'DOES Architect' \n" +
                            "GROUP BY a.Application_Id \n" +
                            "ORDER BY d.Modified_On DESC ";

                    Query query1 = sqlQuery(query, BuildingDto.class).setParameter(1, from_date).setParameter(2, to_date);
                    reportDto = query1.list();
                } catch (Exception e) {
                    System.out.print("Exception in CommonDao # ReportGeneration: " + e);
                }
            } else if(usr_role.equalsIgnoreCase("DOES Electrical Engineer")){
                try {
                    String query = "SELECT \n" +
                            "  a.Application_Id,\n" +
                            "  (SELECT \n" +
                            "    GROUP_CONCAT(l.Owner_Name) \n" +
                            "  FROM\n" +
                            "    t_building_ownership_details l \n" +
                            "  WHERE l.Application_Id = d.Application_Id) AS Owner_Name,\n" +
                            "  IF(\n" +
                            "    d.Current_Task_Stage_Id = 'TS001',\n" +
                            "    DATE_FORMAT(d.Created_on, '%d/%m/%Y'),\n" +
                            "    (SELECT \n" +
                            "      DATE_FORMAT(k.Created_on, '%d/%m/%Y') \n" +
                            "    FROM\n" +
                            "      t_task_dtls_audit k \n" +
                            "    WHERE k.Application_Id = d.Application_Id \n" +
                            "      AND k.Task_Id = d.Task_Id \n" +
                            "      AND k.Current_Task_Stage_Id = 'TS001' \n" +
                            "    ORDER BY k.Modified_On DESC \n" +
                            "    LIMIT 1)\n" +
                            "  ) AS stringCreated_on,\n" +
                            "  IF(\n" +
                            "    d.Current_Task_Stage_Id = 'TS002',\n" +
                            "    DATE_FORMAT(d.Modified_On, '%d/%m/%Y'),\n" +
                            "    (SELECT \n" +
                            "      DATE_FORMAT(k.Modified_On, '%d/%m/%Y') \n" +
                            "    FROM\n" +
                            "      t_task_dtls_audit k \n" +
                            "    WHERE k.Application_Id = d.Application_Id \n" +
                            "      AND k.Task_Id = d.Task_Id \n" +
                            "      AND k.Current_Task_Stage_Id = 'TS002' \n" +
                            "    ORDER BY k.Modified_On DESC \n" +
                            "    LIMIT 1)\n" +
                            "  ) AS stringClaimed_On,\n" +
                            "  i.Title AS projectTitle,\n" +
                            "  c.Building_Use_Name,\n" +
                            "  a.Number_of_Floor,\n" +
                            "  CONCAT(\n" +
                            "    h.Village_Name,\n" +
                            "    ', ',\n" +
                            "    g.Gewog_Name,\n" +
                            "    ', ',\n" +
                            "    f.Dzongkhag_Name\n" +
                            "  ) location_Id,\n" +
                            "  a.Thram_Nos,\n" +
                            "  a.Plot_Nos,\n" +
                            "  CONCAT(\n" +
                            "    a.Electrical_Name\n" +
                            "  ) Electrical_Name,\n" +
                            "  d.Modified_By,\n" +
                            "  IF(\n" +
                            "    (SELECT \n" +
                            "      COUNT(*) \n" +
                            "    FROM\n" +
                            "      t_task_dtls z \n" +
                            "    WHERE z.Application_Id = a.Application_Id \n" +
                            "      AND z.Current_Task_Stage_Id = 'TS004') > 0,\n" +
                            "    'Rejected',\n" +
                            "    e.Task_Stage_Name\n" +
                            "  ) Task_Stage_Name,\n" +
                            "  DATE_FORMAT(d.Modified_On, '%d/%m/%Y') AS stringModified_On,\n" +
                            "  IF(\n" +
                            "    d.Current_Task_Stage_Id = 'TS003',\n" +
                            "    DATE_FORMAT(d.Modified_On, '%d/%m/%Y'),\n" +
                            "    ''\n" +
                            "  ) AS stringApproved_On,\n" +
                            "  IF(\n" +
                            "    d.Current_Task_Stage_Id = 'TS004',\n" +
                            "    DATE_FORMAT(d.Modified_On, '%d/%m/%Y'),\n" +
                            "    ''\n" +
                            "  ) AS stringRejcted_On,\n" +
                            "  IF(\n" +
                            "    d.Current_Task_Stage_Id = 'TS005',\n" +
                            "    DATE_FORMAT(d.Modified_On, '%d/%m/%Y'),\n" +
                            "    (SELECT \n" +
                            "      DATE_FORMAT(k.Modified_On, '%d/%m/%Y') \n" +
                            "    FROM\n" +
                            "      t_task_dtls_audit k \n" +
                            "    WHERE k.Application_Id = d.Application_Id \n" +
                            "      AND k.Task_Id = d.Task_Id \n" +
                            "      AND k.Current_Task_Stage_Id = 'TS005' \n" +
                            "    ORDER BY DATE_FORMAT(k.Modified_On, '%d/%m/%Y') DESC \n" +
                            "    LIMIT 1)\n" +
                            "  ) AS stringHold_On,\n" +
                            "  IF(\n" +
                            "    d.Current_Task_Stage_Id = 'TS009',\n" +
                            "    DATE_FORMAT(d.Modified_On, '%d/%m/%Y'),\n" +
                            "    (SELECT \n" +
                            "      DATE_FORMAT(k.Modified_On, '%d/%m/%Y') \n" +
                            "    FROM\n" +
                            "      t_task_dtls_audit k \n" +
                            "    WHERE k.Application_Id = d.Application_Id \n" +
                            "      AND k.Task_Id = d.Task_Id \n" +
                            "      AND k.Current_Task_Stage_Id = 'TS009' \n" +
                            "    ORDER BY DATE_FORMAT(k.Modified_On, '%d/%m/%Y') DESC \n" +
                            "    LIMIT 1)\n" +
                            "  ) AS stringUnHold_On,\n" +
                            "  (SELECT \n" +
                            "      k.Remarks\n" +
                            "    FROM\n" +
                            "      t_task_dtls_audit k \n" +
                            "    WHERE k.Application_Id = d.Application_Id \n" +
                            "      AND k.Task_Id = d.Task_Id \n" +
                            "      AND k.Current_Task_Stage_Id = 'TS009' \n" +
                            "   GROUP BY k.Current_Task_Stage_Id DESC\n" +
                            "    LIMIT 1) AS UnHoldReason,\n" +
                            "   (SELECT \n" +
                            "      k.Remarks\n" +
                            "    FROM\n" +
                            "      t_task_dtls_audit k \n" +
                            "    WHERE k.Application_Id = d.Application_Id \n" +
                            "      AND k.Task_Id = d.Task_Id \n" +
                            "      AND k.Current_Task_Stage_Id = 'TS005' \n" +
                            "   ) AS HoldReason,\n" +
                            "   (SELECT \n" +
                            "      k.Remarks\n" +
                            "    FROM\n" +
                            "      t_task_dtls_audit k \n" +
                            "    WHERE k.Application_Id = d.Application_Id \n" +
                            "      AND k.Task_Id = d.Task_Id \n" +
                            "      AND k.Current_Task_Stage_Id = 'TS004' \n" +
                            "   ) AS RejectReason,\n" +
                            "   (SELECT \n" +
                            "      k.Remarks\n" +
                            "    FROM\n" +
                            "      t_task_dtls_audit k \n" +
                            "    WHERE k.Application_Id = d.Application_Id \n" +
                            "      AND k.Task_Id = d.Task_Id \n" +
                            "      AND k.Current_Task_Stage_Id = 'TS007' \n" +
                            "   ) AS ForwardReason,\n" +
                            "   d.Remarks \n" +
                            "FROM\n" +
                            "  t_building_application_details a \n" +
                            "  LEFT JOIN t_building_use c \n" +
                            "    ON a.Building_Use_Id = c.Building_Use_Id \n" +
                            "  LEFT JOIN t_task_dtls d \n" +
                            "    ON d.Application_Id = a.Application_Id \n" +
                            "  LEFT JOIN t_task_stages e \n" +
                            "    ON e.Task_Stage_Id = d.Current_Task_Stage_Id \n" +
                            "  LEFT JOIN t_dzongkhag_lookup f \n" +
                            "    ON a.Dzonkhag_Id = f.Dzongkhag_Serial_No \n" +
                            "  LEFT JOIN t_gewog_lookup g \n" +
                            "    ON a.Gewog_Id = g.Gewog_Serial_No \n" +
                            "  LEFT JOIN t_village_master h \n" +
                            "    ON a.Village_Id = h.Village_Serial_No \n" +
                            "  LEFT JOIN t_project_title i \n" +
                            "    ON a.Project_Title_Id = i.Project_Title_Id \n" +
                            "  LEFT JOIN t_role k \n" +
                            "    ON d.Task_Id = k.role_id \n" +
                            "WHERE d.Created_on BETWEEN ? \n" +
                            "  AND ? \n" +
                            "  AND k.role = 'DOES Electrical Engineer' \n" +
                            "GROUP BY a.Application_Id \n" +
                            "ORDER BY d.Modified_On DESC ";
                    Query query1 = sqlQuery(query, BuildingDto.class).setParameter(1, from_date).setParameter(2, to_date);
                    reportDto = query1.list();
                } catch (Exception e) {
                    System.out.print("Exception in CommonDao # ReportGeneration: " + e);
                }
            } else if(usr_role.equalsIgnoreCase("DZO_SE")){
            try {
                String query = "SELECT \n" +
                        "  a.Application_Id,\n" +
                        "  (SELECT \n" +
                        "    GROUP_CONCAT(l.Owner_Name) \n" +
                        "  FROM\n" +
                        "    t_building_ownership_details l \n" +
                        "  WHERE l.Application_Id = d.Application_Id) AS Owner_Name,\n" +
                        "  IF(\n" +
                        "    d.Current_Task_Stage_Id = 'TS001',\n" +
                        "    DATE_FORMAT(d.Created_on, '%d/%m/%Y'),\n" +
                        "    (SELECT \n" +
                        "      DATE_FORMAT(k.Created_on, '%d/%m/%Y') \n" +
                        "    FROM\n" +
                        "      t_task_dtls_audit k \n" +
                        "    WHERE k.Application_Id = d.Application_Id \n" +
                        "      AND k.Task_Id = d.Task_Id \n" +
                        "      AND k.Current_Task_Stage_Id = 'TS001' \n" +
                        "    ORDER BY k.Modified_On DESC \n" +
                        "    LIMIT 1)\n" +
                        "  ) AS stringCreated_on,\n" +
                        "  IF(\n" +
                        "    d.Current_Task_Stage_Id = 'TS002',\n" +
                        "    DATE_FORMAT(d.Modified_On, '%d/%m/%Y'),\n" +
                        "    (SELECT \n" +
                        "      DATE_FORMAT(k.Modified_On, '%d/%m/%Y') \n" +
                        "    FROM\n" +
                        "      t_task_dtls_audit k \n" +
                        "    WHERE k.Application_Id = d.Application_Id \n" +
                        "      AND k.Task_Id = d.Task_Id \n" +
                        "      AND k.Current_Task_Stage_Id = 'TS002' \n" +
                        "    ORDER BY k.Modified_On DESC \n" +
                        "    LIMIT 1)\n" +
                        "  ) AS stringClaimed_On,\n" +
                        "  i.Title AS projectTitle,\n" +
                        "  c.Building_Use_Name,\n" +
                        "  a.Number_of_Floor,\n" +
                        "  CONCAT(\n" +
                        "    h.Village_Name,\n" +
                        "    ', ',\n" +
                        "    g.Gewog_Name,\n" +
                        "    ', ',\n" +
                        "    f.Dzongkhag_Name\n" +
                        "  ) location_Id,\n" +
                        "  a.Thram_Nos,\n" +
                        "  a.Plot_Nos,\n" +
                        "  CONCAT(\n" +
                        "    a.Structural_Name\n" +
                        "  ) Structural_Name,\n" +
                        "  d.Modified_By,\n" +
                        "  IF(\n" +
                        "    (SELECT \n" +
                        "      COUNT(*) \n" +
                        "    FROM\n" +
                        "      t_task_dtls z \n" +
                        "    WHERE z.Application_Id = a.Application_Id \n" +
                        "      AND z.Current_Task_Stage_Id = 'TS004') > 0,\n" +
                        "    'Rejected',\n" +
                        "    e.Task_Stage_Name\n" +
                        "  ) Task_Stage_Name,\n" +
                        "  DATE_FORMAT(d.Modified_On, '%d/%m/%Y') AS stringModified_On,\n" +
                        "  IF(\n" +
                        "    d.Current_Task_Stage_Id = 'TS003',\n" +
                        "    DATE_FORMAT(d.Modified_On, '%d/%m/%Y'),\n" +
                        "    ''\n" +
                        "  ) AS stringApproved_On,\n" +
                        "  IF(\n" +
                        "    d.Current_Task_Stage_Id = 'TS004',\n" +
                        "    DATE_FORMAT(d.Modified_On, '%d/%m/%Y'),\n" +
                        "    ''\n" +
                        "  ) AS stringRejcted_On,\n" +
                        "  IF(\n" +
                        "    d.Current_Task_Stage_Id = 'TS005',\n" +
                        "    DATE_FORMAT(d.Modified_On, '%d/%m/%Y'),\n" +
                        "    (SELECT \n" +
                        "      DATE_FORMAT(k.Modified_On, '%d/%m/%Y') \n" +
                        "    FROM\n" +
                        "      t_task_dtls_audit k \n" +
                        "    WHERE k.Application_Id = d.Application_Id \n" +
                        "      AND k.Task_Id = d.Task_Id \n" +
                        "      AND k.Current_Task_Stage_Id = 'TS005' \n" +
                        "    ORDER BY DATE_FORMAT(k.Modified_On, '%d/%m/%Y') DESC \n" +
                        "    LIMIT 1)\n" +
                        "  ) AS stringHold_On,\n" +
                        "  IF(\n" +
                        "    d.Current_Task_Stage_Id = 'TS009',\n" +
                        "    DATE_FORMAT(d.Modified_On, '%d/%m/%Y'),\n" +
                        "    (SELECT \n" +
                        "      DATE_FORMAT(k.Modified_On, '%d/%m/%Y') \n" +
                        "    FROM\n" +
                        "      t_task_dtls_audit k \n" +
                        "    WHERE k.Application_Id = d.Application_Id \n" +
                        "      AND k.Task_Id = d.Task_Id \n" +
                        "      AND k.Current_Task_Stage_Id = 'TS009' \n" +
                        "    ORDER BY DATE_FORMAT(k.Modified_On, '%d/%m/%Y') DESC \n" +
                        "    LIMIT 1)\n" +
                        "  ) AS stringUnHold_On,\n" +
                        "  IF(\n" +
                        "    d.Current_Task_Stage_Id = 'TS007',\n" +
                        "    DATE_FORMAT(d.Modified_On, '%d/%m/%Y'),\n" +
                        "    (SELECT \n" +
                        "      DATE_FORMAT(k.Modified_On, '%d/%m/%Y') \n" +
                        "    FROM\n" +
                        "      t_task_dtls_audit k \n" +
                        "    WHERE k.Application_Id = d.Application_Id \n" +
                        "      AND k.Task_Id = d.Task_Id \n" +
                        "      AND k.Current_Task_Stage_Id = 'TS007' \n" +
                        "    ORDER BY DATE_FORMAT(k.Modified_On, '%d/%m/%Y') DESC \n" +
                        "    LIMIT 1)\n" +
                        "  ) AS stringForwarded_On,\n" +
                        "  (SELECT \n" +
                        "      k.Remarks\n" +
                        "    FROM\n" +
                        "      t_task_dtls_audit k \n" +
                        "    WHERE k.Application_Id = d.Application_Id \n" +
                        "      AND k.Task_Id = d.Task_Id \n" +
                        "      AND k.Current_Task_Stage_Id = 'TS009' \n" +
                        "   GROUP BY k.Current_Task_Stage_Id DESC\n" +
                        "    LIMIT 1) AS UnHoldReason,\n" +
                        "   (SELECT \n" +
                        "      k.Remarks\n" +
                        "    FROM\n" +
                        "      t_task_dtls_audit k \n" +
                        "    WHERE k.Application_Id = d.Application_Id \n" +
                        "      AND k.Task_Id = d.Task_Id \n" +
                        "      AND k.Current_Task_Stage_Id = 'TS005' \n" +
                        "   ) AS HoldReason,\n" +
                        "   (SELECT \n" +
                        "      k.Remarks\n" +
                        "    FROM\n" +
                        "      t_task_dtls_audit k \n" +
                        "    WHERE k.Application_Id = d.Application_Id \n" +
                        "      AND k.Task_Id = d.Task_Id \n" +
                        "      AND k.Current_Task_Stage_Id = 'TS004' \n" +
                        "   ) AS RejectReason,\n" +
                        "   (SELECT \n" +
                        "      k.Remarks\n" +
                        "    FROM\n" +
                        "      t_task_dtls_audit k \n" +
                        "    WHERE k.Application_Id = d.Application_Id \n" +
                        "      AND k.Task_Id = d.Task_Id \n" +
                        "      AND k.Current_Task_Stage_Id = 'TS007' \n" +
                        "   ) AS ForwardReason,\n" +
                        "   d.Remarks \n" +
                        "FROM\n" +
                        "  t_building_application_details a \n" +
                        "  LEFT JOIN t_building_use c \n" +
                        "    ON a.Building_Use_Id = c.Building_Use_Id \n" +
                        "  LEFT JOIN t_task_dtls d \n" +
                        "    ON d.Application_Id = a.Application_Id \n" +
                        "  LEFT JOIN t_task_stages e \n" +
                        "    ON e.Task_Stage_Id = d.Current_Task_Stage_Id \n" +
                        "  LEFT JOIN t_dzongkhag_lookup f \n" +
                        "    ON a.Dzonkhag_Id = f.Dzongkhag_Serial_No \n" +
                        "  LEFT JOIN t_gewog_lookup g \n" +
                        "    ON a.Gewog_Id = g.Gewog_Serial_No \n" +
                        "  LEFT JOIN t_village_master h \n" +
                        "    ON a.Village_Id = h.Village_Serial_No \n" +
                        "  LEFT JOIN t_project_title i \n" +
                        "    ON a.Project_Title_Id = i.Project_Title_Id \n" +
                        "  LEFT JOIN t_role k \n" +
                        "    ON d.Task_Id = k.role_id \n" +
                        "WHERE d.Created_on BETWEEN ? \n" +
                        "  AND ? \n" +
                        "  AND k.role = 'DZO_SE' \n" +
                        "GROUP BY a.Application_Id \n" +
                        "ORDER BY d.Modified_On DESC ";
                Query query1 = sqlQuery(query, BuildingDto.class).setParameter(1, from_date).setParameter(2, to_date);
                reportDto = query1.list();
            } catch (Exception e) {
                System.out.print("Exception in CommonDao # ReportGeneration: " + e);
            }

        } else if (usr_role.equalsIgnoreCase("DOES Structural Engineer")){
            try {
                String query = "SELECT \n" +
                        "  a.Application_Id,\n" +
                        "  (SELECT \n" +
                        "    GROUP_CONCAT(l.Owner_Name) \n" +
                        "  FROM\n" +
                        "    t_building_ownership_details l \n" +
                        "  WHERE l.Application_Id = d.Application_Id) AS Owner_Name,\n" +
                        "  IF(\n" +
                        "    d.Current_Task_Stage_Id = 'TS001',\n" +
                        "    DATE_FORMAT(d.Created_on, '%d/%m/%Y'),\n" +
                        "    (SELECT \n" +
                        "      DATE_FORMAT(k.Created_on, '%d/%m/%Y') \n" +
                        "    FROM\n" +
                        "      t_task_dtls_audit k \n" +
                        "    WHERE k.Application_Id = d.Application_Id \n" +
                        "      AND k.Task_Id = d.Task_Id \n" +
                        "      AND k.Current_Task_Stage_Id = 'TS001' \n" +
                        "    ORDER BY k.Modified_On DESC \n" +
                        "    LIMIT 1)\n" +
                        "  ) AS stringCreated_on,\n" +
                        "  IF(\n" +
                        "    d.Current_Task_Stage_Id = 'TS002',\n" +
                        "    DATE_FORMAT(d.Modified_On, '%d/%m/%Y'),\n" +
                        "    (SELECT \n" +
                        "      DATE_FORMAT(k.Modified_On, '%d/%m/%Y') \n" +
                        "    FROM\n" +
                        "      t_task_dtls_audit k \n" +
                        "    WHERE k.Application_Id = d.Application_Id \n" +
                        "      AND k.Task_Id = d.Task_Id \n" +
                        "      AND k.Current_Task_Stage_Id = 'TS002' \n" +
                        "    ORDER BY k.Modified_On DESC \n" +
                        "    LIMIT 1)\n" +
                        "  ) AS stringClaimed_On,\n" +
                        "  i.Title AS projectTitle,\n" +
                        "  c.Building_Use_Name,\n" +
                        "  a.Number_of_Floor,\n" +
                        "  CONCAT(\n" +
                        "    h.Village_Name,\n" +
                        "    ', ',\n" +
                        "    g.Gewog_Name,\n" +
                        "    ', ',\n" +
                        "    f.Dzongkhag_Name\n" +
                        "  ) location_Id,\n" +
                        "  a.Thram_Nos,\n" +
                        "  a.Plot_Nos,\n" +
                        "  CONCAT(\n" +
                        "    a.Structural_Name\n" +
                        "  ) Structural_Name,\n" +
                        "  d.Modified_By,\n" +
                        "  IF(\n" +
                        "    (SELECT \n" +
                        "      COUNT(*) \n" +
                        "    FROM\n" +
                        "      t_task_dtls z \n" +
                        "    WHERE z.Application_Id = a.Application_Id \n" +
                        "      AND z.Current_Task_Stage_Id = 'TS004') > 0,\n" +
                        "    'Rejected',\n" +
                        "    e.Task_Stage_Name\n" +
                        "  ) Task_Stage_Name,\n" +
                        "  DATE_FORMAT(d.Modified_On, '%d/%m/%Y') AS stringModified_On,\n" +
                        "  IF(\n" +
                        "    d.Current_Task_Stage_Id = 'TS003',\n" +
                        "    DATE_FORMAT(d.Modified_On, '%d/%m/%Y'),\n" +
                        "    ''\n" +
                        "  ) AS stringApproved_On,\n" +
                        "  IF(\n" +
                        "    d.Current_Task_Stage_Id = 'TS004',\n" +
                        "    DATE_FORMAT(d.Modified_On, '%d/%m/%Y'),\n" +
                        "    ''\n" +
                        "  ) AS stringRejcted_On,\n" +
                        "  IF(\n" +
                        "    d.Current_Task_Stage_Id = 'TS005',\n" +
                        "    DATE_FORMAT(d.Modified_On, '%d/%m/%Y'),\n" +
                        "    (SELECT \n" +
                        "      DATE_FORMAT(k.Modified_On, '%d/%m/%Y') \n" +
                        "    FROM\n" +
                        "      t_task_dtls_audit k \n" +
                        "    WHERE k.Application_Id = d.Application_Id \n" +
                        "      AND k.Task_Id = d.Task_Id \n" +
                        "      AND k.Current_Task_Stage_Id = 'TS005' \n" +
                        "    ORDER BY DATE_FORMAT(k.Modified_On, '%d/%m/%Y') DESC \n" +
                        "    LIMIT 1)\n" +
                        "  ) AS stringHold_On,\n" +
                        "  IF(\n" +
                        "    d.Current_Task_Stage_Id = 'TS009',\n" +
                        "    DATE_FORMAT(d.Modified_On, '%d/%m/%Y'),\n" +
                        "    (SELECT \n" +
                        "      DATE_FORMAT(k.Modified_On, '%d/%m/%Y') \n" +
                        "    FROM\n" +
                        "      t_task_dtls_audit k \n" +
                        "    WHERE k.Application_Id = d.Application_Id \n" +
                        "      AND k.Task_Id = d.Task_Id \n" +
                        "      AND k.Current_Task_Stage_Id = 'TS009' \n" +
                        "    ORDER BY DATE_FORMAT(k.Modified_On, '%d/%m/%Y') DESC \n" +
                        "    LIMIT 1)\n" +
                        "  ) AS stringUnHold_On,\n" +
                        "  (SELECT \n" +
                        "      k.Remarks\n" +
                        "    FROM\n" +
                        "      t_task_dtls_audit k \n" +
                        "    WHERE k.Application_Id = d.Application_Id \n" +
                        "      AND k.Task_Id = d.Task_Id \n" +
                        "      AND k.Current_Task_Stage_Id = 'TS009' \n" +
                        "   GROUP BY k.Current_Task_Stage_Id DESC\n" +
                        "    LIMIT 1) AS UnHoldReason,\n" +
                        "   (SELECT \n" +
                        "      k.Remarks\n" +
                        "    FROM\n" +
                        "      t_task_dtls_audit k \n" +
                        "    WHERE k.Application_Id = d.Application_Id \n" +
                        "      AND k.Task_Id = d.Task_Id \n" +
                        "      AND k.Current_Task_Stage_Id = 'TS005' \n" +
                        "   ) AS HoldReason,\n" +
                        "   (SELECT \n" +
                        "      k.Remarks\n" +
                        "    FROM\n" +
                        "      t_task_dtls_audit k \n" +
                        "    WHERE k.Application_Id = d.Application_Id \n" +
                        "      AND k.Task_Id = d.Task_Id \n" +
                        "      AND k.Current_Task_Stage_Id = 'TS004' \n" +
                        "   ) AS RejectReason,\n" +
                        "   (SELECT \n" +
                        "      k.Remarks\n" +
                        "    FROM\n" +
                        "      t_task_dtls_audit k \n" +
                        "    WHERE k.Application_Id = d.Application_Id \n" +
                        "      AND k.Task_Id = d.Task_Id \n" +
                        "      AND k.Current_Task_Stage_Id = 'TS007' \n" +
                        "   ) AS ForwardReason,\n" +
                        "   d.Remarks \n" +
                        "FROM\n" +
                        "  t_building_application_details a \n" +
                        "  LEFT JOIN t_building_use c \n" +
                        "    ON a.Building_Use_Id = c.Building_Use_Id \n" +
                        "  LEFT JOIN t_task_dtls d \n" +
                        "    ON d.Application_Id = a.Application_Id \n" +
                        "  LEFT JOIN t_task_stages e \n" +
                        "    ON e.Task_Stage_Id = d.Current_Task_Stage_Id \n" +
                        "  LEFT JOIN t_dzongkhag_lookup f \n" +
                        "    ON a.Dzonkhag_Id = f.Dzongkhag_Serial_No \n" +
                        "  LEFT JOIN t_gewog_lookup g \n" +
                        "    ON a.Gewog_Id = g.Gewog_Serial_No \n" +
                        "  LEFT JOIN t_village_master h \n" +
                        "    ON a.Village_Id = h.Village_Serial_No \n" +
                        "  LEFT JOIN t_project_title i \n" +
                        "    ON a.Project_Title_Id = i.Project_Title_Id \n" +
                        "  LEFT JOIN t_role k \n" +
                        "    ON d.Task_Id = k.role_id \n" +
                        "WHERE d.Created_on BETWEEN ? \n" +
                        "  AND ? \n" +
                        "  AND k.role = 'DOES Structural Engineer' \n" +
                        "GROUP BY a.Application_Id \n" +
                        "ORDER BY d.Modified_On DESC ";
                Query query1 = sqlQuery(query, BuildingDto.class).setParameter(1, from_date).setParameter(2, to_date);
                reportDto = query1.list();
            } catch (Exception e) {
                System.out.print("Exception in CommonDao # ReportGeneration: " + e);
            }
        }
        return reportDto;
    }

    @Transactional
    public List<BuildingDto> getDocument(String applicationNo) {
        List<BuildingDto> dto = new ArrayList<BuildingDto>();
        try{
            String sqlQuery = "SELECT \n" +
                    "  d.Document_Name document_Name,\n" +
                    "  d.Document_Type document_Type,\n" +
                    "  d.Document_Path AS document_path,\n" +
                    "  d.Document_Id AS document_id\n" +
                    "FROM\n" +
                    "  t_application_documents d\n" +
                    "WHERE d.Application_Id = ?";
            Query query1 = sqlQuery(sqlQuery, BuildingDto.class).setParameter(1, applicationNo);
            dto = query1.list();

        }catch (Exception e){
            e.printStackTrace();
        }
        return dto;
    }
}


