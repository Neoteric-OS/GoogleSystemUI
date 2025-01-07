package com.android.systemui.statusbar.commandline;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Result;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MultipleArgParam implements Param {
    public final String description;
    public final List inner = new ArrayList();
    public final String longName;
    public final String shortName;
    public final ValueParser valueParser;

    public MultipleArgParam(String str, String str2, String str3, ValueParser valueParser) {
        this.longName = str;
        this.shortName = str2;
        this.description = str3;
        this.valueParser = valueParser;
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

    @Override // com.android.systemui.statusbar.commandline.Param
    public final void parseArgsFromIter(Iterator it) {
        if (!it.hasNext()) {
            throw new ArgParseError("no argument provided for " + this.shortName);
        }
        Object mo801parseValueIoAF18A = this.valueParser.mo801parseValueIoAF18A((String) it.next());
        Throwable m1771exceptionOrNullimpl = Result.m1771exceptionOrNullimpl(mo801parseValueIoAF18A);
        if (m1771exceptionOrNullimpl != null) {
            throw m1771exceptionOrNullimpl;
        }
        this.inner.add(mo801parseValueIoAF18A);
    }
}
