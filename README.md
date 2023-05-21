# ejbecom
##
### Built with
[![java][java.com]][java-url]
[![ejb][ejb.com]][ejb-url]
### Installation

1. clone the project
2. create a `JBOSS` (Wildfly now) or any other JEE Application Server 
3. create a `management user`
4. set up a driver for the target database in `JBOSS`
5. create a `datasource` in the `JBOSS` Server and test it
7. go to `ejbModule/META-INF/persistance.xml` and put the name of the created `datasource`
   ```sh
    <>
       <jta-data-source>java:/MySqlDS</jta-data-source> 
    </>
   ```
8. in `java build path` of the EJB specify `Java path` as a java version `<=` java version of the server
9. specify `Server Runtime` as the created `JBOSS Server`
10. `Deploy` the `EJB` and `run` the server



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[ejb.com]:https://img.shields.io/badge/Specifications%20-EJB%20,%20JPA-red
[java.com]:	https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white
[java-url]:https://docs.oracle.com/en/java/
[ejb-url]:https://gayerie.dev/epsi-b3-orm/javaee_orm/ejb.html
