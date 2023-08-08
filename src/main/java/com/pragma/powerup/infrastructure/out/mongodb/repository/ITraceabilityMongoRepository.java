package com.pragma.powerup.infrastructure.out.mongodb.repository;

import com.pragma.powerup.infrastructure.out.mongodb.entity.Traceability;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITraceabilityMongoRepository extends MongoRepository<Traceability, String> {

    List<Traceability> findByOrderId(String orderId);

    Optional<Traceability> findByOrderIdAndNewStatus(String orderId, String status);
}