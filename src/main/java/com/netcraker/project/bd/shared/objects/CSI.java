package com.netcraker.project.bd.shared.objects;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.fusesource.restygwt.client.Json;

@Json
public class CSI extends ObjectBD {
    private int id;
    private int status;
    private String start;
    private String end;
    private TSP tsp;

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

    public int getId() {
        return id;
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

   /// @JsonCreator
  /*  public CSI(@JsonProperty("id") int id,@JsonProperty("status") int status,
               @JsonProperty("start") Date start,@JsonProperty("end") Date end,
               @JsonProperty("tsp") TSP tsp) {

//        this.id = id;
  //      this.status = status;
    //    this.start = start;
      //  this.end = end;
        //this.tsp = tsp;
    }*/
    @JsonCreator
    public CSI(@JsonProperty("id") int id,@JsonProperty("status") int status//,
               ,@JsonProperty("start") String start,@JsonProperty("end") String end,
               @JsonProperty("tsp") TSP tsp) {

        this.id = id;
        this.status = status;
        this.start = (start);
        this.end = (end);
        this.tsp = tsp;
    }

    public CSI() {

    }
}
