package com.golzstore.springstore.repositories;

import com.golzstore.springstore.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class ProductCriteriaRepositoryImpl implements ProductCriteriaRepository {
    @PersistenceContext
    private final EntityManager em;
    private final EntityManagerFactory entityManagerFactory;


    @Override
    public List<Product> findProductsByCriteria(String name, BigDecimal minPrice, BigDecimal maxPrice) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> root = cq.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();
        if(name!=null) {
            predicates.add(cb.like(root.get("name"), "%"+name+"%"));
        } if (minPrice != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("price"), minPrice));
        } if (maxPrice != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("price"), maxPrice));
        }


        cq.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        return em.createQuery(cq).getResultList();


    }


}
