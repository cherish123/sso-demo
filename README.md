# sso-demo
### 访问受保护的资源(资源与认证服务器在一台机器)/phone
      启动sso-server和sso-client4
      访问http://127.0.0.1:8090/
      输入user/123或者admin/123
      返回phone:13285155519
      
### 访问受保护的资源(资源服务器与认证服务器分离)/phone
      启动sso-server和sso-client4和resource-server
      访问http://127.0.0.1:8090/resource
      输入user/123或者admin/123
      返回resource
      
      原理：资源服务器收到OAuth客户端资源请求（http://127.0.0.1:9090/resource1/resource）-> 
           资源服务器携带客户端传来的access_token，请求授权服务器用户信息端口（user-info-uri),构建认证对象

###  多服务提供商并存(qq,github)
      github注册回调域为http://www.pinzhi365.com/qqLogin/github
      启动ssso-client3（端口改为80）
      访问http://www.pinzhi365.com
      git登录时能正常访问，qq会显示redirect_uri异常（registrationId配置的是callback，
      正确的应该是callback.do（暂时不能配置带点），
      可配置自己注册的qq第三方）
     
