package com.org.QiNiouUtils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author HP
 * @Date 2021/11/27 15:27
 * @Version 1.0
 * 其它事与我无关，多看一眼都是愚蠢的。
 * 享有特权而没有力量的人是废物，
 * 受过教育而无影响力的人是一堆一文不值的垃圾。
 */

public class QiNiou {
    public static final String DOMAIN= "http://qotintfhc.hn-bkt.clouddn.com/";
    String AccessKey="IVXJgcfHKRlsQ4fS2E533mK8ftxt4MBxfryFAnkO";
    String SecretKey="Svhs6OnzrBUsiMABhhZJo-_iAeB8MuAyEFYoVugA";
//     空间名
    String bucket="firstbbs";
    // name表示的是文件名,dir表示的为文件地址
    public void AddFile(String name,String dir){
        // 构建一个Zone配置类对象
        Configuration cfg = new Configuration(Zone.zone0());
        // 上传管理
        UploadManager uploadManager = new UploadManager(cfg);
        String localFille=dir;
        String rename=name;
        String bucket="firstbbs";
        // 进行认证
        Auth auth = Auth.create(AccessKey, SecretKey);
        // 上传令牌
        String uploadToken = auth.uploadToken(bucket);
        try {
            // 提交文件，文件名，认证令牌
            Response put = uploadManager.put(localFille, rename, uploadToken);
            String address = put.address;
            System.out.println(address);
        } catch (QiniuException e) {
            e.printStackTrace();
            Response response = e.response;
            System.out.println(response.toString());
        }
    }


    // 删除空间中的文件
    public void Delete(String name){
        Configuration configuration = new Configuration(Zone.zone0());
        String rename=name;
        String bucket="firstbbs";
        Auth auth = Auth.create(AccessKey, SecretKey);
        BucketManager manager = new BucketManager(auth, configuration);
        try {
            manager.delete(bucket,rename);
        } catch (QiniuException e) {
            e.printStackTrace();
            System.out.println(e.code());
            System.out.println(e.response.toString());

        }
    }

    // 字节的方式来上传图片

    /**
     *
     * @param savedFilename 文件名
     * @param bytes     文件字节
     */
    public void ByteUpload(String savedFilename,byte[] bytes)  {
        Configuration configuration = new Configuration(Zone.zone0());
        UploadManager uploadManager = new UploadManager(configuration);
        Auth auth = Auth.create(AccessKey, SecretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(bytes, savedFilename, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
            throw new RuntimeException("上传文件失败");
        }
    }


    // 获取到空间中的文件列表

    public ArrayList<String> GetFile(){
        ArrayList<String> list = new ArrayList<>();
        Configuration configuration = new Configuration(Zone.zone0());
        Auth auth = Auth.create(AccessKey, SecretKey);
        // 空间管理器
        BucketManager bucketManager = new BucketManager(auth, configuration);
        BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(bucket, "");
        while (fileListIterator.hasNext()){
            for (FileInfo fileInfo : fileListIterator.next()) {
                list.add(fileInfo.key);
            }
        }
        return list;
    }

    // 批量删除文件

    /**
     *
     * @param list  要被删除的图片文件集合
     */
    public  void DeleteFile(List<String> list){
        Configuration configuration = new Configuration(Zone.zone0());
        Auth auth = Auth.create(AccessKey, SecretKey);
        BucketManager bucketManager = new BucketManager(auth, configuration);
        BucketManager.Batch batch = new BucketManager.Batch();
        for (String s : list) {
            Delete(s);
        }
    }
}
