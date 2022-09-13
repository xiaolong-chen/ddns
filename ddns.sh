ipv6RR=ipv6
ipv6Url=http://speed.neu6.edu.cn/getIP.php
ipv6ConfIP=192.168.1.201
success={\"result\":1,

ipv6=$(curl -s "$ipv6Url")
echo "$ipv6"
echo ""

# 打印操作参数
echo "打印操作参数"
test=$(curl -s POST --location "http://"$ipv6ConfIP"/ddns/test" -H "Content-Type: application/x-www-form-urlencoded" -d "rr="$ipv6RR"&type=AAAA&value="$ipv6"")
echo "$test"
if [[ "$test" != $success* ]]; then
  exit
fi
echo ""

# 尝试添加记录
echo "尝试添加记录"
add=$(curl -s POST --location "http://"$ipv6ConfIP"/ddns/add" -H "Content-Type: application/x-www-form-urlencoded" -d "rr="$ipv6RR"&type=AAAA&value="$ipv6"")
echo "$add"
if [[ "$add" == $success* ]]; then
  echo "添加记录成功"
  exit
else
  echo "添加记录失败"
fi
echo ""

# 尝试修改记录
echo "尝试修改记录"
update=$(curl -s POST --location "http://"$ipv6ConfIP"/ddns/update" -H "Content-Type: application/x-www-form-urlencoded" -d "rr="$ipv6RR"&type=AAAA&value="$ipv6"")
echo "$update"
if [[ "$update" == $success* ]]; then
  echo "修改记录成功"
else
  echo "修改记录失败"
fi
echo ""
