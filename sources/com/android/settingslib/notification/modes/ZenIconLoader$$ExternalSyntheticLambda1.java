package com.android.settingslib.notification.modes;

import android.graphics.drawable.Drawable;
import com.android.settingslib.notification.modes.ZenIcon;
import com.google.common.base.Function;
import com.google.common.base.Strings;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class ZenIconLoader$$ExternalSyntheticLambda1 implements Function {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ ZenIcon.Key f$1;

    public /* synthetic */ ZenIconLoader$$ExternalSyntheticLambda1(Object obj, ZenIcon.Key key, int i) {
        this.$r8$classId = i;
        this.f$0 = obj;
        this.f$1 = key;
    }

    @Override // com.google.common.base.Function
    public final Object apply(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                Drawable drawable = (Drawable) obj;
                if (drawable != null) {
                    return new ZenIcon(this.f$1, drawable);
                }
                throw new NullPointerException(Strings.lenientFormat("Couldn't load DEFAULT icon for mode %s!", (ZenMode) this.f$0));
            default:
                ZenIconLoader zenIconLoader = (ZenIconLoader) this.f$0;
                ZenIcon.Key key = this.f$1;
                Drawable drawable2 = (Drawable) obj;
                synchronized (zenIconLoader.mCache) {
                    zenIconLoader.mCache.put(key, drawable2 != null ? drawable2 : ZenIconLoader.MISSING);
                }
                return drawable2;
        }
    }
}
