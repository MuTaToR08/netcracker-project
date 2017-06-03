package com.netcraker.project.bd.client.windows;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;

public class TreePanel implements Panels{
    private final RootPanel main = RootPanel.get("main");
    private final TreePanel customers = new TreePanel();
    private final Panel listCustomer = new FlowPanel();


    @Override
    public void replaceWindows() {
        replaceWindows(main);
    }

    private void replaceWindows(RootPanel replaced)
    {
        //replaced.clear();
    }

    @Override
    public void refreshData() {

        /*TreeApi treeApi = GWT.create(TreeApi.class);

        treeApi.getChilds(0, new MethodCallback<List<? super ObjectBD>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {

            }

            @Override
            public void onSuccess(Method method, List<? super ObjectBD> objects) {
                for (Object object : objects) {
                    Window.alert(object.toString());
                }
            }
        });*/
    }

    public TreePanel() {

    }

    private void defaultWidgets()
    {

    }



}
