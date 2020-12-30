package cn.tedu.store.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class QiNiuCloudStorageConfig {

    @Value("${oss.qiniu.domain}")
    private String qiniuDomain;

    @Value("${oss.qiniu.accessKey}")
    private String qiniuAccessKey;

    @Value("${oss.qiniu.secretKey}")
    private String qiniuSecretKey;

    @Value("${oss.qiniu.bucketName}")
    private String qiniuBucketName;

    public QiNiuCloudStorageConfig() {}

    public QiNiuCloudStorageConfig(String qiniuDomain, String qiniuAccessKey, String qiniuSecretKey, String qiniuBucketName) {
        this.qiniuDomain = qiniuDomain;
        this.qiniuAccessKey = qiniuAccessKey;
        this.qiniuSecretKey = qiniuSecretKey;
        this.qiniuBucketName = qiniuBucketName;
    }

    public String getQiniuDomain() {
        return qiniuDomain;
    }

    public void setQiniuDomain(String qiniuDomain) {
        this.qiniuDomain = qiniuDomain;
    }

    public String getQiniuAccessKey() {
        return qiniuAccessKey;
    }

    public void setQiniuAccessKey(String qiniuAccessKey) {
        this.qiniuAccessKey = qiniuAccessKey;
    }

    public String getQiniuSecretKey() {
        return qiniuSecretKey;
    }

    public void setQiniuSecretKey(String qiniuSecretKey) {
        this.qiniuSecretKey = qiniuSecretKey;
    }

    public String getQiniuBucketName() {
        return qiniuBucketName;
    }

    public void setQiniuBucketName(String qiniuBucketName) {
        this.qiniuBucketName = qiniuBucketName;
    }
}
