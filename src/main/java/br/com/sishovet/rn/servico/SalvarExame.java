/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sishovet.rn.servico;


import br.com.sishovet.entidade.pojo.Hemodialise;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 *
 * @author BPMLAB-01
 */
public class SalvarExame {

    public static Hemodialise salvarHemodialise() {
        Hemodialise hemodialise = new Hemodialise();
        hemodialise.setPCT(5.0);
        hemodialise.setCHCM(5.5);
        
        MongoClient mongoClient;

        
            // connect to the local database server
        mongoClient = MongoClients.create();
        
        
        
        // create codec registry for POJOs
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        
        // get handle to "mydb" database
        MongoDatabase database = mongoClient.getDatabase("RExame").withCodecRegistry(pojoCodecRegistry);
        
        // get a handle to the "hemodialises" collection
        MongoCollection<Hemodialise> collection = database.getCollection("hemodialises", Hemodialise.class);
        
        System.out.println("Original hemodialise Model: " + hemodialise);
        collection.insertOne(hemodialise);

        // Person will now have an ObjectId
        System.out.println("Mutated hemodialise Model: " + hemodialise);
        
        return hemodialise;
    }
    
    public static void main(String[] args) {
        System.out.println(salvarHemodialise());
    }

}
