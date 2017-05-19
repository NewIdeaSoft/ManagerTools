package com.nisoft.problemlistplus;

import android.app.Fragment;
import android.os.Bundle;

import com.nisoft.problemlistplus.entity.Problem;

import java.util.Date;

public class MainActivity extends SingleFragmentActivity{
    private Problem problem;
    @Override
    protected Fragment createFragment() {
        problem = new Problem();
        problem.setAnalyst("韩梅梅");
        problem.setDetailedText("123456789");
        problem.setDate(new Date());
        problem.setDiscover("李雷");
        problem.setReasonText("987654321");
        problem.setSolvedDate(new Date());
        problem.setSolvedText("无敌时多么寂寞");
        problem.setTitle("时间简史");
        problem.setSolver("哈利波特");
        return ProblemInfoItemFragment.newInstance(problem);
    }
}
