package com.scuptel.falemais.util;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class MessageUtil {

    @Autowired
    private MessageSource messageSource;

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public List<String> getMessage(List<String> codes){
        if(CollectionUtils.isEmpty(codes)){
            return codes;
        }

        List<String> messages = new ArrayList<>();

        for(String code : codes){
            messages.add(getMessage(code));
        }

        return messages;
    }

    public String getMessage(String code){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, new Object[] {} , locale);
    }
}
