cd ~/singer/apache-tomcat-8.5.24
./bin/catalina.sh stop 1
rm -rf webapps/chapter16*
cd ~/prospring5/chapter16
mvn clean install -DskipTests
cp target/chapter16.war ~/singer/apache-tomcat-8.5.24/webapps/
cd ~/singer/apache-tomcat-8.5.24
./bin/catalina.sh start
