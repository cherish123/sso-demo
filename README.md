# sso-demo
v1.0版本，访问http://127.0.0.1:8088/client1/index.html，用户名：test 密码123456


v1.1版本，通过新增两个类，实现了用户静默授权（通过改写/oauth/confirm_access的请求的处理），感兴趣的同志们还可以通过ViewControllerRegistry或者AuthorizationServerEndpointsConfigurer来改变系统默认的/oauth/confirm_access的路径


bug/001 （添加了server.servlet.context-path）修复了feature/001 上切换client1和client2时需要多次授权的问题，网上查找可能是由于本地单体开发引发的。
本地开发，server与client1和client2都是localhost，造成JSESSIONID相互影响问题。可以通过配置context-path或者session名称来解决


feature/002 访问受资源服务器保护的api（sso-server和sso-client3模块）以及支持多种服务提供商并存（sso-client3）
