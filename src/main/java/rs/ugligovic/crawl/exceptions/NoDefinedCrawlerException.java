/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ugligovic.crawl.exceptions;

/**
 *
 * @author User
 */
public class NoDefinedCrawlerException extends RuntimeException {

    public NoDefinedCrawlerException() {

    }

    public NoDefinedCrawlerException(String message) {
        super(message);
    }

}
