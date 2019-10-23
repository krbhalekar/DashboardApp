package com.app.dashboard.listener;

import com.app.dashboard.beans.CaseBean;
import com.app.dashboard.beans.ComponentBean;

import java.util.List;

/**
 * Created by khushalb on 23/10/19.
 */

public interface FileCallback {
    void onLoading();

    void onCompleted(List<ComponentBean> c1, List<CaseBean> c2);
}
