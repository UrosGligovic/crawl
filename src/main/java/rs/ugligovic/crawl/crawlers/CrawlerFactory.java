/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ugligovic.crawl.crawlers;

import rs.ugligovic.crawl.crawlers.PageCrawler;
import rs.ugligovic.crawl.crawlers.LrtCrawler;
import rs.ugligovic.crawl.crawlers.SeoCrawler;
import rs.ugligovic.crawl.crawlers.SpiegelCrawler;
import rs.ugligovic.crawl.exceptions.NoDefinedCrawlerException;

/**
 *
 * @author Uros Gligovic
 */
public class CrawlerFactory {
    
    public static PageCrawler makeCrawler(String url) throws NoDefinedCrawlerException{
        
        url = url.replace("http://", "").replace("https://", "").split("/")[0].replace("www.", "");
        
        switch(url){
            
            case "spiegel.de":
                return new SpiegelCrawler();
            case "seo.de":
                return new SeoCrawler();
            case "lrt.li":
                return new LrtCrawler();
            default:
                throw new NoDefinedCrawlerException("No defined crawler for "+url); // TODO make special exception for unimplemented crawler
            
            
        }
        
        
    }
    
}
