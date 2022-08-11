package bt.gov.ditt.does.Dto;

/**
 * Created by Tandin on 9/22/2020.
 */
public class ResponseMessage {

    private Integer responseStatus;
    private String responseText;
    private String appl_no;
    private String rejectReason;
    private String serviceName;
    private Object responseDTO;
    private String text;
    private Integer status;


    public ResponseMessage() {
    }

    public ResponseMessage(Integer responseStatus, String responseText, String appl_no, String rejectReason, String serviceName, Object responseDTO, String text, Integer status) {
        this.responseStatus = responseStatus;
        this.responseText = responseText;
        this.appl_no = appl_no;
        this.rejectReason = rejectReason;
        this.serviceName = serviceName;
        this.responseDTO = responseDTO;
        this.text = text;
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(Integer responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public String getAppl_no() {
        return appl_no;
    }

    public void setAppl_no(String appl_no) {
        this.appl_no = appl_no;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Object getResponseDTO() {
        return responseDTO;
    }

    public void setResponseDTO(Object responseDTO) {
        this.responseDTO = responseDTO;
    }
}
