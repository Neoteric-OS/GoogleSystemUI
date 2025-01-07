package com.android.systemui.statusbar.commandline;

import android.util.IndentingPrintWriter;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt__StringsJVMKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ParseableCommand implements Command {
    public static final /* synthetic */ KProperty[] $$delegatedProperties;
    public static final Companion Companion;
    public final String name;
    public final CommandParser parser = new CommandParser();
    public final Flag help$delegate = flag("help", "h", "Print help and return");

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
    }

    static {
        PropertyReference1Impl propertyReference1Impl = new PropertyReference1Impl(ParseableCommand.class, "help", "getHelp()Z", 0);
        Reflection.factory.getClass();
        $$delegatedProperties = new KProperty[]{propertyReference1Impl};
        Companion = new Companion();
    }

    public ParseableCommand(String str) {
        this.name = str;
    }

    public abstract void execute(PrintWriter printWriter);

    @Override // com.android.systemui.statusbar.commandline.Command
    public final void execute(PrintWriter printWriter, List list) {
        KProperty[] kPropertyArr;
        CommandParser commandParser = this.parser;
        try {
            boolean parse = commandParser.parse(list);
            List list2 = commandParser.subCommands;
            ArrayList arrayList = new ArrayList();
            Iterator it = list2.iterator();
            while (true) {
                boolean hasNext = it.hasNext();
                kPropertyArr = $$delegatedProperties;
                if (!hasNext) {
                    break;
                }
                Object next = it.next();
                ParseableCommand parseableCommand = ((OptionalSubCommand) next).cmd;
                KProperty kProperty = kPropertyArr[0];
                if (parseableCommand.help$delegate.inner) {
                    arrayList.add(next);
                }
            }
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                arrayList2.add(((OptionalSubCommand) it2.next()).cmd);
            }
            KProperty kProperty2 = kPropertyArr[0];
            if (this.help$delegate.inner) {
                help(printWriter);
                return;
            }
            if (!arrayList2.isEmpty()) {
                Iterator it3 = arrayList2.iterator();
                while (it3.hasNext()) {
                    ((ParseableCommand) it3.next()).help(printWriter);
                }
                return;
            }
            if (parse) {
                execute(printWriter);
                return;
            }
            ArrayList arrayList3 = new ArrayList();
            if (!commandParser.getUnhandledParams().isEmpty()) {
                List unhandledParams = commandParser.getUnhandledParams();
                ArrayList arrayList4 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(unhandledParams, 10));
                Iterator it4 = unhandledParams.iterator();
                while (it4.hasNext()) {
                    arrayList4.add(((Param) it4.next()).getLongName());
                }
                arrayList3.add("No values passed for required params: " + arrayList4);
            }
            if (!commandParser.getUnhandledSubCmds().isEmpty()) {
                List unhandledSubCmds = commandParser.getUnhandledSubCmds();
                ArrayList arrayList5 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(unhandledSubCmds, 10));
                Iterator it5 = unhandledSubCmds.iterator();
                while (it5.hasNext()) {
                    arrayList5.add(((OptionalSubCommand) it5.next()).longName);
                }
                arrayList3.addAll(arrayList5);
                List unhandledSubCmds2 = commandParser.getUnhandledSubCmds();
                ArrayList arrayList6 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(unhandledSubCmds2, 10));
                Iterator it6 = unhandledSubCmds2.iterator();
                while (it6.hasNext()) {
                    ((OptionalSubCommand) it6.next()).getClass();
                    arrayList6.add(null);
                }
                arrayList3.add("No values passed for required sub-commands: " + arrayList6);
            }
            Iterator it7 = arrayList3.iterator();
            while (it7.hasNext()) {
                printWriter.println((String) it7.next());
            }
        } catch (ArgParseError e) {
            printWriter.println(e.getMessage());
        } catch (Exception e2) {
            printWriter.println("Unknown exception encountered during parse");
            printWriter.println(e2);
        }
    }

    public final Flag flag(String str, String str2, String str3) {
        boolean z = true;
        if (str2 != null && str2.length() != 1) {
            z = false;
        }
        if (!z) {
            throw new IllegalArgumentException(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Flag short name must be one character long, or null. Got (", str2, ")"));
        }
        if (str.startsWith("-")) {
            throw new IllegalArgumentException(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Flags must not start with '-'. Got $(", str, ")"));
        }
        String concat = str2 != null ? "-".concat(str2) : null;
        String concat2 = "--".concat(str);
        CommandParser commandParser = this.parser;
        String checkCliNames = commandParser.checkCliNames(concat, concat2);
        if (checkCliNames != null) {
            throw new IllegalArgumentException(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Detected reused flag name (", checkCliNames, ")"));
        }
        if (concat != null) {
            commandParser.tokenSet.add(concat);
        }
        commandParser.tokenSet.add(concat2);
        Flag flag = new Flag(concat, concat2, str3);
        commandParser._flags.add(flag);
        return flag;
    }

    public final void help(PrintWriter printWriter) {
        final IndentingPrintWriter indentingPrintWriter = new IndentingPrintWriter(printWriter);
        String str = this.name;
        int length = str.length() + 2;
        indentingPrintWriter.println("┌" + StringsKt__StringsJVMKt.repeat(length, "─") + "┐");
        indentingPrintWriter.println("│ " + str + " │");
        indentingPrintWriter.println("└" + StringsKt__StringsJVMKt.repeat(length, "─") + "┘");
        indentingPrintWriter.println();
        ParseableCommandKt.indented(indentingPrintWriter, new Function0() { // from class: com.android.systemui.statusbar.commandline.ParseableCommand$help$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                ParseableCommand.this.usage(indentingPrintWriter);
                return Unit.INSTANCE;
            }
        });
        CommandParser commandParser = this.parser;
        final List list = commandParser.flags;
        if (!list.isEmpty()) {
            indentingPrintWriter.println("FLAGS:");
            ParseableCommandKt.indented(indentingPrintWriter, new Function0() { // from class: com.android.systemui.statusbar.commandline.ParseableCommand$help$5
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    List list2 = list;
                    IndentingPrintWriter indentingPrintWriter2 = indentingPrintWriter;
                    Iterator it = list2.iterator();
                    while (it.hasNext()) {
                        ((Flag) it.next()).describe(indentingPrintWriter2);
                        indentingPrintWriter2.println();
                    }
                    return Unit.INSTANCE;
                }
            });
        }
        List list2 = commandParser.params;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : list2) {
            if (((Param) obj) instanceof SingleArgParam) {
                arrayList.add(obj);
            } else {
                arrayList2.add(obj);
            }
        }
        Pair pair = new Pair(arrayList, arrayList2);
        final List list3 = (List) pair.component1();
        final List list4 = (List) pair.component2();
        boolean isEmpty = list3.isEmpty();
        Companion companion = Companion;
        if (!isEmpty) {
            indentingPrintWriter.println("REQUIRED PARAMS:");
            companion.getClass();
            ParseableCommandKt.indented(indentingPrintWriter, new Function0() { // from class: com.android.systemui.statusbar.commandline.ParseableCommand$Companion$describe$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Iterable iterable = list3;
                    IndentingPrintWriter indentingPrintWriter2 = indentingPrintWriter;
                    Iterator it = iterable.iterator();
                    while (it.hasNext()) {
                        ((Describable) it.next()).describe(indentingPrintWriter2);
                        indentingPrintWriter2.println();
                    }
                    return Unit.INSTANCE;
                }
            });
        }
        if (!list4.isEmpty()) {
            indentingPrintWriter.println("OPTIONAL PARAMS:");
            companion.getClass();
            ParseableCommandKt.indented(indentingPrintWriter, new Function0() { // from class: com.android.systemui.statusbar.commandline.ParseableCommand$Companion$describe$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Iterable iterable = list4;
                    IndentingPrintWriter indentingPrintWriter2 = indentingPrintWriter;
                    Iterator it = iterable.iterator();
                    while (it.hasNext()) {
                        ((Describable) it.next()).describe(indentingPrintWriter2);
                        indentingPrintWriter2.println();
                    }
                    return Unit.INSTANCE;
                }
            });
        }
        List list5 = commandParser.subCommands;
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        for (Object obj2 : list5) {
            arrayList4.add(obj2);
        }
        Pair pair2 = new Pair(arrayList3, arrayList4);
        final List list6 = (List) pair2.component1();
        final List list7 = (List) pair2.component2();
        if (!list6.isEmpty()) {
            indentingPrintWriter.println("REQUIRED SUBCOMMANDS:");
            companion.getClass();
            ParseableCommandKt.indented(indentingPrintWriter, new Function0() { // from class: com.android.systemui.statusbar.commandline.ParseableCommand$Companion$describe$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Iterable iterable = list6;
                    IndentingPrintWriter indentingPrintWriter2 = indentingPrintWriter;
                    Iterator it = iterable.iterator();
                    while (it.hasNext()) {
                        ((Describable) it.next()).describe(indentingPrintWriter2);
                        indentingPrintWriter2.println();
                    }
                    return Unit.INSTANCE;
                }
            });
        }
        if (list7.isEmpty()) {
            return;
        }
        indentingPrintWriter.println("OPTIONAL SUBCOMMANDS:");
        companion.getClass();
        ParseableCommandKt.indented(indentingPrintWriter, new Function0() { // from class: com.android.systemui.statusbar.commandline.ParseableCommand$Companion$describe$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Iterable iterable = list7;
                IndentingPrintWriter indentingPrintWriter2 = indentingPrintWriter;
                Iterator it = iterable.iterator();
                while (it.hasNext()) {
                    ((Describable) it.next()).describe(indentingPrintWriter2);
                    indentingPrintWriter2.println();
                }
                return Unit.INSTANCE;
            }
        });
    }

    public final SingleArgParamOptional param(String str, String str2, String str3, ValueParser valueParser) {
        boolean z = true;
        if (str2 != null && str2.length() != 1) {
            z = false;
        }
        if (!z) {
            throw new IllegalArgumentException(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Parameter short name must be one character long, or null. Got (", str2, ")"));
        }
        if (str.startsWith("-")) {
            throw new IllegalArgumentException(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Parameters must not start with '-'. Got $(", str, ")"));
        }
        String concat = str2 != null ? "-".concat(str2) : null;
        String concat2 = "--".concat(str);
        CommandParser commandParser = this.parser;
        String checkCliNames = commandParser.checkCliNames(concat, concat2);
        if (checkCliNames != null) {
            throw new IllegalArgumentException(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Detected reused param name (", checkCliNames, ")"));
        }
        if (concat != null) {
            commandParser.tokenSet.add(concat);
        }
        commandParser.tokenSet.add(concat2);
        SingleArgParamOptional singleArgParamOptional = new SingleArgParamOptional(concat2, concat, str3, valueParser);
        commandParser._params.add(singleArgParamOptional);
        return singleArgParamOptional;
    }

    public final SingleArgParam required(SingleArgParamOptional singleArgParamOptional) {
        CommandParser commandParser = this.parser;
        commandParser.getClass();
        SingleArgParam singleArgParam = new SingleArgParam(singleArgParamOptional.longName, singleArgParamOptional.shortName, singleArgParamOptional.description, singleArgParamOptional.valueParser);
        commandParser._params.remove(singleArgParamOptional);
        commandParser._params.add(singleArgParam);
        return singleArgParam;
    }

    public final OptionalSubCommand subCommand(ParseableCommand parseableCommand) {
        CommandParser commandParser = this.parser;
        commandParser.getClass();
        String str = parseableCommand.name;
        String checkCliNames = commandParser.checkCliNames(null, str);
        if (checkCliNames != null) {
            throw new IllegalArgumentException(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Cannot re-use name for subcommand (", checkCliNames, ")"));
        }
        if (!parseableCommand.parser.subCommands.isEmpty()) {
            throw new IllegalArgumentException("SubCommands may not contain other SubCommands. " + parseableCommand);
        }
        commandParser.tokenSet.add(str);
        OptionalSubCommand optionalSubCommand = new OptionalSubCommand(parseableCommand);
        commandParser._subCommands.add(optionalSubCommand);
        return optionalSubCommand;
    }

    public void usage(IndentingPrintWriter indentingPrintWriter) {
    }
}
