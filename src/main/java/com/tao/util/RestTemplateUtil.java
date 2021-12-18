package com.tao.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * TestTemplate配置类：配置和获取RestTemplate对象
 *
 * @author hetao
 * @since 2021/7/13
 */
@Slf4j
public class RestTemplateUtil {

    private static final RestTemplate restTemplate = new RestTemplate();

    static {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(30000);
        factory.setReadTimeout(30000);
        restTemplate.setRequestFactory(factory);
        JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.DisableCircularReferenceDetect.getMask();
        {
            final FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
            converter.setSupportedMediaTypes(
                    Arrays.asList(
                            MediaType.APPLICATION_JSON,
                            MediaType.APPLICATION_OCTET_STREAM,
                            //MediaType.APPLICATION_FORM_URLENCODED,
                            MediaType.TEXT_HTML,
                            MediaType.TEXT_PLAIN
                    )
            );
            final FastJsonConfig fastJsonConfig = converter.getFastJsonConfig();
            fastJsonConfig.setFeatures(Feature.OrderedField);
            fastJsonConfig.setCharset(UTF_8);
            //封装格式类型
            restTemplate.getMessageConverters().add(1, converter);
            restTemplate.getMessageConverters().add(0,new StringHttpMessageConverter());
        }
    }

    public static RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public static <T> T post(final String url, final Object bodyParams, HttpHeaders headers, final TypeReference<T> typeReference) {
        try {
            HttpEntity<Object> objectHttpEntity = new HttpEntity<>(bodyParams, headers);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, objectHttpEntity, String.class);
            log.info("请求地址:{},请求头:{},请求参数：{},响应结果:{}", url, JSON.toJSONString(headers), JSON.toJSONString(bodyParams), JSON.toJSONString(responseEntity.getBody()));
            if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
                //return JSON.parseObject(responseEntity.getBody(), typeReference);
                return null;
            } else {
                return null;
            }
        } catch (HttpClientErrorException clientErrorException) {
            log.info("请求地址:{},请求头:{},请求参数：{},响应结果:{}", url, JSON.toJSONString(headers), JSON.toJSONString(bodyParams), clientErrorException.getResponseBodyAsString());
            if (Objects.nonNull(clientErrorException.getResponseHeaders())) {
                MediaType contentType = clientErrorException.getResponseHeaders().getContentType();
                if (Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON_UTF8).contains(contentType)) {
                    return JSON.parseObject(clientErrorException.getResponseBodyAsString(), typeReference);
                }
            }
            log.error(clientErrorException.getMessage(), clientErrorException);
            return null;
        } catch (Exception e) {
            log.info("请求地址:{},请求头:{},请求参数：{}", url, JSON.toJSONString(headers), JSON.toJSONString(bodyParams));
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static <T> T post(final String url, final Object bodyParams, final TypeReference<T> typeReference) {
        return post(url, bodyParams, null, typeReference);
    }

    public static <T> T get(final String url, final Map<String, Object> queryParams, final TypeReference<T> typeReference) {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, queryParams);
            log.info("请求地址:{},请求参数：{},响应结果:{}", url, JSON.toJSONString(queryParams), JSON.toJSONString(response.getBody()));
            if (HttpStatus.OK.equals(response.getStatusCode())) {
                return JSON.parseObject(response.getBody(), typeReference);
            } else {
                return null;
            }
        } catch (Exception e) {
            log.info("请求地址:{},请求参数：{}", url, JSON.toJSONString(queryParams));
            log.error(e.getMessage(), e);
            return null;
        }
    }


}
