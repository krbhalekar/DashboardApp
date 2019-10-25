package com.app.dashboard.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.dashboard.R;
import com.app.dashboard.beans.ComponentBean;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LineGraphFragment extends Fragment {

    private LineChart lineChart;

    public LineGraphFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_line_graph, container, false);
        lineChart = view.findViewById(R.id.lineGraph);
        loadData();
        return view;
    }

    private void loadData() {
        ArrayList tempComponentList = new ArrayList();
        List<ComponentBean> componentBeanList = ComponentBean.findWithQuery(ComponentBean.class, "SELECT NAME, TOTAL_ELAPSED_TIME FROM COMPONENT_BEAN");

        for(int i = 0; i < componentBeanList.size();i++){
            System.out.println("Component = " + componentBeanList.get(i).getName());
            System.out.println("Total Elapsed Time = " + componentBeanList.get(i).getTotalElapsedTime());
            tempComponentList.add(new Entry(i,componentBeanList.get(i).getTotalElapsedTime()/3600));
        }

        LineDataSet dataSet = new LineDataSet(tempComponentList, "Number Of Components");
//        dataSet.setValueTextColor(Color.BLACK);
//        dataSet.setValueTextSize(18f);
        LineData data = new LineData(dataSet);
        lineChart.setData(data);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        lineChart.animateXY(5000, 5000);
        Description description =  new Description();
        description.setText("Component Line Graph");
        lineChart.setDescription(description);
        Legend l = lineChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);
        l.setTextSize(11f);
        l.setTextColor(Color.WHITE);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
    }

}
