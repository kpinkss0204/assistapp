package com.wutka.dtd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes4.dex */
public class DTDChoice extends DTDContainer {
    @Override // com.wutka.dtd.DTDContainer, com.wutka.dtd.DTDItem, com.wutka.dtd.DTDOutput
    public void write(PrintWriter printWriter) throws IOException {
        printWriter.print("(");
        Enumeration enumerationElements = getItemsVec().elements();
        boolean z = true;
        while (enumerationElements.hasMoreElements()) {
            if (!z) {
                printWriter.print(" | ");
            }
            ((DTDItem) enumerationElements.nextElement()).write(printWriter);
            z = false;
        }
        printWriter.print(")");
        this.cardinal.write(printWriter);
    }

    @Override // com.wutka.dtd.DTDContainer, com.wutka.dtd.DTDItem
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DTDChoice) {
            return super.equals(obj);
        }
        return false;
    }
}
