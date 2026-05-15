package com.wutka.dtd;

/* JADX INFO: loaded from: classes4.dex */
class TokenType {
    public String name;
    public int value;

    public TokenType(int i, String str) {
        this.value = i;
        this.name = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof TokenType) && ((TokenType) obj).value == this.value;
    }

    public int hashCode() {
        return this.name.hashCode();
    }
}
