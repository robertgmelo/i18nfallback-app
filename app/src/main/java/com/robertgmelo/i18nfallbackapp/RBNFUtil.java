package com.robertgmelo.i18nfallbackapp;


import com.ibm.icu.impl.ICUData;
import com.ibm.icu.impl.ICUResourceBundle;
import com.ibm.icu.impl.LocaleIDs;
import com.ibm.icu.text.RuleBasedNumberFormat;

import java.util.Locale;
import java.util.Set;

public class RBNFUtil {

    private static RBNFUtil instance;
    private Set<String> mLocaleSet;

    private RBNFUtil() {
        mLocaleSet = ICUResourceBundle.getAvailableLocaleNameSet(ICUData.ICU_RBNF_BASE_NAME, ICUResourceBundle.ICU_DATA_CLASS_LOADER);
    }

    public static RBNFUtil getInstance() {
        if (instance == null) {
            instance = new RBNFUtil();
        }

        return instance;
    }

    public boolean isLanguageSupported(String languageTag) {

        if (languageTag != null && !languageTag.isEmpty()) {
            Locale loc = Locale.forLanguageTag(languageTag);
            String language = loc.getLanguage();
            if (language.length() == 3) {
                language = LocaleIDs.threeToTwoLetterLanguage(language);
            }
            return mLocaleSet.contains(language);
        }
        return false;
    }

    public static String format(String languageTag, double number) {
        Locale loc = Locale.forLanguageTag(languageTag);

        RuleBasedNumberFormat rbnf1 = new RuleBasedNumberFormat(loc, RuleBasedNumberFormat.SPELLOUT);
        String content1 = rbnf1.format(number);

        RuleBasedNumberFormat rbnf2 = new RuleBasedNumberFormat(loc, RuleBasedNumberFormat.SPELLOUT);
        rbnf2.setDefaultRuleSet("%spellout-ordinal-verbose");
        String content2 = rbnf2.format(number);


        RuleBasedNumberFormat rbnf3 = new RuleBasedNumberFormat(loc, RuleBasedNumberFormat.ORDINAL);
        String content3 = rbnf3.format(number);

        RuleBasedNumberFormat rbnf4 = new RuleBasedNumberFormat(loc, RuleBasedNumberFormat.DURATION);
        String content4 = rbnf4.format(number);

        RuleBasedNumberFormat rbnf5 = new RuleBasedNumberFormat(loc, RuleBasedNumberFormat.NUMBERING_SYSTEM);
        rbnf5.setDefaultRuleSet("%roman-upper");
        String content5 = rbnf5.format(number);

        return String.format("(%s): %s, %s, %s, %s, %s", loc.toLanguageTag(), content1, content2, content3, content4, content5);
    }



//        Locale[] availableLocales = RuleBasedNumberFormat.getAvailableLocales();
//        Log.d(TAG, "RBNF locales size: " + availableLocales.length);
//        for (int i = 0; i < availableLocales.length; i++) {
//            Log.d(TAG, String.format("%s. %s", i + 1, availableLocales[i].toLanguageTag()));
//        }

}
