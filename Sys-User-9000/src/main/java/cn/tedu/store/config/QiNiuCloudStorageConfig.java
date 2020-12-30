package cn.tedu.store.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QiNiuCloudStorageConfig {

    @Value("${oss.qiniu.domain}")
    private String qiniuDomain;

    @Value("${oss.qiniu.accessKey}")
    private String qiniuAccessKey;

    @Value("${oss.qiniu.secretKey}")
    private String qiniuSecretKey;

    @Value("${oss.qiniu.bucketName}")
    private String qiniuBucketName;
}
