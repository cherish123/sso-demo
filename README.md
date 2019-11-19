# sso-demo
### 访问受保护的资源/phone
      启动sso-server和sso-client4
      访问http://127.0.0.1:8090/
      输入user/123或者admin123
      返回phone:13285155519

###  多服务提供商并存(qq,github)
      github注册回调域为http://www.pinzhi365.com/qqLogin/github
      启动ssso-client3（端口改为80）
      访问http://www.pinzhi365.com
      git登录时能正常访问，qq会显示redirect_uri异常（registrationId配置的是callback，
      正确的应该是callback.do（暂时不能配置带点），
      可配置自己注册的qq第三方）
     
