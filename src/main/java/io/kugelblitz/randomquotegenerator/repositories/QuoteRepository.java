package io.kugelblitz.randomquotegenerator.repositories;

import io.kugelblitz.randomquotegenerator.model.Quote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface QuoteRepository extends CrudRepository<Quote, Long> {
}
