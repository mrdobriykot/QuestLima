FROM tomcat:9.0.65-jdk17-corretto

EXPOSE 8080

COPY ./target/rpg-servlet-game.war /usr/local/tomcat/webapps/ROOT.war