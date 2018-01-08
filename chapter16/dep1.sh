cd ~/rennMetro/apache-tomcat-8.5.24
./bin/catalina.sh stop 1
rm -rf webapps/chapter16*
cd ~/rennMetro/prospring5/chapter16
mvn clean install -DskipTests
cp target/chapter16.war ~/rennMetro/apache-tomcat-8.5.24/webapps/
cd ~/rennMetro/apache-tomcat-8.5.24
./bin/catalina.sh start
