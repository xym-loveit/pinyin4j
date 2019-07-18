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

    @Parameter(names = {"-t", "-type"}, description = "转换类型\n\t[1:小写全拼；2:大写全拼：3:首字符大写全拼；4:小写简写全拼；5:大写简写全拼；6:姓全拼，名首字母；7:选项6的外国人的姓名方式；8:外国人的姓名方式，全拼小写]", required = false)
    private Integer type;

    @Parameter(names = {"-ts", "-types"}, validateWith = TypesValidator.class, description = "转换类型复数形式[e.g. -ts 1,2,3]，逗号分隔", required = false)
    private String types;

    @Parameter(names = {"-if", "-infile"}, description = "待转换输入文件", required = false)
    private String inFile;

    @Parameter(names = {"-e", "-encoding"}, description = "文件编码格式", required = false)
    private String encoding;

    @Parameter(names = {"--help", "-h", "-man"}, description = "Help/Usage", help = true)
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

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }
}