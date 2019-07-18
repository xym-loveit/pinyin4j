package com.xym.pinyin4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2019-07-18 12:01
 */
public class StringUtils {

    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    static final Pattern regex = Pattern.compile("(-)?\\d*(.\\d*)?");

    public static boolean isNumber(String str) {
        if (isBlank(str)) {
            return false;
        } else {
            Matcher matcher = regex.matcher(str);
            return matcher.matches();
        }
    }

    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }

}
