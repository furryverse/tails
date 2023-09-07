package moe.furryverse.server.hecatebolus.config;

import io.minio.MinioClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Data
@Configuration
public class MinioConfiguration {
    @Value("${minio.endpoint}")
    String minioEndpoint;
    @Value("${minio.access.key}")
    String minioAccessKey;
    @Value("${minio.secret.secret}")
    String minioAccessSecret;
    @Value("${minio.bucket}")
    String minioBucket;

    @Bean
    @Primary
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(minioEndpoint)
                .credentials(minioAccessKey, minioAccessSecret)
                .build();
    }
}
