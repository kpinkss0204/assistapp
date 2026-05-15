package org.xmlpull.v1.builder.adapter;

import java.util.Iterator;
import org.xmlpull.v1.builder.XmlAttribute;
import org.xmlpull.v1.builder.XmlContainer;
import org.xmlpull.v1.builder.XmlDocument;
import org.xmlpull.v1.builder.XmlElement;
import org.xmlpull.v1.builder.XmlNamespace;

/* JADX INFO: loaded from: classes4.dex */
public class XmlElementAdapter implements XmlElement {
    private XmlContainer parent;
    private XmlElement target;

    public XmlElementAdapter(XmlElement xmlElement) {
        this.target = xmlElement;
        if (xmlElement.getParent() != null) {
            XmlContainer parent = xmlElement.getParent();
            if (parent instanceof XmlDocument) {
                ((XmlDocument) parent).setDocumentElement(this);
            }
            if (parent instanceof XmlElement) {
                ((XmlElement) parent).replaceChild(this, xmlElement);
            }
        }
        Iterator itChildren = xmlElement.children();
        while (itChildren.hasNext()) {
            fixParent(itChildren.next());
        }
    }

    private void fixParent(Object obj) {
        if (obj instanceof XmlElement) {
            fixElementParent((XmlElement) obj);
        }
    }

    private XmlElement fixElementParent(XmlElement xmlElement) {
        xmlElement.setParent(this);
        return xmlElement;
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public XmlContainer getParent() {
        return this.parent;
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public void setParent(XmlContainer xmlContainer) {
        this.parent = xmlContainer;
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public XmlNamespace newNamespace(String str, String str2) {
        return this.target.newNamespace(str, str2);
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public XmlAttribute findAttribute(String str, String str2) {
        return this.target.findAttribute(str, str2);
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public Iterator attributes() {
        return this.target.attributes();
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public void removeAllChildren() {
        this.target.removeAllChildren();
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public XmlAttribute addAttribute(String str, String str2, String str3, String str4, String str5, boolean z) {
        return this.target.addAttribute(str, str2, str3, str4, str5, z);
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public XmlNamespace lookupNamespaceByPrefix(String str) {
        return this.target.lookupNamespaceByPrefix(str);
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public XmlAttribute addAttribute(XmlNamespace xmlNamespace, String str, String str2) {
        return this.target.addAttribute(xmlNamespace, str, str2);
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public String getNamespaceName() {
        return this.target.getNamespaceName();
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public void ensureChildrenCapacity(int i) {
        this.target.ensureChildrenCapacity(i);
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public Iterator namespaces() {
        return this.target.namespaces();
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public void removeAllAttributes() {
        this.target.removeAllAttributes();
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public XmlNamespace getNamespace() {
        return this.target.getNamespace();
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public String getBaseUri() {
        return this.target.getBaseUri();
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public void removeAttribute(XmlAttribute xmlAttribute) {
        this.target.removeAttribute(xmlAttribute);
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public XmlNamespace declareNamespace(String str, String str2) {
        return this.target.declareNamespace(str, str2);
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public void removeAllNamespaceDeclarations() {
        this.target.removeAllNamespaceDeclarations();
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public boolean hasAttributes() {
        return this.target.hasAttributes();
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public XmlAttribute addAttribute(String str, XmlNamespace xmlNamespace, String str2, String str3, boolean z) {
        return this.target.addAttribute(str, xmlNamespace, str2, str3, z);
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public XmlNamespace declareNamespace(XmlNamespace xmlNamespace) {
        return this.target.declareNamespace(xmlNamespace);
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public XmlAttribute addAttribute(String str, String str2) {
        return this.target.addAttribute(str, str2);
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public boolean hasNamespaceDeclarations() {
        return this.target.hasNamespaceDeclarations();
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public XmlNamespace lookupNamespaceByName(String str) {
        return this.target.lookupNamespaceByName(str);
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public XmlNamespace newNamespace(String str) {
        return this.target.newNamespace(str);
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public void setBaseUri(String str) {
        this.target.setBaseUri(str);
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public void setNamespace(XmlNamespace xmlNamespace) {
        this.target.setNamespace(xmlNamespace);
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public void ensureNamespaceDeclarationsCapacity(int i) {
        this.target.ensureNamespaceDeclarationsCapacity(i);
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public String getName() {
        return this.target.getName();
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public void setName(String str) {
        this.target.setName(str);
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public XmlAttribute addAttribute(String str, XmlNamespace xmlNamespace, String str2, String str3) {
        return this.target.addAttribute(str, xmlNamespace, str2, str3);
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public void ensureAttributeCapacity(int i) {
        this.target.ensureAttributeCapacity(i);
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public XmlAttribute addAttribute(XmlAttribute xmlAttribute) {
        return this.target.addAttribute(xmlAttribute);
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public XmlElement findElementByName(String str, XmlElement xmlElement) {
        return this.target.findElementByName(str, xmlElement);
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public XmlElement newElement(XmlNamespace xmlNamespace, String str) {
        return this.target.newElement(xmlNamespace, str);
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public XmlElement addElement(XmlElement xmlElement) {
        return fixElementParent(this.target.addElement(xmlElement));
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public XmlElement addElement(String str) {
        return fixElementParent(this.target.addElement(str));
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public XmlElement findElementByName(String str, String str2) {
        return this.target.findElementByName(str, str2);
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public void addChild(Object obj) {
        this.target.addChild(obj);
        fixParent(obj);
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public void insertChild(int i, Object obj) {
        this.target.insertChild(i, obj);
        fixParent(obj);
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public XmlElement findElementByName(String str) {
        return this.target.findElementByName(str);
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public XmlElement findElementByName(String str, String str2, XmlElement xmlElement) {
        return this.target.findElementByName(str, str2, xmlElement);
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public void removeChild(Object obj) {
        this.target.removeChild(obj);
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public Iterator children() {
        return this.target.children();
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public boolean hasChild(Object obj) {
        return this.target.hasChild(obj);
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public XmlElement newElement(String str, String str2) {
        return this.target.newElement(str, str2);
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public XmlElement addElement(XmlNamespace xmlNamespace, String str) {
        return fixElementParent(this.target.addElement(xmlNamespace, str));
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public boolean hasChildren() {
        return this.target.hasChildren();
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public void addChild(int i, Object obj) {
        this.target.addChild(i, obj);
        fixParent(obj);
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public void replaceChild(Object obj, Object obj2) {
        this.target.replaceChild(obj, obj2);
        fixParent(obj);
    }

    @Override // org.xmlpull.v1.builder.XmlElement
    public XmlElement newElement(String str) {
        return this.target.newElement(str);
    }
}
