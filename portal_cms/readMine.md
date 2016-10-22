Maven里主程序的java文件都存放在src/main/java中，
配置文件及资源文件等存放在src/main/resources中，这两个文件编译后的文件会存放到src/target/classes中；

测试的java文件存放在src/test/java中，
测试的配置文件及资源文件存放在src/test/resources中，这两个编译后文件存放在src/target/test-class中，

src/main/webapp目录是maven web项目特有的，类似于原来的web项目的WebRoot。

Maven clean 清除上一次Maven执行的结果
Maven generate-sources会根据pom配置去生成源代码格式的包
Maven install将项目输出构件部署到本地仓库
Maven source

nexus :

hosted，本地仓库，通常我们会部署自己的构件到这一类型的仓库。 
proxy，代理仓库，它们被用来代理远程的公共仓库，如maven中央仓库。 
group，仓库组，用来合并多个hosted/proxy仓库，通常我们配置maven依赖仓库组。 