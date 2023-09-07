package moe.furryverse.server.hecatebolus.service;

import io.minio.DeleteObjectTagsArgs;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import moe.furryverse.server.hecatebolus.config.MinioConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MinioService {
    final MinioConfiguration minioConfiguration;

    @SneakyThrows
    public String uploadFile(String fileId, MultipartFile file) {
        minioConfiguration.minioClient().putObject(
                PutObjectArgs.builder()
                        .bucket(minioConfiguration.getMinioBucket())
                        .object(fileId)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build()
        );

        return String.format("%s/%s/%s", minioConfiguration.getMinioEndpoint(), minioConfiguration.getMinioBucket(), fileId);
    }

    @SneakyThrows
    public void deleteFile(String fileId) {
        minioConfiguration.minioClient().deleteObjectTags(
                DeleteObjectTagsArgs.builder()
                        .bucket(minioConfiguration.getMinioBucket())
                        .object(fileId)
                        .build()
        );
    }
}
