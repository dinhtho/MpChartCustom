package com.example.pcpv.mpchartline;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.pcpv.mpchartline.model.Data;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnChartValueSelectedListener {
    private static final String TAG = "MainActivity";
    private LineChart chart;
    private LineDataSet dataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chart = findViewById(R.id.lineChart);
        configureChart();
        configureLegend();
        configureXAxis();
        configureYAxis();
        provideDataToChart();
        chart.invalidate();

        findViewById(R.id.bt_barChart).setOnClickListener(view -> {
            startActivity(
                    new Intent(MainActivity.this, BarChartActivity.class));
        });

    }

    private void provideDataToChart() {
        List<Data> dataList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            int y = i * i;
            dataList.add(new Data(i, y));
        }

        List<Entry> entries = new ArrayList<>();
        for (Data data : dataList) {
            // turn your data into Entry objects
            entries.add(new Entry(data.getX(), data.getY()));
        }
        dataSet = new LineDataSet(entries, "abc"); // add entries to dataset
        configureLineDataSet();
        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
    }

    private void configureChart() {
        chart.getDescription().setEnabled(false);
        chart.setOnChartValueSelectedListener(this);
    }

    private void configureLegend() {
        Legend legend = chart.getLegend();
        legend.setXOffset(2);
        legend.setYOffset(0);
    }

    private void configureLineDataSet() {
        dataSet.setColor(Color.parseColor("#5DBCD2"));
        dataSet.setLineWidth(3);
        dataSet.setDrawValues(false);
        dataSet.setHighLightColor(Color.GREEN);
        dataSet.setMode(LineDataSet.Mode.LINEAR);
        dataSet.setDrawCircles(false);
    }

    private void configureXAxis() {
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(5);
        xAxis.setTextColor(Color.WHITE);
    }

    private void configureYAxis() {
        YAxis yAxisRight = chart.getAxisRight();
        yAxisRight.setEnabled(false);

        YAxis yAxisLeft = chart.getAxisLeft();
        yAxisLeft.setInverted(true);
        yAxisLeft.setLabelCount(6);
        yAxisLeft.setTextColor(Color.WHITE);

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.i(TAG, "onValueSelected: " + e.getX());
        Log.i(TAG, "onValueSelected: " + e.getY());
    }

    @Override
    public void onNothingSelected() {

    }
}
