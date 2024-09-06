FROM openjdk

COPY lib/fastcgi-lib.jar /usr/src/myapp/lib/fastcgi-lib.jar
COPY src /usr/src/myapp/src

WORKDIR /usr/src/myapp
RUN javac -cp lib/fastcgi-lib.jar src/Main.java

EXPOSE 9001

CMD ["java", "-DFCGI_PORT=9001","-cp", "lib/fastcgi-lib.jar:src", "Main"]
