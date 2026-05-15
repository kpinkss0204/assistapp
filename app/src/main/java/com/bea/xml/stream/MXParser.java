package com.bea.xml.stream;

import androidx.compose.animation.core.AnimationKt;
import com.bea.xml.stream.events.DTDEvent;
import com.bea.xml.stream.reader.XmlReader;
import com.bea.xml.stream.util.ElementTypeNames;
import com.bea.xml.stream.util.EmptyIterator;
import com.wutka.dtd.DTD;
import com.wutka.dtd.DTDAttlist;
import com.wutka.dtd.DTDAttribute;
import com.wutka.dtd.DTDEntity;
import com.wutka.dtd.DTDNotation;
import com.wutka.dtd.DTDParser;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.EntityDeclaration;
import javax.xml.stream.events.NotationDeclaration;
import kotlin.text.Typography;
import okio.Utf8;

/* JADX INFO: loaded from: classes2.dex */
public class MXParser implements XMLStreamReader, Location {
    protected static final char CHAR_UTF8_BOM = 65279;
    private static final int DOCDECL = 32768;
    protected static final char[] ENCODING;
    static final String EOF_MSG = "Unexpected end of stream";
    protected static final String FEATURE_NAMES_INTERNED = "http://xmlpull.org/v1/doc/features.html#names-interned";
    public static final String FEATURE_PROCESS_DOCDECL = "http://xmlpull.org/v1/doc/features.html#process-docdecl";
    public static final String FEATURE_PROCESS_NAMESPACES = "http://xmlpull.org/v1/doc/features.html#process-namespaces";
    public static final String FEATURE_STAX_ENTITIES = "javax.xml.stream.entities";
    public static final String FEATURE_STAX_NOTATIONS = "javax.xml.stream.notations";
    protected static final String FEATURE_XML_ROUNDTRIP = "http://xmlpull.org/v1/doc/features.html#xml-roundtrip";
    protected static final int LOOKUP_MAX = 1024;
    protected static final char LOOKUP_MAX_CHAR = 1024;
    protected static final int MAX_UNICODE_CHAR = 1114111;
    protected static final char[] NO;
    private static final char[] NO_CHARS;
    private static final int[] NO_INTS;
    private static final String[] NO_STRINGS;
    protected static final int READ_CHUNK_SIZE = 8192;
    protected static final char[] STANDALONE;
    private static final int TEXT = 16384;
    private static final boolean TRACE_SIZING = false;
    protected static final char[] VERSION;
    protected static final char[] YES;
    static /* synthetic */ Class class$com$wutka$dtd$DTDAttlist;
    static /* synthetic */ Class class$com$wutka$dtd$DTDEntity;
    static /* synthetic */ Class class$com$wutka$dtd$DTDNotation;
    protected boolean allStringsInterned;
    protected int attributeCount;
    protected String[] attributeName;
    protected int[] attributeNameHash;
    protected String[] attributePrefix;
    protected String[] attributeUri;
    protected String[] attributeValue;
    protected char[] buf;
    protected int bufAbsoluteStart;
    protected int bufEnd;
    protected int bufLoadFactor;
    protected int bufSoftLimit;
    protected int bufStart;
    protected String charEncodingScheme;
    protected char[] charRefOneCharBuf;
    protected char[] charRefTwoCharBuf;
    protected int columnNumber;
    private ConfigurationContextBase configurationContext;
    protected HashMap defaultAttributes;
    protected int depth;
    protected String[] elName;
    protected int[] elNamespaceCount;
    protected String[] elPrefix;
    protected char[][] elRawName;
    protected int[] elRawNameEnd;
    protected String[] elUri;
    protected boolean emptyElementTag;
    protected int entityEnd;
    protected String[] entityName;
    protected char[][] entityNameBuf;
    protected int[] entityNameHash;
    protected String entityRefName;
    protected String[] entityReplacement;
    protected char[][] entityReplacementBuf;
    protected char[] entityValue;
    protected int eventType;
    protected String inputEncoding;
    protected int lineNumber;
    protected int localNamespaceEnd;
    protected String[] localNamespacePrefix;
    protected int[] localNamespacePrefixHash;
    protected String[] localNamespaceUri;
    protected DTD mDtdIntSubset;
    protected int namespaceEnd;
    protected String[] namespacePrefix;
    protected int[] namespacePrefixHash;
    protected String[] namespaceUri;
    protected boolean pastEndTag;
    protected char[] pc;
    protected int pcEnd;
    protected int pcStart;
    protected String piData;
    protected String piTarget;
    protected int pos;
    protected int posEnd;
    protected int posStart;
    protected boolean reachedEnd;
    protected Reader reader;
    protected boolean seenAmpersand;
    protected boolean seenDocdecl;
    protected boolean seenEndTag;
    protected boolean seenMarkup;
    protected boolean seenRoot;
    protected boolean seenStartTag;
    protected String text;
    protected boolean tokenize;
    protected boolean usePC;
    public static final String[] TYPES = {"[UNKNOWN]", "START_ELEMENT", "END_ELEMENT", "PROCESSING_INSTRUCTION", "CHARACTERS", "COMMENT", "SPACE", "START_DOCUMENT", "END_DOCUMENT", "ENTITY_REFERENCE", "ATTRIBUTE", "DTD", "CDATA", "NAMESPACE", "NOTATION_DECLARATION", "ENTITY_DECLARATION"};
    public static final String NO_NAMESPACE = null;
    protected static boolean[] lookupNameStartChar = new boolean[1024];
    protected static boolean[] lookupNameChar = new boolean[1024];
    private boolean reportCdataEvent = false;
    protected boolean processNamespaces = true;
    protected boolean roundtripSupported = true;
    protected String xmlVersion = null;
    protected boolean standalone = false;
    protected boolean standaloneSet = false;

    private static boolean isElementEvent(int i) {
        return i == 1 || i == 2;
    }

    @Override // javax.xml.stream.XMLStreamReader
    public void close() throws XMLStreamException {
    }

    @Override // javax.xml.stream.XMLStreamReader
    public Location getLocation() {
        return this;
    }

    public String getLocationURI() {
        return null;
    }

    @Override // javax.xml.stream.Location
    public String getPublicId() {
        return null;
    }

    @Override // javax.xml.stream.Location
    public String getSystemId() {
        return null;
    }

    protected boolean isS(char c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t';
    }

    protected void resetStringCache() {
    }

    static {
        setNameStart(':');
        for (char c = 'A'; c <= 'Z'; c = (char) (c + 1)) {
            setNameStart(c);
        }
        setNameStart('_');
        for (char c2 = 'a'; c2 <= 'z'; c2 = (char) (c2 + 1)) {
            setNameStart(c2);
        }
        for (char c3 = 192; c3 <= 767; c3 = (char) (c3 + 1)) {
            setNameStart(c3);
        }
        for (char c4 = 880; c4 <= 893; c4 = (char) (c4 + 1)) {
            setNameStart(c4);
        }
        for (char c5 = 895; c5 < 1024; c5 = (char) (c5 + 1)) {
            setNameStart(c5);
        }
        setName('-');
        setName('.');
        for (char c6 = '0'; c6 <= '9'; c6 = (char) (c6 + 1)) {
            setName(c6);
        }
        setName(Typography.middleDot);
        for (char c7 = 768; c7 <= 879; c7 = (char) (c7 + 1)) {
            setName(c7);
        }
        NO_STRINGS = new String[0];
        NO_INTS = new int[0];
        NO_CHARS = new char[0];
        VERSION = new char[]{'v', 'e', 'r', 's', 'i', 'o', 'n'};
        ENCODING = new char[]{'e', 'n', 'c', 'o', 'd', 'i', 'n', 'g'};
        STANDALONE = new char[]{'s', 't', 'a', 'n', 'd', 'a', 'l', 'o', 'n', 'e'};
        YES = new char[]{'y', 'e', 's'};
        NO = new char[]{'n', 'o'};
    }

    protected String newString(char[] cArr, int i, int i2) {
        return new String(cArr, i, i2);
    }

    protected String newStringIntern(char[] cArr, int i, int i2) {
        return new String(cArr, i, i2).intern();
    }

    protected void ensureElementsCapacity() {
        String[] strArr = this.elName;
        int length = strArr != null ? strArr.length : 0;
        int i = this.depth;
        if (i + 1 >= length) {
            int i2 = (i >= 7 ? i * 2 : 8) + 2;
            boolean z = length > 0;
            String[] strArr2 = new String[i2];
            if (z) {
                System.arraycopy(strArr, 0, strArr2, 0, length);
            }
            this.elName = strArr2;
            String[] strArr3 = new String[i2];
            if (z) {
                System.arraycopy(this.elPrefix, 0, strArr3, 0, length);
            }
            this.elPrefix = strArr3;
            String[] strArr4 = new String[i2];
            if (z) {
                System.arraycopy(this.elUri, 0, strArr4, 0, length);
            }
            this.elUri = strArr4;
            int[] iArr = new int[i2];
            if (z) {
                System.arraycopy(this.elNamespaceCount, 0, iArr, 0, length);
            } else {
                iArr[0] = 0;
            }
            this.elNamespaceCount = iArr;
            int[] iArr2 = new int[i2];
            if (z) {
                System.arraycopy(this.elRawNameEnd, 0, iArr2, 0, length);
            }
            this.elRawNameEnd = iArr2;
            char[][] cArr = new char[i2][];
            if (z) {
                System.arraycopy(this.elRawName, 0, cArr, 0, length);
            }
            this.elRawName = cArr;
        }
    }

    private static final void setName(char c) {
        lookupNameChar[c] = true;
    }

    private static final void setNameStart(char c) {
        lookupNameStartChar[c] = true;
        setName(c);
    }

    protected boolean isNameStartChar(char c) {
        if (c < 1024 && lookupNameStartChar[c]) {
            return true;
        }
        if (c >= 1024 && c <= 8231) {
            return true;
        }
        if (c < 8234 || c > 8591) {
            return c >= 10240 && c <= 65519;
        }
        return true;
    }

    protected boolean isNameChar(char c) {
        if (c < 1024 && lookupNameChar[c]) {
            return true;
        }
        if (c >= 1024 && c <= 8231) {
            return true;
        }
        if (c < 8234 || c > 8591) {
            return c >= 10240 && c <= 65519;
        }
        return true;
    }

    protected void checkCharValidity(int i, boolean z) throws XMLStreamException {
        if (i < 32) {
            if (!isS((char) i)) {
                throw new XMLStreamException(new StringBuffer("Illegal white space character (code 0x").append(Integer.toHexString(i)).append(")").toString());
            }
        } else if (i >= 55296) {
            if (i <= 57343) {
                if (!z) {
                    throw new XMLStreamException(new StringBuffer("Illegal character (code 0x").append(Integer.toHexString(i)).append("): surrogate characters are not valid XML characters").toString(), getLocation());
                }
            } else if (i > MAX_UNICODE_CHAR) {
                throw new XMLStreamException(new StringBuffer("Illegal character (code 0x").append(Integer.toHexString(i)).append("), past max. Unicode character 0x").append(Integer.toHexString(MAX_UNICODE_CHAR)).toString(), getLocation());
            }
        }
    }

    protected void ensureAttributesCapacity(int i) {
        String[] strArr = this.attributeName;
        int length = strArr != null ? strArr.length : 0;
        if (i >= length) {
            int i2 = i > 7 ? i * 2 : 8;
            boolean z = length > 0;
            String[] strArr2 = new String[i2];
            if (z) {
                System.arraycopy(strArr, 0, strArr2, 0, length);
            }
            this.attributeName = strArr2;
            String[] strArr3 = new String[i2];
            if (z) {
                System.arraycopy(this.attributePrefix, 0, strArr3, 0, length);
            }
            this.attributePrefix = strArr3;
            String[] strArr4 = new String[i2];
            if (z) {
                System.arraycopy(this.attributeUri, 0, strArr4, 0, length);
            }
            this.attributeUri = strArr4;
            String[] strArr5 = new String[i2];
            if (z) {
                System.arraycopy(this.attributeValue, 0, strArr5, 0, length);
            }
            this.attributeValue = strArr5;
            if (this.allStringsInterned) {
                return;
            }
            int[] iArr = new int[i2];
            if (z) {
                System.arraycopy(this.attributeNameHash, 0, iArr, 0, length);
            }
            this.attributeNameHash = iArr;
        }
    }

    protected void ensureNamespacesCapacity(int i) {
        String[] strArr = this.namespacePrefix;
        if (i >= (strArr != null ? strArr.length : 0)) {
            int i2 = i > 7 ? i * 2 : 8;
            String[] strArr2 = new String[i2];
            String[] strArr3 = new String[i2];
            if (strArr != null) {
                System.arraycopy(strArr, 0, strArr2, 0, this.namespaceEnd);
                System.arraycopy(this.namespaceUri, 0, strArr3, 0, this.namespaceEnd);
            }
            this.namespacePrefix = strArr2;
            this.namespaceUri = strArr3;
            if (this.allStringsInterned) {
                return;
            }
            int[] iArr = new int[i2];
            int[] iArr2 = this.namespacePrefixHash;
            if (iArr2 != null) {
                System.arraycopy(iArr2, 0, iArr, 0, this.namespaceEnd);
            }
            this.namespacePrefixHash = iArr;
        }
    }

    protected void ensureLocalNamespacesCapacity(int i) {
        String[] strArr = this.localNamespacePrefix;
        if (i >= (strArr != null ? strArr.length : 0)) {
            int i2 = i > 7 ? i * 2 : 8;
            String[] strArr2 = new String[i2];
            String[] strArr3 = new String[i2];
            if (strArr != null) {
                System.arraycopy(strArr, 0, strArr2, 0, this.localNamespaceEnd);
                System.arraycopy(this.localNamespaceUri, 0, strArr3, 0, this.localNamespaceEnd);
            }
            this.localNamespacePrefix = strArr2;
            this.localNamespaceUri = strArr3;
            if (this.allStringsInterned) {
                return;
            }
            int[] iArr = new int[i2];
            int[] iArr2 = this.localNamespacePrefixHash;
            if (iArr2 != null) {
                System.arraycopy(iArr2, 0, iArr, 0, this.localNamespaceEnd);
            }
            this.localNamespacePrefixHash = iArr;
        }
    }

    public int getLocalNamespaceCount() {
        return this.namespaceEnd - this.elNamespaceCount[this.depth - 1];
    }

    private String getLocalNamespaceURI(int i) {
        return this.namespaceUri[i];
    }

    private String getLocalNamespacePrefix(int i) {
        return this.namespacePrefix[i];
    }

    protected static final int fastHash(char[] cArr, int i, int i2) {
        if (i2 == 0) {
            return 0;
        }
        int i3 = (cArr[i] << 7) + cArr[(i + i2) - 1];
        if (i2 > 16) {
            i3 = (i3 << 7) + cArr[(i2 / 4) + i];
        }
        return i2 > 8 ? (i3 << 7) + cArr[i + (i2 / 2)] : i3;
    }

    protected void ensureEntityCapacity() {
        char[][] cArr = this.entityReplacementBuf;
        int length = cArr != null ? cArr.length : 0;
        int i = this.entityEnd;
        if (i >= length) {
            int i2 = i > 7 ? i * 2 : 8;
            String[] strArr = new String[i2];
            char[][] cArr2 = new char[i2][];
            String[] strArr2 = new String[i2];
            char[][] cArr3 = new char[i2][];
            String[] strArr3 = this.entityName;
            if (strArr3 != null) {
                System.arraycopy(strArr3, 0, strArr, 0, i);
                System.arraycopy(this.entityNameBuf, 0, cArr2, 0, this.entityEnd);
                System.arraycopy(this.entityReplacement, 0, strArr2, 0, this.entityEnd);
                System.arraycopy(this.entityReplacementBuf, 0, cArr3, 0, this.entityEnd);
            }
            this.entityName = strArr;
            this.entityNameBuf = cArr2;
            this.entityReplacement = strArr2;
            this.entityReplacementBuf = cArr3;
            if (this.allStringsInterned) {
                return;
            }
            int[] iArr = new int[i2];
            int[] iArr2 = this.entityNameHash;
            if (iArr2 != null) {
                System.arraycopy(iArr2, 0, iArr, 0, this.entityEnd);
            }
            this.entityNameHash = iArr;
        }
    }

    private void reset() {
        this.lineNumber = 1;
        this.columnNumber = 0;
        this.seenRoot = false;
        this.reachedEnd = false;
        this.eventType = 7;
        this.emptyElementTag = false;
        this.depth = 0;
        this.attributeCount = 0;
        this.namespaceEnd = 0;
        this.localNamespaceEnd = 0;
        this.entityEnd = 0;
        this.reader = null;
        this.inputEncoding = null;
        this.bufAbsoluteStart = 0;
        this.bufStart = 0;
        this.bufEnd = 0;
        this.posEnd = 0;
        this.posStart = 0;
        this.pos = 0;
        this.pcStart = 0;
        this.pcEnd = 0;
        this.usePC = false;
        this.seenStartTag = false;
        this.seenEndTag = false;
        this.pastEndTag = false;
        this.seenAmpersand = false;
        this.seenMarkup = false;
        this.seenDocdecl = false;
        resetStringCache();
    }

    public MXParser() {
        String[] strArr = NO_STRINGS;
        this.namespacePrefix = strArr;
        this.namespaceUri = strArr;
        this.bufLoadFactor = 95;
        char[] cArr = new char[Runtime.getRuntime().freeMemory() > AnimationKt.MillisToNanos ? 8192 : 256];
        this.buf = cArr;
        this.bufSoftLimit = (this.bufLoadFactor * cArr.length) / 100;
        this.pc = new char[Runtime.getRuntime().freeMemory() <= AnimationKt.MillisToNanos ? 64 : 8192];
        this.entityValue = null;
        this.charRefOneCharBuf = new char[1];
        this.charRefTwoCharBuf = null;
    }

    public void setFeature(String str, boolean z) throws XMLStreamException {
        if (str == null) {
            throw new IllegalArgumentException("feature name should not be nulll");
        }
        if ("http://xmlpull.org/v1/doc/features.html#process-namespaces".equals(str)) {
            if (this.eventType != 7) {
                throw new XMLStreamException("namespace processing feature can only be changed before parsing", getLocation());
            }
            this.processNamespaces = z;
        } else if (FEATURE_NAMES_INTERNED.equals(str)) {
            if (z) {
                throw new XMLStreamException("interning names in this implementation is not supported");
            }
        } else if ("http://xmlpull.org/v1/doc/features.html#process-docdecl".equals(str)) {
            if (z) {
                throw new XMLStreamException("processing DOCDECL is not supported");
            }
        } else {
            if (!FEATURE_XML_ROUNDTRIP.equals(str)) {
                throw new XMLStreamException(new StringBuffer("unknown feature ").append(str).toString());
            }
            if (!z) {
                throw new XMLStreamException("roundtrip feature can not be switched off");
            }
        }
    }

    public boolean getFeature(String str) {
        if (str == null) {
            throw new IllegalArgumentException("feature name should not be null");
        }
        if ("http://xmlpull.org/v1/doc/features.html#process-namespaces".equals(str)) {
            return this.processNamespaces;
        }
        return (FEATURE_NAMES_INTERNED.equals(str) || "http://xmlpull.org/v1/doc/features.html#process-docdecl".equals(str) || !FEATURE_XML_ROUNDTRIP.equals(str)) ? false : true;
    }

    public void setProperty(String str, Object obj) throws XMLStreamException {
        throw new XMLStreamException(new StringBuffer("unsupported property: '").append(str).append("'").toString());
    }

    public boolean checkForXMLDecl() throws XMLStreamException {
        try {
            BufferedReader bufferedReader = new BufferedReader(this.reader, 7);
            this.reader = bufferedReader;
            bufferedReader.mark(7);
            int i = bufferedReader.read();
            if (i == 65279) {
                bufferedReader.mark(7);
                i = bufferedReader.read();
            }
            if (i == 60 && bufferedReader.read() == 63 && bufferedReader.read() == 120 && bufferedReader.read() == 109 && bufferedReader.read() == 108) {
                bufferedReader.reset();
                return true;
            }
            bufferedReader.reset();
            return false;
        } catch (IOException e) {
            throw new XMLStreamException(e);
        }
    }

    public void setInput(Reader reader) throws XMLStreamException {
        reset();
        this.reader = reader;
        if (checkForXMLDecl()) {
            next();
        }
    }

    public void setInput(InputStream inputStream) throws XMLStreamException {
        try {
            Reader readerCreateReader = XmlReader.createReader(inputStream);
            String encoding = readerCreateReader instanceof XmlReader.BaseReader ? ((XmlReader.BaseReader) readerCreateReader).getEncoding() : null;
            setInput(readerCreateReader);
            if (encoding != null) {
                this.inputEncoding = encoding;
            }
        } catch (IOException e) {
            throw new XMLStreamException(e);
        }
    }

    public void setInput(InputStream inputStream, String str) throws XMLStreamException {
        if (inputStream == null) {
            throw new IllegalArgumentException("input stream can not be null");
        }
        try {
            setInput(str != null ? XmlReader.createReader(inputStream, str) : XmlReader.createReader(inputStream));
            if (str != null) {
                this.inputEncoding = str;
            }
        } catch (IOException e) {
            throw new XMLStreamException(new StringBuffer("could not create reader ").append(str == null ? new StringBuffer("(for encoding '").append(str).append("')").toString() : "").append(": ").append(e).toString(), getLocation(), e);
        }
    }

    public String getInputEncoding() {
        return this.inputEncoding;
    }

    public void defineEntityReplacementText(String str, String str2) throws XMLStreamException {
        ensureEntityCapacity();
        char[] charArray = str.toCharArray();
        this.entityName[this.entityEnd] = newString(charArray, 0, str.length());
        char[][] cArr = this.entityNameBuf;
        int i = this.entityEnd;
        cArr[i] = charArray;
        this.entityReplacement[i] = str2;
        char[] charArray2 = str2 == null ? NO_CHARS : str2.toCharArray();
        char[][] cArr2 = this.entityReplacementBuf;
        int i2 = this.entityEnd;
        cArr2[i2] = charArray2;
        if (!this.allStringsInterned) {
            int[] iArr = this.entityNameHash;
            char[] cArr3 = this.entityNameBuf[i2];
            iArr[i2] = fastHash(cArr3, 0, cArr3.length);
        }
        this.entityEnd++;
    }

    @Override // javax.xml.stream.XMLStreamReader
    public int getNamespaceCount() {
        if (!isElementEvent(this.eventType)) {
            throwIllegalState(new int[]{1, 2});
        }
        return getNamespaceCount(this.depth);
    }

    public int getNamespaceCount(int i) {
        if (!this.processNamespaces || i == 0) {
            return 0;
        }
        if (i < 0) {
            throw new IllegalArgumentException(new StringBuffer("namespace count may be 0..").append(this.depth).append(" not ").append(i).toString());
        }
        int[] iArr = this.elNamespaceCount;
        return iArr[i] - iArr[i - 1];
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getNamespacePrefix(int i) {
        if (!isElementEvent(this.eventType)) {
            throwIllegalState(new int[]{1, 2});
        }
        int i2 = this.depth;
        int namespaceCount = getNamespaceCount(i2);
        int i3 = this.elNamespaceCount[i2 - 1] + i;
        if (i < namespaceCount) {
            return this.namespacePrefix[i3];
        }
        throw new ArrayIndexOutOfBoundsException(new StringBuffer("position ").append(i).append(" exceeded number of available namespaces ").append(namespaceCount).toString());
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getNamespaceURI(int i) {
        if (!isElementEvent(this.eventType)) {
            throwIllegalState(new int[]{1, 2});
        }
        int i2 = this.depth;
        int namespaceCount = getNamespaceCount(i2);
        int i3 = this.elNamespaceCount[i2 - 1] + i;
        if (i < namespaceCount) {
            return this.namespaceUri[i3];
        }
        throw new ArrayIndexOutOfBoundsException(new StringBuffer("position ").append(i).append(" exceedded number of available namespaces ").append(namespaceCount).toString());
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getNamespaceURI(String str) {
        if (!isElementEvent(this.eventType)) {
            throwIllegalState(new int[]{1, 2});
        }
        if (str != null && str.length() > 0) {
            for (int i = this.namespaceEnd - 1; i >= 0; i--) {
                if (str.equals(this.namespacePrefix[i])) {
                    return this.namespaceUri[i];
                }
            }
            if (XMLConstants.XML_NS_PREFIX.equals(str)) {
                return XMLConstants.XML_NS_URI;
            }
            if (XMLConstants.XMLNS_ATTRIBUTE.equals(str)) {
                return XMLConstants.XMLNS_ATTRIBUTE_NS_URI;
            }
            return null;
        }
        for (int i2 = this.namespaceEnd - 1; i2 >= 0; i2--) {
            if (this.namespacePrefix[i2] == null) {
                return this.namespaceUri[i2];
            }
        }
        return null;
    }

    public int getDepth() {
        return this.depth;
    }

    private static int findFragment(int i, char[] cArr, int i2, int i3) {
        if (i2 < i) {
            return i > i3 ? i3 : i;
        }
        if (i3 - i2 > 65) {
            i2 = i3 - 10;
        }
        int i4 = i2 + 1;
        while (true) {
            i4--;
            if (i4 <= i || i3 - i4 > 65 || (cArr[i4] == '<' && i2 - i4 > 10)) {
                break;
            }
        }
        return i4;
    }

    public String getPositionDescription() {
        int i = this.posStart;
        int i2 = this.pos;
        if (i <= i2) {
            int iFindFragment = findFragment(0, this.buf, i, i2);
            str = iFindFragment < this.pos ? new String(this.buf, iFindFragment, this.pos - iFindFragment) : null;
            if (this.bufAbsoluteStart > 0 || iFindFragment > 0) {
                str = new StringBuffer("...").append(str).toString();
            }
        }
        return new StringBuffer(" ").append(str != null ? new StringBuffer(" seen ").append(printable(str)).append("...").toString() : "").append(" @").append(getLineNumber()).append(":").append(getColumnNumber()).toString();
    }

    @Override // javax.xml.stream.Location
    public int getLineNumber() {
        return this.lineNumber;
    }

    @Override // javax.xml.stream.Location
    public int getColumnNumber() {
        return this.columnNumber;
    }

    @Override // javax.xml.stream.XMLStreamReader
    public boolean isWhiteSpace() {
        int i = this.eventType;
        if (i != 4 && i != 12) {
            return i == 6;
        }
        if (this.usePC) {
            for (int i2 = this.pcStart; i2 < this.pcEnd; i2++) {
                if (!isS(this.pc[i2])) {
                    return false;
                }
            }
            return true;
        }
        for (int i3 = this.posStart; i3 < this.posEnd; i3++) {
            if (!isS(this.buf[i3])) {
                return false;
            }
        }
        return true;
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getNamespaceURI() {
        int i = this.eventType;
        if (i == 1 || i == 2) {
            return this.processNamespaces ? this.elUri[this.depth] : NO_NAMESPACE;
        }
        return throwIllegalState(new int[]{1, 2});
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getLocalName() {
        int i = this.eventType;
        if (i == 1) {
            return this.elName[this.depth];
        }
        if (i == 2) {
            return this.elName[this.depth];
        }
        if (i == 9) {
            if (this.entityRefName == null) {
                char[] cArr = this.buf;
                int i2 = this.posStart;
                this.entityRefName = newString(cArr, i2, this.posEnd - i2);
            }
            return this.entityRefName;
        }
        return throwIllegalState(new int[]{1, 2, 9});
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getPrefix() {
        int i = this.eventType;
        if (i == 1 || i == 2) {
            return this.elPrefix[this.depth];
        }
        return throwIllegalState(new int[]{1, 2});
    }

    public boolean isEmptyElementTag() throws XMLStreamException {
        if (this.eventType != 1) {
            throw new XMLStreamException("parser must be on XMLStreamConstants.START_ELEMENT to check for empty element", getLocation());
        }
        return this.emptyElementTag;
    }

    @Override // javax.xml.stream.XMLStreamReader
    public int getAttributeCount() {
        if (this.eventType != 1) {
            throwIllegalState(1);
        }
        return this.attributeCount;
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getAttributeNamespace(int i) {
        if (this.eventType != 1) {
            throwIllegalState(1);
        }
        if (!this.processNamespaces) {
            return NO_NAMESPACE;
        }
        if (i < 0 || i >= this.attributeCount) {
            throw new IndexOutOfBoundsException(new StringBuffer("attribute position must be 0..").append(this.attributeCount - 1).append(" and not ").append(i).toString());
        }
        return this.attributeUri[i];
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getAttributeLocalName(int i) {
        if (this.eventType != 1) {
            throwIllegalState(1);
        }
        if (i < 0 || i >= this.attributeCount) {
            throw new IndexOutOfBoundsException(new StringBuffer("attribute position must be 0..").append(this.attributeCount - 1).append(" and not ").append(i).toString());
        }
        return this.attributeName[i];
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getAttributePrefix(int i) {
        if (this.eventType != 1) {
            throwIllegalState(1);
        }
        if (!this.processNamespaces) {
            return null;
        }
        if (i < 0 || i >= this.attributeCount) {
            throw new IndexOutOfBoundsException(new StringBuffer("attribute position must be 0..").append(this.attributeCount - 1).append(" and not ").append(i).toString());
        }
        return this.attributePrefix[i];
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getAttributeType(int i) {
        if (this.eventType != 1) {
            throwIllegalState(1);
        }
        if (i < 0 || i >= this.attributeCount) {
            throw new IndexOutOfBoundsException(new StringBuffer("attribute position must be 0..").append(this.attributeCount - 1).append(" and not ").append(i).toString());
        }
        return "CDATA";
    }

    @Override // javax.xml.stream.XMLStreamReader
    public boolean isAttributeSpecified(int i) {
        if (this.eventType != 1) {
            throwIllegalState(1);
        }
        if (i < 0 || i >= this.attributeCount) {
            throw new IndexOutOfBoundsException(new StringBuffer("attribute position must be 0..").append(this.attributeCount - 1).append(" and not ").append(i).toString());
        }
        return true;
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getAttributeValue(int i) {
        if (this.eventType != 1) {
            throwIllegalState(1);
        }
        if (i < 0 || i >= this.attributeCount) {
            throw new IndexOutOfBoundsException(new StringBuffer("attribute position must be 0..").append(this.attributeCount - 1).append(" and not ").append(i).toString());
        }
        return this.attributeValue[i];
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getAttributeValue(String str, String str2) {
        if (this.eventType != 1) {
            throwIllegalState(1);
        }
        if (str2 == null) {
            throw new IllegalArgumentException("attribute name can not be null");
        }
        int i = 0;
        if (str != null) {
            while (i < this.attributeCount) {
                if (str2.equals(this.attributeName[i]) && str.equals(this.attributeUri[i])) {
                    return this.attributeValue[i];
                }
                i++;
            }
            return null;
        }
        while (i < this.attributeCount) {
            if (str2.equals(this.attributeName[i])) {
                return this.attributeValue[i];
            }
            i++;
        }
        return null;
    }

    @Override // javax.xml.stream.XMLStreamReader
    public int getEventType() {
        return this.eventType;
    }

    @Override // javax.xml.stream.XMLStreamReader
    public void require(int i, String str, String str2) throws XMLStreamException {
        int eventType = getEventType();
        boolean zEquals = i == eventType;
        if (zEquals && str2 != null) {
            if (eventType == 1 || eventType == 2 || eventType == 9) {
                zEquals = str2.equals(getLocalName());
            } else {
                throw new XMLStreamException(new StringBuffer("Using non-null local name argument for require(); ").append(ElementTypeNames.getEventTypeString(eventType)).append(" event does not have local name").toString(), getLocation());
            }
        }
        if (zEquals && str != null && (eventType == 1 || eventType == 1)) {
            String namespaceURI = getNamespaceURI();
            if (str.length() == 0) {
                zEquals = namespaceURI == null;
            } else {
                zEquals = str.equals(namespaceURI);
            }
        }
        if (zEquals) {
            return;
        }
        String string = "";
        StringBuffer stringBufferAppend = new StringBuffer("expected event ").append(ElementTypeNames.getEventTypeString(i)).append(str2 != null ? new StringBuffer(" with name '").append(str2).append("'").toString() : "").append((str == null || str2 == null) ? "" : " and").append(str != null ? new StringBuffer(" with namespace '").append(str).append("'").toString() : "").append(" but got").append(i != getEventType() ? new StringBuffer(" ").append(ElementTypeNames.getEventTypeString(getEventType())).toString() : "").append((str2 == null || getLocalName() == null || str2.equals(getName())) ? "" : new StringBuffer(" name '").append(getLocalName()).append("'").toString()).append((str == null || str2 == null || getLocalName() == null || str2.equals(getName()) || getNamespaceURI() == null || str.equals(getNamespaceURI())) ? "" : " and");
        if (str != null && getNamespaceURI() != null && !str.equals(getNamespaceURI())) {
            string = new StringBuffer(" namespace '").append(getNamespaceURI()).append("'").toString();
        }
        throw new XMLStreamException(stringBufferAppend.append(string).append(" (position:").append(getPositionDescription()).append(")").toString(), getLocation());
    }

    public String nextText() throws XMLStreamException {
        if (getEventType() != 1) {
            throw new XMLStreamException("parser must be on START_ELEMENT to read next text", getLocation());
        }
        int next = next();
        if (next != 4) {
            if (next == 2) {
                return "";
            }
            throw new XMLStreamException("parser must be on START_ELEMENT or TEXT to read text", getLocation());
        }
        String text = getText();
        if (next() == 2) {
            return text;
        }
        throw new XMLStreamException(new StringBuffer("TEXT must be immediately followed by END_ELEMENT and not ").append(ElementTypeNames.getEventTypeString(getEventType())).toString(), getLocation());
    }

    @Override // javax.xml.stream.XMLStreamReader
    public int nextTag() throws XMLStreamException {
        next();
        while (true) {
            int i = this.eventType;
            if (i != 6 && i != 5 && i != 3 && ((i != 4 || !isWhiteSpace()) && (this.eventType != 12 || !isWhiteSpace()))) {
                break;
            }
            next();
        }
        int i2 = this.eventType;
        if (i2 == 1 || i2 == 2) {
            return i2;
        }
        throw new XMLStreamException(new StringBuffer("expected XMLStreamConstants.START_ELEMENT or XMLStreamConstants.END_ELEMENT not ").append(ElementTypeNames.getEventTypeString(getEventType())).toString(), getLocation());
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getElementText() throws XMLStreamException {
        StringBuffer stringBuffer = new StringBuffer();
        if (getEventType() != 1) {
            throw new XMLStreamException("Precondition for readText is getEventType() == START_ELEMENT");
        }
        while (next() != 8) {
            if (isStartElement()) {
                throw new XMLStreamException("Unexpected Element start");
            }
            if (isCharacters() || getEventType() == 9) {
                stringBuffer.append(getText());
            }
            if (isEndElement()) {
                return stringBuffer.toString();
            }
        }
        throw new XMLStreamException("Unexpected end of Document");
    }

    @Override // javax.xml.stream.XMLStreamReader
    public int next() throws XMLStreamException {
        this.tokenize = true;
        this.pcStart = 0;
        this.pcEnd = 0;
        this.usePC = false;
        return nextImpl();
    }

    public int nextToken() throws XMLStreamException {
        this.tokenize = true;
        return nextImpl();
    }

    public int nextElement() throws XMLStreamException {
        return nextTag();
    }

    @Override // javax.xml.stream.XMLStreamReader
    public boolean hasNext() throws XMLStreamException {
        return this.eventType != 8;
    }

    public void skip() throws XMLStreamException {
        nextToken();
    }

    @Override // javax.xml.stream.XMLStreamReader
    public boolean isStartElement() {
        return this.eventType == 1;
    }

    @Override // javax.xml.stream.XMLStreamReader
    public boolean isEndElement() {
        return this.eventType == 2;
    }

    @Override // javax.xml.stream.XMLStreamReader
    public boolean isCharacters() {
        return this.eventType == 4;
    }

    public boolean isEOF() {
        return this.eventType == 8;
    }

    public boolean moveToStartElement() throws XMLStreamException {
        if (isStartElement()) {
            return true;
        }
        while (hasNext()) {
            if (isStartElement()) {
                return true;
            }
            next();
        }
        return false;
    }

    public boolean moveToStartElement(String str) throws XMLStreamException {
        if (str == null) {
            return false;
        }
        while (moveToStartElement()) {
            if (str.equals(getLocalName())) {
                return true;
            }
            if (!hasNext()) {
                return false;
            }
            next();
        }
        return false;
    }

    public boolean moveToStartElement(String str, String str2) throws XMLStreamException {
        if (str != null && str2 != null) {
            while (moveToStartElement(str)) {
                if (str2.equals(getNamespaceURI())) {
                    return true;
                }
                if (!hasNext()) {
                    return false;
                }
                next();
            }
        }
        return false;
    }

    public boolean moveToEndElement() throws XMLStreamException {
        if (isEndElement()) {
            return true;
        }
        while (hasNext()) {
            if (isEndElement()) {
                return true;
            }
            next();
        }
        return false;
    }

    public boolean moveToEndElement(String str) throws XMLStreamException {
        if (str == null) {
            return false;
        }
        while (moveToEndElement()) {
            if (str.equals(getLocalName())) {
                return true;
            }
            if (!hasNext()) {
                return false;
            }
            next();
        }
        return false;
    }

    public boolean moveToEndElement(String str, String str2) throws XMLStreamException {
        if (str != null && str2 != null) {
            while (moveToEndElement(str)) {
                if (str2.equals(getNamespaceURI())) {
                    return true;
                }
                if (!hasNext()) {
                    return false;
                }
                next();
            }
        }
        return false;
    }

    public boolean hasAttributes() {
        return getAttributeCount() > 0;
    }

    public boolean hasNamespaces() {
        return getNamespaceCount() > 0;
    }

    public Iterator getAttributes() {
        if (!hasAttributes()) {
            return EmptyIterator.emptyIterator;
        }
        int attributeCount = getAttributeCount();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < attributeCount; i++) {
            arrayList.add(new AttributeBase(getAttributePrefix(i), getAttributeNamespace(i), getAttributeLocalName(i), getAttributeValue(i), getAttributeType(i)));
        }
        return arrayList.iterator();
    }

    public Iterator internalGetNamespaces(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        int i3 = this.elNamespaceCount[i - 1];
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = i4 + i3;
            String localNamespacePrefix = getLocalNamespacePrefix(i5);
            if (localNamespacePrefix == null) {
                arrayList.add(new NamespaceBase(getLocalNamespaceURI(i5)));
            } else {
                arrayList.add(new NamespaceBase(localNamespacePrefix, getLocalNamespaceURI(i5)));
            }
        }
        return arrayList.iterator();
    }

    public Iterator getNamespaces() {
        if (!hasNamespaces()) {
            return EmptyIterator.emptyIterator;
        }
        return internalGetNamespaces(this.depth, getLocalNamespaceCount());
    }

    public Iterator getOutOfScopeNamespaces() {
        int[] iArr = this.elNamespaceCount;
        int i = this.depth;
        return internalGetNamespaces(i, iArr[i] - iArr[i - 1]);
    }

    public XMLStreamReader subReader() throws XMLStreamException {
        return new SubReader(this);
    }

    public void recycle() throws XMLStreamException {
        reset();
    }

    public Reader getTextStream() {
        throw new UnsupportedOperationException();
    }

    private final void checkTextEvent() {
        if (!hasText()) {
            throw new IllegalStateException(new StringBuffer("Current state (").append(eventTypeDesc(this.eventType)).append(") does not have textual content").toString());
        }
    }

    private final void checkTextEventXxx() {
        int i = this.eventType;
        if (i != 4 && i != 12 && i != 5 && i != 6) {
            throw new IllegalStateException(new StringBuffer("getTextXxx methods cannot be called for ").append(eventTypeDesc(this.eventType)).toString());
        }
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getText() {
        checkTextEvent();
        if (this.eventType == 9) {
            if (this.text == null && this.entityValue != null) {
                this.text = new String(this.entityValue);
            }
            return this.text;
        }
        if (this.usePC) {
            char[] cArr = this.pc;
            int i = this.pcStart;
            this.text = new String(cArr, i, this.pcEnd - i);
        } else {
            char[] cArr2 = this.buf;
            int i2 = this.posStart;
            this.text = new String(cArr2, i2, this.posEnd - i2);
        }
        return this.text;
    }

    @Override // javax.xml.stream.XMLStreamReader
    public int getTextCharacters(int i, char[] cArr, int i2, int i3) throws XMLStreamException {
        checkTextEventXxx();
        int textLength = getTextLength();
        if (i < 0 || i > textLength) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i4 = textLength - i;
        if (i4 < i3) {
            i3 = i4;
        }
        if (i3 > 0) {
            System.arraycopy(getTextCharacters(), getTextStart() + i, cArr, i2, i3);
        }
        return i3;
    }

    @Override // javax.xml.stream.XMLStreamReader
    public char[] getTextCharacters() {
        checkTextEventXxx();
        if (this.eventType == 4) {
            if (this.usePC) {
                return this.pc;
            }
            return this.buf;
        }
        return this.buf;
    }

    @Override // javax.xml.stream.XMLStreamReader
    public int getTextStart() {
        checkTextEventXxx();
        if (this.usePC) {
            return this.pcStart;
        }
        return this.posStart;
    }

    @Override // javax.xml.stream.XMLStreamReader
    public int getTextLength() {
        int i;
        int i2;
        checkTextEventXxx();
        if (this.usePC) {
            i = this.pcEnd;
            i2 = this.pcStart;
        } else {
            i = this.posEnd;
            i2 = this.posStart;
        }
        return i - i2;
    }

    @Override // javax.xml.stream.XMLStreamReader
    public boolean hasText() {
        int i = this.eventType;
        return i == 4 || i == 11 || i == 12 || i == 5 || i == 6 || i == 9;
    }

    public String getValue() {
        return getText();
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getEncoding() {
        return getInputEncoding();
    }

    @Override // javax.xml.stream.Location
    public int getCharacterOffset() {
        return this.posEnd;
    }

    private static final String checkNull(String str) {
        return str != null ? str : "";
    }

    private static String eventTypeDesc(int i) {
        if (i < 0) {
            return "[UNKNOWN]";
        }
        String[] strArr = TYPES;
        return i >= strArr.length ? "[UNKNOWN]" : strArr[i];
    }

    @Override // javax.xml.stream.XMLStreamReader
    public QName getAttributeName(int i) {
        if (this.eventType != 1) {
            throwIllegalState(1);
        }
        return new QName(checkNull(getAttributeNamespace(i)), getAttributeLocalName(i), checkNull(getAttributePrefix(i)));
    }

    @Override // javax.xml.stream.XMLStreamReader
    public QName getName() {
        if (!isElementEvent(this.eventType)) {
            throw new IllegalStateException("Current state not START_ELEMENT or END_ELEMENT");
        }
        return new QName(checkNull(getNamespaceURI()), getLocalName(), checkNull(getPrefix()));
    }

    @Override // javax.xml.stream.XMLStreamReader
    public boolean hasName() {
        return isElementEvent(this.eventType);
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getVersion() {
        return this.xmlVersion;
    }

    @Override // javax.xml.stream.XMLStreamReader
    public boolean isStandalone() {
        return this.standalone;
    }

    @Override // javax.xml.stream.XMLStreamReader
    public boolean standaloneSet() {
        return this.standaloneSet;
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getCharacterEncodingScheme() {
        return this.charEncodingScheme;
    }

    protected int nextImpl() throws XMLStreamException {
        char cMore;
        try {
            this.text = null;
            this.bufStart = this.posEnd;
            if (this.pastEndTag) {
                this.pastEndTag = false;
                int i = this.depth - 1;
                this.depth = i;
                this.namespaceEnd = this.elNamespaceCount[i];
            }
            if (this.emptyElementTag) {
                this.emptyElementTag = false;
                this.pastEndTag = true;
                this.eventType = 2;
                return 2;
            }
            if (this.depth > 0) {
                if (this.seenStartTag) {
                    this.seenStartTag = false;
                    int startTag = parseStartTag();
                    this.eventType = startTag;
                    return startTag;
                }
                if (this.seenEndTag) {
                    this.seenEndTag = false;
                    int endTag = parseEndTag();
                    this.eventType = endTag;
                    return endTag;
                }
                if (this.seenMarkup) {
                    this.seenMarkup = false;
                    cMore = '<';
                } else if (this.seenAmpersand) {
                    this.seenAmpersand = false;
                    cMore = '&';
                } else {
                    cMore = more();
                }
                this.posStart = this.pos - 1;
                boolean z = false;
                boolean z2 = false;
                while (true) {
                    if (cMore == '<') {
                        if (z && this.tokenize) {
                            this.seenMarkup = true;
                            this.eventType = 4;
                            return 4;
                        }
                        char cMore2 = more();
                        if (cMore2 == '/') {
                            if (!this.tokenize && z) {
                                this.seenEndTag = true;
                                this.eventType = 4;
                                return 4;
                            }
                            int endTag2 = parseEndTag();
                            this.eventType = endTag2;
                            return endTag2;
                        }
                        if (cMore2 == '!') {
                            char cMore3 = more();
                            if (cMore3 == '-') {
                                parseComment();
                                if (this.tokenize) {
                                    this.eventType = 5;
                                    return 5;
                                }
                                if (!this.usePC && z) {
                                    z2 = true;
                                }
                            } else if (cMore3 == '[') {
                                int i2 = this.posStart;
                                int i3 = this.posEnd;
                                parseCDATA();
                                int i4 = this.posStart;
                                int i5 = this.posEnd;
                                this.posStart = i2;
                                this.posEnd = i3;
                                int i6 = i5 - i4;
                                if (i6 > 0) {
                                    if (z) {
                                        if (!this.usePC) {
                                            if (i3 > i2) {
                                                joinPC();
                                            } else {
                                                this.usePC = true;
                                                this.pcEnd = 0;
                                                this.pcStart = 0;
                                            }
                                        }
                                        int i7 = this.pcEnd;
                                        if (i7 + i6 >= this.pc.length) {
                                            ensurePC(i7 + i6);
                                        }
                                        System.arraycopy(this.buf, i4, this.pc, this.pcEnd, i6);
                                        this.pcEnd += i6;
                                    } else {
                                        this.posStart = i4;
                                        this.posEnd = i5;
                                        z2 = true;
                                    }
                                    z = true;
                                } else if (!this.usePC && z) {
                                    z2 = true;
                                }
                                if (this.reportCdataEvent) {
                                    this.eventType = 12;
                                    return 12;
                                }
                            } else {
                                throw new XMLStreamException(new StringBuffer().append("unexpected character in markup ").append(printable(cMore3)).toString(), getLocation());
                            }
                        } else if (cMore2 == '?') {
                            parsePI();
                            if (this.tokenize) {
                                this.eventType = 3;
                                return 3;
                            }
                            if (!this.usePC && z) {
                                z2 = true;
                            }
                        } else {
                            if (isNameStartChar(cMore2)) {
                                if (!this.tokenize && z) {
                                    this.seenStartTag = true;
                                    this.eventType = 4;
                                    return 4;
                                }
                                int startTag2 = parseStartTag();
                                this.eventType = startTag2;
                                return startTag2;
                            }
                            throw new XMLStreamException(new StringBuffer().append("unexpected character in markup ").append(printable(cMore2)).toString(), getLocation());
                        }
                    } else if (cMore == '&') {
                        if (this.tokenize && z) {
                            this.seenAmpersand = true;
                            this.eventType = 4;
                            return 4;
                        }
                        int i8 = this.posStart;
                        int i9 = this.posEnd;
                        boolean zIsReplacingEntities = getConfigurationContext().isReplacingEntities();
                        char[] entityRef = parseEntityRef(zIsReplacingEntities);
                        if (!zIsReplacingEntities) {
                            this.eventType = 9;
                            return 9;
                        }
                        this.eventType = 4;
                        if (entityRef == null) {
                            if (this.entityRefName == null) {
                                char[] cArr = this.buf;
                                int i10 = this.posStart;
                                this.entityRefName = newString(cArr, i10, this.posEnd - i10);
                            }
                            throw new XMLStreamException(new StringBuffer().append("could not resolve entity named '").append(printable(this.entityRefName)).append("'").toString(), getLocation());
                        }
                        this.posStart = i8;
                        this.posEnd = i9;
                        if (!this.usePC) {
                            if (z) {
                                joinPC();
                                z2 = false;
                            } else {
                                this.usePC = true;
                                this.pcEnd = 0;
                                this.pcStart = 0;
                            }
                        }
                        for (char c : entityRef) {
                            int i11 = this.pcEnd;
                            if (i11 >= this.pc.length) {
                                ensurePC(i11);
                            }
                            char[] cArr2 = this.pc;
                            int i12 = this.pcEnd;
                            this.pcEnd = i12 + 1;
                            cArr2[i12] = c;
                        }
                        z = true;
                    } else {
                        if (z2) {
                            joinPC();
                            z2 = false;
                        }
                        boolean z3 = false;
                        do {
                            if (cMore == '\r') {
                                int i13 = this.pos - 1;
                                this.posEnd = i13;
                                if (!this.usePC) {
                                    if (i13 > this.posStart) {
                                        joinPC();
                                    } else {
                                        this.usePC = true;
                                        this.pcEnd = 0;
                                        this.pcStart = 0;
                                    }
                                }
                                int i14 = this.pcEnd;
                                if (i14 >= this.pc.length) {
                                    ensurePC(i14);
                                }
                                char[] cArr3 = this.pc;
                                int i15 = this.pcEnd;
                                this.pcEnd = i15 + 1;
                                cArr3[i15] = '\n';
                                z3 = true;
                            } else {
                                if (cMore == '\n') {
                                    if (!z3 && this.usePC) {
                                        int i16 = this.pcEnd;
                                        if (i16 >= this.pc.length) {
                                            ensurePC(i16);
                                        }
                                        char[] cArr4 = this.pc;
                                        int i17 = this.pcEnd;
                                        this.pcEnd = i17 + 1;
                                        cArr4[i17] = '\n';
                                    }
                                } else if (this.usePC) {
                                    int i18 = this.pcEnd;
                                    if (i18 >= this.pc.length) {
                                        ensurePC(i18);
                                    }
                                    char[] cArr5 = this.pc;
                                    int i19 = this.pcEnd;
                                    this.pcEnd = i19 + 1;
                                    cArr5[i19] = cMore;
                                }
                                z3 = false;
                            }
                            cMore = more();
                            if (cMore == '<') {
                                break;
                            }
                        } while (cMore != '&');
                        this.posEnd = this.pos - 1;
                        z = true;
                    }
                    cMore = more();
                }
            } else {
                if (this.seenRoot) {
                    return parseEpilog();
                }
                return parseProlog();
            }
        } catch (EOFException e) {
            throw new XMLStreamException(EOF_MSG, getLocation(), e);
        }
    }

    protected int parseProlog() throws XMLStreamException {
        char cMore;
        try {
            if (this.seenMarkup) {
                cMore = this.buf[this.pos - 1];
            } else {
                cMore = more();
            }
            if (this.eventType == 7) {
                if (cMore == 65534) {
                    throw new XMLStreamException("first character in input was UNICODE noncharacter (0xFFFE)- input requires int swapping", getLocation());
                }
                if (cMore == 65279) {
                    cMore = more();
                }
            }
            boolean z = false;
            this.seenMarkup = false;
            this.posStart = this.pos - 1;
            while (true) {
                if (cMore == '<') {
                    if (z && this.tokenize) {
                        this.posEnd = this.pos - 1;
                        this.seenMarkup = true;
                        this.eventType = 6;
                        return 6;
                    }
                    char cMore2 = more();
                    if (cMore2 == '?') {
                        boolean pi = parsePI();
                        if (this.tokenize) {
                            if (pi) {
                                this.eventType = 7;
                                return 7;
                            }
                            this.eventType = 3;
                            return 3;
                        }
                    } else {
                        if (cMore2 != '!') {
                            if (cMore2 == '/') {
                                throw new XMLStreamException(new StringBuffer().append("expected start tag name and not ").append(printable(cMore2)).toString(), getLocation());
                            }
                            if (isNameStartChar(cMore2)) {
                                this.seenRoot = true;
                                return parseStartTag();
                            }
                            throw new XMLStreamException(new StringBuffer().append("expected start tag name and not ").append(printable(cMore2)).toString(), getLocation());
                        }
                        char cMore3 = more();
                        if (cMore3 == 'D') {
                            if (this.seenDocdecl) {
                                throw new XMLStreamException("only one docdecl allowed in XML document", getLocation());
                            }
                            this.seenDocdecl = true;
                            parseDocdecl();
                            if (this.tokenize) {
                                this.eventType = 11;
                                return 11;
                            }
                        } else if (cMore3 == '-') {
                            parseComment();
                            if (this.tokenize) {
                                this.eventType = 5;
                                return 5;
                            }
                        } else {
                            throw new XMLStreamException(new StringBuffer().append("unexpected markup <!").append(printable(cMore3)).toString(), getLocation());
                        }
                    }
                } else {
                    if (!isS(cMore)) {
                        throw new XMLStreamException(new StringBuffer().append("only whitespace content allowed before start tag and not ").append(printable(cMore)).toString(), getLocation());
                    }
                    z = true;
                }
                cMore = more();
            }
        } catch (EOFException e) {
            throw new XMLStreamException(EOF_MSG, getLocation(), e);
        }
    }

    protected int parseEpilog() throws XMLStreamException {
        char cMore;
        if (this.eventType == 8) {
            throw new XMLStreamException("already reached end document", getLocation());
        }
        if (this.reachedEnd) {
            this.eventType = 8;
            return 8;
        }
        boolean z = false;
        try {
            if (this.seenMarkup) {
                cMore = this.buf[this.pos - 1];
            } else {
                cMore = more();
            }
            this.seenMarkup = false;
            this.posStart = this.pos - 1;
            while (true) {
                if (cMore == '<') {
                    if (z && this.tokenize) {
                        this.posEnd = this.pos - 1;
                        this.seenMarkup = true;
                        this.eventType = 6;
                        return 6;
                    }
                    char cMore2 = more();
                    if (cMore2 == '?') {
                        parsePI();
                        if (this.tokenize) {
                            this.eventType = 3;
                            return 3;
                        }
                    } else {
                        if (cMore2 != '!') {
                            if (cMore2 == '/') {
                                throw new XMLStreamException(new StringBuffer().append("end tag not allowed in epilog but got ").append(printable(cMore2)).toString(), getLocation());
                            }
                            if (isNameStartChar(cMore2)) {
                                throw new XMLStreamException(new StringBuffer().append("start tag not allowed in epilog but got ").append(printable(cMore2)).toString(), getLocation());
                            }
                            throw new XMLStreamException(new StringBuffer().append("in epilog expected ignorable content and not ").append(printable(cMore2)).toString(), getLocation());
                        }
                        char cMore3 = more();
                        if (cMore3 == 'D') {
                            parseDocdecl();
                            if (this.tokenize) {
                                this.eventType = 11;
                                return 11;
                            }
                        } else if (cMore3 == '-') {
                            parseComment();
                            if (this.tokenize) {
                                this.eventType = 5;
                                return 5;
                            }
                        } else {
                            throw new XMLStreamException(new StringBuffer().append("unexpected markup <!").append(printable(cMore3)).toString(), getLocation());
                        }
                    }
                } else {
                    if (!isS(cMore)) {
                        throw new XMLStreamException(new StringBuffer().append("in epilog non whitespace content is not allowed but got ").append(printable(cMore)).toString(), getLocation());
                    }
                    z = true;
                }
                cMore = more();
            }
        } catch (EOFException unused) {
            this.reachedEnd = true;
            if (this.tokenize && 0 != 0) {
                this.posEnd = this.pos;
                this.eventType = 6;
                return 6;
            }
            this.eventType = 8;
            return 8;
        }
    }

    public int parseEndTag() throws XMLStreamException {
        char cMore;
        this.eventType = 2;
        try {
            char cMore2 = more();
            if (!isNameStartChar(cMore2)) {
                throw new XMLStreamException(new StringBuffer("expected name start and not ").append(printable(cMore2)).toString(), getLocation());
            }
            int i = this.pos;
            this.posStart = i - 3;
            int i2 = (i - 1) + this.bufAbsoluteStart;
            do {
                cMore = more();
            } while (isNameChar(cMore));
            int i3 = this.pos - 1;
            int i4 = i2 - this.bufAbsoluteStart;
            int i5 = i3 - i4;
            char[][] cArr = this.elRawName;
            int i6 = this.depth;
            char[] cArr2 = cArr[i6];
            if (this.elRawNameEnd[i6] != i5) {
                throw new XMLStreamException(new StringBuffer().append("end tag name '").append(new String(this.buf, i4, i5)).append("' must match start tag name '").append(new String(cArr2, 0, this.elRawNameEnd[this.depth])).append("'").toString(), getLocation());
            }
            int i7 = 0;
            while (i7 < i5) {
                int i8 = i4 + 1;
                if (this.buf[i4] != cArr2[i7]) {
                    throw new XMLStreamException(new StringBuffer().append("end tag name '").append(new String(this.buf, (i8 - i7) - 1, i5)).append("' must be the same as start tag '").append(new String(cArr2, 0, i5)).append("'").toString(), getLocation());
                }
                i7++;
                i4 = i8;
            }
            while (isS(cMore)) {
                cMore = more();
            }
            if (cMore != '>') {
                throw new XMLStreamException(new StringBuffer().append("expected > to finsh end tag not ").append(printable(cMore)).toString(), getLocation());
            }
            this.posEnd = this.pos;
            this.pastEndTag = true;
            return 2;
        } catch (EOFException e) {
            throw new XMLStreamException(EOF_MSG, getLocation(), e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:75:0x0190 A[Catch: EOFException -> 0x0304, TryCatch #0 {EOFException -> 0x0304, blocks: (B:3:0x0003, B:5:0x0023, B:8:0x0028, B:9:0x0033, B:11:0x0036, B:13:0x0040, B:15:0x0053, B:18:0x005c, B:21:0x0072, B:24:0x00b9, B:26:0x00bf, B:35:0x00da, B:38:0x00e4, B:41:0x00ec, B:42:0x00ef, B:43:0x010b, B:44:0x010c, B:45:0x0113, B:47:0x0117, B:49:0x011d, B:51:0x0123, B:55:0x014b, B:52:0x0128, B:53:0x0144, B:54:0x0145, B:57:0x014f, B:61:0x0156, B:63:0x015e, B:65:0x0162, B:73:0x0186, B:75:0x0190, B:76:0x01a9, B:78:0x01b3, B:79:0x01cc, B:80:0x01ee, B:67:0x016e, B:69:0x0172, B:71:0x017a, B:81:0x01ef, B:82:0x01f3, B:103:0x0257, B:106:0x0269, B:107:0x0282, B:84:0x01f8, B:88:0x01ff, B:90:0x0203, B:99:0x0228, B:100:0x0250, B:92:0x020f, B:94:0x0213, B:96:0x021b, B:101:0x0251, B:102:0x0254, B:33:0x00d2, B:109:0x0286, B:110:0x02a6, B:111:0x02a7, B:116:0x02b2, B:117:0x02bd, B:118:0x02be, B:119:0x02c7, B:120:0x02e7, B:22:0x009a, B:23:0x00ac, B:17:0x0056, B:122:0x02ea, B:125:0x02f0, B:126:0x02f8, B:127:0x0303), top: B:131:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01b3 A[Catch: EOFException -> 0x0304, TryCatch #0 {EOFException -> 0x0304, blocks: (B:3:0x0003, B:5:0x0023, B:8:0x0028, B:9:0x0033, B:11:0x0036, B:13:0x0040, B:15:0x0053, B:18:0x005c, B:21:0x0072, B:24:0x00b9, B:26:0x00bf, B:35:0x00da, B:38:0x00e4, B:41:0x00ec, B:42:0x00ef, B:43:0x010b, B:44:0x010c, B:45:0x0113, B:47:0x0117, B:49:0x011d, B:51:0x0123, B:55:0x014b, B:52:0x0128, B:53:0x0144, B:54:0x0145, B:57:0x014f, B:61:0x0156, B:63:0x015e, B:65:0x0162, B:73:0x0186, B:75:0x0190, B:76:0x01a9, B:78:0x01b3, B:79:0x01cc, B:80:0x01ee, B:67:0x016e, B:69:0x0172, B:71:0x017a, B:81:0x01ef, B:82:0x01f3, B:103:0x0257, B:106:0x0269, B:107:0x0282, B:84:0x01f8, B:88:0x01ff, B:90:0x0203, B:99:0x0228, B:100:0x0250, B:92:0x020f, B:94:0x0213, B:96:0x021b, B:101:0x0251, B:102:0x0254, B:33:0x00d2, B:109:0x0286, B:110:0x02a6, B:111:0x02a7, B:116:0x02b2, B:117:0x02bd, B:118:0x02be, B:119:0x02c7, B:120:0x02e7, B:22:0x009a, B:23:0x00ac, B:17:0x0056, B:122:0x02ea, B:125:0x02f0, B:126:0x02f8, B:127:0x0303), top: B:131:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x020f A[Catch: EOFException -> 0x0304, TryCatch #0 {EOFException -> 0x0304, blocks: (B:3:0x0003, B:5:0x0023, B:8:0x0028, B:9:0x0033, B:11:0x0036, B:13:0x0040, B:15:0x0053, B:18:0x005c, B:21:0x0072, B:24:0x00b9, B:26:0x00bf, B:35:0x00da, B:38:0x00e4, B:41:0x00ec, B:42:0x00ef, B:43:0x010b, B:44:0x010c, B:45:0x0113, B:47:0x0117, B:49:0x011d, B:51:0x0123, B:55:0x014b, B:52:0x0128, B:53:0x0144, B:54:0x0145, B:57:0x014f, B:61:0x0156, B:63:0x015e, B:65:0x0162, B:73:0x0186, B:75:0x0190, B:76:0x01a9, B:78:0x01b3, B:79:0x01cc, B:80:0x01ee, B:67:0x016e, B:69:0x0172, B:71:0x017a, B:81:0x01ef, B:82:0x01f3, B:103:0x0257, B:106:0x0269, B:107:0x0282, B:84:0x01f8, B:88:0x01ff, B:90:0x0203, B:99:0x0228, B:100:0x0250, B:92:0x020f, B:94:0x0213, B:96:0x021b, B:101:0x0251, B:102:0x0254, B:33:0x00d2, B:109:0x0286, B:110:0x02a6, B:111:0x02a7, B:116:0x02b2, B:117:0x02bd, B:118:0x02be, B:119:0x02c7, B:120:0x02e7, B:22:0x009a, B:23:0x00ac, B:17:0x0056, B:122:0x02ea, B:125:0x02f0, B:126:0x02f8, B:127:0x0303), top: B:131:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int parseStartTag() throws javax.xml.stream.XMLStreamException {
        /*
            Method dump skipped, instruction units count: 785
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bea.xml.stream.MXParser.parseStartTag():int");
    }

    protected void addDefaultAttributes(String str) throws XMLStreamException {
        HashMap map = this.defaultAttributes;
        if (map == null) {
            return;
        }
        DTDAttlist dTDAttlist = (DTDAttlist) map.get(str);
        if (str == null || dTDAttlist == null) {
            return;
        }
        for (DTDAttribute dTDAttribute : dTDAttlist.getAttribute()) {
            if (dTDAttribute.getDefaultValue() != null) {
                int i = this.attributeCount;
                int i2 = 0;
                while (true) {
                    if (i2 < i) {
                        if (this.attributeName[i2].equals(dTDAttribute.getName())) {
                            break;
                        } else {
                            i2++;
                        }
                    } else {
                        int i3 = this.attributeCount + 1;
                        this.attributeCount = i3;
                        ensureAttributesCapacity(i3);
                        String[] strArr = this.attributePrefix;
                        int i4 = this.attributeCount;
                        strArr[i4 - 1] = null;
                        this.attributeUri[i4 - 1] = NO_NAMESPACE;
                        this.attributeName[i4 - 1] = dTDAttribute.getName();
                        this.attributeValue[this.attributeCount - 1] = dTDAttribute.getDefaultValue();
                        break;
                    }
                }
            }
        }
    }

    protected char parseAttribute() throws XMLStreamException {
        String str;
        String strNewString;
        String strNewStringIntern;
        String string;
        try {
            int i = this.posStart;
            int i2 = this.bufAbsoluteStart;
            int i3 = i + i2;
            int i4 = this.pos;
            int i5 = (i4 - 1) + i2;
            char c = this.buf[i4 - 1];
            if (c == ':' && this.processNamespaces) {
                throw new XMLStreamException("when namespaces processing enabled colon can not be at attribute name start", getLocation());
            }
            boolean z = this.processNamespaces && c == 'x';
            char cMore = more();
            int iHashCode = -1;
            int i6 = 0;
            int i7 = -1;
            while (isNameChar(cMore)) {
                if (this.processNamespaces) {
                    if (z && i6 < 5) {
                        i6++;
                        if (i6 == 1) {
                            if (cMore != 'm') {
                                z = false;
                            }
                        } else if (i6 == 2) {
                            if (cMore != 'l') {
                                z = false;
                            }
                        } else if (i6 == 3) {
                            if (cMore != 'n') {
                                z = false;
                            }
                        } else if (i6 == 4) {
                            if (cMore != 's') {
                                z = false;
                            }
                        } else if (i6 == 5 && cMore != ':') {
                            throw new XMLStreamException("after xmlns in attribute name must be colonwhen namespaces are enabled", getLocation());
                        }
                    }
                    if (cMore != ':') {
                        continue;
                    } else {
                        if (i7 != -1) {
                            throw new XMLStreamException("only one colon is allowed in attribute name when namespaces are enabled", getLocation());
                        }
                        i7 = this.bufAbsoluteStart + (this.pos - 1);
                    }
                }
                cMore = more();
            }
            ensureAttributesCapacity(this.attributeCount);
            if (this.processNamespaces) {
                if (i6 < 4) {
                    z = false;
                }
                if (z) {
                    if (i7 != -1) {
                        char[] cArr = this.buf;
                        int i8 = this.bufAbsoluteStart;
                        strNewString = newString(cArr, (i7 - i8) + 1, (this.pos - 2) - (i7 - i8));
                        if (strNewString.equals(XMLConstants.XMLNS_ATTRIBUTE)) {
                            throw new XMLStreamException("trying to bind reserved NS prefix 'xmlns'", getLocation());
                        }
                    } else {
                        strNewString = null;
                    }
                    str = null;
                } else {
                    if (i7 != -1) {
                        this.attributePrefix[this.attributeCount] = newString(this.buf, i5 - this.bufAbsoluteStart, i7 - i5);
                        String[] strArr = this.attributeName;
                        int i9 = this.attributeCount;
                        char[] cArr2 = this.buf;
                        int i10 = this.bufAbsoluteStart;
                        str = null;
                        String strNewString2 = newString(cArr2, (i7 - i10) + 1, (this.pos - 2) - (i7 - i10));
                        strArr[i9] = strNewString2;
                        strNewString = strNewString2;
                    } else {
                        str = null;
                        String[] strArr2 = this.attributePrefix;
                        int i11 = this.attributeCount;
                        strArr2[i11] = null;
                        String[] strArr3 = this.attributeName;
                        char[] cArr3 = this.buf;
                        int i12 = this.bufAbsoluteStart;
                        String strNewString3 = newString(cArr3, i5 - i12, (this.pos - 1) - (i5 - i12));
                        strArr3[i11] = strNewString3;
                        strNewString = strNewString3;
                    }
                    if (!this.allStringsInterned) {
                        this.attributeNameHash[this.attributeCount] = strNewString.hashCode();
                    }
                }
            } else {
                str = null;
                String[] strArr4 = this.attributeName;
                int i13 = this.attributeCount;
                char[] cArr4 = this.buf;
                int i14 = this.bufAbsoluteStart;
                String strNewString4 = newString(cArr4, i5 - i14, (this.pos - 1) - (i5 - i14));
                strArr4[i13] = strNewString4;
                if (!this.allStringsInterned) {
                    this.attributeNameHash[this.attributeCount] = strNewString4.hashCode();
                }
                strNewString = strNewString4;
            }
            while (isS(cMore)) {
                cMore = more();
            }
            if (cMore != '=') {
                throw new XMLStreamException("expected = after attribute name", getLocation());
            }
            char cMore2 = more();
            while (isS(cMore2)) {
                cMore2 = more();
            }
            if (cMore2 != '\"' && cMore2 != '\'') {
                throw new XMLStreamException(new StringBuffer().append("attribute value must start with quotation or apostrophe not ").append(printable(cMore2)).toString(), getLocation());
            }
            this.usePC = false;
            this.pcStart = this.pcEnd;
            this.posStart = this.pos;
            boolean z2 = false;
            while (true) {
                char cMore3 = more();
                if (cMore3 == cMore2) {
                    if (this.processNamespaces && z) {
                        if (!this.usePC) {
                            char[] cArr5 = this.buf;
                            int i15 = this.posStart;
                            strNewStringIntern = newStringIntern(cArr5, i15, (this.pos - 1) - i15);
                        } else {
                            char[] cArr6 = this.pc;
                            int i16 = this.pcStart;
                            strNewStringIntern = newStringIntern(cArr6, i16, this.pcEnd - i16);
                        }
                        ensureNamespacesCapacity(this.namespaceEnd);
                        if (strNewStringIntern.equals(XMLConstants.XML_NS_URI)) {
                            if (!XMLConstants.XML_NS_PREFIX.equals(strNewString)) {
                                throw new XMLStreamException("trying to bind reserved NS URI  'http://www.w3.org/XML/1998/namespace' to prefix other than 'xml'");
                            }
                        } else if (strNewStringIntern.equals(XMLConstants.XMLNS_ATTRIBUTE_NS_URI)) {
                            throw new XMLStreamException("trying to bind reserved NS URI  'http://www.w3.org/2000/xmlns/'");
                        }
                        if (i7 != iHashCode) {
                            if (strNewStringIntern.length() == 0) {
                                throw new XMLStreamException("non-default namespace can not be declared to be empty string (in xml 1.0)", getLocation());
                            }
                            if (strNewString.equals(XMLConstants.XML_NS_PREFIX) && !strNewStringIntern.equals(XMLConstants.XML_NS_URI)) {
                                throw new XMLStreamException("trying to bind reserved NS prefix 'xml' to URI other than its standard value (http://www.w3.org/XML/1998/namespace)", getLocation());
                            }
                            String[] strArr5 = this.namespacePrefix;
                            int i17 = this.namespaceEnd;
                            strArr5[i17] = strNewString;
                            if (!this.allStringsInterned) {
                                int[] iArr = this.namespacePrefixHash;
                                iHashCode = strNewString.hashCode();
                                iArr[i17] = iHashCode;
                            }
                        } else {
                            this.namespacePrefix[this.namespaceEnd] = str;
                            if (strNewStringIntern.length() == 0) {
                                strNewStringIntern = NO_NAMESPACE;
                            }
                            if (!this.allStringsInterned) {
                                this.namespacePrefixHash[this.namespaceEnd] = iHashCode;
                            }
                        }
                        this.namespaceUri[this.namespaceEnd] = strNewStringIntern;
                        int i18 = this.elNamespaceCount[this.depth - 1];
                        for (int i19 = r5 - 1; i19 >= i18; i19--) {
                            boolean z3 = this.allStringsInterned;
                            if (((!z3 && strNewString != null) || this.namespacePrefix[i19] != strNewString) && (z3 || strNewString == null || this.namespacePrefixHash[i19] != iHashCode || !strNewString.equals(this.namespacePrefix[i19]))) {
                            }
                            if (strNewString != null) {
                                string = new StringBuffer().append("'").append(strNewString).append("'").toString();
                            } else {
                                string = "default";
                            }
                            throw new XMLStreamException(new StringBuffer().append("duplicated namespace declaration for ").append(string).append(" prefix").toString(), getLocation());
                        }
                        this.namespaceEnd++;
                    } else {
                        if (!this.usePC) {
                            String[] strArr6 = this.attributeValue;
                            int i20 = this.attributeCount;
                            char[] cArr7 = this.buf;
                            int i21 = this.posStart;
                            strArr6[i20] = new String(cArr7, i21, (this.pos - 1) - i21);
                        } else {
                            String[] strArr7 = this.attributeValue;
                            int i22 = this.attributeCount;
                            char[] cArr8 = this.pc;
                            int i23 = this.pcStart;
                            strArr7[i22] = new String(cArr8, i23, this.pcEnd - i23);
                        }
                        this.attributeCount++;
                    }
                    this.posStart = i3 - this.bufAbsoluteStart;
                    return cMore3;
                }
                if (cMore3 == '<') {
                    throw new XMLStreamException("markup not allowed inside attribute value - illegal < ", getLocation());
                }
                if (cMore3 == '&') {
                    int i24 = this.pos - 1;
                    this.posEnd = i24;
                    if (!this.usePC) {
                        if (i24 > this.posStart) {
                            joinPC();
                        } else {
                            this.usePC = true;
                            this.pcEnd = 0;
                            this.pcStart = 0;
                        }
                    }
                    char[] entityRef = parseEntityRef(getConfigurationContext().isReplacingEntities());
                    if (entityRef == null) {
                        if (this.entityRefName == null) {
                            char[] cArr9 = this.buf;
                            int i25 = this.posStart;
                            this.entityRefName = newString(cArr9, i25, this.posEnd - i25);
                        }
                        throw new XMLStreamException(new StringBuffer().append("could not resolve entity named '").append(printable(this.entityRefName)).append("'").toString(), getLocation());
                    }
                    for (char c2 : entityRef) {
                        int i26 = this.pcEnd;
                        if (i26 >= this.pc.length) {
                            ensurePC(i26);
                        }
                        char[] cArr10 = this.pc;
                        int i27 = this.pcEnd;
                        this.pcEnd = i27 + 1;
                        cArr10[i27] = c2;
                    }
                } else if (cMore3 == '\t' || cMore3 == '\n' || cMore3 == '\r') {
                    if (!this.usePC) {
                        int i28 = this.pos - 1;
                        this.posEnd = i28;
                        if (i28 > this.posStart) {
                            joinPC();
                        } else {
                            this.usePC = true;
                            this.pcStart = 0;
                            this.pcEnd = 0;
                        }
                    }
                    int i29 = this.pcEnd;
                    if (i29 >= this.pc.length) {
                        ensurePC(i29);
                    }
                    if (cMore3 != '\n' || !z2) {
                        char[] cArr11 = this.pc;
                        int i30 = this.pcEnd;
                        this.pcEnd = i30 + 1;
                        cArr11[i30] = ' ';
                    }
                } else if (this.usePC) {
                    int i31 = this.pcEnd;
                    if (i31 >= this.pc.length) {
                        ensurePC(i31);
                    }
                    char[] cArr12 = this.pc;
                    int i32 = this.pcEnd;
                    this.pcEnd = i32 + 1;
                    cArr12[i32] = cMore3;
                }
                z2 = cMore3 == '\r';
                iHashCode = -1;
            }
        } catch (EOFException e) {
            throw new XMLStreamException(EOF_MSG, getLocation(), e);
        }
    }

    protected char[] parseEntityRef(boolean z) throws XMLStreamException {
        int i;
        int i2;
        try {
            this.entityRefName = null;
            this.posStart = this.pos;
            if (more() == '#') {
                char cMore = more();
                if (cMore == 'x') {
                    i = 0;
                    do {
                        char cMore2 = more();
                        if (cMore2 == ';') {
                            break;
                        }
                        int i3 = i << 4;
                        if (cMore2 >= '0' && cMore2 <= '9') {
                            i2 = cMore2 - 48;
                        } else if (cMore2 >= 'a' && cMore2 <= 'f') {
                            i2 = cMore2 - 87;
                        } else {
                            if (cMore2 < 'A' || cMore2 > 'F') {
                                throw new XMLStreamException(new StringBuffer().append("character reference (with hex value) may not contain ").append(printable(cMore2)).toString(), getLocation());
                            }
                            i2 = cMore2 - 55;
                        }
                        i = i3 + i2;
                    } while (i <= MAX_UNICODE_CHAR);
                } else {
                    int i4 = 0;
                    while (cMore >= '0' && cMore <= '9') {
                        i4 = (i4 * 10) + (cMore - '0');
                        cMore = more();
                        if (i4 > MAX_UNICODE_CHAR) {
                            break;
                        }
                    }
                    if (cMore != ';') {
                        throw new XMLStreamException(new StringBuffer().append("character reference (with decimal value) may not contain ").append(printable(cMore)).toString(), getLocation());
                    }
                    i = i4;
                }
                this.posEnd = this.pos - 1;
                checkCharValidity(i, false);
                if (i > 65535) {
                    if (this.charRefTwoCharBuf == null) {
                        this.charRefTwoCharBuf = new char[2];
                    }
                    int i5 = i - 65536;
                    char[] cArr = this.charRefTwoCharBuf;
                    cArr[0] = (char) ((i5 >> 10) + 55296);
                    cArr[1] = (char) ((i5 & 1023) + Utf8.LOG_SURROGATE_HEADER);
                    this.entityValue = cArr;
                    return cArr;
                }
                char[] cArr2 = this.charRefOneCharBuf;
                cArr2[0] = (char) i;
                this.entityValue = cArr2;
                return cArr2;
            }
            while (more() != ';') {
            }
            int i6 = this.pos - 1;
            this.posEnd = i6;
            int i7 = this.posStart;
            int i8 = i6 - i7;
            if (i8 == 2) {
                char[] cArr3 = this.buf;
                char c = cArr3[i7];
                if (c == 'l' && cArr3[i7 + 1] == 't') {
                    if (!z) {
                        this.text = "<";
                    }
                    char[] cArr4 = this.charRefOneCharBuf;
                    cArr4[0] = Typography.less;
                    this.entityValue = cArr4;
                    return cArr4;
                }
                if (c == 'g' && cArr3[i7 + 1] == 't') {
                    if (!z) {
                        this.text = ">";
                    }
                    char[] cArr5 = this.charRefOneCharBuf;
                    cArr5[0] = Typography.greater;
                    this.entityValue = cArr5;
                    return cArr5;
                }
            } else if (i8 == 3) {
                char[] cArr6 = this.buf;
                if (cArr6[i7] == 'a' && cArr6[i7 + 1] == 'm' && cArr6[i7 + 2] == 'p') {
                    if (!z) {
                        this.text = "&";
                    }
                    char[] cArr7 = this.charRefOneCharBuf;
                    cArr7[0] = Typography.amp;
                    this.entityValue = cArr7;
                    return cArr7;
                }
            } else if (i8 == 4) {
                char[] cArr8 = this.buf;
                char c2 = cArr8[i7];
                if (c2 == 'a' && cArr8[i7 + 1] == 'p' && cArr8[i7 + 2] == 'o' && cArr8[i7 + 3] == 's') {
                    if (!z) {
                        this.text = "'";
                    }
                    char[] cArr9 = this.charRefOneCharBuf;
                    cArr9[0] = '\'';
                    this.entityValue = cArr9;
                    return cArr9;
                }
                if (c2 == 'q' && cArr8[i7 + 1] == 'u' && cArr8[i7 + 2] == 'o' && cArr8[i7 + 3] == 't') {
                    if (!z) {
                        this.text = "\"";
                    }
                    char[] cArr10 = this.charRefOneCharBuf;
                    cArr10[0] = Typography.quote;
                    this.entityValue = cArr10;
                    return cArr10;
                }
            }
            char[] cArrLookupEntityReplacement = lookupEntityReplacement(i8);
            this.entityValue = cArrLookupEntityReplacement;
            return cArrLookupEntityReplacement;
        } catch (EOFException e) {
            throw new XMLStreamException(EOF_MSG, getLocation(), e);
        }
    }

    protected char[] lookupEntityReplacement(int i) throws XMLStreamException {
        if (!this.allStringsInterned) {
            char[] cArr = this.buf;
            int i2 = this.posStart;
            int iFastHash = fastHash(cArr, i2, this.posEnd - i2);
            for (int i3 = this.entityEnd - 1; i3 >= 0; i3--) {
                if (iFastHash == this.entityNameHash[i3]) {
                    char[] cArr2 = this.entityNameBuf[i3];
                    if (i == cArr2.length) {
                        for (int i4 = 0; i4 < i; i4++) {
                            if (this.buf[this.posStart + i4] != cArr2[i4]) {
                                break;
                            }
                        }
                        if (this.tokenize) {
                            this.text = this.entityReplacement[i3];
                        }
                        this.entityRefName = this.entityName[i3];
                        return this.entityReplacementBuf[i3];
                    }
                    continue;
                }
            }
            return null;
        }
        char[] cArr3 = this.buf;
        int i5 = this.posStart;
        this.entityRefName = newString(cArr3, i5, this.posEnd - i5);
        for (int i6 = this.entityEnd - 1; i6 >= 0; i6--) {
            if (this.entityRefName == this.entityName[i6]) {
                if (this.tokenize) {
                    this.text = this.entityReplacement[i6];
                }
                return this.entityReplacementBuf[i6];
            }
        }
        return null;
    }

    protected void parseComment() throws XMLStreamException {
        try {
            if (more() != '-') {
                throw new XMLStreamException("expected <!-- for COMMENT start", getLocation());
            }
            this.posStart = this.pos;
            int i = this.lineNumber;
            int i2 = this.columnNumber;
            int i3 = -2;
            boolean z = false;
            int i4 = -1;
            int i5 = -2;
            while (true) {
                try {
                    char cMore = more();
                    int i6 = i4 + 1;
                    if (cMore == '-') {
                        if (i3 >= i6) {
                            break;
                        } else {
                            i3 = i4 + 2;
                        }
                    } else if (cMore == '\r') {
                        this.columnNumber = 1;
                        i5 = i4 + 2;
                        if (z) {
                            cMore = '\n';
                        } else {
                            this.buf[this.pos - 1] = '\n';
                            i4 = i6;
                        }
                    } else if (cMore == '\n' && i5 == i6) {
                        if (z) {
                            i4 = i6;
                        } else {
                            this.posEnd = this.pos - 1;
                            i4 = i6;
                            z = true;
                        }
                    }
                    if (z) {
                        char[] cArr = this.buf;
                        int i7 = this.posEnd;
                        cArr[i7] = cMore;
                        this.posEnd = i7 + 1;
                    }
                    i4 = i6;
                } catch (EOFException e) {
                    throw new XMLStreamException(new StringBuffer().append("COMMENT started on line ").append(i).append(" and column ").append(i2).append(" was not closed").toString(), getLocation(), e);
                }
            }
            char cMore2 = more();
            if (cMore2 != '>') {
                throw new XMLStreamException(new StringBuffer().append("in COMMENT after two dashes (--) next character must be '>' not ").append(printable(cMore2)).toString(), getLocation());
            }
            if (z) {
                this.posEnd--;
            } else {
                this.posEnd = this.pos - 3;
            }
        } catch (EOFException e2) {
            throw new XMLStreamException(EOF_MSG, getLocation(), e2);
        }
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getPITarget() {
        if (this.eventType != 3) {
            throwIllegalState(3);
        }
        return this.piTarget;
    }

    @Override // javax.xml.stream.XMLStreamReader
    public String getPIData() {
        if (this.eventType != 3) {
            throwIllegalState(3);
        }
        return this.piData;
    }

    @Override // javax.xml.stream.XMLStreamReader
    public NamespaceContext getNamespaceContext() {
        return new ReadOnlyNamespaceContextBase(this.namespacePrefix, this.namespaceUri, this.namespaceEnd);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0025, code lost:
    
        r6 = true;
        r5 = (r14.pos - r14.posStart) - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002c, code lost:
    
        if (r5 == 0) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002e, code lost:
    
        r14.piTarget = new java.lang.String(r14.buf, r14.posStart, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0039, code lost:
    
        if (r3 == '?') goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003b, code lost:
    
        r3 = skipS(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003f, code lost:
    
        r5 = r14.piTarget.equalsIgnoreCase(javax.xml.XMLConstants.XML_NS_PREFIX);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0046, code lost:
    
        if (r5 == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004d, code lost:
    
        if ((r14.posStart + r14.bufAbsoluteStart) > 2) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0055, code lost:
    
        if (javax.xml.XMLConstants.XML_NS_PREFIX.equals(r14.piTarget) == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0057, code lost:
    
        r14.posStart = r14.pos - 1;
        parseXmlDecl(r3);
        r14.posEnd = r14.pos - 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0070, code lost:
    
        throw new javax.xml.stream.XMLStreamException("XMLDecl must have xml name in lowercase", getLocation());
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x007c, code lost:
    
        throw new javax.xml.stream.XMLStreamException("processing instruction can not have PITarget with reserved name 'xml'", getLocation());
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x007d, code lost:
    
        r14.posStart = r14.pos - 1;
        r0 = -2;
        r10 = false;
        r9 = -1;
        r8 = r3;
        r3 = -2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0089, code lost:
    
        r11 = r9 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x008b, code lost:
    
        if (r8 != '?') goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x008d, code lost:
    
        r0 = r9 + 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0092, code lost:
    
        if (r8 != '>') goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0094, code lost:
    
        if (r11 != r0) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0096, code lost:
    
        if (r10 == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0098, code lost:
    
        r14.posEnd--;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x009e, code lost:
    
        r14.posEnd = r14.pos - 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00a3, code lost:
    
        r6 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00a4, code lost:
    
        r3 = r14.buf;
        r4 = r14.posStart;
        r14.piData = new java.lang.String(r3, r4, r14.posEnd - r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00b2, code lost:
    
        return r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00b7, code lost:
    
        if (r8 != '\r') goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00b9, code lost:
    
        r14.columnNumber = 1;
        r3 = r9 + 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00bd, code lost:
    
        if (r10 != false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00bf, code lost:
    
        r14.buf[r14.pos - 1] = '\n';
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00c7, code lost:
    
        r8 = '\n';
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00c9, code lost:
    
        if (r8 != '\n') goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00cb, code lost:
    
        if (r3 != r11) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00cd, code lost:
    
        if (r10 != false) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00cf, code lost:
    
        r14.posEnd = r14.pos - 1;
        r10 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00d6, code lost:
    
        if (r10 == false) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00d8, code lost:
    
        r9 = r14.buf;
        r12 = r14.posEnd;
        r9[r12] = r8;
        r14.posEnd = r12 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00e2, code lost:
    
        r8 = more();
        r9 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00f3, code lost:
    
        throw new javax.xml.stream.XMLStreamException("processing instruction must have PITarget name", getLocation());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected boolean parsePI() throws javax.xml.stream.XMLStreamException {
        /*
            Method dump skipped, instruction units count: 325
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bea.xml.stream.MXParser.parsePI():boolean");
    }

    protected char requireInput(char c, char[] cArr) throws XMLStreamException {
        for (int i = 0; i < cArr.length; i++) {
            if (c != cArr[i]) {
                throw new XMLStreamException(new StringBuffer("expected ").append(printable(cArr[i])).append(" in ").append(new String(cArr)).append(" and not ").append(printable(c)).toString(), getLocation());
            }
            try {
                c = more();
            } catch (EOFException e) {
                throw new XMLStreamException(EOF_MSG, getLocation(), e);
            }
        }
        return c;
    }

    protected char requireNextS() throws XMLStreamException {
        try {
            char cMore = more();
            if (!isS(cMore)) {
                throw new XMLStreamException(new StringBuffer("white space is required and not ").append(printable(cMore)).toString(), getLocation());
            }
            return skipS(cMore);
        } catch (EOFException e) {
            throw new XMLStreamException(EOF_MSG, getLocation(), e);
        }
    }

    protected char skipS(char c) throws XMLStreamException {
        while (isS(c)) {
            try {
                c = more();
            } catch (EOFException e) {
                throw new XMLStreamException(EOF_MSG, getLocation(), e);
            }
        }
        return c;
    }

    protected void parseXmlDecl(char c) throws XMLStreamException {
        try {
            char cSkipS = skipS(requireInput(skipS(c), VERSION));
            if (cSkipS != '=') {
                throw new XMLStreamException(new StringBuffer("expected equals sign (=) after version and not ").append(printable(cSkipS)).toString(), getLocation());
            }
            char cSkipS2 = skipS(more());
            if (cSkipS2 != '\'' && cSkipS2 != '\"') {
                throw new XMLStreamException(new StringBuffer("expected apostrophe (') or quotation mark (\") after version and not ").append(printable(cSkipS2)).toString(), getLocation());
            }
            int i = this.pos;
            char cMore = more();
            while (cMore != cSkipS2) {
                if ((cMore < 'a' || cMore > 'z') && ((cMore < 'A' || cMore > 'Z') && ((cMore < '0' || cMore > '9') && cMore != '_' && cMore != '.' && cMore != ':' && cMore != '-'))) {
                    throw new XMLStreamException(new StringBuffer().append("<?xml version value expected to be in ([a-zA-Z0-9_.:] | '-') not ").append(printable(cMore)).toString(), getLocation());
                }
                cMore = more();
            }
            parseXmlDeclWithVersion(i, this.pos - 1);
        } catch (EOFException e) {
            throw new XMLStreamException(EOF_MSG, getLocation(), e);
        }
    }

    protected void parseXmlDeclWithVersion(int i, int i2) throws XMLStreamException {
        boolean z;
        char cRequireInput;
        int i3 = i2 - i;
        if (i3 == 3) {
            try {
                char[] cArr = this.buf;
                if (cArr[i] == '1' && cArr[i + 1] == '.' && cArr[i + 2] == '0') {
                    this.xmlVersion = new String(this.buf, i, i3);
                    char cSkipS = skipS(more());
                    if (cSkipS != '?') {
                        cSkipS = skipS(cSkipS);
                        char[] cArr2 = ENCODING;
                        if (cSkipS == cArr2[0]) {
                            char cSkipS2 = skipS(requireInput(cSkipS, cArr2));
                            if (cSkipS2 != '=') {
                                throw new XMLStreamException(new StringBuffer("expected equals sign (=) after encoding and not ").append(printable(cSkipS2)).toString(), getLocation());
                            }
                            char cSkipS3 = skipS(more());
                            if (cSkipS3 != '\'' && cSkipS3 != '\"') {
                                throw new XMLStreamException(new StringBuffer("expected apostrophe (') or quotation mark (\") after encoding and not ").append(printable(cSkipS3)).toString(), getLocation());
                            }
                            int i4 = this.pos;
                            char cMore = more();
                            z = true;
                            if ((cMore < 'a' || cMore > 'z') && (cMore < 'A' || cMore > 'Z')) {
                                throw new XMLStreamException(new StringBuffer("<?xml encoding name expected to start with [A-Za-z] not ").append(printable(cMore)).toString(), getLocation());
                            }
                            char cMore2 = more();
                            while (cMore2 != cSkipS3) {
                                if ((cMore2 < 'a' || cMore2 > 'z') && ((cMore2 < 'A' || cMore2 > 'Z') && ((cMore2 < '0' || cMore2 > '9') && cMore2 != '.' && cMore2 != '_' && cMore2 != '-'))) {
                                    throw new XMLStreamException(new StringBuffer().append("<?xml encoding value expected to be in ([A-Za-z0-9._] | '-') not ").append(printable(cMore2)).toString(), getLocation());
                                }
                                cMore2 = more();
                            }
                            this.charEncodingScheme = newString(this.buf, i4, (this.pos - 1) - i4);
                            cSkipS = skipS(more());
                        } else {
                            z = true;
                        }
                        if (cSkipS != '?') {
                            char cSkipS4 = skipS(requireInput(skipS(cSkipS), STANDALONE));
                            if (cSkipS4 != '=') {
                                throw new XMLStreamException(new StringBuffer().append("expected equals sign (=) after standalone and not ").append(printable(cSkipS4)).toString(), getLocation());
                            }
                            char cSkipS5 = skipS(more());
                            if (cSkipS5 != '\'' && cSkipS5 != '\"') {
                                throw new XMLStreamException(new StringBuffer().append("expected apostrophe (') or quotation mark (\") after encoding and not ").append(printable(cSkipS5)).toString(), getLocation());
                            }
                            char cMore3 = more();
                            if (cMore3 == 'y') {
                                cRequireInput = requireInput(cMore3, YES);
                                this.standalone = z;
                            } else if (cMore3 == 'n') {
                                cRequireInput = requireInput(cMore3, NO);
                                this.standalone = false;
                            } else {
                                throw new XMLStreamException(new StringBuffer().append("expected 'yes' or 'no' after standalone and not ").append(printable(cMore3)).toString(), getLocation());
                            }
                            this.standaloneSet = true;
                            if (cRequireInput != cSkipS5) {
                                throw new XMLStreamException(new StringBuffer().append("expected ").append(cSkipS5).append(" after standalone value not ").append(printable(cRequireInput)).toString(), getLocation());
                            }
                            cSkipS = more();
                        }
                    }
                    char cSkipS6 = skipS(cSkipS);
                    if (cSkipS6 != '?') {
                        throw new XMLStreamException(new StringBuffer().append("expected ?> as last part of <?xml not ").append(printable(cSkipS6)).toString(), getLocation());
                    }
                    char cMore4 = more();
                    if (cMore4 != '>') {
                        throw new XMLStreamException(new StringBuffer().append("expected ?> as last part of <?xml not ").append(printable(cMore4)).toString(), getLocation());
                    }
                    return;
                }
            } catch (EOFException e) {
                throw new XMLStreamException(EOF_MSG, getLocation(), e);
            }
        }
        throw new XMLStreamException(new StringBuffer("only 1.0 is supported as <?xml version not '").append(printable(new String(this.buf, i, i2))).append("'").toString(), getLocation());
    }

    protected void parseDocdecl() throws XMLStreamException {
        char cMore;
        this.posStart = this.pos - 3;
        try {
            if (more() != 'O' || more() != 'C' || more() != 'T' || more() != 'Y' || more() != 'P' || more() != 'E') {
                throw new XMLStreamException("expected <!DOCTYPE", getLocation());
            }
            char cRequireNextS = requireNextS();
            if (!isNameStartChar(cRequireNextS)) {
                throwNotNameStart(cRequireNextS);
            }
            do {
                cMore = more();
            } while (isNameChar(cMore));
            char cSkipS = skipS(cMore);
            if (cSkipS == 'S' || cSkipS == 'P') {
                if (cSkipS == 'S') {
                    if (more() != 'Y' || more() != 'S' || more() != 'T' || more() != 'E' || more() != 'M') {
                        throw new XMLStreamException("expected keyword SYSTEM", getLocation());
                    }
                } else {
                    if (more() != 'U' || more() != 'B' || more() != 'L' || more() != 'I' || more() != 'C') {
                        throw new XMLStreamException("expected keyword PUBLIC", getLocation());
                    }
                    char cRequireNextS2 = requireNextS();
                    if (cRequireNextS2 != '\"' && cRequireNextS2 != '\'') {
                        throw new XMLStreamException(new StringBuffer().append("Public identifier has to be enclosed in quotes, not ").append(printable(cSkipS)).toString(), getLocation());
                    }
                    do {
                        cSkipS = more();
                    } while (cSkipS != cRequireNextS2);
                }
                char cRequireNextS3 = requireNextS();
                if (cRequireNextS3 != '\"' && cRequireNextS3 != '\'') {
                    throw new XMLStreamException(new StringBuffer().append("System identifier has to be enclosed in quotes, not ").append(printable(cSkipS)).toString(), getLocation());
                }
                while (more() != cRequireNextS3) {
                }
                cSkipS = skipS(more());
            }
            if (cSkipS == '[') {
                this.posStart = this.pos;
                int i = 1;
                while (true) {
                    char cMore2 = more();
                    if (cMore2 == '\"' || cMore2 == '\'') {
                        while (more() != cMore2) {
                        }
                    } else if (cMore2 != '>') {
                        if (cMore2 == '[') {
                            i++;
                        } else if (cMore2 == ']') {
                            i--;
                        }
                    } else if (i <= 0) {
                        this.posEnd = this.pos - 2;
                        processDTD();
                        return;
                    }
                }
            } else {
                int i2 = this.pos;
                this.posEnd = i2;
                this.posStart = i2;
                char cSkipS2 = skipS(cSkipS);
                if (cSkipS2 != '>') {
                    throw new XMLStreamException(new StringBuffer().append("Expected closing '>' after internal DTD subset, not '").append(printable(cSkipS2)).append("'").toString(), getLocation());
                }
            }
        } catch (EOFException e) {
            throw new XMLStreamException(EOF_MSG, getLocation(), e);
        }
    }

    protected void processDTD() throws XMLStreamException {
        try {
            char[] cArr = this.buf;
            int i = this.posStart;
            DTD dtd = new DTDParser(new StringReader(new String(cArr, i, this.posEnd - i))).parse();
            this.mDtdIntSubset = dtd;
            Class clsClass$ = class$com$wutka$dtd$DTDEntity;
            if (clsClass$ == null) {
                clsClass$ = class$("com.wutka.dtd.DTDEntity");
                class$com$wutka$dtd$DTDEntity = clsClass$;
            }
            Enumeration enumerationElements = dtd.getItemsByType(clsClass$).elements();
            while (enumerationElements.hasMoreElements()) {
                DTDEntity dTDEntity = (DTDEntity) enumerationElements.nextElement();
                if (!dTDEntity.isParsed()) {
                    defineEntityReplacementText(dTDEntity.getName(), dTDEntity.getValue());
                }
            }
            DTD dtd2 = this.mDtdIntSubset;
            Class clsClass$2 = class$com$wutka$dtd$DTDAttlist;
            if (clsClass$2 == null) {
                clsClass$2 = class$("com.wutka.dtd.DTDAttlist");
                class$com$wutka$dtd$DTDAttlist = clsClass$2;
            }
            Enumeration enumerationElements2 = dtd2.getItemsByType(clsClass$2).elements();
            while (enumerationElements2.hasMoreElements()) {
                DTDAttlist dTDAttlist = (DTDAttlist) enumerationElements2.nextElement();
                for (DTDAttribute dTDAttribute : dTDAttlist.getAttribute()) {
                    if (dTDAttribute.getDefaultValue() != null) {
                        if (this.defaultAttributes == null) {
                            this.defaultAttributes = new HashMap();
                        }
                        this.defaultAttributes.put(dTDAttlist.getName(), dTDAttlist);
                    }
                }
            }
        } catch (IOException e) {
            throw new XMLStreamException(e);
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    protected void parseCDATA() throws XMLStreamException {
        try {
            if (more() != 'C' || more() != 'D' || more() != 'A' || more() != 'T' || more() != 'A' || more() != '[') {
                throw new XMLStreamException("expected <[CDATA[ for CDATA start", getLocation());
            }
            this.posStart = this.pos;
            int i = this.lineNumber;
            int i2 = this.columnNumber;
            int i3 = -2;
            int i4 = -1;
            int i5 = 0;
            boolean z = false;
            while (true) {
                int i6 = i4 + 1;
                try {
                    char cMore = more();
                    if (cMore == ']') {
                        i5++;
                    } else {
                        if (cMore == '>') {
                            if (i5 >= 2) {
                                break;
                            }
                        } else if (cMore == '\r') {
                            this.columnNumber = 1;
                            i3 = i4 + 2;
                            if (z) {
                                i5 = 0;
                                cMore = '\n';
                            } else {
                                this.buf[this.pos - 1] = '\n';
                                i5 = 0;
                                i4 = i6;
                            }
                        } else if (cMore == '\n' && i3 == i6) {
                            this.posEnd = this.pos - 1;
                            i5 = 0;
                            i4 = i6;
                            z = true;
                        }
                        i5 = 0;
                    }
                    if (z) {
                        char[] cArr = this.buf;
                        int i7 = this.posEnd;
                        cArr[i7] = cMore;
                        this.posEnd = i7 + 1;
                    }
                    i4 = i6;
                } catch (EOFException e) {
                    throw new XMLStreamException(new StringBuffer("CDATA section on line ").append(i).append(" and column ").append(i2).append(" was not closed").toString(), getLocation(), e);
                }
            }
            if (z) {
                this.posEnd -= 2;
            } else {
                this.posEnd = this.pos - 3;
            }
        } catch (EOFException e2) {
            throw new XMLStreamException("Unexpected EOF in directive", getLocation(), e2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void fillBuf() throws javax.xml.stream.XMLStreamException, java.io.EOFException {
        /*
            r7 = this;
            java.io.Reader r0 = r7.reader
            if (r0 == 0) goto Laa
            int r0 = r7.bufEnd
            int r1 = r7.bufSoftLimit
            if (r0 <= r1) goto L6a
            int r2 = r7.bufStart
            r3 = 1
            r4 = 0
            if (r2 <= r1) goto L12
            r1 = r3
            goto L13
        L12:
            r1 = r4
        L13:
            if (r1 != 0) goto L20
            char[] r5 = r7.buf
            int r5 = r5.length
            int r5 = r5 / 2
            if (r2 >= r5) goto L21
            r6 = r3
            r3 = r1
            r1 = r6
            goto L22
        L20:
            r3 = r1
        L21:
            r1 = r4
        L22:
            if (r3 == 0) goto L2b
            char[] r1 = r7.buf
            int r0 = r0 - r2
            java.lang.System.arraycopy(r1, r2, r1, r4, r0)
            goto L44
        L2b:
            if (r1 == 0) goto L62
            char[] r1 = r7.buf
            int r3 = r1.length
            int r3 = r3 * 2
            char[] r3 = new char[r3]
            int r0 = r0 - r2
            java.lang.System.arraycopy(r1, r2, r3, r4, r0)
            r7.buf = r3
            int r0 = r7.bufLoadFactor
            if (r0 <= 0) goto L44
            int r1 = r3.length
            int r0 = r0 * r1
            int r0 = r0 / 100
            r7.bufSoftLimit = r0
        L44:
            int r0 = r7.bufEnd
            int r1 = r7.bufStart
            int r0 = r0 - r1
            r7.bufEnd = r0
            int r0 = r7.pos
            int r0 = r0 - r1
            r7.pos = r0
            int r0 = r7.posStart
            int r0 = r0 - r1
            r7.posStart = r0
            int r0 = r7.posEnd
            int r0 = r0 - r1
            r7.posEnd = r0
            int r0 = r7.bufAbsoluteStart
            int r0 = r0 + r1
            r7.bufAbsoluteStart = r0
            r7.bufStart = r4
            goto L6a
        L62:
            javax.xml.stream.XMLStreamException r0 = new javax.xml.stream.XMLStreamException
            java.lang.String r1 = "internal error in fillBuffer()"
            r0.<init>(r1)
            throw r0
        L6a:
            char[] r0 = r7.buf
            int r1 = r0.length
            int r2 = r7.bufEnd
            int r1 = r1 - r2
            r3 = 8192(0x2000, float:1.148E-41)
            if (r1 <= r3) goto L75
            r1 = r3
        L75:
            java.io.Reader r3 = r7.reader     // Catch: java.io.IOException -> La3
            int r0 = r3.read(r0, r2, r1)     // Catch: java.io.IOException -> La3
            if (r0 <= 0) goto L83
            int r1 = r7.bufEnd
            int r1 = r1 + r0
            r7.bufEnd = r1
            return
        L83:
            r1 = -1
            if (r0 != r1) goto L8e
            java.io.EOFException r0 = new java.io.EOFException
            java.lang.String r1 = "no more data available"
            r0.<init>(r1)
            throw r0
        L8e:
            javax.xml.stream.XMLStreamException r1 = new javax.xml.stream.XMLStreamException
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            java.lang.String r3 = "error reading input, returned "
            r2.<init>(r3)
            java.lang.StringBuffer r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        La3:
            r0 = move-exception
            javax.xml.stream.XMLStreamException r1 = new javax.xml.stream.XMLStreamException
            r1.<init>(r0)
            throw r1
        Laa:
            javax.xml.stream.XMLStreamException r0 = new javax.xml.stream.XMLStreamException
            java.lang.String r1 = "reader must be set before parsing is started"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bea.xml.stream.MXParser.fillBuf():void");
    }

    protected char more() throws XMLStreamException, EOFException {
        if (this.pos >= this.bufEnd) {
            fillBuf();
        }
        char[] cArr = this.buf;
        int i = this.pos;
        this.pos = i + 1;
        char c = cArr[i];
        if (c == '\n') {
            this.lineNumber++;
            this.columnNumber = 1;
            return c;
        }
        this.columnNumber++;
        return c;
    }

    protected String printable(char c) {
        if (c == '\n') {
            return "\\n";
        }
        if (c == '\r') {
            return "\\r";
        }
        if (c == '\t') {
            return "\\t";
        }
        if (c == '\'') {
            return "\\'";
        }
        if (c > 127 || c < ' ') {
            return new StringBuffer("\\u").append(Integer.toHexString(c)).toString();
        }
        return new StringBuffer("").append(c).toString();
    }

    protected String printable(String str) {
        if (str == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            stringBuffer.append(printable(str.charAt(i)));
        }
        return stringBuffer.toString();
    }

    protected void ensurePC(int i) {
        char[] cArr = new char[i > 8192 ? i * 2 : 16384];
        System.arraycopy(this.pc, 0, cArr, 0, this.pcEnd);
        this.pc = cArr;
    }

    protected void joinPC() {
        int i = this.posEnd - this.posStart;
        int i2 = this.pcEnd + i + 1;
        if (i2 >= this.pc.length) {
            ensurePC(i2);
        }
        System.arraycopy(this.buf, this.posStart, this.pc, this.pcEnd, i);
        this.pcEnd += i;
        this.usePC = true;
    }

    public void setConfigurationContext(ConfigurationContextBase configurationContextBase) {
        this.configurationContext = configurationContextBase;
        Boolean.TRUE.equals(configurationContextBase.getProperty(XMLInputFactory.IS_COALESCING));
        this.reportCdataEvent = Boolean.TRUE.equals(configurationContextBase.getProperty("http://java.sun.com/xml/stream/properties/report-cdata-event"));
    }

    public ConfigurationContextBase getConfigurationContext() {
        return this.configurationContext;
    }

    @Override // javax.xml.stream.XMLStreamReader
    public Object getProperty(String str) {
        ArrayList arrayList = null;
        if (str.equals("javax.xml.stream.entities")) {
            DTD dtd = this.mDtdIntSubset;
            if (dtd != null) {
                Class clsClass$ = class$com$wutka$dtd$DTDEntity;
                if (clsClass$ == null) {
                    clsClass$ = class$("com.wutka.dtd.DTDEntity");
                    class$com$wutka$dtd$DTDEntity = clsClass$;
                }
                Vector itemsByType = dtd.getItemsByType(clsClass$);
                Enumeration enumerationElements = itemsByType.elements();
                arrayList = new ArrayList(itemsByType.size());
                while (enumerationElements.hasMoreElements()) {
                    EntityDeclaration entityDeclarationCreateEntityDeclaration = DTDEvent.createEntityDeclaration((DTDEntity) enumerationElements.nextElement());
                    if (entityDeclarationCreateEntityDeclaration != null) {
                        arrayList.add(entityDeclarationCreateEntityDeclaration);
                    }
                }
            }
            return arrayList;
        }
        if (str.equals("javax.xml.stream.notations")) {
            DTD dtd2 = this.mDtdIntSubset;
            if (dtd2 != null) {
                Class clsClass$2 = class$com$wutka$dtd$DTDNotation;
                if (clsClass$2 == null) {
                    clsClass$2 = class$("com.wutka.dtd.DTDNotation");
                    class$com$wutka$dtd$DTDNotation = clsClass$2;
                }
                Vector itemsByType2 = dtd2.getItemsByType(clsClass$2);
                Enumeration enumerationElements2 = itemsByType2.elements();
                arrayList = new ArrayList(itemsByType2.size());
                while (enumerationElements2.hasMoreElements()) {
                    NotationDeclaration notationDeclarationCreateNotationDeclaration = DTDEvent.createNotationDeclaration((DTDNotation) enumerationElements2.nextElement());
                    if (notationDeclarationCreateNotationDeclaration != null) {
                        arrayList.add(notationDeclarationCreateNotationDeclaration);
                    }
                }
            }
            return arrayList;
        }
        return this.configurationContext.getProperty(str);
    }

    private String throwIllegalState(int i) throws IllegalStateException {
        throw new IllegalStateException(new StringBuffer("Current state (").append(eventTypeDesc(this.eventType)).append(") not ").append(eventTypeDesc(i)).toString());
    }

    private String throwIllegalState(int[] iArr) throws IllegalStateException {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(eventTypeDesc(iArr[0]));
        int length = iArr.length - 1;
        for (int i = 0; i < length; i++) {
            stringBuffer.append(", ");
            stringBuffer.append(eventTypeDesc(iArr[i]));
        }
        stringBuffer.append(" or ");
        stringBuffer.append(eventTypeDesc(iArr[length]));
        throw new IllegalStateException(new StringBuffer("Current state (").append(eventTypeDesc(this.eventType)).append(") not ").append(stringBuffer.toString()).toString());
    }

    private void throwNotNameStart(char c) throws XMLStreamException {
        throw new XMLStreamException(new StringBuffer("expected name start character and not ").append(printable(c)).toString(), getLocation());
    }
}
