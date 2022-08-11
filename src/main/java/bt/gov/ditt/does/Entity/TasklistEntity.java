package bt.gov.ditt.does.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Tandin on 6/12/2020.
 */
@Entity
@Table(name="t_task_dtls")
public class TasklistEntity implements Serializable {

    @Id
    @Column(name="Task_Instance_Id")
    private String taskInstanceId;

    @Column(name="Owner")
    private String owner;

    @Column(name="Task_Id")
    private String taskId;
    @Column(name="Remarks")
    private String remarks;
    @Column(name="Current_Task_Stage_Id")
    private String currentTaskStageId;
    @Column(name="Application_Id")
    private String applicationNo;
    @Column(name="Application_Status")
    private String applicationStatus;
    @Column(name="Created_on")
    private Date createdOn;
    @Column(name="Created_By")
    private String createdBy;
    @Column(name="Modified_On")
    private Date modifiedOn;
    @Column(name="Modified_By")
    private String modifiedBy;
    @Column(name="Workflow_Instance_Id")
    private String workflowInstanceId;
    @Column(name="reason_id")
    private String reason_id;
    @Column(name="juris_id")
    private int juris_id;
    @Column(name="juris_type")
    private int juris_type;
    @Column(name="Is_Architect_Forwarded")
    private String Is_Architect_Forwarded;
    @Column(name="Is_Structural_Forwarded")
    private String Is_Structural_Forwarded;
    @Column(name="Is_Electrical_Forwarded")
    private String Is_Electrical_Forwarded;
    @Column(name="UnHold_On")
    private Date unholdOn;



    public TasklistEntity() {
    }

    public TasklistEntity(String taskInstanceId, String owner, String taskId, String remarks, String currentTaskStageId, String applicationNo, String applicationStatus, Date createdOn, String createdBy, Date modifiedOn, String modifiedBy, String workflowInstanceId, String reason_id, int juris_id, int juris_type, String is_Architect_Forwarded, String is_Structural_Forwarded, String is_Electrical_Forwarded, Date unholdOn) {
        this.taskInstanceId = taskInstanceId;
        this.owner = owner;
        this.taskId = taskId;
        this.remarks = remarks;
        this.currentTaskStageId = currentTaskStageId;
        this.applicationNo = applicationNo;
        this.applicationStatus = applicationStatus;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.modifiedOn = modifiedOn;
        this.modifiedBy = modifiedBy;
        this.workflowInstanceId = workflowInstanceId;
        this.reason_id = reason_id;
        this.juris_id = juris_id;
        this.juris_type = juris_type;
        Is_Architect_Forwarded = is_Architect_Forwarded;
        Is_Structural_Forwarded = is_Structural_Forwarded;
        Is_Electrical_Forwarded = is_Electrical_Forwarded;
        this.unholdOn = unholdOn;
    }

    public Date getUnholdOn() {
        return unholdOn;
    }
    public void setUnholdOn(Date unholdOn) {
        this.unholdOn = unholdOn;
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

    public String getTaskInstanceId() {
        return taskInstanceId;
    }

    public void setTaskInstanceId(String taskInstanceId) {
        this.taskInstanceId = taskInstanceId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCurrentTaskStageId() {
        return currentTaskStageId;
    }

    public void setCurrentTaskStageId(String currentTaskStageId) {
        this.currentTaskStageId = currentTaskStageId;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getWorkflowInstanceId() {
        return workflowInstanceId;
    }

    public void setWorkflowInstanceId(String workflowInstanceId) {
        this.workflowInstanceId = workflowInstanceId;
    }

    public String getReason_id() {
        return reason_id;
    }

    public void setReason_id(String reason_id) {
        this.reason_id = reason_id;
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
