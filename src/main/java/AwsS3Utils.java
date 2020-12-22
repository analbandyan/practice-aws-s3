import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;

public class AwsS3Utils {

    public static AmazonS3 createAwsS3Client() {
        Regions clientRegion = Regions.DEFAULT_REGION;
        return AmazonS3ClientBuilder.standard()
                .withRegion(clientRegion)
                .withCredentials(new ProfileCredentialsProvider())
//                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY)))
                .build();
    }

    public static TransferManager createAwsS3TransferManager(AmazonS3 awsS3Client) {
        return TransferManagerBuilder.standard()
                .withS3Client(awsS3Client)
                .build();
    }

}
