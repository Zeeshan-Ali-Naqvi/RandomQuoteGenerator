package io.kugelblitz.randomquotegenerator.controllers;

import io.kugelblitz.randomquotegenerator.entity.Quote;
import io.kugelblitz.randomquotegenerator.services.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/quoterest")
public class QuoteController {

    private final QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @PostMapping("/quote")
    public ResponseEntity<Quote> createQuote(@RequestBody Quote quote) {
        try {
            Quote _quote = quoteService.createQuote(quote);
            return new ResponseEntity<>(_quote, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/quotes/{id}")
    public ResponseEntity<Quote> getQuoteById(@PathVariable String id) {
        try {
            if(quoteService.getQuoteById(id).isPresent()) {
                return new ResponseEntity<>(quoteService.getQuoteById(id).get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/quotes")
    public ResponseEntity<List<Quote>> getAllQuotes() {
        try {
            List<Quote> quotes = quoteService.getAllQuotes();
            return new ResponseEntity<>(quotes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
