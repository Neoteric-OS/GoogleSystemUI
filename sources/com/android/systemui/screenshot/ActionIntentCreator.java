package com.android.systemui.screenshot;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ContentProvider;
import android.content.Intent;
import android.net.Uri;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ActionIntentCreator {
    public static Intent createShare(Uri uri, String str) {
        Uri uriWithoutUserId = ContentProvider.getUriWithoutUserId(uri);
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setDataAndType(uriWithoutUserId, "image/png");
        intent.putExtra("android.intent.extra.STREAM", uriWithoutUserId);
        intent.setClipData(new ClipData(new ClipDescription("content", new String[]{"text/plain"}), new ClipData.Item(uriWithoutUserId)));
        if (str != null) {
            intent.putExtra("android.intent.extra.SUBJECT", str);
        }
        intent.addFlags(1);
        intent.addFlags(2);
        return Intent.createChooser(intent, null).addFlags(32768).addFlags(268435456).addFlags(1);
    }
}
