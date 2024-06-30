#!/bin/bash

if [ -z "$1"]; then
  echo 'No arg passed'
  exit 1
fi

CONN_TIMEOUT=10
MAX_TRIAL=20
ACTIVATED=false

CURRENT=""
NEXT=""
NEXT_PORT=0
INIT=false

IMAGE_NAME=$1

echo 'pull docker image'
docker pull bkkmw/${IMAGE_NAME}

if docker ps -a | grep -q 'blue'; then
  echo 'current running : blue'
  CURRENT="blue"
  NEXT="green"
  NEXT_PORT=8081
elif docker ps -a | grep -q 'green'; then
  echo 'current running : green'
  CURRENT="green"
  NEXT="blue"
  NEXT_PORT=8080
else
  echo 'no container running'
  INIT=true
  NEXT="blue"
  NEXT_PORT=8080
fi

echo 'run container'
docker run --name ${IMAGE_NAME}-$NEXT --net host -e PORT_NUM=$NEXT_PORT -e TZ=Asia/Seoul -d bkkmw/${IMAGE_NAME}

count=1
url="http://localhost:$NEXT_PORT/actuator/health"

while [ $count -le $MAX_TRIAL ]
do
  statusCode=$(curl -sL --connect-timeout "$CONN_TIMEOUT" --max-time 30 -w "%{http_code}\\n" "$url" -o /dev/null)
  echo "[$count] trial : $statusCode"

  if [ "$statusCode" = "200" ]; then
    ACTIVATED=true
    break
  else
    count=$((count+1))
    sleep 5
  fi
done

if [ "$ACTIVATED" = false ]; then
  echo 'health check failed'
  docker stop ${IMAGE_NAME}-$NEXT
  docker rm ${IMAGE_NAME}-$NEXT
  docker image prune --force
  exit 1
fi

if [ "$INIT" = true ]; then
  echo 'no container to stop'
  exit 0
fi

echo 'stop running container'

docker stop ${IMAGE_NAME}-$CURRENT
docker rm ${IMAGE_NAME}-$CURRENT
docker image prune --force

exit 0
