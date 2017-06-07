package com.netcraker.project.bd.shared.objects.client;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.netcraker.project.bd.client.BD;
import com.netcraker.project.bd.shared.objects.ObjectBD;
import org.fusesource.restygwt.client.Json;

import java.util.HashMap;

@Json
public class CSI extends ObjectBD {
    private int status;
    private String start;
    private String end;
    private TSP tsp;

    public CSI(HashMap<String, Object> e) throws Exception {
        super(e);
        if(e.containsKey("status") && e.get("status") !=     null) status = Integer.valueOf(e.get("status").toString());
        else status = 1;
        if(e.containsKey("start") && e.get("start") != null) start = e.get("start").toString();
        else start = "";
        if(e.containsKey("end") && e.get("end") !=       null) status =Integer.valueOf(e.get("end").toString());
        else end = "";
        if(e.containsKey("tsp") && e.get("tsp") !=       null) tsp = new TSP((HashMap<String,Object>)e.get("tsp"));
        else tsp = null;

    }

    @Override
    public String toString() {
        return "CSI{" +
                "id=" + id +
                ", status=" + status +
                ", start=" + start +
                ", end=" + end +
                ", tsp=" + tsp +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public TSP getTsp() {
        return tsp;
    }

    public void setTsp(TSP tsp) {
        this.tsp = tsp;
    }

    @Override
    @JsonIgnore
    public String getObjectClass() {
        return "csi";
    }

    @Override
    @JsonIgnore
    public String getHtmlUl() {
        return (BD.statuses.get(status)==null?"":BD.statuses.get(status).getText())+"|"+start+"-"+end+"|"+
                (BD.statuses.get(tsp.getService().getView())==null?"":BD.statuses.get(tsp.getService().getView()).getText())+
                "|"+tsp.getService().getName()+"|"+tsp.getPrice()+"руб";
    }

    @Override
    public String publicName() {
        return super.publicName();
    }

    @JsonCreator
    public CSI(@JsonProperty("id") int id,@JsonProperty("status") int status//,
               ,@JsonProperty("start") String start,@JsonProperty("end") String end,
               @JsonProperty("tsp") TSP tsp) {
        super(id);
        this.status = status;
        this.start = (start);
        this.end = (end);
        this.tsp = tsp;
    }
}
