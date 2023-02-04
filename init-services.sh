#!/bin/sh

echo "STARTING SERVICE"
docker-compose --file docker-compose.yml up --build -d

echo "REMOVING UNUSED DOCKER IMAGES"
docker image prune --force

echo "Done..."
