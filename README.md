# remember-word
## 该项目只完成主要的生成单词音频文件以及配合Nginx实现动静分离

### 演示地址
https://znsd.top:3009

### 使用步骤
1. 有道云申请开发者账号
2. 修改properties中的配置
3. 更多功能可自行完成,本项目只提供思路
4. nginx动静分离配置
```nginx
server {
    listen 80;
    listen 3009 ssl http2;
    server_name znsd.top;



    #ssl on;
    ssl_certificate /etc/nginx/ssl/5086991_znsd.top.pem;
    ssl_certificate_key /etc/nginx/ssl/5086991_znsd.top.key;
    ssl_protocols SSLv3 TLSv1 TLSv1.1 TLSv1.2;
    #ssl_ciphers ALL:!ADH:!EXPORT56:RC4+RSA:+HIGH:+MEDIUM:+LOW:+SSLv2:+EXP;
     proxy_set_header X-Forwarded-Proto  $scheme;
    #手动启用 cipher 列表;
    ssl_prefer_server_ciphers on;
    ssl_ciphers 'EECDH+AESGCM:EDH+AESGCM:AES256+EECDH:AES256+EDH';
    ssl_buffer_size 4k;

    ssl_session_cache   shared:SSL:50m;
#speed up first time. 1m ~= 4000 connections
    ssl_session_timeout 4h;


    location ~* /page/audio/ {

        root /etc/nginx  ;
        if (!-f $request_filename) {
            #proxy_pass http:// 172.17.0.4:9527;
            #rewrite /page/audio /audio break;
            rewrite ^/page/audio/(.+?).mp3$ /audio/$1.mp3 break;
        }
    }

      location ~* /static/ {

        root /etc/nginx  ;
    }

    location ~* ^/page/ {


        root /etc/nginx/static;
        if (!-f $request_filename) {
            proxy_pass http://172.17.0.1:9527;
            break;
        }
    }

    location / {
        proxy_pass  http://znsd.top:9527/;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_redirect     off;

        proxy_set_header X-Forwarded-Proto  $scheme;
        ### Most PHP, Python, Rails, Java App can use this header -> https ###
              proxy_buffer_size 4k;
        proxy_buffers 4 64k;
        proxy_temp_file_write_size 64k;

        client_body_buffer_size 128k;

        client_max_body_size 1024m;
        proxy_connect_timeout 1;
        proxy_send_timeout 30;
        proxy_read_timeout 60;

    }
}

```
