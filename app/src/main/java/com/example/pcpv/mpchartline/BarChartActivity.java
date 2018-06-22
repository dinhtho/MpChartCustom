
package com.example.pcpv.mpchartline;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BarChartActivity extends AppCompatActivity {
    private static final String TAG = "BarChartActivity";
    private BarChart chart;
    private BarDataSet barDataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        chart = findViewById(R.id.barChart);
        configureChart();
        configureYAxis();
        configureXAxis();
        configureLegend();
        provideDataToChart();
        chart.invalidate();

        findViewById(R.id.bt_pieChart).setOnClickListener(view -> startActivity(
                new Intent(BarChartActivity.this, PieChartActivity.class)));
    }

    private void configureChart() {
        chart.getDescription().setEnabled(false);
        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.i(TAG, "onValueSelected: " + e.getX());
                Log.i(TAG, "onValueSelected: " + e.getY());
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    private void configureLegend() {
        Legend legend = chart.getLegend();
        legend.setXOffset(2);
        legend.setYOffset(0);
        legend.setCustom(createCustomLegendEntries());
    }

    private List<LegendEntry> createCustomLegendEntries() {
        LegendEntry legendEntryA = new LegendEntry();
        legendEntryA.label = "a";
        legendEntryA.formColor = Color.BLUE;

        LegendEntry legendEntryB = new LegendEntry();
        legendEntryB.label = "b";
        legendEntryB.formColor = Color.GREEN;

        LegendEntry legendEntryC = new LegendEntry();
        legendEntryC.label = "c";
        legendEntryC.formColor = Color.RED;

        LegendEntry legendEntryD = new LegendEntry();
        legendEntryD.label = "d";
        legendEntryD.formColor = Color.YELLOW;

        return Arrays.asList(legendEntryA, legendEntryB, legendEntryC, legendEntryD);
    }

    private void configureXAxis() {
        chart.getXAxis().setEnabled(false);
    }

    private void configureYAxis() {
        chart.getAxisRight().setEnabled(false);

        YAxis yAxisLeft = chart.getAxisLeft();
        yAxisLeft.setTextColor(Color.WHITE);
        yAxisLeft.setLabelCount(6);
        yAxisLeft.setAxisMinimum(0f);
        yAxisLeft.setSpaceBottom(0);
    }

    private void provideDataToChart() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 106.1f));
        entries.add(new BarEntry(1f, 33.0f));
        entries.add(new BarEntry(2f, 89.6f));
        entries.add(new BarEntry(3f, 48.0f));

        barDataSet = new BarDataSet(entries, null);
        configureDataSet();

        BarData data = new BarData(barDataSet);
//        data.setHighlightEnabled(false);
        chart.setData(data);


    }

    private void configureDataSet() {
        barDataSet.setValueTextSize(10);
        barDataSet.setColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        barDataSet.setValueTextColor(Color.WHITE);
    }
}
