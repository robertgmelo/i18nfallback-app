package com.robertgmelo.i18nfallbackapp;

import android.app.Activity;
import android.icu.text.DisplayContext;
import android.icu.text.MeasureFormat;
import android.icu.text.NumberFormat;
import android.icu.util.Measure;
import android.icu.util.MeasureUnit;
import android.os.Bundle;
import android.text.BidiFormatter;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.text.Layout.BREAK_STRATEGY_HIGH_QUALITY;
import static android.text.Layout.HYPHENATION_FREQUENCY_FULL;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView t = (TextView) findViewById(R.id.text);
        t.setText(String.format("Locale: %s", Locale.getDefault().toLanguageTag()));

        TextView t1 = (TextView) findViewById(R.id.text1);
        t1.setText(getString(R.string.resource1));
        TextView t2 = (TextView) findViewById(R.id.text2);
        t2.setText(getString(R.string.resource2));
    }
}
