package com.google.protobuf.nano;

import java.nio.charset.Charset;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class InternalNano {
    public static final Object LAZY_INIT_LOCK;
    public static final Charset UTF_8 = Charset.forName("UTF-8");

    static {
        Charset.forName("ISO-8859-1");
        LAZY_INIT_LOCK = new Object();
    }
}
