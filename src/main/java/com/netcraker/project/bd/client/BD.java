package com.netcraker.project.bd.client;

import com.google.gwt.core.client.*;
import com.google.gwt.dev.util.collect.HashMap;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.netcraker.project.bd.client.api.StaticObjectApi;
import com.netcraker.project.bd.client.windows.CustomerPanel;
import com.netcraker.project.bd.client.windows.TreePanel;
import com.netcraker.project.bd.shared.objects.ObjectType;
import com.netcraker.project.bd.shared.objects.Status;
import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.Map;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class BD implements EntryPoint {
  /**
   * The message displayed to the user when the server cannot be reached or
   * returns an error.
   */
  private static final String SERVER_ERROR = "An error occurred while "
      + "attempting to contact the server. Please check your network "
      + "connection and try again.";
  public static Map<Integer, Status> statuses;

    public static Map<Integer,ObjectType> types;
    static BD gwtInstance = null;
    //BD()
  //  {gwtInstance = this;}

    public static Map<Integer, Status> getStatuses() {
        return gwtInstance.statuses;
    }

   // public Map<Integer, ObjectType> getTypes() {
    //    return gwtInstance.types;
    //}

    final CustomerPanel customerPanel = new CustomerPanel(this);
  final TreePanel treePanel = new TreePanel();

  public CustomerPanel getCustomerPanel() {
    return customerPanel;
  }

  public TreePanel getTreePanel() {
    return treePanel;
  }

  final protected RootPanel main = RootPanel.get("main");

  protected final RootPanel menuButton = RootPanel.get("menu-buttons");



  public void getStaticData()
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
  }//*/
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

  }-*/;

  /**
   * This is the entry point method.
   */
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
    RootPanel.get("breadcrumbs").clear();
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
    menuBar.addItem(new MenuItem("Заказчики", new Scheduler.ScheduledCommand() {
      @Override
      public void execute() {
          customerPanel.replaceWindows();
          customerPanel.refreshData();
      }
    }));
    menuBar.addItem(new MenuItem("Путь", new Scheduler.ScheduledCommand() {
      @Override
      public void execute() {
        treePanel.replaceWindows();
        treePanel.refreshData();
      }
    }));
    menuBar.addItem(new MenuItem("Здания", new Scheduler.ScheduledCommand() {
      @Override
      public void execute() {}
    }));

    menuButton.add(menuBar);
  }
}
