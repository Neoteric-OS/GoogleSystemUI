package com.android.systemui;

import com.android.systemui.statusbar.commandline.OptionalSubCommand;
import com.android.systemui.statusbar.commandline.ParseableCommand;
import com.android.systemui.statusbar.phone.StatusBarContentInsetsProvider;
import java.io.PrintWriter;
import java.util.Map;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class StatusBarInsetsCommand extends ParseableCommand {
    public static final /* synthetic */ KProperty[] $$delegatedProperties;
    public final OptionalSubCommand bottomMargin$delegate;
    public final StatusBarContentInsetsProvider.AnonymousClass1.C02121 callback;

    static {
        PropertyReference1Impl propertyReference1Impl = new PropertyReference1Impl(StatusBarInsetsCommand.class, "bottomMargin", "getBottomMargin()Lcom/android/systemui/BottomMarginCommand;", 0);
        Reflection.factory.getClass();
        $$delegatedProperties = new KProperty[]{propertyReference1Impl};
    }

    public StatusBarInsetsCommand(StatusBarContentInsetsProvider.AnonymousClass1.C02121 c02121) {
        super("status-bar-insets");
        this.callback = c02121;
        this.bottomMargin$delegate = subCommand(new BottomMarginCommand());
    }

    @Override // com.android.systemui.statusbar.commandline.ParseableCommand
    public final void execute(PrintWriter printWriter) {
        StatusBarContentInsetsProvider statusBarContentInsetsProvider = this.callback.this$0;
        statusBarContentInsetsProvider.getClass();
        KProperty kProperty = $$delegatedProperties[0];
        OptionalSubCommand optionalSubCommand = this.bottomMargin$delegate;
        BottomMarginCommand bottomMarginCommand = (BottomMarginCommand) (optionalSubCommand.isPresent ? optionalSubCommand.cmd : null);
        if (bottomMarginCommand != null) {
            Map map = BottomMarginCommand.ROTATION_DEGREES_TO_VALUE_MAPPING;
            KProperty[] kPropertyArr = BottomMarginCommand.$$delegatedProperties;
            Integer num = (Integer) map.get((Integer) bottomMarginCommand.rotationDegrees$delegate.getValue(bottomMarginCommand, kPropertyArr[0]));
            if (num == null) {
                BottomMarginCommand.Companion.getClass();
                printWriter.println("Rotation should be one of " + BottomMarginCommand.ROTATION_DEGREES_OPTIONS);
                return;
            }
            Float f = (Float) bottomMarginCommand.marginBottomDp$delegate.getValue(bottomMarginCommand, kPropertyArr[1]);
            if (f == null) {
                printWriter.println("Margin bottom not set.");
                return;
            }
            float floatValue = f.floatValue();
            statusBarContentInsetsProvider.insetsCache.evictAll();
            statusBarContentInsetsProvider.marginBottomOverrides.put(num, Integer.valueOf((int) (floatValue * statusBarContentInsetsProvider.context.getResources().getDisplayMetrics().density)));
            statusBarContentInsetsProvider.notifyInsetsChanged();
        }
    }
}
