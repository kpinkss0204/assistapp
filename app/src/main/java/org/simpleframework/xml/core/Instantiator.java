package org.simpleframework.xml.core;

import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
interface Instantiator {
    List<Creator> getCreators();

    Object getInstance() throws Exception;

    Object getInstance(Criteria criteria) throws Exception;

    Parameter getParameter(String str);

    List<Parameter> getParameters();

    boolean isDefault();
}
