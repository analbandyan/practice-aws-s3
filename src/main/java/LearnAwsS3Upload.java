import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;

import java.io.InputStream;
import java.util.concurrent.CompletableFuture;

public class LearnAwsS3Upload {

    public static CompletableFuture<Void> upload(TransferManager awsS3TransferManager, String bucketName, String keyName, InputStream dataInputStream, long contentLength) {

        Upload upload = doUpload(awsS3TransferManager, bucketName, keyName, dataInputStream, contentLength);

        return CompletableFuture.supplyAsync(() -> waitForCompletion(upload));
    }

    private static Void waitForCompletion(Upload upload) {
        try {
            upload.waitForCompletion();
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return null;
    }

    private static Upload doUpload(TransferManager awsS3TransferManager, String bucketName, String keyName, InputStream dataInputStream, long contentLength) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(contentLength);
        return awsS3TransferManager.upload(bucketName, keyName, dataInputStream, objectMetadata);
    }

}
