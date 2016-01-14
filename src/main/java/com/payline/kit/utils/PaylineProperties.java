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

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * This class allow access to payline config file
 * @author payline dev team
 */
public final class PaylineProperties {

    /**
     * Bundle file name.
     */
    private static final String BUNDLE_NAME = "payline";

    /**
     * Resource bundle.
     * @see ResourceBundle
     */
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    /**
     * Private constructor, to avoid instantiation of this object.
     */
    private PaylineProperties() {
    }

    /**
     * @param key the config entry
     * @return the value of the key, null if the key is not present in the boundle
     */
    public static String getString(final String key) {
        try {
            return RESOURCE_BUNDLE.getString(key).trim();
        } catch (MissingResourceException e) {
            return null; // '!' + key + '!';
        }
    }
}
