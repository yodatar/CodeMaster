
package com.gratex.perconik.astrcs.ientityservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.gratex_perconik_astrcs_svc.GetImportCapabilitiesRequest;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="request" type="{http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces}GetImportCapabilitiesRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "request"
})
@XmlRootElement(name = "GetImportCapabilities")
public class GetImportCapabilities {

    @XmlElementRef(name = "request", namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", type = JAXBElement.class, required = false)
    protected JAXBElement<GetImportCapabilitiesRequest> request;

    /**
     * Gets the value of the request property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link GetImportCapabilitiesRequest }{@code >}
     *     
     */
    public JAXBElement<GetImportCapabilitiesRequest> getRequest() {
        return request;
    }

    /**
     * Sets the value of the request property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link GetImportCapabilitiesRequest }{@code >}
     *     
     */
    public void setRequest(JAXBElement<GetImportCapabilitiesRequest> value) {
        this.request = value;
    }

}
