package dev.example.productservice;

import dev.example.productservice.inheritancedemo.singletable.Mentor;
import dev.example.productservice.inheritancedemo.singletable.MentorRepository;
import dev.example.productservice.models.Category;
import dev.example.productservice.models.Price;
import dev.example.productservice.models.Product;
import dev.example.productservice.repository.CategoryRepository;
import dev.example.productservice.repository.PriceRepository;
import dev.example.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class ProductserviceApplication implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final PriceRepository priceRepository;

    public ProductserviceApplication(ProductRepository productRepository,CategoryRepository categoryRepository,
                                     PriceRepository priceRepository){
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.priceRepository = priceRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductserviceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Category category = new Category();
        category.setName("Apple Devices");
        Category saveCategory = categoryRepository.save(category);

        // there is new category .. but before inserting this category to the
        // database, we need to check the database.
        // case 1 : insert if it is a new category
        // case 2 : don't do anything if there is no category
        /*
        Category saveCategory = new Category() ;
        List<Category> categoryList = categoryRepository.findAllByName("Apple Devices");
        boolean isCategoryPresent = false;
        for(Category c : categoryList){
            if(c.getName().equals("Apple Devices")){
                System.out.println("Category is already present in DB...!!!");
                isCategoryPresent = true;
            }
        }
        if(isCategoryPresent == false){
            Category category = new Category();
            category.setName("Apple Devices");
            saveCategory = categoryRepository.save(category);
        }

         */



        Price price = new Price("Ruppee",10);
        //Price savePrice = priceRepository.save(price);

        Product product = new Product();
        product.setTitle("Iphone 15 Pro");
        product.setDescription("Best phone forever");
        product.setCategory(saveCategory);
        product.setPrice(price);
        productRepository.save(product);


        Product product1 = productRepository.findAllById(UUID.fromString("53ab81f8-6097-4574-b035-acae1bd83309"));
        System.out.println("Your producttttt......");
        System.out.println(product1);

        System.out.println("findByTitle.. Native Query...!!");
        List<Product> productList = productRepository.findAllByTitle("Iphone 15 Pro");
        System.out.println("Size of productList is " + productList.size());

        for(Product p : productList){
            System.out.println("Hey you are inside....");
            System.out.println(p);
        }


        //deleting a price id from product table
        //productRepository.deleteById(UUID.fromString("f5d0bf32-bddf-402d-9b01-61baf6edd4e2"));


//        Mentor mentor = new Mentor();
//        mentor.setName("Naman");
//        mentor.setEmail("Naman@scaler.com");
//        mentor.setAverageRating(4.44);
//        mentorRepository.save(mentor);
    }
}
