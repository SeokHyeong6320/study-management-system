package com.zerobase.fastlms.aahomework;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadService  {

    @Value("${file.dir}")
    private String fileDir;

    public String getFullFilePath() {
        return fileDir + UUID.randomUUID().toString();
    }


}
