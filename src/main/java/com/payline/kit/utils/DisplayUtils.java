/*
 * Copyright (C) 2016 Monext
 *
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.gnu.org/licenses/lgpl-3.0.fr.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.payline.kit.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DisplayUtils class
 * @author payline dev team
 */
public final class DisplayUtils {

    /**
     * A Logger object is used to log messages
     */
    private static final Logger logger = Logger.getLogger(DisplayUtils.class.getName());

    /**
     * Private default constructor for DisplayUtils so this class can not be instantiated.
     * Default constructor.
     */
    private DisplayUtils() {
    }

    /**
     * The objectDetails method
     * @param obj Object
     * @return String
     */
    public static String objectDetails(final Object obj) {
        StringBuffer out = new StringBuffer();
        try {
            if (obj != null) {
                Class<?> cl = obj.getClass();

                if (obj instanceof List) {
                    out.append(arrayDetails((List<?>) obj));
                } else if (cl.isPrimitive() || cl.getName().contains("String")) {
                    out.append(out.append("<li>" + cl.getName() + " => " + obj + "</li>"));
                } else {
                    out.append("<ul><li class=\"object\">");
                    out.append(cl.getName());
                    out.append("</li>");

                    Method[] methods = cl.getDeclaredMethods();

                    for (Method m : methods) {
                        String methodName = m.getName();
                        Class<?> retType = m.getReturnType();
                        Class<?>[] paramTypes = m.getParameterTypes();

                        if (methodName.startsWith("get") && Modifier.toString(m.getModifiers()).equalsIgnoreCase("public") && paramTypes.length == 0) {
                            try {
                                if (retType.isPrimitive() || retType.getName().contains("String")) {
                                    Object r = m.invoke(obj);
                                    String result = null;
                                    if (r != null) {
                                        result = r.toString();
                                    }
                                    // if result sounds like an url, puts
                                    // <a>
                                    if (result != null && result.startsWith("http")) {
                                        result = "<a href=\"" + result + "\">" + result + "</a>";
                                    }
                                    out.append("<li>" + methodName + " => " + result + "</li>");
                                } else {
                                    out.append(objectDetails(m.invoke(obj)));
                                }
                            } catch (IllegalAccessException ex) {
                                logger.log(Level.SEVERE, "error during introspection :", ex);
                            } catch (InvocationTargetException ex) {
                                logger.log(Level.SEVERE, "error during introspection :", ex);
                            }
                        }
                    }
                }
                out.append(" </ul>");
            }

        } catch (NoSuchElementException ex) {
            logger.log(Level.SEVERE, "error during introspection :", ex);
        }
        return out.toString();
    }

    /**
     * The arrayDetails method
     * @param obj List
     * @return String
     */
    public static String arrayDetails(final List<?> obj) {
        StringBuffer out = new StringBuffer();
        try {

            if (obj != null) {
                if (obj.size() > 0) {
                    out.append("<ul><li class=\"object\">").append(obj.getClass().getName()).append("</li>");
                }
                for (Object o : obj) {
                    out.append(objectDetails(o));
                }
                out.append(" </ul>");
            }
        } catch (NoSuchElementException ex) {
            System.out.println(ex.getMessage());
        }
        return out.toString();
    }
}
