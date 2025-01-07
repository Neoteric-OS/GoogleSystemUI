package com.android.wm.shell.sysui;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.InsetsSource;
import android.view.InsetsState;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.systemui.deviceentry.data.repository.FaceWakeUpTriggersConfigImpl$$ExternalSyntheticOutline0;
import com.android.wm.shell.common.DisplayInsetsController;
import com.android.wm.shell.common.ExternalInterfaceBinder;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.sysui.ShellCommandHandler;
import com.android.wm.shell.sysui.ShellController;
import com.google.android.systemui.input.TouchContextService;
import com.google.input.ImeState;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ShellController {
    public final Context mContext;
    public final DisplayInsetsController mDisplayInsetsController;
    public Configuration mLastConfiguration;
    public final ShellExecutor mMainExecutor;
    public final ShellCommandHandler mShellCommandHandler;
    public final ShellInit mShellInit;
    public final ShellInterfaceImpl mImpl = new ShellInterfaceImpl();
    public final CopyOnWriteArrayList mConfigChangeListeners = new CopyOnWriteArrayList();
    public final CopyOnWriteArrayList mKeyguardChangeListeners = new CopyOnWriteArrayList();
    public final CopyOnWriteArrayList mUserChangeListeners = new CopyOnWriteArrayList();
    public final ConcurrentHashMap mDisplayImeChangeListeners = new ConcurrentHashMap();
    public final ArrayMap mExternalInterfaceSuppliers = new ArrayMap();
    public final ArrayMap mExternalInterfaces = new ArrayMap();
    public final AnonymousClass1 mInsetsChangeListener = new DisplayInsetsController.OnInsetsChangedListener() { // from class: com.android.wm.shell.sysui.ShellController.1
        public InsetsState mInsetsState = new InsetsState();

        @Override // com.android.wm.shell.common.DisplayInsetsController.OnInsetsChangedListener
        public final void insetsChanged(InsetsState insetsState) {
            InsetsState insetsState2 = this.mInsetsState;
            if (insetsState2 == insetsState) {
                return;
            }
            int i = InsetsSource.ID_IME;
            InsetsSource peekSource = insetsState2.peekSource(i);
            boolean z = false;
            boolean z2 = peekSource != null && peekSource.isVisible();
            Rect frame = z2 ? peekSource.getFrame() : null;
            InsetsSource peekSource2 = insetsState.peekSource(i);
            if (peekSource2 != null && peekSource2.isVisible()) {
                z = true;
            }
            Rect frame2 = z ? peekSource2.getFrame() : null;
            ShellController shellController = ShellController.this;
            if (z2 != z) {
                shellController.onImeVisibilityChanged(z);
            }
            if (frame2 != null && !frame2.equals(frame)) {
                shellController.onImeBoundsChanged(frame2);
            }
            this.mInsetsState = insetsState;
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ShellInterfaceImpl implements ShellInterface {
        public ShellInterfaceImpl() {
        }

        @Override // com.android.wm.shell.sysui.ShellInterface
        public final void addDisplayImeChangeListener(TouchContextService.AnonymousClass4 anonymousClass4, Executor executor) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_SYSUI_EVENTS_enabled[1]) {
                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_SYSUI_EVENTS, 7916821057362804003L, 0, null);
            }
            ShellController.this.mDisplayImeChangeListeners.put(anonymousClass4, executor);
        }

        @Override // com.android.wm.shell.sysui.ShellInterface
        public final void createExternalInterfaces(Bundle bundle) {
            try {
                ShellController.this.mMainExecutor.executeBlocking(new ShellController$ShellInterfaceImpl$$ExternalSyntheticLambda2(this, bundle, 3));
            } catch (InterruptedException e) {
                throw new RuntimeException("Failed to get Shell command in 2s", e);
            }
        }

        @Override // com.android.wm.shell.sysui.ShellInterface
        public final void dump(PrintWriter printWriter) {
            try {
                ShellController.this.mMainExecutor.executeBlocking(new ShellController$ShellInterfaceImpl$$ExternalSyntheticLambda2(this, printWriter, 0));
            } catch (InterruptedException e) {
                throw new RuntimeException("Failed to dump the Shell in 2s", e);
            }
        }

        @Override // com.android.wm.shell.sysui.ShellInterface
        public final boolean handleCommand(final PrintWriter printWriter, final String[] strArr) {
            try {
                final boolean[] zArr = new boolean[1];
                ShellController.this.mMainExecutor.executeBlocking(new Runnable() { // from class: com.android.wm.shell.sysui.ShellController$ShellInterfaceImpl$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        boolean z;
                        ShellController.ShellInterfaceImpl shellInterfaceImpl = ShellController.ShellInterfaceImpl.this;
                        boolean[] zArr2 = zArr;
                        String[] strArr2 = strArr;
                        PrintWriter printWriter2 = printWriter;
                        ShellCommandHandler shellCommandHandler = ShellController.this.mShellCommandHandler;
                        shellCommandHandler.getClass();
                        if (strArr2.length >= 2) {
                            z = true;
                            String str = strArr2[1];
                            if (str.toLowerCase().equals("help")) {
                                printWriter2.println("Window Manager Shell commands:");
                                for (String str2 : shellCommandHandler.mCommands.keySet()) {
                                    FaceWakeUpTriggersConfigImpl$$ExternalSyntheticOutline0.m(printWriter2, "  ", str2);
                                    ((ShellCommandHandler.ShellCommandActionHandler) shellCommandHandler.mCommands.get(str2)).printShellCommandHelp(printWriter2, "    ");
                                }
                                printWriter2.println("  help");
                                printWriter2.println("      Print this help text.");
                                printWriter2.println("  <no arguments provided>");
                                printWriter2.println("    Dump all Window Manager Shell internal state");
                            } else if (shellCommandHandler.mCommands.containsKey(str)) {
                                ((ShellCommandHandler.ShellCommandActionHandler) shellCommandHandler.mCommands.get(strArr2[1])).onShellCommand(printWriter2, (String[]) Arrays.copyOfRange(strArr2, 2, strArr2.length));
                            }
                            zArr2[0] = z;
                        }
                        z = false;
                        zArr2[0] = z;
                    }
                });
                return zArr[0];
            } catch (InterruptedException e) {
                throw new RuntimeException("Failed to handle Shell command in 2s", e);
            }
        }

        @Override // com.android.wm.shell.sysui.ShellInterface
        public final void onConfigurationChanged(Configuration configuration) {
            ((HandlerExecutor) ShellController.this.mMainExecutor).execute(new ShellController$ShellInterfaceImpl$$ExternalSyntheticLambda2(this, configuration, 1));
        }

        @Override // com.android.wm.shell.sysui.ShellInterface
        public final void onInit() {
            ShellController shellController = ShellController.this;
            ((HandlerExecutor) shellController.mMainExecutor).execute(new ShellController$$ExternalSyntheticLambda2(1, shellController));
        }

        @Override // com.android.wm.shell.sysui.ShellInterface
        public final void onKeyguardDismissAnimationFinished() {
            ((HandlerExecutor) ShellController.this.mMainExecutor).execute(new ShellController$$ExternalSyntheticLambda2(2, this));
        }

        @Override // com.android.wm.shell.sysui.ShellInterface
        public final void onKeyguardVisibilityChanged(final boolean z, final boolean z2, final boolean z3) {
            ((HandlerExecutor) ShellController.this.mMainExecutor).execute(new Runnable() { // from class: com.android.wm.shell.sysui.ShellController$ShellInterfaceImpl$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    ShellController.ShellInterfaceImpl shellInterfaceImpl = ShellController.ShellInterfaceImpl.this;
                    ShellController.this.onKeyguardVisibilityChanged(z, z2, z3);
                }
            });
        }

        @Override // com.android.wm.shell.sysui.ShellInterface
        public final void onUserChanged(final int i, final Context context) {
            ((HandlerExecutor) ShellController.this.mMainExecutor).execute(new Runnable() { // from class: com.android.wm.shell.sysui.ShellController$ShellInterfaceImpl$$ExternalSyntheticLambda8
                @Override // java.lang.Runnable
                public final void run() {
                    ShellController.ShellInterfaceImpl shellInterfaceImpl = ShellController.ShellInterfaceImpl.this;
                    ShellController.this.onUserChanged(i, context);
                }
            });
        }

        @Override // com.android.wm.shell.sysui.ShellInterface
        public final void onUserProfilesChanged(List list) {
            ((HandlerExecutor) ShellController.this.mMainExecutor).execute(new ShellController$ShellInterfaceImpl$$ExternalSyntheticLambda2(this, list, 2));
        }

        @Override // com.android.wm.shell.sysui.ShellInterface
        public final void removeDisplayImeChangeListener(TouchContextService.AnonymousClass4 anonymousClass4) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_SYSUI_EVENTS_enabled[1]) {
                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_SYSUI_EVENTS, -2668668723248677927L, 0, null);
            }
            ShellController.this.mDisplayImeChangeListeners.remove(anonymousClass4);
        }
    }

    /* JADX WARN: Type inference failed for: r0v7, types: [com.android.wm.shell.sysui.ShellController$1] */
    public ShellController(Context context, ShellInit shellInit, ShellCommandHandler shellCommandHandler, DisplayInsetsController displayInsetsController, ShellExecutor shellExecutor) {
        this.mContext = context;
        this.mShellInit = shellInit;
        this.mShellCommandHandler = shellCommandHandler;
        this.mDisplayInsetsController = displayInsetsController;
        this.mMainExecutor = shellExecutor;
        shellInit.addInitCallback(new ShellController$$ExternalSyntheticLambda2(0, this), this);
    }

    public final void addConfigurationChangeListener(ConfigurationChangeListener configurationChangeListener) {
        this.mConfigChangeListeners.remove(configurationChangeListener);
        this.mConfigChangeListeners.add(configurationChangeListener);
    }

    public final void addExternalInterface(String str, Supplier supplier, Object obj) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_INIT_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_INIT, 1169202424525373405L, 0, obj.getClass().getSimpleName(), str);
        }
        if (this.mExternalInterfaceSuppliers.containsKey(str)) {
            throw new IllegalArgumentException("Supplier with same key already exists: ".concat(str));
        }
        this.mExternalInterfaceSuppliers.put(str, supplier);
    }

    public final void addKeyguardChangeListener(KeyguardChangeListener keyguardChangeListener) {
        this.mKeyguardChangeListeners.remove(keyguardChangeListener);
        this.mKeyguardChangeListeners.add(keyguardChangeListener);
    }

    public void createExternalInterfaces(Bundle bundle) {
        for (int i = 0; i < this.mExternalInterfaces.size(); i++) {
            ((ExternalInterfaceBinder) this.mExternalInterfaces.valueAt(i)).invalidate();
        }
        this.mExternalInterfaces.clear();
        for (int i2 = 0; i2 < this.mExternalInterfaceSuppliers.size(); i2++) {
            String str = (String) this.mExternalInterfaceSuppliers.keyAt(i2);
            ExternalInterfaceBinder externalInterfaceBinder = (ExternalInterfaceBinder) ((Supplier) this.mExternalInterfaceSuppliers.valueAt(i2)).get();
            this.mExternalInterfaces.put(str, externalInterfaceBinder);
            bundle.putBinder(str, externalInterfaceBinder.asBinder());
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        Configuration configuration2 = this.mLastConfiguration;
        if (configuration2 == null) {
            this.mLastConfiguration = new Configuration(configuration);
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_SYSUI_EVENTS_enabled[1]) {
                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_SYSUI_EVENTS, -3140463631940501752L, 0, String.valueOf(configuration));
                return;
            }
            return;
        }
        int diff = configuration.diff(configuration2);
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SYSUI_EVENTS_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_SYSUI_EVENTS, 5422673531719793357L, 0, String.valueOf(configuration));
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SYSUI_EVENTS_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_SYSUI_EVENTS, -308891106308048671L, 0, String.valueOf(Configuration.configurationDiffToString(diff)));
        }
        boolean z = ((1073741824 & diff) == 0 && (diff & 4096) == 0) ? false : true;
        boolean z2 = ((Integer.MIN_VALUE & diff) == 0 && (diff & 512) == 0) ? false : true;
        if ((diff & 4) == 0) {
            int i = diff & 8192;
        }
        this.mLastConfiguration.updateFrom(configuration);
        Iterator it = this.mConfigChangeListeners.iterator();
        while (it.hasNext()) {
            ConfigurationChangeListener configurationChangeListener = (ConfigurationChangeListener) it.next();
            configurationChangeListener.onConfigurationChanged(configuration);
            if (z) {
                configurationChangeListener.onDensityOrFontScaleChanged$1();
            }
            if (z2) {
                configurationChangeListener.onThemeChanged();
            }
        }
    }

    public void onImeBoundsChanged(final Rect rect) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SYSUI_EVENTS_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_SYSUI_EVENTS, 6711228585917822804L, 0, null);
        }
        this.mDisplayImeChangeListeners.forEach(new BiConsumer() { // from class: com.android.wm.shell.sysui.ShellController$$ExternalSyntheticLambda1
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                final ShellController shellController = ShellController.this;
                final Rect rect2 = rect;
                final TouchContextService.AnonymousClass4 anonymousClass4 = (TouchContextService.AnonymousClass4) obj;
                ((Executor) obj2).execute(new Runnable() { // from class: com.android.wm.shell.sysui.ShellController$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        TouchContextService touchContextService;
                        ShellController shellController2 = ShellController.this;
                        TouchContextService.AnonymousClass4 anonymousClass42 = anonymousClass4;
                        Rect rect3 = rect2;
                        int displayId = shellController2.mContext.getDisplayId();
                        if (rect3 == null) {
                            anonymousClass42.getClass();
                            return;
                        }
                        synchronized (TouchContextService.this.mContextPacket) {
                            touchContextService = TouchContextService.this;
                            ImeState imeState = touchContextService.mContextPacket.imeState;
                            imeState.displayId = displayId;
                            imeState.top = rect3.top;
                            imeState.left = rect3.left;
                            imeState.bottom = rect3.bottom;
                            imeState.right = rect3.right;
                        }
                        touchContextService.updateTouchContext();
                    }
                });
            }
        });
    }

    public void onImeVisibilityChanged(final boolean z) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SYSUI_EVENTS_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_SYSUI_EVENTS, -4536758676284752066L, 3, Boolean.valueOf(z));
        }
        this.mDisplayImeChangeListeners.forEach(new BiConsumer() { // from class: com.android.wm.shell.sysui.ShellController$$ExternalSyntheticLambda0
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                final ShellController shellController = ShellController.this;
                final boolean z2 = z;
                final TouchContextService.AnonymousClass4 anonymousClass4 = (TouchContextService.AnonymousClass4) obj;
                ((Executor) obj2).execute(new Runnable() { // from class: com.android.wm.shell.sysui.ShellController$$ExternalSyntheticLambda5
                    @Override // java.lang.Runnable
                    public final void run() {
                        TouchContextService touchContextService;
                        ShellController shellController2 = ShellController.this;
                        TouchContextService.AnonymousClass4 anonymousClass42 = anonymousClass4;
                        boolean z3 = z2;
                        int displayId = shellController2.mContext.getDisplayId();
                        synchronized (TouchContextService.this.mContextPacket) {
                            try {
                                touchContextService = TouchContextService.this;
                                ImeState imeState = touchContextService.mContextPacket.imeState;
                                imeState.displayId = displayId;
                                imeState.visible = z3;
                                if (!z3) {
                                    imeState.top = 0;
                                    imeState.left = 0;
                                    imeState.bottom = 0;
                                    imeState.right = 0;
                                }
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                        touchContextService.updateTouchContext();
                    }
                });
            }
        });
    }

    public void onKeyguardDismissAnimationFinished() {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SYSUI_EVENTS_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_SYSUI_EVENTS, 5111757128463758136L, 0, null);
        }
        Iterator it = this.mKeyguardChangeListeners.iterator();
        while (it.hasNext()) {
            ((KeyguardChangeListener) it.next()).onKeyguardDismissAnimationFinished();
        }
    }

    public void onKeyguardVisibilityChanged(boolean z, boolean z2, boolean z3) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SYSUI_EVENTS_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_SYSUI_EVENTS, -1112500327174164343L, 63, Boolean.valueOf(z), Boolean.valueOf(z2), Boolean.valueOf(z3));
        }
        Iterator it = this.mKeyguardChangeListeners.iterator();
        while (it.hasNext()) {
            ((KeyguardChangeListener) it.next()).onKeyguardVisibilityChanged(z, z2, z3);
        }
    }

    public void onUserChanged(int i, Context context) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SYSUI_EVENTS_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_SYSUI_EVENTS, 8165881968506254140L, 1, Long.valueOf(i));
        }
        Iterator it = this.mUserChangeListeners.iterator();
        while (it.hasNext()) {
            ((UserChangeListener) it.next()).onUserChanged$1(i);
        }
    }

    public void onUserProfilesChanged(List list) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SYSUI_EVENTS_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_SYSUI_EVENTS, -4504001331358256160L, 0, null);
        }
        Iterator it = this.mUserChangeListeners.iterator();
        while (it.hasNext()) {
            ((UserChangeListener) it.next()).getClass();
        }
    }
}
