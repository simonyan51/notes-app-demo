package io.gnelsimonyan.notesetl.factories.common;

public enum WriterType {
    FILE("file"),
    S3("s3");

    private String fileType;

    WriterType(final String fileType) {
        this.fileType = fileType;
    }
}
