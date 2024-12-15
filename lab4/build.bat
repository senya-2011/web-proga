docker-compose down
docker-compose build --no-cache
docker image prune -f
docker-compose up