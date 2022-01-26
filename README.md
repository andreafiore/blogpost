# Blogpost
Blogpost is the assignment made by Andrea Fiore for Cloudacademy

##Installation
Use maven to install and run the project
```bash
mvn clean install
```

##Usage
```bash
mvn exec:java -Dexec.mainClass="com.cloudacademy.blogpost.Main"
```

###Docker
Use the command
```bash
docker build --tag=blogpost:latest .
```
to build local docker image, and
```bash
docker-compose up -d
```
to create and run container.
