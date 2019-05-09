package com.xym.pinyin4j;

import com.github.promeg.pinyinhelper.Pinyin;
import com.github.promeg.pinyinhelper.PinyinMapDict;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author: laohu on 2016/12/17
 * @site: http://ittiger.cn
 */
public class PinYinHelper {

    private static final String PATTERN_LETTER = "^[a-zA-Z]+$";


    static {
        Pinyin.init(Pinyin.newConfig()
                .with(new PinyinMapDict() {
                    @Override
                    public Map<String, String[]> mapping() {
                        HashMap<String, String[]> map = new HashMap<String, String[]>();
                        map.put("哦", new String[]{"O"});
                        return map;
                    }
                }));
    }

    /**
     * 将中文转换为pinyin
     */
    public static String getPingYin(String inputString, String separator) {
        if (separator == null) {
            separator = "";
        }
        char[] input = inputString.trim().toCharArray();
        StringBuffer output = new StringBuffer();
        for (int i = 0; i < input.length; i++) {
            //if (isLetter(String.valueOf(input[i]))) {
            output.append(Pinyin.toPinyin(String.valueOf(input[i]), separator));
            //}
        }
        return output.toString();
    }

    /**
     * 是否为字母
     *
     * @param inputString
     * @return
     */
    public static boolean isLetter(String inputString) {
        return Pattern.matches(PATTERN_LETTER, inputString);
    }

    public static String getPinyinSimple(String str) {
        String pinyin = Pinyin.toPinyin(str, ",");
        String[] strings = pinyin.split(",");

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            String string = strings[i];
            res.append(string.charAt(0));
        }
        return res.toString().replaceAll(" ", "");
    }

    /**
     * 全拼小写
     *
     * @param source
     * @return
     */
    public static String getPinyinToLowerCase(String source) {
        return getPingYin(source, "").toLowerCase();
    }

    /**
     * 全拼大写
     *
     * @param source
     * @return
     */
    public static String getPinyinToUpperCase(String source) {
        return getPingYin(source, "").toUpperCase();
    }

    /**
     * 首字母大写
     *
     * @param source
     * @return
     */
    public static String getPinyinFirstToUpperCase(String source) {
        String pinyin = Pinyin.toPinyin(source, ",");
        String[] strings = pinyin.toLowerCase().split(",");

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            String string = strings[i];
            res.append(String.valueOf(string.charAt(0)).toUpperCase()).append(string.substring(1));
        }
        return res.toString().replaceAll(" ", "");
    }

    /**
     * 简拼大写
     *
     * @param source
     * @return
     */
    public static String getPinyinJianPin(String source) {
        return getPinyinSimple(source).toUpperCase();
    }

    /**
     * 简拼小写
     *
     * @param source
     * @return
     */
    public static String getPinyinJianPinLowerCase(String source) {
        return getPinyinSimple(source).toLowerCase();
    }

    public static void main(String[] args) {
        String strOrg = "你个板货喽子，憨水妥妥神的，看到好求招爷，连慢到高头喊你妈回克把毛湿挨身的弯刀拿出来把狗甲给你垮垮，光鼻子化眼的儿娃子，糊的正求另人，二回还有哪个俩娃跟你";
        //String pingYin = getPingYin(strOrg, null);
        //String str = getPinyinSimple(strOrg);
        //System.out.println(pingYin.toLowerCase());
        //System.out.println(str);
        System.out.println(getPinyinFirstToUpperCase(strOrg));
        System.out.println(getPinyinJianPinLowerCase(strOrg));
    }
}