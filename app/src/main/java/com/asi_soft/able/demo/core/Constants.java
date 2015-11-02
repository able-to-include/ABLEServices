package com.asi_soft.able.demo.core;

/**
 * Created 04/02/2015.
 *
 * @author Javier Sanchez
 */
public class Constants {
    /**
     *
     */
    //public final static String HOST = "arwen.asi.es"; // Arwen
    //public final static String HOST = "5.56.57.123"; // Gigas
    public final static String HOST = "al.abletoinclude.eu"; // Gimli

    /**
     *
     */
    public final static String SIMPLEXT_URL = "http://" + HOST
            + "/Simplext.php";
    //        + ":8080/ABLE_Webservice/ABLE_API/Simplext/";
    /**
     *
     */
    public final static String TEXT2PICTO_URL = "http://" + HOST
            + "/Text2Picto.php";
    //        + ":8080/ABLE_Webservice/ABLE_API/Text2Picto/";

    /**
     *
     */
    public final static String TEXT2SPEECH_URL = "http://" + HOST
            + "/Text2Speech.php";
    //        + ":8080/ABLE_Webservice/ABLE_API/Text2Speech/";


    public final static int SIMPLEXT = 1;
    public final static int TEXT2PICTO = 2;
    public final static int TEXT2SPEECH = 3;
}
