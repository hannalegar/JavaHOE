/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.javagladiators.education.hoe.herolevel.controllers;

import java.util.regex.Pattern;

public class Utils {
    public static boolean isNumber(String s) {
        return s != null && Pattern.compile("^(0|[1-9][0-9]*)$").matcher(s).matches();
    }
}
