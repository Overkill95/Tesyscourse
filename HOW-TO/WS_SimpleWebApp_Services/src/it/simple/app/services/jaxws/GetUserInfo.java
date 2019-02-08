
package it.simple.app.services.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getUserInfo", namespace = "http://simpleWeb.it")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getUserInfo", namespace = "http://simpleWeb.it")
public class GetUserInfo {

    @XmlElement(name = "name", namespace = "http://simpleWeb.it/name")
    private String name;

    /**
     * 
     * @return
     *     returns String
     */
    public String getName() {
        return this.name;
    }

    /**
     * 
     * @param name
     *     the value for the name property
     */
    public void setName(String name) {
        this.name = name;
    }

}
