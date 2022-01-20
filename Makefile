# 注意：window环境下先安装MinGW，idea中Makefile插件
# Makefile 命令开头必须为tab键

# 生成riie-admin的文档
riie-admin@html-doc:
	mvn -X smart-doc:torna-rest -Dfile.encoding=UTF-8  -pl :riie-admin -am