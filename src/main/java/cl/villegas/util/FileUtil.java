package cl.villegas.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {
    private final static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static byte[] getBytesOfFileByPathAndNameAndType(String path, String name, String type) {
        File file = new File(path + name + type);
        FileInputStream fileInputStream;
        byte[] bytes = new byte[(int) file.length()];
        try {
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bytes);
            fileInputStream.close();
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
        return bytes;
    }
}