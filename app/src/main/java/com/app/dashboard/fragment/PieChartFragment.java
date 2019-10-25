package com.app.dashboard.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.dashboard.R;
import com.app.dashboard.beans.CaseBean;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;


public class PieChartFragment extends Fragment {

    private PieChart pieChart;

    public PieChartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pie_chart, container, false);
        pieChart = view.findViewById(R.id.piechart);
        loadData();
        return view;
    }

    private void loadData() {
        ArrayList tempCaseList = new ArrayList();
        List<CaseBean> caseStatusBeanList = CaseBean.findWithQuery(CaseBean.class, "SELECT CASE_STATUS, COUNT(CASE_STATUS) AS COUNT FROM CASE_BEAN GROUP BY CASE_STATUS");
        System.out.println("caseStatusBeanList = " + caseStatusBeanList.size());
        for(int i = 0; i < caseStatusBeanList.size();i++){
            System.out.println("Status = " + caseStatusBeanList.get(i).getCaseStatus());
            System.out.println("Count = " + caseStatusBeanList.get(i).getCount());
            tempCaseList.add(new PieEntry(caseStatusBeanList.get(i).getCount(), caseStatusBeanList.get(i).getCaseStatus(), i));
        }

        PieDataSet dataSet = new PieDataSet(tempCaseList, "Number Of Cases");
        dataSet.setValueTextColor(Color.WHITE);
        dataSet.setValueTextSize(10f);
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.animateXY(5000, 5000);
        pieChart.setMinAngleForSlices(60f);
        pieChart.setCenterText("Case Status Count");
        pieChart.setCenterTextSize(20);
        pieChart.setCenterTextColor(Color.parseColor("#0097A7"));
        Description description =  new Description();
        description.setText("Case Status Pie Chart");
        pieChart.setDescription(description);
    }
}
