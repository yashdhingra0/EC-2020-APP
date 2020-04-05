
package com.culmyca.ec_2020_app.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventData {

    private Integer perPage;
    private Integer page;
    @SerializedName("list")
    private List<List<EventList>> list = null;
    private Integer total;

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<List<EventList>> getList() {
        return list;
    }

    public void setList(List<List<EventList>> list) {
        this.list = list;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
