@echo on
rem
cd /d %~dp0
call java -jar G:/Repository/Maven/org/mybatis/generator/mybatis-generator-core/1.3.2/mybatis-generator-core-1.3.2.jar -configfile G:\Repository\Git\jhcztech\portal_cms\src\main\resources\generator\generatorConfig.xml -overwrite