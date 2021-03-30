package com.dankai.mp.android.chart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LineChart mLineChart = null;
    private XAxis mXAxis = null;
    private YAxis mLeftYAxis = null;
    private Legend mLegend = null;
    private LimitLine mLimitLine = null;

    private ListView mListView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = findViewById(R.id.list_view);
        List<ChartItem> chartItems = new ArrayList<>();
        //折线图数据
        List<Entry> lineEntries = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            lineEntries.add(new Entry(i, (int) (Math.random() * 65) + 40));
        }
        //饼图数据
        List<PieEntry> pieEntries = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            pieEntries.add(new PieEntry((float) ((Math.random() * 70) + 30), "Quarter " + (i + 1)));
        }
        //折线
        LineDataSet lineData = new LineDataSet(lineEntries, "line");
        lineData.setLineWidth(6f);
        lineData.setHighLightColor(Color.rgb(244, 117, 117));
        lineData.setDrawValues(false);
        //饼图分块
        PieDataSet pieData = new PieDataSet(pieEntries, "pie");
        pieData.setSliceSpace(2f);
        pieData.setColors(ColorTemplate.VORDIPLOM_COLORS);
        //将数据加进listView
        chartItems.add(new LineCharItem(new LineData(lineData), getApplicationContext()));
        chartItems.add(new PieChartItem(new PieData(pieData), getApplicationContext()));
        ChartDataAdapter adapter = new ChartDataAdapter(getApplicationContext(), chartItems);
        mListView.setAdapter(adapter);


//        initLineChart(mLineChart);
//        List<IncomeBean> list = new ArrayList<>();
//        list.add(new IncomeBean("", 3676));
//        list.add(new IncomeBean("", 4389));
//        list.add(new IncomeBean("", 2586));
//        list.add(new IncomeBean("", 6254));
//        list.add(new IncomeBean("", 8384));
//        list.add(new IncomeBean("", 5759));
//        list.add(new IncomeBean("", 6473));
//        list.add(new IncomeBean("", 6764));
//        list.add(new IncomeBean("", 8505));
//        list.add(new IncomeBean("", 9219));
//        list.add(new IncomeBean("", 6195));
//        list.add(new IncomeBean("", 8178));
//        list.add(new IncomeBean("", 6189));
//        showLineChart(list, "我的收益", Color.parseColor("#ff0ebcf6"));
//        Drawable drawable = getResources().getDrawable(R.drawable.fade_blue);
//        setChartFillDrawable(drawable);
    }

    private void showLineChart(List<IncomeBean> dataList, String name, int color) {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            IncomeBean data = dataList.get(i);
            /**
             * 在此可查看 Entry构造方法，可发现 可传入数值 Entry(float x, float y)
             * 也可传入Drawable， Entry(float x, float y, Drawable icon) 可在XY轴交点 设置Drawable图像展示
             */
            Entry entry = new Entry(i, (float) data.getValue());
            entries.add(entry);
        }
        // 每一个LineDataSet代表一条线
        LineDataSet lineDataSet = new LineDataSet(entries, name);
        initLineDataSet(lineDataSet, color, LineDataSet.Mode.LINEAR);
        LineData lineData = new LineData(lineDataSet);
        mLineChart.setData(lineData);
    }

    private void initLineChart(LineChart lineChart) {
        /***图表设置***/
        //是否展示网格线
        lineChart.setDrawGridBackground(false);
        //显示背景颜色为白色
        lineChart.setBackgroundColor(Color.WHITE);
        //是否显示边界
        lineChart.setDrawBorders(false);
        //是否可以拖动
        lineChart.setDragEnabled(false);
        //是否有触摸事件
        lineChart.setTouchEnabled(true);
        //设置XY轴动画效果
        lineChart.animateY(2500);
        lineChart.animateX(1500);
        lineChart.getAxisRight().setEnabled(false);

        /***XY轴的设置***/
        mXAxis = lineChart.getXAxis();
        mLeftYAxis = lineChart.getAxisLeft();
        //X轴设置显示位置在底部
        mXAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        mXAxis.setDrawGridLines(false);
        mXAxis.setAxisMinimum(0f);
        mXAxis.setGranularity(1f);
        //保证Y轴从0开始，不然会上移一点
        mLeftYAxis.setAxisMinimum(0f);
        mLeftYAxis.setDrawGridLines(true);
        mLeftYAxis.enableGridDashedLine(10f, 10f, 0f);

        /***折线图例 标签 设置***/
        mLegend = lineChart.getLegend();
        //设置显示类型，LINE CIRCLE SQUARE EMPTY 等等 多种方式，查看LegendForm 即可
        mLegend.setForm(Legend.LegendForm.LINE);
        mLegend.setTextSize(12f);
        //显示位置 左下方
        mLegend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        mLegend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        mLegend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //是否绘制在图表里面
        mLegend.setDrawInside(false);

    }

    private void initLineDataSet(LineDataSet lineDataSet, int color, LineDataSet.Mode mode) {
        lineDataSet.setColor(color);
        lineDataSet.setCircleColor(color);
        lineDataSet.setLineWidth(1f);
        lineDataSet.setCircleRadius(3f);
        //设置曲线值的圆点是实心还是空心
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(10f);
        lineDataSet.setDrawValues(false);
        //设置折线图填充
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFormLineWidth(1f);
        lineDataSet.setFormSize(15.f);
        if (mode == null) {
            //设置曲线展示为圆滑曲线（如果不设置则默认折线）
            lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        } else {
            lineDataSet.setMode(mode);
        }
    }

    private void setChartFillDrawable(Drawable drawable) {
        if (mLineChart.getData() != null && mLineChart.getData().getDataSetCount() > 0) {
            LineDataSet lineDataSet = (LineDataSet) mLineChart.getData().getDataSetByIndex(0);
            //避免在 initLineDataSet()方法中 设置了 lineDataSet.setDrawFilled(false); 而无法实现效果
            lineDataSet.setDrawFilled(true);
            lineDataSet.setFillDrawable(drawable);
            mLineChart.invalidate();
        }
    }

    private class ChartDataAdapter extends ArrayAdapter<ChartItem> {

        ChartDataAdapter(Context context, List<ChartItem> objects) {
            super(context, 0, objects);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            //noinspection ConstantConditions
            return getItem(position).getView(position, convertView, getContext());
        }

        @Override
        public int getItemViewType(int position) {
            // return the views type
            ChartItem ci = getItem(position);
            return ci != null ? ci.getItemType() : 0;
        }

        @Override
        public int getViewTypeCount() {
            return 3; // we have 3 different item-types
        }
    }
}
