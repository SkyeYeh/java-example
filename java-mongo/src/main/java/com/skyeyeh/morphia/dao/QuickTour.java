package com.skyeyeh.morphia.dao;

import com.mongodb.MongoClient;
import com.skyeyeh.morphia.model.Employee;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import java.net.UnknownHostException;
import java.util.List;

/**
 * This class is used in the Quick Tour documentation and is used to demonstrate various Morphia features.
 */
public final class QuickTour {
    private QuickTour() {
    }

    public static void main(final String[] args) throws UnknownHostException {
        final Morphia morphia = new Morphia();

        // tell morphia where to find your classes
        // can be called multiple times with different packages or classes
        morphia.mapPackage("com.skyeyeh.morphia.model");

        // create the Datastore connecting to the database running on the default port on the local host
        final Datastore datastore = morphia.createDatastore(new MongoClient("192.168.99.100", 27017), "morphia_example");
        datastore.getDB().dropDatabase();
        datastore.ensureIndexes();

        final Employee elmer = new Employee("Elmer Fudd", 50000.0);
        datastore.save(elmer);

        final Employee daffy = new Employee("Daffy Duck 邱先生", 40000.0);
        datastore.save(daffy);

        final Employee pepe = new Employee("Pepé Le Pew", 25000.0);
        datastore.save(pepe);

        elmer.getDirectReports().add(daffy);
        elmer.getDirectReports().add(pepe);

        datastore.save(elmer);

        Query<Employee> query = datastore.createQuery(Employee.class);
        final List<Employee> employees = query.asList();

//        Assert.assertEquals(3, employees.size());

        List<Employee> underpaid = datastore.createQuery(Employee.class)
                .filter("salary <=", 30000)
                .asList();
//        Assert.assertEquals(1, underpaid.size());

        underpaid = datastore.createQuery(Employee.class)
                .field("salary").lessThanOrEq(30000)
                .asList();
//        Assert.assertEquals(1, underpaid.size());

        final Query<Employee> underPaidQuery = datastore.createQuery(Employee.class)
                .filter("salary <=", 30000);
        final UpdateOperations<Employee> updateOperations = datastore.createUpdateOperations(Employee.class)
                .inc("salary", 10000);

        final UpdateResults results = datastore.update(underPaidQuery, updateOperations);

//        Assert.assertEquals(1, results.getUpdatedCount());

        final Query<Employee> overPaidQuery = datastore.createQuery(Employee.class)
                .filter("salary >", 100000);
        datastore.delete(overPaidQuery);
    }
}
