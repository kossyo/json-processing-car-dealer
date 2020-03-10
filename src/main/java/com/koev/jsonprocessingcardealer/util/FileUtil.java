package com.koev.jsonprocessingcardealer.util;

import java.io.IOException;

public interface FileUtil {

    String getFileContent(String filePath) throws IOException;

    void writeFile(String filePath, String outputString) throws IOException;
}
