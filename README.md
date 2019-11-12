# sso-demo
v1.0版本，访问http://127.0.0.1:8088/client1/index.html，用户名：test 密码123456
v1.1版本，通过新增两个类，实现了用户静默授权（通过改写/oauth/confirm_access的请求的处理），感兴趣的同志们还可以通过ViewControllerRegistry或者AuthorizationServerEndpointsConfigurer来改变系统默认的/oauth/confirm_access的路径
