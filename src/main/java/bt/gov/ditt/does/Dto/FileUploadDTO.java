package bt.gov.ditt.does.Dto;

/**
 * Created by Tandin on 9/22/2020.
 */
public class FileUploadDTO {
    private Double maxFileSize;
    private String supportedFileType[];
    //used for error message
    private String supportedFileTypeInString;
    private String uploadFilePath;

    public FileUploadDTO() {
    }

    public FileUploadDTO(Double maxFileSize, String[] supportedFileType, String supportedFileTypeInString, String uploadFilePath) {
        this.maxFileSize = maxFileSize;
        this.supportedFileType = supportedFileType;
        this.supportedFileTypeInString = supportedFileTypeInString;
        this.uploadFilePath = uploadFilePath;
    }

    public Double getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(Double maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public String[] getSupportedFileType() {
        return supportedFileType;
    }

    public void setSupportedFileType(String[] supportedFileType) {
        this.supportedFileType = supportedFileType;
    }

    public String getSupportedFileTypeInString() {
        return supportedFileTypeInString;
    }

    public void setSupportedFileTypeInString(String supportedFileTypeInString) {
        this.supportedFileTypeInString = supportedFileTypeInString;
    }

    public String getUploadFilePath() {
        return uploadFilePath;
    }

    public void setUploadFilePath(String uploadFilePath) {
        this.uploadFilePath = uploadFilePath;
    }
}
