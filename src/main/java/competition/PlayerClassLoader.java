package competition;

import java.io.*;

/**
 * Custom implementation of {@link ClassLoader} to load the classes from the .class files
 */
public class PlayerClassLoader extends ClassLoader {
    public Class<?> loadClassFromFile(String className, String filePath) throws IOException {
        File file = new File(filePath);
        byte[] bytes = loadFileBytes(file);
        return defineClass(className, bytes, 0, bytes.length);
    }

    private byte[] loadFileBytes(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
            return bos.toByteArray();
        }
    }
}