//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.01.11 at 11:42:40 AM NZDT 
//


package nz.net.ultraq.eclipse.thymeleaf.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;choice maxOccurs="unbounded">
 *           &lt;element name="attribute-processor" type="{http://www.ultraq.net.nz/eclipse/thymeleaf-dialect}AttributeProcessor"/>
 *           &lt;element name="element-processor" type="{http://www.ultraq.net.nz/eclipse/thymeleaf-dialect}ElementProcessor"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="prefix" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "processors"
})
@XmlRootElement(name = "dialect")
public class Dialect {

    @XmlElements({
        @XmlElement(name = "attribute-processor", type = AttributeProcessor.class),
        @XmlElement(name = "element-processor", type = ElementProcessor.class)
    })
    protected List<Processor> processors;
    @XmlAttribute(name = "prefix", required = true)
    protected String prefix;

    /**
     * Gets the value of the processors property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the processors property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProcessors().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AttributeProcessor }
     * {@link ElementProcessor }
     * 
     * 
     */
    public List<Processor> getProcessors() {
        if (processors == null) {
            processors = new ArrayList<Processor>();
        }
        return this.processors;
    }

    /**
     * Gets the value of the prefix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Sets the value of the prefix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrefix(String value) {
        this.prefix = value;
    }

}
