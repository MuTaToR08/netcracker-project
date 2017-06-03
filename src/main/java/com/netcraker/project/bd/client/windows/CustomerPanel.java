package com.netcraker.project.bd.client.windows;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.netcraker.project.bd.client.BD;
import com.netcraker.project.bd.client.api.CustomerApi;
import com.netcraker.project.bd.shared.objects.client.CSI;
import com.netcraker.project.bd.shared.objects.client.Customer;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.Date;
import java.util.List;
import java.util.Map;


public class CustomerPanel implements Panels {
    private final RootPanel main = RootPanel.get("main");
    private final Panel customers = new FlowPanel();
    private final Panel listCustomer = new FlowPanel();

    private void defaultWidget()
    {
        Label title = new Label("Заказчики");
        title.addStyleName("title");
        customers.add(title);
        customers.setStyleName("customers");
        customers.add(listCustomer);
    }

    public void refreshData()
    {
        CustomerApi RPC = GWT.create(CustomerApi.class);
        RPC.getAll(new MethodCallback<Map<Integer, Customer>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {

            }

            @Override
            public void onSuccess(Method method, Map<Integer, Customer> map) {
                for(Map.Entry<Integer, Customer> cust: map.entrySet())
                {
                    listCustomer.add(GetCustomerElements(cust.getValue()));
                }

            }
        });
    }

    private Panel GetCustomerElements(final Customer customer)
    {
        String status =BD.statuses.get(customer.getStatus()).getText();
        Panel pCustomer = new FlowPanel();
        pCustomer.setStyleName("customer-entity");
        final Panel control = new FlowPanel();
        control.setStyleName("customer-action");
        final Button button = new Button("Список подключённых услуг");
        button.addMouseUpHandler(new MouseUpHandler() {
            @Override
            public void onMouseUp(MouseUpEvent mouseUpEvent) {
                final PopupPanel popupCSI = new PopupPanel();
                final FlowPanel panels = new FlowPanel();

                popupCSI.setWidth(String.valueOf(control.getOffsetWidth()+"px"));
                popupCSI.setHeight(String.valueOf(control.getOffsetHeight()+"px"));

                panels.setStyleName("customer-csi");
                panels.add(new Label("Loading"));
                popupCSI.add(panels);
                popupCSI.setStyleName("customer-csi-outer");
                popupCSI.setPopupPosition(control.getAbsoluteLeft(),control.getAbsoluteTop());
                popupCSI.show();
                panels.addDomHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent clickEvent) {
                        popupCSI.hide();
                    }
                },ClickEvent.getType());

                CustomerApi customerApi = GWT.create(CustomerApi.class);
                customerApi.getCSICustomer(customer.getId(), new MethodCallback<List<CSI>>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {
                        Window.alert(throwable.getMessage());
                        popupCSI.hide();
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onSuccess(Method method, List<CSI> csis) {
                        panels.clear();

                        for (CSI csi : csis) {
                            FlowPanel flow = new FlowPanel();
                            panels.add(flow);
                            flow.setStyleName("csi-flow");
                            flow.add(new Label(BD.statuses.get(csi.getTsp().getService().getView()).getText()));
                            flow.add(new Label(csi.getTsp().getService().getName()));
                            flow.add(new Label(DateTimeFormat.getShortDateFormat().format(new Date(csi.getStart()))));
                            flow.add(new Label(BD.statuses.get(csi.getStatus()).getText()));
                            flow.add(new Label(String.valueOf(csi.getTsp().getPrice()+" руб.")));
                            flow.add(new Label(BD.statuses.get(csi.getTsp().getService().getType()).getText()+" "+
                                    csi.getTsp().getService().getPeriod()+" "+
                                    BD.statuses.get(csi.getTsp().getService().getPeriodType()).getText()));
                        }
                        //panels.add(new Label(csis));

                    }
                });
            }
        });
        control.add(new Button("Кнопка 1"));
        control.add(new Button("Кнопка 2"));
        control.add(new Button("Кнопка 3"));
        control.add(new Button("Кнопка 4"));
        Button toTree = new Button("К дереву");
        toTree.addMouseUpHandler(new MouseUpHandler() {
            @Override
            public void onMouseUp(MouseUpEvent mouseUpEvent) {

            }
        });
        control.add(toTree);
        control.add(button);

        HorizontalPanel horisontal = new HorizontalPanel();
        horisontal.setWidth("100%");
        Panel block = new FlowPanel();
        block.setStyleName("customer-object");

        Label text = new Label(customer.publicName());
        text.setStyleName("customer-title");
        block.add(text);

        text = new Label("id:"+String.valueOf(customer.getId()));
        text.setStyleName("customer-id");
        pCustomer.add(text);

        text = new Label("FIO:"+customer.getFio());
        text.setStyleName("customer-fio");
        pCustomer.add(text);

        text = new Label("username:"+customer.getUsername());
        text.setStyleName("customer-username");
        pCustomer.add(text);

        text = new Label("INN:"+String.valueOf(customer.getInn()));
        text.setStyleName("customer-inn");
        pCustomer.add(text);

        text = new Label("phone:"+String.valueOf(customer.getPhone()));
        text.setStyleName("customer-phone");
        pCustomer.add(text);

        text = new Label("status:"+ status);
        text.setStyleName("customer-status");
        pCustomer.add(text);

        FlowPanel popupDiv = new FlowPanel();
        popupDiv.add(new Label("Tariff info"));
        popupDiv.add(new Label("Id:"+customer.getTariff().getId()));
        popupDiv.add(new Label("name:"+customer.getTariff().getTariffName()));
        popupDiv.add(new Label("Status:"+BD.statuses.get(customer.getTariff().getStatusId()).getText()));
        final PopupPanel popupTariff = new PopupPanel();
        popupDiv.setStyleName("customer-tariff-popup");
        popupTariff.add(popupDiv);

        final Label  tarifftext = new Label("tariff:"+String.valueOf(customer.getTariff()));
        tarifftext.setStyleName("customer-tariff");
        tarifftext.addMouseOverHandler(new MouseOverHandler() {
            @Override
            public void onMouseOver(MouseOverEvent mouseOverEvent) {
                popupTariff.setPopupPosition(tarifftext.getAbsoluteLeft()+150,tarifftext.getAbsoluteTop());
                popupTariff.show();
            }
        });
        tarifftext.addDomHandler(new MouseOutHandler() {
            public void onMouseOut(MouseOutEvent event) {
                popupTariff.hide();
            }
        }, MouseOutEvent.getType());
        pCustomer.add(tarifftext);

        text = new Label("Balance:"+String.valueOf(customer.getBalance()+" руб"));
        text.setStyleName("customer-balance");
        pCustomer.add(text);


        block.add(horisontal);
        horisontal.add(pCustomer);
        horisontal.setCellWidth(pCustomer,"50%");
        horisontal.add(control);
        horisontal.setCellWidth(control,"50%");
        horisontal.setCellHeight(control,"100%");



        return block;
    }

    private void replaceWindows(RootPanel replaced)
    {
        replaced.clear();
        replaced.add(customers);
    }
    public void replaceWindows()
    {
        replaceWindows(main);
    }

    public CustomerPanel()
    {
        defaultWidget();
    }




}
