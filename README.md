xjc-lombok-plugin
================

[![Build Status](https://travis-ci.org/mplushnikov/xjc-lombok-plugin.svg)](https://travis-ci.org/mplushnikov/xjc-lombok-plugin.svg)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/de.plushnikov.xjc/xjc-lombok-plugin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/de.plushnikov.xjc/xjc-lombok-plugin)

*Fell in love with lombok [@ToString](http://projectlombok.org/features/ToString.html),
[@EqualsAndHashCode](http://projectlombok.org/features/EqualsAndHashCode.html)?
Tired of writing StringBuilders for JAX-WS wsgen generated Beans? This XJC Compiler plugin comes to the rescue and creates yummie standards methods for your JAX-B/WS Beans - with a taste of [Lombok](http://projectlombok.org/).*

Profit!

Example
---------------------
This plugin generates lombok standard annotations for toString, hashCode and equals:
```java
@ToString
@EqualsAndHashCode
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "thunderbolt", propOrder = {
    "intensity"
})
public class Thunderbolt {

    protected Double intensity;

    public Double getIntensity() {
        return intensity;
    }

    public void setIntensity(Double value) {
        this.intensity = value;
    }

}
```


Usage
---------------------

In contract first scenarios webservice clients models are often generated with jaxws.wsgen or cxf-codegen-plugin

# using jaxws-maven-plugin
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.jvnet.jax-ws-commons</groupId>
            <artifactId>jaxws-maven-plugin</artifactId>
            <executions>
                <execution>
                    <phase>generate-sources</phase>
                    <goals>
                        <goal>wsimport</goal>
                    </goals>
                    <configuration>
                        <wsdlFiles>
                            <wsdlFile>${basedir}/src/test/resources/test.wsdl</wsdlFile>
                        </wsdlFiles>
                        <args>
                            <arg>-B-Xlombok</arg>
                        </args>
                    </configuration>
                </execution>
            </executions>
            <dependencies>
                <dependency>
                    <groupId>de.plushnikov.xjc</groupId>
                    <artifactId>xjc-lombok-plugin</artifactId>
                    <version>0.3.1</version>
                </dependency>
            </dependencies>
        </plugin>
    </plugins>
</build>
<dependencies>
    <dependency>
        <groupId>org.projectlombok</groupId>
		<artifactId>lombok</artifactId>
		<version>1.14.8</version>
		<scope>provided</scope>
    </dependency>
</dependencies>
```

# using cxf-codegen-plugin

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.cxf</groupId>
            <artifactId>cxf-codegen-plugin</artifactId>
            <version>2.7.1</version>
            <executions>
                <execution>
                    <phase>generate-sources</phase>
                    <configuration>
                        <sourceRoot>${project.build.directory}/generated-sources/cxf</sourceRoot>
                        <wsdlOptions>

                            <wsdlOption>
                                <extraargs>
                                    <extraarg>-xjc-Xlombok</extraarg>
                                </extraargs>
                                <wsdl>${basedir}/src/test/resources/test.wsdl</wsdl>
                            </wsdlOption>
                        </wsdlOptions>
                    </configuration>
                    <goals>
                        <goal>wsdl2java</goal>
                    </goals>
                </execution>
            </executions>
            <dependencies>
                <dependency>
                    <groupId>de.plushnikov.xjc</groupId>
                    <artifactId>xjc-lombok-plugin</artifactId>
                    <version>0.3.1</version>
                </dependency>
            </dependencies>
        </plugin>
    </plugins>
</build>
<dependencies>
    <dependency>
        <groupId>org.projectlombok</groupId>
		<artifactId>lombok</artifactId>
		<version>1.14.8</version>
		<scope>provided</scope>
    </dependency>
</dependencies>

```
#jaxb2-maven-plugin

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>jaxb2-maven-plugin</artifactId>
            <version>1.6</version>
            <dependencies>
                <dependency>
                    <groupId>de.plushnikov.xjc</groupId>
                    <artifactId>xjc-lombok-plugin</artifactId>
                    <version>0.3.1</version>
                </dependency>
                <dependency>
                    <groupId>xerces</groupId>
                    <artifactId>xercesImpl</artifactId>
                    <version>2.11.0</version>
                </dependency>
            </dependencies>
            <configuration>
                <arguments>-Xlombok</arguments>
            </configuration>
            <executions>
                <execution>
                    <id>generate-model</id>
                    <goals>
                        <goal>xjc</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
    <dependencies>
        <dependency>
            <groupId>org.projectlombok</groupId>
		    <artifactId>lombok</artifactId>
		    <version>1.14.8</version>
		    <scope>provided</scope>
        </dependency>
    </dependencies>
</build>
```
