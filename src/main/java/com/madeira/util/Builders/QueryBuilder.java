package com.madeira.util.Builders;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

public class QueryBuilder {

    private String sql;
    private List<String> querySelectors; 
    private String queryFilters;
    private HashMap<String, List<String>> queryDatastore;

    public QueryBuilder() {
        this.sql = "";
        this.querySelectors = new ArrayList<String>();
        this.queryFilters = "";
        this.queryDatastore = new HashMap<String, List<String>>();
        this.queryDatastore.put("datastore", new ArrayList<String>());
        this.queryDatastore.put("join", new ArrayList<String>());
    }

    public QueryBuilder addVideoSelectors() {
        this.querySelectors.add("v.video_id");
        this.querySelectors.add("v.recorded_date");
        this.querySelectors.add("v.name");
        this.querySelectors.add("v.description");
        this.querySelectors.add("v.link");
        return this;
    }

    public QueryBuilder addTagSelectors() {
        this.querySelectors.add("t.tag_id");
        this.querySelectors.add("t.name");
        return this;
    }

    public QueryBuilder addProductSelectors() {
        this.querySelectors.add("p.product_id");
        this.querySelectors.add("p.name");
        return this;
    }

    public QueryBuilder addVideoDatastore() {
        this.queryDatastore.get("datastore").add("Video v");
        return this;
    }

    public QueryBuilder addTagJoin() {
        this.queryDatastore.get("join").add("video_tag_map vt on vt.video_id = v.video_id");
        this.queryDatastore.get("join").add("Tag t on t.tag_id = vt.tag_id");
        return this;
    }

    public QueryBuilder addProductJoin() {
        this.queryDatastore.get("join").add("tag_product_map tp on tp.tag_id = t.tag_id");
        this.queryDatastore.get("join").add("Product p on p.product_id = tp.product_id");
        return this;
    }

    public QueryBuilder addtextSearchFilter(String textToSearch) {
        String filter = "( to_tsvector('english', v.name || ' ' || v.description) @@ to_tsquery( '"
                            + textToSearch + "' ) )";
        this.queryFilters = filter;
        return this;
    }

    public String buildSelectSql() {
        return "SELECT " + String.join(" , ", this.querySelectors);
    }

    public String buildDatastoreSql() {
        String fromSql = "FROM " + this.queryDatastore.get("datastore").get(0);
        String joinSql = String.join(" JOIN " , this.queryDatastore.get("join"));
        return fromSql + " JOIN " + joinSql;
    }

    public String buildFilterSql() {
        return "WHERE " + queryFilters;
    }

    public String build() {
        this.sql = this.buildSelectSql() + " " 
            + this.buildDatastoreSql() + " " 
            + this.buildFilterSql();
        return this.sql;
    }

}
