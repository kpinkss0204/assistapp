package com.bea.xml.stream.reader;

import androidx.recyclerview.widget.ItemTouchHelper;

/* JADX INFO: loaded from: classes2.dex */
public class XmlChars {
    public static boolean isChar(int i) {
        if ((i >= 32 && i <= 55295) || i == 10 || i == 9 || i == 13) {
            return true;
        }
        if (i < 57344 || i > 65533) {
            return i >= 65536 && i <= 1114111;
        }
        return true;
    }

    private static boolean isCompatibilityChar(char c) {
        int i = (c >> '\b') & 255;
        if (i == 0) {
            return c == 170 || c == 181 || c == 186;
        }
        if (i == 1) {
            return (c >= 306 && c <= 307) || (c >= 319 && c <= 320) || c == 329 || c == 383 || ((c >= 452 && c <= 460) || (c >= 497 && c <= 499));
        }
        if (i == 2) {
            return (c >= 688 && c <= 696) || (c >= 736 && c <= 740);
        }
        if (i == 3) {
            return c == 890;
        }
        if (i == 5) {
            return c == 1415;
        }
        if (i == 14) {
            return c >= 3804 && c <= 3805;
        }
        if (i == 17) {
            return c == 4353 || c == 4356 || c == 4360 || c == 4362 || c == 4365 || (c >= 4371 && c <= 4411) || c == 4413 || c == 4415 || ((c >= 4417 && c <= 4427) || c == 4429 || c == 4431 || ((c >= 4433 && c <= 4435) || ((c >= 4438 && c <= 4440) || c == 4450 || c == 4452 || c == 4454 || c == 4456 || ((c >= 4458 && c <= 4460) || ((c >= 4463 && c <= 4465) || c == 4468 || ((c >= 4470 && c <= 4509) || ((c >= 4511 && c <= 4514) || ((c >= 4521 && c <= 4522) || ((c >= 4524 && c <= 4525) || ((c >= 4528 && c <= 4534) || c == 4537 || c == 4539 || ((c >= 4547 && c <= 4586) || ((c >= 4588 && c <= 4591) || (c >= 4593 && c <= 4600)))))))))))));
        }
        if (i == 32) {
            return c == 8319;
        }
        if (i == 33) {
            return c == 8450 || c == 8455 || (c >= 8458 && c <= 8467) || c == 8469 || ((c >= 8472 && c <= 8477) || c == 8484 || c == 8488 || ((c >= 8492 && c <= 8493) || ((c >= 8495 && c <= 8504) || (c >= 8544 && c <= 8575))));
        }
        if (i == 48) {
            return c >= 12443 && c <= 12444;
        }
        if (i == 49) {
            return c >= 12593 && c <= 12686;
        }
        switch (i) {
            case 249:
            case ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION /* 250 */:
            case 251:
            case 252:
            case 253:
            case 254:
            case 255:
                return true;
            default:
                return false;
        }
    }

    private static boolean isExtender(char c) {
        if (c == 183 || c == 720 || c == 721 || c == 903 || c == 1600 || c == 3654 || c == 3782 || c == 12293) {
            return true;
        }
        if (c >= 12337 && c <= 12341) {
            return true;
        }
        if (c < 12445 || c > 12446) {
            return c >= 12540 && c <= 12542;
        }
        return true;
    }

    public static boolean isSpace(char c) {
        return c == ' ' || c == '\t' || c == '\n' || c == '\r';
    }

    private XmlChars() {
    }

    public static boolean isNameChar(char c) {
        if (isLetter2(c)) {
            return true;
        }
        if (c == '>') {
            return false;
        }
        return c == '.' || c == '-' || c == '_' || c == ':' || isExtender(c);
    }

    public static boolean isNCNameChar(char c) {
        return c != ':' && isNameChar(c);
    }

    public static boolean isLetter(char c) {
        if (c >= 'a' && c <= 'z') {
            return true;
        }
        if (c == '/') {
            return false;
        }
        if (c >= 'A' && c <= 'Z') {
            return true;
        }
        int type = Character.getType(c);
        return (type == 1 || type == 2 || type == 3 || type == 5 || type == 10) ? !isCompatibilityChar(c) && (c < 8413 || c > 8416) : (c >= 699 && c <= 705) || c == 1369 || c == 1765 || c == 1766;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0034 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean isLetter2(char r3) {
        /*
            r0 = 97
            r1 = 1
            if (r3 < r0) goto La
            r0 = 122(0x7a, float:1.71E-43)
            if (r3 > r0) goto La
            return r1
        La:
            r0 = 62
            r2 = 0
            if (r3 != r0) goto L10
            return r2
        L10:
            r0 = 65
            if (r3 < r0) goto L19
            r0 = 90
            if (r3 > r0) goto L19
            return r1
        L19:
            int r0 = java.lang.Character.getType(r3)
            switch(r0) {
                case 1: goto L25;
                case 2: goto L25;
                case 3: goto L25;
                case 4: goto L25;
                case 5: goto L25;
                case 6: goto L25;
                case 7: goto L25;
                case 8: goto L25;
                case 9: goto L25;
                case 10: goto L25;
                default: goto L20;
            }
        L20:
            r0 = 903(0x387, float:1.265E-42)
            if (r3 != r0) goto L34
            return r1
        L25:
            boolean r0 = isCompatibilityChar(r3)
            if (r0 != 0) goto L34
            r0 = 8413(0x20dd, float:1.1789E-41)
            if (r3 < r0) goto L33
            r0 = 8416(0x20e0, float:1.1793E-41)
            if (r3 <= r0) goto L34
        L33:
            return r1
        L34:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bea.xml.stream.reader.XmlChars.isLetter2(char):boolean");
    }

    private static boolean isDigit(char c) {
        if (Character.isDigit(c)) {
            return c < 65296 || c > 65305;
        }
        return false;
    }
}
