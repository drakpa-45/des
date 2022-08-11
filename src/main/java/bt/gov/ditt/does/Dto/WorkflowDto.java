package bt.gov.ditt.does.Dto;

import java.io.Serializable;

/**
 * Created by Tandin on 6/12/2020.
 */
public class WorkflowDto  implements Serializable {

    private int serviceId;
    private String actorId;
    private String taskId;
    private String taskName;
    private String taskDescription;
    private String ownerPrivilegdeId;
    private String taskPosition;
    private String initialStatus;
    private String completeStatus;
    private String remarks;
    private String nextTaskId;
    private String applicationNo;
    private String setApplicationStatus;
    private String taskInstanceId;
    private int viewerPrivilegeId;
    private String nextGroupTaskId;
    private String workFlowInstanceId;


    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getActorId() {
        return actorId;
    }

    public void setActorId(String actorId) {
        this.actorId = actorId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getOwnerPrivilegdeId() {
        return ownerPrivilegdeId;
    }

    public void setOwnerPrivilegdeId(String ownerPrivilegdeId) {
        this.ownerPrivilegdeId = ownerPrivilegdeId;
    }

    public String getTaskPosition() {
        return taskPosition;
    }

    public void setTaskPosition(String taskPosition) {
        this.taskPosition = taskPosition;
    }

    public String getInitialStatus() {
        return initialStatus;
    }

    public void setInitialStatus(String initialStatus) {
        this.initialStatus = initialStatus;
    }

    public String getCompleteStatus() {
        return completeStatus;
    }

    public void setCompleteStatus(String completeStatus) {
        this.completeStatus = completeStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getNextTaskId() {
        return nextTaskId;
    }

    public void setNextTaskId(String nextTaskId) {
        this.nextTaskId = nextTaskId;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getSetApplicationStatus() {
        return setApplicationStatus;
    }

    public void setSetApplicationStatus(String setApplicationStatus) {
        this.setApplicationStatus = setApplicationStatus;
    }

    public String getTaskInstanceId() {
        return taskInstanceId;
    }

    public void setTaskInstanceId(String taskInstanceId) {
        this.taskInstanceId = taskInstanceId;
    }

    public int getViewerPrivilegeId() {
        return viewerPrivilegeId;
    }

    public void setViewerPrivilegeId(int viewerPrivilegeId) {
        this.viewerPrivilegeId = viewerPrivilegeId;
    }

    public String getNextGroupTaskId() {
        return nextGroupTaskId;
    }

    public void setNextGroupTaskId(String nextGroupTaskId) {
        this.nextGroupTaskId = nextGroupTaskId;
    }

    public String getWorkFlowInstanceId() {
        return workFlowInstanceId;
    }

    public void setWorkFlowInstanceId(String workFlowInstanceId) {
        this.workFlowInstanceId = workFlowInstanceId;
    }
}
