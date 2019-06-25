package ru.shapovalov.sdm.repository.support;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.sql.Timestamp;
import java.util.*;

public class RepositoryUtils {

    public static <T> Collection<T> findAll(EntityManager em, Class<T> cl, JpaPredicateSupplier<List<Predicate>> createPredicates) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(cl);
        Root<T> root = cq.from(cl);

        Predicate[] predicates = createPredicates.get(cb, root, cq).toArray(new Predicate[]{});

        cq.select(root);
        cq.where(predicates);

        TypedQuery<T> query = em.createQuery(cq);

        List<T> list = query.getResultList();
        return list;
    }

    public static <T> Page<T> findAll(EntityManager em, Class<T> cl, int offset, int limit, JpaPredicateSupplier<List<Predicate>> createPredicates) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(cl);
        Root<T> root = cq.from(cl);

        Predicate[] predicates = createPredicates.get(cb, root, cq).toArray(new Predicate[]{});

        cq.select(root);
        cq.where(predicates);

        TypedQuery<T> query = em.createQuery(cq);

        query.setFirstResult(offset);
        if (limit != 0) {
            query.setMaxResults(limit);
        }

        List<T> list = query.getResultList();

        Long total = getCount(cl, cb, em, predicates);

        Page<T> pages = new Page<>(list, total);
        return pages;
    }

    public static <T> Long getCount(Class<T> cl, CriteriaBuilder cb, EntityManager em, Predicate[] predicates) {
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<T> root = cq.from(cl);

        cq.select(cb.count(root));
        cq.where(predicates);

        Long count = em.createQuery(cq).getSingleResult().longValue();
        return count;
    }

    public static <E, V> Optional<Predicate> createPredicateGenericEqual(CriteriaBuilder cb, Expression<E> expression, V value) {
        Predicate predicate = null;
        if (value != null) {
            predicate = cb.equal(expression, value);
        }
        return Optional.ofNullable(predicate);
    }

    public static <E, V> Optional<Predicate> createPredicateCollectionIn(Expression<E> expression, Collection<V> value) {
        Predicate predicate = null;
        if (CollectionUtils.isNotEmpty(value)) {
            predicate = expression.in(value);
        }
        return Optional.ofNullable(predicate);
    }

    public static <T> Optional<Predicate> createPredicateStringLike(CriteriaBuilder cb, Expression<T> expression, String value) {
        Predicate predicate = null;
        if (StringUtils.isNotBlank(value)) {
            predicate = cb.like(cb.lower(expression.as(String.class)), '%' + value.toLowerCase().replace('*', '%') + '%');
        }
        return Optional.ofNullable(predicate);
    }

    public static <E, V> Optional<Predicate> createPredicateTwoStringLike(CriteriaBuilder cb, Expression<E> expressionOne, Expression<V> expressionTwo, String value) {
        Predicate predicate = null;
        if (value != null) {
            Expression<String> exp1 = cb.concat(expressionOne.as(String.class), " ");
            exp1 = cb.lower(cb.concat(exp1, expressionTwo.as(String.class)));

            Expression<String> exp2 = cb.concat(expressionTwo.as(String.class), " ");
            exp2 = cb.lower(cb.concat(exp2, expressionOne.as(String.class)));

            predicate = cb.or(cb.like(exp1, "%" + value.toLowerCase().replace('*', '%') + "%"), cb.like(exp2, "%" + value.toLowerCase().replace('*', '%') + "%"));
        }
        return Optional.ofNullable(predicate);
    }

    public static Optional<List<Predicate>> createPredicateSearchCoordinates(CriteriaBuilder cb, Expression<Double> expressionOne, Expression<Double> expressionTwo, String pointA, String pointB) {
        List<Predicate> predicates = new ArrayList<>();

        if (pointA != null && pointB != null) {
            double[] pointAArray = stringsToPoint(pointA);
            double[] pointBArray = stringsToPoint(pointB);
            if (pointAArray.length == 2 && pointBArray.length == 2) {
                predicates = new LinkedList<>();
                double pointAx = pointAArray[0];
                double pointAy = pointAArray[1];
                double pointBx = pointBArray[0];
                double pointBy = pointBArray[1];

                Predicate predicateX = searchOnPoint(cb, expressionOne, pointAx, pointBx);
                predicates.add(predicateX);

                Predicate predicateY = searchOnPoint(cb, expressionTwo, pointAy, pointBy);
                predicates.add(predicateY);
            }
        }
        return Optional.ofNullable(predicates);
    }

    public static Optional<Predicate> createPredicateDateBetween(CriteriaBuilder cb, Expression<Timestamp> expression, Date dateFrom, Date dateTo) {
        Predicate predicate = null;
        if ((dateFrom != null) && (dateTo != null)) {
            predicate = cb.between(expression.as(Timestamp.class), new Timestamp(dateFrom.getTime()), new Timestamp(dateTo.getTime()));
        }
        return Optional.ofNullable(predicate);
    }

    public static Optional<List<Predicate>> createPredicateTwoDateBetween(CriteriaBuilder cb, Expression<Timestamp> expressionOne, Expression<Timestamp> expressionTwo, Date dateFrom, Date dateTo) {
        List<Predicate> predicates = new ArrayList<>();

        if ((dateFrom != null) && (dateTo != null)) {
            Predicate validFromPredicate = cb.greaterThanOrEqualTo(expressionOne, new Timestamp(dateFrom.getTime()));
            predicates.add(validFromPredicate);

            Predicate validToPredicate = cb.lessThanOrEqualTo(expressionTwo, new Timestamp(dateTo.getTime()));
            predicates.add(validToPredicate);
        }

        return Optional.ofNullable(predicates);
    }

    public static Optional<Predicate> createPredicateDateGreaterOrEqual(CriteriaBuilder cb, Expression<Timestamp> expression, Date date) {
        Predicate predicate = null;
        if ((date != null)) {
            predicate = cb.greaterThanOrEqualTo(expression, new Timestamp(date.getTime()));
        }
        return Optional.ofNullable(predicate);
    }

    public static Optional<Predicate> createPredicateDateLessOrEqual(CriteriaBuilder cb, Expression<Timestamp> expression, Date date) {
        Predicate predicate = null;
        if ((date != null)) {
            predicate = cb.lessThanOrEqualTo(expression, new Timestamp(date.getTime()));
        }
        return Optional.ofNullable(predicate);
    }

    public static Optional<Predicate> createPredicateBitmaskEntry(CriteriaBuilder cb, Expression<Short> expression, Short value) {
        Predicate predicate = null;
        if (value != null) {
            Expression<Integer> div = cb.quot(expression, value).as(Integer.class);
            Expression<Integer> mod = cb.mod(div, 2);
            predicate = cb.equal(mod, 1);
        }
        return Optional.ofNullable(predicate);
    }

    private static Predicate searchOnPoint(CriteriaBuilder cb, Expression<Double> expression, double pointA, double pointB) {
        Predicate predicate;
        if (pointA <= pointB) {
            predicate = cb.between(expression, pointA, pointB);
        } else {
            predicate = cb.between(expression, pointB, pointA);
        }
        return predicate;
    }

    private static double[] stringsToPoint(String arg) {
        String[] tempArray = arg.split(",");
        double pointX = Double.parseDouble(tempArray[0]);
        double pointZ = Double.parseDouble(tempArray[1]);

        double[] points = new double[2];
        points[0] = pointX;
        points[1] = pointZ;
        return points;
    }
}


