package com.xym.pinyin4j;

import com.beust.jcommander.JCommander;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;
import java.util.stream.Stream;

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
                    //try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(pinYinOptions.getInFile()), Charset.forName("GBK"));
                    //     BufferedWriter bufferedWriter = Files.newBufferedWriter(outFile, StandardOpenOption.CREATE)) {
                    //    Optional<String> lineOption;
                    //    while ((lineOption = getPinYinStr(pinYinOptions.getType(), bufferedReader.readLine())).isPresent()) {
                    //        lineOption.ifPresent((line -> {
                    //            try {
                    //                bufferedWriter.write(line);
                    //                bufferedWriter.newLine();
                    //            } catch (IOException e) {
                    //                e.printStackTrace();
                    //            }
                    //        }));
                    //    }
                    //}

                    try (BufferedWriter bufferedWriter = Files.newBufferedWriter(outFile, StandardOpenOption.CREATE);
                         Stream<String> lines = Files.lines(Paths.get(pinYinOptions.getInFile()), Charset.forName(Optional.ofNullable(pinYinOptions.getEncoding()).orElse("GBK"))).parallel()) {
                        lines.forEach(line -> {
                            //System.out.println("line--" + line);
                            getPinYinStr(pinYinOptions.getType(), line).ifPresent(ln -> {
                                try {
                                    bufferedWriter.write(ln);
                                    bufferedWriter.newLine();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });
                        });
                    } catch (MalformedInputException e) {
                        System.err.println("请指定文件编码格式参数");
                        return;
                    }

                    Optional.ofNullable(outFile).ifPresent(f -> {
                        System.out.println(String.format("文件转换成功，path：%s", f.toFile().getAbsolutePath()));
                    });
                } else {
                    System.err.println(String.format("待转换文件不存在 %s", inFile.getAbsolutePath()));
                }
            }
        } catch (MalformedInputException e) {
            System.err.println("请指定文件编码格式参数");
            return;
        } catch (Exception e) {
            //System.err.println("当前命令有误");
            e.printStackTrace();
            jCommander.usage();
        }
    }

    private static Optional<String> getPinYinStr(int type, String source) {
        if (Optional.ofNullable(source).isPresent()) {
            String outStr;
            switch (type) {
                case 1:
                    outStr = PinYinHelper.getPinyinToLowerCase(source);
                    break;
                case 2:
                    outStr = PinYinHelper.getPinyinToUpperCase(source);
                    break;
                case 3:
                    outStr = PinYinHelper.getPinyinFirstToUpperCase(source);
                    break;
                case 4:
                    outStr = PinYinHelper.getPinyinJianPin(source);
                    break;
                case 5:
                    outStr = PinYinHelper.getPinyinToLowerCase(source);
                    break;
                case 6:
                    outStr = PinYinHelper.getPinyinToLowerCaseWithFirst(source);
                    break;
                case 7:
                    outStr = PinYinHelper.getPinyinToLowerCaseWithFirstInversion(source);
                    break;
                case 8:
                    outStr = PinYinHelper.getPinyinToLowerCaseInversion(source);
                    break;
                default:
                    outStr = PinYinHelper.getPinyinJianPinLowerCase(source);
                    break;
            }
            return Optional.ofNullable(outStr);
        }
        return Optional.empty();
    }
}
