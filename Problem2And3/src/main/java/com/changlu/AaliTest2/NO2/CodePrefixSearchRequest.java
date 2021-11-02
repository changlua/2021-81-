package com.changlu.AaliTest2.NO2;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.fc.runtime.Context;
import com.aliyun.fc.runtime.FunctionComputeLogger;
import com.aliyun.fc.runtime.FunctionInitializer;
import com.aliyun.fc.runtime.StreamRequestHandler;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName CodePrefixSearchRequest
 * @Author ChangLu
 * @Date 2021/10/27 19:03
 * @Description TODO
 */
public class CodePrefixSearchRequest implements StreamRequestHandler, FunctionInitializer {

    @Override
    public void initialize(Context context) throws IOException {
        FunctionComputeLogger logger = context.getLogger();
        logger.debug(String.format("RequestID is %s %n", context.getRequestId()));
    }

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
        FunctionComputeLogger logger = context.getLogger();
        logger.debug(String.format("RequestID is %s %n", context.getRequestId()));
        String inputJson = IOUtils.toString(input, "UTF-8");
        if (inputJson == null || "".equals(inputJson)) {
            error(output, "invalid input json string.");
            return;
        }
        // 解析输入JSON数据
        JSONObject params = JSON.parseObject(inputJson);
        if (params == null) {
            error(output, "invalid input json data.");
            return;
        }
        // 创建OSS Client
        OSS ossClient = createOSSClient(params);
        // 实现算法并返回结果
        Map<String, List<String>> result = calculate(ossClient, params);
        // 输出结果
        output.write(JSON.toJSONString(result).getBytes());
        output.flush();
    }

    @SuppressWarnings("unused")
    private OSS createOSSClient(JSONObject params) {
        String endpoint = params.getString("oss_endpoint");
        String accessKeyId = params.getString("access_key");
        String secretAccessKey = params.getString("access_secret");
        // TODO 创建OSS Client，建议参照OSS文档 https://help.aliyun.com/document_detail/32010.html
        return new OSSClientBuilder().build(endpoint, accessKeyId, secretAccessKey);
    }

    @SuppressWarnings("unused")
    private Map<String, List<String>> calculate(OSS ossClient, JSONObject params) throws IOException {
        // OSS文件地址
        String ossFileKey = params.getString("oss_key");
        // OSS Bucket
        String ossBucket = params.getString("bucket");
        // 待计算的前缀字符串集合
        JSONArray prefixs = params.getJSONArray("prefixs");
        // 计算结果，key为前缀字符串，value为匹配前缀的字符串集合
        Map<String, List<String>> result = new HashMap<>();

        // TODO 获取OSS文件数据，建议参照OSS文档 https://help.aliyun.com/document_detail/84823.html
        OSSObject ossObject = ossClient.getObject(ossBucket,ossFileKey);

        // TODO 实现前缀字符串查找算法，建议考虑百万数据集情况下的性能及内存消耗问题
        InputStream objectContent = ossObject.getObjectContent();
        String content = IOUtils.toString(ossObject.getObjectContent(), "UTF-8");
        String[] strArr = content.split("\n");
        //构建结果集
        prefixs.stream().forEach(prefix->{
            String prefixStr = (String) prefix;
            //过滤匹配
            result.put(prefixStr,Arrays.stream(strArr).filter(str->str.startsWith(prefixStr)).collect(Collectors.toList()));
        });
        return result;
    }

    private void error(OutputStream output, String message) throws IOException {
        Map<String, String> msg = new HashMap<>();
        msg.put("errorMessage", message);
        output.write(JSON.toJSONString(msg).getBytes());
        output.flush();
    }
}