package com.demo.openshift.redhat.SpringDemo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SpringDemoController {

   @Autowired
    private OwnerRepository ownerRepository;

    @GetMapping("/owner")
    public Collection<Owner> getOwners() {

        System.out.println("[Debug] Received Request - getAllOwners()");
        Collection<Owner> results = ownerRepository.findByLastName("");
        return results;
    }

    @GetMapping("/owner/{ownerName}")
    public Collection<Owner> getOwners(@PathVariable("ownerName") String ownerName) {

        System.out.println("[Debug] Received Request - getSpecificOwner()");
        Collection<Owner> results = ownerRepository.findByLastName(ownerName);
        return results;
    }

    @PostMapping("/owner/add")
    public String addOwners(@RequestParam("ownerFirstName") String ownerFirstName, @RequestParam("ownerLastName")String ownerLastName) {

        System.out.println("[Debug] Received Request - addOwner()");
        Owner owner = new Owner();
        owner.setFirstName(ownerFirstName);
        owner.setLastName(ownerLastName);
        owner.setAddress("Address Line 1");
        owner.setCity("City");
        owner.setId(121);


        //Random Generator function to generate 10 digit random mobile number
        Random rand = new Random();
        long drand = (long)(rand.nextDouble()*10000000000L);

        owner.setTelephone(String.valueOf(drand));

        for(int i =0; i<99999; i++)
        {
            Random random = new Random();
            random.setSeed(1234567890);
            int randNum = random.nextInt();
        }

        ownerRepository.save(owner);

        return "Owner Added Successfully";
    }
}
