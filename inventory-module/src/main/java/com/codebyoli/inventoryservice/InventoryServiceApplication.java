package com.codebyoli.inventoryservice;

import com.codebyoli.inventoryservice.Model.Inventory;
//import com.codebyoli.inventoryservice.repo.InventoryRepo;
import com.codebyoli.inventoryservice.repo.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	//create data in database
	//Bean will try to load data
	@Bean
		public CommandLineRunner loadData(InventoryRepository inventoryRepository){
			return args ->{
			Inventory inventory=new Inventory();
			inventory.setSkuCode("iphone_15");
			inventory.setQty(100);

			Inventory inventory1=new Inventory();
			inventory1.setSkuCode("iphone_14");
			inventory1.setQty(0);
			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
			};
		}

	}



