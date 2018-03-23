package com.duwei.pay;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.lang.Nullable;

import java.text.MessageFormat;
import java.util.Locale;

public class DwMessageSource extends AbstractMessageSource implements ResourceLoaderAware {

    private  ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
          this.resourceLoader = resourceLoader;
    }

    @Nullable
    @Override
    protected MessageFormat resolveCode(String s, Locale locale) {
        String ss = s;
        Locale localll = locale;
        return null;
    }
}
