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
public class LrtCrawler implements PageCrawler {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LLL d, yyyy",Locale.GERMAN);
    
    private Document document;

    public void setDocument(Document document) {
        this.document = document;
    }

    @Override
    public String findHeadline() {
        return document.getElementById("screensteps-main-content").getElementsByClass("lesson").get(0).text();
    }

    @Override
    public String findContent() {
        Elements content = document.getElementsByClass("screensteps-steps").get(0).getElementsByTag("p");
        StringBuilder strBuilder = new StringBuilder();
        content.stream()
                .forEach(x -> strBuilder.append(x.text()));
        return strBuilder.toString();
    }

    @Override
    public LocalDate findDate() {
        String dateAsString = document.getElementById("lesson-sidebar-info").getElementsByTag("p").get(0).text();
        return  LocalDate.parse(dateAsString, formatter);
    }

    @Override
    public String findAuthor() {
        return null;
    }

}
