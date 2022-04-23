package io.kugelblitz.randomquotegenerator.controllers;

import io.kugelblitz.randomquotegenerator.model.Quote;
import io.kugelblitz.randomquotegenerator.repositories.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/quoterest")
public class QuoteController {

    @Autowired
    QuoteRepository quoteRepository;

    @PostMapping("/quote")
    public ResponseEntity<Quote> createQuote(@RequestBody Quote quote) {
        try {
            Quote _quote = quoteRepository.save(new Quote(quote.getId(), quote.getQuote(), quote.getAuthor()));
            return new ResponseEntity<>(_quote, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/quotes")
    public ResponseEntity<List<Quote>> getAllQuotes() {
        try {
            List<Quote> quotes = new ArrayList<>();
            quoteRepository.findAll().forEach(quotes::add);

            return new ResponseEntity<>(quotes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
