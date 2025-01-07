package com.android.systemui.dump;

import android.os.SystemClock;
import android.os.Trace;
import android.util.Log;
import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpsysEntry;
import com.android.systemui.dump.nano.SystemUIProtoDump;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractor;
import com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl;
import com.android.systemui.util.io.Files;
import com.google.protobuf.nano.MessageNano;
import java.io.BufferedOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.Stream;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt__MutableCollectionsJVMKt;
import kotlin.collections.CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1;
import kotlin.collections.EmptyList;
import kotlin.comparisons.ComparisonsKt___ComparisonsJvmKt;
import kotlin.io.CloseableKt;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.FilteringSequence$iterator$1;
import kotlin.sequences.SequenceBuilderIterator;
import kotlin.sequences.SequencesKt;
import kotlin.sequences.SequencesKt__SequenceBuilderKt;
import kotlin.text.StringsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DumpHandler {
    public static final Companion Companion = null;
    public final SystemUIConfigDumpable config;
    public final DumpManager dumpManager;
    public final LogBufferEulogizer logBufferEulogizer;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static final DumpsysEntry access$findBestTargetMatch(Collection collection, final String str) {
            Object next;
            FilteringSequence$iterator$1 filteringSequence$iterator$1 = new FilteringSequence$iterator$1(SequencesKt.filter(new CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1(collection), new Function1() { // from class: com.android.systemui.dump.DumpHandler$Companion$findBestTargetMatch$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(((DumpsysEntry) obj).getName().endsWith(str));
                }
            }));
            if (filteringSequence$iterator$1.hasNext()) {
                next = filteringSequence$iterator$1.next();
                if (filteringSequence$iterator$1.hasNext()) {
                    int length = ((DumpsysEntry) next).getName().length();
                    do {
                        Object next2 = filteringSequence$iterator$1.next();
                        int length2 = ((DumpsysEntry) next2).getName().length();
                        if (length > length2) {
                            next = next2;
                            length = length2;
                        }
                    } while (filteringSequence$iterator$1.hasNext());
                }
            } else {
                next = null;
            }
            return (DumpsysEntry) next;
        }

        public static final boolean access$matchesAny(DumpsysEntry dumpsysEntry, Collection collection) {
            Collection collection2 = collection;
            if ((collection2 instanceof Collection) && collection2.isEmpty()) {
                return false;
            }
            Iterator it = collection2.iterator();
            while (it.hasNext()) {
                if (dumpsysEntry.getName().endsWith((String) it.next())) {
                    return true;
                }
            }
            return false;
        }

        public static void dumpBuffer(DumpsysEntry.LogBufferEntry logBufferEntry, PrintWriter printWriter, int i) {
            Trace.beginSection(StringsKt.take(127, logBufferEntry.name));
            preamble(printWriter, logBufferEntry);
            long currentTimeMillis = System.currentTimeMillis();
            LogBuffer logBuffer = logBufferEntry.buffer;
            synchronized (logBuffer) {
                int size = logBuffer.buffer.getSize();
                for (int max = i > 0 ? Math.max(0, logBuffer.buffer.getSize() - i) : 0; max < size; max++) {
                    ((LogMessageImpl) logBuffer.buffer.get(max)).dump(printWriter);
                }
            }
            footer(printWriter, logBufferEntry, System.currentTimeMillis() - currentTimeMillis);
            Trace.endSection();
        }

        public static void dumpDumpable(DumpsysEntry.DumpableEntry dumpableEntry, PrintWriter printWriter, String[] strArr) {
            Trace.beginSection(StringsKt.take(127, dumpableEntry.name));
            preamble(printWriter, dumpableEntry);
            long currentTimeMillis = System.currentTimeMillis();
            dumpableEntry.dumpable.dump(printWriter, strArr);
            footer(printWriter, dumpableEntry, System.currentTimeMillis() - currentTimeMillis);
            Trace.endSection();
        }

        public static void dumpTableBuffer(DumpsysEntry.TableLogBufferEntry tableLogBufferEntry, PrintWriter printWriter, String[] strArr) {
            Trace.beginSection(StringsKt.take(127, tableLogBufferEntry.name));
            preamble(printWriter, tableLogBufferEntry);
            long currentTimeMillis = System.currentTimeMillis();
            tableLogBufferEntry.table.dump(printWriter, strArr);
            footer(printWriter, tableLogBufferEntry, System.currentTimeMillis() - currentTimeMillis);
            Trace.endSection();
        }

        public static void footer(PrintWriter printWriter, DumpsysEntry dumpsysEntry, long j) {
            if (dumpsysEntry instanceof DumpsysEntry.DumpableEntry) {
                printWriter.println();
                DumpsysEntry.DumpableEntry dumpableEntry = (DumpsysEntry.DumpableEntry) dumpsysEntry;
                printWriter.print(dumpableEntry.priority);
                printWriter.print(" dump took ");
                printWriter.print(j);
                printWriter.print("ms -- ");
                printWriter.print(dumpableEntry.name);
                if (dumpableEntry.priority == DumpPriority.CRITICAL && j > 25) {
                    printWriter.print(" -- warning: individual dump time exceeds 5% of total CRITICAL dump time!");
                }
                printWriter.println();
            }
        }

        public static void preamble(PrintWriter printWriter, DumpsysEntry dumpsysEntry) {
            if (dumpsysEntry instanceof DumpsysEntry.DumpableEntry ? true : dumpsysEntry instanceof DumpsysEntry.TableLogBufferEntry) {
                printWriter.println();
                printWriter.println(dumpsysEntry.getName() + ":");
                printWriter.println("----------------------------------------------------------------------------");
                return;
            }
            if (!(dumpsysEntry instanceof DumpsysEntry.LogBufferEntry)) {
                throw new NoWhenBranchMatchedException();
            }
            printWriter.println();
            printWriter.println();
            printWriter.println("BUFFER " + ((DumpsysEntry.LogBufferEntry) dumpsysEntry).name + ":");
            printWriter.println("----------------------------------------------------------------------------");
        }
    }

    public DumpHandler(DumpManager dumpManager, LogBufferEulogizer logBufferEulogizer, SystemUIConfigDumpable systemUIConfigDumpable) {
        this.dumpManager = dumpManager;
        this.logBufferEulogizer = logBufferEulogizer;
        this.config = systemUIConfigDumpable;
    }

    public static void listOrDumpEntries(Collection collection, PrintWriter printWriter, ParsedArgs parsedArgs) {
        if (parsedArgs.listOnly) {
            listTargetNames(collection, printWriter);
            return;
        }
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            dump((DumpsysEntry) it.next(), printWriter, parsedArgs);
        }
    }

    public static void listTargetNames(Collection collection, PrintWriter printWriter) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            printWriter.println(((DumpsysEntry) it.next()).getName());
        }
    }

    public static ParsedArgs parseArgs(String[] strArr) {
        List mutableList = ArraysKt.toMutableList(strArr);
        ParsedArgs parsedArgs = new ParsedArgs(strArr, mutableList);
        Iterator it = mutableList.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str.startsWith("-")) {
                it.remove();
                switch (str.hashCode()) {
                    case -1616754616:
                        if (!str.equals("--proto")) {
                            throw new ArgParseException("Unknown flag: ".concat(str));
                        }
                        parsedArgs.proto = true;
                        break;
                    case 1492:
                        if (!str.equals("-a")) {
                            throw new ArgParseException("Unknown flag: ".concat(str));
                        }
                        parsedArgs.matchAll = true;
                        break;
                    case 1499:
                        if (!str.equals("-h")) {
                            throw new ArgParseException("Unknown flag: ".concat(str));
                        }
                        parsedArgs.command = "help";
                        break;
                    case 1503:
                        if (!str.equals("-l")) {
                            throw new ArgParseException("Unknown flag: ".concat(str));
                        }
                        parsedArgs.listOnly = true;
                        break;
                    case 1511:
                        if (!str.equals("-t")) {
                            throw new ArgParseException("Unknown flag: ".concat(str));
                        }
                        parsedArgs.tailLength = ((Number) readArgument(it, str, new Function1() { // from class: com.android.systemui.dump.DumpHandler$parseArgs$2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return Integer.valueOf(Integer.parseInt((String) obj));
                            }
                        })).intValue();
                        break;
                    case 42995713:
                        if (!str.equals("--all")) {
                            throw new ArgParseException("Unknown flag: ".concat(str));
                        }
                        parsedArgs.matchAll = true;
                        break;
                    case 1056887741:
                        if (!str.equals("--dump-priority")) {
                            throw new ArgParseException("Unknown flag: ".concat(str));
                        }
                        parsedArgs.dumpPriority = (String) readArgument(it, "--dump-priority", new Function1() { // from class: com.android.systemui.dump.DumpHandler$parseArgs$1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                String str2 = (String) obj;
                                if (ArraysKt.contains(DumpHandlerKt.PRIORITY_OPTIONS, str2)) {
                                    return str2;
                                }
                                throw new IllegalArgumentException();
                            }
                        });
                        break;
                    case 1333069025:
                        if (!str.equals("--help")) {
                            throw new ArgParseException("Unknown flag: ".concat(str));
                        }
                        parsedArgs.command = "help";
                        break;
                    case 1333192254:
                        if (!str.equals("--list")) {
                            throw new ArgParseException("Unknown flag: ".concat(str));
                        }
                        parsedArgs.listOnly = true;
                        break;
                    case 1333422576:
                        if (!str.equals("--tail")) {
                            throw new ArgParseException("Unknown flag: ".concat(str));
                        }
                        parsedArgs.tailLength = ((Number) readArgument(it, str, new Function1() { // from class: com.android.systemui.dump.DumpHandler$parseArgs$2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return Integer.valueOf(Integer.parseInt((String) obj));
                            }
                        })).intValue();
                        break;
                    default:
                        throw new ArgParseException("Unknown flag: ".concat(str));
                }
            }
        }
        if (parsedArgs.command == null && !mutableList.isEmpty() && ArraysKt.contains(DumpHandlerKt.COMMANDS, ((ArrayList) mutableList).get(0))) {
            parsedArgs.command = (String) mutableList.remove(0);
        }
        return parsedArgs;
    }

    public static Object readArgument(Iterator it, String str, Function1 function1) {
        if (!it.hasNext()) {
            throw new ArgParseException("Missing argument for ".concat(str));
        }
        String str2 = (String) it.next();
        try {
            Object invoke = function1.invoke(str2);
            it.remove();
            return invoke;
        } catch (Exception unused) {
            throw new ArgParseException(GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("Invalid argument '", str2, "' for flag ", str));
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    public final void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        List arrayList;
        Object next;
        Object next2;
        Trace.beginSection("DumpManager#dump()");
        long uptimeMillis = SystemClock.uptimeMillis();
        try {
            ParsedArgs parseArgs = parseArgs(strArr);
            printWriter.print("Dump starting: ");
            printWriter.println(DumpHandlerKt.DATE_FORMAT.format(Long.valueOf(System.currentTimeMillis())));
            if (Intrinsics.areEqual(parseArgs.dumpPriority, "CRITICAL")) {
                dumpCritical(printWriter, parseArgs);
            } else if (!Intrinsics.areEqual(parseArgs.dumpPriority, "NORMAL") || parseArgs.proto) {
                String str = parseArgs.command;
                DumpManager dumpManager = this.dumpManager;
                if (str != null) {
                    switch (str.hashCode()) {
                        case -1354792126:
                            if (str.equals("config")) {
                                this.config.dump(printWriter, new String[0]);
                                break;
                            }
                            break;
                        case -1353714459:
                            if (str.equals("dumpables")) {
                                listOrDumpEntries(dumpManager.getDumpables(), printWriter, parseArgs);
                                break;
                            }
                            break;
                        case -1045369428:
                            if (str.equals("bugreport-normal")) {
                                dumpNormal(printWriter, parseArgs);
                                break;
                            }
                            break;
                        case -881377691:
                            if (str.equals("tables")) {
                                listOrDumpEntries(dumpManager.getTableLogBuffers(), printWriter, parseArgs);
                                break;
                            }
                            break;
                        case 96673:
                            if (str.equals("all")) {
                                listOrDumpEntries(dumpManager.getDumpables(), printWriter, parseArgs);
                                listOrDumpEntries(dumpManager.getLogBuffers(), printWriter, parseArgs);
                                listOrDumpEntries(dumpManager.getTableLogBuffers(), printWriter, parseArgs);
                                break;
                            }
                            break;
                        case 3198785:
                            if (str.equals("help")) {
                                printWriter.println("Let <invocation> be:");
                                printWriter.println("$ adb shell dumpsys activity service com.android.systemui/.SystemUIService");
                                printWriter.println();
                                printWriter.println("Most common usage:");
                                printWriter.println("$ <invocation> <targets>");
                                printWriter.println("$ <invocation> NotifLog");
                                printWriter.println("$ <invocation> StatusBar FalsingManager BootCompleteCacheImpl");
                                printWriter.println("etc.");
                                printWriter.println();
                                printWriter.println("Print all matches, instead of the best match:");
                                printWriter.println("$ <invocation> --all <targets>");
                                printWriter.println("$ <invocation> --all Log");
                                printWriter.println();
                                printWriter.println("Special commands:");
                                printWriter.println("$ <invocation> dumpables");
                                printWriter.println("$ <invocation> buffers");
                                printWriter.println("$ <invocation> tables");
                                printWriter.println("$ <invocation> bugreport-critical");
                                printWriter.println("$ <invocation> bugreport-normal");
                                printWriter.println("$ <invocation> config");
                                printWriter.println();
                                printWriter.println("Targets can be listed:");
                                printWriter.println("$ <invocation> --list");
                                printWriter.println("$ <invocation> dumpables --list");
                                printWriter.println("$ <invocation> buffers --list");
                                printWriter.println("$ <invocation> tables --list");
                                printWriter.println();
                                printWriter.println("Show only the most recent N lines of buffers");
                                printWriter.println("$ <invocation> NotifLog --tail 30");
                                break;
                            }
                            break;
                        case 227996723:
                            if (str.equals("buffers")) {
                                listOrDumpEntries(dumpManager.getLogBuffers(), printWriter, parseArgs);
                                break;
                            }
                            break;
                        case 842828580:
                            if (str.equals("bugreport-critical")) {
                                dumpCritical(printWriter, parseArgs);
                                break;
                            }
                            break;
                    }
                }
                if (parseArgs.proto) {
                    List<String> list = parseArgs.nonFlagArgs;
                    SystemUIProtoDump systemUIProtoDump = new SystemUIProtoDump();
                    Collection dumpables = dumpManager.getDumpables();
                    if (list.isEmpty()) {
                        Iterator it = dumpables.iterator();
                        while (it.hasNext()) {
                            Dumpable dumpable = ((DumpsysEntry.DumpableEntry) it.next()).dumpable;
                            CurrentTilesInteractor currentTilesInteractor = dumpable instanceof CurrentTilesInteractor ? (CurrentTilesInteractor) dumpable : null;
                            if (currentTilesInteractor != null) {
                                ((CurrentTilesInteractorImpl) currentTilesInteractor).dumpProto(systemUIProtoDump);
                            }
                        }
                    } else {
                        for (final String str2 : list) {
                            FilteringSequence$iterator$1 filteringSequence$iterator$1 = new FilteringSequence$iterator$1(SequencesKt.filter(SequencesKt.filter(new CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1(dumpables), new Function1() { // from class: com.android.systemui.dump.DumpHandler$Companion$findBestProtoTargetMatch$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return Boolean.valueOf(((DumpsysEntry.DumpableEntry) obj).name.endsWith(str2));
                                }
                            }), new Function1() { // from class: com.android.systemui.dump.DumpHandler$Companion$findBestProtoTargetMatch$2
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj) {
                                    return Boolean.valueOf(((DumpsysEntry.DumpableEntry) obj).dumpable instanceof CurrentTilesInteractor);
                                }
                            }));
                            if (filteringSequence$iterator$1.hasNext()) {
                                next2 = filteringSequence$iterator$1.next();
                                if (filteringSequence$iterator$1.hasNext()) {
                                    int length = ((DumpsysEntry.DumpableEntry) next2).name.length();
                                    do {
                                        Object next3 = filteringSequence$iterator$1.next();
                                        int length2 = ((DumpsysEntry.DumpableEntry) next3).name.length();
                                        if (length > length2) {
                                            next2 = next3;
                                            length = length2;
                                        }
                                    } while (filteringSequence$iterator$1.hasNext());
                                }
                            } else {
                                next2 = null;
                            }
                            DumpsysEntry.DumpableEntry dumpableEntry = (DumpsysEntry.DumpableEntry) next2;
                            Dumpable dumpable2 = dumpableEntry != null ? dumpableEntry.dumpable : null;
                            CurrentTilesInteractor currentTilesInteractor2 = dumpable2 instanceof CurrentTilesInteractor ? (CurrentTilesInteractor) dumpable2 : null;
                            if (currentTilesInteractor2 != null) {
                                ((CurrentTilesInteractorImpl) currentTilesInteractor2).dumpProto(systemUIProtoDump);
                            }
                        }
                    }
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileDescriptor));
                    try {
                        bufferedOutputStream.write(MessageNano.toByteArray(systemUIProtoDump));
                        bufferedOutputStream.flush();
                        CloseableKt.closeFinally(bufferedOutputStream, null);
                    } finally {
                    }
                } else {
                    List list2 = parseArgs.nonFlagArgs;
                    if (!list2.isEmpty()) {
                        Collection dumpables2 = dumpManager.getDumpables();
                        Collection logBuffers = dumpManager.getLogBuffers();
                        Collection tableLogBuffers = dumpManager.getTableLogBuffers();
                        if (parseArgs.matchAll) {
                            DumpHandler$findAllMatchesInCollection$1 dumpHandler$findAllMatchesInCollection$1 = new DumpHandler$findAllMatchesInCollection$1(dumpables2, logBuffers, tableLogBuffers, list2, null);
                            final int i = 1;
                            Comparator comparator = new Comparator() { // from class: com.android.systemui.dump.DumpHandler$findTargetInCollection$$inlined$sortedBy$1
                                @Override // java.util.Comparator
                                public final int compare(Object obj, Object obj2) {
                                    switch (i) {
                                    }
                                    return ComparisonsKt___ComparisonsJvmKt.compareValues(((DumpsysEntry) obj).getName(), ((DumpsysEntry) obj2).getName());
                                }
                            };
                            ArrayList arrayList2 = new ArrayList();
                            SequenceBuilderIterator it2 = SequencesKt__SequenceBuilderKt.iterator(dumpHandler$findAllMatchesInCollection$1);
                            while (it2.hasNext()) {
                                arrayList2.add(it2.next());
                            }
                            CollectionsKt__MutableCollectionsJVMKt.sortWith(arrayList2, comparator);
                            Iterator it3 = arrayList2.iterator();
                            if (it3.hasNext()) {
                                Object next4 = it3.next();
                                if (it3.hasNext()) {
                                    ArrayList arrayList3 = new ArrayList();
                                    arrayList3.add(next4);
                                    while (it3.hasNext()) {
                                        arrayList3.add(it3.next());
                                    }
                                    arrayList = arrayList3;
                                } else {
                                    arrayList = Collections.singletonList(next4);
                                }
                            } else {
                                arrayList = EmptyList.INSTANCE;
                            }
                        } else {
                            arrayList = new ArrayList();
                            Iterator it4 = list2.iterator();
                            while (it4.hasNext()) {
                                DumpHandler$findTargetInCollection$1 dumpHandler$findTargetInCollection$1 = new DumpHandler$findTargetInCollection$1(dumpables2, (String) it4.next(), logBuffers, tableLogBuffers, null);
                                final int i2 = 0;
                                Comparator comparator2 = new Comparator() { // from class: com.android.systemui.dump.DumpHandler$findTargetInCollection$$inlined$sortedBy$1
                                    @Override // java.util.Comparator
                                    public final int compare(Object obj, Object obj2) {
                                        switch (i2) {
                                        }
                                        return ComparisonsKt___ComparisonsJvmKt.compareValues(((DumpsysEntry) obj).getName(), ((DumpsysEntry) obj2).getName());
                                    }
                                };
                                ArrayList arrayList4 = new ArrayList();
                                SequenceBuilderIterator it5 = SequencesKt__SequenceBuilderKt.iterator(dumpHandler$findTargetInCollection$1);
                                while (it5.hasNext()) {
                                    arrayList4.add(it5.next());
                                }
                                CollectionsKt__MutableCollectionsJVMKt.sortWith(arrayList4, comparator2);
                                Iterator it6 = arrayList4.iterator();
                                if (it6.hasNext()) {
                                    next = it6.next();
                                    if (it6.hasNext()) {
                                        int length3 = ((DumpsysEntry) next).getName().length();
                                        do {
                                            Object next5 = it6.next();
                                            int length4 = ((DumpsysEntry) next5).getName().length();
                                            if (length3 > length4) {
                                                next = next5;
                                                length3 = length4;
                                            }
                                        } while (it6.hasNext());
                                    }
                                } else {
                                    next = null;
                                }
                                DumpsysEntry dumpsysEntry = (DumpsysEntry) next;
                                if (dumpsysEntry != null) {
                                    arrayList.add(dumpsysEntry);
                                }
                            }
                        }
                        Iterator it7 = arrayList.iterator();
                        while (it7.hasNext()) {
                            dump((DumpsysEntry) it7.next(), printWriter, parseArgs);
                        }
                    } else if (parseArgs.listOnly) {
                        Collection dumpables3 = dumpManager.getDumpables();
                        Collection logBuffers2 = dumpManager.getLogBuffers();
                        Collection tableLogBuffers2 = dumpManager.getTableLogBuffers();
                        printWriter.println("Dumpables:");
                        listTargetNames(dumpables3, printWriter);
                        printWriter.println();
                        printWriter.println("Buffers:");
                        listTargetNames(logBuffers2, printWriter);
                        printWriter.println();
                        printWriter.println("TableBuffers:");
                        listTargetNames(tableLogBuffers2, printWriter);
                    } else {
                        printWriter.println("Nothing to dump :(");
                    }
                }
            } else {
                dumpNormal(printWriter, parseArgs);
            }
            printWriter.println();
            printWriter.println("Dump took " + (SystemClock.uptimeMillis() - uptimeMillis) + "ms");
            Trace.endSection();
        } catch (ArgParseException e) {
            printWriter.println(e.getMessage());
        }
    }

    public final void dumpCritical(PrintWriter printWriter, ParsedArgs parsedArgs) {
        for (DumpsysEntry.DumpableEntry dumpableEntry : this.dumpManager.getDumpables()) {
            if (dumpableEntry.priority == DumpPriority.CRITICAL) {
                Companion.dumpDumpable(dumpableEntry, printWriter, parsedArgs.rawArgs);
            }
        }
    }

    public final void dumpNormal(final PrintWriter printWriter, ParsedArgs parsedArgs) {
        String[] strArr;
        DumpManager dumpManager = this.dumpManager;
        Iterator it = dumpManager.getDumpables().iterator();
        while (true) {
            boolean hasNext = it.hasNext();
            strArr = parsedArgs.rawArgs;
            if (!hasNext) {
                break;
            }
            DumpsysEntry.DumpableEntry dumpableEntry = (DumpsysEntry.DumpableEntry) it.next();
            if (dumpableEntry.priority == DumpPriority.NORMAL) {
                Companion.dumpDumpable(dumpableEntry, printWriter, strArr);
            }
        }
        Iterator it2 = dumpManager.getLogBuffers().iterator();
        while (it2.hasNext()) {
            Companion.dumpBuffer((DumpsysEntry.LogBufferEntry) it2.next(), printWriter, parsedArgs.tailLength);
        }
        Iterator it3 = dumpManager.getTableLogBuffers().iterator();
        while (it3.hasNext()) {
            Companion.dumpTableBuffer((DumpsysEntry.TableLogBufferEntry) it3.next(), printWriter, strArr);
        }
        LogBufferEulogizer logBufferEulogizer = this.logBufferEulogizer;
        logBufferEulogizer.getClass();
        try {
            long millisSinceLastWrite = logBufferEulogizer.getMillisSinceLastWrite(logBufferEulogizer.logPath);
            if (millisSinceLastWrite > logBufferEulogizer.maxLogAgeToDump) {
                Log.i("BufferEulogizer", "Not eulogizing buffers; they are " + TimeUnit.HOURS.convert(millisSinceLastWrite, TimeUnit.MILLISECONDS) + " hours old");
                return;
            }
            Files files = logBufferEulogizer.files;
            Path path = logBufferEulogizer.logPath;
            files.getClass();
            Stream<String> lines = java.nio.file.Files.lines(path);
            try {
                printWriter.println();
                printWriter.println();
                printWriter.println("=============== BUFFERS FROM MOST RECENT CRASH ===============");
                lines.forEach(new Consumer() { // from class: com.android.systemui.dump.LogBufferEulogizer$readEulogyIfPresent$1$1
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        printWriter.println((String) obj);
                    }
                });
                AutoCloseableKt.closeFinally(lines, null);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    AutoCloseableKt.closeFinally(lines, th);
                    throw th2;
                }
            }
        } catch (IOException unused) {
        } catch (UncheckedIOException e) {
            Log.e("BufferEulogizer", "UncheckedIOException while dumping the core", e);
        }
    }

    public static void dump(DumpsysEntry dumpsysEntry, PrintWriter printWriter, ParsedArgs parsedArgs) {
        boolean z = dumpsysEntry instanceof DumpsysEntry.DumpableEntry;
        String[] strArr = parsedArgs.rawArgs;
        if (z) {
            Companion.dumpDumpable((DumpsysEntry.DumpableEntry) dumpsysEntry, printWriter, strArr);
        } else if (dumpsysEntry instanceof DumpsysEntry.LogBufferEntry) {
            Companion.dumpBuffer((DumpsysEntry.LogBufferEntry) dumpsysEntry, printWriter, parsedArgs.tailLength);
        } else {
            if (dumpsysEntry instanceof DumpsysEntry.TableLogBufferEntry) {
                Companion.dumpTableBuffer((DumpsysEntry.TableLogBufferEntry) dumpsysEntry, printWriter, strArr);
                return;
            }
            throw new NoWhenBranchMatchedException();
        }
    }
}
