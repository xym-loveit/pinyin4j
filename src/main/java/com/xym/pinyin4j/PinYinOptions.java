package com.xym.pinyin4j;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

/**
 * @author xym
 */
@Parameters(separators = "=", commandDescription = "各种拼音转换")
public class PinYinOptions {

    @Parameter(names = {"-s", "-str"}, description = "待转换字符", required = false)
    private String source;

    @Parameter(names = {"-t", "-type"}, description = "转换类型\n\t[1:小写全拼；2:大写全拼：3:首字符大写全拼；4:小写简写全拼；5:大写简写全拼]", required = false)
    private Integer type = 1;

    @Parameter(names = {"-if", "-infile"}, description = "待转换输入文件", required = false)
    private String inFile;

    @Parameter(names = {"--help", "-h"}, description = "Help/Usage", help = true)
    private boolean help;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean isHelp() {
        return help;
    }

    public String getInFile() {
        return inFile;
    }

    public void setInFile(String inFile) {
        this.inFile = inFile;
    }

    public void setHelp(boolean help) {
        this.help = help;
    }
}