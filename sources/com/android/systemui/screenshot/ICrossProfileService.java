package com.android.systemui.screenshot;

import android.content.Intent;
import android.os.Bundle;
import android.os.IInterface;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface ICrossProfileService extends IInterface {
    void launchIntent(Intent intent, Bundle bundle);
}
