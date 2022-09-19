docker run -d \
  --name ddns \
  -p 80:80 \
  -v /etc/timezone:/etc/timezone:ro \
  -v /etc/localtime:/etc/localtime:ro \
  -e ddns.endpoint=127.0.0.1 \
  -e ddns.accessKeyId=1234567890 \
  -e ddns.accessKeySecret=1234567890 \
  -e ddns.domainName=example.com \
  xiaolong-chen/ddns:1.0-SNAPSHOT
