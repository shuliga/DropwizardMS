package com.shl.sample.resources;

import com.shl.sample.SampleProcessor;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 * Created by yshuliga on 07.11.2015.
 */
public class MsProcessorBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(SampleProcessor.class).to(SampleProcessor.class);
    }
}
