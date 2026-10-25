package org.example.core;

import java.io.File;

public interface Module {
    boolean supports(File file);
    String getDescription();
    void process(File file) throws Exception;
}
