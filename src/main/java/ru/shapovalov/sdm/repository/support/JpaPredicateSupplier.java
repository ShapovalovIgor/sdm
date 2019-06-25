package ru.shapovalov.sdm.repository.support;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@FunctionalInterface
public interface JpaPredicateSupplier<T> {
    T get(CriteriaBuilder cb, Root<?> root, CriteriaQuery<?> cq);
}
