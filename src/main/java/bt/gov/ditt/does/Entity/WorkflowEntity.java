package bt.gov.ditt.does.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Tandin on 6/12/2020.
 */
@Entity
@Table(name="t_workflow_dtls")
public class WorkflowEntity  implements Serializable {


    @Id
    @Column(name="Workflow_instance_Id")
    private String workFlowInstanceId;
    @Column(name="Application_Id")
    private String applicationId;
    @Column(name="Application_Status")
    private String applicationStatus;
/*

    @OneToMany(mappedBy = "workFlowEntity")
    private Set<TaskDetailsEntity> taskDetailsEntity;
*/

    public WorkflowEntity() {
    }

    public WorkflowEntity(String applicationId, String applicationStatus) {
        this.applicationId = applicationId;
        this.applicationStatus = applicationStatus;
    }

    public String getWorkFlowInstanceId() {
        return workFlowInstanceId;
    }

    public void setWorkFlowInstanceId(String workFlowInstanceId) {
        this.workFlowInstanceId = workFlowInstanceId;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
}
