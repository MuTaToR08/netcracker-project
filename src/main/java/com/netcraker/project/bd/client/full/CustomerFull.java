package com.netcraker.project.bd.client.full;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.*;
import com.netcraker.project.bd.client.BD;
import com.netcraker.project.bd.client.FactoryView;
import com.netcraker.project.bd.client.api.CustomerApi;
import com.netcraker.project.bd.client.api.TreeApi;
import com.netcraker.project.bd.client.api.client.TariffApi;
import com.netcraker.project.bd.shared.objects.ObjectBD;
import com.netcraker.project.bd.shared.objects.Status;
import com.netcraker.project.bd.shared.objects.client.Billing;
import com.netcraker.project.bd.shared.objects.client.CSI;
import com.netcraker.project.bd.shared.objects.client.Customer;
import com.netcraker.project.bd.shared.objects.client.Tariff;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.*;

import static com.netcraker.project.bd.client.FactoryView.createObject;

public class CustomerFull implements FullWindowPanel<Customer> {
    @Override
    public void OpenWindow(Customer obj) {

        BD.viewPreloader();
        final int[] query = {0,0,0,0};
        TreeApi path = GWT.create(TreeApi.class);

        path.getParents(obj.getId(), new MethodCallback<List<Object>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {}
            @Override
            public void onSuccess(Method method, List<Object> objects) {
                Element bs = (RootPanel.get("breadcrumbs").getElement());

                for (Object object : objects) {
                    ObjectBD tmp = createObject(object);
                    if (tmp == null)
                        continue;
                    bs.setInnerHTML(bs.getInnerHTML()+"<li>"+tmp.publicName()+"</li>");
                }
                query[0]++;
            }
        });

        RootPanel main = RootPanel.get("main");
        main.clear();

        FlowPanel left = new FlowPanel();
        FlowPanel right = new FlowPanel();
        HorizontalPanel tbl = new HorizontalPanel();

        main.add(tbl);
        tbl.setWidth("100%");
        tbl.add(left);
        tbl.add(right);

        left.add(addLabelBox("ФИО:"     ,"fio"      ,obj.getFio()                   ) );
        left.add(addLabelBox("ИНН:"     ,"inn"      ,String.valueOf(obj.getInn())   ) );
        left.add(addLabelBox("Телефон:" ,"phone"    ,String.valueOf(obj.getPhone()) ) );
        left.add(addLabelBox("Логин:"   ,"login"    ,obj.getUsername()              ) );
        left.add(addLabelBox("Пароль:"  ,"pass"     ,obj.getPassword()              ) );
        FlowPanel tarif = addLabelBox("Тариф:"   ,"tariff"   ,obj.getTariff().getTariffName());
        left.add(tarif);
        left.add(addLabelBox("Статус:"   ,"status"  ,BD.getStatuses().get(obj.getStatus()).getText()) );
        left.add(addLabelBox("Баланс(руб):"   ,"balance" ,String.valueOf(obj.getBalance())) );

        TariffApi tariffApi = GWT.create(TariffApi.class);
        tariffApi.getAll(new MethodCallback<Map<Integer, Tariff>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {}

            @Override
            public void onSuccess(Method method, Map<Integer, Tariff> tariffs) {
                tarif.clear();
                InlineLabel label = new InlineLabel("Тариф:");
                label.setStyleName("labelCustomer");
                tarif.add(label);
                ListBox tariffList = new ListBox();
                for (Map.Entry<Integer, Tariff> entry : tariffs.entrySet()) {
                    tariffList.addItem(entry.getValue().getObjName(),String.valueOf(entry.getKey()));
                }
                tarif.add(tariffList);
                query[1]++;
            }
        });

        final FlowPanel billing = new FlowPanel();
        billing.setStyleName("customer-billing");
        final FlowPanel csiFlow = new FlowPanel();
        csiFlow.setStyleName("customer-csiTable");
        final FlowPanel references = new FlowPanel();
        references.setStyleName("customer-reference");
        CustomerApi customerApi = GWT.create(CustomerApi.class);
        customerApi.getBillingCustomer(obj.getId(), new MethodCallback<List<Billing>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {}

            @Override
            public void onSuccess(Method method, List<Billing> billings) {
                billing.add(createTable(billings));
                query[2]++;
            }
        });

        customerApi.getCSICustomer(obj.getId(), new MethodCallback<List<CSI>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {}

            @Override
            public void onSuccess(Method method, List<CSI> csis) {
                csiFlow.add(createTableCSI(csis));
                query[3]++;
            }
        });
        TreeApi treeApi = GWT.create(TreeApi.class);
        treeApi.getReference(obj.getId(), new MethodCallback<List<Object>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {}

            @Override
            public void onSuccess(Method method, List<Object> objects) {
                Label title = new Label("Связи");
                title.setStyleName("reference-title");
                references.add(title);
                FlowPanel ul = new FlowPanel();
                ul.setStyleName("tree-ul-li");
                for (Object object : objects) {

                    HashMap<String, Object> e = (HashMap<String, Object>) object;
                    FlowPanel ulDiv = new FlowPanel();
                    FlowPanel data = new FlowPanel();
                    Widget html = new HTML();
                    ul.add(ulDiv);
                    ulDiv.add(data);
                    data.add(html);

                    Optional<Map.Entry<Integer, Status>> opt = BD.getStatuses().entrySet().
                            parallelStream().
                            filter((map) -> Integer.valueOf(e.get("attribute").toString()) == map.getValue().getAttrId()).
                            findFirst();
                    if(opt.isPresent())
                        data.add(new InlineLabel("|>>"+opt.get().getValue().getText()));

                    ObjectBD tmp = FactoryView.createObject(e.get("objectBD"));
                    if(tmp == null)
                        continue;
                    html.getElement().setInnerHTML(tmp.getHtmlUl());
                    html.setStyleName("tree-"+tmp.getObjectClass());
                    html.addStyleName("tree-element");
                    data.setStyleName("info");
                }
                references.add(ul);
            }
        });



        right.add(billing);
        right.add(csiFlow);
        right.add(references);
        Timer t= new Timer() {
            @Override
            public void run() {
                if(query[0] == 1 && query[1] == 1 && query[2] == 1 && query[3] == 1) {
                    BD.hidePreloader();
                    return;
                }
                schedule(500);
            }
        };
        t.schedule(500);
    }

    private FlowPanel addLabelBox(String Label, String name, String value)
    {
        InlineLabel label = new InlineLabel(Label);
        FlowPanel str = new FlowPanel();
        TextBox strBox = new TextBox();

        label.setStyleName("labelCustomer");
        strBox.setName(name);
        strBox.setValue(value);
        str.add(label);
        str.add(strBox);

        return str;
    }

    final private CellTable<Billing> billingsTable = new CellTable<>();

    private CellTable<Billing> createTable(List<Billing> billings)
    {
        billingsTable.setRowData(0, billings);
        TextColumn<Billing> nameColumn = new TextColumn<Billing>() {
            @Override
            public String getValue(Billing billing) {
                return billing.publicName();
            }
        };


        // Create address column.
        TextColumn<Billing> date = new TextColumn<Billing>() {
            @Override
            public String getValue(Billing billing) {
                if(billing.getStartDate() == null)
                    return "";
                return  DateTimeFormat.getShortDateFormat().format(new Date(billing.getStartDate()));
            }
        };
        date.setSortable(true);

        TextColumn<Billing> amout = new TextColumn<Billing>() {
            @Override
            public String getValue(Billing billing) {
                return String.valueOf(billing.getAmount())+" руб.";
            }
        };


        billingsTable.addColumn(nameColumn, "Имя");
        billingsTable.addColumn(date, "Дата");
        billingsTable.addColumn(amout, "Цена");

        billingsTable.setRowCount(billings.size(), true);
        billingsTable.setVisibleRange(0, 10);
        billingsTable.getColumnSortList().push(date);
        return billingsTable;
    }

    final private CellTable<CSI> csiTable = new CellTable<>();
    private CellTable<CSI> createTableCSI(List<CSI> csis)
    {
        csiTable.setRowData(0, csis);
        TextColumn<CSI> nameColumn = new TextColumn<CSI>() {
            @Override
            public String getValue(CSI csi) {
                return csi.publicName();
            }
        };
        TextColumn<CSI> dateStartColumn = new TextColumn<CSI>() {
            @Override
            public String getValue(CSI csi) {
                if(csi.getStart() == null)
                    return "";
                return  DateTimeFormat.getShortDateFormat().format(new Date(csi.getStart()));
            }
        };
        TextColumn<CSI> dateEndColumn = new TextColumn<CSI>() {
            @Override
            public String getValue(CSI csi) {
                if(csi.getEnd() == null)
                    return "";
                return  DateTimeFormat.getShortDateFormat().format(new Date(csi.getEnd()));
            }
        };
        TextColumn<CSI> statusColumn = new TextColumn<CSI>() {
            @Override
            public String getValue(CSI csi) {
                return BD.getStatuses().get(csi.getStatus()).getText();
            }
        };
        TextColumn<CSI> serviceColumn = new TextColumn<CSI>() {
            @Override
            public String getValue(CSI csi) {
                return csi.getTsp().getService().publicName();
            }
        };
        TextColumn<CSI> priceColumn = new TextColumn<CSI>() {
            @Override
            public String getValue(CSI csi) {
                return csi.getTsp().getPrice()+" руб";
            }
        };
        TextColumn<CSI> periodColumn = new TextColumn<CSI>() {
            @Override
            public String getValue(CSI csi) {
                return csi.getTsp().getService().getPeriod()+" "+
                        BD.getStatuses().get(csi.getTsp().getService().getPeriodType()).getText();
            }
        };





        csiTable.addColumn(nameColumn, "Имя");
        csiTable.addColumn(dateStartColumn, "Начало");
        csiTable.addColumn(dateEndColumn, "Окончание");
        csiTable.addColumn(statusColumn, "Статус");
        csiTable.addColumn(serviceColumn, "Уcлуга");
        csiTable.addColumn(priceColumn, "Цена");
        csiTable.addColumn(periodColumn, "Период");

        csiTable.setRowCount(csis.size(), true);
        csiTable.setVisibleRange(0, 10);
        //csiTable.getColumnSortList().push(date);
        return csiTable;
    }

}
