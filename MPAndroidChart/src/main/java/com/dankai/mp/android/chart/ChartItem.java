package com.dankai.mp.android.chart;

import android.content.Context;
import android.view.View;

import com.github.mikephil.charting.data.ChartData;

public abstract class ChartItem {
    static final int TYPE_BAR_CHART = 0;
    static final int TYPE_LINE_CHART = 1;
    static final int TYPE_PIE_CHART = 2;

    ChartData<?> mChartData;

    ChartItem(ChartData<?> chartData) {
        this.mChartData = chartData;
    }

    public abstract int getItemType();

    public abstract View getView(int position, View convertView, Context c);
}
