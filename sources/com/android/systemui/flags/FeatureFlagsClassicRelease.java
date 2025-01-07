package com.android.systemui.flags;

import android.content.res.Resources;
import android.os.SystemProperties;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FeatureFlagsClassicRelease implements FeatureFlagsClassic {
    public final Map mBooleanCache = new HashMap();
    public final Resources mResources;
    public final ConditionalRestarter mRestarter;
    public final ServerFlagReaderImpl mServerFlagReader;
    public final SystemPropertiesHelper mSystemProperties;

    public FeatureFlagsClassicRelease(Resources resources, SystemPropertiesHelper systemPropertiesHelper, ServerFlagReaderImpl serverFlagReaderImpl, ConditionalRestarter conditionalRestarter) {
        new HashMap();
        new HashMap();
        this.mResources = resources;
        this.mSystemProperties = systemPropertiesHelper;
        this.mServerFlagReader = serverFlagReaderImpl;
    }

    @Override // android.util.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        boolean z;
        boolean z2;
        boolean z3;
        printWriter.println("can override: false");
        FlagsFactory flagsFactory = FlagsFactory.INSTANCE;
        Map map = FlagsFactory.flagMap;
        map.containsKey(Flags.NULL_FLAG.name);
        printWriter.println("Booleans: ");
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Flag flag = (Flag) ((Map.Entry) it.next()).getValue();
            if ((flag instanceof BooleanFlag) && ((z = flag instanceof ResourceBooleanFlag)) && ((z2 = flag instanceof SysPropBooleanFlag))) {
                if (!this.mBooleanCache.containsKey(flag.getName())) {
                    if (z2) {
                        SysPropBooleanFlag sysPropBooleanFlag = (SysPropBooleanFlag) flag;
                        SystemPropertiesHelper systemPropertiesHelper = this.mSystemProperties;
                        String str = sysPropBooleanFlag.name;
                        systemPropertiesHelper.getClass();
                        z3 = SystemProperties.getBoolean(str, sysPropBooleanFlag.f28default);
                    } else if (z) {
                        z3 = this.mResources.getBoolean(((ResourceBooleanFlag) flag).resourceId);
                    } else if (flag instanceof BooleanFlag) {
                        z3 = ((BooleanFlag) flag).f26default;
                    }
                    printWriter.println("  " + flag.getName() + ": " + ((HashMap) this.mBooleanCache).getOrDefault(flag.getName(), Boolean.valueOf(z3)));
                }
                z3 = false;
                printWriter.println("  " + flag.getName() + ": " + ((HashMap) this.mBooleanCache).getOrDefault(flag.getName(), Boolean.valueOf(z3)));
            }
        }
        printWriter.println("Strings: ");
        Iterator it2 = map.entrySet().iterator();
        while (it2.hasNext()) {
            boolean z4 = ((Flag) ((Map.Entry) it2.next()).getValue()) instanceof StringFlag;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x001f, code lost:
    
        if (android.provider.DeviceConfig.getBoolean(r4, r0, true) != false) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean isEnabled(com.android.systemui.flags.ReleasedFlag r4) {
        /*
            r3 = this;
            java.lang.String r0 = r4.name
            com.android.systemui.flags.ServerFlagReaderImpl r1 = r3.mServerFlagReader
            java.lang.String r4 = r4.namespace
            r1.getClass()
            boolean r2 = kotlin.text.StringsKt__StringsJVMKt.isBlank(r4)
            if (r2 != 0) goto L22
            boolean r2 = kotlin.text.StringsKt__StringsJVMKt.isBlank(r0)
            if (r2 != 0) goto L22
            com.android.systemui.util.DeviceConfigProxy r1 = r1.deviceConfig
            r1.getClass()
            r1 = 1
            boolean r4 = android.provider.DeviceConfig.getBoolean(r4, r0, r1)
            if (r4 == 0) goto L22
            goto L23
        L22:
            r1 = 0
        L23:
            java.util.Map r4 = r3.mBooleanCache
            boolean r4 = r4.containsKey(r0)
            if (r4 != 0) goto L34
            java.util.Map r4 = r3.mBooleanCache
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            r4.put(r0, r1)
        L34:
            java.util.Map r3 = r3.mBooleanCache
            java.lang.Object r3 = r3.get(r0)
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            boolean r3 = r3.booleanValue()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.flags.FeatureFlagsClassicRelease.isEnabled(com.android.systemui.flags.ReleasedFlag):boolean");
    }

    public final boolean isEnabled(ResourceBooleanFlag resourceBooleanFlag) {
        String str = resourceBooleanFlag.name;
        boolean z = this.mResources.getBoolean(resourceBooleanFlag.resourceId);
        if (!this.mBooleanCache.containsKey(str)) {
            this.mBooleanCache.put(str, Boolean.valueOf(z));
        }
        return ((Boolean) this.mBooleanCache.get(str)).booleanValue();
    }
}
