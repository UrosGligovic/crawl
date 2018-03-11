/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ugligovic.crawl.crawlers.response;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author User
 */
public class CrawlerResponse implements Serializable {
    
    static final long serialVersionUID = 12325342L;
    
    private String url;
    private String author;
    private LocalDate date;
    private String title;
    private String content;

    public CrawlerResponse() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    
    
}
