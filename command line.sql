-- Maven build jar
mvn clean install
mvn package

-- Build docker
docker build -t fun-chat-service .

-- Docker run
docker run --name fun-chat-service -p 8080:8080 fun-chat-service

