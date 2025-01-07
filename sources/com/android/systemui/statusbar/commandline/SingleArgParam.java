package com.android.systemui.statusbar.commandline;

import java.util.ArrayList;
import java.util.Iterator;
import kotlin.reflect.KProperty;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SingleArgParam extends UnaryParamBase {
    public final String description;
    public final String longName;
    public final String shortName;

    public SingleArgParam(String str, String str2, String str3, ValueParser valueParser) {
        super(new MultipleArgParam(str, str2, str3, valueParser));
        this.longName = str;
        this.shortName = str2;
        this.description = str3;
    }

    @Override // com.android.systemui.statusbar.commandline.Describable
    public final String getDescription() {
        return this.description;
    }

    @Override // com.android.systemui.statusbar.commandline.Describable
    public final String getLongName() {
        return this.longName;
    }

    @Override // com.android.systemui.statusbar.commandline.Describable
    public final String getShortName() {
        return this.shortName;
    }

    public final Object getValue(Object obj, KProperty kProperty) {
        if (this.handled) {
            return ((ArrayList) this.wrapped.inner).get(0);
        }
        throw new IllegalStateException("Attempt to read property before parse() has executed");
    }

    @Override // com.android.systemui.statusbar.commandline.Param
    public final void parseArgsFromIter(Iterator it) {
        this.wrapped.parseArgsFromIter(it);
        this.handled = true;
    }
}
