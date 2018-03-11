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
public class SpiegelCrawler implements PageCrawler {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" dd.MM.yyyy HH:mm ",Locale.GERMAN);

    private Document document;

    public void setDocument(Document document) {
        this.document = document;
    }

    @Override
    public String findHeadline() {
        return document.getElementsByClass("article-title").get(0).getElementsByClass("headline").get(0).text();
    }

    @Override
    public String findContent() {
        Elements content = document.getElementsByClass("article-section").get(0).getElementsByTag("p");
        StringBuilder strBuilder = new StringBuilder();
        content.forEach(x -> strBuilder.append(x.text()));
        return strBuilder.toString();
    }

    @Override
    public LocalDate findDate() {
        String dateAsString = document.getElementsByClass("timeformat").get(0).text();
        
        return LocalDate.parse(dateAsString.replace("Uhr", "").split(",")[1], formatter);
    }

    @Override
    public String findAuthor() {
        return document.getElementById("js-article-column").child(1).text();
    }

}
