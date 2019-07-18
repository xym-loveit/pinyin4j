package com.xym.pinyin4j;

import com.beust.jcommander.IParameterValidator2;
import com.beust.jcommander.ParameterDescription;
import com.beust.jcommander.ParameterException;

import java.util.Arrays;

/**
 * Types验证
 *
 * @author xym
 * @create 2019-07-18 11:33
 */
public class TypesValidator implements IParameterValidator2 {
    @Override
    public void validate(String name, String value, ParameterDescription pd) throws ParameterException {

    }

    @Override
    public void validate(String name, String value) throws ParameterException {
        if (StringUtils.isNotBlank(value)) {
            if (Arrays.stream(value.split(",")).anyMatch((s) -> !StringUtils.isNumber(s))) {
                throw new ParameterException("Parameter " + name + " should be numbers e.g. -ts 1,2,3,4");
            }
        }
    }
}
