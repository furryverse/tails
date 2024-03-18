/*
 * Copyright 2024 FurryVerse
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *           http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package moe.furryverse.tails.service;

import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import moe.furryverse.tails.config.StorageConfiguration;
import moe.furryverse.tails.model.FileRecord;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class FileService {
    final Auth auth;
    final StorageConfiguration storageConfiguration;

    public FileService(StorageConfiguration storageConfiguration) {
        this.storageConfiguration = storageConfiguration;

        this.auth = Auth.create(
                storageConfiguration.getQiniuAccessKey(),
                storageConfiguration.getQiniuAccessKey()
        );
    }

    public FileRecord upload(InputStream stream) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
        // 指定分片上传版本
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);

        return null;
    }

    public FileRecord get(String id) {
        return null;
    }

    public FileRecord delete(String id) {
        return null;
    }
}
