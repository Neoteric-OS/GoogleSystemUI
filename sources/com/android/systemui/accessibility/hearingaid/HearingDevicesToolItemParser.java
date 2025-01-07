package com.android.systemui.accessibility.hearingaid;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.google.common.collect.CollectPreconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class HearingDevicesToolItemParser {
    static final int MAX_NUM = 3;

    public static ImmutableList parseStringArray(Context context, String[] strArr, String[] strArr2) {
        if (strArr.length == 0) {
            Log.i("HearingDevicesToolItemParser", "Empty hearing device related tool name in array.");
            return ImmutableList.of();
        }
        int i = 0;
        String[] strArr3 = (String[]) Arrays.copyOfRange(strArr, 0, Math.min(strArr.length, 3));
        String[] strArr4 = (String[]) Arrays.copyOfRange(strArr2, 0, Math.min(strArr2.length, 3));
        PackageManager packageManager = context.getPackageManager();
        ImmutableList.Itr itr = ImmutableList.EMPTY_ITR;
        CollectPreconditions.checkNonnegative(4, "initialCapacity");
        Object[] objArr = new Object[4];
        PackageManager packageManager2 = context.getPackageManager();
        ArrayList arrayList = new ArrayList();
        for (String str : strArr3) {
            if (str.split("/").length == 2) {
                ComponentName unflattenFromString = ComponentName.unflattenFromString(str);
                try {
                    arrayList.add(packageManager2.getActivityInfo(unflattenFromString, 0));
                } catch (PackageManager.NameNotFoundException unused) {
                    Log.e("HearingDevicesToolItemParser", "Unable to find hearing device related tool: " + unflattenFromString.flattenToString());
                }
            } else {
                Log.e("HearingDevicesToolItemParser", "Malformed hearing device related tool name item in array: ".concat(str));
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (String str2 : strArr4) {
            try {
                arrayList2.add(context.getDrawable(context.getResources().getIdentifier(str2, "drawable", context.getPackageName())));
            } catch (Resources.NotFoundException unused2) {
                Log.e("HearingDevicesToolItemParser", "Resource does not exist: " + str2);
            }
        }
        int size = arrayList.size();
        boolean z = size == arrayList2.size();
        int i2 = 0;
        while (i < size) {
            ToolItem toolItem = new ToolItem(((ActivityInfo) arrayList.get(i)).loadLabel(packageManager).toString(), z ? (Drawable) arrayList2.get(i) : ((ActivityInfo) arrayList.get(i)).loadIcon(packageManager), new Intent("android.intent.action.MAIN").setComponent(((ActivityInfo) arrayList.get(i)).getComponentName()));
            int i3 = i2 + 1;
            if (objArr.length < i3) {
                objArr = Arrays.copyOf(objArr, ImmutableCollection.Builder.expandedCapacity(objArr.length, i3));
            }
            objArr[i2] = toolItem;
            i++;
            i2 = i3;
        }
        return ImmutableList.asImmutableList(i2, objArr);
    }
}
