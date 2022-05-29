package io.gnelsimonyan.notesetl.factories.common;

public enum ProcessorType {
    JSON("json"),
    PARQUET("parquet");

    private String processorType;

    ProcessorType(final String processorType) {
        this.processorType = processorType;
    }
}
