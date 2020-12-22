import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LearnAwsS3Download {

    public static void displayAwsS3Data(AmazonS3 awsS3Client, String bucketName, String keyName) throws IOException {
        InputStream inputStream = getAmazonS3DataInputStream(awsS3Client, bucketName, keyName);
        // Read the text input stream one line at a time and display each line.
        System.out.println(">>Printing data");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println(">>Finished printing data");
    }

    public static InputStream getAmazonS3DataInputStream(AmazonS3 awsS3Client, String bucketName, String keyName) {
        return getAmazonS3Data(awsS3Client, bucketName, keyName).getObjectContent();
    }

    public static S3Object getAmazonS3Data(AmazonS3 awsS3Client, String bucketName, String keyName) {
        return awsS3Client.getObject(new GetObjectRequest(bucketName, keyName));
    }

}
