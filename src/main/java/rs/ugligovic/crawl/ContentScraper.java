/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ugligovic.crawl;

import rs.ugligovic.crawl.crawlers.CrawlerFactory;
import rs.ugligovic.crawl.crawlers.response.CrawlerResponse;
import rs.ugligovic.crawl.crawlers.PageCrawler;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import rs.ugligovic.crawl.exceptions.NoDefinedCrawlerException;

/**
 *
 * @author Uros Gligovic
 */
public class ContentScraper {

    public static void main(String[] args) throws IOException {

        long startTime = new Date().getTime();
        List<String> listOfLinks = new ArrayList<>();
        listOfLinks.add("http://www.spiegel.de/wissenschaft/technik/solar-impulse-2-solarflugzeug-startet-erdumrundung-a-1022458.html");
        listOfLinks.add("http://seo.de/8963/seo-der-soeldner-sinniert-ueber-die-welt-von-heute/");
        listOfLinks.add("http://lrt.li/faqsitewide");
        listOfLinks.add("http://seo.de/3256/linda-teodosiu-auf-der-abakus-pubcon-video/");
        listOfLinks.add("http://www.spiegel.de/wissenschaft/technik/batterie-funktioniert-auch-bei-minus-70-grad-a-1196750.html");

        List<CrawlerResponse> resultList = crawlTheList(listOfLinks);
        String response = new Gson().toJson(resultList);

        System.out.println(response);
        System.out.println("finished in " + (new Date().getTime() - startTime));

    }

    public static List<CrawlerResponse> crawlTheList(List<String> listOfLinks) {
        return listOfLinks.stream().parallel()
                .map(x -> crawl(x))
                .filter(x -> x != null)
                .collect(Collectors.toList());
    }

    private static CrawlerResponse crawl(String url) {

        PageCrawler crawler;
        Document document;
        try {
            crawler = CrawlerFactory.makeCrawler(url);
            document = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
        } catch (IOException | NoDefinedCrawlerException ex) {
            System.err.println(ex);
            return null;
        }
        crawler.setDocument(document);
        CrawlerResponse response = new CrawlerResponse();
        response.setUrl(url);
        response.setAuthor(crawler.findAuthor());
        response.setDate(crawler.findDate());
        response.setTitle(crawler.findHeadline());
        response.setContent(crawler.findContent());

        return response;

    }

}
