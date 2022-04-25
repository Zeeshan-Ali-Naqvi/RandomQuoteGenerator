package io.kugelblitz.randomquotegenerator.services;


import io.kugelblitz.randomquotegenerator.entity.Quote;
import io.kugelblitz.randomquotegenerator.repositories.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuoteService {
    private final QuoteRepository quoteRepository;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public Quote createQuote(Quote quote) {
        return quoteRepository.save(quote);
    }

    public List<Quote> getAllQuotes() {
        List<Quote> quoteList = new ArrayList<>();
        for(Quote quote : quoteRepository.findAll()) {
            quoteList.add(quote);
        }
        //quoteRepository.findAll().forEach(quoteList::add);
        return quoteList;
    }

    public Optional<Quote> getQuoteById(String id) {
        return quoteRepository.findById(Long.valueOf(id));
    }
}
