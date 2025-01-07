package com.android.systemui.recordissue;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.traceur.PresetTraceConfigs;
import com.android.traceur.TraceConfig;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CustomTraceSettingsDialogDelegate implements SystemUIDialog.Delegate {
    public final TraceConfig.Builder builder;
    public final CustomTraceState customTraceState;
    public final SystemUIDialog.Factory factory;
    public final RecordIssueDialogDelegate$beforeCreate$2 onSave;
    public final Set tagTitles;

    public CustomTraceSettingsDialogDelegate(SystemUIDialog.Factory factory, CustomTraceState customTraceState, Set set, RecordIssueDialogDelegate$beforeCreate$2 recordIssueDialogDelegate$beforeCreate$2) {
        this.factory = factory;
        this.customTraceState = customTraceState;
        this.tagTitles = set;
        this.onSave = recordIssueDialogDelegate$beforeCreate$2;
        TraceConfig traceConfig = customTraceState.getTraceConfig();
        int i = traceConfig.bufferSizeKb;
        boolean z = traceConfig.winscope;
        boolean z2 = traceConfig.apps;
        boolean z3 = traceConfig.longTrace;
        boolean z4 = traceConfig.attachToBugreport;
        int i2 = traceConfig.maxLongTraceSizeMb;
        int i3 = traceConfig.maxLongTraceDurationMinutes;
        Set set2 = traceConfig.tags;
        TraceConfig.Builder builder = new TraceConfig.Builder();
        builder.bufferSizeKb = i;
        builder.winscope = z;
        builder.apps = z2;
        builder.longTrace = z3;
        builder.attachToBugreport = z4;
        builder.maxLongTraceSizeMb = i2;
        builder.maxLongTraceDurationMinutes = i3;
        builder.tags = set2;
        this.builder = builder;
    }

    @Override // com.android.systemui.statusbar.phone.DialogDelegate
    public final void beforeCreate(Dialog dialog) {
        SystemUIDialog systemUIDialog = (SystemUIDialog) dialog;
        systemUIDialog.setTitle(R.string.custom_trace_settings_dialog_title);
        systemUIDialog.setView(LayoutInflater.from(systemUIDialog.getContext()).inflate(R.layout.custom_trace_settings_dialog, (ViewGroup) null));
        systemUIDialog.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() { // from class: com.android.systemui.recordissue.CustomTraceSettingsDialogDelegate$beforeCreate$1$1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                CustomTraceSettingsDialogDelegate.this.onSave.run();
                CustomTraceSettingsDialogDelegate customTraceSettingsDialogDelegate = CustomTraceSettingsDialogDelegate.this;
                CustomTraceState customTraceState = customTraceSettingsDialogDelegate.customTraceState;
                TraceConfig.Builder builder = customTraceSettingsDialogDelegate.builder;
                builder.getClass();
                int i2 = builder.bufferSizeKb;
                boolean z = builder.winscope;
                boolean z2 = builder.apps;
                boolean z3 = builder.longTrace;
                boolean z4 = builder.attachToBugreport;
                int i3 = builder.maxLongTraceSizeMb;
                int i4 = builder.maxLongTraceDurationMinutes;
                Set<String> set = builder.tags;
                customTraceState.getClass();
                customTraceState.prefs.edit().putStringSet("key_tags", set).apply();
                customTraceState.prefs.edit().putInt("key_bufferSizeKb", i2).putBoolean("key_winscope", z).putBoolean("key_apps", z2).putBoolean("key_longTrace", z3).putBoolean("key_attachToBugReport", z4).putInt("key_maxLongTraceSizeMb", i3).putInt("key_maxLongTraceDurationInMinutes", i4).apply();
            }
        });
        systemUIDialog.setNegativeButton(R.string.cancel, CustomTraceSettingsDialogDelegate$beforeCreate$1$2.INSTANCE);
    }

    @Override // com.android.systemui.statusbar.phone.SystemUIDialog.Delegate
    public final SystemUIDialog createDialog() {
        SystemUIDialog.Factory factory = this.factory;
        return factory.create(this, factory.mContext);
    }

    @Override // com.android.systemui.statusbar.phone.DialogDelegate
    public final void onCreate(Dialog dialog, Bundle bundle) {
        String string;
        final int i = 1;
        final int i2 = 2;
        final int i3 = 0;
        SystemUIDialog systemUIDialog = (SystemUIDialog) dialog;
        final TextView textView = (TextView) systemUIDialog.requireViewById(R.id.categories);
        String string2 = textView.getContext().getString(R.string.categories);
        TraceConfig.Builder builder = this.builder;
        Set set = builder.tags;
        if (set == null || set.equals(PresetTraceConfigs.getDefaultConfig().tags)) {
            string = textView.getContext().getString(R.string.notification_alert_title);
        } else {
            Set set2 = this.tagTitles;
            ArrayList arrayList = new ArrayList();
            for (Object obj : set2) {
                Set set3 = builder.tags;
                Intrinsics.checkNotNull(set3);
                if (set3.contains(StringsKt.substringBefore$default((String) obj, ": "))) {
                    arrayList.add(obj);
                }
            }
            string = CollectionsKt.joinToString$default(arrayList, null, null, null, null, 63);
        }
        textView.setText(string2 + "\n" + string);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.recordissue.CustomTraceSettingsDialogDelegate$onCreate$1$1$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                final CustomTraceSettingsDialogDelegate customTraceSettingsDialogDelegate = CustomTraceSettingsDialogDelegate.this;
                final TextView textView2 = textView;
                customTraceSettingsDialogDelegate.getClass();
                Context context = textView2.getContext();
                Function1 function1 = new Function1() { // from class: com.android.systemui.recordissue.CustomTraceSettingsDialogDelegate$showCategorySelector$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        final AlertDialog.Builder builder2 = (AlertDialog.Builder) obj2;
                        Set set4 = CustomTraceSettingsDialogDelegate.this.builder.tags;
                        if (set4 == null) {
                            set4 = PresetTraceConfigs.getDefaultConfig().tags;
                        }
                        Set<String> set5 = CustomTraceSettingsDialogDelegate.this.tagTitles;
                        int mapCapacity = MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(set5, 10));
                        if (mapCapacity < 16) {
                            mapCapacity = 16;
                        }
                        LinkedHashMap linkedHashMap = new LinkedHashMap(mapCapacity);
                        for (String str : set5) {
                            linkedHashMap.put(str, Boolean.valueOf(set4.contains(StringsKt.substringBefore$default(str, ": "))));
                        }
                        int i4 = 0;
                        final String[] strArr = (String[]) linkedHashMap.keySet().toArray(new String[0]);
                        Collection values = linkedHashMap.values();
                        boolean[] zArr = new boolean[values.size()];
                        Iterator it = values.iterator();
                        while (it.hasNext()) {
                            zArr[i4] = ((Boolean) it.next()).booleanValue();
                            i4++;
                        }
                        Set entrySet = linkedHashMap.entrySet();
                        ArrayList arrayList2 = new ArrayList();
                        for (Object obj3 : entrySet) {
                            if (((Boolean) ((Map.Entry) obj3).getValue()).booleanValue()) {
                                arrayList2.add(obj3);
                            }
                        }
                        ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList2, 10));
                        Iterator it2 = arrayList2.iterator();
                        while (it2.hasNext()) {
                            arrayList3.add(StringsKt.substringAfter$default((String) ((Map.Entry) it2.next()).getKey(), ": "));
                        }
                        final Set mutableSet = CollectionsKt.toMutableSet(arrayList3);
                        Intrinsics.checkNotNull(set4);
                        final Set mutableSet2 = CollectionsKt.toMutableSet(set4);
                        builder2.setMultiChoiceItems(strArr, zArr, new DialogInterface.OnMultiChoiceClickListener() { // from class: com.android.systemui.recordissue.CustomTraceSettingsDialogDelegate$showCategorySelector$1.1
                            @Override // android.content.DialogInterface.OnMultiChoiceClickListener
                            public final void onClick(DialogInterface dialogInterface, int i5, boolean z) {
                                String substringBefore$default = StringsKt.substringBefore$default(strArr[i5], ": ");
                                String substringAfter$default = StringsKt.substringAfter$default(strArr[i5], ": ");
                                if (z) {
                                    mutableSet2.add(substringBefore$default);
                                    mutableSet.add(substringAfter$default);
                                } else {
                                    mutableSet2.remove(substringBefore$default);
                                    mutableSet.remove(substringAfter$default);
                                }
                            }
                        });
                        final TextView textView3 = textView2;
                        final CustomTraceSettingsDialogDelegate customTraceSettingsDialogDelegate2 = CustomTraceSettingsDialogDelegate.this;
                        builder2.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() { // from class: com.android.systemui.recordissue.CustomTraceSettingsDialogDelegate$showCategorySelector$1.2
                            @Override // android.content.DialogInterface.OnClickListener
                            public final void onClick(DialogInterface dialogInterface, int i5) {
                                TextView textView4 = textView3;
                                textView4.setText(textView4.getContext().getResources().getString(R.string.categories) + "\n" + CollectionsKt.joinToString$default(mutableSet, null, null, null, null, 63));
                                customTraceSettingsDialogDelegate2.builder.tags = mutableSet2;
                            }
                        });
                        final TextView textView4 = textView2;
                        final CustomTraceSettingsDialogDelegate customTraceSettingsDialogDelegate3 = CustomTraceSettingsDialogDelegate.this;
                        builder2.setNeutralButton(R.string.restore_default, new DialogInterface.OnClickListener() { // from class: com.android.systemui.recordissue.CustomTraceSettingsDialogDelegate$showCategorySelector$1.3
                            @Override // android.content.DialogInterface.OnClickListener
                            public final void onClick(DialogInterface dialogInterface, int i5) {
                                textView4.setText(builder2.getContext().getString(R.string.categories) + "\n" + builder2.getContext().getString(R.string.notification_alert_title));
                                customTraceSettingsDialogDelegate3.builder.tags = null;
                            }
                        });
                        builder2.setNegativeButton(R.string.cancel, CustomTraceSettingsDialogDelegate$beforeCreate$1$2.INSTANCE$1);
                        return Unit.INSTANCE;
                    }
                };
                AlertDialog.Builder builder2 = new AlertDialog.Builder(context, R.style.Theme_SystemUI_Dialog_Alert);
                function1.invoke(builder2);
                AlertDialog create = builder2.create();
                SystemUIDialog.applyFlags(create, true);
                create.show();
            }
        });
        String string3 = systemUIDialog.getContext().getString(R.string.attach_to_bug_report);
        Switch r3 = (Switch) systemUIDialog.requireViewById(R.id.attach_to_bugreport_switch);
        r3.setChecked(builder.attachToBugreport);
        r3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.android.systemui.recordissue.CustomTraceSettingsDialogDelegate$onCreate$1$2$1
            public final /* synthetic */ CustomTraceSettingsDialogDelegate this$0;

            {
                this.this$0 = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                switch (i3) {
                    case 0:
                        this.this$0.builder.attachToBugreport = z;
                        break;
                    case 1:
                        this.this$0.builder.winscope = z;
                        break;
                    default:
                        this.this$0.builder.apps = z;
                        break;
                }
            }
        });
        r3.setContentDescription(string3);
        setupSingleChoiceText((TextView) systemUIDialog.requireViewById(R.id.cpu_buffer_size), R.array.buffer_size_values, R.array.buffer_size_names, builder.bufferSizeKb, R.string.buffer_size, new Consumer(this) { // from class: com.android.systemui.recordissue.CustomTraceSettingsDialogDelegate$onCreate$1$3
            public final /* synthetic */ CustomTraceSettingsDialogDelegate this$0;

            {
                this.this$0 = this;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj2) {
                switch (i3) {
                    case 0:
                        this.this$0.builder.bufferSizeKb = ((Integer) obj2).intValue();
                        break;
                    case 1:
                        this.this$0.builder.maxLongTraceDurationMinutes = ((Integer) obj2).intValue();
                        break;
                    default:
                        this.this$0.builder.maxLongTraceSizeMb = ((Integer) obj2).intValue();
                        break;
                }
            }
        });
        final TextView textView2 = (TextView) systemUIDialog.requireViewById(R.id.long_trace_size);
        setupSingleChoiceText(textView2, R.array.long_trace_size_values, R.array.long_trace_size_names, builder.maxLongTraceSizeMb, R.string.max_long_trace_size, new Consumer(this) { // from class: com.android.systemui.recordissue.CustomTraceSettingsDialogDelegate$onCreate$1$3
            public final /* synthetic */ CustomTraceSettingsDialogDelegate this$0;

            {
                this.this$0 = this;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj2) {
                switch (i2) {
                    case 0:
                        this.this$0.builder.bufferSizeKb = ((Integer) obj2).intValue();
                        break;
                    case 1:
                        this.this$0.builder.maxLongTraceDurationMinutes = ((Integer) obj2).intValue();
                        break;
                    default:
                        this.this$0.builder.maxLongTraceSizeMb = ((Integer) obj2).intValue();
                        break;
                }
            }
        });
        final TextView textView3 = (TextView) systemUIDialog.requireViewById(R.id.long_trace_duration);
        setupSingleChoiceText(textView3, R.array.long_trace_duration_values, R.array.long_trace_duration_names, builder.maxLongTraceDurationMinutes, R.string.max_long_trace_duration, new Consumer(this) { // from class: com.android.systemui.recordissue.CustomTraceSettingsDialogDelegate$onCreate$1$3
            public final /* synthetic */ CustomTraceSettingsDialogDelegate this$0;

            {
                this.this$0 = this;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj2) {
                switch (i) {
                    case 0:
                        this.this$0.builder.bufferSizeKb = ((Integer) obj2).intValue();
                        break;
                    case 1:
                        this.this$0.builder.maxLongTraceDurationMinutes = ((Integer) obj2).intValue();
                        break;
                    default:
                        this.this$0.builder.maxLongTraceSizeMb = ((Integer) obj2).intValue();
                        break;
                }
            }
        });
        String string4 = systemUIDialog.getContext().getString(R.string.long_traces);
        final Switch r6 = (Switch) systemUIDialog.requireViewById(R.id.long_traces_switch);
        r6.setChecked(builder.longTrace);
        final Lazy lazy = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.recordissue.CustomTraceSettingsDialogDelegate$onCreate$1$4$disabledAlpha$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                CustomTraceSettingsDialogDelegate customTraceSettingsDialogDelegate = CustomTraceSettingsDialogDelegate.this;
                Context context = r6.getContext();
                customTraceSettingsDialogDelegate.getClass();
                TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{android.R.attr.disabledAlpha});
                float f = obtainStyledAttributes.getFloat(0, 0.0f);
                obtainStyledAttributes.recycle();
                return Float.valueOf(f);
            }
        });
        float floatValue = r6.isChecked() ? 1.0f : ((Number) lazy.getValue()).floatValue();
        textView3.setAlpha(floatValue);
        textView2.setAlpha(floatValue);
        r6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.android.systemui.recordissue.CustomTraceSettingsDialogDelegate$onCreate$1$4$1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                CustomTraceSettingsDialogDelegate.this.builder.longTrace = z;
                textView3.setEnabled(z);
                textView2.setEnabled(z);
                float floatValue2 = z ? 1.0f : ((Number) lazy.getValue()).floatValue();
                textView3.setAlpha(floatValue2);
                textView2.setAlpha(floatValue2);
            }
        });
        r6.setContentDescription(string4);
        String string5 = systemUIDialog.getContext().getString(R.string.winscope_tracing);
        Switch r32 = (Switch) systemUIDialog.requireViewById(R.id.winscope_switch);
        r32.setChecked(builder.winscope);
        r32.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.android.systemui.recordissue.CustomTraceSettingsDialogDelegate$onCreate$1$2$1
            public final /* synthetic */ CustomTraceSettingsDialogDelegate this$0;

            {
                this.this$0 = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                switch (i) {
                    case 0:
                        this.this$0.builder.attachToBugreport = z;
                        break;
                    case 1:
                        this.this$0.builder.winscope = z;
                        break;
                    default:
                        this.this$0.builder.apps = z;
                        break;
                }
            }
        });
        r32.setContentDescription(string5);
        String string6 = systemUIDialog.getContext().getString(R.string.trace_debuggable_applications);
        Switch r33 = (Switch) systemUIDialog.requireViewById(R.id.trace_debuggable_apps_switch);
        r33.setChecked(builder.apps);
        r33.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(this) { // from class: com.android.systemui.recordissue.CustomTraceSettingsDialogDelegate$onCreate$1$2$1
            public final /* synthetic */ CustomTraceSettingsDialogDelegate this$0;

            {
                this.this$0 = this;
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                switch (i2) {
                    case 0:
                        this.this$0.builder.attachToBugreport = z;
                        break;
                    case 1:
                        this.this$0.builder.winscope = z;
                        break;
                    default:
                        this.this$0.builder.apps = z;
                        break;
                }
            }
        });
        r33.setContentDescription(string6);
        ((TextView) systemUIDialog.requireViewById(R.id.long_traces_switch_label)).setText(string4);
        ((TextView) systemUIDialog.requireViewById(R.id.debuggable_apps_switch_label)).setText(string6);
        ((TextView) systemUIDialog.requireViewById(R.id.winscope_switch_label)).setText(string5);
        ((TextView) systemUIDialog.requireViewById(R.id.attach_to_bugreport_switch_label)).setText(string3);
    }

    public final void setupSingleChoiceText(final TextView textView, int i, int i2, int i3, final int i4, final Consumer consumer) {
        String[] stringArray = textView.getResources().getStringArray(i);
        final ArrayList arrayList = new ArrayList(stringArray.length);
        for (String str : stringArray) {
            arrayList.add(Integer.valueOf(Integer.parseInt(str)));
        }
        final String[] stringArray2 = textView.getResources().getStringArray(i2);
        final int indexOf = arrayList.indexOf(Integer.valueOf(i3));
        textView.setText(textView.getResources().getString(i4) + "\n" + stringArray2[indexOf]);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.recordissue.CustomTraceSettingsDialogDelegate$setupSingleChoiceText$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CustomTraceSettingsDialogDelegate customTraceSettingsDialogDelegate = CustomTraceSettingsDialogDelegate.this;
                Context context = textView.getContext();
                final int i5 = i4;
                final String[] strArr = stringArray2;
                final int i6 = indexOf;
                final TextView textView2 = textView;
                final Consumer consumer2 = consumer;
                final List list = arrayList;
                Function1 function1 = new Function1() { // from class: com.android.systemui.recordissue.CustomTraceSettingsDialogDelegate$setupSingleChoiceText$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        AlertDialog.Builder builder = (AlertDialog.Builder) obj;
                        builder.setTitle(i5);
                        final String[] strArr2 = strArr;
                        int i7 = i6;
                        final TextView textView3 = textView2;
                        final int i8 = i5;
                        final Consumer consumer3 = consumer2;
                        final List list2 = list;
                        builder.setSingleChoiceItems(strArr2, i7, new DialogInterface.OnClickListener() { // from class: com.android.systemui.recordissue.CustomTraceSettingsDialogDelegate.setupSingleChoiceText.1.1.1
                            @Override // android.content.DialogInterface.OnClickListener
                            public final void onClick(DialogInterface dialogInterface, int i9) {
                                TextView textView4 = textView3;
                                textView4.setText(textView4.getResources().getString(i8) + "\n" + strArr2[i9]);
                                consumer3.accept(list2.get(i9));
                                dialogInterface.dismiss();
                            }
                        });
                        return Unit.INSTANCE;
                    }
                };
                customTraceSettingsDialogDelegate.getClass();
                AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.Theme_SystemUI_Dialog_Alert);
                function1.invoke(builder);
                AlertDialog create = builder.create();
                SystemUIDialog.applyFlags(create, true);
                create.show();
            }
        });
    }
}
