import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;

import java.util.List;
import java.util.Optional;

public class LearnAwsS3Buckets {

    public static synchronized void createBucketIfNotExist(AmazonS3 awsS3Client, String bucketName) {
        System.out.println("Check if bucket exists: " + bucketName);
        if (!doesBucketExistV2(awsS3Client, bucketName)) {
            System.out.println("Bucket does not exists, create one: " + bucketName);
            createBucket(awsS3Client, bucketName);
        }
    }

    public static void createBucket(AmazonS3 awsS3Client, String bucketName) {
        awsS3Client.createBucket(bucketName);
    }

    public static boolean doesBucketExist(AmazonS3 awsS3Client, String bucketName) {
        return getExistingBucket(awsS3Client, bucketName).isPresent();
    }

    public static Optional<Bucket> getExistingBucket(AmazonS3 awsS3Client, String bucketName) {
        return getAllExistingBuckets(awsS3Client).stream()
                .filter(b -> b.getName().equals(bucketName))
                .findFirst();
    }

    private static List<Bucket> getAllExistingBuckets(AmazonS3 awsS3Client) {
        return awsS3Client.listBuckets();
    }

    private static boolean doesBucketExistV2(AmazonS3 awsS3Client, String bucketName) {
        return awsS3Client.doesBucketExistV2(bucketName);
    }

}
