package br.com.trix.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import br.com.trix.model.Route; 

public interface RouteRepository extends MongoRepository<Route, String> {

}
