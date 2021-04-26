package com.robertgmelo.i18nfallbackapp;

import android.content.Context
import android.icu.text.DateFormatSymbols
import android.icu.text.DisplayContext
import android.icu.text.MeasureFormat
import android.icu.text.NumberFormat
import android.icu.util.Measure
import android.icu.util.MeasureUnit
import android.text.format.DateFormat
import android.text.format.DateUtils
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class FormattingUtil {

    companion object {

        val SUBSET_LOCALES = arrayOf(
            "af-ZA",
            "am-ET",
            "ar-EG",
            "as-IN",
            "az-Cyrl-AZ",
            "az-Latn-AZ",
            "be-BY",
            "bg-BG",
            "bn-BD",
            "bo-IN",
            "brx-IN",
            "bs-Cyrl-BA",
            "bs-Latn-BA",
            "ca-ES",
            "cs-CZ",
            "da-DK",
            "de-DE",
            "el-GR",
            "en-AU",
            "en-CA",
            "en-GB",
            "en-IN",
            "en-US",
            "es-ES",
            "es-US",
            "et-EE",
            "eu-ES",
            "fa-IR",
            "fi-FI",
            "fil-PH",
            "fr-CA",
            "fr-FR",
            "iw-IL",
            "gl-ES",
            "gu-IN",
            "hi-IN",
            "hr-HR",
            "hu-HU",
            "hy-AM",
            "in-ID",
            "is-IS",
            "it-IT",
            "ja-JP",
            "ka-GE",
            "kgp-BR",
            "kk-KZ",
            "km-KH",
            "kn-IN",
            "ko-KR",
            "kok-IN",
            "ky-KG",
            "lo-LA",
            "lt-LT",
            "lv-LV",
            "mk-MK",
            "ml-IN",
            "mn-MN",
            "mr-IN",
            "ms-MY",
            "my-MM",
            "nb-NO",
            "ne-NP",
            "nl-NL",
            "or-IN",
            "pa-Guru-IN",
            "pl-PL",
            "pt-BR",
            "pt-PT",
            "ro-RO",
            "ru-RU",
            "si-LK",
            "sk-SK",
            "sl-SI",
            "sq-AL",
            "sr-Cyrl-RS",
            "sr-Latn-RS",
            "sv-SE",
            "sw-TZ",
            "ta-IN",
            "te-IN",
            "th-TH",
            "tr-TR",
            "uk-UA",
            "ur-PK",
            "uz-Cyrl-UZ",
            "uz-Latn-UZ",
            "vi-VN",
            "yrl-BR",
            "yrl-CO",
            "yrl-VE",
            "zh-Hans-CN",
            "zh-Hant-HK",
            "zh-Hant-TW",
            "zu-ZA"
        )

        val LOCALES = arrayOf(
            "af-NA",
            "af-ZA",
            "am-ET",
            "ar-AE",
            "ar-AE-u-nu-latn",
            "ar-BH",
            "ar-BH-u-nu-latn",
            "ar-DJ",
            "ar-DJ-u-nu-latn",
            "ar-DZ",
            "ar-DZ-u-nu-arab",
            "ar-EG",
            "ar-EG-u-nu-latn",
            "ar-EH",
            "ar-EH-u-nu-arab",
            "ar-ER",
            "ar-ER-u-nu-latn",
            "ar-IL",
            "ar-IL-u-nu-latn",
            "ar-IQ",
            "ar-IQ-u-nu-latn",
            "ar-JO",
            "ar-JO-u-nu-latn",
            "ar-KM",
            "ar-KM-u-nu-latn",
            "ar-KW",
            "ar-KW-u-nu-latn",
            "ar-LB",
            "ar-LB-u-nu-latn",
            "ar-LY",
            "ar-LY-u-nu-arab",
            "ar-MA",
            "ar-MA-u-nu-arab",
            "ar-MR",
            "ar-MR-u-nu-latn",
            "ar-OM",
            "ar-OM-u-nu-latn",
            "ar-PS",
            "ar-PS-u-nu-latn",
            "ar-QA",
            "ar-QA-u-nu-latn",
            "ar-SA",
            "ar-SA-u-nu-latn",
            "ar-SD",
            "ar-SD-u-nu-latn",
            "ar-SO",
            "ar-SO-u-nu-latn",
            "ar-SS",
            "ar-SS-u-nu-latn",
            "ar-SY",
            "ar-SY-u-nu-latn",
            "ar-TD",
            "ar-TD-u-nu-latn",
            "ar-TN",
            "ar-TN-u-nu-arab",
            "ar-YE",
            "ar-YE-u-nu-latn",
            "as-IN",
            "as-IN-u-nu-latn",
            "az-Cyrl-AZ",
            "az-Latn-AZ",
            "be-BY",
            "bg-BG",
            "bn-BD",
            "bn-BD-u-nu-latn",
            "bn-IN",
            "bn-IN-u-nu-latn",
            "bo-CN",
            "bo-IN",
            "brx-IN",
            "bs-Cyrl-BA",
            "bs-Latn-BA",
            "ca-ES",
            "cs-CZ",
            "da-DK",
            "da-GL",
            "de-AT",
            "de-BE",
            "de-CH",
            "de-DE",
            "de-IT",
            "de-LI",
            "de-LU",
            "el-CY",
            "el-GR",
            "en-AG",
            "en-AI",
            "en-AS",
            "en-AT",
            "en-AU",
            "en-BB",
            "en-BE",
            "en-BI",
            "en-BM",
            "en-BS",
            "en-BW",
            "en-BZ",
            "en-CA",
            "en-CC",
            "en-CH",
            "en-CK",
            "en-CM",
            "en-CX",
            "en-CY",
            "en-DE",
            "en-DG",
            "en-DK",
            "en-DM",
            "en-ER",
            "en-FI",
            "en-FJ",
            "en-FK",
            "en-FM",
            "en-GB",
            "en-GD",
            "en-GG",
            "en-GH",
            "en-GI",
            "en-GM",
            "en-GU",
            "en-GY",
            "en-HK",
            "en-IE",
            "en-IL",
            "en-IM",
            "en-IN",
            "en-IO",
            "en-JE",
            "en-JM",
            "en-KE",
            "en-KI",
            "en-KN",
            "en-KY",
            "en-LC",
            "en-LR",
            "en-LS",
            "en-MG",
            "en-MH",
            "en-MO",
            "en-MP",
            "en-MS",
            "en-MT",
            "en-MU",
            "en-MW",
            "en-MY",
            "en-NA",
            "en-NF",
            "en-NG",
            "en-NL",
            "en-NR",
            "en-NU",
            "en-NZ",
            "en-PG",
            "en-PH",
            "en-PK",
            "en-PN",
            "en-PR",
            "en-PW",
            "en-RW",
            "en-SB",
            "en-SC",
            "en-SD",
            "en-SE",
            "en-SG",
            "en-SH",
            "en-SI",
            "en-SL",
            "en-SS",
            "en-SX",
            "en-SZ",
            "en-TC",
            "en-TK",
            "en-TO",
            "en-TT",
            "en-TV",
            "en-TZ",
            "en-UG",
            "en-UM",
            "en-US",
            "en-VC",
            "en-VG",
            "en-VI",
            "en-VU",
            "en-WS",
            "en-ZA",
            "en-ZM",
            "en-ZW",
            "es-AR",
            "es-BO",
            "es-BR",
            "es-BZ",
            "es-CL",
            "es-CO",
            "es-CR",
            "es-CU",
            "es-DO",
            "es-EA",
            "es-EC",
            "es-ES",
            "es-GQ",
            "es-GT",
            "es-HN",
            "es-IC",
            "es-MX",
            "es-NI",
            "es-PA",
            "es-PE",
            "es-PH",
            "es-PR",
            "es-PY",
            "es-SV",
            "es-US",
            "es-UY",
            "es-VE",
            "et-EE",
            "eu-ES",
            "fa-AF",
            "fa-AF-u-nu-latn",
            "fa-IR",
            "fa-IR-u-nu-latn",
            "fi-FI",
            "fil-PH",
            "fr-BE",
            "fr-BF",
            "fr-BI",
            "fr-BJ",
            "fr-BL",
            "fr-CA",
            "fr-CD",
            "fr-CF",
            "fr-CG",
            "fr-CH",
            "fr-CI",
            "fr-CM",
            "fr-DJ",
            "fr-DZ",
            "fr-FR",
            "fr-GA",
            "fr-GF",
            "fr-GN",
            "fr-GP",
            "fr-GQ",
            "fr-HT",
            "fr-KM",
            "fr-LU",
            "fr-MA",
            "fr-MC",
            "fr-MF",
            "fr-MG",
            "fr-ML",
            "fr-MQ",
            "fr-MR",
            "fr-MU",
            "fr-NC",
            "fr-NE",
            "fr-PF",
            "fr-PM",
            "fr-RE",
            "fr-RW",
            "fr-SC",
            "fr-SN",
            "fr-SY",
            "fr-TD",
            "fr-TG",
            "fr-TN",
            "fr-VU",
            "fr-WF",
            "fr-YT",
            "iw-IL",
            "gl-ES",
            "gu-IN",
            "hi-IN",
            "hr-BA",
            "hr-HR",
            "hu-HU",
            "hy-AM",
            "in-ID",
            "is-IS",
            "it-CH",
            "it-IT",
            "it-SM",
            "ja-JP",
            "ka-GE",
            "kk-KZ",
            "km-KH",
            "kn-IN",
            "ko-KP",
            "ko-KR",
            "kok-IN",
            "ky-KG",
            "lo-LA",
            "lt-LT",
            "lv-LV",
            "mk-MK",
            "ml-IN",
            "mn-MN",
            "mr-IN",
            "ms-BN",
            "ms-MY",
            "ms-SG",
            "my-MM",
            "my-MM-u-nu-latn",
            "nb-NO",
            "nb-SJ",
            "ne-IN",
            "ne-NP",
            "nl-AW",
            "nl-BE",
            "nl-BQ",
            "nl-CW",
            "nl-NL",
            "nl-SR",
            "nl-SX",
            "or-IN",
            "pa-Guru-IN",
            "pl-PL",
            "pt-AO",
            "pt-BR",
            "pt-CV",
            "pt-GW",
            "pt-MO",
            "pt-MZ",
            "pt-PT",
            "pt-ST",
            "pt-TL",
            "ro-MD",
            "ro-RO",
            "ru-BY",
            "ru-KG",
            "ru-KZ",
            "ru-MD",
            "ru-RU",
            "ru-UA",
            "si-LK",
            "sk-SK",
            "sl-SI",
            "sq-AL",
            "sq-MK",
            "sq-XK",
            "sr-Cyrl-BA",
            "sr-Cyrl-ME",
            "sr-Cyrl-RS",
            "sr-Latn-BA",
            "sr-Latn-ME",
            "sr-Latn-RS",
            "sv-AX",
            "sv-FI",
            "sv-SE",
            "sw-CD",
            "sw-KE",
            "sw-TZ",
            "sw-UG",
            "ta-IN",
            "ta-LK",
            "ta-MY",
            "ta-SG",
            "te-IN",
            "th-TH",
            "tr-CY",
            "tr-TR",
            "uk-UA",
            "ur-IN",
            "ur-IN-u-nu-latn",
            "ur-PK",
            "ur-PK-u-nu-arabex",
            "uz-Cyrl-UZ",
            "uz-Latn-UZ",
            "vi-VN",
            "zh-Hans-CN",
            "zh-Hans-HK",
            "zh-Hans-MO",
            "zh-Hans-SG",
            "zh-Hant-HK",
            "zh-Hant-MO",
            "zh-Hant-TW",
            "zu-ZA"
        )
        fun formatTemperature(locale: Locale): String? {
            val cValue = 25
            val mf = MeasureFormat.getInstance(locale, MeasureFormat.FormatWidth.SHORT)
            val mf2 = MeasureFormat.getInstance(locale, MeasureFormat.FormatWidth.NARROW)
            val cContent = mf.formatMeasures(Measure(cValue, MeasureUnit.CELSIUS))
            val fContent = mf.formatMeasures(Measure(((cValue * 9) / 5 + 32), MeasureUnit.FAHRENHEIT))
            val cContent2 = mf2.formatMeasures(Measure(cValue, MeasureUnit.CELSIUS))
            val fContent2 = mf2.formatMeasures(Measure(((cValue * 9) / 5 + 32), MeasureUnit.FAHRENHEIT))
            val format = String.format("|%s|%s/%s|%s/%s|", locale.toLanguageTag(), cContent, fContent, cContent2, fContent2)
            Log.d("formatting", format)
            return format
        }

        fun formatFileSize(size: Float, unit: MeasureUnit): String? {
            val mf =
                MeasureFormat.getInstance(Locale.getDefault(), MeasureFormat.FormatWidth.NARROW)
            return mf.formatMeasures(Measure(size, unit))
        }

        fun getUnitDisplayName(unit: MeasureUnit): String? {
            val mf =
                MeasureFormat.getInstance(Locale.getDefault(), MeasureFormat.FormatWidth.NARROW)
            return mf.getUnitDisplayName(unit);
        }

        fun formatPercentage(locale: Locale, value: Int): String? {
            val format = NumberFormat.getPercentInstance(locale).format(value / 100.0)
            Log.d("formatting", String.format("|%s|%s|", locale.toLanguageTag(), format))
            return format
        }

        fun formatNumber(locale: Locale): String? {
            val nf = java.text.NumberFormat.getNumberInstance(locale)
            val format = nf.format(9)
            val format2 = nf.format(16)
            Log.d("formatting", String.format("|%s|%s %s", locale.toLanguageTag(), format, format2))
            return format
        }

        fun formatElapsedTime(locale: Locale): String? {
            Locale.setDefault(locale)
            val elapsed = DateUtils.formatElapsedTime(4134)
            Log.d("formatting", String.format("|%s|%s|", locale.toLanguageTag(), elapsed))
            return elapsed
        }


        private fun formatDefaultTime(ctx: Context, locale: Locale) {
            val now = Date()
            Locale.setDefault(locale)
            ctx.resources.configuration.locale = locale
            var s1 = "EEEEMMMd"
            var s2 = "EEEMMMd"
            val fmt1 = android.icu.text.DateFormat.getInstanceForSkeleton(s1, locale)
            fmt1.setContext(DisplayContext.CAPITALIZATION_FOR_STANDALONE)
            val t1 = fmt1.format(now)
            val fmt2 = android.icu.text.DateFormat.getInstanceForSkeleton(s2, locale)
            fmt2.setContext(DisplayContext.CAPITALIZATION_FOR_STANDALONE)
            val t2 = fmt2.format(now)

            val pattern1 = DateFormat.getBestDateTimePattern(locale, s1)
            val pattern2 = DateFormat.getBestDateTimePattern(locale, s2)
            val day1A = DateFormat.format(pattern1, now)
            val day1B = DateFormat.format(pattern2, now)
            now.time = now.time + 86400000L
            val day2A = DateFormat.format(pattern1, now)
            val day2B = DateFormat.format(pattern2, now)
            now.time = now.time + 86400000L
            val day3A = DateFormat.format(pattern1, now)
            val day3B = DateFormat.format(pattern2, now)
            now.time = now.time + 86400000L
            val day4A = DateFormat.format(pattern1, now)
            val day4B = DateFormat.format(pattern2, now)
            now.time = now.time + 86400000L
            val day5A = DateFormat.format(pattern1, now)
            val day5B = DateFormat.format(pattern2, now)
            now.time = now.time + 86400000L
            val day6A = DateFormat.format(pattern1, now)
            val day6B = DateFormat.format(pattern2, now)
            now.time = now.time + 86400000L
            val day7A = DateFormat.format(pattern1, now)
            val day7B = DateFormat.format(pattern2, now)
            Log.d("formatting", String.format("|%s|%s|%s|", locale.toLanguageTag(), day1A, day1B))
            Log.d("formatting", String.format("|%s|%s|%s|", locale.toLanguageTag(), day2A, day2B))
            Log.d("formatting", String.format("|%s|%s|%s|", locale.toLanguageTag(), day3A, day3B))
            Log.d("formatting", String.format("|%s|%s|%s|", locale.toLanguageTag(), day4A, day4B))
            Log.d("formatting", String.format("|%s|%s|%s|", locale.toLanguageTag(), day5A, day5B))
            Log.d("formatting", String.format("|%s|%s|%s|", locale.toLanguageTag(), day6A, day6B))
            Log.d("formatting", String.format("|%s|%s|%s|", locale.toLanguageTag(), day7A, day7B))
            if (day1A.length >= 25 || day2A.length >= 25 || day3A.length >= 25 || day4A.length >= 25
                || day5A.length >= 25 || day6A.length >= 25 || day7A.length >= 25
            ) {
                Log.d("alert", locale.toLanguageTag())
            }
        }

        fun formatDateTime(ctx: Context, locale: Locale): String? {

            val now = Date()
            Locale.setDefault(locale)
            ctx.resources.configuration.locale = locale
            val s24 = DateFormat.getBestDateTimePattern(locale, "Hm")
            val f24 = DateFormat.format(s24, now)
            val s12 = DateFormat.getBestDateTimePattern(locale, "hm")
            val f12 = DateFormat.format(s12, now)
            val eDate = DateFormat.getBestDateTimePattern(locale, "yyyyMMdd")
            val fDate = DateFormat.format(eDate, now)
            val amPmStrings = DateFormatSymbols.getInstance(locale).amPmStrings

            var s = if (DateFormat.is24HourFormat(ctx)) "MMMdyyyyHm" else "MMMdyyyyhm"
            var s1 = if (DateFormat.is24HourFormat(ctx)) "MMdyyyyHm" else "MMdyyyyhm"
            var s2 = "ddMMyyyy"//"EEEEMMMdd"
            val skeleton = DateFormat.getBestDateTimePattern(locale, s)
            val skeleton1 = DateFormat.getBestDateTimePattern(locale, s1)
            val skeleton2 = DateFormat.getBestDateTimePattern(locale, s2)
            val t = DateFormat.format(skeleton, now).toString()
            val t1 = DateFormat.format(skeleton1, now).toString()
            val t2 = DateFormat.format(skeleton2, now).toString()

//            Log.d("formatting", t1 + " | " + t2)
//            Log.d("formatting", String.format("|%s|%s|%s|%s", locale.toLanguageTag(), f24, f12, Arrays.toString(amPmStrings)))
            Log.d("formatting", String.format("|%s|%s", locale.toLanguageTag(), t2))

//            val Hm = DateTimeFormatter.ofPattern("HH:mm", locale)
//            val hm = DateTimeFormatter.ofPattern("hh:mm a", locale)
//            val format1 = Hm.format(LocalTime.now())
//            val format2 = hm.format(LocalTime.now())
//            Log.d("formatting", String.format("|%s|%s|%s", locale.toLanguageTag(), f24, f12))
//            Log.d(
//                "formatting",
//                String.format("|%s|%s|%s", locale.toLanguageTag(), format1, format2)
//            )

//            val system_ui_aod_date_pattern = "eMMMd"
//            val patternDate = android.text.format.DateFormat.getBestDateTimePattern(
//                locale,
//                system_ui_aod_date_pattern
//            )
//            val patternTime12h = android.text.format.DateFormat.getBestDateTimePattern(locale, "hm")
//            val patternTime24h = android.text.format.DateFormat.getBestDateTimePattern(locale, "Hm")
//
//            val format = DateFormat.getInstanceForSkeleton(system_ui_aod_date_pattern, locale);
//            format.setContext(DisplayContext.CAPITALIZATION_FOR_STANDALONE);
//
//            val value1 = android.text.format.DateFormat.format(patternTime12h, Date()).toString()
//            Log.d(
//                "formatting",
//                String.format("|%s|%s|%s|", locale.toLanguageTag(), patternTime12h, value1)
//            )
//            val value2 = android.text.format.DateFormat.format(patternTime24h, Date()).toString()
//            Log.d(
//                "formatting",
//                String.format("|%s|%s|%s|", locale.toLanguageTag(), patternTime24h, value2)
//            )

            return t;

        }

        fun formatWeekday(ctx: Context, locale: Locale) {
            val mShortWeekdayNames = mutableListOf<String>()
            val format = SimpleDateFormat("ccccc", locale);

            for (i in 1..Calendar.SATURDAY) {
                val calendar = GregorianCalendar(2014, Calendar.JULY, 20 + i - 1);
                val weekday = format.format(calendar.getTime());
                mShortWeekdayNames.add(weekday)
            }

            val nf = NumberFormat.getNumberInstance()
            val firstday = Calendar.getInstance(locale).getFirstDayOfWeek()
            Log.d(
                "formatting",
                String.format(
                    "|%s|%s|%s",
                    locale.toLanguageTag(),
                    firstday,
                    mShortWeekdayNames.toString()
                )
            )
        }

        fun format(ctx: Context) {
            var count = 1;
            val iterator = SUBSET_LOCALES.iterator()
            iterator.forEach {
                val loc = Locale.forLanguageTag(it)
//                Log.d(
//                    "locale",
//                    String.format(
//                        "%d. %s - %s (%s)",
//                        count,
//                        loc.toLanguageTag(),
//                        loc.getDisplayLanguage(Locale.ENGLISH),
//                        loc.getDisplayCountry(Locale.ENGLISH)
//                    )
//                )
//                count++
//                formatDateTime(ctx, loc)
//                formatElapsedTime(loc)
//                formatPercentage(loc,80)
//                formatNumber(loc)
//                formatDefaultTime(ctx, loc)
                formatTemperature(loc)
            }
        }
    }

}
