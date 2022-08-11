package bt.gov.ditt.does.Common;
import org.jfree.util.Log;

import javax.activation.MimetypesFileTypeMap;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.text.DateFormatSymbols;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Deepak on 3/30/2020.
 */
public class GlobalUtil {
    public static final int NUMRIC = 1;
    public static final int ALPHABETIC = 2;
    public static final int PASSPORT_CODE = 3;

    //This map stores server path for image need to shown in JASPER report which is DOP_Common.properties driven
    private static Map<String, String> JasperImageMap = null;
    /**
     * Common function that can be used to format a properties file message resource and replace the placeholders with the appropriate values that are passed as a string in a vararg
     * @param applicationResourceRef
     * @param messageKey
     * @param messagePlaceHolders
     * @return
     */
    public static String formatMessage(String applicationResourceRef, String messageKey, String... messagePlaceHolders){
        String formattedMessage="";
        try {
            MessageFormat formatter = new MessageFormat("");
            ResourceBundle resourceBundle = ResourceBundle.getBundle(applicationResourceRef);
            formatter.applyPattern(resourceBundle.getString(messageKey));
            formattedMessage = formatter.format(messagePlaceHolders);
        } catch (Exception exception) {
          //  Log.error("", exception.fillInStackTrace());
            System.out.print(exception.fillInStackTrace());
        }
        return formattedMessage;
    }
    public static Properties getPropertiesFromFile(String filePath) {

        Properties properties = new Properties();
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle(filePath);
            Log.debug("loaded local Resource Bundle File:" +  filePath);
            String key = null;
            if (resourceBundle != null) {
                Enumeration<String> localenum = resourceBundle.getKeys();
                while (localenum.hasMoreElements()) {
                    key = localenum.nextElement();
                    properties.put(key, resourceBundle.getString(key));
                }
            }

        } catch (MissingResourceException ex) {
           System.out.print("could not find file:" + ex);
        }
        return properties;
    }




}
