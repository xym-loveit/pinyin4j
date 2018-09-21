package com.xym.pinyin4j;

import com.beust.jcommander.JCommander;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

/**
 * 描述类作用
 *
 * @author xym
 * @create 2018-09-21 11:42
 */
public class PinYin {
    public static void main(String[] args) {
        JCommander jCommander = null;
        try {
            PinYinOptions pinYinOptions = new PinYinOptions();
            jCommander = JCommander.newBuilder().addObject(pinYinOptions).build();
            jCommander.parse(args);
            if (pinYinOptions.isHelp()) {
                jCommander.usage();
                return;
            }

            if (Optional.ofNullable(pinYinOptions.getSource()).isPresent()) {
                Optional.ofNullable(getPinYinStr(pinYinOptions.getType(), pinYinOptions.getSource())).ifPresent(System.out::println);
            } else if (Optional.ofNullable(pinYinOptions.getInFile()).isPresent()) {
                File inFile = Paths.get(pinYinOptions.getInFile()).toFile();
                if (inFile.exists()) {
                    String outName = inFile.getName().replace(".", "_pinyin.");
                    Path outFile = Paths.get(outName);
                    try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(pinYinOptions.getInFile()));
                         BufferedWriter bufferedWriter = Files.newBufferedWriter(outFile, StandardOpenOption.CREATE)) {
                        Optional<String> lineOption;
                        while ((lineOption = getPinYinStr(pinYinOptions.getType(), bufferedReader.readLine())).isPresent()) {
                            lineOption.ifPresent((line -> {
                                try {
                                    bufferedWriter.write(line);
                                    bufferedWriter.newLine();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }));
                        }
                    }
                    Optional.ofNullable(outFile).ifPresent(f -> {
                        System.out.println(String.format("文件转换成功，path：%s", f.toFile().getAbsolutePath()));
                    });
                } else {
                    System.err.println(String.format("待转换文件不存在 %s", inFile.getAbsolutePath()));
                }
            }
        } catch (Exception e) {
            //System.err.println("当前命令有误");
            e.printStackTrace();
            jCommander.usage();
        }
    }

    private static Optional getPinYinStr(int type, String source) {
        if (Optional.ofNullable(source).isPresent()) {
            String outStr;
            switch (type) {
                case 1:
                    outStr = Pinyin4jUtil.getPinyinToLowerCase(source);
                    break;
                case 2:
                    outStr = Pinyin4jUtil.getPinyinToUpperCase(source);
                    break;
                case 3:
                    outStr = Pinyin4jUtil.getPinyinFirstToUpperCase(source);
                    break;
                case 4:
                    outStr = Pinyin4jUtil.getPinyinJianPin(source);
                    break;
                case 5:
                    outStr = Pinyin4jUtil.getPinyinJianPinLowerCase(source);
                    break;
                default:
                    outStr = Pinyin4jUtil.getPinyinToLowerCase(source);
                    break;
            }
            return Optional.ofNullable(outStr);
        }
        return Optional.empty();
    }
}
