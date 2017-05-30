package com.netcraker.project.bd.client.windows;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Tree;

public class TreePanel implements Panels{
    private final RootPanel main = RootPanel.get("main");
    private final TreePanel customers = new TreePanel();
    private final Panel listCustomer = new FlowPanel();


    @Override
    public void replaceWindows() {

    }

    @Override
    public void refreshData() {
        main.clear();


    }

    public TreePanel() {

    }

    private void defaultWidgets()
    {

    }



}
