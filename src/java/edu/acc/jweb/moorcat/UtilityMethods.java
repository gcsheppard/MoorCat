package edu.acc.jweb.moorcat;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;

public class UtilityMethods {
    
    protected static Integer integerFromString(String str) {
        if (str == null) {
            return null;
        } else if (str.isEmpty()) {
            return null;
        } else try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return null;
        }
    }
    
    protected static void printParamInfo(HttpServletRequest request) {
        Enumeration paramNames = request.getParameterNames();

        while(paramNames.hasMoreElements()) {
         String paramName = (String)paramNames.nextElement();
         System.out.println("paramName:" + paramName);
         String[] paramValues = request.getParameterValues(paramName);

         // Read single valued data
         if (paramValues.length == 1) {
            String paramValue = paramValues[0];
            if (paramValue.length() == 0)
               System.out.println("No Value");
            else
               System.out.println(paramValue);
         } else {
            // Read multiple valued data
            for(int i = 0; i < paramValues.length; i++) {
               System.out.println(paramValues[i]);
            }
         }
      }
    }    
}
