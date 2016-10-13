/*     说明:若已经存在相应的用户和表空间，则需要先删除相应的用户和表空间 然后再全部重新建立 */
--删除用户 
drop user jhcms cascade;

--删除表空间
drop tablespace JH_DATA_TEMP including contents and datafiles; 
drop tablespace JH_DATA      including contents and datafiles;

--创建临时表空间
create temporary tablespace JH_DATA_TEMP tempfile 'JH_DATA_TEMP.DBF' size 500M autoextend on;

--创建表空间
create tablespace JH_DATA logging datafile 'JH_DATA.DBF' size 500M autoextend on;

--创建用户并指定表空间
create user jhcms identified by 888888 default tablespace JH_DATA  temporary tablespace JH_DATA_TEMP profile default;

--给用户授予角色权限 
grant connect to jhcms; 
grant resource to jhcms;

--给用户授予系统权限
grant unlimited tablespace to jhcms;
exit;