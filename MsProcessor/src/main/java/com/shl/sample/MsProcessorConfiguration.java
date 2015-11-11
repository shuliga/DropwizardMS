package com.shl.sample;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shl.sample.qualifiers.Prefix;
import com.shl.sample.qualifiers.Root;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by yshuliga on 06.11.2015.
 */
public class MsProcessorConfiguration extends Configuration {

    @Valid
    @NotNull
    @NotEmpty
    @JsonProperty
    private String processorRoot;

    @Valid
    @NotNull
    @NotEmpty
    @JsonProperty
    private String imagePrefix;

    public String getProcessorRoot() {
        return processorRoot;
    }

    public void setProcessorRoot(String processorRoot) {
        this.processorRoot = processorRoot;
    }

    public String getImagePrefix() {
        return imagePrefix;
    }

    public void setImagePrefix(String imagePrefix) {
        this.imagePrefix = imagePrefix;
    }

}
