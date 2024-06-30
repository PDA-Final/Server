#!/bin/bash

docker pull bkkmw/tofin-api-gw

docker stop tofin-api-gw
docker rm tofin-api-gw

docker run --name tofin-api-gw -p 8080:8080 -d bkkmw/tofin-api-gw

docker image prune --force
