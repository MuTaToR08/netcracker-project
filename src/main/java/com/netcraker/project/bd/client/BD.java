package com.netcraker.project.bd.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.netcraker.project.bd.client.windows.CustomerPanel;
import com.netcraker.project.bd.client.windows.TreePanel;
import com.netcraker.project.bd.shared.objects.ObjectType;
import com.netcraker.project.bd.shared.objects.Status;
import org.fusesource.restygwt.client.Defaults;

import java.util.Map;

public class BD implements EntryPoint {


    public static Map<Integer, Status> statuses;
    private static Map<Integer,ObjectType> types;
    public static Map<Integer, Status> getStatuses() {
        return statuses;
    }
    private final CustomerPanel customerPanel = new CustomerPanel(this);
    private final TreePanel treePanel = new TreePanel();

    public CustomerPanel getCustomerPanel() {
    return customerPanel;
  }

    public TreePanel getTreePanel() {
    return treePanel;
  }

    final protected RootPanel main = RootPanel.get("main");

    private final RootPanel menuButton = RootPanel.get("menu-buttons");



    private void getStaticData()
    {
        BD.statuses =  GeneralUtils.toMapStatus(RootPanel.get("statusList").getElement().getInnerText());
        BD.types =  GeneralUtils.toMapObjects(RootPanel.get("objectType").getElement().getInnerText());
        /*  GWT.log(BD.statuses.toString());
        StaticObjectApi rpcTable = GWT.create(StaticObjectApi.class);
        rpcTable.getAllStatus(new MethodCallback<HashMap<Integer,Status>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {}

            @Override
            public void onSuccess(Method method, HashMap<Integer,Status> stats) {
                BD.statuses = stats;
                loadStatus = true;
              }
            });

        rpcTable.getAllObjectTypes(new MethodCallback<Map<Integer, ObjectType>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {

            }

            @Override
            public void onSuccess(Method method, Map<Integer, ObjectType> typess) {
              BD.types = typess;
              loadtype = true;
            }
        });*/
    }

  /*public static native void getStaticData()/*-{
      try {
          obj = new String($wnd.document.getElementById("statusList").textContent);
          obj1 = new String($wnd.document.getElementById("objectType").textContent);
         // var a = ();
      //console.log( @com.google.gwt.core.client.JsonUtils::safeEval(*) (obj));//window.top.statusList));
      //@com.google.gwt.json.client.JSONParser::parseLenient(obj);
          //String
          this.@com.netcraker.project.bd.client.BD::statuses =(@com.google.gwt.json.client.JSONObject::new(Lcom/google/gwt/core/client/JavaScriptObject;)( @com.google.gwt.core.client.JsonUtils::safeEval(*) (obj)));;

          //= (@com.google.gwt.json.client.JSONObject::new(Lcom/google/gwt/core/client/JavaScriptObject;)( @com.google.gwt.core.client.JsonUtils::safeEval(*) (obj)));
          this.@com.netcraker.project.bd.client.BD::types = (@com.google.gwt.json.client.JSONObject::new(Lcom/google/gwt/core/client/JavaScriptObject;)( @com.google.gwt.core.client.JsonUtils::safeEval(*) (obj1)));

  } catch(Exception ) {
        //  console.log(statusList);
  }
    //  types =  com.google.gwt.json.client.JSONArray(window.top.objectType); // org.fusesource.restygwt.client.AbstractJsonEncoderDecoder.toMap(window.top.objectType,org.fusesource.restygwt.client.AbstractJsonEncoderDecoder.INT, com.netcraker.project.bd.shared.objects.client.Customer_Generated_JsonEncoderDecoder_.INSTANCE, org.fusesource.restygwt.client.Json.Style.DEFAULT);

  }-*/


    public void onModuleLoad() {
      Defaults.setServiceRoot(GWT.getHostPageBaseURL());
      getStaticData();
      setDefaultElement();
      customerPanel.replaceWindows();
      customerPanel.refreshData();
    }

    static public void viewPreloader()
    {
        Panel panel = RootPanel.get("preloader");
        Panel breads =  RootPanel.get("breadcrumbs");
        UListElement list= UListElement.as(breads.getElement());
        list.removeAllChildren();
        panel.removeStyleName("none");
        panel.addStyleName("opacity");
    }

    static public void hidePreloader()
    {
        Panel panel = RootPanel.get("preloader");
        panel.removeStyleName("opacity");
        panel.addStyleName("none");
      }

    private void setDefaultElement() {
        MenuBar menuBar = new MenuBar();
        menuBar.addItem(new MenuItem("Заказчики", () -> {
            customerPanel.replaceWindows();
            customerPanel.refreshData();
        }));
        menuBar.addItem(new MenuItem("Путь", () -> {
          treePanel.replaceWindows();
          treePanel.refreshData();
        }));
        menuBar.addItem(new MenuItem("Здания", () -> {}));

      menuButton.add(menuBar);
    }
}
