/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ugligovic.crawl.crawlers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.jsoup.nodes.Document;

/**
 *
 * @author Uros Gligovic
 */
public interface PageCrawler {
    
    void setDocument(Document document);
    
    String findHeadline();
    
    String findContent();
    
    LocalDate findDate();
    
    String findAuthor();
    
}
