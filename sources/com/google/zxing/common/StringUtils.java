package com.google.zxing.common;

import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class StringUtils {
    public static final Charset PLATFORM_DEFAULT_ENCODING = Charset.defaultCharset();
    public static final Charset SHIFT_JIS_CHARSET = Charset.forName("SJIS");

    static {
        try {
            Charset.forName("GB2312");
        } catch (UnsupportedCharsetException unused) {
        }
        Charset forName = Charset.forName("EUC_JP");
        Charset charset = SHIFT_JIS_CHARSET;
        Charset charset2 = PLATFORM_DEFAULT_ENCODING;
        if (charset.equals(charset2)) {
            return;
        }
        forName.equals(charset2);
    }
}
