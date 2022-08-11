package bt.gov.ditt.does.Controller;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ResourceBundle;

/**
 * Created by Tandin on 9/21/2020.
 */
    public class SMSUtil implements Runnable{

        ResourceBundle bundle = ResourceBundle.getBundle("SMS");
        private String smsUrl = bundle.getString("sms.url.firstPart");
        private String mobileNo = null;
        private String smsContent = null;
        boolean result = false;
        public static final String SMS_ENCONDING_TYPE = "UTF-8";
        public static final String URL_MIDDLE_PART = "&msg=";

        public boolean sendSMS(){

            Thread thread = new Thread(this);
            thread.start();
            return result;
        }

        /**
         * @return the mobileNo
         */
        public String getMobileNo() {
            return mobileNo;
        }

        /**
         * @param mobileNo the mobileNo to set
         */
        public void setMobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
        }

        /**
         * @return the smsUrl
         */
        public String getSmsUrl() {
            return smsUrl;
        }

        /**
         * @param smsUrl the smsUrl to set
         */
        public void setSmsUrl(String smsUrl) {
            this.smsUrl = smsUrl;
        }
        /**
         * @return the smsContent
         */
        public String getSmsContent() {
            return smsContent;
        }

        /**
         * @param smsContent the smsContent to set
         */
        public void setSmsContent(String smsContent) {
            this.smsContent = smsContent;
        }
        @Override
        public void run() {

            String encodedMobileNo = null;
            String encodedSMScontent = null;
            String fullURLStr = null;
            URL url =null;
            HttpURLConnection connection = null;
            String responseMSg = null;

            if(smsUrl!=null && mobileNo!=null && smsContent!=null){
                try
                {

                    encodedMobileNo= URLEncoder.encode(mobileNo, SMS_ENCONDING_TYPE);
                    encodedSMScontent=URLEncoder.encode(smsContent, SMS_ENCONDING_TYPE);

                    fullURLStr = smsUrl + mobileNo+ URL_MIDDLE_PART +smsContent;

                    System.out.println("SMSURL:::"+ fullURLStr);

                    fullURLStr= fullURLStr.replaceAll(" ","%20");
                    url = new URL(fullURLStr);

                    System.out.println("SMSURL:::"+ url);

                    connection = (HttpURLConnection) url.openConnection();
                    connection.setDoOutput(false);
                    connection.setDoInput(true);
                    responseMSg = connection.getResponseMessage();

                    int code = connection.getResponseCode() ;
                    if (code == HttpURLConnection.HTTP_OK)
                    {
                        connection.disconnect() ;
                        result = true;
                    }
                }
                catch(Exception e){
                }
            }
        }

    }

