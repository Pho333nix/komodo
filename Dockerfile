# define base docer image

FROM openjdk:11
LABEL maintainer="www.github.com/Pho333nix"
ADD target/IV1201-0.0.1-SNAPSHOT.jar komodo-docker.jar
ENTRYPOINT ["java", "-jar", "komodo-docker.jar"]

## install & run