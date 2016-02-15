/**
 * Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * The work represented by this file is partially funded by the ABLE-TO-INCLUDE
 * project through the European Commission's ICT Policy Support Programme as
 * part of the Competitiveness & Innovation Programme (Grant no.: 621055)
 * Copyright © 2016, ABLE-TO-INCLUDE Consortium.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions & limitations
 * under the License.
 */
package com.asi_soft.able.demo.core;

/**
 * Created 04/02/2015.
 *
 * @author Javier Sanchez
 */
public class Constants {
    /**
     * The protocol used to connect to the server.
     */
    private final static String PROTOCOL = "http://";
    /**
     * The server name.
     */
    private final static String HOST = "al.abletoinclude.eu";
    /**
     * The Simplext resource.
     */
    public final static String SIMPLEXT_URL = PROTOCOL + HOST + "/Simplext.php";
    /**
     * The Text to Picto resource.
     */
    public final static String TEXT2PICTO_URL = PROTOCOL + HOST + "/Text2Picto.php";
    /**
     * The Text to Speech resource.
     */
    public final static String TEXT2SPEECH_URL = PROTOCOL + HOST + "/Text2Speech.php";

    //public final static String HOST = "arwen.asi.es"; // Arwen
    //public final static String HOST = "5.56.57.123"; // Gigas
    //public final static String SIMPLEXT_URL = PROTOCOL + HOST + ":8080/ABLE_Webservice/ABLE_API/Simplext/";
    //public final static String TEXT2PICTO_URL = PROTOCOL + HOST + ":8080/ABLE_Webservice/ABLE_API/Text2Picto/";
    //public final static String TEXT2SPEECH_URL = PROTOCOL + HOST + ":8080/ABLE_Webservice/ABLE_API/Text2Speech/";
}
