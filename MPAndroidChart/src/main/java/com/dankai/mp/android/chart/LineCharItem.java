package com.dankai.mp.android.chart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.LineData;

public class LineCharItem extends ChartItem {

    private final Typeface mTypeface;

    LineCharItem(ChartData<?> chartData, Context context) {
        super(chartData);
        mTypeface = Typeface.createFromAsset(context.getAssets(), "OpenSans-Regular.ttf");
    }

    @Override
    public int getItemType() {
        return ChartItem.TYPE_LINE_CHART;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, Context c) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(c).inflate(R.layout.list_item_linechart, null);
            holder.mChart = convertView.findViewById(R.id.chart);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //设置统计图的格式
        holder.mChart.getDescription().setEnabled(false);
        holder.mChart.setDrawGridBackground(false);

        XAxis xAxis = holder.mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTypeface);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);

        YAxis leftAxis = holder.mChart.getAxisLeft();
        leftAxis.setTypeface(mTypeface);
        leftAxis.setLabelCount(5, false);
        leftAxis.setAxisMinimum(0f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        //不显示图示
        holder.mChart.getLegend().setEnabled(false);

        //设置右边Y轴不显示
        holder.mChart.getAxisRight().setEnabled(false);
        //设置数据
        holder.mChart.setData((LineData) mChartData);

        holder.mChart.animateX(750);
        return convertView;
    }

    private static class ViewHolder {
        LineChart mChart;
    }
}
