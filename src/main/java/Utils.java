import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Utils {

    public static InputStream getFileInputStream(String filePath) {
        try {
            return new FileInputStream(new File(filePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static long getFileContentLength(String filePath) {
        return new File(filePath).length();
    }

}
