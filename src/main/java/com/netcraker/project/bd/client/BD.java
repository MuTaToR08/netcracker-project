package com.netcraker.project.bd.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;
import com.netcraker.project.bd.client.api.StaticObjectApi;
import com.netcraker.project.bd.client.windows.CustomerPanel;
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
  public static Map<Integer,Status> statuses = null;
  public static Map<Integer,ObjectType> types = null;
  static boolean loadStatus = false;
  static boolean loadtype = false;

  final CustomerPanel customerPanel = new CustomerPanel();
  final protected RootPanel main = RootPanel.get("main");

  protected final RootPanel menuButton = RootPanel.get("menu-buttons");
  
  public void getStaticData()
  {
    StaticObjectApi rpcTable = GWT.create(StaticObjectApi.class);
    rpcTable.getAllStatus(new MethodCallback<Map<Integer,Status>>() {
      @Override
      public void onFailure(Method method, Throwable throwable) {}

      @Override
      public void onSuccess(Method method, Map<Integer,Status> stats) {
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
    });
  }

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    Defaults.setServiceRoot(GWT.getHostPageBaseURL());
    getStaticData();
    setDefaultElement();

    Timer t = new Timer() {
      public void run() {
        if(loadStatus && loadtype)
        {
          customerPanel.replaceWindows();
          customerPanel.refreshData();
          return;
        }
        this.schedule(500);
      }
    };
    t.schedule(500);


  }

  private void setDefaultElement() {
    MenuBar menuBar = new MenuBar();
    menuBar.addItem(new MenuItem("Заказчики", new Scheduler.ScheduledCommand() {
      @Override
      public void execute() {}
    }));
    menuBar.addItem(new MenuItem("Путь", new Scheduler.ScheduledCommand() {
      @Override
      public void execute() {}
    }));
    menuBar.addItem(new MenuItem("Здания", new Scheduler.ScheduledCommand() {
      @Override
      public void execute() {}
    }));

    menuButton.add(menuBar);
  }
}
