:: 设置utf-8编码
@chcp 65001

@set ipv6RR=ipv6
@set ipv6Url=http://speed.neu6.edu.cn/getIP.php
@set ipv6ConfIP=192.168.1.201
@set success={\"result\":1,

@for /F %%i in ('curl -s %ipv6Url%') do @(set ipv6=%%i)
@echo %ipv6%
@echo.

@rem 打印操作参数
@echo 打印操作参数
@curl -X POST --location "http://%ipv6ConfIP%/ddns/test" -H "Content-Type: application/x-www-form-urlencoded" -d "rr=%ipv6RR%&type=AAAA&value=%ipv6%"
@echo.
@echo.

@rem 尝试添加记录
@echo 尝试添加记录
@curl -X POST --location "http://%ipv6ConfIP%/ddns/add" -H "Content-Type: application/x-www-form-urlencoded" -d "rr=%ipv6RR%&type=AAAA&value=%ipv6%"
@echo.
@echo.

@rem 尝试修改记录
@echo 尝试修改记录
@curl -X POST --location "http://%ipv6ConfIP%/ddns/update" -H "Content-Type: application/x-www-form-urlencoded" -d "rr=%ipv6RR%&type=AAAA&value=%ipv6%"
@echo.
@echo.

@rem @rem 尝试删除记录
@rem @echo 尝试删除记录
@rem @curl -X POST --location "http://%ipv6ConfIP%/ddns/delete" -H "Content-Type: application/x-www-form-urlencoded" -d "rr=%ipv6RR%&type=AAAA&value=%ipv6%"
@rem @echo.
@rem @echo.

pause
