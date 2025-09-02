package com.golzstore.springstore.services;

import com.golzstore.springstore.entities.*;
import com.golzstore.springstore.repositories.*;
import com.golzstore.springstore.repositories.specifications.ProductSpec;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Service
public class UserService {
    private final EntityManager entityManager;
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public void showEntityStates() {

        var user = User.builder()
                .name("7John")
                .email("7JohnJames@example.com")
                .password("7password1")
                .build();
        if (entityManager.contains(user))
            System.out.println("Persistent");
        else
            System.out.println("Transient / Detached");

        userRepository.save(user);

    }

    @Transactional
    public void showRelatedEntities() {
        var profile = profileRepository.findById(2L)
                .orElseThrow();
        System.out.println(profile.getBio());
    }

    public void fetchAddress() {
        var address = addressRepository.findById(1L)
                .orElseThrow();
    }
    public void fetchSortedProducts() {
        var sort = Sort.by("name").and(
                Sort.by("price").descending()
        );

        productRepository.findAll(sort).forEach(System.out::println);
    }
    public void fetchProductsByCriteria() {
        var products = productRepository.findProductsByCriteria("prod", BigDecimal.valueOf(1), null);
        products.forEach(System.out::println);

    }
    public void fetchPaginatedProducts(int pageNumber, int size) {
        PageRequest pageRequest = PageRequest.of(pageNumber, size);
        Page<Product> page = productRepository.findAll(pageRequest);

        var products = page.getContent();
        products.forEach(System.out::println);

        var totalPages = page.getTotalPages();
        var totalElements = page.getTotalElements();
        System.out.println("Total Pages: " + totalPages);
        System.out.println("Total Elements: " + totalElements);
    }
    public void fetchProductsBySpecifications(String name, BigDecimal minPrice, BigDecimal maxPrice) {
        Specification<Product> spec = Specification.anyOf();

        if (name != null) {
            spec = spec.and(ProductSpec.hasName(name));
        }
        if (minPrice != null) {
            spec = spec.and(ProductSpec.hasPriceGreaterThanOrEqualTo(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpec.hasPriceLessThanOrEqualTo(maxPrice));
        }

        productRepository.findAll(spec).forEach(System.out::println);
    }

    public void persistRelated() {
        var user = User.builder()
                .name("John")
                .email("JohnSnow@gmail.com")
                .password("JohnSnow123")
                .build();

        var address = Address.builder()
                .street("123 Main St")
                .city("lubekke")
                .state("NY")
                .zip("12345")
                .build();

        user.addAddress(address);

        userRepository.save(user);
        addressRepository.save(address);
    }

    @Transactional
    public void deleteRelated() {
        var user = userRepository.findById(11L)
                .orElseThrow();
        var address = user.getAddresses()
                .getFirst();
        user.removeAdress(address);
        userRepository.save(user);
    }

    @Transactional
    public void manageProducts() {
        var category = categoryRepository.findById((byte) 1)
                .orElse(categoryRepository.save(new Category("category 1")));
        var product = Product.builder()
                .name("bread")
                .price(BigDecimal.valueOf(2.79))
                .description("just Bread.")
                .category(category)
                .build();
        productRepository.save(product);
    }

    @Transactional

    public void updateProductPrices() {
        productRepository.updatePriceByCategory(BigDecimal.valueOf(999), (byte) 2);
    }

    @Transactional
    public void fetchProducts() {
        var product = new Product();
        product.setName("productttt");

        var matcher = ExampleMatcher.matching()
                .withIncludeNullValues()
                .withIgnorePaths("id", "description")
                .withIncludeNullValues();

        var example = Example.of(product, matcher);
        var products = productRepository.findAll(example);
        products.forEach(System.out::println);
    }

    @Transactional
    public void fetchUsers() {
        var user = userRepository.findAllWithAddresses();
        user.forEach(u -> {
            System.out.println(u);
            u.getAddresses()
                    .forEach(System.out::println);
        });
    }

    @Transactional
    public void addSomeUsers() {
        var user1 = User.builder()
                .name("Alex 1")
                .email("alex@gmail.com")
                .password("alex123")
                .build();
        var user2 = User.builder()
                .name("Betty 2")
                .email("betty@gmail.com")
                .password("betty123")
                .build();
        var user3 = User.builder()
                .name("Carla 3")
                .email("Carla@gmail.com")
                .password("carla123")
                .build();
        var profile1 = Profile.builder()
                .bio("Alex bio")
                .user(user1)
                .phone("+77777777")
                .loyaltyPoints(5)
                .build();
        var profile2 = Profile.builder()
                .bio("Betty bio")
                .user(user2)
                .phone("+88888888")
                .loyaltyPoints(10)
                .build();
        var profile3 = Profile.builder()
                .bio("Carla bio")
                .user(user3)
                .phone("+99999999")
                .loyaltyPoints(20)
                .build();

        userRepository.saveAll(List.of(user1, user2, user3));
        profileRepository.saveAll(List.of(profile1, profile2, profile3));

    }

    @Transactional
    public void printLoyalProfiles() {
        var users = userRepository.findLoyalUsers(2);
        users.forEach(p -> System.out.println(p.getId() + ": " + p.getEmail()));
    }
}
