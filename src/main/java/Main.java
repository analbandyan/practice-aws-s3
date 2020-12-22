import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.transfer.TransferManager;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class Main {

    public static void main(String[] args) throws IOException {
        String bucketName = "testbucketbame";
        String keyName = "key" + System.currentTimeMillis();
        String filePath = "./testfileupload";

        AmazonS3 awsS3Client = AwsS3Utils.createAwsS3Client();
        TransferManager awsS3TransferManager = AwsS3Utils.createAwsS3TransferManager(awsS3Client);

        LearnAwsS3Buckets.createBucketIfNotExist(awsS3Client, bucketName);

        upload(awsS3TransferManager, bucketName, keyName, filePath);

        LearnAwsS3Download.displayAwsS3Data(awsS3Client, bucketName, keyName);
    }

    private static void upload(TransferManager awsS3TransferManager, String bucketName, String keyName, String filePath) {
        System.out.println("Scheduling upload");
        CompletableFuture<Void> uploadCompletableFuture =
                LearnAwsS3Upload.upload(
                        awsS3TransferManager,
                        bucketName,
                        keyName,
                        Utils.getFileInputStream(filePath),
                        Utils.getFileContentLength(filePath)
                );

        System.out.println("Scheduled upload");
        uploadCompletableFuture.join();
        System.out.println("Upload completed");
    }

}
