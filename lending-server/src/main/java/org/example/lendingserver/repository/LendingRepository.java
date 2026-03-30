package org.example.lendingserver.repository;

import org.example.lendingserver.entity.Lending;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LendingRepository extends MongoRepository<Lending, String> {
    List<Lending> findByReaderId(String readerId);
    List<Lending> findByBookId(String bookId);
}