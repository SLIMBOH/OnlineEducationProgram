package com.suhao.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.suhao.oledu.handler.exception.MyException;
import com.suhao.vod.service.VodService;
import com.suhao.vod.util.InitialVodClient;
import com.suhao.vod.util.VodUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Service
public class VodServiceImpl implements VodService {

    @Override
    public String uploadVideoAly(MultipartFile file) {

        try {

            String fileName = file.getOriginalFilename();
            String title = fileName.substring(0, fileName.lastIndexOf("."));
            InputStream inputStream = file.getInputStream();

            UploadStreamRequest request = new UploadStreamRequest(VodUtil.ACCESS_KEY_ID, VodUtil.ACCESS_KEY_SECRET, title, fileName, inputStream);

            request.setApiRegionId("ap-northeast-1");

            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);

            return response.getVideoId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void batchRemoveVideos(List videoIdList) {
        try {
            DefaultAcsClient client = InitialVodClient.initVodClient(VodUtil.ACCESS_KEY_ID, VodUtil.ACCESS_KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            String videoIds = StringUtils.join(videoIdList.toArray(), ",");

            request.setVideoIds(videoIds);
            client.getAcsResponse(request);
        }catch(Exception e) {
            e.printStackTrace();
            throw new MyException(20001,"Failed to batch delete the videos");
        }
    }
}
