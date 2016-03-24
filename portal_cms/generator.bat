@echo on
rem
cd /d %~dp0
call java -jar G:/Repository/Maven/org/mybatis/generator/mybatis-generator-core/1.3.2/mybatis-generator-core-1.3.2.jar -configfile G:/Workspace/portal_cms/generatorConfig.xml -overwrite