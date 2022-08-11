package bt.gov.ditt.does.Dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by Tandin on 6/12/2020.
 */
public class TasklistDto implements Serializable {

    private String taskInstanceId;
    private String owner;
    private String taskId;
    private String remarks;
    private String currentTaskStageId;
    private String applicationNo;
    private String applicationStatus;
    private Date createdOn;
    private String createdBy;
    private Date modifiedOn;
    private String modifiedBy;
    private String workflowInstanceId;
    private String Service_Name;
    private int Service_Id;
    private String Task_Stage_Name;
    private String Task_Name;
    private String Task_Id;
    private String stringCreatedOn;
    private String stringModifiedOn;
    private BigInteger rowCount;
    private Date unholdOn;

    public String getTaskInstanceId() {
        return taskInstanceId;
    }

    public BigInteger getRowCount() {
        return rowCount;
    }

    public Date getUnholdOn() {
        return unholdOn;
    }

    public void setUnholdOn(Date unholdOn) {
        this.unholdOn = unholdOn;
    }

    public void setRowCount(BigInteger rowCount) {
        this.rowCount = rowCount;
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

    public String getService_Name() {
        return Service_Name;
    }

    public void setService_Name(String service_Name) {
        Service_Name = service_Name;
    }

    public int getService_Id() {
        return Service_Id;
    }

    public void setService_Id(int service_Id) {
        Service_Id = service_Id;
    }

    public String getTask_Stage_Name() {
        return Task_Stage_Name;
    }

    public void setTask_Stage_Name(String task_Stage_Name) {
        Task_Stage_Name = task_Stage_Name;
    }

    public String getTask_Name() {
        return Task_Name;
    }

    public void setTask_Name(String task_Name) {
        Task_Name = task_Name;
    }

    public String getTask_Id() {
        return Task_Id;
    }

    public void setTask_Id(String task_Id) {
        Task_Id = task_Id;
    }

    public String getStringCreatedOn() {
        return stringCreatedOn;
    }

    public void setStringCreatedOn(String stringCreatedOn) {
        this.stringCreatedOn = stringCreatedOn;
    }

    public String getStringModifiedOn() {
        return stringModifiedOn;
    }

    public void setStringModifiedOn(String stringModifiedOn) {
        this.stringModifiedOn = stringModifiedOn;
    }
}
