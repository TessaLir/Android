package ru.vetukov.cinema.mycinemaviewers.queries;

public class CinemaListQuery {
    private int page;
    private int page_size;
    private String[] fields;

    public CinemaListQuery() {
        this.page = 1;
        this.page_size = 25;
        this.fields = new String[] {
                "id", "site_url" ,"title", "description", "body_text", "ganres" , "comments_count",
                "original_title", "locale", "country", "year", "language", "running_time", "budget",
                "stars", "directormwriter", "poster", "url", "imdb_url"
        };
    }

    public CinemaListQuery(int page, int page_size) {
        this();
        this.page = page;
        this.page_size = page_size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }

    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }
}
