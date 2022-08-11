package bt.gov.ditt.does.Entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Tandin on 9/22/2020.
 */
@Entity
@Table(name = "t_application_documents")
public class ApplicationDocEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ENTRY_ID")
    private Integer entry_id;
    @Column(name = "Application_Id")
    private String application_id;
    @Column(name = "Task_Instance_Id")
    private String task_instance_id;
    @Column(name = "Document_Id")
    private String document_id;
    @Column(name = "Document_Type")
    private String document_type;
    @Column(name = "Document_Name")
    private String document_name;
    @Column(name = "Document_Path")
    private String document_path;
    @Column(name = "Remarks")
    private String remarks;
    @Column(name = "Created_On_Date")
    private Date created_on_date;
    @Column(name = "Created_By")
    private String created_by;
    @Column(name = "Is_Active")
    private String is_active;
    @Column(name = "Task_Id")
    private Integer Task_Id;

    public ApplicationDocEntity() {
    }

    public ApplicationDocEntity(String application_id, String task_instance_id, String document_id, String document_type, String document_name, String document_path, String remarks, Date created_on_date, String created_by, String is_active, Integer task_Id) {
        this.application_id = application_id;
        this.task_instance_id = task_instance_id;
        this.document_id = document_id;
        this.document_type = document_type;
        this.document_name = document_name;
        this.document_path = document_path;
        this.remarks = remarks;
        this.created_on_date = created_on_date;
        this.created_by = created_by;
        this.is_active = is_active;
        Task_Id = task_Id;
    }

    public Integer getTask_Id() {
        return Task_Id;
    }

    public void setTask_Id(Integer task_Id) {
        Task_Id = task_Id;
    }

    public Integer getEntry_id() {
        return entry_id;
    }

    public void setEntry_id(Integer entry_id) {
        this.entry_id = entry_id;
    }

    public String getApplication_id() {
        return application_id;
    }

    public void setApplication_id(String application_id) {
        this.application_id = application_id;
    }

    public String getTask_instance_id() {
        return task_instance_id;
    }

    public void setTask_instance_id(String task_instance_id) {
        this.task_instance_id = task_instance_id;
    }

    public String getDocument_id() {
        return document_id;
    }

    public void setDocument_id(String document_id) {
        this.document_id = document_id;
    }

    public String getDocument_type() {
        return document_type;
    }

    public void setDocument_type(String document_type) {
        this.document_type = document_type;
    }

    public String getDocument_name() {
        return document_name;
    }

    public void setDocument_name(String document_name) {
        this.document_name = document_name;
    }

    public String getDocument_path() {
        return document_path;
    }

    public void setDocument_path(String document_path) {
        this.document_path = document_path;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreated_on_date() {
        return created_on_date;
    }

    public void setCreated_on_date(Date created_on_date) {
        this.created_on_date = created_on_date;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }
}
