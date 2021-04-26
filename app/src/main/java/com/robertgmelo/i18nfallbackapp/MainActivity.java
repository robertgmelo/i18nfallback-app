package com.robertgmelo.i18nfallbackapp;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.icu.text.LocaleDisplayNames;
import android.icu.text.MessageFormat;
import android.icu.util.ULocale;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.text.Annotation;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.SpannedString;
import android.text.format.Formatter;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ibm.icu.number.NumberFormatter;
import com.ibm.icu.text.DateIntervalFormat;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.MeasureUnit;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getName();
    private static final String CHANNEL_ID = "channel_i18n_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        LocaleList test = LocaleList.getDefault();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Locale loc = Locale.getDefault();
        TextView t = (TextView) findViewById(R.id.text);
        t.setText(String.format("Locale: %s (%s)", loc.toLanguageTag(), loc.getDisplayName()));

        TextView t1 = (TextView) findViewById(R.id.text1);
        t1.setText(getString(R.string.resource1));
        TextView t2 = (TextView) findViewById(R.id.text2);
        t2.setText(getString(R.string.resource2));

//        FormattingUtil.Companion.format(this);

        String s1 = Formatter.formatFileSize(this, 1024000);
        String s2 = Formatter.formatFileSize(this, 456);
        String s3 = Formatter.formatFileSize(this, 67800);
        String s4 = Formatter.formatFileSize(this, 2650000000L);
        String s5 = Formatter.formatFileSize(this, 42650000000L);
        String s7 = Formatter.formatFileSize(this, 3480000000000L);

        String s10 = Formatter.formatShortFileSize(this, 1024000);
        String s20 = Formatter.formatShortFileSize(this, 456);
        String s30 = Formatter.formatShortFileSize(this, 67800);
        String s40 = Formatter.formatShortFileSize(this, 2650000000L);
        String s50 = Formatter.formatShortFileSize(this, 42650000000L);
        String s70 = Formatter.formatShortFileSize(this, 3480000000000L);

        String format = String.format("%.2f", 57.8);
        String format2 = String.format("%.2f", 57.8);
        String format3 = NumberFormat.getInstance().format(57.8);
        String format4 = NumberFormat.getNumberInstance().format(57.8);
        String format5 = NumberFormatter.with().locale(Locale.getDefault()).unit(MeasureUnit.GIGABYTE).format(57.8).toString();

        resourcetypesKgp();
    }

    private void resourcetypesKgp() {
        byte[] fil = FWUtils.packLanguage("fil");
        String filPack = FWUtils.printPack(fil);
        String filvalue = FWUtils.unpackLanguage(fil);

        byte[] kgp = FWUtils.packLanguage("kgp");
        String kgpPack = FWUtils.printPack(kgp);
        String kgpvalue = FWUtils.unpackLanguage(kgp);

        byte[] yrl = FWUtils.packLanguage("yrl");
        String yrlPack = FWUtils.printPack(yrl);
        String yrlvalue = FWUtils.unpackLanguage(yrl);

        Locale l = Locale.getDefault();
        String s1 = LocaleDisplayNames.getInstance(l).languageDisplayName(l.getLanguage());
        String displayLanguage = ULocale.getDisplayLanguage("kgp", "kgp");
        displayLanguage = ULocale.getDisplayLanguage("kgp-BR", "kgp");
        displayLanguage = ULocale.getDisplayLanguage("kgp", "kgp-BR");
        displayLanguage = ULocale.getDisplayLanguage("kgp-BR", "kgp-BR");
    }

    private String customIntervalFormat(Calendar c1, Calendar c2, String skeleton) {

        Locale l = Locale.getDefault();

        DateIntervalFormat formatDate = DateIntervalFormat.getInstance(skeleton, l);
        String st1 = formatDate.formatToValue(c1, c1).toString();
        String st2 = formatDate.formatToValue(c2, c2).toString();

        String intervalPattern = formatDate.getDateIntervalInfo().getFallbackIntervalPattern();

        return com.ibm.icu.text.MessageFormat.format(intervalPattern, st1, st2);
    }

    public void samples() {

        Calendar time1 = Calendar.getInstance();
        time1.set(Calendar.HOUR_OF_DAY, 11);
        time1.set(Calendar.MINUTE, 11);

        Calendar time2 = Calendar.getInstance();
        time2.set(Calendar.HOUR_OF_DAY, 3);
        time2.set(Calendar.MINUTE, 13);
        time2.set(Calendar.DAY_OF_YEAR, time2.get(Calendar.DAY_OF_YEAR) + 1);

        String value12h = customIntervalFormat(time1, time2, "hm");
        System.out.println(value12h); // 11:11 AM - 03:13 AM
        String value24h = customIntervalFormat(time1, time2, "Hm");
        System.out.println(value24h); // 11:11 - 03:13

        Calendar date1 = Calendar.getInstance();
        date1.set(Calendar.MONTH, Calendar.DECEMBER);
        date1.set(Calendar.DAY_OF_MONTH, 9);

        Calendar date2 = Calendar.getInstance();
        date2.set(Calendar.MONTH, Calendar.JANUARY);
        date2.set(Calendar.DAY_OF_MONTH, 8);
        date2.set(Calendar.YEAR, date1.get(Calendar.YEAR) + 1);

        String valueDate = customIntervalFormat(date1, date2, "dMMM");
        System.out.println(valueDate); // Dec 9 - Jan 8
    }

    private void addSecondaryLocale() {
        Locale defLocale = Locale.getDefault();
        final Locale secondaryLocale;
        if (defLocale.getCountry().equals("BR")) {
            secondaryLocale = new Locale.Builder()
                    .setLanguage("pt")
                    .setRegion(defLocale.getCountry())
                    .build();
        } else {
            secondaryLocale = new Locale.Builder()
                    .setLanguage("es")
                    .setRegion(defLocale.getCountry())
                    .build();
        }

        LocaleList localeList = LocaleList.getDefault();
        final Locale[] newList = new Locale[localeList.size() + 1];
        for (int i = 0; i < localeList.size(); i++) {
            newList[i] = localeList.get(i);
        }
        newList[localeList.size()] = secondaryLocale;

        LocaleList ll = new LocaleList(newList);
        LocaleList.setDefault(ll);
//        LocalePicker.updateLocales(ll);
    }


//    public static String getRatioText(PreviewSize previewRatio) {
//        Resources res = CameraApp.getInstance().getResources();
//
//        if (PreviewSize.hasAspectRatio(PreviewSize.ASPECT_RATIOS_FULL,
//                previewRatio.getTrueAspectRatio())) {
//            return res.getString(R.string.picture_aspect_ratio_full);
//        }
//
//        String[] size = new String[2];
//        if (previewRatio.width >= IMAGE_RATIO_LIMIT || previewRatio.height >= IMAGE_RATIO_LIMIT) {
//            size[0] = trimToDecimal(previewRatio.height);
//            size[1] = trimToDecimal(previewRatio.width);
//        } else {
//            NumberFormat nf = NumberFormat.getNumberInstance(Locale.getDefault());
//            nf.setMaximumIntegerDigits(1);
//            size[0] = nf.format(previewRatio.height);
//            size[1] = nf.format(previewRatio.width);
//        }
//        return res.getString(R.string.image_ratio, size[0], size[1]);
//    }
//
//    private static String trimToDecimal(int value) {
//        NumberFormat nf = NumberFormat.getNumberInstance(Locale.getDefault());
//        nf.setMaximumIntegerDigits(1);
//        return value % 10 == 0 ? nf.format(value / 10)
//                : nf.format((float)value / 10);
//    }

    private void test() {

        String deleteFileMessage = getString(R.string.delete_file);
        MessageFormat msgFmt2 = new MessageFormat(deleteFileMessage, Locale.getDefault()); //ru-RU
        Map args = new HashMap();
        args.put("count", 0);
        String message = msgFmt2.format(args);
        Log.d(TAG, msgFmt2.format(args));
    }

    public void onNotification(View view) {
        createNotificationChannel();

        SpannableStringBuilder text = getStyledText("99884523");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.recording_countdown)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(R.drawable.recording_record, getString(R.string.notification_action), null);

        // Issue the notification.
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(100, builder.build());

    }

    private SpannableStringBuilder getStyledText(String content) {

        SpannedString titleText;
        boolean isPhoneNumber = false;
        if (isPhoneNumber) {
            titleText = new SpannedString(getText(R.string.notification_content_phone));
        } else {
            titleText = new SpannedString(getText(R.string.notification_content_non_phone));
        }

        // get all the annotation spans from the text
        Annotation[] annotations = titleText.getSpans(0, titleText.length(), Annotation.class);

        SpannableStringBuilder spannableString = new SpannableStringBuilder(titleText);

        for (Annotation annotation : annotations) {
            // look for the span with the key font
            if (annotation.getKey().equals("id")) {
                String value = annotation.getValue();
                if (value.equals("phone_status")) {
                    spannableString.setSpan(new ForegroundColorSpan(Color.GREEN),
                            titleText.getSpanStart(annotation),
                            titleText.getSpanEnd(annotation),
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }
        for (Annotation annotation : annotations) {
            // look for the span with the key font
            if (annotation.getKey().equals("id")) {
                String value = annotation.getValue();
                if (value.equals("phone_info")) {
                    spannableString.replace(
                            titleText.getSpanStart(annotation),
                            titleText.getSpanEnd(annotation),
                            content
                    );
                }
            }
        }

        return spannableString;
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}


