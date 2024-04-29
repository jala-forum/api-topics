# Build stage
FROM gradle:8.6-jdk21

WORKDIR /app

COPY . .

RUN chmod +x entrypoint-docker.sh

# Package stage
EXPOSE 8080
CMD ["sh", "entrypoint-docker.sh"]