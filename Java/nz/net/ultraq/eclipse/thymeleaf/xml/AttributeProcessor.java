//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.01.13 at 05:26:58 PM NZDT 
//


package nz.net.ultraq.eclipse.thymeleaf.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				An attribute processor, includes an extra set of restrictions to
 * 				help with deciding where the processor can go and what values it
 * 				can take.
 * 			
 * 
 * <p>Java class for AttributeProcessor complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AttributeProcessor">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.ultraq.net.nz/eclipse/thymeleaf-dialect}Processor">
 *       &lt;sequence>
 *         &lt;element name="restrictions" type="{http://www.ultraq.net.nz/eclipse/thymeleaf-dialect}AttributeRestrictions" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AttributeProcessor", propOrder = {
    "restrictions"
})
public class AttributeProcessor
    extends Processor
{

    protected AttributeRestrictions restrictions;

    /**
     * Gets the value of the restrictions property.
     * 
     * @return
     *     possible object is
     *     {@link AttributeRestrictions }
     *     
     */
    public AttributeRestrictions getRestrictions() {
        return restrictions;
    }

    /**
     * Sets the value of the restrictions property.
     * 
     * @param value
     *     allowed object is
     *     {@link AttributeRestrictions }
     *     
     */
    public void setRestrictions(AttributeRestrictions value) {
        this.restrictions = value;
    }

    public boolean isSetRestrictions() {
        return (this.restrictions!= null);
    }

}
