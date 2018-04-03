//
// ���ļ����� JavaTM Architecture for XML Binding (JAXB) ����ʵ�� v2.2.8-b130911.1802 ���ɵ�
// ����� <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
// ����ʱ��: 2018.01.26 ʱ�� 03:02:16 PM CST 
//


package com.base.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="scan-package" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="view">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="content-type" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="prefix" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="suffix" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "scanPackage",
    "view"
})
@XmlRootElement(name = "beans")
public class Beans {

    @XmlElement(name = "scan-package", required = true)
    protected String scanPackage;
    @XmlElement(required = true)
    protected View view;

    /**
     * ��ȡscanPackage���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScanPackage() {
        return scanPackage;
    }

    /**
     * ����scanPackage���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScanPackage(String value) {
        this.scanPackage = value;
    }

    /**
     * ��ȡview���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link View }
     *     
     */
    public View getView() {
        return view;
    }

    /**
     * ����view���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link View }
     *     
     */
    public void setView(View value) {
        this.view = value;
    }


    /**
     * <p>anonymous complex type�� Java �ࡣ
     * 
     * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="content-type" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="prefix" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="suffix" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "contentType",
        "prefix",
        "suffix"
    })
    public static class View {

        @XmlElement(name = "content-type", required = true)
        protected String contentType;
        @XmlElement(required = true)
        protected String prefix;
        @XmlElement(required = true)
        protected String suffix;

        /**
         * ��ȡcontentType���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getContentType() {
            return contentType;
        }

        /**
         * ����contentType���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setContentType(String value) {
            this.contentType = value;
        }

        /**
         * ��ȡprefix���Ե�ֵ��
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
         * ����prefix���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPrefix(String value) {
            this.prefix = value;
        }

        /**
         * ��ȡsuffix���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSuffix() {
            return suffix;
        }

        /**
         * ����suffix���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSuffix(String value) {
            this.suffix = value;
        }

    }

}
