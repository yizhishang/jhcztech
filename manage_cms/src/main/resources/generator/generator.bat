@echo on
rem
cd /d %~dp0
call java -jar D:/MavenRepository/org/mybatis/generator/mybatis-generator-core/1.3.2/mybatis-generator-core-1.3.2.jar -configfile F:\Repositories\git\jhcztech\manage_cms\src\main\resources\generator\generatorConfig.xml -overwrite