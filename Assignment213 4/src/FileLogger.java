import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger {
    private static final String FILE_LOGGER_NAME =  "StudentFileOutput.txt";

    static {

        /** TODO
         * create a new File object for FILE_LOGGER_NAME
         * if the file already exists, delete it first
         * use try/catch block
         */
        File file = new File(FILE_LOGGER_NAME);
        if (file.exists())
            file.delete();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void log (String message) {
        try(
            FileWriter writer = new FileWriter(FILE_LOGGER_NAME,true);)
        {
            writer.write(message +"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        /** TODO
         * create a new FileWriter in append mode
         * write the message to file
         * check the ExpectedOutput files
         * use try-with-resources/catch block
         */
    }
}
