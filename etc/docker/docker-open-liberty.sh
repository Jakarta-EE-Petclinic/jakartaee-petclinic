sudo docker pull open-liberty:19.0.0.8
sudo docker run -d -p 80:9080 -p 443:9443 \
        -v   /tmp/petclinic.war:/config/dropins/petclinic.war \
        open-liberty:javaee8