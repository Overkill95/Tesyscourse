
package it.simple.app.services.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getUserInfoResponse", namespace = "http://simpleWeb.it")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getUserInfoResponse", namespace = "http://simpleWeb.it")
public class GetUserInfoResponse {

    @XmlElement(name = "UserInfo", namespace = "http://simpleWeb.it/userInfo")
    private List<it.simple.app.dto.UserInfo> userInfo;

    /**
     * 
     * @return
     *     returns List<UserInfo>
     */
    public List<it.simple.app.dto.UserInfo> getUserInfo() {
        return this.userInfo;
    }

    /**
     * 
     * @param userInfo
     *     the value for the userInfo property
     */
    public void setUserInfo(List<it.simple.app.dto.UserInfo> userInfo) {
        this.userInfo = userInfo;
    }

}
