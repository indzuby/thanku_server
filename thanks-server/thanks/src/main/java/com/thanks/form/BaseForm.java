package com.thanks.form;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

/**
 * api에서 요청할 때 사용하기 위한 폼
 */
public abstract class BaseForm {
    public static ModelMapper modelMapper = new ModelMapper();
    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }
}
