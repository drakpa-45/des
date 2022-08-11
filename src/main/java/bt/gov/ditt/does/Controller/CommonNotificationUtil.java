package bt.gov.ditt.does.Controller;

import bt.gov.ditt.does.Common.DesConstant;
import bt.gov.ditt.does.Common.GlobalUtil;
import org.springframework.util.StringUtils;

import java.util.Properties;

/**
 * Created by Tandin on 9/21/2020.
 */
public class CommonNotificationUtil
{

        //private EmailUtil emailSender = null;
        private SMSUtil smsSender = null;
        private static final String EMAIL_BODY_PART1 = "Dear Applicant, Your application has been registered and your application ID is";
        private static String SMS_URL = null;
        Properties properties = null;

        public static boolean notifyOnSubmission(String appNumber, String[] contactNumbers, String phoneNo, String officeTel)
        {
            boolean result = false;
            try
            {
                if(appNumber!=null && contactNumbers!=null)
                {
                    //System.out.println("Inside SMS");
                    CommonNotificationUtil notification =new CommonNotificationUtil();
                    notification.properties = GlobalUtil.getPropertiesFromFile(DesConstant.DES_COMMON_PROPERTIES_FILE_PATH);
                    if(notification.properties.getProperty("sms.url.firstPart")!=null)
                        SMS_URL = notification.properties.getProperty("sms.url.firstPart");

                    for(String mobile : contactNumbers){

                        if(!StringUtils.isEmpty(mobile)){
                            notification.smsSender = new SMSUtil();
                            notification.smsSender.setMobileNo(mobile);
                            notification.smsSender.setSmsUrl(SMS_URL);
                            notification.smsSender.setSmsContent(EMAIL_BODY_PART1+" "+appNumber+", for more details: Tel:"+" "+officeTel+", Mobile:"+" "+ phoneNo+".");
                            notification.smsSender.sendSMS();
                        }
                    }
                }
                result = true;
            }
            catch(Exception ee){
                ee.printStackTrace();
            }
            return result;
        }

    public static boolean notifyOnArchitectReject(String appNumber, String[] contactNumbers, String phoneNo, String officeTel, String user_Role)
    {
        boolean result = false;
        try
        {
            if(appNumber!=null && contactNumbers!=null)
            {
                //System.out.println("Inside SMS");
                CommonNotificationUtil notification =new CommonNotificationUtil();
                notification.properties = GlobalUtil.getPropertiesFromFile(DesConstant.DES_COMMON_PROPERTIES_FILE_PATH);
                if(notification.properties.getProperty("sms.url.firstPart")!=null)
                    SMS_URL = notification.properties.getProperty("sms.url.firstPart");

                for(String mobile : contactNumbers){
                    System.out.println("mo"+mobile);
                    System.out.println("SMS_URL::::"+SMS_URL);

                    if(!StringUtils.isEmpty(mobile)){
                        notification.smsSender = new SMSUtil();
                        notification.smsSender.setMobileNo(mobile);
                        notification.smsSender.setSmsUrl(SMS_URL);
                        notification.smsSender.setSmsContent("Dear Applicant, Your Architectural Drawing for the application ID"+" "+appNumber+" is rejected by "+user_Role+", Kindly consult the Dzongkhag Administration for more details, Tel:"+" "+officeTel+", Mobile:"+" "+phoneNo+".");
                        notification.smsSender.sendSMS();
                    }
                }
            }
            result = true;
        }
        catch(Exception ee){
            ee.printStackTrace();
        }
        return result;
    }

    public static boolean notifyOnElectricalReject(String appNumber, String[] contactNumbers, String phoneNo, String officeTel, String user_Role)
    {
        boolean result = false;
        try
        {
            if(appNumber!=null && contactNumbers!=null)
            {
                //System.out.println("Inside SMS");
                CommonNotificationUtil notification =new CommonNotificationUtil();
                notification.properties = GlobalUtil.getPropertiesFromFile(DesConstant.DES_COMMON_PROPERTIES_FILE_PATH);
                if(notification.properties.getProperty("sms.url.firstPart")!=null)
                    SMS_URL = notification.properties.getProperty("sms.url.firstPart");

                for(String mobile : contactNumbers){
                    System.out.println("mo"+mobile);
                    System.out.println("SMS_URL::::"+SMS_URL);

                    if(!StringUtils.isEmpty(mobile)){
                        notification.smsSender = new SMSUtil();
                        notification.smsSender.setMobileNo(mobile);
                        notification.smsSender.setSmsUrl(SMS_URL);
                        notification.smsSender.setSmsContent("Dear Applicant, Your Electrical Drawing for the application ID"+" "+appNumber+" is rejected by "+user_Role+", Kindly consult the Dzongkhag Administration for more details, Tel:"+" "+officeTel+", Mobile:"+" "+phoneNo+".");
                        notification.smsSender.sendSMS();
                    }
                }
            }
            result = true;
        }
        catch(Exception ee){
            ee.printStackTrace();
        }
        return result;
    }
    public static boolean notifyOnStructuralReject(String appNumber, String[] contactNumbers, String phoneNo, String officeTel, String user_Role)
    {
        boolean result = false;
        try
        {
            if(appNumber!=null && contactNumbers!=null)
            {
                //System.out.println("Inside SMS");
                CommonNotificationUtil notification =new CommonNotificationUtil();
                notification.properties = GlobalUtil.getPropertiesFromFile(DesConstant.DES_COMMON_PROPERTIES_FILE_PATH);
                if(notification.properties.getProperty("sms.url.firstPart")!=null)
                    SMS_URL = notification.properties.getProperty("sms.url.firstPart");

                for(String mobile : contactNumbers){
                    System.out.println("mo"+mobile);
                    System.out.println("SMS_URL::::"+SMS_URL);

                    if(!StringUtils.isEmpty(mobile)){
                        notification.smsSender = new SMSUtil();
                        notification.smsSender.setMobileNo(mobile);
                        notification.smsSender.setSmsUrl(SMS_URL);
                        notification.smsSender.setSmsContent("Dear Applicant, Your Structural Drawing for the application ID"+" "+appNumber+" is rejected by "+user_Role+", Kindly consult the Dzongkhag Administration for more details, Tel:"+" "+officeTel+", Mobile:"+" "+ phoneNo+".");
                        notification.smsSender.sendSMS();
                    }
                }
            }
            result = true;
        }
        catch(Exception ee){
            ee.printStackTrace();
        }
        return result;
    }

    public static boolean notifyOnArchitectHold(String appNumber, String[] contactNumbers, String phoneNo, String officeTel, String user_Role)
    {
        boolean result = false;
        try
        {
            if(appNumber!=null && contactNumbers!=null)
            {
                //System.out.println("Inside SMS");
                CommonNotificationUtil notification =new CommonNotificationUtil();
                notification.properties = GlobalUtil.getPropertiesFromFile(DesConstant.DES_COMMON_PROPERTIES_FILE_PATH);
                if(notification.properties.getProperty("sms.url.firstPart")!=null)
                    SMS_URL = notification.properties.getProperty("sms.url.firstPart");

                for(String mobile : contactNumbers){
                    System.out.println("mo"+mobile);
                    System.out.println("SMS_URL::::"+SMS_URL);

                    if(!StringUtils.isEmpty(mobile)){
                        notification.smsSender = new SMSUtil();
                        notification.smsSender.setMobileNo(mobile);
                        notification.smsSender.setSmsUrl(SMS_URL);
                        notification.smsSender.setSmsContent("Dear Applicant, Your Architectural Drawing for the application ID"+" "+appNumber+" is on Hold by "+user_Role+", Kindly consult the Dzongkhag Administration for details, Tel:"+" "+officeTel+", Mobile:"+" "+ phoneNo+".");
                        notification.smsSender.sendSMS();
                    }
                }
            }
            result = true;
        }
        catch(Exception ee){
            ee.printStackTrace();
        }
        return result;
    }

    public static boolean notifyOnElectricalHold(String appNumber, String[] contactNumbers, String phoneNo, String officeTel, String user_Role)
    {
        boolean result = false;
        try
        {
            if(appNumber!=null && contactNumbers!=null)
            {
                //System.out.println("Inside SMS");
                CommonNotificationUtil notification =new CommonNotificationUtil();
                notification.properties = GlobalUtil.getPropertiesFromFile(DesConstant.DES_COMMON_PROPERTIES_FILE_PATH);
                if(notification.properties.getProperty("sms.url.firstPart")!=null)
                    SMS_URL = notification.properties.getProperty("sms.url.firstPart");

                for(String mobile : contactNumbers){
                    System.out.println("mo"+mobile);
                    System.out.println("SMS_URL::::"+SMS_URL);

                    if(!StringUtils.isEmpty(mobile)){
                        notification.smsSender = new SMSUtil();
                        notification.smsSender.setMobileNo(mobile);
                        notification.smsSender.setSmsUrl(SMS_URL);
                        notification.smsSender.setSmsContent("Dear Applicant, Your Electrical Drawing for the application ID"+" "+appNumber+" is on Hold by "+user_Role+", Kindly consult the Dzongkhag Administration for details, Tel:"+" "+officeTel+", Mobile:"+" "+ phoneNo+".");
                        notification.smsSender.sendSMS();
                    }
                }
            }
            result = true;
        }
        catch(Exception ee){
            ee.printStackTrace();
        }
        return result;
    }

    public static boolean notifyOnStructuralHold(String appNumber, String[] contactNumbers, String phoneNo, String officeTel, String user_Role)
    {
        boolean result = false;
        try
        {
            if(appNumber!=null && contactNumbers!=null)
            {
                //System.out.println("Inside SMS");
                CommonNotificationUtil notification =new CommonNotificationUtil();
                notification.properties = GlobalUtil.getPropertiesFromFile(DesConstant.DES_COMMON_PROPERTIES_FILE_PATH);
                if(notification.properties.getProperty("sms.url.firstPart")!=null)
                    SMS_URL = notification.properties.getProperty("sms.url.firstPart");

                for(String mobile : contactNumbers){
                    System.out.println("mo"+mobile);
                    System.out.println("SMS_URL::::"+SMS_URL);

                    if(!StringUtils.isEmpty(mobile)){
                        notification.smsSender = new SMSUtil();
                        notification.smsSender.setMobileNo(mobile);
                        notification.smsSender.setSmsUrl(SMS_URL);
                        notification.smsSender.setSmsContent("Dear Applicant, Your Structural Drawing for the application ID"+" "+appNumber+" is on Hold by "+user_Role+", Kindly consult the Dzongkhag Administration for details, Tel:"+" "+officeTel+", Mobile:"+" "+ phoneNo+".");
                        notification.smsSender.sendSMS();
                    }
                }
            }
            result = true;
        }
        catch(Exception ee){
            ee.printStackTrace();
        }
        return result;
    }

    public static boolean notifyOnAllDrawingForwarded(String appNumber, String[] contactNumbers, String phoneNo, String officeTel, String user_Role)
    {
        boolean result = false;
        try
        {
            if(appNumber!=null && contactNumbers!=null)
            {
                //System.out.println("Inside SMS");
                CommonNotificationUtil notification =new CommonNotificationUtil();
                notification.properties = GlobalUtil.getPropertiesFromFile(DesConstant.DES_COMMON_PROPERTIES_FILE_PATH);
                if(notification.properties.getProperty("sms.url.firstPart")!=null)
                    SMS_URL = notification.properties.getProperty("sms.url.firstPart");

                for(String mobile : contactNumbers){
                    System.out.println("mo"+mobile);
                    System.out.println("SMS_URL::::"+SMS_URL);

                    if(!StringUtils.isEmpty(mobile)){
                        notification.smsSender = new SMSUtil();
                        notification.smsSender.setMobileNo(mobile);
                        notification.smsSender.setSmsUrl(SMS_URL);
                        notification.smsSender.setSmsContent("Dear Applicant, Your drawings (Architectural/Electrical/Structural) for the application ID"+" "+appNumber+" is forwarded to DES by "+user_Role+", MoWHS, Thimphu, Tel:"+" "+officeTel+", Mobile:"+" "+ phoneNo+".");
                        notification.smsSender.sendSMS();
                    }
                }
            }
            result = true;
        }
        catch(Exception ee){
            ee.printStackTrace();
        }
        return result;
    }

    public static boolean notifyOnPowerClearance(String appNumber, String[] contactNumbers, String phoneNo, String officeTel, String user_Role)
    {
        boolean result = false;
        try
        {
            if(appNumber!=null && contactNumbers!=null)
            {
                //System.out.println("Inside SMS");
                CommonNotificationUtil notification =new CommonNotificationUtil();
                notification.properties = GlobalUtil.getPropertiesFromFile(DesConstant.DES_COMMON_PROPERTIES_FILE_PATH);
                if(notification.properties.getProperty("sms.url.firstPart")!=null)
                    SMS_URL = notification.properties.getProperty("sms.url.firstPart");

                for(String mobile : contactNumbers){
                    System.out.println("mo"+mobile);
                    System.out.println("SMS_URL::::"+SMS_URL);

                    if(!StringUtils.isEmpty(mobile)){
                        notification.smsSender = new SMSUtil();
                        notification.smsSender.setMobileNo(mobile);
                        notification.smsSender.setSmsUrl(SMS_URL);
                        notification.smsSender.setSmsContent("Dear Applicant, Your Electrical drawing for the application ID"+" "+appNumber+" has been approved by "+user_Role+", Kindly consult Dzongkhag Administration for power clearance, Tel:"+" "+officeTel+", Mobile:"+" "+phoneNo+".");
                        notification.smsSender.sendSMS();
                    }
                }
            }
            result = true;
        }
        catch(Exception ee){
            ee.printStackTrace();
        }
        return result;
    }

    public static boolean notifyOnAllDrawingsApprove(String appNumber, String[] contactNumbers, String mobileNo, String officeTel, String user_Role)
    {
        boolean result = false;
        try
        {
            if(appNumber!=null && contactNumbers!=null)
            {
                //System.out.println("Inside SMS");
                CommonNotificationUtil notification =new CommonNotificationUtil();
                notification.properties = GlobalUtil.getPropertiesFromFile(DesConstant.DES_COMMON_PROPERTIES_FILE_PATH);
                if(notification.properties.getProperty("sms.url.firstPart")!=null)
                    SMS_URL = notification.properties.getProperty("sms.url.firstPart");

                for(String mobile : contactNumbers){
                    System.out.println("mo"+mobile);
                    System.out.println("SMS_URL::::"+SMS_URL);

                    if(!StringUtils.isEmpty(mobile)){
                        notification.smsSender = new SMSUtil();
                        notification.smsSender.setMobileNo(mobile);
                        notification.smsSender.setSmsUrl(SMS_URL);
                        notification.smsSender.setSmsContent("Dear Applicant, Your Drawing/s for the application ID" + " " + appNumber + " has been approved by " + " " + user_Role + "and Technical Sanction is ready, Kindly consult Dzongkhag Administration for Construction approval, Tel: "+" "+officeTel+", Mobile:"+" "+mobileNo+".");
                        notification.smsSender.sendSMS();
                    }
                }
            }
            result = true;
        }
        catch(Exception ee){
            ee.printStackTrace();
        }
        return result;
    }

    public static boolean notifyOnDESArchitectReject(String appNumber, String[] contactNumbers, String phoneNo, String officeTel, String user_Role)
    {
        boolean result = false;
        try
        {
            if(appNumber!=null && contactNumbers!=null)
            {
                //System.out.println("Inside SMS");
                CommonNotificationUtil notification =new CommonNotificationUtil();
                notification.properties = GlobalUtil.getPropertiesFromFile(DesConstant.DES_COMMON_PROPERTIES_FILE_PATH);
                if(notification.properties.getProperty("sms.url.firstPart")!=null)
                    SMS_URL = notification.properties.getProperty("sms.url.firstPart");

                for(String mobile : contactNumbers){
                    System.out.println("mo"+mobile);
                    System.out.println("SMS_URL::::"+SMS_URL);

                    if(!StringUtils.isEmpty(mobile)){
                        notification.smsSender = new SMSUtil();
                        notification.smsSender.setMobileNo(mobile);
                        notification.smsSender.setSmsUrl(SMS_URL);
                        notification.smsSender.setSmsContent("Dear Applicant, Your Architectural drawing for the application ID" + " " + appNumber + " is rejected by " + user_Role + ", Kindly consult DES, MoWHS, Tel:" + " " + officeTel + ", Mobile:" + " " + phoneNo + ".");
                        notification.smsSender.sendSMS();
                    }
                }
            }
            result = true;
        }
        catch(Exception ee){
            ee.printStackTrace();
        }
        return result;
    }

    public static boolean notifyOnDESStructuralReject(String appNumber, String[] contactNumbers, String phoneNo, String officeTel, String user_Role)
    {
        boolean result = false;
        try
        {
            if(appNumber!=null && contactNumbers!=null)
            {
                //System.out.println("Inside SMS");
                CommonNotificationUtil notification =new CommonNotificationUtil();
                notification.properties = GlobalUtil.getPropertiesFromFile(DesConstant.DES_COMMON_PROPERTIES_FILE_PATH);
                if(notification.properties.getProperty("sms.url.firstPart")!=null)
                    SMS_URL = notification.properties.getProperty("sms.url.firstPart");

                for(String mobile : contactNumbers){
                    System.out.println("mo"+mobile);
                    System.out.println("SMS_URL::::"+SMS_URL);

                    if(!StringUtils.isEmpty(mobile)){
                        notification.smsSender = new SMSUtil();
                        notification.smsSender.setMobileNo(mobile);
                        notification.smsSender.setSmsUrl(SMS_URL);
                        notification.smsSender.setSmsContent("Dear Applicant, Your Structural drawing for the application ID" + " " + appNumber + " is rejected by " + user_Role + ", Kindly consult DES, MoWHS, Tel:"+" "+officeTel+", Mobile:"+" "+ phoneNo+".");
                        notification.smsSender.sendSMS();
                    }
                }
            }
            result = true;
        }
        catch(Exception ee){
            ee.printStackTrace();
        }
        return result;
    }

    public static boolean notifyOnDESElectricalReject(String appNumber, String[] contactNumbers, String phoneNo, String officeTel, String user_Role)
    {
        boolean result = false;
        try
        {
            if(appNumber!=null && contactNumbers!=null)
            {
                //System.out.println("Inside SMS");
                CommonNotificationUtil notification =new CommonNotificationUtil();
                notification.properties = GlobalUtil.getPropertiesFromFile(DesConstant.DES_COMMON_PROPERTIES_FILE_PATH);
                if(notification.properties.getProperty("sms.url.firstPart")!=null)
                    SMS_URL = notification.properties.getProperty("sms.url.firstPart");

                for(String mobile : contactNumbers){
                    System.out.println("mo"+mobile);
                    System.out.println("SMS_URL::::"+SMS_URL);

                    if(!StringUtils.isEmpty(mobile)){
                        notification.smsSender = new SMSUtil();
                        notification.smsSender.setMobileNo(mobile);
                        notification.smsSender.setSmsUrl(SMS_URL);
                        notification.smsSender.setSmsContent("Dear Applicant, Your Electrical drawing for the application ID" + " " + appNumber + " is rejected by " + user_Role + ", Kindly consult DES, MoWHS, Tel:"+" "+officeTel+", Mobile:"+" "+ phoneNo+".");
                        notification.smsSender.sendSMS();
                    }
                }
            }
            result = true;
        }
        catch(Exception ee){
            ee.printStackTrace();
        }
        return result;
    }

    public static boolean notifyOnDESElectricalHold(String appNumber, String[] contactNumbers, String phoneNo, String officeTel, String user_Role)
    {
        boolean result = false;
        try
        {
            if(appNumber!=null && contactNumbers!=null)
            {
                //System.out.println("Inside SMS");
                CommonNotificationUtil notification =new CommonNotificationUtil();
                notification.properties = GlobalUtil.getPropertiesFromFile(DesConstant.DES_COMMON_PROPERTIES_FILE_PATH);
                if(notification.properties.getProperty("sms.url.firstPart")!=null)
                    SMS_URL = notification.properties.getProperty("sms.url.firstPart");

                for(String mobile : contactNumbers){
                    System.out.println("mo"+mobile);
                    System.out.println("SMS_URL::::"+SMS_URL);

                    if(!StringUtils.isEmpty(mobile)){
                        notification.smsSender = new SMSUtil();
                        notification.smsSender.setMobileNo(mobile);
                        notification.smsSender.setSmsUrl(SMS_URL);
                        notification.smsSender.setSmsContent("Dear Applicant, Your Electrical drawing for the application ID" + " " + appNumber + " is on Hold by " + user_Role + ", Kindly consult DES, MoWHS, Tel:"+" "+officeTel+", Mobile:"+" "+ phoneNo+".");
                        notification.smsSender.sendSMS();
                    }
                }
            }
            result = true;
        }
        catch(Exception ee){
            ee.printStackTrace();
        }
        return result;
    }

    public static boolean notifyOnDESStructuralHold(String appNumber, String[] contactNumbers, String phoneNo, String officeTel, String user_Role)
    {
        boolean result = false;
        try
        {
            if(appNumber!=null && contactNumbers!=null)
            {
                //System.out.println("Inside SMS");
                CommonNotificationUtil notification =new CommonNotificationUtil();
                notification.properties = GlobalUtil.getPropertiesFromFile(DesConstant.DES_COMMON_PROPERTIES_FILE_PATH);
                if(notification.properties.getProperty("sms.url.firstPart")!=null)
                    SMS_URL = notification.properties.getProperty("sms.url.firstPart");

                for(String mobile : contactNumbers){
                    System.out.println("mo"+mobile);
                    System.out.println("SMS_URL::::"+SMS_URL);

                    if(!StringUtils.isEmpty(mobile)){
                        notification.smsSender = new SMSUtil();
                        notification.smsSender.setMobileNo(mobile);
                        notification.smsSender.setSmsUrl(SMS_URL);
                        notification.smsSender.setSmsContent("Dear Applicant, Your Structural drawing for the application ID" + " " + appNumber + " is on Hold by " + user_Role + ", Kindly consult DES, MoWHS, Tel:"+" "+officeTel+", Mobile:"+" "+ phoneNo+".");
                        notification.smsSender.sendSMS();
                    }
                }
            }
            result = true;
        }
        catch(Exception ee){
            ee.printStackTrace();
        }
        return result;
    }

    public static boolean notifyOnDESArchitectHold(String appNumber, String[] contactNumbers, String phoneNo, String officeTel, String user_Role)
    {
        boolean result = false;
        try
        {
            if(appNumber!=null && contactNumbers!=null)
            {
                //System.out.println("Inside SMS");
                CommonNotificationUtil notification =new CommonNotificationUtil();
                notification.properties = GlobalUtil.getPropertiesFromFile(DesConstant.DES_COMMON_PROPERTIES_FILE_PATH);
                if(notification.properties.getProperty("sms.url.firstPart")!=null)
                    SMS_URL = notification.properties.getProperty("sms.url.firstPart");

                for(String mobile : contactNumbers){
                    System.out.println("mo"+mobile);
                    System.out.println("SMS_URL::::"+SMS_URL);

                    if(!StringUtils.isEmpty(mobile)){
                        notification.smsSender = new SMSUtil();
                        notification.smsSender.setMobileNo(mobile);
                        notification.smsSender.setSmsUrl(SMS_URL);
                        notification.smsSender.setSmsContent("Dear Applicant, Your Architectural drawing for the application ID" + " " + appNumber + " is on Hold by " + user_Role + ", Kindly consult DES, MoWHS, Tel:"+" "+officeTel+", Mobile:"+" "+ phoneNo+".");
                        notification.smsSender.sendSMS();
                    }
                }
            }
            result = true;
        }
        catch(Exception ee){
            ee.printStackTrace();
        }
        return result;
    }

    public static boolean notifyOnDESApproval(String appNumber, String[] contactNumbers, String phoneNo, String officeTel, String user_Role)
    {
        boolean result = false;
        try
        {
            if(appNumber!=null && contactNumbers!=null)
            {
                //System.out.println("Inside SMS");
                CommonNotificationUtil notification =new CommonNotificationUtil();
                notification.properties = GlobalUtil.getPropertiesFromFile(DesConstant.DES_COMMON_PROPERTIES_FILE_PATH);
                if(notification.properties.getProperty("sms.url.firstPart")!=null)
                    SMS_URL = notification.properties.getProperty("sms.url.firstPart");

                for(String mobile : contactNumbers){
                    System.out.println("mo"+mobile);
                    System.out.println("SMS_URL::::"+SMS_URL);

                    if(!StringUtils.isEmpty(mobile)){
                        notification.smsSender = new SMSUtil();
                        notification.smsSender.setMobileNo(mobile);
                        notification.smsSender.setSmsUrl(SMS_URL);
                        notification.smsSender.setSmsContent("Dear Applicant, Your Drawing/s for the application ID" + " " + appNumber + " has been approved by " + user_Role + " and Technical Sanction is ready, Kindly collect the drawing/s within two working days or it shall be forwarded to Dzongkhag by post, Tel:"+" "+officeTel+", Mobile:"+" "+ phoneNo+".");
                        notification.smsSender.sendSMS();
                    }
                }
            }
            result = true;
        }
        catch(Exception ee){
            ee.printStackTrace();
        }
        return result;
    }

    public static boolean notifyOnDESApprovalOfApplication(String appNumber, String[] contactNumbers, String phoneNo, String officeTel, String user_Role) {
        boolean result = false;
        try
        {
            if(appNumber!=null && contactNumbers!=null)
            {
                //System.out.println("Inside SMS");
                CommonNotificationUtil notification =new CommonNotificationUtil();
                notification.properties = GlobalUtil.getPropertiesFromFile(DesConstant.DES_COMMON_PROPERTIES_FILE_PATH);
                if(notification.properties.getProperty("sms.url.firstPart")!=null)
                    SMS_URL = notification.properties.getProperty("sms.url.firstPart");

                System.out.println("mo"+contactNumbers);
                System.out.println("SMS_URL::::"+SMS_URL);

                for(String mobile : contactNumbers){
                    System.out.println("mo"+mobile);
                    System.out.println("SMS_URL::::"+SMS_URL);

                    if(!StringUtils.isEmpty(mobile)){
                        notification.smsSender = new SMSUtil();
                        notification.smsSender.setMobileNo(mobile);
                        notification.smsSender.setSmsUrl(SMS_URL);
                        notification.smsSender.setSmsContent("Dear Applicant, Your Drawing/s for the application ID"+" "+appNumber+" has been approved by "+user_Role+" and Technical Sanction is ready, Kindly collect the drawing/s within two working days or it shall be forwarded to Dzongkhag by post, Tel#:"+" "+officeTel+", Mobile#:"+" "+ phoneNo+".");
                        notification.smsSender.sendSMS();
                    }
                }
            }
            result = true;
        }
        catch(Exception ee){
            ee.printStackTrace();
        }
        return result;
    }
}
