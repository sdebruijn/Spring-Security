package nl.project.repositories;

import org.springframework.data.repository.CrudRepository;

import nl.project.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{
}
