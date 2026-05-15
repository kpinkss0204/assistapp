package org.simpleframework.xml.transform;

import java.io.File;

/* JADX INFO: loaded from: classes3.dex */
class FileTransform implements Transform<File> {
    FileTransform() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.simpleframework.xml.transform.Transform
    public File read(String str) {
        return new File(str);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(File file) {
        return file.getPath();
    }
}
