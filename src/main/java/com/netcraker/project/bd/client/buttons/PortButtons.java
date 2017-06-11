package com.netcraker.project.bd.client.buttons;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.netcraker.project.bd.client.BD;
import com.netcraker.project.bd.client.api.networked.PortApi;
import com.netcraker.project.bd.client.windows.TreePanel;
import com.netcraker.project.bd.shared.objects.controled.ExtendsButton;
import com.netcraker.project.bd.shared.objects.networked.Carrier;
import com.netcraker.project.bd.shared.objects.networked.Port;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

/**
 * Created by mutat on 08.06.2017.
 */
public class PortButtons implements AdditionButton<Port> {
    TreePanel treePanel;

    public PortButtons(TreePanel treePanel) {
        this.treePanel = treePanel;
    }

    @Override
    public void extendsButton(Panel panel,Port obj){
        PortApi api = GWT.create(PortApi.class);
        BD.viewPreloader();
        api.getConnectCarrier(obj.getId(), new MethodCallback<Carrier>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {

            }

            @Override
            public void onSuccess(Method method, Carrier carrier) {
                int id[] = carrier.getPorts();
                int addPorts[] = new int[id.length-1];
                int iter = 0;
                for (int i : id) {
                    if(i!=obj.getId())
                        addPorts[iter++] = i;
                }
                if(addPorts.length == 1)
                {
                    Widget button = new Button("Go To", new ClickHandler() {
                        @Override
                        public void onClick(ClickEvent clickEvent) {
                            treePanel.replaceWindows();
                            treePanel.refreshData(addPorts[0]);
                        }
                    });
                    button.setStyleName("port-goto");
                    panel.add((button));
                }else
                {

                }
                BD.hidePreloader();
            }
        });
    }
}
