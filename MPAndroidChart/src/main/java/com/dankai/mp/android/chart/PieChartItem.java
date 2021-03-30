package com.dankai.mp.android.chart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.formatter.PercentFormatter;

public class PieChartItem extends ChartItem {

    private final Typeface mTypeface;

    PieChartItem(ChartData<?> chartData, Context context) {
        super(chartData);
        this.mTypeface = Typeface.createFromAsset(context.getAssets(), "OpenSans-Regular.ttf");
    }

    @Override
    public int getItemType() {
        return ChartItem.TYPE_PIE_CHART;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, Context c) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(c).inflate(R.layout.list_item_piechart, null);
            holder.mChart = convertView.findViewById(R.id.chart);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //设置饼图
        holder.mChart.getDescription().setEnabled(false);
        holder.mChart.setHoleRadius(52f);
        holder.mChart.setTransparentCircleRadius(57f);
        holder.mChart.setUsePercentValues(true);
        holder.mChart.setExtraOffsets(5, 10, 50, 10);

        mChartData.setValueFormatter(new PercentFormatter());
        mChartData.setValueTypeface(mTypeface);
        mChartData.setValueTextSize(11f);
        mChartData.setValueTextColor(Color.WHITE);
        //设置数据
        holder.mChart.setData((PieData) mChartData);

        Legend l = holder.mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        holder.mChart.animateY(900);

        return convertView;
    }

    private static class ViewHolder {
        PieChart mChart;
    }
}
