
sudo service docker start
sudo docker pull jboss/wildfly
sudo docker run -p 8080:8080 -p 9990:9990 -it jboss/wildfly /opt/jboss/wildfly/bin/standalone.sh -bmanagement 0.0.0.0