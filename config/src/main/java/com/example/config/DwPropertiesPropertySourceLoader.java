package com.example.config;

import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DwPropertiesPropertySourceLoader implements PropertySourceLoader {
    private static final String XML_FILE_EXTENSION = ".xml";

    public DwPropertiesPropertySourceLoader() {
    }

    public String[] getFileExtensions() {
        return new String[]{"properties", "xml"};
    }

    public List<PropertySource<?>> load(String name, Resource resource) throws IOException {
        Map<String, ?> properties = this.loadProperties(resource);
        return properties.isEmpty() ? Collections.emptyList() : Collections.singletonList(new OriginTrackedMapPropertySource(name, properties));
    }

    private Map<String, ?> loadProperties(Resource resource) throws IOException {
        String filename = resource.getFilename();
        return (Map)(filename != null && filename.endsWith(".xml") ? PropertiesLoaderUtils.loadProperties(resource) : (new DwOriginTrackedPropertiesLoader(resource)).load());
    }
}