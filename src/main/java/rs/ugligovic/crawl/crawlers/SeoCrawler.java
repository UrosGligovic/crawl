/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ugligovic.crawl.crawlers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author Uros Gligovic
 */
public class SeoCrawler implements PageCrawler{

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy",Locale.GERMAN);
    
    
    private Document document;

    public void setDocument(Document document) {
        this.document = document;
    }
    
    @Override
    public String findHeadline() {
        
        return document.getElementsByClass("postarea").get(0).getElementsByTag("h1").get(0).text();
    }

    @Override
    public String findContent() {
        Elements content = document.getElementsByClass("breadcrumb").get(0).siblingElements();
        StringBuilder strBuilder = new StringBuilder();
        content.stream()
                .filter(x->x.tagName().equals("p"))
                .forEach(x->strBuilder.append(x.text()));
        
        return strBuilder.toString();
    }

    @Override
    public LocalDate findDate() {
        String dateAsString = document.getElementsByClass("time").get(0).text();
        return  LocalDate.parse(dateAsString, formatter);
    }
    

    @Override
    public String findAuthor() {
        return document.getElementsByClass("time").get(0).siblingElements().get(0).text();
    }
    
}
