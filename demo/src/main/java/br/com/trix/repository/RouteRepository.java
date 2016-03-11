package br.com.trix.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.trix.model.Route; 

@RepositoryRestResource(collectionResourceRel="routes",path="routes")
public interface RouteRepository extends MongoRepository<Route, String> {

}
